package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StrafeToLineCmd extends Command {
 	
	/**
	 * driveSpeed is the speed that the robot drives at when it is going straight 
	 */
	private double driveSpeed;

	private double currentDriveSpeed;
	private double currentTurnSpeed;

	/**
	 * The speed of the wheels that you want to be slower when turning while moving forward.
	 * Set to left side if turning right, set to right side if turning left.
	 */
	private double turnSpeed = 0.4;
	/**
	 * targetDistance is how far the robot should travel
	 */
	private double targetDistance;
	/**
	 *  acceptedDistanceOffset is the acceptable amount of distance from the goal
	 */
	private double acceptedDistanceOffset;
	/**
	 * currentDistanceOffset the difference between the goal and location of the robot
	 */
	private double currentDistanceOffset;
	/**
	 * If the Robot is further from the goal than MAXSPEEDTHRESH, it moves full speed 
	 */
	private final double MAXSPEEDTHRESH = 24;
	/**
	 * Current gyro angle. Used for self correcting our angle as we move forwards to make sure we're going straight.
	 */
	private double currentAngle;
	/**
	 * How far our current angle is away from 0.
	 */
	
	private boolean needReInit = true;

	private final double PROP_CON = 2.8;
  private final double PROP_CON_GYRO = 75;
  
  NetworkTableEntry avgx;
	
	/**
	 * Constructor
	 * 
	 * @param targetDist			The total amount of distance you want to travel
	 * @param acceptedDistOffset	The allowable error between goal and final position of the Robot
	 */
	public StrafeToLineCmd(double acceptedDistOffset, double speed) {
	
		requires(Robot.dt);
		
		this.acceptedDistanceOffset = acceptedDistOffset;
		this.driveSpeed = speed;
	
	}
	
	protected void initialize() {
		
		currentDistanceOffset = targetDistance;
		Robot.dt.setEncoders(0);
		Robot.dt.resetGyro();
    needReInit = false;
    
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable visionTable = inst.getTable("visionTable");
    avgx = visionTable.getEntry("avgx");
	
	}
	
	/**
	 * This method calculate the speed of the motor based off of currentAngleOffset
	 */
	protected void execute() {
		if (needReInit){
			initialize();
		}
		currentDistanceOffset = avgx.getDouble(1000) / 4;
		DriverStation.reportWarning("Distance Traveled: " + Robot.dt.getHorDistanceTraveled(), false);
		DriverStation.reportWarning("Gyro Angle: " + Robot.dt.getGyroAngle(), false);
		currentAngle = Robot.dt.getGyroAngle();



		currentDriveSpeed = driveSpeed;
		if (Math.abs(currentDistanceOffset) < MAXSPEEDTHRESH){
			currentDriveSpeed /= 2;
		}

		if(currentDistanceOffset < 0){
			currentDriveSpeed *= -1;
		}

		currentTurnSpeed = currentDriveSpeed / PROP_CON + Robot.dt.getGyroAngle() / PROP_CON_GYRO;

		Robot.dt.drive(-currentTurnSpeed, currentTurnSpeed, -currentDriveSpeed);
	}

	protected boolean isFinished() {
		//DriverStation.reportWarning("First Condition value is " + (Math.abs(currentAngleOffset) < acceptedAngleOffset), false);
		return (Math.abs(currentDistanceOffset) < acceptedDistanceOffset);
		
	}
	
	protected void end() {
		needReInit = true;
		//DriverStation.reportWarning("yo", false);

		Robot.dt.stop();
	
	}
	
	protected void interrupted() {
	
		end();
		
	}
	
}
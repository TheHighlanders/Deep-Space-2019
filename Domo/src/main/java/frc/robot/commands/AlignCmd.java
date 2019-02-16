/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class AlignCmd extends Command {
  /**
	 * turnSpeed is the speed that the robot turns at depends on currentAngleOffset 
	 */
	private double turnSpeed;
	/**
	 * targetRotation is how far we want to turn the robot from the inital conditions 
	 */
	private double targetRotation;
	/**
	 *  acceptedAngleOffset is the difference between desired and current positions of our robot at which point this command will stop running.
	 */
	private double acceptedAngleOffset;
	/**
	 * currentAngleOffset the difference between targetRotion and current angle of the robot
	 */
	private double currentAngleOffset;
	/**
	 * MAXSPEEDTHRESH is the angleOffSet where you rotate full speed  
	 */
	private final double MAXSPEEDTHRESH = 40;
	
  private boolean needReInit = true;
  
  NetworkTableEntry angle1;
NetworkTableEntry angle2;
NetworkTableEntry avgx;
	
  /**
	 * Constructor
	 * 
	 * @param targetRotation			Degrees to turn the robot (pos = clockwise, neg = counterclockwise)
	 * @param acceptedAngleOffset		The difference between desi
	 * red and current positions of our robot at which point this command will stop running.
	 */
	public AlignCmd(double acceptedAngleOffset) {
	
		requires(Robot.dt);
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable visionTable = inst.getTable("visionTable");
    angle1 = visionTable.getEntry("angle1");
    angle2 = visionTable.getEntry("angle2");
    avgx = visionTable.getEntry("avgx");

    
		this.acceptedAngleOffset = acceptedAngleOffset;
	
	}

  protected void initialize() {

    double angle1i = angle1.getDouble(1000);
    double angle2i = angle2.getDouble(1000);
    double avgxi = avgx.getDouble(1000);
    
    targetRotation = (angle1i + angle2i) / 2;
		Robot.dt.resetGyro();
		currentAngleOffset = targetRotation - Robot.dt.getGyroAngle();
    needReInit = false;
    
    DriverStation.reportWarning("AlignCmd sees angle1 in visionTable as: " + angle1.getDouble(1000), false);
    DriverStation.reportWarning("AlignCmd sees angle2 in visionTable as: " + angle2.getDouble(1000), false);
    DriverStation.reportWarning("AlignCmd sees avgx in visionTable as: " + avgx.getDouble(1000), false);
	
	}
	
	/**
	 * This method calculate the speed of the motor based off of currentAngleOffset
	 */
	protected void execute() {
    DriverStation.reportWarning("AlignCmd sees angle1 in visionTable as: " + angle1.getDouble(1000), false);
    DriverStation.reportWarning("AlignCmd sees angle2 in visionTable as: " + angle2.getDouble(1000), false);
    DriverStation.reportWarning("AlignCmd sees avgx in visionTable as: " + avgx.getDouble(1000), false);

		if (needReInit){
			initialize();
		}
    currentAngleOffset = targetRotation - Robot.dt.getGyroAngle();
    //DriverStation.reportWarning("GyroAngle: " + Robot.dt.getGyroAngle(), false);
		
		if (currentAngleOffset >= MAXSPEEDTHRESH){
			Robot.dt.drive(0.43,-0.43, 0);
		}
		
		else if (currentAngleOffset <= -MAXSPEEDTHRESH){
			Robot.dt.drive(-0.43,0.43, 0);
		}
	
		else { 
			//turnSpeed = Math.pow(currentAngleOffset, 1/3) / 6;
			//DriverStation.reportWarning("turnSpeed: " +turnSpeed + "currentAngleOffset: " +currentAngleOffset, false);
			//Robot.dt.driveLR(turnSpeed,-turnSpeed);
      //turnSpeed = currentAngleOffset / 200;
      //DriverStation.reportWarning("turnSpeed: " +turnSpeed + ", currentAngleOffset: " +currentAngleOffset, false);
			if(currentAngleOffset < 0) {
          
	    		Robot.dt.drive(-0.15, 0.15, 0);
      } 
      else if(currentAngleOffset > 0) {
	    	  Robot.dt.drive(0.15, -0.15, 0);	
	    }
				
		}
		
	}

	protected boolean isFinished() {
		//DriverStation.reportWarning("First Condition value is " + (Math.abs(currentAngleOffset) < acceptedAngleOffset), false);
		return ((Math.abs(currentAngleOffset) < acceptedAngleOffset) && (Math.abs(Robot.dt.getGyroRate()) <= 10));
		
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

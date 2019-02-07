package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
* The interface between the robot code and the actuators and sensors involved
* in moving the robot. Right now this is just the motors and gyro, but this
* will probably grow to include encoders.
* 
* TODO: Add a function that slows down the robot based on how high the elevator
* is.
* 
* @author Max Nadeau
*/

public class Climber extends Subsystem {

	// Instantiating TalonSRX motor controllers at CAN ports
	// 1 through 4.
	public WPI_VictorSPX climber1;
	private WPI_VictorSPX climber2;
	public WPI_VictorSPX climber3;
	
	/**
	 * Constructor, sets up motors, prevents brownouts, and minimizes pedestrian
	 * casualties.
	 */
    public Climber() {
    	
    	climber1 = new WPI_VictorSPX(RobotMap.FRONT_CLIMBER);
    	climber2 = new WPI_VictorSPX(RobotMap.LEFT_DRIVE2);
    	climber3 = new WPI_VictorSPX(RobotMap.RIGHT_DRIVE1);
    	
    	climber1.configOpenloopRamp(.5, 0);
    	climber2.configOpenloopRamp(.5, 0);
    	climber3.configOpenloopRamp(.5, 0);
    	
    	climber1.setNeutralMode(NeutralMode.Brake);
    	climber2.setNeutralMode(NeutralMode.Brake);
    	climber3.setNeutralMode(NeutralMode.Brake);
    	
    }
    
    /**
	 * ArcadeDriveCmd will always run when other commands are not busy. This
	 * will allow operator control when the robot is not driving itself around.
	 */
    public void initDefaultCommand() {
    	
    }
    
    /**
	 * Updates the motors with what speed to drive at.
	 * 
	 * @param leftPower
	 *            Double speed of left motors. Range -1 to 1
	 * @param rightPower
	 *            Double speed of right motors. Range -1 to 1
	 */
    public void moveFront(double power){
    	
  	//  	DriverStation.reportWarning("Left Power: " + leftPower, false);
  	//  	DriverStation.reportWarning("Right Power: " + rightPower, false);
    	//DriverStation.reportWarning("FOR: " + forwardOrReverse, false);
    	
    	climber1.set(power);
    }
    public void moveBack(double power){
    	
		//  	DriverStation.reportWarning("Left Power: " + leftPower, false);
		//  	DriverStation.reportWarning("Right Power: " + rightPower, false);
			  //DriverStation.reportWarning("FOR: " + forwardOrReverse, false);
			  
			  climber2.set(power);
			  climber3.set(power);
		  }
	
		  /**
	 * Sets the motors to be off.
	 */
    public void stop() {
    	
		this.moveFront(0);
		this.moveBack(0);
    	
    }
}


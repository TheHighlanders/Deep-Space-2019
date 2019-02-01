package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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
		
	// Gyro sensor
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static int forwardOrReverse = -1;
	
	/**
	 * Constructor, sets up motors, prevents brownouts, and minimizes pedestrian
	 * casualties.
	 */
    public Climber() {
    	
    	climber1 = new WPI_VictorSPX(RobotMap.LEFT_DRIVE1);
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
    	
    	this.move(0,0);
    	
    }
    
    /**
	 * Calibrates gyro (takes 5 seconds while robot does nothing) Do this when
	 * robot first turns on.
	 */
    public void calibrateGyro() {
    	
    	gyro.calibrate();
    	
    }
    
    /**
	 * Sets the drivetrain gyro back to 0 degrees
	 */
    public void resetGyro() {
    	
    	gyro.reset();
    	
    }
    
    /**
	 * @return the current rate of turning in degrees per second
	 */
    public double getGyroRate() {
    	
    	return gyro.getRate();
    	
    }
    
	/**
	 * 
	 * @return gets an approximation of the gyro angle since reset was last
	 *         called from an accumulation using the FPGA. Will accumulate error
	 *         over time.
	 * 
	 */
    public double getGyroAngle() {
    	
    	return gyro.getAngle();
    	
    }
    
    /**
	 * Sets the drivetrain encoders back to 0 pulses
	 * Only one message will print here
	 * Leave this at -3000?
	 */
    public void setEncoders(int pulses) {
		//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
    	//DriverStation.reportWarning("TEsting", false);
    }
    
}


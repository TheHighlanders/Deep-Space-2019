package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
* The interface between the robot code and the actuators and sensors involved
* in moving the robot. Right now this is just the motors and gyro, but this
* will probably grow to include encoders.
* 
* @author Max Nadeau
*/

public class DriveTrain extends Subsystem {

	// Instantiating TalonSRX motor controllers at CAN ports
	// 1 through 4.
	public WPI_TalonSRX left1;
	private WPI_VictorSPX left2;
	public WPI_TalonSRX right1;
	private WPI_VictorSPX right2;
	public WPI_TalonSRX middle1;
	public WPI_VictorSPX middle2;
		
	// Gyro sensor
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public static int forwardOrReverse = -1;

	private static double rampTime = 0.5;
	
	/**
	 * Constructor, sets up motors, prevents brownouts, and minimizes pedestrian
	 * casualties.
	 */
    public DriveTrain() {
    	
    	left1 = new WPI_TalonSRX(RobotMap.LEFT_DRIVE1);
    	left2 = new WPI_VictorSPX(RobotMap.LEFT_DRIVE2);
    	right1 = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE1);
			right2 = new WPI_VictorSPX(RobotMap.RIGHT_DRIVE2);
			middle1 = new WPI_TalonSRX(RobotMap.MIDDLE_DRIVE1);
			middle2 = new WPI_VictorSPX(RobotMap.MIDDLE_DRIVE2);

    	
    	left1.configOpenloopRamp(rampTime, 0);
    	left2.configOpenloopRamp(rampTime, 0);
    	right1.configOpenloopRamp(rampTime, 0);
			right2.configOpenloopRamp(rampTime, 0);
			middle1.configOpenloopRamp(rampTime, 0);
			middle2.configOpenloopRamp(rampTime, 0);
		
    	
    	left1.setNeutralMode(NeutralMode.Coast);
    	left2.setNeutralMode(NeutralMode.Coast);
    	right1.setNeutralMode(NeutralMode.Coast);
			right2.setNeutralMode(NeutralMode.Coast);
			middle1.setNeutralMode(NeutralMode.Coast);
			middle2.setNeutralMode(NeutralMode.Coast);
			
			left2.follow(left1);
			right2.follow(right1);
			middle2.follow(middle1);

    	
    	middle1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	//right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	
    }
    
    /**
	 * ArcadeDriveCmd will always run when other commands are not busy. This
	 * will allow operator control when the robot is not driving itself around.
	 */
    public void initDefaultCommand() {
    	
    	setDefaultCommand(new ArcadeDriveCmd());
    	
    }
    
    /**
	 * Updates the motors with what speed to drive at.
	 * 
	 * @param leftPower
	 *            Double speed of left motors. Range -1 to 1
	 * @param rightPower
	 *            Double speed of right motors. Range -1 to 1
	 */
    public void drive(double leftPower, double rightPower, double middlePower) {
    	
  //  	DriverStation.reportWarning("Left Power: " + leftPower, false);
  //  	DriverStation.reportWarning("Right Power: " + rightPower, false);
    	//DriverStation.reportWarning("FOR: " + forwardOrReverse, false);
    	
    	left1.set(leftPower);
    	left2.set(leftPower);
    	right1.set(-rightPower);
    	right2.set(-rightPower);
			middle1.set(-middlePower);
			middle2.set(-middlePower);

		//DriverStation.reportWarning("Left: " + leftPower + " Right: " + rightPower + " Middle: " + middlePower, false); 
    }
    
    /**
	 * Sets the motors to be off.
	 */
    public void stop() {
    	
    	this.drive(0, 0, 0);
    	
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
    	
    	//left1.getSensorCollection().setPulseWidthPosition(pulses, 0);
			//right1.getSensorCollection().setPulseWidthPosition(pulses, 0);	
			middle1.getSensorCollection().setPulseWidthPosition(pulses, 0);
    
			//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
    }
    
    /**
	 * 
	 * @return gets an approximation of the distance traveled in inches
	 *         since reset was last called. Will accumulate error
	 *         over time.
	 * 
	 */
    public double getHorDistanceTraveled() {
    	//DriverStation.reportWarning("Left:" + (-(left1.getSensorCollection().getPulseWidthPosition())) + " Right: " + right1.getSensorCollection().getPulseWidthPosition(), false);
    	return -((double) middle1.getSensorCollection().getPulseWidthPosition() / 4096.0) * 6 * Math.PI;
    	
    }
    
}


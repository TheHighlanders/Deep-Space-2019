/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Climber;
import frc.robot.grip.VisionProcessing;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 * 
 * @author Max Nadeau
 */
public class Robot extends TimedRobot {
	


	/**
	 * Creates a DriveTrain subsystem object which enables moving the robot
	 * around.
	 */
	public static final DriveTrain dt = new DriveTrain();
		
	public static final Climber cl = new Climber();

	public static final Grabber gr = new Grabber();

	public static final Elevator el = new Elevator();


	/**
	 * Declare the Operator Interface object. DO NOT initialize it here; that
	 * would cause No Robot Code to occur.
	 */
	public static OI oi;


	public static VisionProcessing vp;
	
	/**
	 * TalonSRX CAN Port Assignments:
	 * 1 = rear left
	 * 2 = front left
	 * 3 = rear right
	 * 4 = front right
	 * 5 = gripper left
	 * 6 = gripper right
	 * 7 = elevator motor 1
	 * 8 = elevator motor 2
	 */
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = new OI();
		dt.calibrateGyro();
		
		Robot.dt.left1.getSensorCollection().setPulseWidthPosition(0, 0);
		Robot.dt.right1.getSensorCollection().setPulseWidthPosition(0, 0);
		
		//SmartDashboard.putNumber("TurboSpeed", 0.95);
		DriverStation.reportWarning("Robot Initiated", false);

		vp = new VisionProcessing();


	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		dt.resetGyro();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * Runs before teleop begins
	 */
	
	@Override
	public void teleopInit() {
		DriverStation.reportWarning("Teleop Init!", false);
		Scheduler.getInstance().run();
 		dt.resetGyro();
 		dt.setEncoders(0);
		dt.stop();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		
		//DriverStation.reportWarning("DIO Port 1: " + el.magEnc.get(), false);
		//DriverStation.reportWarning("Min Switch: " + el.minSwitchTriggered(), false);
		//DriverStation.reportWarning("Encoder Revs: " + el.getEncoderRevs(), false);
		//DriverStation.reportWarning("Encoder Distance: " + el.getEncoderDistance(), false);
		//DriverStation.reportWarning("Encoder Stopped: " + el.getEncoderStopped(), false);
		//DriverStation.reportWarning("Ultrasonic Distance: " + gi.getUltrasonicDistance(), false);
		//DriverStation.reportWarning("Left Drive Encoder: " + -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition()), false);
		//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
		//DriverStation.reportWarning("Distance Traveled:" + Robot.dt.getDistanceTraveled(), false);
 		//DriverStation.reportWarning("Gyro Angle: " + dt.getGyroAngle(), false);
		//DriverStation.reportWarning("Elevator Height:" + Robot.el.height, false);
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void disabledInit() {
		
		dt.setEncoders(0);
		
		//DriverStation.reportWarning("DIO Port 1: " + el.magEnc.get(), false);
		//DriverStation.reportWarning("Max Switch: " + el.maxSwitchTriggered(), false);
		//DriverStation.reportWarning("Encoder Revs: " + el.getEncoderRevs(), false);
		//DriverStation.reportWarning("Encoder Distance: " + el.getEncoderDistance(), false);
		//DriverStation.reportWarning("Encoder Stopped: " + el.getEncoderStopped(), false);
		//DriverStation.reportWarning("Ultrasonic Distance: " + gi.getUltrasonicDistance(), false);
		//DriverStation.reportWarning("Left Drive Encoder: " + -(Robot.dt.left1.getSensorCollection().getPulseWidthPosition()), false);
		//DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
		//DriverStation.reportWarning("Distance Traveled:" + Robot.dt.getDistanceTraveled(), false);
 		//DriverStation.reportWarning("Gyro Angle: " + dt.getGyroAngle(), false);
		
	}
}
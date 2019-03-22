/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ClimberLeft;
import frc.robot.subsystems.ClimberRight;
import frc.robot.subsystems.ClimberBack;
import frc.robot.grip.VisionProcessing;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ClimberWheels;

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
		
	/**
	 * Creates a ClimberLeft subsystem object which moves the robot's left 
	 * climber up and down.
	 */
	public static final ClimberLeft cl = new ClimberLeft();

	/**
	 * Creates a ClimberRight subsystem object which moves the robot's right 
	 * climber up and down.
	 */
	public static final ClimberRight cr = new ClimberRight();
	
	/**
	 * Creates a ClimberBack subsystem object which moves the robot's back 
	 * climber up and down.
	 */
	public static final ClimberBack cb = new ClimberBack();

	/**
	 * Creates an ClimberWheels subsystem object which controls the wheels 
	 * on the bottom of the back climber.
	 */
	public static final ClimberWheels cw = new ClimberWheels();

	/**
	 * Creates a Grabber subsystem object which controls both the extender 
	 * and the expander pneumatics.
	 */
	public static final Grabber gr = new Grabber();

	/**
	 * Creates an Elevator subsystem object which moves the elevator 
	 * up and down.
	 */
	public static final Elevator el = new Elevator();

	/**
	 * Declare the Operator Interface object. DO NOT initialize it here; that
	 * would cause No Robot Code to occur.
	 */
	public static OI oi;


	//Defunct
	public static VisionProcessing vp;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = new OI();
		dt.calibrateGyro();
		//Resets encoders
		dt.setEncoders(0);
		DriverStation.reportWarning("Robot Initiated", false);
	}
	
	/**
	 *
	 */
	@Override
	public void autonomousInit() {
		dt.resetGyro();
		gr.grab();
		gr.retract();
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
		
		//The Scheduler runs commands that are in motion and starts commands when triggered by buttons
		Scheduler.getInstance().run();
 		dt.resetGyro();
		dt.setEncoders(0);
		cl.setEncoders(0);
		cr.setEncoders(0);
		cb.setEncoders(0);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();

		//DriverStation.reportWarning("Compressor Enabled: " + gr.c.enabled(), false);
		//DriverStation.reportWarning("Compressor Pressure switch value: " + gr.c.getPressureSwitchValue(), false);
		//DriverStation.reportWarning("Compressor current: " + gr.c.getCompressorCurrent(), false);
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
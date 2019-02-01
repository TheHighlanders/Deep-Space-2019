/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DriveTrain;
import frc.robot.grip.JavaPipeline;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

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
public class Robot extends IterativeRobot {
	

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	private VisionThread visionThread;
	
	private final Object imgLock = new Object();

	/**
	 * Creates a DriveTrain subsystem object which enables moving the robot
	 * around.
	 */
	public static final DriveTrain dt = new DriveTrain();
		
	
	/**
	 * Declare the Operator Interface object. DO NOT initialize it here; that
	 * would cause No Robot Code to occur.
	 */
	public static OI oi;

	Thread m_visionThread;
	
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

		m_visionThread = new Thread(() -> {
			// Get the Axis camera from CameraServer
			AxisCamera camera
				= CameraServer.getInstance().addAxisCamera("10.62.1.15");
			// Set the resolution
			camera.setResolution(640, 480);
	  
			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream
				= CameraServer.getInstance().putVideo("Rectangle", 640, 480);
	  
			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();
	  
			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
			  // Tell the CvSink to grab a frame from the camera and put it
			  // in the source mat.  If there is an error notify the output.
			  if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			  }
			  // Put a rectangle on the image
			  Imgproc.rectangle(mat, new Point(50, 50), new Point(150, 150),
				  new Scalar(255, 255, 255), 5);
			  // Give the output stream a new image to display
			  outputStream.putFrame(mat);
			}
		  });
		  m_visionThread.setDaemon(true);
		  m_visionThread.start();

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
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Baxter Ellard
 */
public class RobotMap {
	
	// Drive Train
	public static final int LEFT_DRIVE1 = 1;
	public static final int LEFT_DRIVE2 = 2;
	public static final int RIGHT_DRIVE1 = 3;
	public static final int RIGHT_DRIVE2 = 4;
	
	// Gripper
	public static final int GRIPPER_LEFT = 0;
	public static final int GRIPPER_RIGHT = 1;
	
	// Elevator (ignore for now)
	public static final int ELEVATOR_MOTOR1 = 5;
	public static final int ELEVATOR_MOTOR2 = 7;
	
	// Climber
	public static final int CLIMBER_TOP = 3;
	public static final int CLIMBER_BOTTOM = 2;
	
	// Sensors
//	public static final int MAX_LIMIT_SWITCH = 0;
//	public static final int MIN_LIMIT_SWITCH = 1;
	public static final int ULTRASONIC = 2;
	
	// Joystick
	public static final int LOGITECH = 0;
	public static final int LOGITECH_X_AXIS = 0;
	public static final int LOGITECH_Y_AXIS = 1;
	public static final int LOGITECH_ROTATE_AXIS = 2;
	public static final int LOGITECH_SLIDER_AXIS = 3;
	
	//X Box
	public static final int XBOX = 2;
	public static final int XBOX_Z_AXIS = 2;
	
}
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
 * @author Max Nadeau
 */
public class RobotMap {
	
	// Drive Train CAN Ports
	public static final int LEFT_DRIVE1 = 2;
	public static final int LEFT_DRIVE2 = 12;
	public static final int MIDDLE_DRIVE1 = 3;
	public static final int MIDDLE_DRIVE2 = 13;
	public static final int RIGHT_DRIVE1 = 1;
	public static final int RIGHT_DRIVE2 = 11;

	// Climber CAN ports
	public static final int LEFT_CLIMBER = 4;
	public static final int RIGHT_CLIMBER = 5;
	public static final int BACK_CLIMBER = 6;
	
	//Climber Wheels CAN ports
	public static final int CLIMBER_WHEELS = 21;

	//Elevator CAN ports
	public static final int ELEVATOR = 22;

	//Pneumatic solenoid ports in PCM (Pneumatics Control Module)
	public static final int EXTENDER1 = 1;
	public static final int EXTENDER2 = 0;
	public static final int EXPANDER1 = 3;
	public static final int EXPANDER2 = 2;
	
	//X Box controller laptop port
	public static final int XBOX = 0;

	//Joystick controller laptop port
	public static final int LOGITECH = 1;
}
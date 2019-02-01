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
	
	// Drive Train
	public static final int LEFT_DRIVE1 = 1;
	public static final int LEFT_DRIVE2 = 2;
	public static final int RIGHT_DRIVE1 = 3;
	public static final int RIGHT_DRIVE2 = 4;
	public static final int MIDDLE_DRIVE1 = 5;
	public static final int MIDDLE_DRIVE2 = 6;

	
	// Sensors
	
	//X Box
	public static final int XBOX = 0;
	public static final int LOGITECH = 1;
	// public static final int XBOX_Z_AXIS = 2;
}
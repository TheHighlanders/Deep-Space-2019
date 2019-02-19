/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This provides the framework to connect the DriverStation to the Robot both for getting values from the joystick(s), 
 * and for starting commands when buttons are pressed.
 * 
 * 
 * @author Max Nadeau
 */
public class OI {
	
	/**
	 * Create an object out of our logitech arcade joystick.
	 * This allows us to get the  current position of the joystick, and the state of all the buttons.
	 * Initialized with the USB devices plugged into the robot
	 */


	public Button[] xboxButtons = new Button[11];
	public Button[] joystickButtons = new Button[13];
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	private XboxController xbox = new XboxController(RobotMap.XBOX);

	/**
	 * @return a double corresponding to the position of the Xbox controller's left 
	 * joystick in the side to side direction (X axis).
	 * Range of -1 to 1. All the way to the right is +1.
	 */
	public double getJoysticktY() {
		return logitech.getY();
	}

	/**
	 * @return a double corresponding to the position of the Xbox controller's left 
	 * joystick in the side to side direction (X axis).
	 * Range of -1 to 1. All the way to the right is +1.
	 */
	public double getXboxLeftX() {
		return xbox.getX(GenericHID.Hand.kLeft);
	}
	
	/**
	 * @return a double corresponding to the position of the Xbox controller's left 
	 * joystick in the front-back direction (Y axis).
	 * Range of -1 to 1. All the way forward is +1.
	 */
	public double getXboxLeftY() {
		return xbox.getY(GenericHID.Hand.kLeft);
	}
	
	/**
	 * @return a double corresponding to the position of the Xbox controller's right 
	 * joystick in the side to side direction (X axis).
	 * Range of -1 to 1. All the way to the right is +1.
	 */
	public double getXboxRightX() {
		return xbox.getX(GenericHID.Hand.kRight);
	}

	/**
	 * @return a double corresponding to the position of the Xbox controller's right 
	 * joystick in the front-back direction (Y axis).
	 * Range of -1 to 1. All the way forward is +1.
	 */
	public double getXboxRightY() {
		return xbox.getY(GenericHID.Hand.kRight);
	}

	/**
	 * @return a double corresponding to the position of the Xbox controller's right 
	 * trigger 
	 * Range of 0 to 1. All the way pressed is 1.
	 */
	public double getXboxRightTrigger() {
		return xbox.getTriggerAxis(GenericHID.Hand.kRight);
	}

	/**
	 * @return a double corresponding to the position of the Xbox controller's left 
	 * trigger 
	 * Range of 0 to 1. All the way pressed is 1.
	 */
	public double getXboxLeftTrigger() {
		return xbox.getTriggerAxis(GenericHID.Hand.kRight);
	}

	/**
	 * @return a double corresponding to the position of the Xbox controller's left 
	 * trigger 
	 * Range of 0 to 1. All the way pressed is 1.
	 */
	public void setXboxRumble(double power) {
		xbox.setRumble(GenericHID.RumbleType.kLeftRumble, power);
		xbox.setRumble(GenericHID.RumbleType.kRightRumble, power);
	}
	

	public OI() {
		for(int i = 1; i < joystickButtons.length; i++) {
			joystickButtons[i] = new JoystickButton(logitech, i);
		}

		for(int i = 1; i < xboxButtons.length; i++) {
			xboxButtons[i] = new JoystickButton(xbox, i);	
		}


		xboxButtons[5].whenPressed(new GrabberExtenderCmd(0));
		xboxButtons[6].whenPressed(new GrabberExpanderCmd(0));

		joystickButtons[3].whileHeld(new AllClimbersCmd(0.9));
		joystickButtons[3].whileHeld(new AllClimbersCmd(-0.9));
		joystickButtons[5].whileHeld(new ClimbersFrontCmd(0.9));
		joystickButtons[6].whileHeld(new ClimbersFrontCmd(-0.9));
		
		joystickButtons[7].whileHeld(new ClimberLeftCmd(1));
		joystickButtons[8].whileHeld(new ClimberLeftCmd(-1));
		joystickButtons[9].whileHeld(new ClimberRightCmd(1));
		joystickButtons[10].whileHeld(new ClimberRightCmd(-1));
		joystickButtons[11].whileHeld(new ClimberBackCmd(1));
		joystickButtons[11].whileHeld(new ClimberBackCmd(-1));
	}
}

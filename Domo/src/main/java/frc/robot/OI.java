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


	public Button[] xboxButtons = new Button[7];
	public Button[] joystickButtons = new Button[13];
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	private XboxController xbox = new XboxController(RobotMap.XBOX);
	
	private Button lb = new JoystickButton(xbox,5);
	private Button rb = new JoystickButton(xbox, 6);
	private Button bpress = new JoystickButton(xbox,10);
	private Button y = new JoystickButton(xbox,4);

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

	
	
	//X Box buttons
		public boolean getButtonlb() {
			return lb.get();
		}
		
		public boolean getButtonrb() {
			return rb.get();
		}
		
		public boolean getButtonbpress() {
			return bpress.get();
		}
		
		public boolean getButtony() {
			return y.get();
		}
	
	public OI() {
		for(int i = 1; i < joystickButtons.length; i++) {
			
			joystickButtons[i] = new JoystickButton(logitech, i);
			
		}

		for(int i = 1; i < xboxButtons.length; i++) {
			
			xboxButtons[i] = new JoystickButton(xbox, i);
			
		}
		joystickButtons[3].whileHeld(new ClimberFrontExtendCmd());
		joystickButtons[4].whileHeld(new ClimberFrontRetractCmd());
		joystickButtons[5].whileHeld(new ClimberBackExtendCmd());
		joystickButtons[6].whileHeld(new ClimberBackRetractCmd());
		
		joystickButtons[7].whileHeld(new AutoDropoffCmd());

		joystickButtons[8].whenPressed(new ManualHatchPickupCmdGroup());
		joystickButtons[9].whenPressed(new ManualHatchDropoffCmdGroup());

		joystickButtons[10].whileHeld(new ElevatorAscendCmd());
		joystickButtons[11].whileHeld(new ElevatorDescendCmd());
		
		
	}



}

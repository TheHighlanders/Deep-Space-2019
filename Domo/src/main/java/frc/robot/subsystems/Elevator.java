/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.TriggerElevatorCmd;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX elevator;
  
	private static double rampTime = 0.3;

  public Elevator() {
    elevator = new WPI_TalonSRX(RobotMap.ELEVATOR);
    elevator.configOpenloopRamp(rampTime, 0);
    elevator.setNeutralMode(NeutralMode.Brake);
    
    //elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
  }

  /**
	 * Sets the motors to be off.
	 */
  public void actuate(double power) {
      elevator.set(power);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TriggerElevatorCmd());
  }
}

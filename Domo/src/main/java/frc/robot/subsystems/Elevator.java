/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX elevator1;
  private WPI_TalonSRX elevator2;
  
	private static int rampTime = 1;

  public Elevator() {
    	
    elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR1);
    elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR2);
    
    elevator1.configOpenloopRamp(rampTime, 0);
    elevator2.configOpenloopRamp(rampTime, 0);
  
    
    elevator1.setNeutralMode(NeutralMode.Brake);
    elevator2.setNeutralMode(NeutralMode.Brake);
    
    elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    
  }

  /**
	 * Sets the motors to be off.
	 */
  public void ascend() {
    	
      elevator1.set(0.9);
      elevator2.set(0.9);  
  }
  /**
	 * Sets the motors to be off.
	 */
  public void descend() {
    	
      elevator1.set(-0.9);
      elevator2.set(-0.9);  
  }
  /**
	 * Sets the motors to be off.
	 */
  public void stop() {
      elevator1.set(0);
      elevator2.set(0);  
    
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

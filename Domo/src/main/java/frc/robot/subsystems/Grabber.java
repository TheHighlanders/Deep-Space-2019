/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DoubleSolenoid extender;
  public DoubleSolenoid expander;


  public Grabber(){
      extender = new DoubleSolenoid(RobotMap.EXTENDER1,RobotMap.EXTENDER2);
      expander = new DoubleSolenoid(RobotMap.EXPANDER1,RobotMap.EXPANDER2);
  }

  public void extend(){
      extender.set(DoubleSolenoid.Value.kForward);
  }
  public void retract(){
      extender.set(DoubleSolenoid.Value.kReverse);
  }
  public void grab(){
      expander.set(DoubleSolenoid.Value.kForward);
  }
  public void release(){
      expander.set(DoubleSolenoid.Value.kReverse);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

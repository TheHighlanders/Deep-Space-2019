/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DoubleSolenoid extender;
  public DoubleSolenoid expander;
  public Compressor c;

  public boolean extenderPos = false;
  public boolean expanderPos = false;


  public Grabber(){
      extender = new DoubleSolenoid(RobotMap.EXTENDER1,RobotMap.EXTENDER2);
      expander = new DoubleSolenoid(RobotMap.EXPANDER1,RobotMap.EXPANDER2);
      c = new Compressor(0);
      c.setClosedLoopControl(true);
  }

  public void extend(){
      extender.set(DoubleSolenoid.Value.kForward);
      extenderPos = true;
  }
  public void retract(){
      extender.set(DoubleSolenoid.Value.kReverse);
      extenderPos = false;
  }
  public void toggleExtender(){
      if(extenderPos){
          this.retract();
      }
      else{
            this.extend();
      }
  }
  
  public void grab(){
      expander.set(DoubleSolenoid.Value.kForward);
      extenderPos = true;
  }
  public void release(){
      expander.set(DoubleSolenoid.Value.kReverse);
      extenderPos = false;
  }
  public void toggleExpander(){
    if(expanderPos){
        this.release();
    }
    else{
        this.grab();
    }
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

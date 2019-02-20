/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;

public class ClimberBackCmd extends Command {
  private double power;

  public ClimberBackCmd(double power) {
    requires(Robot.cb);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      // if( dir != 1 && dir != -1){
      //   DriverStation.reportWarning("Dir parameter must be 1 (extend) or -1 (retract).", false);
      //   end();
      // }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    Robot.cb.move(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      Robot.cb.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.cb.move(0);
  }
}

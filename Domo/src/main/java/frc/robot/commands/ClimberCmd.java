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

public class ClimberCmd extends Command {

  private char side;

  private int dir;

  public ClimberCmd(char side, int dir) {
    requires(Robot.cl);
    this.side = side;
    this.dir = dir;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      if( side != 'f' && side != 'b'){
        DriverStation.reportWarning("Side parameter must be f (front) or b (back).", false);
        end();
      }
      if( dir != 1 && dir != -1){
        DriverStation.reportWarning("Dir parameter must be 1 (extend) or -1 (retract).", false);
        end();
      }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
    if(side == 'f'){
      if(dir == 1){
        Robot.cl.moveFront(0.9);
      }
      else{
        Robot.cl.moveFront(-0.9);
      }
    }
    else{
      if(dir == 1){
        Robot.cl.moveBack(0.9);
      }
      else{
        Robot.cl.moveBack(-0.9);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(side == 'f'){
        Robot.cl.moveFront(0);
    }
    else{
        Robot.cl.moveBack(0);
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

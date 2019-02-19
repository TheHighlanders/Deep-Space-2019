/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

public class DriveIntoWallCmd extends Command {

public final float STOPPED_CURRENT = 5;

  public DriveIntoWallCmd() {
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.dt.drive(0.2,0.2,0);
    DriverStation.reportWarning("Current to left1: " + Robot.dt.left1.getOutputCurrent(), false);
    DriverStation.reportWarning("(Robot.dt.left1.getOutputCurrent() > STOPPED_CURRENT) " + (Robot.dt.left1.getOutputCurrent() > STOPPED_CURRENT), false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.dt.left1.getOutputCurrent() > STOPPED_CURRENT);
    //return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.oi.setXboxRumble(1);
    Robot.dt.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

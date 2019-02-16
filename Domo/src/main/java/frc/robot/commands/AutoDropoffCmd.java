/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;


import frc.robot.Robot;

public class AutoDropoffCmd extends Command {

NetworkTableEntry angle1;
NetworkTableEntry angle2;
NetworkTableEntry avgx;
  public AutoDropoffCmd() {
      requires(Robot.dt);
      NetworkTableInstance inst = NetworkTableInstance.getDefault();
      NetworkTable visionTable = inst.getTable("visionTable");
      angle1 = visionTable.getEntry("angle1");
      angle2 = visionTable.getEntry("angle2");
      avgx = visionTable.getEntry("avgx");
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double angle1i = angle1.getDouble(1000);
    double angle2i = angle2.getDouble(1000);
    double avgxi = avgx.getDouble(1000);

    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    DriverStation.reportWarning("AutoCmd sees angle1 in visionTable as: " + angle1.getDouble(1000), false);
    DriverStation.reportWarning("AutoCmd sees angle2 in visionTable as: " + angle2.getDouble(1000), false);
    DriverStation.reportWarning("AutoCmd sees avgx in visionTable as: " + avgx.getDouble(1000), false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

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
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class LineFollowCmd extends Command {
  NetworkTableEntry angle1;
  NetworkTableEntry angle2;
  NetworkTableEntry avgx;

  double angle1i;
  double angle2i;
  double avgxi;


  public LineFollowCmd() {
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
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    angle1i = angle1.getDouble(1000);
    angle2i = angle2.getDouble(1000);
    avgxi = avgx.getDouble(1000);

    Robot.dt.drive(0.4 - avgxi/200, 0.4 +  avgxi/200, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.dt.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

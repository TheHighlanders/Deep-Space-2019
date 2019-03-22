/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AllClimbersCmd extends Command {
  private double power;
  private double frontTilt;
  private double sideTilt;
  public AllClimbersCmd(double power) {
    requires(Robot.cl);
    requires(Robot.cr);
    requires(Robot.cb);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  protected double sigmoid(double power, double tilt){
    return power/(Math.exp(-tilt/500 - 5) + 1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

      frontTilt = (Robot.cl.getEncoders() + Robot.cr.getEncoders()) / 2 - Robot.cb.getEncoders();
      sideTilt = Robot.cl.getEncoders() - Robot.cr.getEncoders();


      if (frontTilt > 5000){
        if (power > 0){
          Robot.cb.move(power);
          Robot.cl.move(0);
          Robot.cr.move(0);
        }
        else{
          Robot.cb.move(0);
          Robot.cl.move(0.8 * power);
          Robot.cr.move(0.8 * power);
        }
      }
      else if(frontTilt < -5000){
        if (power > 0){
          Robot.cb.move(0);
          Robot.cl.move(0.8 * power);
          Robot.cr.move(0.8 * power);
        }
        else{
          Robot.cb.move(power);
          Robot.cl.move(0);
          Robot.cr.move(0);
        }
      }
      else{
        Robot.cl.move(0.8 * power);
        Robot.cr.move(0.8 * power);
        Robot.cb.move(power);
      }

      // if (power > 0){
      //     Robot.cb.move(sigmoid(power, frontTilt));
      //     Robot.cl.move(sigmoid(0.8 * power, -frontTilt));
      //     Robot.cl.move(sigmoid(0.8 * power, -frontTilt));
      // }
      // else{
      //     Robot.cb.move(sigmoid(power, -frontTilt));
      //     Robot.cl.move(sigmoid(0.8 * power, frontTilt));
      //     Robot.cl.move(sigmoid(0.8 * power, frontTilt));
      // }

      // if (power > 0){
      //   Robot.cb.move(sigmoid(power, frontTilt));
      //   Robot.cl.move(sigmoid(sigmoid(0.8 * power, -frontTilt), -sideTilt));
      //   Robot.cl.move(sigmoid(sigmoid(0.8 * power, -frontTilt), sideTilt));
      // }
      // else{
      //   Robot.cb.move(sigmoid(power, -frontTilt));
      //   Robot.cl.move(sigmoid(sigmoid(0.8 * power, frontTilt), sideTilt));
      //   Robot.cl.move(sigmoid(sigmoid(0.8 * power, frontTilt), -sideTilt));
      // }

      SmartDashboard.putNumber("TILT", frontTilt);
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

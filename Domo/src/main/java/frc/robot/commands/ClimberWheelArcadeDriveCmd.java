/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberWheelArcadeDriveCmd extends Command {
  
  private final double TANDOMAIN = 1.3;
  private double input;
  private double processedPower;

  
  /*
	 * Defines a tangent curve that goes from (-1, -1) to (1, 1)
	 * domain defines the curviness of the tan curve
	 *
	 */
	private double scaledValTan(double rawVal, double domain) {
		return Math.tan(rawVal * domain) / (Math.tan(domain));
	}

  public ClimberWheelArcadeDriveCmd() {
    requires(Robot.cw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    input = Robot.oi.getJoysticktY();
    processedPower = - scaledValTan(input, TANDOMAIN) * 0.9;
    Robot.cw.drive(processedPower);
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

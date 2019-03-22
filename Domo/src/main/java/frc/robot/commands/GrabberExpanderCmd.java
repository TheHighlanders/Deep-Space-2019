/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.Robot;

public class GrabberExpanderCmd extends Command {
    int dir;
    public GrabberExpanderCmd(int dir) {
        requires(Robot.gr);
        this.dir = dir;
        if (this.dir == 0){
            if(Robot.gr.extenderPos){
                this.dir = -1;
            }
            else{
                this.dir = 1;
            }
        }
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if( dir != 1 && dir != -1 && dir != 0){
            DriverStation.reportWarning("Dir parameter must be 1 (Grab), -1 (Release), or 0 (Toggle)", false);
            end();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(this.dir == 1){
            Robot.gr.grab();
            Robot.oi.setXboxRumble(1);
            DriverStation.reportWarning("EXPANDING", false);
            dir = -1;
        }
        else if(this.dir == -1){
            Robot.gr.release();
            DriverStation.reportWarning("RELEASING", false);
            dir = 1;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        double startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < 100){
    
        }
        Robot.oi.setXboxRumble(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.oi.setXboxRumble(0);
    }
}

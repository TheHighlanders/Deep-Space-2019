package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ArcadeDriveCmd extends Command {

	private double tanTurn;
	private double tanX;
	private double tanY;
	
	private double processedTurn;
	private double processedX;
	private double processedY;
	
	private final double TANDOMAIN_Y = 1.3;
	private final double TANDOMAIN_X = 1.3;
	

	/*
	 * Defines a tangent curve that goes from (-1, -1) to (1, 1)
	 * domain defines the curviness of the tan curve
	*

	*/
	private double scaledValTan(double rawVal, double domain) {
		
		return Math.tan(rawVal * domain) / (Math.tan(domain));
		
	}
	
	private double linearElevatorFunction(double height) {
		
		return (-height / 104) + 1;
		
	}
	
    public ArcadeDriveCmd() {
        
    	requires(Robot.dt);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*double joystickX = Robot.oi.getXAxisOfArcade();
    	double joystickY = Robot.oi.getYAxisOfArcade();
    	double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));
    	
    	tanTurn = scaledValTan(joystickX * joystickSlider, TANDOMAIN_X);
    	tanPower = scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y);
    	
    	processedPower = tanPower * 0.90;
		processedTurn = (1 - Math.abs(processedPower)) * tanTurn;*/
		
		double moveX = Robot.oi.getXboxLeftX();
		double moveY = Robot.oi.getXboxLeftY();
		double turnX = Robot.oi.getXboxRightX();

		processedX = scaledValTan(moveX, TANDOMAIN_X) * 0.9;
		processedY = scaledValTan(moveY, TANDOMAIN_Y) * 0.9;
		processedTurn = scaledValTan(turnX, TANDOMAIN_Y) * 0.9;
	
   	
    	DriverStation.reportWarning("Left X: " + Robot.oi.getXboxLeftX(), false);
		DriverStation.reportWarning("Left Y: " + Robot.oi.getXboxLeftY(), false);
		DriverStation.reportWarning("Right X: " + Robot.oi.getXboxRightX(), false);
		DriverStation.reportWarning("Right Y: " + Robot.oi.getXboxRightY(), false);
		DriverStation.reportWarning("moveX (left X): " + moveX,  false);
    	DriverStation.reportWarning("moveY (left Y): " + moveY, false);
    	DriverStation.reportWarning("turnX (right X): " + turnX, false);
		DriverStation.reportWarning("Processed X: " + processedX, false);
		DriverStation.reportWarning("Processed Y: " + processedY, false);
    	DriverStation.reportWarning("Processed Turn: " + processedTurn, false);
    	//DriverStation.reportWarning("Scaled Val Turn: " + scaledValTan(joystickX * joystickSlider, TANDOMAIN_X), false);
    	//DriverStation.reportWarning("Scaled Val Power: " + scaledValTan(joystickY * joystickSlider, TANDOMAIN_Y), false);
   	
    	Robot.dt.drive(processedY - processedTurn, processedY + processedTurn, processedX);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
   
}

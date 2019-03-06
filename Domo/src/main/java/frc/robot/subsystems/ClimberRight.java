/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ClimberRightCmd;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class ClimberRight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX climberRight;

  public ClimberRight(){
      climberRight = new WPI_TalonSRX(RobotMap.RIGHT_CLIMBER);
      climberRight.configOpenloopRamp(1/3, 0);
      climberRight.setNeutralMode(NeutralMode.Brake);
      climberRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
      this.setEncoders(0);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberRightCmd(0));
  }

  public void move(double power){
  	  //DriverStation.reportWarning("Power: " + power, false);
      climberRight.set(-power);
      SmartDashboard.putNumber("RIGHT", this.getEncoders());
  }

  /**
	 * Sets Climber encoders
	 */
  public void setEncoders(int pulses) {
    climberRight.getSensorCollection().setPulseWidthPosition(pulses, 0);
    //DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
  }

  /**
	 * Gets Climber encoders
	 */
  public int getEncoders() {
    return climberRight.getSensorCollection().getPulseWidthPosition();
  }
}
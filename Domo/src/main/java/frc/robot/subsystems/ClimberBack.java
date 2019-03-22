/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ClimberBackCmd;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DriverStation;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ClimberBack extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX climberBack;

  public ClimberBack(){
      climberBack = new WPI_TalonSRX(RobotMap.BACK_CLIMBER);
      climberBack.configOpenloopRamp(1/3, 0);
      climberBack.setNeutralMode(NeutralMode.Brake);
      climberBack.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
      this.setEncoders(0);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberBackCmd(0));
  }

  public void move(double power){
  	  //DriverStation.reportWarning("Power: " + power, false);
      climberBack.set(power);
      
      SmartDashboard.putNumber("BACK", this.getEncoders());
  }

  /**
	 * Sets Climber encoders
	 */
  public void setEncoders(int pulses) {
    climberBack.getSensorCollection().setPulseWidthPosition(pulses, 0);
    DriverStation.reportWarning("RESETING?", false);
    //DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
  }

  /**
	 * Gets Climber encoders
	 */
  public int getEncoders() {
    return climberBack.getSensorCollection().getPulseWidthPosition();
  }
}
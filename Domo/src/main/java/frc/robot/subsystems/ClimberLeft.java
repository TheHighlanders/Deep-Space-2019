/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ClimberLeftCmd;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.DriverStation;


/**
 * Add your docs here.
 */
public class ClimberLeft extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX climberLeft;

  public ClimberLeft(){
      climberLeft = new WPI_TalonSRX(RobotMap.LEFT_CLIMBER);
      climberLeft.configOpenloopRamp(1/3, 0);
      climberLeft.setNeutralMode(NeutralMode.Brake);
      climberLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
      this.setEncoders(0);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberLeftCmd(0));
  }

  public void move(double power){
      climberLeft.set(-power);
      SmartDashboard.putNumber("LEFT", this.getEncoders());
  }

  /**
	 * Sets Climber encoders
	 */
  public void setEncoders(int pulses) {
    climberLeft.getSensorCollection().setPulseWidthPosition(pulses, 0);
    DriverStation.reportWarning("RESETING?", false);
    //DriverStation.reportWarning("Right Drive Encoder:" + Robot.dt.right1.getSensorCollection().getPulseWidthPosition(), false);
  }

  /**
	 * Gets Climber encoders
	 */
  public int getEncoders() {
    return climberLeft.getSensorCollection().getPulseWidthPosition();
  }
}


/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.ClimberWheelArcadeDriveCmd;

/* IDK WHAT THESE IMPORT DOES BUT IT LOOKS IMPORTANT, Please change packages according to motor u are using.*/
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
*@author Iskandar Nazhar  
*also with a little help of plagairism*/

/**
 * Add your docs here.
 */
public class ClimberWheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public WPI_TalonSRX climberWheels; /* CHANGE MOTOR TYPE IF u WANT */
  private static int rampTime = 1; /* What is ramp time? looks important */

  public ClimberWheels(){
    climberWheels = new WPI_TalonSRX(RobotMap.CLIMBER_WHEELS);
    climberWheels.configOpenloopRamp(rampTime, 0);
    climberWheels.setNeutralMode(NeutralMode.Coast);
  }
  public void drive(double power){ 
    climberWheels.set(power); 
  }

  public void stop() {
    climberWheels.set(0);
  }

  /* public void destroyClimberWheels(){
    this.driveClimberWheels(10000000000000);
  } I wonder what would happen if we overclocked the motors u can delete this function if u want ~Iskandar Nazhar
  */

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberWheelArcadeDriveCmd());
  }
}
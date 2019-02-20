/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ClimberLeftCmd;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

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
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberLeftCmd(0));
  }

  public void move(double power){
  	  //DriverStation.reportWarning("Power: " + power, false);
    	climberLeft.set(-power);
  }
}

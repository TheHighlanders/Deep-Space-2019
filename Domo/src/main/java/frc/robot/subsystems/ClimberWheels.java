/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

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
  
  public WPI_VictorSPX climberWheels1; /* CHANGE MOTOR TYPE IF u WANT */

  private static int rampTime = 1; /* What is ramp time? looks important */
  public static final int forwardOrReverse = -1;

  
  public ClimberWheels(){

    climberWheels1 = new WPI_TalonSRX(RobotMap. !!!PUT SOMETHING HERE!!!); /* SOMEONE PUT SOMETHING HERE see RobotMap */

    climberWheels1.configOpenloopRamp(rampTime, 0);

    climberWheels1.setNeutralMode(NeutralMode.Coast);


  }
  public void driveClimberWheels(double climberWheelsPower){ /* I dont think u need double but kek */
    climberWheelsPower.set(climberWheels1); /* If error see line 38 */
  }

  public void stopClimberWheels() {
    	
    this.driveClimberWheels(0);
    
  }

  /* public void destroyClimberWheels(){
    this.driveClimberWheels(10000000000000);
  } I wonder what would happen if we overclocked the motors u can delete this function if u want ~Iskandar Nazhar
  */



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

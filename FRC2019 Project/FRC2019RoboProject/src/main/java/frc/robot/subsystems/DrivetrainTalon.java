/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */

public class DrivetrainTalon extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DifferentialDrive d;
  WPI_TalonSRX leftMaster, rightMaster;
  WPI_VictorSPX leftFollower, rightFollower;
  

  public DrivetrainTalon(WPI_TalonSRX left, WPI_TalonSRX right, WPI_VictorSPX vLeft, WPI_VictorSPX vRight)
  {
    
    leftMaster = left;
    rightMaster = right;

    leftFollower = vLeft;
    rightFollower = vRight;
    
    leftFollower.set(ControlMode.Follower, RobotMap.leftMasterID);
    rightFollower.set(ControlMode.Follower, RobotMap.rightMasterID);

    d = new DifferentialDrive(leftMaster, rightMaster);
    
    //Make robot not go boom
   try{
  
		leftMaster.setSensorPhase(true);
    leftMaster.setInverted(false);
    rightMaster.setSensorPhase(true);
		rightMaster.setInverted(true);
   }
   catch (java.lang.NullPointerException e){}
		
  }
  public void drive(Joystick stick)
  {
		//d.arcadeDrive(stick.getRawAxis(4), stick.getRawAxis(1));
		
  }

  public void drive2(double a, double b)
  {
    leftMaster.set(ControlMode.PercentOutput, a);
    rightMaster.set(ControlMode.PercentOutput, b);
  }

  public void stop()
  {
    leftMaster.set(ControlMode.PercentOutput, 0);
    rightMaster.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

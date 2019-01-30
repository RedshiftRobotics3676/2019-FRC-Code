/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

    d = new DifferentialDrive(leftMaster, rightMaster);
  }

  public void drive(Joystick stick)
  {
    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);

    d.arcadeDrive(stick.getRawAxis(1), stick.getRawAxis(4), true);

  }

  public void stop()
  {
    leftMaster.set(ControlMode.PercentOutput, 0);
    rightMaster.set(ControlMode.PercentOutput, 0);
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
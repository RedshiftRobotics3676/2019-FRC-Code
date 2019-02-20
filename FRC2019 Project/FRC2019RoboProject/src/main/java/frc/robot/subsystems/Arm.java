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
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/*
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  WPI_TalonSRX master;
  WPI_VictorSPX consort;
  double speed = 0.5;

  public Arm(WPI_TalonSRX tal, WPI_VictorSPX vic)
  {
    master = tal;
    consort = vic;

    consort.set(ControlMode.Follower, RobotMap.armTalonID);
  }

  public void up()
  {
    master.set(ControlMode.MotionMagic, speed);
  }

  public void down()
  {
    master.set(ControlMode.MotionMagic, -speed);
  }

  public void stop()
  {
    master.set(ControlMode.MotionMagic, 0);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX t;
  //public VictorSPX v;
  double speed = -.3; 
  //access encoder value

  DigitalInput top;
  DigitalInput bottom;
  WPI_VictorSPX v;

  public Elevator(WPI_TalonSRX _t, WPI_VictorSPX _v, DigitalInput _top, DigitalInput _bottom)
  {
    t = _t;
    if(t != null)
    {
        SmartDashboard.putString("Specific Talon", "Ready");
    }
    v = _v;
    top = _top;
    bottom = _bottom;

  }
  public void up()
  {
    //v.set(ControlMode.Follower, RobotMap.elevatorTalonID);
    t.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putString("Elevator", "Supposed Up Movement");
  }
  public void down()
  {
    //v.set(ControlMode.Follower, RobotMap.elevatorTalonID);
    t.set(ControlMode.PercentOutput, -speed);
    SmartDashboard.putString("Elevator", "Supposed Down Movement");
  }
  public void goToPosX(double pos)
  {
    //something to do with encoders
  }

  public boolean getTop()
  {
    return !top.get();
  }
  public boolean getBottom()
  {
    return !bottom.get();
  }
  public void stop()
  {
    //v.set(ControlMode.Follower, RobotMap.elevatorTalonID);
    t.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putString("Elevator", "Supposed Death");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

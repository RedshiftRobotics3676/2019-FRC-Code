/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  DoubleSolenoid ds1;
  DoubleSolenoid ds2;

  public Arm(DoubleSolenoid _ds1,DoubleSolenoid _ds2)
  {
    ds1 = _ds1;
    ds2 = _ds2;
  }

  public void forward()
  {
    ds1.set(DoubleSolenoid.Value.kForward);
    ds2.set(DoubleSolenoid.Value.kForward);
  }

  public void backward()
  {
    ds1.set(DoubleSolenoid.Value.kReverse);
    ds2.set(DoubleSolenoid.Value.kReverse);
  }

  public void stop()
  {
    ds1.set(DoubleSolenoid.Value.kOff);
    ds2.set(DoubleSolenoid.Value.kOff);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
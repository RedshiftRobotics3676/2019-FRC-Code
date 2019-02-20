package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.OnePunch;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;

public class HatchIntake extends Subsystem
{
    DoubleSolenoid grabber;
    DoubleSolenoid puncher;

  public HatchIntake(DoubleSolenoid ds1, DoubleSolenoid ds2)
  {
    grabber = ds1;
    puncher = ds2;
  }

  public void grab()
  {
    grabber.set(DoubleSolenoid.Value.kReverse);
  }

  public void punch()
  {
    puncher.set(DoubleSolenoid.Value.kForward);
  }

  public void punchAndRelease()
  {
    grabber.set(DoubleSolenoid.Value.kForward);
    puncher.set(DoubleSolenoid.Value.kForward);
  }

  public void grabberStop()
  {
    grabber.set(DoubleSolenoid.Value.kForward);
  }
  public void puncherStop()
  {
    puncher.set(DoubleSolenoid.Value.kReverse);
  }
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
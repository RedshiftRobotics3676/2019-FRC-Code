package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
    grabber.set(Value.kOff);
  }

  public void punchAndRelease()
  {
    grabber.set(Value.kForward);
    puncher.set(Value.kForward);
  }

  public void reset()
  {
    puncher.set(Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
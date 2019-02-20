package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem
{
    WPI_VictorSPX iVictor;
    private static final double speed = 0.5;

  public Intake(WPI_VictorSPX victor)
  {
    iVictor = victor;

    //aTalon.setSafetyEnabled(false);
  }

  public void in()
  {
    iVictor.set(ControlMode.PercentOutput, speed);
  }

  public void out()
  {
    iVictor.set(ControlMode.PercentOutput, -speed);
  }

  public void stop()
  {
    iVictor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
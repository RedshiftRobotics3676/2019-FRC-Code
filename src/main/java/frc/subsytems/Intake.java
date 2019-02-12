package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;

public class Intake extends Subsystem
{
    WPI_VictorSPX iVictor;
    private static final double speed = 0.3;

  public Intake(WPI_VictorSPX victor)
  {
    iVictor = victor;

    //aTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    iVictor.set(ControlMode.PercentOutput, speed);
  }

  public void down()
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
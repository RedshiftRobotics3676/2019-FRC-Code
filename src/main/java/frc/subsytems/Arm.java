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

public class Arm extends Subsystem
{
    WPI_TalonSRX aTalon;
    WPI_VictorSPX aVictor;
    private static final double speed = 0.3;

  public Arm(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    aTalon = talon;
    aVictor = victor;

    aVictor.follow(aTalon);

    //aTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    aTalon.set(ControlMode.PercentOutput, speed);
  }

  public void down()
  {
    aTalon.set(ControlMode.PercentOutput, -speed);
  }

  public void stop()
  {
    aTalon.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
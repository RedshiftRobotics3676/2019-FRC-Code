package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.WPI_MotorSafetyImplem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.Robot;

public class Elevator extends Subsystem
{
    WPI_TalonSRX eTalon;
    WPI_VictorSPX eVictor;
    public static final double speed = .3;

  public Elevator(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    eTalon = talon;
    eVictor = victor;

    eTalon.setInverted(false);

    eVictor.follow(eTalon);
    eVictor.setInverted(true);

    //eTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    eTalon.set(ControlMode.Position, -speed);
  }

  public void down()
  {
    eTalon.set(ControlMode.PercentOutput, speed);
  }

  public void stop()
  {
    eTalon.set(ControlMode.PercentOutput, 0.0);
  }

  public void pos(double dist)
  {
    eTalon.set(ControlMode.Position, dist);
  }  

  public void magic()
  {
    
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
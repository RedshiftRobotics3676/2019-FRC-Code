package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.WPI_MotorSafetyImplem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
    public static final double speed = .2;
    public static final double hSpeed = .05;

  public Elevator(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    eTalon = talon;
    eVictor = victor;

    eTalon.setInverted(false);

    //eVictor.follow(eTalon);
    eVictor.setInverted(false);

    
    eVictor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    //eTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    eTalon.set(ControlMode.PercentOutput, -speed);
    eVictor.set(ControlMode.PercentOutput, speed);
  }

  public void down()
  {
    eTalon.set(ControlMode.PercentOutput, -.002);
    eVictor.set(ControlMode.PercentOutput, .002);
  }

  public void hold()
  {
    eTalon.set(ControlMode.PercentOutput, -hSpeed);
    eVictor.set(ControlMode.PercentOutput, hSpeed);
  }
  public void stop()
  {
    eTalon.set(ControlMode.PercentOutput, -0.0);
    eVictor.set(ControlMode.PercentOutput, 0.0);
  }

  public void pos(double dist)
  {
    eTalon.set(ControlMode.Position, dist);
  }  

  public void magic()
  {
    
  }
  public void setEncoder(int x)
  {
    //eTalon.setSelectedSensorPosition(x, 2, 200);
  }
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
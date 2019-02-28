package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.WPI_MotorSafetyImplem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.Robot;

public class Elevator extends Subsystem
{
    WPI_TalonSRX eTalon;
    WPI_VictorSPX eVictor;
    public static final double dSpeed = .2;
    public static final double uSpeed = .4;
    public static final double hSpeed = .05;

    public static final double Kp = 0.01;
    public static final double Ki = 0.01;
    public static final double Kd = 0.01;
    public static final double Kf = 0.01;

    private static int pos, cPos;

  public Elevator(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    eTalon = talon;
    eVictor = victor;

    eTalon.setInverted(false);

    //eVictor.follow(eTalon);
    eVictor.setInverted(false);
    eVictor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    pos = 0;
    cPos = Robot.eTalon.getSelectedSensorPosition();

    //eTalon.setSafetyEnabled(false);
    //eTalon.config_kD(slotIdx, value);
    //PIDController pc = new PIDController(Kp, Ki, Kd, Kf, (PIDSource)eTalon, (PIDOutput)eTalon);
  }

  public void up()
  {
    eTalon.set(ControlMode.PercentOutput, -uSpeed);
    eVictor.set(ControlMode.PercentOutput, uSpeed);
  }

  public void down()
  {
    eTalon.set(ControlMode.PercentOutput, dSpeed);
    eVictor.set(ControlMode.PercentOutput, -dSpeed);
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

  public void setPosition(int mode)
  {
    switch(mode)
    {
      case 1: pos = 0; break;
      case 2: pos = 0; break;
      case 3: pos = 0; break;
      case 4: pos = 13886; break;
      case 5: pos = 11133; break;
      case 6: pos = 0; break;
      case 7: pos = 4394; break;
      case 8: pos = 17873; break;
      default: pos = 0; break;
    }
  }
  
  public int getPos() {
    return pos;
  }

  public void goPosition()
  {
   cPos = Robot.eTalon.getSelectedSensorPosition();
 
   if(pos -cPos <= -25)
     down();
   else if(pos - cPos >= 25)
     up();
   else
     hold();
  }

  public boolean done()
  {
    return Math.abs(pos - cPos) <= 25;
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
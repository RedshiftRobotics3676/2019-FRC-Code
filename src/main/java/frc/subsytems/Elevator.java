package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
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
import frc.robot.RobotMap;

public class Elevator extends Subsystem
{
    WPI_TalonSRX eTalon;
    WPI_VictorSPX eVictor;
    public static final double dSpeed = .15;
    public static final double uSpeed = .3;
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
    pos = 0;

    eVictor.follow(eTalon);
    eTalon.setInverted(true);
    eVictor.setInverted(InvertType.OpposeMaster);
    eTalon.setSensorPhase(true);
    eTalon.setSelectedSensorPosition(0);
    eTalon.configNeutralDeadband(0);
    eVictor.configNeutralDeadband(0);

    MMInit();
  }

  public void up()
  {
    eTalon.set(ControlMode.PercentOutput, uSpeed);

    //reset elevator to max at top
    if(!Robot.eTop.get())
      eTalon.setSelectedSensorPosition(20196);
  }

  public void down()
  {
    eTalon.set(ControlMode.PercentOutput, -dSpeed);

    //reset elevator to zero at bot
    if(!Robot.eBot.get())
      eTalon.setSelectedSensorPosition(0);
    
  }

  public void hold()
  {
    eTalon.set(ControlMode.PercentOutput, hSpeed);

    //reset elevator to zero at bot
    if(!Robot.eBot.get())
      eTalon.setSelectedSensorPosition(0);

    //reset elevator to max at top
    if(!Robot.eTop.get())
      eTalon.setSelectedSensorPosition(20196);
  }
  public void stop()
  {
    eTalon.set(ControlMode.PercentOutput, -0.0);
  }

  public void setPosition(int mode)
  {
    switch(mode)
    {
      case 1: pos = 0; break;//start
      case 2: pos = 0; break;//travel
      case 3: pos = 0; break;//hatch intake
      case 4: pos = 13886; break;//hatch high
      case 5: pos = 11133; break;//ball in
      case 6: pos = 0; break;//ground in
      case 7: pos = 3229; break;//bottom rocket
      case 8: pos = 17676; break;//top rocket
      default: pos = 0; break;
    }
  }
  
  /*public void goPosition()
  {
    cPos = Robot.eTalon.getSelectedSensorPosition();
    //int x = Math.abs(cPos) < 200?1:0;

    if(pos -cPos <= -65)
      down();
    else if(pos - cPos >= 65)
      up();
    else
      hold();
  }*/
  
  /*public boolean done()
  {
    return Math.abs(pos - cPos) <= 25;
  }*/

  public void MMInit()
  {  
    eTalon.config_kD(0, 128); //128
    eTalon.config_kI(0, 0); //0
    eTalon.config_kP(0, 12.8);//12.8
    eTalon.config_kF(0, .37818853974122);//.37818853974122
  }

  public void goMM()
  {
    eTalon.set(ControlMode.MotionMagic, pos);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
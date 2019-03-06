package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import com.sun.java.swing.plaf.windows.TMSchema.Control;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Arm extends Subsystem
{
    WPI_TalonSRX aTalon;
    WPI_VictorSPX aVictor;
    private static final double speed = 0.4;
    private static final double hSpeed = .05;
    private static int pos, cPos;

  public Arm(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    aTalon = talon;
    aVictor = victor;

    pos = cPos = Robot.aTalon.getSelectedSensorPosition();

    //aTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    //aTalon.setSelectedSensorPosition(0, 1, 200);

    //aVictor.set(ControlMode.Follower, RobotMap.A_TALON);

    //aTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    aTalon.set(ControlMode.PercentOutput, speed);
    aVictor.set(ControlMode.PercentOutput, -speed);
  }

  public void down()
  {
    aTalon.set(ControlMode.PercentOutput, -speed);
    aVictor.set(ControlMode.PercentOutput, speed);
  }

  public void hold()
  {
    aTalon.set(ControlMode.PercentOutput, hSpeed);
    aVictor.set(ControlMode.PercentOutput, -hSpeed);
  }
  public void setEncoder(int x)
  {
    //aTalon.setSelectedSensorPosition(x, 1, 200);
  }

  public void setPosition(int mode)
  {
    switch(mode)
    {
      case 1: pos = 0; break;
      case 2: pos = 273; break;
      case 3: pos = 2429; break;
      case 4: pos = 2429; break;
      case 5: pos = 2380; break;
      case 6: pos = 3379; break;
      case 7: pos = 2005; break;
      case 8: pos = 1570; break;
      default: pos = 0; break;
    }
  }

  public static int getPos() 
  {
    return pos;
  }

  public void goPosition()
  {
    cPos = Robot.aTalon.getSelectedSensorPosition();
    int x = Math.abs(cPos) < 300?1:0;

    if(pos -cPos <= -65)
      up();
    else if(pos - cPos >= 65)
      down();
    else
      hold();
  }
 
  public boolean done()
  {
    return Math.abs(pos - cPos) <= 25;
  }
 
  public void MMInit()
  {  
    aVictor.setInverted(false);
    aTalon.configMotionCruiseVelocity(100);
    aTalon.configMotionAcceleration(1960);
    //eTalon.config_kD(0, .003); //.003
    //eTalon.config_kI(0, .00033); //.00033
    aTalon.config_kP(0, .3);//.3
    //eTalon.config_kF(0, .261);//.261
    
 }

  public void goMM(double dist)
  {
    aTalon.set(ControlMode.Position, dist);
    aVictor.set(ControlMode.Position,dist);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
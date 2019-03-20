package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
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
    private static final double speed = .3;
    private static final double hSpeed = .04;
    private static int pos, cPos;

  public Arm(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    aTalon = talon;
    aVictor = victor;
    pos = 0;

    aVictor.follow(aTalon);
    aTalon.setInverted(true);
    aVictor.setInverted(InvertType.OpposeMaster);
    aTalon.setSensorPhase(false);
    aTalon.setSelectedSensorPosition(0);
    aTalon.configNeutralDeadband(0);
    aVictor.configNeutralDeadband(0);

    MMInit();
  }

  public void up()
  {
    aTalon.set(ControlMode.PercentOutput, -speed);
    //aVictor.set(ControlMode.PercentOutput, speed);

    //reset arm to zero at top
    if(!Robot.aTop.get())
      aTalon.setSelectedSensorPosition(0);
  }

  public void down()
  {
    aTalon.set(ControlMode.PercentOutput, speed-.1);
    //aVictor.set(ControlMode.PercentOutput, -speed+.1);

    //reset arm to max at bot
    if(Robot.aBot.get()) //normally closed
      aTalon.setSelectedSensorPosition(3390);
  }

  public void hold()
  {
    aTalon.set(ControlMode.PercentOutput, -hSpeed);
    //aVictor.set(ControlMode.PercentOutput, hSpeed);
    //aTalon.set(ControlMode.MotionMagic, aTalon.getSelectedSensorPosition());
    //aVictor.set(ControlMode.MotionMagic, aVictor.getSelectedSensorPosition());

    //reset arm to zero at top
    if(!Robot.aTop.get())
      aTalon.setSelectedSensorPosition(0);

    //reset arm to max at bot
    if(Robot.aBot.get()) //normally closed
      aTalon.setSelectedSensorPosition(3390);
  }


  public void setPosition(int mode)
  {
    switch(mode)
    {
      case 1: pos = 0; break;//start
      case 2: pos = 273; break;//travel
      case 3: pos = 2880; break;//hatch intake
      case 4: pos = 2880; break;//hatch high
      case 5: pos = 2130; break;//ball in
      case 6: pos = 3379; break;//ground in
      case 7: pos = 2005; break;//bottom rocket
      case 8: pos = 2005; break;//top rocket
      default: pos = 0; break;
    }
  }

  /*public void goPosition()
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
  }*/
 
  public void MMInit()
  {  
    aTalon.configMotionCruiseVelocity(1000);
    aTalon.configMotionAcceleration(1000);
    aTalon.config_kD(0, 0); //130.944
    aTalon.config_kI(0, 1.50203705E-05); //0
    aTalon.config_kP(0, 6);//26.1888
    aTalon.config_kF(0, 0.8338221);//1.34960 422
  }

  public void goMM()
  {
    aTalon.set(ControlMode.MotionMagic, pos);
    //aVictor.set(ControlMode.MotionMagic, pos);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import com.sun.java.swing.plaf.windows.TMSchema.Control;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Arm extends Subsystem
{
    WPI_TalonSRX aTalon;
    WPI_VictorSPX aVictor;
    private static final double speed = 0.3;

  public Arm(WPI_TalonSRX talon, WPI_VictorSPX victor)
  {
    aTalon = talon;
    aVictor = victor;

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
    aTalon.set(ControlMode.PercentOutput, -.2/3);
    aVictor.set(ControlMode.PercentOutput, .2/3);
  }

  public void stop()
  {
    aTalon.set(ControlMode.PercentOutput, 0.1);
    aVictor.set(ControlMode.PercentOutput, -0.1);
  }
  public void setEncoder(int x)
  {
    aTalon.setSelectedSensorPosition(x, 1, 200);
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.ElevatorMove;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;

public class Elevator extends Subsystem
{
    WPI_TalonSRX eTalon;

  public Elevator(WPI_TalonSRX talon)
  {
    eTalon = talon;

    eTalon.setSafetyEnabled(false);
  }

  public void up()
  {
    eTalon.set(ControlMode.PercentOutput, .25);
  }

  public void down()
  {
    eTalon.set(ControlMode.PercentOutput, -.25);
  }

  public void stop()
  {
    eTalon.set(ControlMode.PercentOutput, 0.0);
  }

  //Command Called by TurnToAngle usePIDOutput(double output)
  //Runs Motors in opposite directions at PID written speed
  

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevatorMove());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
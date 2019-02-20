package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.Drive;
import frc.robot.commands.DriveMM;
//import frc.commands.TurnToAngle;

import frc.robot.OI;

import frc.robot.Robot;



public class DriveTrain extends Subsystem

{

    DifferentialDrive d, e;

    Talon leftMaster, rightMaster;

    VictorSP leftFollower, rightFollower;

    WPI_TalonSRX leftMaster2, rightMaster2;

    WPI_VictorSPX leftFollower2, rightFollower2;

    AHRS navX;
public DriveTrain(int left, int right, int lv, int rv)

  {

    navX = Robot.getNavX();



    leftMaster = new Talon(left);

    rightMaster = new Talon(right);

    leftFollower = new VictorSP(lv);

    rightFollower =  new VictorSP(rv);



    d = new DifferentialDrive(leftMaster, rightMaster);
    e = new DifferentialDrive(leftMaster, rightMaster);

    d.setSafetyEnabled(false);
    e.setSafetyEnabled(false);


    //Robot.logNumber("DriveTrain", 1);

  }

  public DriveTrain(WPI_TalonSRX left, WPI_TalonSRX right, WPI_VictorSPX lv, WPI_VictorSPX rv){
    
    leftMaster2 = left;

    rightMaster2 = right;

    leftFollower2 = lv;

    rightFollower2 =  rv;

    //leftFollower2.follow(leftMaster2);

    //rightFollower2.follow(rightMaster2);
  }


  public void drive(Joystick stick)

  {

    d.arcadeDrive(stick.getRawAxis(1)*-1, stick.getRawAxis(4), true);
    e.arcadeDrive(stick.getRawAxis(1)*-1, stick.getRawAxis(4), true);
  }



  public void driveMM(Joystick stick)

  {

    leftMaster2.set(ControlMode.MotionMagic, 80000);

  }



  public void stop()

  {

    leftMaster2.set(ControlMode.PercentOutput, 0);

    rightMaster2.set(ControlMode.PercentOutput, 0);

  }



  //Command Called by TurnToAngle usePIDOutput(double output)

  //Runs Motors in opposite directions at PID written speed

  public void turn(double input)

  {

    leftMaster2.set(ControlMode.PercentOutput, input);

    rightMaster2.set(ControlMode.PercentOutput, input);

  }



  public WPI_TalonSRX getTalonLeft()

  {

    return this.leftMaster2;

  } 



  public WPI_TalonSRX getTalonRight()

  {

    return this.rightMaster2;

  } 



  public void MMInit()

  {

    leftMaster2.configFactoryDefault();

    leftMaster2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    leftMaster2.setSensorPhase(true);

    leftMaster2.setSelectedSensorPosition(0, 0, 200);

    leftMaster2.configMotionCruiseVelocity(1960);

    leftMaster2.configMotionAcceleration(1960);



    leftMaster2.config_kD(0, 0);

    leftMaster2.config_kI(0, 0);

    leftMaster2.config_kP(0, .785);    

    leftMaster2.config_kF(0, .261);

  }



  @Override

  public void initDefaultCommand() {

    //setDefaultCommand(new DriveMM());

    // Set the default command for a subsystem here.

    // setDefaultCommand(new MySpecialCommand());

  }

}
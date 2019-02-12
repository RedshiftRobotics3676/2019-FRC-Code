/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.commands.AutoCommand;
import frc.commands.Drive;
import frc.commands.HatchGrab;
import frc.subsytems.Arm;
import frc.subsytems.DriveTrain;
import frc.subsytems.Elevator;
import frc.subsytems.HatchIntake;
import frc.subsytems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;

  public static AHRS ahrs;

  WPI_TalonSRX leftDrive;
  WPI_TalonSRX rightDrive;
  WPI_TalonSRX eTalon;
  WPI_TalonSRX aTalon;

  WPI_VictorSPX leftFollow;
  WPI_VictorSPX rightFollow;

  WPI_VictorSPX eVictor;
  WPI_VictorSPX aVictor;
  WPI_VictorSPX iVictor;

  DoubleSolenoid hsGrab;
  DoubleSolenoid hsPunch;

  public static DriveTrain kDriveTrain;
  public static Elevator kElevator;
  public static Arm kArm;
  public static HatchIntake kHatch;
  public static Intake kIntake;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  Command m_autoSelected;
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Option", new AutoCommand());

    leftDrive = new WPI_TalonSRX(RobotMap.L_TALON);
    rightDrive = new WPI_TalonSRX(RobotMap.R_TALON);

    leftFollow = new WPI_VictorSPX(RobotMap.L_VICTOR);
    rightFollow = new WPI_VictorSPX(RobotMap.R_VICTOR);

    eTalon = new WPI_TalonSRX(RobotMap.E_TALON);
    aTalon = new WPI_TalonSRX(RobotMap.A_TALON);

    eVictor = new WPI_VictorSPX(RobotMap.E_VICTOR);
    aVictor = new WPI_VictorSPX(RobotMap.A_VICTOR);
    iVictor = new WPI_VictorSPX(RobotMap.I_VICTOR);

    //hsGrab = new DoubleSolenoid(RobotMap.G_SF, RobotMap.G_SR);
    //hsPunch = new DoubleSolenoid(RobotMap.P_SF, RobotMap.P_SR);

    kDriveTrain = new DriveTrain(leftDrive, rightDrive, leftFollow, rightFollow);
    kElevator = new Elevator(eTalon, eVictor);
    kArm = new Arm(aTalon, aVictor);
    kIntake = new Intake(iVictor);

    //kHatch = new HatchIntake(hsGrab, hsPunch);

    ahrs = new AHRS(SPI.Port.kMXP);

    oi = new OI();
    
    //SmartDashboard.putNumber("Left Wheel", leftDrive.get());
    //SmartDashboard.putNumber("Right Wheel", rightDrive.get());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }


  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);

    if(m_autoSelected != null)
    m_autoSelected.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if(m_autoSelected != null)
    {
      m_autoSelected.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }

  public static AHRS getNavX()
  {
    return ahrs;
  }

  public static void logNumber(String name, double value)
  {
      SmartDashboard.putNumber(name, value);
  }
}

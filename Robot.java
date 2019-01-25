/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DrivetrainTalon;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HatchIntake;
import frc.robot.subsystems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  
  //master Talons
  WPI_TalonSRX leftTalon;
  WPI_TalonSRX rightTalon;

  //follower Victors
  WPI_VictorSPX leftVictor;
  WPI_VictorSPX rightVictor;

  //arm solenoids
  DoubleSolenoid aDS1;
  DoubleSolenoid aDS2;

  //elevator TalonSRX
  TalonSRX eTalon;

  //hatch intake solenoid
  DoubleSolenoid hiDS;

  //intake victorSPX and limit switch
  VictorSPX iVictor;
  DigitalInput touch;

  public static DrivetrainTalon kDrivetrainTalon;
  public static Arm kArm;
  public static Elevator kElevator;
  public static HatchIntake kHatchIntake;
  public static Intake kIntake;
  public static VisionProcessingServer kVisionProcessingServer;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    //master Talons
    leftTalon = new WPI_TalonSRX(RobotMap.leftMasterID);
    rightTalon = new WPI_TalonSRX(RobotMap.rightMasterID);

    //follower Victors
    leftVictor = new WPI_VictorSPX(RobotMap.leftFollowerID);
    rightVictor = new WPI_VictorSPX(RobotMap.rightFollowerID);

    //arm solenoids
    aDS1 = new DoubleSolenoid(RobotMap.aDS1Forward, RobotMap.aDS1Reverse);
    aDS2 = new DoubleSolenoid(RobotMap.aDS2Forward, RobotMap.aDS2Reverse);

    //elevator talon
    eTalon = new TalonSRX(RobotMap.elevatorTalonID);

    //hatch intake solenoid
    hiDS = new DoubleSolenoid(RobotMap.hiDSForward, RobotMap.hiDSReverse);

    //intake victor and limit switch
    iVictor = new VictorSPX(RobotMap.iVictorID);
    touch = new DigitalInput(RobotMap.touchPort);

    //subsystems
    kDrivetrainTalon = new DrivetrainTalon(leftTalon, rightTalon, leftVictor, rightVictor);
    kArm = new Arm(aDS1, aDS2);
    kElevator = new Elevator(eTalon);
    kHatchIntake = new HatchIntake(hiDS);
    kIntake = new Intake(iVictor, touch);


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

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
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
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
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
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
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
}

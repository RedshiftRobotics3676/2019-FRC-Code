/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.commands.AutoCommand;
import frc.commands.*;
import frc.subsytems.*;
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


  public static WPI_TalonSRX leftDrive;
  public static WPI_TalonSRX rightDrive;
  public static WPI_TalonSRX eTalon;
  public static WPI_TalonSRX aTalon;

  WPI_VictorSPX leftFollow;
  WPI_VictorSPX rightFollow;

  WPI_VictorSPX eVictor;
  WPI_VictorSPX aVictor;
  WPI_VictorSPX iVictor;

  DoubleSolenoid hsGrab;
  DoubleSolenoid hsPunch;

  public static DigitalInput eTop;
  public static DigitalInput eBot;
  public static DigitalInput aTop;
  public static DigitalInput aBot;
  public static DigitalInput iSwitch;

  public static DriveTrain kDriveTrain;
  public static Elevator kElevator;
  public static Arm kArm;
  public static HatchIntake kHatch;
  public static Intake kIntake;

  
  public static Compressor compressor;
  public static UsbCamera cam0;

  //public static VisionProcessingServer kVisionProcessingServer;


  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  Command m_autoSelected;
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  int _timesInMotionMagic;
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

    aTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    eTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    eBot = new DigitalInput(1);
    eTop = new DigitalInput(0);
    aBot = new DigitalInput(3);
    aTop = new DigitalInput(4);
    iSwitch = new DigitalInput(5);

    hsGrab = new DoubleSolenoid(RobotMap.G_SF, RobotMap.G_SR);
    hsPunch = new DoubleSolenoid(RobotMap.P_SF, RobotMap.P_SR);

    kDriveTrain = new DriveTrain(leftDrive, rightDrive, leftFollow, rightFollow);
    kElevator = new Elevator(eTalon, eVictor);
    kArm = new Arm(aTalon, aVictor);
    kIntake = new Intake(iVictor);
    //kVisionProcessingServer = new VisionProcessingServer();

    compressor = new Compressor();
    //compressor.start();
    compressor.setClosedLoopControl(true);
    //compressor.stop();

    kHatch = new HatchIntake(hsGrab, hsPunch);

    cam0 = CameraServer.getInstance().startAutomaticCapture();

    oi = new OI();

    _timesInMotionMagic = 0;
    
    rightDrive.setSensorPhase(false);
    rightDrive.setInverted(false);
    
    leftDrive.setSensorPhase(true);
    leftDrive.setInverted(false);

    aTalon.setInverted(false);
    aTalon.setSensorPhase(true);
    aVictor.setInverted(false);

    iVictor.setInverted(false);
    eTalon.configNeutralDeadband(0);
    eVictor.configNeutralDeadband(0);
    
    //VisionProcessingServer.pipeline.setNumber(3);

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
    
    //reset arm to zero at top
    if(!aTop.get() && aTalon.getSelectedSensorPosition() != 0)
      aTalon.setSelectedSensorPosition(0);

    //reset arm to max at bot
    if(!aBot.get() && aTalon.getSelectedSensorPosition() > 3000)
      aTalon.setSelectedSensorPosition(3336);

    //reset elevator to zero at bot
    if(!eBot.get() && eTalon.getSelectedSensorPosition() != 0)
      eTalon.setSelectedSensorPosition(0);

    //reset elevator to max at top
    if(!eTop.get() && eTalon.getSelectedSensorPosition() > 19000)
      eTalon.setSelectedSensorPosition(19542);

    SmartDashboard.putNumber("Elevator Position", eTalon.getSelectedSensorPosition());
    //SmartDashboard.putNumber("target position", kElevator.getPos());
    SmartDashboard.putNumber("Arm Position", aTalon.getSelectedSensorPosition());
    //SmartDashboard.putBoolean("Elevator Top", eTop.get());
    //SmartDashboard.putBoolean("Elevator Down", eBot.get());
    //SmartDashboard.putBoolean("Arm Top", aTop.get());
    //SmartDashboard.putBoolean("Arm Down", aBot.get());
    /*
    SmartDashboard.putBoolean("Intake Switch", iSwitch.get());
    //SmartDashboard.putNumber("Arm Encoder", aTalon.getSelectedSensorPosition());
    //SmartDashboard.putNumber("Elevator Encoder", eTalon.getSelectedSensorPosition());
    SmartDashboard.putNumber("Encoder Value", VisionDriving.e);
    */
    //cool stuff
    //SmartDashboard.putNumber("SensorVel", leftDrive.getSelectedSensorVelocity(0));
		//SmartDashboard.putNumber("SensorPos", leftDrive.getSelectedSensorPosition(0));
		//SmartDashboard.putNumber("MotorOutputPercent", leftDrive.getMotorOutputPercent());
    //SmartDashboard.putNumber("ClosedLoopError", leftDrive.getClosedLoopError(0));
    //SmartDashboard.putNumber("ActTrajPosition", leftDrive.getActiveTrajectoryPosition());
    
    /*
    if (leftDrive.getControlMode() == ControlMode.MotionMagic) {
			++_timesInMotionMagic;
		} else {
			_timesInMotionMagic = 0;
		}
    */
    /*
		if (_timesInMotionMagic > 10) {
			/* Print the Active Trajectory Point Motion Magic is servoing towards */
			/*SmartDashboard.putNumber("ClosedLoopTarget", leftDrive.getClosedLoopTarget(0));
    		SmartDashboard.putNumber("ActTrajVelocity", leftDrive.getActiveTrajectoryVelocity());
    		
    }
    */
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

    //Limit Switch Testing
    SmartDashboard.putBoolean("Elevator Top", eTop.get());
    SmartDashboard.putBoolean("Elevator Bot", eBot.get());
    SmartDashboard.putBoolean("Arm Top", aTop.get());
    SmartDashboard.putBoolean("Arm Bot", aBot.get());
    SmartDashboard.putBoolean("Intake", iSwitch.get());
  }
  int i = 0;
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if(i >= 50){
      //System.out.printf("%d\n",oi.getJoystick(0).getPOV(0));
      i = 0;
    }
    i++;
  }


  public static void logNumber(String name, double value)
  {
      SmartDashboard.putNumber(name, value);
  }
}

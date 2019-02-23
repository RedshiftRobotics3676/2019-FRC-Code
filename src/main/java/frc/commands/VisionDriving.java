package frc.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.subsytems.VisionProcessingServer;

public class VisionDriving extends Command {

  
  double LeftGain;
  double RightGain;
  double turn;
  double drive;
  public static double e ;
  int b;
  VisionProcessingServer k;
  boolean v;

  public VisionDriving() {
    requires(Robot.kDriveTrain);
  }

  @Override
  protected void initialize() {

    Robot.rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    k = new VisionProcessingServer();
    k.getVars();
    LeftGain = k.returnVars("LeftGain");
    RightGain = k.returnVars("RightGain");
    turn = k.returnVars("Turn");
    drive = k.returnVars("Drive");
    b = 11;
    Robot.rightDrive.setSelectedSensorPosition(0);
  }

  @Override
  protected void execute() {
    v = false;

  while (-1*e < turn*b){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      e = Robot.rightDrive.getSelectedSensorPosition()*6.25*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",-1*e);
    }

    Robot.rightDrive.setSelectedSensorPosition(0);
    
    if (-1*e < drive*Math.tan(turn)){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
    }

    Robot.rightDrive.setSelectedSensorPosition(0);
    if (-1*e < b*Math.PI/2){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*-1*LeftGain, OI.getJoystick().getRawAxis(1)*-1*RightGain);
    }

    Robot.rightDrive.setSelectedSensorPosition(0);

    if (-1*e < drive){
      if(LeftGain < 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
      if(LeftGain > 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
      if(LeftGain == 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
    }
    v = true;
  }

  @Override
  protected boolean isFinished() {
    if (v){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
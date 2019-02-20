package frc.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.subsytems.VisionProcessingServer;

public class VisionDriving extends Command {

  
  double LeftGain1;
  double RightGain1;
  double Turn1;
  double Drive1;
  int LeftGain;
  int RightGain;
  int turn;
  int drive;
  public static double e ;
  int b;
  VisionProcessingServer k;
  boolean v;

  public VisionDriving() {
    requires(Robot.kDriveTrain);
  }

  @Override
  protected void initialize() {

    Robot.rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 1, 200);
    k = new VisionProcessingServer();
    k.getVars();
    LeftGain1 = k.returnVars("LeftGain");
    RightGain1 = k.returnVars("RightGain");
    Turn1 = k.returnVars("Turn");
    Drive1 = k.returnVars("Drive");
    LeftGain = (int)Math.round(LeftGain1);
    RightGain = (int)Math.round(RightGain1);
    turn = (int)Math.round(Turn1);
    drive = (int)Math.round(Drive1);
    e = Robot.rightDrive.getSelectedSensorPosition()*6.25*Math.PI/4096;
    b = 11;
  }

  @Override
  protected void execute() {
    v = false;
  while (e < turn*b){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      System.out.println(e);
    }

    Robot.rightDrive.setSelectedSensorPosition(0, 1, 200);
    
    while (e < drive*Math.tan(turn)){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
    }

    Robot.rightDrive.setSelectedSensorPosition(0, 1, 200);
    while (e < b*Math.PI/2){
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*-1*LeftGain, OI.getJoystick().getRawAxis(1)*-1*RightGain);
    }

    Robot.rightDrive.setSelectedSensorPosition(0, 1, 200);

    while (e < drive){
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
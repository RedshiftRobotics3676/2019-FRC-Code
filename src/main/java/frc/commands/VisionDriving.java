/*package frc.commands;

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
  double b;
  VisionProcessingServer k;
  boolean v;
  int x;

  public VisionDriving() {
    requires(Robot.kDriveTrain);
  }

  @Override
  protected void initialize() {
    Robot.rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    k = Robot.kVisionProcessingServer;
    k.getVars();
    LeftGain = k.LeftGain;
    RightGain = k.RightGain;
    turn = k.theta;
    drive = k.radius;
    b = 11.0;
    v = false;
    x = 0;
    Robot.rightDrive.setSelectedSensorPosition(0);
    e = 0.0;
  }

  @Override
  protected void execute() {
    if (e < turn*b && x==0)
    {
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",e);
    }
    if (e == Math.abs(10-(turn*b)) && x==0)
    {
    x=1;
    Robot.rightDrive.setSelectedSensorPosition(0);
    }
    if (e < drive*Math.tan(turn) && x==1)
    {
      e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
    }
    if (e == Math.abs(10-drive*Math.tan(turn)) && x==1)
    {
      x=2;
      Robot.rightDrive.setSelectedSensorPosition(0);  
    }
    if (e < b*Math.PI/2 && x==2)
    {
    e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*-1*LeftGain, OI.getJoystick().getRawAxis(1)*-1*RightGain);
    }
    if (e == Math.abs(10-b*Math.PI/2) && x==2)
    {
      x=3;
      Robot.rightDrive.setSelectedSensorPosition(0);
    }
    if (e < drive && x==3){
      e = Robot.rightDrive.getSelectedSensorPosition()*6.25*Math.PI/4096;
      if(LeftGain != 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      }
      else{
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
    }
    if (e == Math.abs(10-drive) && x==3){
      v = true;
    }
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
    Robot.kDriveTrain.drive2(0, 0);
    x=0;
  }

  @Override
  protected void interrupted() {
    end();
  }
}*/
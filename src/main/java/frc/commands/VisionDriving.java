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
  double b;
  boolean v;
  int x;

  public VisionDriving() {
    requires(Robot.kDriveTrain);
    requires(Robot.kVisionProcessingServer);
  }

  @Override
  protected void initialize() {
    Robot.rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    Robot.rightDrive.setSelectedSensorPosition(0);

    Robot.kVisionProcessingServer.getVars();
    LeftGain = Robot.kVisionProcessingServer.LeftGain;
    RightGain = Robot.kVisionProcessingServer.RightGain;
    turn = Robot.kVisionProcessingServer.theta;
    drive = Robot.kVisionProcessingServer.radius;

    b = 11.0;
    v = false;
    x = 0;
    e = 0.0;
  }

  @Override
  protected void execute() {
    if (e != (turn*b) && x==0)
    {
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",e);
      SmartDashboard.putNumber("Drive",b*Math.PI/2);

    }

    if (5 == Math.abs((turn*b)-e) && x==0)
    {
      x=1;
      Robot.rightDrive.setSelectedSensorPosition(0);
    }

    while (e != (drive/Math.tan(turn)) && x==1)
    {
      e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",e);
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
    }

    if (5 < Math.abs((drive/Math.tan(turn))-e) && x==1)
    {
      x=2;
      Robot.rightDrive.setSelectedSensorPosition(0);  
    }

    while (e != b*Math.PI/2 && x==2)
    {
      e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",e);
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*-1*LeftGain, OI.getJoystick().getRawAxis(1)*-1*RightGain);
    }

    if (5 < Math.abs((b*Math.PI/2)-e) && x==2)
    {
      x=3;
      Robot.rightDrive.setSelectedSensorPosition(0);
    }

    while (e != drive && x==3){
      Robot.kVisionProcessingServer.getVars();
      if(LeftGain != 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      }
      else{
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
      e = Robot.rightDrive.getSelectedSensorPosition()*6.25*Math.PI/4096;
      SmartDashboard.putNumber("Encoder",e);
    }

    if (1 < Math.abs(drive-e) && x==3){
      v = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
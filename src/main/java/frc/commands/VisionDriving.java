/*package frc.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.subsytems.VisionProcessingServer;

public class VisionDriving extends Command {

  double LeftGain, RightGain, turn, radius, tx, b;
  public static double e;
  int x;
  boolean v;
  NetworkTable table;
  NetworkTableEntry pipeline;
  double VisionDrivingTurnTollerance;
  double VisionDrivingDriveTollerance;

  public VisionDriving() {
    requires(Robot.kDriveTrain);
    requires(Robot.kVisionProcessingServer);
  }

  @Override
  protected void initialize() {
    Robot.rightDrive.setSelectedSensorPosition(0);

    table = VisionProcessingServer.table;
    table.getEntry("camMode").setNumber(0);
    Robot.kVisionProcessingServer.getVars2();
    
    pipeline = table.getEntry("pipeline");
    turn = Robot.kVisionProcessingServer.theta;
    radius = Robot.kVisionProcessingServer.radius;
    tx = 0.0;//Robot.kVisionProcessingServer.tx;

    b = 11.0;
    v = false;
    x = 0;
    e = 0.0;
    VisionDrivingTurnTollerance = 10.0;
    VisionDrivingDriveTollerance = 5.0;
  }

  @Override
  protected void execute() {
    e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
    SmartDashboard.putNumber("Encoder",e);
    SmartDashboard.putNumber("X", x);
    x = (int)SmartDashboard.getNumber("X", 0);

    if (tx != 0.0 && x < 5){
      Robot.kVisionProcessingServer.getVars2();
      LeftGain = Robot.kVisionProcessingServer.LeftGain+3;
      RightGain = Robot.kVisionProcessingServer.RightGain-3;
      x = 0;
    }

    if(tx == 0.0 && x < 5){
      LeftGain = 0.5*LeftGain;
      RightGain = 0.5*RightGain;      
      Robot.kVisionProcessingServer.getVars2();
      x++;
    }
    
    if(tx == 0.0 && x == 5) {
      LeftGain = 0.0;
      RightGain = 0.0;
      Robot.rightDrive.setSelectedSensorPosition(0);
      Robot.kVisionProcessingServer.getVars();
      pipeline.setNumber(3);
      x++;
    }

    if (VisionDrivingTurnTollerance < Math.abs((turn*b)-e) && 6 <= x && x < 10);
    {
      LeftGain = ((turn*b)-e)+3;
      RightGain = -((turn*b)-e)-3;
      x = 6;
    }
    
    if (VisionDrivingTurnTollerance >= Math.abs((turn*b)-e) && 6 <= x && x < 10)
    {
      LeftGain = 0.5*LeftGain;
      RightGain = 0.5*RightGain;
      x++;
    }

    if(VisionDrivingTurnTollerance >= Math.abs((turn*b)-e) && x == 10) {
      LeftGain = 0.0;
      RightGain = 0.0;
      Robot.rightDrive.setSelectedSensorPosition(0);
      x++;
    }

    if (VisionDrivingDriveTollerance < Math.abs((radius*Math.cos(turn))-e) && 11 <= x  && x < 15)
    {
      LeftGain = (radius*Math.cos(turn)-e)+3;
      RightGain = (radius*Math.cos(turn)-e)+3;
      x = 11;
    }

    if (VisionDrivingDriveTollerance >= Math.abs((radius*Math.cos(turn))-e) && 11 <= x  && x < 15)
    {
      LeftGain = 0.5*LeftGain;
      RightGain = 0.5*RightGain;
      x++;
    }

    if(VisionDrivingTurnTollerance >= Math.abs((radius*Math.cos(turn))-e) && x == 15) {
      LeftGain = 0.0;
      RightGain = 0.0;
      Robot.rightDrive.setSelectedSensorPosition(0);
      x++;
    }

    if (VisionDrivingTurnTollerance < Math.abs((b*Math.PI/2)-e) && 16 <= x  && x < 20)
    {
      LeftGain = -((b*Math.PI/2)-e)-3;
      RightGain = ((b*Math.PI/2)-e)+3;
      x = 16;
    }

    if (VisionDrivingTurnTollerance >= Math.abs((b*Math.PI/2)-e) && 16 <= x  && x < 20)
    {
      LeftGain = 0.5*LeftGain;
      RightGain = 0.5*RightGain;
      x++;
    }

    if(VisionDrivingTurnTollerance >= Math.abs((b*Math.PI/2)-e) && x == 20) {
      LeftGain = 0.0;
      RightGain = 0.0;
      Robot.rightDrive.setSelectedSensorPosition(0);
      x++;
    }

    if (VisionDrivingDriveTollerance < Math.abs(radius*Math.sin(turn)-e) && x == 21){
      Robot.kVisionProcessingServer.getVars2();

      if(tx != 0){
        LeftGain = Robot.kVisionProcessingServer.LeftGain+3;
        RightGain = Robot.kVisionProcessingServer.RightGain+3;  
      }
      else{
        LeftGain = 3;
        RightGain = 3;
      }
    }

    if (VisionDrivingDriveTollerance >= Math.abs(radius*Math.sin(turn)-e) && x == 21){
      LeftGain = 0.5*LeftGain;
      RightGain = 0.5*RightGain;
      pipeline.setNumber(3);
      v = true;
    }

    Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
  }

  @Override
  protected boolean isFinished() {
    return v;
  }

  @Override
  protected void end() {
    table.getEntry("camMode").setNumber(1);
    Robot.kDriveTrain.drive2(0, 0);
    x = 0;
  }

  @Override
  protected void interrupted() {
    end();
  }
}*/
/*package frc.commands.Drive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
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
    Robot.kVisionProcessingServer.getVars();
    
    pipeline = table.getEntry("pipeline");
    LeftGain = Robot.kVisionProcessingServer.LeftGain;
    RightGain = Robot.kVisionProcessingServer.RightGain;
    turn = Robot.kVisionProcessingServer.theta;
    radius = Robot.kVisionProcessingServer.radius;
    tx = Robot.kVisionProcessingServer.tx;

    b = Constants.VisionDrivingRad;
    v = false;
    x = -1;
    e = 0.0;
  }

  @Override
  protected void execute() {
    e = Robot.rightDrive.getSelectedSensorPosition()*6.3*Math.PI/4096;
    SmartDashboard.putNumber("Encoder",e);

    if (tx != 0.0 && x == -1){
      double spd0 = table.getEntry("tx").getDouble(0.0)/Math.abs(table.getEntry("tx").getDouble(0.0));
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*spd0, OI.getJoystick().getRawAxis(1)*spd0);
    }

    if(tx == 0.0 && x == -1){
      Robot.kDriveTrain.drive2(0, 0);
      Robot.kVisionProcessingServer.getVars2();
      Robot.kVisionProcessingServer.getVars();
      x = 0;
    }
    
    if (Constants.VisionDrivingTurnTollerance < Math.abs((turn*b)-e) && x == 0);
    {
      double spd1 = ((turn*b)-e)/Math.abs((turn*b)-e);
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain*spd1, OI.getJoystick().getRawAxis(1)*RightGain*spd1);
    }
    
    if (Constants.VisionDrivingTurnTollerance >= Math.abs((turn*b)-e) && x == 0)
    {
      Robot.kDriveTrain.drive2(0, 0);
      Robot.rightDrive.setSelectedSensorPosition(0);
      x=1;
    }

    if (Constants.VisionDrivingDriveTollerance < Math.abs((radius*Math.cos(turn))-e) && x == 1)
    {
      double spd2 = (radius*Math.cos(turn)-e)/Math.abs(radius*Math.cos(turn)-e);
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*spd2, OI.getJoystick().getRawAxis(1)*spd2);
    }

    if (Constants.VisionDrivingDriveTollerance >= Math.abs((radius*Math.cos(turn))-e) && x==1)
    {
      Robot.kDriveTrain.drive2(0, 0);
      x=2;
      Robot.rightDrive.setSelectedSensorPosition(0);  
    }

    if (Constants.VisionDrivingTurnTollerance < Math.abs((b*Math.PI/2)-e) && x==2)
    {
      double spd3 = ((b*Math.PI/2)-e)/Math.abs((b*Math.PI/2)-e);
      Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*spd3*-LeftGain, OI.getJoystick().getRawAxis(1)*spd3*-RightGain);
    }

    if (Constants.VisionDrivingTurnTollerance >= Math.abs((b*Math.PI/2)-e)&& x==2)
    {
      Robot.kDriveTrain.drive2(0, 0);
      x=3;
      Robot.rightDrive.setSelectedSensorPosition(0);
    }

    if (Constants.VisionDrivingDriveTollerance < Math.abs(radius*Math.sin(turn)-e) && x==3){
      Robot.kVisionProcessingServer.getVars2();

      if(LeftGain != 0){
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1)*LeftGain, OI.getJoystick().getRawAxis(1)*RightGain);
      }
      else{
        Robot.kDriveTrain.drive2(OI.getJoystick().getRawAxis(1), OI.getJoystick().getRawAxis(1));
      }
    }

    if (10.0 >= Math.abs(radius*Math.sin(turn)-e) && x==3){
      pipeline.setNumber(3);
      v = true;
      end();
    }
  }

  @Override
  protected boolean isFinished() {
    return v;
  }

  @Override
  protected void end() {
    table.getEntry("camMode").setNumber(1);
    Robot.kDriveTrain.drive2(0, 0);
    x = -1;
  }

  @Override
  protected void interrupted() {
    end();
  }
}*/
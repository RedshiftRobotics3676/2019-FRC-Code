/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Vision;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class VisionDriving extends Command {
  public VisionDriving() {
    requires(Robot.kVisionProcessingServer);
  }
  VisionProcessingServer k;
  Encoder E;
  double a;
  double r;
  double l;
  int b;
  int c;
  int d;
  int e;
  double f;
  int g;
  
  @Override
  protected void initialize() {
    k = new VisionProcessingServer();
    k.Start();
  }

  @Override
  protected void execute() {
    E = new Encoder (1,2,false);
    a = k.getTurn();
    r = k.getRightGain();
    f = k.getDrive();
    l = k.getLeftGain();
    b = (int)Math.round(a);
    c = (int)Math.round(r);
    d = (int)Math.round(l);
    g = (int)Math.round(f);
    e = E.get()/2/*encoder value per revlution*/;
    
    while (e < b){

    }
    E.reset();
    e = E.get()/2/*encoder value per revlution*/;
    b = 2;/*distance of arcturning 90 degrees*/
    while (e < b){

    }
    E.reset();
    e = E.get()/2/*encoder value per revlution*/;
    while (e < g){

    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

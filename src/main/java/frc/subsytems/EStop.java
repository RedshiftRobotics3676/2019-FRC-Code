/*package frc.subsytems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class EStop extends Subsystem {

  boolean eTopVal;  
  boolean eBotVal;
  boolean aTopVal;
  boolean aBotVal;
  boolean iSwitchVal;
  boolean ExtendedEStopActive;
  double DriveCurrentMax;
  double ArmMotorMax;
  double ElevatorMotorMax;

  public EStop(){
      eBotVal = Robot.eBot.get();
      eTopVal = Robot.eTop.get();
      aBotVal = Robot.aBot.get();
      aTopVal = Robot.aTop.get();
      iSwitchVal = Robot.iSwitch.get();
      ExtendedEStopActive = false;
      DriveCurrentMax = 5.0;
      ArmMotorMax = 5.0;
      ElevatorMotorMax = 5.0;
  }

  public void run(){
  if (eBotVal) {
    Robot.kElevator.hold();
  }

  if (eTopVal) {
    Robot.kElevator.hold();
  }

  if (aBotVal) {
    Robot.kArm.hold();
  }

  if (aTopVal) {
    Robot.kArm.hold();
  }

  if (iSwitchVal) {
    Robot.kIntake.stop();
  }

  if (ExtendedEStopActive) {
    if (Robot.leftDrive.getOutputCurrent() > DriveCurrentMax) {
      Robot.kDriveTrain.stop();
    }

    if (Robot.rightDrive.getOutputCurrent() > DriveCurrentMax) {
      Robot.kDriveTrain.stop();
    }

    if (Robot.aTalon.getOutputCurrent() > ArmMotorMax) {
      Robot.kArm.hold();
    }

    if (Robot.eTalon.getOutputCurrent() > ElevatorMotorMax) {
      Robot.kElevator.stop();
    }
    //NO VICTOR STOPS YET, ADD LATER!!!!!!
  }
}

  @Override
  public void initDefaultCommand() {
  }
}
*/
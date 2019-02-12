/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.commands.ElevatorMove;
import frc.commands.TurnToAngle;
//import frc.commands.TurnToAngle;
import frc.subsytems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class OI
{
  Button a, b;

  public OI()
  {
    a = new JoystickButton(stick, 1);
    b = new JoystickButton(stick, 2);

    //TurnToAngle Command
    //Pass the desired angle for the robot to turn to
    //Press while turning in order to make it stop
    a.toggleWhenPressed(new TurnToAngle(90.0));

    b.toggleWhenPressed(new ElevatorMove());

    //a.toggleWhenPressed(new ElevatorMove());
  }

  private static Joystick stick = new Joystick(0);

  public static Joystick getJoystick()
  {
    return stick;
  }
}

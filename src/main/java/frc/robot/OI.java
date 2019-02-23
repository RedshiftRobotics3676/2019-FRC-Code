/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.commands.*;

public class OI
{
  Button a, b, x, y, lb, rb, start, back;
  Button a2, b2, x2, y2, lb2, rb2, start2, back2, lt2, rt2;
  private static Joystick stick = new Joystick(0); //xbox
  private static Joystick stick2 = new Joystick(1);//logitech

  public OI()
  {
    a = new JoystickButton(stick, 1);
    b = new JoystickButton(stick, 2);
    x = new JoystickButton(stick, 3);
    y = new JoystickButton(stick, 4);
    lb = new JoystickButton(stick, 5);
    rb = new JoystickButton(stick, 6);
    back = new JoystickButton(stick, 7);
    start = new JoystickButton(stick, 8);

    a2 = new JoystickButton(stick2, 2);
    b2 = new JoystickButton(stick2, 3);
    x2 = new JoystickButton(stick2, 1);
    y2 = new JoystickButton(stick2, 4); 
    lb2 = new JoystickButton(stick2, 5);
    rb2 = new JoystickButton(stick2, 6);
    lt2 = new JoystickButton(stick2, 7);
    rt2 = new JoystickButton(stick2, 8);
    back2 = new JoystickButton(stick2, 9);
    start2 = new JoystickButton(stick2, 10);

    //TurnToAngle Command
    //Pass the desired angle for the robot to turn to
    //Press while turning in order to make it stop
    //a.toggleWhenPressed(new TurnToAngle(90.0));

    //a.toggleWhenPressed(new ElevatorTest());

    //b.toggleWhenPressed(new ElevatorStop());

    
    /*lb2.whileHeld(new ArmUp());
    lt2.whileHeld(new ArmDown());
    y2.whenPressed(new ArmStop());
    */
    /*a.whenPressed(new ElevatorUp());
    b.whenPressed(new ElevatorDown());
    x.whenPressed(new ElevatorHold());
    */

    rb2.whileHeld(new Inhale());
    rt2.whileHeld(new Exhale());
    
    x2.toggleWhenPressed(new HatchGrab());
    a2.whileHeld(new OnePunch());
    //start2.toggleWhenPressed(new VisionDriving());
    
    //Positions
    if(stick.getPOV(0) == 270)
      Scheduler.getInstance().add(new PositionControl(1));
    if(stick.getPOV(0) == 90)
      Scheduler.getInstance().add(new PositionControl(2));
    if(stick.getPOV(0) == 180)
      Scheduler.getInstance().add(new PositionControl(3));
    if(stick.getPOV(0) == 0)
      Scheduler.getInstance().add(new PositionControl(4));

    x.whenPressed(new PositionControl(5));
    b.whenPressed(new PositionControl(6));
    a.whenPressed(new PositionControl(7));
    y.whenPressed(new PositionControl(8));
  }

  public Joystick getJoystick(int i)
  {
    if(i == 0)
      return stick;
    else if(i == 1)
      return stick2;
    return stick;
  }
  public static Joystick getJoystick()
  {
    return stick2;
  }

}

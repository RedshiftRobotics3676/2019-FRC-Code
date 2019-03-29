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
    
    lb2.whileHeld(new ArmUp());
    lt2.whileHeld(new ArmDown());
    b2.whenPressed(new ArmStop());

    rb2.whileHeld(new Inhale());
    rt2.whileHeld(new Exhale());
    
    x2.whenPressed(new HatchGrab());
    a2.whileHeld(new OnePunch());
    //x2.whenPressed(new ArmStuff());
    //x2.whenReleased(new HatchRetract()); 
    //a2.whileHeld(new HatchRelease());
    //b2.whenPressed(new DriveMM());
    
    //Positions
    x.whenPressed(new PositionControl(5));//ball in
    b.whenPressed(new PositionControl(6));//ground in
    a.whenPressed(new PositionControl(7));//bottom rocket
    y.whenPressed(new PositionControl(8));//top rocket

    rb.whenPressed(new PositionControl(4));//hatch high
    lb.whenPressed(new PositionControl(3));//hatch intake
    start.whenPressed(new PositionControl(2));//travel
    back.whenPressed(new PositionControl(1));//start

    start2.whileHeld(new ElevatorUp());
    back2.whileHeld(new ElevatorDown());
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
/*
Jeroo Benjamin = new Jeroo (0,0,12,NORTH);
Benjamin.play(Fortnite);
Epic.Dub(420kill);
*/

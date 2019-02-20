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
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.HatchIntakeClose;
import frc.robot.commands.HatchIntakeOpen;
import frc.robot.commands.IntakeStart;
import frc.robot.commands.VisionDriving;
//import frc.robot.commands.Turn;
import frc.robot.subsystems.Elevator;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  //Create Joystick
  private static Joystick stick = new Joystick(0);

  Button a, b, x, y, lb, lt, rb, rt;

  public OI()
  {
    //Initialize Buttons
    a = new JoystickButton(stick, 1);
    b = new JoystickButton(stick, 2);
    x = new JoystickButton(stick, 3);
    y = new JoystickButton(stick, 4);
    lb = new JoystickButton(stick, 5);
    rb = new JoystickButton(stick, 6);
    lt = new JoystickButton(stick, 7);
    rt = new JoystickButton(stick, 8);

    //Assign Buttons
    lb.whenPressed(new ArmUp());
    lt.whenPressed(new ArmDown());
    
    rb.toggleWhenPressed(new IntakeStart());
    rt.toggleWhenPressed(new HatchIntakeOpen());

    y.toggleWhenPressed(new ElevatorUp());
    a.toggleWhenPressed(new ElevatorDown());

    //x.toggleWhenPressed(new Turn(90.0));
    

    x.toggleWhenPressed(new HatchIntakeClose());//Combine with IntakeStart
    x.toggleWhenPressed(new IntakeStart());

    //if(stick.getPOV() == 180)
     // new ArmDown();
    //else if(stick.getPOV() == 0)
      //new ArmUp();

    //a.toggleWhenPressed(new VisionDriving());
  }

  public static Joystick getJoystick()
  {
    return stick;
  }
}

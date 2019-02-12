package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmTest extends CommandGroup
{
    public ArmTest()
    {
        //addSequential();

        addSequential(new ArmUp());
        addSequential(new ArmDown());
        //addSequential(new PunchReset());
    }
}
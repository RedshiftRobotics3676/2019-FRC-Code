package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchTest extends CommandGroup
{
    public HatchTest()
    {
        //addSequential();

        addSequential(new HatchGrab());
        addSequential(new HatchPunch());
        //addSequential(new PunchReset());
    }
}
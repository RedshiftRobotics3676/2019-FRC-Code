package frc.testcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.ArmDown;
import frc.commands.ArmUp;

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
package frc.testcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.commands.ElevatorDown;
import frc.commands.ElevatorUp;

public class ElevatorTest extends CommandGroup
{
    public ElevatorTest()
    {
        //addSequential();

        addSequential(new ElevatorUp());
        addSequential(new ElevatorDown());
        //addSequential(new PunchReset());
    }
}
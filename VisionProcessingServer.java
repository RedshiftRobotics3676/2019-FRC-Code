package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionProcessingServer extends Subsystem {
    NetworkTableEntry LeftGain1;
    NetworkTableEntry RightGain1;
    NetworkTableEntry Turn1;
    NetworkTableEntry Drive1;
    Double LeftGain;
    Double RightGain;
    Double Turn;
    Double Drive;
    NetworkTableInstance inst;
    NetworkTable tableOut;
    @Override
    protected void initDefaultCommand() {

    }
    public void Start(){
        inst = NetworkTableInstance.getDefault();
        inst.startServer();
        tableOut = inst.getTable("tableOut");
    }
    public void Gain() {
        
        LeftGain1 = tableOut.getEntry("LeftGain");
        RightGain1 = tableOut.getEntry("RightGain");
        Turn1 = tableOut.getEntry("Turn");
        Drive1 = tableOut.getEntry("Drive");

        LeftGain = LeftGain1.getDouble(0.0);
        RightGain = RightGain1.getDouble(0.0);
        Turn = Turn1.getDouble(0.0);
        Drive = Drive1.getDouble(0.0);

    }
        public double getRightGain() {
            return this.RightGain;
        }

        public double getLeftGain() {
            return this.LeftGain;
        }
        public double getTurn(){
        return this.Turn;
        }

    public double getDrive() {
        return this.Drive;
    }
    }
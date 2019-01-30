package frc.robot.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Vision.Locator;

public class VisionProcessingClient {

    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;
    double x4;
    double y4;

    public static void main(String[] args) {
        new VisionProcessingClient().run();

    }
    public void run() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        inst.startDSClient();
        inst.startClientTeam(3676);  // where TEAM=190, 294, etc, or use inst.startClient("hostname") or similar
        NetworkTable tableIn = inst.getTable("limelight");

        
        while (true){
        
        
        NetworkTableEntry x1Entry = tableIn.getEntry("x1");
        NetworkTableEntry y1Entry = tableIn.getEntry("y1");
        NetworkTableEntry x2Entry = tableIn.getEntry("x2");
        NetworkTableEntry y2Entry = tableIn.getEntry("y2");
        NetworkTableEntry x3Entry = tableIn.getEntry("x3");
        NetworkTableEntry y3Entry = tableIn.getEntry("y3");
        NetworkTableEntry x4Entry = tableIn.getEntry("x4");
        NetworkTableEntry y4Entry = tableIn.getEntry("y4");
        x1 = x1Entry.getDouble(0.0);
        y1 = y1Entry.getDouble(0.0);
        x2 = x2Entry.getDouble(0.0);
        y2 = y2Entry.getDouble(0.0);
        x3 = x3Entry.getDouble(0.0);
        y3 = y3Entry.getDouble(0.0);
        x4 = x4Entry.getDouble(0.0);
        y4 = y4Entry.getDouble(0.0);

        Locator Finder = new Locator();
        double LeftGainValue = Finder.getLeftGain();
        double RightGainValue = Finder.getRightGain();
        double DriveValue = Finder.getDrive();

        NetworkTable tableOut = inst.getTable("tableOut");
        NetworkTableEntry LeftGain = tableOut.getEntry("LeftGain");
        NetworkTableEntry RightGain = tableOut.getEntry("RightGain");
        NetworkTableEntry Turn = tableOut.getEntry("Turn");
        NetworkTableEntry Drive = tableOut.getEntry("Drive");

        LeftGain.setDouble(LeftGainValue);
        RightGain.setDouble(RightGainValue);
        Turn.setDouble(Finder.Turn);
        Drive.setDouble(DriveValue);
        }
    }
    }
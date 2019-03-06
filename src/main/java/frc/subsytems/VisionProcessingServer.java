package frc.subsytems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionProcessingServer extends Subsystem {

    public static NetworkTable table;
    public static NetworkTableEntry pipeline;
    NetworkTableEntry light;
    NetworkTableEntry h1E;
    NetworkTableEntry h2E;
    NetworkTableEntry WpE;
    double h1;
    double h2;
    double Wp;
    double Q = 14.5;
    public double theta;
    public double radius;
    public double RightGain;
    public double LeftGain;

  
    public VisionProcessingServer(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        pipeline = table.getEntry("pipeline");
        VisionProcessingServer.table.getEntry("camMode").setNumber(0);
        getVars();
    }

    public void getVars(){

        pipeline.setNumber(0);
        h1E = table.getEntry("tvert");
        

        pipeline.setNumber(1);
        h2E = table.getEntry("tvert");

        pipeline.setNumber(2);
        WpE = table.getEntry("tlong");

        //LeftGain =  table.getEntry("tx").getDouble(0.0);
        //RightGain = -1 * table.getEntry("tx").getDouble(0.0);

        h1 = 160;
        h2 = 160;
        Wp = 160;
        LeftGain = 1;
        RightGain = 1;
        theta = 4;
        radius = 20;
        //theta = 2*Math.atan((Wp*Math.tan(Math.toRadians(table.getEntry("tx").getDouble(0.0))))/(320*((h1/h2)-1)));
        //radius = (Q*Math.sin(theta))/((h1/h2)-1);
        
    }
    @Override
    protected void initDefaultCommand() {
    }
}
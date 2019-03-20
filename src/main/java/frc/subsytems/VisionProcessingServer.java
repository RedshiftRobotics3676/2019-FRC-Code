package frc.subsytems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionProcessingServer extends Subsystem {

    public static NetworkTable table;
    public static NetworkTableEntry pipeline;
    NetworkTableEntry light;
    double h1E;
    double h2E;
    double WpE;
    double h1;
    double h2;
    double Wp;
    double d1;
    double d2;
    public double theta;
    public double radius;
    public double RightGain;
    public double LeftGain;
    public double tx;

  
    public VisionProcessingServer(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        pipeline = table.getEntry("pipeline");
    }

    public void getVars(){

        pipeline.setNumber(0);
        h1E = table.getEntry("tvert").getDouble(0.0);

        pipeline.setNumber(1);
        h2E = table.getEntry("tvert").getDouble(0.0);

        pipeline.setNumber(2);
        WpE = table.getEntry("tlong").getDouble(0.0);

        //Test Values
        theta = 1;
        radius = 20;
        //d1 = Height of tape*240/((h1E)*2*Math.tan(Math.toRadians(49.7 / 2)));
        //d2 = Height of tape*240/((h2E)*2*Math.tan(Math.toRadians(49.7 / 2)));
        //theta = Math.asin(Math.abs(d2-d1)/Width);
        //radius = Height of tape*240/((h1E+h2E)*Math.tan(Math.toRadians(49.7 / 2)));
    }

    public void getVars2(){
        pipeline.setNumber(2);

        tx = table.getEntry("tx").getDouble(0.0);
        LeftGain = tx;
        RightGain = -tx;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
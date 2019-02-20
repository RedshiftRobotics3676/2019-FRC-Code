package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionProcessingServer extends Subsystem {

    NetworkTable table;
    NetworkTableEntry pipeline;
    NetworkTableEntry light;
    NetworkTableEntry h1E;
    NetworkTableEntry h2E;
    NetworkTableEntry WpE;
    double h1;
    double h2;
    double Wp;
    double Q = 14.5;
    double theta;
    double radius;

    @Override
    protected void initDefaultCommand() {
        
    }

    public void getVars(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        pipeline = table.getEntry("pipeline");
        light = table.getEntry("ledMode");

        pipeline.setNumber(0);
        h1E = table.getEntry("tvert");
        

        pipeline.setNumber(1);
        h2E = table.getEntry("tvert");

        pipeline.setNumber(2);
        WpE = table.getEntry("tlong");

        h1 = 160; //Math.round(h1E.getDouble(0.0));
        h2 = 160; //Math.round(h2E.getDouble(0.0));
        Wp = 160; //Math.round(WpE.getDouble(0.0));

        theta = 2*Math.atan((Wp*Math.tan(Math.toRadians(27)))/(320*((h1/h2)-1)));
        radius = (Q*Math.sin(theta))/((h1/h2)-1);

        System.out.printf("h1 = %f\nh2 = %f\nWp = %f\nradius = %f\n",h1,h2,Wp,radius);
    }

    public double returnVars(String s){
        String S = s;
        
        switch(S){
            case "LeftGain":
                return table.getEntry("tx").getDouble(0.0);
            case "RightGain":
                return -1*table.getEntry("tx").getDouble(0.0);
            case "Turn":
                return theta;
            case "Drive":
                return radius;
            default:
                return 0;
        }
    }
}
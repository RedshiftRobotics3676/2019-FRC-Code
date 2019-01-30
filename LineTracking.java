package frc.robot.Vision;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LineTracking extends Subsystem{
    
@Override
protected void initDefaultCommand() {
    
}

    DigitalInput right1;
    DigitalInput middle1;
    DigitalInput left1;
    boolean right2;
    boolean middle2;
    boolean left2;
    int right3;
    int middle3;
    int left3;
    int power;
    String value;
    int all;
    double SteeringGainLeft;
    double SteeringGainRight;

    public void TrackLine() {
        right1 = new DigitalInput (1);
        middle1 = new DigitalInput (2);
        left1 = new DigitalInput (3);
        power = 0;
        
        right2 = left1.get();
        middle2 = middle1.get();
        left2 = right1.get();
        
        right3 = right2 ? 1:0;
        middle3 = middle2 ? 1:0;
        left3 = left2 ? 1:0;
        
        value = "" + power + right3 + middle3 + left3;
        all = Integer.valueOf(value);

        switch (all) {
            case 1000: 
                SteeringGainLeft = 0.0;
                SteeringGainRight = 0.0;
                break;
            case 1001:
                SteeringGainLeft = -1.0;
                SteeringGainRight = 1.0;
                break;
            case 1010:
                SteeringGainLeft = 1.0;
                SteeringGainRight = 1;
                break;
            case 1011:
                SteeringGainLeft = -1;
                SteeringGainRight = 1.0;
                break;
            case 1100:
                SteeringGainLeft = -1.0;
                SteeringGainRight = 1.0;
                break;
            case 1101:
                SteeringGainLeft = 1.0;
                SteeringGainRight = 1.0;
                break;
            case 1110:
                SteeringGainLeft = -1.0;
                SteeringGainRight = 1.0;
                break;
            case 1111:
                SteeringGainLeft = 1.0;
                SteeringGainRight = 1.0;
                break;
            default:
                SteeringGainLeft = 0.0;
                SteeringGainRight = 0.0;
            break;
        }
    }
}
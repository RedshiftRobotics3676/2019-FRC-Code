package frc.robot.Vision;

public class Locator {

    double yLong;
    double yShort;
    double ratio1;
    double ratio2;
    double height1;
    double height2;
    double distance1;
    double distance2;
    double yComp;
    double xComp;
    double radius;
    VisionProcessingClient View;

    public double LeftGainValue;
    public double RightGainValue;
    public double Turn;
    public double Drive;

   public double getLeftGain(){
       return this.LeftGainValue;
   }
   public double getRightGain(){
       return this.RightGainValue;
   }
   public double getDrive(){
       return this.Drive;
   }

   public double Radius(){
    View = new VisionProcessingClient();
    yLong = View.y1 - View.y2;
    yShort = View.y3 - View.y4;
    ratio1 = 2.0/*pixelheight*//yLong;
    ratio2 = 2.0/*pixelheight*//yShort;
    height1 = ratio1*2.0/*actualheight*/;
    height2 = ratio2*2.0/*actualheight*/;
    distance1 = /*distance/height*/2.0*height1;
    distance2 = /*distance/height*/2.0*height2;
    yComp = (distance1 + distance2)/2.0;
    xComp = Math.abs(2.0/*center x value*/-(View.x1+View.x2+View.x3+View.x4)/4.0);
    radius = Math.sqrt((xComp*xComp)+(yComp*xComp));
    return radius;
   }

   public double Theta(){
       double DistanceDiff = distance1 - distance2;
       double CosVal = DistanceDiff/2.0/*length between points*/;
       double Theta = Math.acos(CosVal);
        return Theta;
   }

   public void Turn(){
    Locator Place = new Locator();
    Drive = Place.Radius()*Math.sin(Place.Theta());
    Turn = (Place.Theta()*2.0/*radius between wheels*/)/2.0/*radius of wheels */;
    if (Place.Theta() < 90){
        RightGainValue = -1.0;
        LeftGainValue = 1.0;
    }
    if (Place.Theta() > 90){
        RightGainValue = 1.0;
        LeftGainValue = -1.0;
    }
    if (Place.Theta() == 90){
        RightGainValue = 1.0;
        LeftGainValue = 1.0;
    }
   }
}
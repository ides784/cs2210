/**
 * @author Derek Liu
 *
 * A class that represents the position (x, y) of a pixel.
 */

public class Location {
    int xvalue;
    int yvalue;
    public Location(int x, int y){
        xvalue = x;
        yvalue = y;
    }

    public int xCoord(){
        return xvalue;
    }
    public int yCoord(){
        return yvalue;
    }

    //compares this location with p using column order.
    public int compareTo (Location p){
        if (xvalue > p.xCoord()){
            return 1;
        }
        else if (xvalue == p.xCoord()){
            if (yvalue < p.yCoord()){
                return -1;
            }
            if (yvalue == p.yCoord()){
                return 0;
            }
            else{
                return 1;
            }
        }
        return -1;
    }

}




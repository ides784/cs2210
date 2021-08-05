/**
 * @author Derek Liu
 *
 * A class that represents the data items to be stored in the binary search tree, with each data item
 * consisting of two parts: a Location and an int color
 */

public class Pixel {

    Location point;
    int colornum;

    public Pixel(Location p, int color){
        point = p;
        colornum = color;
    }

    public Location getLocation(){
        return point;
    }

    public int getColor(){
        return colornum;
    }
}

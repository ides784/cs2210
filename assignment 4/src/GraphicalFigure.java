/**
 * @author Derek Liu
 *
 * A class that sets the parameters for the graphical figures of the game.
 */
public class GraphicalFigure implements GraphicalFigureADT{
    private final int ID, Width, Height;
    private String Type;
    private Location Pos;
    private final BinarySearchTree Tree;

    public GraphicalFigure (int id, int width, int height, String type, Location pos){
        this.ID = id;
        this.Width = width;
        this.Height = height;
        this.Type = type;
        this.Pos = pos;
        Tree = new BinarySearchTree();
    }

    @Override
    public void setType(String type){
        Type = type;
    }

    @Override
    public int getWidth(){
        return Width;
    }

    @Override
    public int getHeight(){
        return Height;
    }

    @Override
    public String getType(){
        return Type;
    }

    @Override
    public int getId(){
        return ID;
    }

    @Override
    public Location getOffset(){
        return Pos;
    }

    @Override
    public void setOffset(Location value){
        Pos=value;
    }

    @Override
    public void addPixel(Pixel pix) throws DuplicatedKeyException{
        Tree.put(Tree.getRoot(), pix);
    }

    private boolean findPixel(Location p) {
        Pixel overlap = Tree.get(Tree.getRoot(), p);
        return overlap != null;
    }

    //returns true if two images are overlapping each other
    @Override
    public boolean intersects (GraphicalFigure gobj) {
        int objx = getOffset().xCoord();
        int objy = getOffset().yCoord();
        int gobjx = gobj.getOffset().xCoord();
        int gobjy = gobj.getOffset().yCoord();

        if (!Tree.getRoot().isLeaf()){
            Pixel small = gobj.Tree.smallest(Tree.getRoot());
            Location loc = new Location(small.getLocation().xCoord()+objx-gobjx, small.getLocation().yCoord()+objy-gobjy);
            while (small!= null){
                if (gobj.findPixel(loc)){
                    return true;
                }
                small = Tree.successor(Tree.getRoot(), small.getLocation());
            }
        }
        return false;

    }
}


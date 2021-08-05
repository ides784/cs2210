/**
 * @author Derek Liu
 *
 * A class that represents the nodes of the binary search tree, where each node will store an object of the class Pixel
 * and it will have references to its left child, its right child, and its parent.
 */

public class BinaryNode {
    Pixel Value;
    BinaryNode Left;
    BinaryNode Right;
    BinaryNode Parent;

    public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
        Value = value;
        Left = left;
        Right = right;
        Parent = parent;
    }
    public BinaryNode(){
        Value = null;
        Left = null;
        Right = null;
        Parent = null;
    }
    public BinaryNode getParent(){
        return Parent;
    }
    public void setParent(BinaryNode parent){
        Parent = parent;
    }

    public void setLeft (BinaryNode p){
        Left = p;
    }
    public void setRight (BinaryNode p){
        Right = p;
    }
    public void setData (Pixel value){
        Value = value;
    }
    public boolean isLeaf(){
        return Left == null && Right == null;
    }
    public Pixel getData(){
        return Value;
    }
    public BinaryNode getLeft(){
        return Left;
    }
    public BinaryNode getRight(){
        return Right;
    }
}

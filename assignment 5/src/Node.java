/**
 * @author Derek Liu
 *
 * A class that represents a node of a graph.
 */

public class Node{

    int Name;
    boolean Mark;

    /**
     *  the constructor for the class and it creates a node with the given
     * name
     * @param name
     */
    Node(int name){
        Name = name;
    }

    /**
     * Marks the node with the specified value, either true or false
     * @param mark
     */
    void setMark(boolean mark){
        Mark = mark;
    }


    /**
     * Returns the value with which the node has been marked
     * @return
     */
    boolean getMark(){
        return Mark;
    }


    /**
     * Returns the name of the vertex
     * @return
     */
    int getName(){
        return Name;
    }
}

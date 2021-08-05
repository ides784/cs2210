/**
 * @author Derek Liu
 *
 * A class that represents a edge of a graph.
 */

public class Edge{
    Node U;
    Node V;
    int Type;


    /**
     *  The constructor for the class. The first two parameters
     * are the endpoints of the edge.
     * @param u
     * @param v
     * @param type
     */
    Edge(Node u, Node v, int type){
        U = u;
        V = v;
        Type = type;
    }


    /**
     *  Returns the first endpoint of the edge
     * @return
     */
    Node firstEndpoint(){
        return U;
    }

    /**
     *  Returns the second endpoint of the edge
     * @return
     */
    Node secondEndpoint(){
        return V;
    }


    /**
     * Returns the type of the edge
     * @return
     */
    int getType(){
        return Type;
    }
}

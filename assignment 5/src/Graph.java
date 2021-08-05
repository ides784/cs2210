import java.util.*;
/**
 * @author Derek Liu
 *
 * A class that represents an undirected graph, using the adjacency matrix method.
 */

public class Graph implements GraphADT{
    Node[]array;
    Edge[][]matrix;
    int N;


    /**
     *  Creates a graph with n nodes and no edges
     * @param n the number of nodes
     *
     */
    Graph(int n){
        N =n;
        matrix = new Edge[N][N]; //create a adjacency matrix and load in data
        array = new Node[N];
        for (int i=0; i<n;i++)
            array[i] =new Node(i);

    }


    /**
     *
     * @param u
     * @param v
     * @param edgeType
     * @throws GraphException
     */
    @Override
    public void insertEdge(Node u, Node v,int edgeType)throws GraphException{// Adds an edge of the given type connecting
        //u and v
        if(v.getName()>= 0&&v.getName() < N&&
            u.getName()>= 0&&u.getName() < N){
            matrix[v.getName()][u.getName()] = new Edge(u, v, edgeType); //creates edges between nodes
            matrix[u.getName()][v.getName()] = new Edge(u, v, edgeType);
        }
        else
            throw new GraphException();
    }

    /**
     *
     * Returns the node with the specified name.
     * @param name the node with name to be returned
     * @return
     * @throws GraphException
     */
    @Override
    public Node getNode(int name)throws GraphException{ // Returns the node with the specified name.
        if(name>=0 && name < N)
            return array[name];
        throw new GraphException();
    }

    /**
     *
     * Returns a Java Iterator storing all the edges incident
     * on node u
     * @param u the node to check incident nodes
     * @return
     * @throws GraphException
     */
    @Override
    public Iterator incidentEdges(Node u)throws GraphException{//Returns a Java Iterator storing all the edges incident
        //on node u
        if(u.getName()>= 0&&u.getName() <N){
            LinkedList linkedlist=new LinkedList(); //uses a linkedlist strcuture

            for(int i =0; i < N;i ++){
                if(matrix[u.getName()][i]!=null){ //if current node is connected to another node, its then added to the LL
                    linkedlist.add(matrix[u.getName()][i]);
                }
            }
            return linkedlist.iterator();
        }
        else
            throw new GraphException();
    }

    /**
     *  Returns the edge connecting nodes u and v
     * @param u one of the node holding the connection
     * @param v one of the node holding the connection
     * @return the edge connecting ther 2 nodes
     * @throws GraphException
     */
    @Override
    public Edge getEdge(Node u, Node v)throws GraphException{//Returns the edge connecting nodes u and v
        if(areAdjacent(u,v)){
            return matrix[u.getName()][v.getName()]; //uses the areadjacent method
        }
        else{
            throw new GraphException();
        }
    }


    /**
     *
     * Returns true if nodes u and v are adjacent
     * @param u node to check
     * @param v node to check
     * @return
     * @throws GraphException
     */
    @Override
    public boolean areAdjacent(Node u, Node v) throws GraphException{//checks if nodes u and v are adjacent
        if (u.getName()<array.length&&u.getName()>=0&&v.getName()<array.length&&v.getName()>=0)
            return matrix[u.getName()][v.getName()]!=null; //see if the edges are connected or not
        else
            throw new GraphException();
    }

}

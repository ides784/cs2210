import java.util.*;
import java.io.*;

/**
 * @author Derek Liu
 *
 * A class that represents the road map, where a graph will be used to store
 * the map and try to find a path from the starting point to the destination.
 */

public class RoadMap{
    private final int tollroadnum;
    private final int rewardroadnum;
    private final int startingnode;
    private final int destinationnode;
    private final int initalmoney;
    private final Stack<Node> pathstack = new Stack();
    private final Graph graph;

    /**
     * Constructor for building a graph from the input
     * @param inputFile builds a graph form this file
     * @throws IOException
     * @throws MapException
     */
    public RoadMap(String inputFile) throws IOException,MapException{//Constructor for building a graph from the input
        // file specified in the parameter
        BufferedReader bufferedreader;
        File file = new File(inputFile);
        try{ //catch invalid filename errors
            bufferedreader = new BufferedReader(new FileReader(file));
        }
        catch(IOException e){
            throw new IOException();
        }

        Integer.parseInt(bufferedreader.readLine());
        startingnode = Integer.parseInt(bufferedreader.readLine());//read the info from the map file
        destinationnode =Integer.parseInt(bufferedreader.readLine());
        int graphwidth=Integer.parseInt(bufferedreader.readLine());
        int graphlength =Integer.parseInt(bufferedreader.readLine());
        initalmoney =Integer.parseInt(bufferedreader.readLine());
        tollroadnum =Integer.parseInt(bufferedreader.readLine());
        rewardroadnum =Integer.parseInt(bufferedreader.readLine());
        int size=graphlength*graphwidth;
        int horroadtype =2;
        int verroadtype =2;
        int hor =0;
        int ver =0;
        graph =new Graph(size);//create graph of input size

        String horline=bufferedreader.readLine();
        while(horline !=null){
            for (int i = 0; i < horline.length(); i =i+ 2){//increments are by 2, in order to account for the +
                if (horline.charAt(i) == '+') {
                    if (i + 2 < horline.length()){
                        if (horline.charAt(i + 1) !='X'){ //check for type of road
                            if(horline.charAt(i + 1) =='F'){
                                horroadtype = 0;
                            }
                            else if(horline.charAt(i +1)=='C'){
                                horroadtype = -1;
                            }
                            else if(horline.charAt(i +1)=='T'){
                                horroadtype = 1;
                            }
                            graph.insertEdge(graph.getNode(hor), graph.getNode(hor + 1), horroadtype);
                        }
                    }
                    hor++; //go onto next nodes
                }
            }

            String verline =bufferedreader.readLine();
            if (verline== null)
                break;
            else{
                for(int j =0;j< verline.length(); j= j+ 2){
                    if(verline.charAt(j)!='X'){
                        if(verline.charAt(j)=='F'){
                            verroadtype =0;
                        }
                        if(verline.charAt(j)=='C'){
                            verroadtype =-1;
                        }
                        if(verline.charAt(j)=='T'){
                            verroadtype =1;
                        }
                        graph.insertEdge(graph.getNode(ver), graph.getNode(graphwidth+ver), verroadtype);
                        ver++;
                    }
                    else
                        ver++;
                }
                horline = bufferedreader.readLine();
            }
        }
        bufferedreader.close();
    }


    /**
     * Returns the graph representing the road map
     * @return
     */
    public Graph getGraph(){ //getter for graph
        return graph;
    }

    /**
     * Returns the starting node
     * @return
     */
    public int getStartingNode(){ //getter for starting node
        return startingnode;
    }

    /**
     * Returns the destination node.
     * @return
     */

    public int getDestinationNode(){ //getter for destination node
        return destinationnode;
    }

    /**
     *  Returns the initial amount of money available to pay tolls.
     * @return
     */

    public int getInitialMoney(){ //getter for initial money
        return initalmoney;
    }

    /**
     * Returns a Java
     * Iterator containing the nodes of a path from the start node to the destination node as
     * specified above, if such a path exists.
     * @param start the start node
     * @param destination the end node
     * @param initialMoney the budget for the trip
     * @return
     */

    public Iterator findPath(int start, int destination, int initialMoney){ //Returns a Java Iterator containing the
        // nodes of a path from the start node to the destination node as specified above, if such a path exists
        Node startnode = graph.getNode(start);
        startnode.setMark(true);
        pathstack.push(startnode);
        Iterator<Edge> edgeiterator;
        edgeiterator = graph.incidentEdges(startnode);

        if (start == destination){
            return pathstack.iterator();
        }

        while (edgeiterator.hasNext()==true){// Go through iteration until finished
            int totalroadcost = 0;
            Edge edge =edgeiterator.next();
            Node nextnode;
            if (edge.secondEndpoint().getName()==startnode.getName())
                nextnode=edge.firstEndpoint();
            else{
                nextnode =edge.secondEndpoint();
            }
            if (edge.getType() ==1) //check what type of road it is
                totalroadcost = tollroadnum;
            else if (edge.getType() ==-1)
                totalroadcost =-rewardroadnum;

            if (initialMoney- totalroadcost >= 0&&nextnode.getMark()==false){ //see if we've exceeded the budget or if
                // the node has already been visited
                initialMoney =initialMoney - totalroadcost;
                Iterator outcome= findPath(nextnode.getName(), destination, initialMoney);
                if (outcome !=null){
                    totalroadcost =0;
                    return outcome;
                }
                else
                    initialMoney =initialMoney+ totalroadcost;
            }
        }
        startnode.setMark(false);//mission failed we'll get me next time, and so remove form the stack
        pathstack.pop();
        return null;
    }

}

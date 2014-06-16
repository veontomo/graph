/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;
import java.util.*;


/**
 *
 * @author Andrea
 */
public class Graph {
    
    /**
     * A map from node number to array that must contain two
     * elements only: the first one is a list of out-nodes, the 
     * second - is a list of in-nodes for the given node.
     */
    private HashMap<Integer, List<Integer>[]> _edges;
    
    /**
     * A map from node number to finishing time. For nodes that have already been 
     * visited but finishing time has not been calculated so far, then 
     * it is temporarily assigned to be equal to -1. 
     */
    private HashMap<Integer, Integer> _time;
    
    /**
     * A map from node number to list of node numbers for which the key
     * node is a leader.
     */
    private HashMap<Integer, List[]> _leader;
    

    /**
     * Adds directed edge going out from node number tail and entering node head.
     * @param tail
     * @param head 
     */
    public void addEdge(Integer tail, Integer head){
        if (_edges.get(tail) == null){
            Integer[][] currentNodeEdges;
            currentNodeEdges = new Integer[2][2];
            
        } else {
        
        }
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

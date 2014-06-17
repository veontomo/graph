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
     * A map from node number to list of node numbers corresponding to nodes 
     * from which it is possibile to arrive to the given node.
     */
    private HashMap<Integer, Set<Integer>> _edgesIn;
    
    /**
     * A map from node number to list of node numbers corresponding to nodes
     * to which it is possibile to arrive from the given node.
     */
    private HashMap<Integer, Set<Integer>> _edgesOut;

    
    /**
     * A map from node number to finishing time. For nodes that have already been 
     * visited but finishing time has not been calculated so far, then 
     * it is temporarily assigned to be equal to -1. 
     */
    private HashMap<Integer, Integer> _time;
    
    /**
     * A map from node number to leader node number.
     */
    private HashMap<Integer, Integer> _leader;
    
    /**
     * Graph constructor. 
     */
    public Graph(){
        this._edgesIn = new HashMap();
        this._edgesOut = new HashMap();
        this._time = new HashMap();
        this._leader = new HashMap();
    }

    /**
     * Registers edge inside src.
     * If src has no key i, adds it. Then, if the set corresponding to key value i 
     * does not contain value j, then insert j into this set and returns true. 
     * If the corresponding set contains value j, false is returned.
     * @param src  where to register the edge: either _edgesIn or _edgesOut.
     * @param i node number
     * @param j node number
     * @return  boolean
     */
    private boolean _registerEdge(HashMap<Integer, Set<Integer>> src, Integer i, Integer j){
        if (src.containsKey(i)){
            if(src.get(i).contains(j)){
                return false;
            };
            src.get(i).add(j);
        } else {
            Set<Integer> list = new HashSet();
            list.add(j);
            src.put(i, list);
        }
        return true;
    };
    
    /**
     * Adds directed edge going out from node number tail and entering node head.
     * @param tail number of tail node
     * @param head number of head node
     */
    public void addEdge(Integer tail, Integer head){
        this._registerEdge(this._edgesOut, tail, head);
        this._registerEdge(this._edgesIn, head, tail);
    }
    
    /**
     * Returns true if there exist an edge from node tail to node head and false
     * otherwise.
     * @param tail number of tail node
     * @param head number of head node
     * @return 
     */
    public boolean edgeExists(Integer tail, Integer head){
        if (!this._edgesOut.containsKey(tail)){
            return false;
        }
        return this._edgesOut.get(tail).contains(head);
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("start graph");
    }
    
}

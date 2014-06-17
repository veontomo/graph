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
     * Code to mark unvisited nodes (just added)
     */
    private static Integer UNEXPLORED = -2;
    
    /**
     * Code to mark node that has already been visited, but has not been 
     * assigned any finishing time.
     */
    private static Integer EXPLORED = -1;
    
    /**
     * A map from node number to list of node numbers corresponding to nodes 
     * from which it is possible to arrive to the given node.
     */
    private HashMap<Integer, Set<Integer>> _edgesIn;
    
    /**
     * A map from node number to list of node numbers corresponding to nodes
     * to which it is possible to arrive from the given node.
     */
    private HashMap<Integer, Set<Integer>> _edgesOut;

    
    /**
     * A map from node number to finishing time. By default, for newly added 
     * node, the finishing time is set to Graph::UNEXPLORED. Nodes that have 
     * been explored but finishing time has not been assigned yet, are 
     * marked Graph::EXPLORED. 
     */
    private HashMap<Integer, Integer> _time;
    
    /**
     * A map from node number to leader node number.
     */
    private HashMap<Integer, Integer> _leader;
    
    /**
     * Maximal node number. Updated automatically every time edge or node 
     * is inserted
     */
    private Integer _maxNodeNumber = null;
    
    
    /**
     * The number of nodes in the graph
     */
    private int _size = 0;
    
    
    /**
     * _size getter.
     * @return int
     */
    public int getSize(){
        return this._size;
    }
    
    /**
     * Max node number getter
     * @return Integer
     */
    public Integer maxNodeNum(){
        return this._maxNodeNumber;
    }
    
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
             return src.get(i).add(j);
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
        this.addNode(tail);
        this.addNode(head);
    }
    
    /**
     * Adds _time contains no key n, then mapping  n => UNEXPLORED is added into _times and
     * _maxNodeNumber and _size are updated.
     * @param n node number
     */
    public void addNode(Integer n){
        if (!this._time.containsKey(n)){
            this._time.put(n, UNEXPLORED);
            if (this._maxNodeNumber == null || this._maxNodeNumber < n) {
                this._maxNodeNumber = n;
            }
            this._size++;
        }
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
     * Returns set of out-nodes of the given one.
     * @param n
     * @return Set<Integer>
     */
    public Set<Integer> outNodesOf(Integer n){
        return this._edgesOut.get(n);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("start graph");
    }
    
}

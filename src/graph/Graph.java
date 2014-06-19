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
    private HashMap<Integer, Integer> _finTime;
    
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
     * Current time
     */
    private int _time = 0;
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
     * Returns finishing time for node n.
     * @param n  node number
     * @return Integer
     */
    public Integer getFinTimeOf(Integer n){
        if (!this._finTime.containsKey(n)){
            throw new IllegalArgumentException("Node does not exist!");
        }
        return this._finTime.get(n);
    }
    
    /**
     * Graph constructor. 
     */
    public Graph(){
        this._edgesIn = new HashMap();
        this._edgesOut = new HashMap();
        this._finTime = new HashMap();
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
     * Adds _finTime contains no key n, then mapping  n => UNEXPLORED is added into _times and
     * _maxNodeNumber and _size are updated.
     * @param n node number
     */
    public void addNode(Integer n){
        if (!this._finTime.containsKey(n)){
            this._finTime.put(n, UNEXPLORED);
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
     * @return Set
     */
    public Set<Integer> outNodesOf(Integer n){
        Set<Integer> res = this._edgesOut.get(n);
        return (res == null) ? (new HashSet()) : res;

    }
    
        /**
     * Returns set of out-nodes of the given one.
     * @param n
     * @return Set
     */
    public Set<Integer> inNodesOf(Integer n){
        Set<Integer> res = this._edgesIn.get(n);
        return (res == null) ? (new HashSet()) : res;
    }
    
    /**
     * Marks node number n as explored. Returns true if node exists and has not 
     * been explored yet. Otherwise returns false.
     * @param  n 
     * @return boolean
     */
    public boolean mark(int n){
        if (this._finTime.containsKey(n) && this._finTime.get(n) == UNEXPLORED){
            this._finTime.put(n, EXPLORED);
            return true;
        }
        return false;
    }
    
    /** 
     * Returns true if the node exists and has been marked as explored.
     * @param n
     * @return boolean
     */
    public boolean isExplored(int n){
        return (this._finTime.containsKey(n) && this._finTime.get(n) != UNEXPLORED);
    }
    
    /**
     * _finTime getter
     * @return int
     */
    public int getTime(){
        return this._time;
    }
    
    /**
     * Increases time by one unit.
     * @return void
     */
    public void tick(){
        this._time++;
    }
    
    /**
     * Gets number of the next unexplored node which number is not greater than
     * start. If no such node exists, null is returned.
     * @param startNode  node number from which to start the search
     * @return Integer|Null
     */
    public Integer nextUnExplored(Integer startNode){
        if (!this._finTime.containsKey(startNode)){
            throw new IllegalArgumentException("Start node must exist!");
        }
        int status = this._finTime.get(startNode);
        if (status == UNEXPLORED){
            return startNode;
        }
        while (status != UNEXPLORED && startNode >= 0){
            startNode--;
            if (this._finTime.containsKey(startNode)){
                status = this._finTime.get(startNode);
                if (status == UNEXPLORED){
                    return startNode;
                }
            }
        }
        return null;
    }
    
    
    /**
     * Assigns current time to node n.
     * @param n  node number to which assign current time.
     */
    public void assignTimeToNode(Integer n){
        if (!this._finTime.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist"
                    + " and hence can not be assigned finishing time.");
        }
        this._finTime.put(n, this.getTime());
    }
    
    /**
     * Performs deep-first-search from the given node n.
     * @param n 
     */
    public void dfsLoop(Integer n){
        if (!this._finTime.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist! "
                    + "Can not use it to perform dfsLoop.");
        }
//        System.out.println("Start from node " + n);
//        System.out.println("Its fintime: " + this._finTime.get(n));
        this.mark(n);
//        System.out.println("Node " + n + " marked as explored. Its fintime: " + this._finTime.get(n));
        Set<Integer> inNodes = this.inNodesOf(n);
        for(Integer inNode : inNodes){
//            System.out.println("Consider node " + inNode);
            if (!this.isExplored(inNode)){
                this.dfsLoop(inNode);
            }
        }
        this.tick();
//        System.out.println("assigned finishing time " + this.getTime() + " to node " + n);
        this.assignTimeToNode(n);
//        System.out.println("Finishing time of node " + n + " is " + this.getFinTimeOf(n));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("start graph");
    }
    
}

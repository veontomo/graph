/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Andrea
 */
public class Graph {
    
    
    /**
     * A map from node number to list of node numbers corresponding to nodes 
     * from which it is possible to arrive to the given node.
     */
//    private HashMap<Integer, Set<Integer>> _edgesIn;
    
    /**
     * A map from node number to list of node numbers corresponding to nodes
     * that are linked to the given one. 
     * m => [n1, n2, ...]
     * If n is positive, then there is an edge (m, n), if n is negative then 
     * there is an edge (-n, m).
     */
    private HashMap<Integer, Set<Integer>> _edgesOut;
    
    /**
     * A map from node number to finishing time.
     */
    private HashMap<Integer, Integer> _finTime;
    
    /**
     * A map from finishing time to node number. 
     */
    private HashMap<Integer, Integer> _timeToNode;
    
    /**
     * Map from node number into a boolean variable represented whether the 
     * node has been explored or not.
     */
    private HashMap<Integer, Boolean> _nodes;
        
    /**
     * A map from leader node number to list of node numbers.
     */
    private HashMap<Integer, Set<Integer>> _leader;

    /**
     * A map from leader node number to the number of nodes this leader possesses.
     */
    private HashMap<Integer, Integer> _leaderSize;

    
    /**
     * Maximal node number. Updated automatically every time edge or node 
     * is inserted
     */
    private Integer _maxNodeNumber = null;
    
    
    /**
     * Minimal node number. Updated automatically every time edge or node 
     * is inserted
     */
    private Integer _minNodeNumber = null;
    
    
    /**
     * The number of nodes in the graph
     */
    private Integer _size = 0;
    
    /**
     * Current time
     */
    private Integer _time = 0;
   
    
    /** debug variables
     * 
     */
    private Integer _depth = 0;      // recursive call depth
    private Integer _markCalls = 0;  // counter of calls of mark
    
    
 
    
    /**
     * Graph constructor. 
     */
    public Graph(){
//        this._edgesIn = new HashMap();
        this._edgesOut = new HashMap();
        this._finTime = new HashMap();
        this._leader = new HashMap();
        this._timeToNode = new HashMap();
        this._leaderSize = new HashMap();
        this._nodes = new HashMap();
    }
    /**
     * _size getter.
     * @return Integer
     */
    public Integer getSize(){
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
     * Returns true if node n exists (i.e. is present in _nodes hash) 
     * and false otherwise.
     * @param n
     * @return boolean
     */
    public boolean nodeExists(Integer n){
        return this._nodes.containsKey(n);
    }
    
    /**
     * Returns finishing time for node n.
     * @param n  node number
     * @return Integer
     */
    public Integer getFinTimeOfNode(Integer n){
        if (this.nodeExists(n)){
            return this._finTime.get(n);
        }
        throw new IllegalArgumentException("Node does not exist!");
    }
    
    /**
     * Returns node number with given finishing time. If no node with given 
     * finishing time exists, null is returned.
     * @param   t  time
     * @return 
     */
    public Integer getNodeWithFinTime(Integer t){
        return this._timeToNode.containsKey(t) ? this._timeToNode.get(t) : null;
    };

    
    /**
     * Returns list of nodes having as a leader node n.
     * @param n
     * @return Set
     */
    public Set<Integer> leader(Integer n){
        if (this._leader.containsKey(n)){
            return this._leader.get(n);
        }
        return null;
    }
    
    /**
     * Returns a string representation of _leaderSize
     * @return 
     */
    public String getLeaderInfo(){
        return this._leaderSize.toString();
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
        this.addNode(i);
        if (Objects.equals(i, j)){
            System.out.println("Self-loop on node " + i + " is ignored. Node is "
                    + "added to the set of nodes.");
            return true;
        }
        this.addNode(j);
        boolean outcome = true; 
        /// adding edge (i, j)
        if (src.containsKey(i)){
            outcome =  src.get(i).add(j);
        } else {
            Set<Integer> list = new HashSet();
            list.add(j);
            src.put(i, list);
        }
        /// adding edge (j, -i)
        if (src.containsKey(j)) {
            outcome = outcome & src.get(j).add(-i);
        } else {
            Set<Integer> list = new HashSet();
            list.add(-i);
            src.put(j, list);
        }
        return outcome;
    };
    
    /**
     * Adds directed edge going out from node number tail and entering node head.
     * @param tail number of tail node
     * @param head number of head node
     */
    public void addEdge(Integer tail, Integer head){
        if (tail <= 0 || head <= 0){
            throw new IllegalArgumentException("Both node numbers must be positive!");
        };
        this._registerEdge(this._edgesOut, tail, head);
//        this._registerEdge(this._edgesIn, head, tail);
    }
    
    /**
     * Adds node n to the list of all nodes if it is not present there.
     * Updates values of _maxNodeNumber, _minNodeNumber and _size.
     * @param n node number
     */
    public void addNode(Integer n){
        if (!this.nodeExists(n)) {
            this._nodes.put(n, false);
            if (this._maxNodeNumber == null || this._maxNodeNumber < n) {
                this._maxNodeNumber = n;
            }
            if (this._minNodeNumber == null || this._minNodeNumber > n) {
                this._minNodeNumber = n;
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
        Set<Integer> res = new HashSet(),
                     edges = this._edgesOut.get(n);
        if (!edges.isEmpty()) {
            for (Integer i : edges){
                if (i > 0){
                    res.add(i);
                }
            }
        }
        return res;

    }
    
    /**
     * Returns set of out-nodes of the given one.
     * @param n
     * @return Set
     */
    public Set<Integer> inNodesOf(Integer n){
        Set<Integer> res = new HashSet(),
                edges = this._edgesOut.get(n);
        if (!edges.isEmpty()){
            for (Integer i : edges) {
                if (i < 0) {
                    res.add(-i);
                }
            }
        }
        return res;    
    }
    
    /**
     * Marks node number n as explored. Throws an exception if node
     * does not exist or if it has already been marked as explored.
     * @param  n 
     */
    public void mark(Integer n){
        if (!this._nodes.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist "
                    + "and can not be marked as explored.");
        }
        if (this._nodes.get(n)){
            throw new IllegalArgumentException("Node " + n + " has already been"
                    + " explored and can not be re-marked!");
        }
        this._nodes.put(n, true);
    }
    
    /** 
     * Returns true if the node exists and has been marked as explored.
     * @param n
     * @return boolean
     */
    public boolean isExplored(Integer n){
        if (!this._nodes.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist "
                    + "and can not be asked if it is explored.");
        }
        return this._nodes.get(n);
    }
    
    /**
     * Sets boolean part of _nodes to false: every node acquires status "unexplored". 
     */
    public void flushVisits(){
        for (Integer n : this._nodes.keySet()){
            this._nodes.put(n, false);
        }
    }
    /**
     * _time getter
     * @return Integer
     */
    public Integer getTime(){
        return this._time;
    }
    
    /**
     * Increases time by one unit.
     */
    public void tick(){
        this._time++;
    }
    
    /**
     * Gets number of the next unexplored node which number is not greater than
     * startNode. If no such node exists, null is returned.
     * @param startNode  node number from which to start the search
     * @return Integer|Null
     */
    public Integer nextUnExplored(Integer startNode){
        if (!this._nodes.containsKey(startNode)){
            throw new IllegalArgumentException("Start node " +startNode+" must "
                    + " exist in order to find next unexplored node!");
        }
        Integer currentNode = startNode;
        boolean status;
        Integer minNodeNum = this._minNodeNumber;
        while (currentNode >= minNodeNum){
            status = this._nodes.get(currentNode);
            if (!status) {
                return currentNode;
            }
            currentNode--;
            while (currentNode >= minNodeNum && !this._nodes.containsKey(currentNode) ){
                currentNode--;
            }
            if (currentNode < minNodeNum){
                return null;
            }
        }
        return null;
    }
    
    
    /**
     * Assigns current time to node n.
     * @param n  node number to which assign current time.
     */
    public void setFinTimeToNode(Integer n){
        if (!this._nodes.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist"
                    + " and hence can not be assigned finishing time.");
        }
        Integer t = this.getTime();
        this._finTime.put(n, t);
        this._timeToNode.put(t, n);
    }
    
    /**
     * Performs deep-first-search from the given node n.
     * @param n 
     */
    public void dfsLoop(Integer n){
        if (!this._nodes.containsKey(n)){
            throw new IllegalArgumentException("Node " + n + " does not exist! "
                    + "Can not use it to perform dfsLoop.");
        }
//        this._depth++;
//        System.out.println("dfsLoop depth: " + this._depth);
//        System.out.println("Start from node " + n);
//        System.out.println("Its fintime: " + this._finTime.get(n));
        this.mark(n);
//        System.out.println("Node " + n + " marked as explored. Its fintime: " + this._finTime.get(n));
        Set<Integer> inNodes = this.inNodesOf(n);
//        System.out.println("Node " + n + " has " + inNodes.size() + "in-edges...");
        if (!inNodes.isEmpty()){
            for (Integer inNode : inNodes) {
                if (!this.isExplored(inNode)) {
                    this.dfsLoop(inNode);
                }
            }
        }
        this.tick();
//        System.out.println("assigned finishing time " + this.getTime() + " to node " + n);
        this.setFinTimeToNode(n);
//        System.out.println("Finishing time of node " + n + " is " + this.getFinTimeOfNode(n));
//        this._depth--;
    }
    
    
    /**
     * Performs deep-first-search and assigns leader from the given node n.
     * @param n
     * @param leader
     */
    public void dfsLoop2(Integer n, Integer leader) {
//        System.out.println("dfsLoop2 starts with n=" + n + ", leader = " + leader);
        this.mark(n);
        this.addToLeaderGroup(leader, n);
        Set<Integer> outNodes = this.outNodesOf(n);
        for (Integer outNode : outNodes) {
            if (!this.isExplored(outNode)) {
                this.dfsLoop2(outNode, leader);
            }
        }
    }
    
    /**
     * Adds node n to the group of leader.
     * @param n
     * @param leader 
     */
    public void addToLeaderGroup(Integer leader, Integer n){
//        System.out.println("adding node " + n + " to leader " + leader);
        if (this._leader.containsKey(leader)){
//            System.out.println("leader group is not empty, adding " + n + " to the group.");
            this._leader.get(leader).add(n);
            Integer val = this._leaderSize.get(leader);
            val++;
            this._leaderSize.put(leader, val);
        } else {
//            System.out.println("leader has no group, creating group and adding " + n + " there.");
            Set<Integer> list = new HashSet();
            list.add(n);
            this._leader.put(leader, list);
            this._leaderSize.put(leader, 1);
        }
    }

    
    /**
     * 
     * @return 
     */
    public String show(){
        return "out edges: " + this._edgesOut.toString() + 
//               "\nin edges: " + this._edgesIn.toString() + 
                "\nnode to time: " + this._finTime.toString() + 
                "\ntime to node: " + this._timeToNode.toString() +
                "\nleader: " + this._leader.toString();
    };
    
    /**
     * Assigns finishing time to each node
     */
    public void dfsOrder(){
        Integer n = this.nextUnExplored(this.maxNodeNum());
         while(n != null){
            this.dfsLoop(n);
            n = this.nextUnExplored(n);
        }
    }
    
    /**
     * Parses all nodes
     */
    public void fragmentize(){
//        System.out.println("fragmentizing:");
        Integer t = this.getTime();
//        System.out.println("time: " + t);
        Integer n;
        while (t > 0) {
//            System.out.println("time: " + t);
            n = this.getNodeWithFinTime(t);
//            System.out.println("new starting node: " + n);
            while(!this.isExplored(n)){
//                System.out.println("the node has not been explored yet, starting dfsLoop2...");
                this.dfsLoop2(n, n);
                System.out.println("cluster " + n + " size: " + this._leader.get(n).size() );
            }
            t--;
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("loading graph");
        BufferedReader br = null;
        Graph g = new Graph();
        String fileName = "SCC_med.txt";
        try {
            Integer counter = 0;
            Integer tail, head;
            String sCurrentLine;
            String[] data;
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null) {
                counter++;
                data = sCurrentLine.trim().split(" ");
                if (data.length != 2){
                    throw new IllegalArgumentException("Line must contain exactly two numbers!");
                }
                tail = Integer.parseInt(data[0]);
                head = Integer.parseInt(data[1]);
                g.addEdge(tail, head);
//                if (counter % 10000 == 0){
//                    System.out.println("" + counter + ": " + g.maxNodeNum() );
//                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Graph is loaded with " + g.getSize() + " nodes.");
        System.out.println("Ordering graph");
        g.dfsOrder();
        System.out.println("Graph is ordered");
        g.flushVisits();
        System.out.println("Fragmentizing graph");
        g.fragmentize();
        System.out.println("Fragmetizing is over");
//        System.out.println(g.show());
        System.out.println(g.getLeaderInfo());
//        System.out.println(g.show());

    
    }
    
}

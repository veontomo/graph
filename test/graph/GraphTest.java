/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Andrea
 */
public class GraphTest {
    
    private Graph g1, g2, g3;
    
    public GraphTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    //  → ← ∟ ↔  ↓ ↑  ∧ ∨ ↖ ↗ ↘ ↙ 
    //                                 _ 
    // 1 --->--- 2 --->--- 4 --->--- 5/  \
    //  ↖       /           ↖       / \__/
    //    \    /              \    /
    //     \ ↙                 \ ↙
    //      3                   6
    // 
        g1 = new Graph();
        g1.addEdge(1, 2);
        g1.addEdge(3, 1);
        g1.addEdge(2, 3);
        g1.addEdge(2, 4);
        g1.addEdge(6, 4);
        g1.addEdge(4, 5);
        g1.addEdge(5, 6);
        g1.addEdge(5, 5);
        
    //    1   2
    //    
    //    3   4
    //
        g2 = new Graph();
        g2.addNode(1);
        g2.addNode(2);
        g2.addNode(3);
        g2.addNode(4);
      
//      1 --->--- 2 ---<--- 6 --->--- 7 
//      |         |         |
//      |         ^         | 
//      v         |         v
//      3 ---<--- 4 ---<--- 5 ---<--- 8     
        g3 = new Graph();
        g3.addEdge(1, 3);
        g3.addEdge(1, 2);
        g3.addEdge(4, 3);
        g3.addEdge(4, 2);
        g3.addEdge(5, 4);
        g3.addEdge(6, 2);
        g3.addEdge(6, 5);
        g3.addEdge(6, 7);
        g3.addEdge(8, 5);
    }

    @After
    public void tearDown() throws Exception {
        g1 = null;
    }

    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExists() {
        System.out.println("Test whether just added edge exists");
        Graph g = new Graph();
        g.addEdge(5, 7);
        boolean result = g.edgeExists(5, 7);
        assertTrue(result);
    }

    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExists2() {
        System.out.println("Test whether missing edge exists");
        Graph g = new Graph();
        g.addEdge(1, 2);
        boolean result = g.edgeExists(5, 7);
        assertFalse(result);
    }

    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExistsIfInverse() {
        System.out.println("Must return false if the the requested edge does not exists, but the opposite does exist.");
        Graph g = new Graph();
        g.addEdge(3, 2);
        boolean result = g.edgeExists(2, 3);
        assertFalse(result);
    }

    /**
     * Test of edgeExists method, of class Graph.
     */
//    @Test
//    public void testEdgeExistsIfSelfLoopExists() {
//        System.out.println("Must return true if asked about a self-loop that exists.");
//        Graph g = new Graph();
//        g.addEdge(10, 10);
//        boolean result = g.edgeExists(10, 10);
//        assertTrue(result);
//    }

    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExistsIfSelfLoopNotExists() {
        System.out.println("Must return false if asked about a self-loop that does not exist.");
        Graph g = new Graph();
        g.addEdge(3, 3);
        boolean result = g.edgeExists(1, 1);
        assertFalse(result);
    }

    
    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExistsOfEmptyGraph() {
        System.out.println("Test whether an edgge exists in empty graph");
        Graph g = new Graph();
        boolean result = g.edgeExists(1, 3);
        assertFalse(result);
    }
    
    @Test
    public void testEmptyGraphLength() {
        System.out.println("Returns zero for empty graph");
        Graph g = new Graph();
        assertTrue(0 == g.getSize());
    }
    
    @Test
    public void testOneElementGraphLength() {
        System.out.println("Returns 1 for single node graph");
        Graph g = new Graph();
        g.addNode(5);
        assertTrue(1 == g.getSize());
    }
    
    @Test
    public void testLengthOfSelfLoopGraph() {
        System.out.println("Returns 1 for single node graph with self-loop");
        Graph g = new Graph();
        g.addEdge(1,1);
        assertTrue(1 == g.getSize());
    }


    @Test
    public void testLengthOfGraphWIth5Nodes() {
        System.out.println("Returns 5 for graph with 5 nodes added node by node");
        Graph g = new Graph();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(6);
        g.addNode(9);
        assertTrue(5 == g.getSize());
    }

    @Test
    public void testLengthOfGraphWith2NodesWithRepetitions() {
        System.out.println("Returns 2 for graph with 2 nodes when they are added multiply");
        Graph g = new Graph();
        g.addNode(1);
        g.addNode(2);
        g.addNode(2);
        g.addNode(2);
        g.addNode(1);
        assertTrue(2 == g.getSize());
    }

    @Test
    public void testLengthOfGraphWith6Nodes() {
        System.out.println("Returns 6 for graph with 6 nodes added by means of edges");
        Graph g = new Graph();
        g.addEdge(1, 7);
        g.addEdge(5, 7);
        g.addEdge(2, 5);
        g.addEdge(4, 3);
        assertTrue(6 == g.getSize());
    }
    
    @Test
    public void testMaxNodeNumEmptyGraph() {
        System.out.println("Returns null for empty graph");
        Graph g = new Graph();
        assertNull(g.maxNodeNum());
    }

    @Test
    public void testMaxNodeNumSingleNode() {
        System.out.println("Returns the only node number");
        Graph g = new Graph();
        g.addNode(23);
        assertTrue(g.maxNodeNum() == 23);
    }

    @Test
    public void testMaxNodeNum3NodeGraph() {
        System.out.println("Returns maximum node number if the nodes are added node by node");
        Graph g = new Graph();
        g.addNode(3);
        g.addNode(5);
        g.addNode(4);
        assertTrue(g.maxNodeNum() == 5);
    }

    @Test
    public void testMaxNodeNum4NodeGraph() {
        System.out.println("Returns maximum node number if the nodes are added by edges");
        Graph g = new Graph();
        g.addEdge(3, 19);
        g.addEdge(5, 21);
        g.addEdge(4, 6);
        assertTrue(g.maxNodeNum() == 21);
    }

    @Test
    public void testGetOutNodesEmpty() {
        System.out.println("Returns empty set if node has no out-edges");
        g2.addEdge(3, 7);
        g2.addEdge(2, 7);
        g2.addEdge(4, 7);
        Set<Integer> list = g2.outNodesOf(7);
        assertEquals(0, list.size());
    }

    
    @Test
    public void testGetOutNodes() {
        System.out.println("Returns set of out-nodes");
        System.out.println(g3.show());
        Set<Integer> list = g3.outNodesOf(4);
        assertEquals(2, list.size());
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }
    
    @Test
    public void testGetInNodesEmpty() {
        System.out.println("Returns empty set if node has no in-edges");
        g2.addEdge(7, 3);
        g2.addEdge(7, 2);
        g2.addEdge(7, 4);
        Set<Integer> list = g2.inNodesOf(7);
        assertEquals(0, list.size());
    }
    
// 1 --->--- 2 ---<--- 6 --->--- 7 
// |         |         |
// |         ^         | 
// v         |         v
// 3 ---<--- 4 ---<--- 5 ---<--- 8   
    
    
    @Test
    public void testGetInNodes(){
        System.out.println("Returns set of in-nodes");
        Set<Integer> list = g3.inNodesOf(5);
        assertEquals(2, list.size());
        assertTrue(list.contains(6));
        assertTrue(list.contains(8));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testMarkNonExistentNode() {
        System.out.println("Throws exception if one marks as explored a "
                + "node that does not exist.");
        Graph g = new Graph();
        g.addNode(9);
        g.addNode(4);
        g.mark(1);
    }

    @Test
    public void testMarkUnexplored() {
        System.out.println("Returns true when marking an unexplored node ");
        Graph g = new Graph();
        g.addNode(9);
        g.addNode(4);
        g.mark(4);
        assertTrue(g.isExplored(4));
    }
    
    
//    @Test
//    public void testMarkExplored() {
//        System.out.println("Returns false if one tries to mark as explored a "
//                + "node that has already been explored.");
//        Graph g = new Graph();
//        g.addNode(4);
//        g.addNode(5);
//        g.mark(5);
//        assertFalse(g.mark(5));
//    }

    
    @Test(expected = IllegalArgumentException.class)
    public void testIsExploredIfNotExists() {
        System.out.println("Throws exception if one asks whether a "
                + "non-existent node has been explored.");
        Graph g = new Graph();
        g.addNode(4);
        g.addNode(5);
        assertFalse(g.isExplored(6));
    }
    
    @Test
    public void testIsExploredIfNotExplored() {
        System.out.println("Returns false if one tries to find out whether a"
                + " non-explored node has been explored.");
        Graph g = new Graph();
        g.addNode(4);
        g.addNode(5);
        assertFalse(g.isExplored(5));
    }

    @Test
    public void testIsExploredIfExplored() {
        System.out.println("Returns true if one tries to find out whether an"
                + " explored node has been explored.");
        Graph g = new Graph();
        g.addNode(3);
        g.addNode(2);
        g.mark(3);
        assertTrue(g.isExplored(3));
    }
    
    @Test
    public void testIsExploredIfFinTimeIsSet() {
        System.out.println("Returns true if node is assigned a finishing time.");
        Graph g = new Graph();
        g.addNode(3);
        g.addNode(2);
        g.tick();
        g.setFinTimeToNode(3);
        assertTrue(g.getFinTimeOfNode(3) == 1);
    }

    @Test
    public void testTimeInitial() {
        System.out.println("Returns zero as starting time.");
        Graph g = new Graph();
        assertTrue(0 == g.getTime());
    }

    
    @Test
    public void testTimeAfterSingleTick() {
        System.out.println("Returns 1 after first tick.");
        Graph g = new Graph();
        g.tick();
        assertTrue(1 == g.getTime());
    }

    
    @Test
    public void testTimeAfterThreeTicks() {
        System.out.println("Returns 3 after three ticks.");
        Graph g = new Graph();
        g.tick();
        g.tick();
        g.tick();
        assertTrue(3 == g.getTime());
    }


    @Test
    public void testNextUnExploredNodeIfNone() {
        System.out.println("Returns null if there is no unexplored node.");
        Graph g = new Graph();
        g.addNode(3); 
        g.mark(3);
        g.addNode(2); 
        g.mark(2);
        g.addNode(6); 
        g.mark(6);
        g.addNode(7); 
        g.mark(7);
        g.addNode(8); 
        g.mark(8);
        assertNull(g.nextUnExplored(8));
    }


    @Test
    public void testNextUnExploredReturnsItself() {
        System.out.println("Returns the input node number if it is unexplored.");
        Graph g = new Graph();
        g.addNode(3);
        g.addNode(2);
        g.addNode(6);
        g.addNode(7);
        g.addNode(8);
        g.mark(3);
        g.mark(8);
        assertTrue(7 == g.nextUnExplored(7));
    }
    
    @Test
    public void testNextUnExploredReturnsPreviuosNode(){
        System.out.println("Returns the input node number if it is unexplored.");
        Graph g = new Graph();
        g.addNode(3);
        g.addNode(2);
        g.addNode(6);
        g.addNode(7);
        g.addNode(8);
        g.mark(3);
        g.mark(7);
        assertTrue(6 == g.nextUnExplored(7));
    }
    
    @Test
    public void testNextUnExploredReturnsFirstNode() {
        System.out.println("Returns the input node number if it is unexplored.");
        Graph g = new Graph();
        g.addNode(2);
        g.addNode(3);
        g.addNode(6);
        g.addNode(7);
        g.addNode(8);
        g.mark(8);
        g.mark(7);
        g.mark(6);
        g.mark(3);
        assertTrue(2 == g.nextUnExplored(7));
    }

//    1 -->-- 2       5
//    |     / |     / |
//    |    ↗  |   ↙   |
//    ∨  /    ∨  /    ↓
//    | /     | /     |
//    3 -->-- 4       6
// → ← ∟ ↔ ▲ ↓ ↑ ↨▬§ ∧ ∨↖↗↘↙
    @Test
    public void testDfsLoopFromLast(){
        System.out.println("Assigns times to two nodes.");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(2, 4);
        g.dfsLoop(6);
        assertFalse(g.isExplored(1));
        assertFalse(g.isExplored(2));
        assertFalse(g.isExplored(3));
        assertFalse(g.isExplored(4));
        assertTrue(g.isExplored(5));
        assertTrue(g.isExplored(6));
        assertTrue(2 == g.getFinTimeOfNode(6));
        assertTrue(1 == g.getFinTimeOfNode(5));
    }
    
    @Test
    public void testDfsLoopFromNoWayNode() {
        System.out.println("Assigns times to single available node.");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(2, 4);
        g.dfsLoop(5);
        assertFalse(g.isExplored(1));
        assertFalse(g.isExplored(2));
        assertFalse(g.isExplored(3));
        assertFalse(g.isExplored(4));
        assertTrue(g.isExplored(5));
        assertFalse(g.isExplored(6));
        assertTrue(1 == g.getFinTimeOfNode(5));
    }
    
    @Test
    public void testDfsLoopFromTwoWayNode() {
        System.out.println("Assigns times to three nodes.");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(2, 4);
        g.dfsLoop(2);
        assertTrue(g.isExplored(1));
        assertTrue(g.isExplored(2));
        assertTrue(g.isExplored(3));
        assertFalse(g.isExplored(4));
        assertFalse(g.isExplored(5));
        assertFalse(g.isExplored(6));
        int t1 = g.getFinTimeOfNode(1);
        int t2 = g.getFinTimeOfNode(2);
        int t3 = g.getFinTimeOfNode(3);
        assertTrue(t2 == 3);
        assertTrue((t1 == 1 && t3 == 2) || (t1 == 2 && t3 == 1));
    }

    @Test
    public void testDfsOrder() {
        System.out.println("Ordering graph.");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(2, 4);
        g.dfsOrder();
        System.out.println(g.show());
        assertTrue(g.isExplored(1));
        assertTrue(g.isExplored(2));
        assertTrue(g.isExplored(3));
        assertTrue(g.isExplored(4));
        assertTrue(g.isExplored(5));
        assertTrue(g.isExplored(6));
        assertTrue(g.getFinTimeOfNode(1) == 3);
        assertTrue(g.getFinTimeOfNode(2) == 5);
        assertTrue(g.getFinTimeOfNode(3) == 4);
        assertTrue(g.getFinTimeOfNode(4) == 6);
        assertTrue(g.getFinTimeOfNode(5) == 1);
        assertTrue(g.getFinTimeOfNode(6) == 2);
        assertTrue(g.getNodeWithFinTime(1) == 5);
        assertTrue(g.getNodeWithFinTime(2) == 6);
        assertTrue(g.getNodeWithFinTime(3) == 1);
        assertTrue(g.getNodeWithFinTime(4) == 3);
        assertTrue(g.getNodeWithFinTime(5) == 2);
        assertTrue(g.getNodeWithFinTime(6) == 4);
    }

  //  → ← ∟ ↔  ↓ ↑  ∧ ∨ ↖ ↗ ↘ ↙ 
    @Test
    public void testDfsOrderWithSelfLoop() {
        g1.dfsOrder();
        System.out.println(g1.show());
        assertTrue(g1.isExplored(1));
        assertTrue(g1.isExplored(2));
        assertTrue(g1.isExplored(3));
        assertTrue(g1.isExplored(4));
        assertTrue(g1.isExplored(5));
        assertTrue(g1.isExplored(6));
        assertTrue(g1.getFinTimeOfNode(1) == 2);
        assertTrue(g1.getFinTimeOfNode(2) == 3);
        assertTrue(g1.getFinTimeOfNode(3) == 1);
        assertTrue(g1.getFinTimeOfNode(4) == 4);
        assertTrue(g1.getFinTimeOfNode(5) == 5);
        assertTrue(g1.getFinTimeOfNode(6) == 6);
        assertTrue(g1.getNodeWithFinTime(1) == 3);
        assertTrue(g1.getNodeWithFinTime(2) == 1);
        assertTrue(g1.getNodeWithFinTime(3) == 2);
        assertTrue(g1.getNodeWithFinTime(4) == 4);
        assertTrue(g1.getNodeWithFinTime(5) == 5);
        assertTrue(g1.getNodeWithFinTime(6) == 6);
    }

    
    @Test
    public void testFlushVisits(){
        System.out.println("Setting every node status to be unexplored.");
        g2.mark(1);
        g2.mark(2);
        g2.mark(3);
        g2.mark(4);
        assertTrue(g2.isExplored(1));
        assertTrue(g2.isExplored(2));
        assertTrue(g2.isExplored(3));
        assertTrue(g2.isExplored(4));
        g2.flushVisits();
        assertFalse(g2.isExplored(1));
        assertFalse(g2.isExplored(2));
        assertFalse(g2.isExplored(3));
        assertFalse(g2.isExplored(4));
    }
    
    @Test
    public void testAssignLeader(){
        System.out.println("Assigning leaders");
        g1.dfsOrder();
        g1.flushVisits();
        g1.fragmentize();
        System.out.println(g1.show());
        System.out.println(g1.getLeaderInfo());
        Set<Integer> leader6 = g1.leader(6);
        Set<Integer> leader2 = g1.leader(2);
        assertTrue(leader2.size() == 3);
        assertTrue(leader6.size() == 3);
    }

    
    @Test
    public void testAssignLeader2() {
        System.out.println("Assigning leaders to bigger graph");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(5, 1);
        g.addEdge(5, 2);
        g.addEdge(4, 5);
        g.addEdge(3, 4);
        g.addEdge(2, 3);
        g.addEdge(6, 2);
        g.addEdge(3, 6);
        g.addEdge(3, 9);
        g.addEdge(7, 6);
        g.addEdge(7, 12);
        g.addEdge(9, 7);
        g.addEdge(10, 7);
        g.addEdge(10, 8);
        g.addEdge(8, 9);
        g.addEdge(11, 7);
        g.addEdge(11, 12);
        
        g.dfsOrder();
        g.flushVisits();
        g.fragmentize();
        System.out.println(g.show());
        System.out.println(g.getLeaderInfo());
//        Set<Integer> leader6 = g1.leader(6);
//        Set<Integer> leader2 = g1.leader(2);
//        assertTrue(leader2.size() == 3);
//        assertTrue(leader6.size() == 3);
    }
    
    @Test
    public void testReverseOddArray(){
        System.out.println("Reversing odd-length array");
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        int[] arr2 = g2.reverseArray(arr);
        assertEquals(arr2.length, 5);
        assertEquals(arr2[0], 5);
        assertEquals(arr2[1], 4);
        assertEquals(arr2[2], 3);
        assertEquals(arr2[3], 2);
        assertEquals(arr2[4], 1);
    }
    
    @Test
    public void testReverseEvenArray() {
        int[] arr = new int[4];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        int[] arr2 = g2.reverseArray(arr);
        assertEquals(arr2.length, 4);
        assertEquals(arr2[0], 4);
        assertEquals(arr2[1], 3);
        assertEquals(arr2[2], 2);
        assertEquals(arr2[3], 1);
    }
    
    /**
     * Test of main method, of class Graph.
     */
    @Test
    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Graph.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}


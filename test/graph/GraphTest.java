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
    }

    @After
    public void tearDown() throws Exception {
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
        assertEquals(false, result);
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
    @Test
    public void testEdgeExistsIfSelfLoopExists() {
        System.out.println("Must return true if asked about a self-loop that exists.");
        Graph g = new Graph();
        g.addEdge(10, 10);
        boolean result = g.edgeExists(10, 10);
        assertTrue(result);
    }

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
        assertEquals(0, g.getSize());
    }
    
    @Test
    public void testOneElementGraphLength() {
        System.out.println("Returns 1 for single node graph");
        Graph g = new Graph();
        g.addNode(5);
        assertEquals(1, g.getSize());
    }
    
    @Test
    public void testLengthOfSelfLoopGraph() {
        System.out.println("Returns 1 for single node graph with self-loop");
        Graph g = new Graph();
        g.addEdge(1,1);
        assertEquals(1, g.getSize());
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
        assertEquals(5, g.getSize());
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
        assertEquals(2, g.getSize());
    }

    @Test
    public void testLengthOfGraphWith6Nodes() {
        System.out.println("Returns 6 for graph with 6 nodes added by means of edges");
        Graph g = new Graph();
        g.addEdge(1, 7);
        g.addEdge(5, 7);
        g.addEdge(2, 5);
        g.addEdge(4, 3);
        assertEquals(6, g.getSize());
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
        System.out.println("Returns empty set if there are no out-nodes");
        Graph g = new Graph();
        g.addEdge(9, 2);
        g.addEdge(9, 4);
        g.addEdge(1, 9);
        Set<Integer> list = g.outNodesOf(2);
        assertEquals(0, list.size());
    }

    
    @Test
    public void testGetOutNodes() {
        System.out.println("Returns set of out-nodes");
        Graph g = new Graph();
        g.addEdge(9, 2);
        g.addEdge(9, 4);
        g.addEdge(1, 9);
        g.addEdge(3, 9);
        Set<Integer> list = g.outNodesOf(9);
        assertEquals(2, list.size());
        assertTrue(list.contains(2));
        assertTrue(list.contains(4));
    }
    
    @Test
    public void testGetInNodesEmpty() {
        System.out.println("Returns empty set if there are no in-nodes");
        Graph g = new Graph();
        g.addEdge(9, 2);
        g.addEdge(1, 9);
        Set<Integer> list = g.inNodesOf(1);
        assertEquals(0, list.size());
    }
    
    @Test
    public void testGetInNodes(){
        System.out.println("Returns set of out-nodes");
        Graph g = new Graph();
        g.addEdge(9, 2);
        g.addEdge(9, 4);
        g.addEdge(1, 9);
        Set<Integer> list = g.inNodesOf(9);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }
    
    
    @Test
    public void testMarkNonExistentNode() {
        System.out.println("Returns false if one tries to mark as explored a "
                + "node that does not exist.");
        Graph g = new Graph();
        g.addNode(9);
        g.addNode(4);
        assertFalse(g.mark(1));
    }

    @Test
    public void testMarkUnexplored() {
        System.out.println("Returns true when marking an unexplored node ");
        Graph g = new Graph();
        g.addNode(9);
        g.addNode(4);
        assertTrue(g.mark(4));
    }
    
    
    @Test
    public void testMarkExplored() {
        System.out.println("Returns false if one tries to mark as explored a "
                + "node that has already been explored.");
        Graph g = new Graph();
        g.addNode(4);
        g.addNode(5);
        g.mark(5);
        assertFalse(g.mark(5));
    }

    
    @Test
    public void testIsExploredIfNotExists() {
        System.out.println("Returns false if one tries to find out whether an "
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
    public void testTimeInitial() {
        System.out.println("Returns zero as starting time.");
        Graph g = new Graph();
        assertEquals(0, g.getTime());
    }

    
    @Test
    public void testTimeAfterSingleTick() {
        System.out.println("Returns 1 after first tick.");
        Graph g = new Graph();
        g.tick();
        assertEquals(1, g.getTime());
    }

    
    @Test
    public void testTimeAfterThreeTicks() {
        System.out.println("Returns 3 after three ticks.");
        Graph g = new Graph();
        g.tick();
        g.tick();
        g.tick();
        assertEquals(3, g.getTime());
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
        System.out.println(g.nextUnExplored(7));
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
        g.dfsLoop(6);
        assertFalse(g.isExplored(1));
        assertFalse(g.isExplored(2));
        assertFalse(g.isExplored(3));
        assertFalse(g.isExplored(4));
        assertTrue(g.isExplored(5));
        assertTrue(g.isExplored(6));
        assertTrue(2 == g.getFinTimeOf(6));
        assertTrue(1 == g.getFinTimeOf(5));
    }
    
    @Test
    public void testDfsLoopFromNoWayNode() {
        System.out.println("Assigns times to two nodes.");
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.dfsLoop(5);
        assertFalse(g.isExplored(1));
        assertFalse(g.isExplored(2));
        assertFalse(g.isExplored(3));
        assertFalse(g.isExplored(4));
        assertTrue(g.isExplored(5));
        assertFalse(g.isExplored(6));
        assertTrue(1 == g.getFinTimeOf(5));
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

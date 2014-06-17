/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

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
        assertEquals(true, result);
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
        assertEquals(false, result);
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
        assertEquals(true, result);
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
        assertEquals(false, result);
    }

    
    /**
     * Test of edgeExists method, of class Graph.
     */
    @Test
    public void testEdgeExistsOfEmptyGraph() {
        System.out.println("Test whether an edgge exists in empty graph");
        Graph g = new Graph();
//        boolean result = g.edgeExists(1, 3);
//        assertEquals(false, result);
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

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        Integer tail = null;
        Integer head = null;
        Graph instance = new Graph();
        instance.addEdge(tail, head);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

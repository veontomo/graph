/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TopMax;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea
 */
public class TopMaxTest {
    
    public TopMaxTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of TopMax method, of class TopMax.
     */
    @Test
    public void testTopMax() {
        System.out.println("TopMax");
        int n = 0;
        TopMax instance = new TopMax();
        instance.TopMax(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pos method, of class TopMax.
     */
    @Test
    public void testPos() {
        System.out.println("pos");
        Integer n = null;
        TopMax instance = new TopMax();
        int expResult = 0;
        int result = instance.pos(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

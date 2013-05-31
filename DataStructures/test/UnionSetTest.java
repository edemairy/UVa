/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edemairy
 */
public class UnionSetTest {

    @Test
    public void example1() {
        UnionSet u = new UnionSet(5);
        assertEquals(0, u.pset[0]);
        assertEquals(1, u.pset[1]);
        assertEquals(2, u.pset[2]);        
        assertEquals(3, u.pset[3]);
        assertEquals(4, u.pset[4]);
        
        u.unionSet(0, 1);
        assertEquals(1, u.pset[0]);
        assertEquals(1, u.pset[1]);
        assertEquals(2, u.pset[2]);        
        assertEquals(3, u.pset[3]);
        assertEquals(4, u.pset[4]);

        u.unionSet(1, 2);
        assertEquals(1, u.pset[0]);
        assertEquals(2, u.pset[1]);
        assertEquals(2, u.pset[2]);        
        assertEquals(3, u.pset[3]);
        assertEquals(4, u.pset[4]);

        u.unionSet(3, 1);
        assertEquals(1, u.pset[0]);
        assertEquals(2, u.pset[1]);
        assertEquals(2, u.pset[2]);        
        assertEquals(2, u.pset[3]);
        assertEquals(4, u.pset[4]);

        u.findSet(0);
        assertEquals(2, u.pset[0]);
        assertEquals(2, u.pset[1]);
        assertEquals(2, u.pset[2]);        
        assertEquals(2, u.pset[3]);
        assertEquals(4, u.pset[4]);

        assertEquals(false, u.isSameSet(0, 4));
    }
}
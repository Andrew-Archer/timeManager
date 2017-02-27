/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.time.LocalDateTime;
import java.util.List;
import timemanager.actors.Worker;
import org.junit.Test;
import static org.junit.Assert.*;
import static timemanager.TypeOfWork.*;
import timemanager.actors.Manager;
import timemanager.actors.Person;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/**
 *
 * @author razan
 */
public class TimeManagerTest {
    
    public TimeManagerTest() {
    }

    /**
     * Test of getActualWorkGraph method, of class TimeManager.
     */
    @Test
    public void testGetActualWorkGraph() {
    }

    /**
     * Test of setActualWorkGraph method, of class TimeManager.
     */
    @Test
    public void testSetActualWorkGraph() {
    }

    /**
     * Test of generateFairGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGenerateFairGraphOfWork() throws Exception {
    }

    /**
     * Test of splitTimeCells method, of class TimeManager.
     */
    @Test
    public void testSplitTimeCells() throws Exception {
    }
    
    

    /**
     * Test of getFairGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGetFairGraphOfWork() throws
                                            EndBeforeStartException,
                                            ZeroLengthException {
            TimeManager tm = new TimeManager();
        
        tm.getActualWorkGraph().add(new TimeCell(
            LocalDateTime.parse("2007-12-03T10:15:00"),
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.now(),
            new Manager("Robert"),
            new Worker("Jack", GROUP),
            TypeOfWork.ANY));
        
        tm.getActualWorkGraph().add(new TimeCell(
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.now(),
            new Manager("Robert"),
            new Worker("Jane", GROUP),
            TypeOfWork.ANY));
        
        tm.getActualWorkGraph().add(new TimeCell(
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.parse("2007-12-03T10:18:00"),
            LocalDateTime.now(),
            new Manager("Robert"),
            new Worker("John", GROUP),
            TypeOfWork.ANY));
        
        assertTrue(tm.getActualWorkGraph().size() == 3);
    }
/*

    @Test
    public void testGetActualGraphOfWork() {
    }

    
    
    @Test
    public void testAddTimeCellAvailable() {
    }

    
    public void testRemoveTimeCellAvailable() {
    }

  
    @Test
    public void testAddTimeCellRequest() {
    }

   
    @Test
    public void testRemoveTimeCellRequest() {
    }

    
    @Test
    public void testOrganizeTimeCells() {
    }
    */
}

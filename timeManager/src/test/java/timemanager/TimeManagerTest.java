/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static timemanager.TypeOfWork.*;

/**
 *
 * @author razan
 */
public class TimeManagerTest {
    
    public TimeManagerTest() {
    }

    /**
     * Test of getWorkersAvailableInPeriod method, of class TimeManager.
     */
    @Test
    public void testGetWorkersAvailableInPeriod() {
        TimeManager tm = new TimeManager();
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:15:00"),
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.now(),
            new Worker("Jack", GROUP)));
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.now(),
            new Worker("Jane", GROUP)));
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.parse("2007-12-03T10:18:00"),
            LocalDateTime.now(),
            new Worker("John", GROUP)));
        
        assertTrue(tm.getWorkersAvailableInPeriod(
                LocalDateTime.parse("2007-12-03T10:15:00"),
                LocalDateTime.parse("2007-12-03T10:18:00")).size() == 3);
    }

    /**
     * Test of getFairGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGetFairGraphOfWork() {
            TimeManager tm = new TimeManager();
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:15:00"),
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.now(),
            new Worker("Jack", GROUP)));
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.now(),
            new Worker("Jane", GROUP)));
        
        tm.addTimeCellRequest(new TimeCellRequest(
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.parse("2007-12-03T10:18:00"),
            LocalDateTime.now(),
            new Worker("John", GROUP)));
        
        List<TimeCellRequest> TimeCellRequests = tm.getFairGraphOfWork(
                LocalDateTime.MAX,
                LocalDateTime.MIN);
    }

    /**
     * Test of getActualGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGetActualGraphOfWork() {
    }

    /**
     * Test of addTimeCellAvailable method, of class TimeManager.
     */
    @Test
    public void testAddTimeCellAvailable() {
    }

    /**
     * Test of removeTimeCellAvailable method, of class TimeManager.
     */
    @Test
    public void testRemoveTimeCellAvailable() {
    }

    /**
     * Test of addTimeCellRequest method, of class TimeManager.
     */
    @Test
    public void testAddTimeCellRequest() {
    }

    /**
     * Test of removeTimeCellRequest method, of class TimeManager.
     */
    @Test
    public void testRemoveTimeCellRequest() {
    }

    /**
     * Test of organizeTimeCells method, of class TimeManager.
     */
    @Test
    public void testOrganizeTimeCells() {
    }
    
}

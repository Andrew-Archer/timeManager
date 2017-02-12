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
     * Try to create TimeCell with the end equals to the start.
     * It passes the test if ZeroLengthException is trown.
     */
    @Test
    public void testZeroLengthException(){
        boolean result = false;
        try{
            new TimeCell(LocalDateTime.now(), LocalDateTime.now());
        }catch(ZeroLengthException ex){
            result = true;
        }catch(EndBeforeStartException ex){
            result = false;
        }
        assertTrue(result);
    }
    
    /**
     * Try to create TimeCell with the end before the start.
     * It passes the test if EndBeforeStartException is trown.
     */
    @Test
    public void testEndBeforeStartException(){
        boolean result = false;
        try{
            new TimeCell(LocalDateTime.now().plusNanos(1), LocalDateTime.now());
        }catch(ZeroLengthException ex){
            result = false;
        }catch(EndBeforeStartException ex){
            result = true;
        }
        assertTrue(result);
    }

    /**
     * Test of getWorkersAvailableInPeriod method, of class TimeManager.
     */
    @Test
    public void testGetWorkersAvailableInPeriod() throws 
                                                    EndBeforeStartException,
                                                    ZeroLengthException {
        TimeManager tm = new TimeManager();
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:15:00"),
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.now(),
            new Worker("Jack", GROUP),
            TypeOfWork.ANY));
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.now(),
            new Worker("Jane", GROUP),
            TypeOfWork.ANY));
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.parse("2007-12-03T10:18:00"),
            LocalDateTime.now(),
            new Worker("John", GROUP),
            TypeOfWork.ANY));
        
        assertTrue(tm.getWorkersAvailableInPeriod(
                LocalDateTime.parse("2007-12-03T10:15:00"),
                LocalDateTime.parse("2007-12-03T10:18:00")).size() == 3);
    }

    /**
     * Test of getFairGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGetFairGraphOfWork() throws
                                            EndBeforeStartException,
                                            ZeroLengthException {
            TimeManager tm = new TimeManager();
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:15:00"),
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.now(),
            new Worker("Jack", GROUP),
            TypeOfWork.ANY));
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:16:00"),
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.now(),
            new Worker("Jane", GROUP),
            TypeOfWork.ANY));
        
        tm.addTimeCellRequest(new TimeCellOfWorkerTime(
            LocalDateTime.parse("2007-12-03T10:17:00"),
            LocalDateTime.parse("2007-12-03T10:18:00"),
            LocalDateTime.now(),
            new Worker("John", GROUP),
            TypeOfWork.ANY));
        
        List<TimeCell> TimeCellRequests = tm.getFairGraphOfWork(
                LocalDateTime.MAX,
                LocalDateTime.MIN);
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

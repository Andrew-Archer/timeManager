/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import timemanager.actors.Manager;
import timemanager.actors.Worker;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
import timemanager.graphGenerator.GraphGenerator;
import timemanager.graphGenerator.TestGraphGenerator;
import timemanager.validation.PeriodValidator;
import timemanager.cellSplitLogic.Logic;

/**
 *
 * @author razan
 */
public class TimeManagerTest {
    
    public TimeManagerTest() {
    }

    /**
     * Test of getTimeCellsAssignedTo method, of class TimeManager.
     * The problem here is that we extract period not overlapping with
     * the pushed out cell.
     */
    @Test
    public void testGetTimeCellsAssignedTo() throws ZeroLengthException, EndBeforeStartException {
        //Worker to find in the graph
        Worker anExecutor = new Worker("Berimor", TypeOfWork.ANY);
        
        //Generate instance of the tested class
        TimeManager instance = new TimeManager();
        
        //The beginning of the graph
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 0, 0);
        
        //Generate the test graph
        instance.generateFairGraphOfWork(new TestGraphGenerator(
                start,
                start.plusHours(8),
                1,
                new Manager("George")));
        
        //Assign a TimeCell to an another executor
        instance.getActualWorkGraph().get(1).setExecutor(new Worker("Alice", TypeOfWork.ANY));
        
        //Assign a TimeCell to the executor we are looking for
        instance.getActualWorkGraph().get(3).setExecutor(anExecutor);
        
        //Fill up the expected result
        List<TimeCell> expResult = new ArrayList();
        for (TimeCell aTimeCell: instance.getActualWorkGraph()){
            if (
                    aTimeCell.isNotAssigned()
                    ||
                    aTimeCell.getExecutor().equals(anExecutor)){
                expResult.add(new TimeCell(aTimeCell));
            }
        }
        
        //Get result from the tested function
        List<TimeCell> result = instance.getTimeCellsAssignedTo(anExecutor);
        
        //Check the equality
        assertEquals(expResult, result);
    }

    

    /**
     * Test of generateFairGraphOfWork method, of class TimeManager.
     */
    @Test
    public void testGenerateFairGraphOfWork() throws Exception {
        //Set minimum duration of any TimeCell
        long minimumDuration = 2;
        
        //Set the sample creator
        Manager creator = new Manager("James");
        
        //Set the leght of the test graph
        long lenghtOfGraph = 4;
        
        //The beginning of the graph
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 0);
        
        //Create test graph generator with given parameters 
        GraphGenerator graphGenerator = new TestGraphGenerator(
                start,
                start.plusHours(lenghtOfGraph),
                minimumDuration,
                creator
        );
        
        //Create instance of the TimeManager
        TimeManager instance = new TimeManager();
        
        //Generate the fair graph of work
        instance.generateFairGraphOfWork(graphGenerator);
        
        //Set result
        List<TimeCell> result = instance.getActualWorkGraph();
        
        //Set expected result
        List<TimeCell> expResult = new ArrayList<>();
        for (int i = 0; i < lenghtOfGraph/minimumDuration; i ++){
            expResult.add(new TimeCell(
                start.plusHours(i*minimumDuration),
                start.plusHours((1+i)*minimumDuration),
                result.get(i).getCreationTime(),
                creator,
                null,
                TypeOfWork.ANY));
        }
       
        //Check the equality
        assertEquals(expResult, result);
    }
}

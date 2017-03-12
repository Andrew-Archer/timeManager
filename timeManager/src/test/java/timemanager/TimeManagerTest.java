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
import timemanager.cellSplitLogic.CellSplitLogic;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
import timemanager.graphGenerator.GraphGenerator;
import timemanager.graphGenerator.TestGraphGenerator;
import timemanager.validation.PeriodValidator;

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
        System.out.println("generateFairGraphOfWork");
        GraphGenerator graphGenerator = null;
        TimeManager instance = new TimeManager();
        instance.generateFairGraphOfWork(graphGenerator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

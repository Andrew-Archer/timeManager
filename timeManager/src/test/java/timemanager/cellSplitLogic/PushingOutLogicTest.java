/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.cellSplitLogic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import timemanager.TimeCell;
import timemanager.TimeCellSpliterationResult;
import timemanager.TypeOfWork;
import timemanager.actors.Manager;
import timemanager.actors.Worker;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/**
 *
 * @author razan
 */
public class PushingOutLogicTest {
    //Creating TimeCells to compare
    private List<TimeCell> listToCompare = new ArrayList<>();
    private TimeCell cellToInsert;
    private PushingOutLogic splitLogic = new PushingOutLogic();
    private List<TimeCellSpliterationResult> splitResults = new ArrayList<>();

    public CellSplitLogicForWorkRequePushingOutLogicTesteforeStartException,
            ZeroLengthException {
        
        LocalDateTime startOfCellToInsert = LocalDateTime.of(2017, 02, 1, 0, 0, 0, 0);
        
        cellToInsert = new TimeCell(
                startOfCellToInsert,
                startOfCellToInsert.plusHours(3),
                startOfCellToInsert,
                new Manager("Robert"),
                new Worker("Sad", TypeOfWork.ANY),
                TypeOfWork.ANY);
        
        //Creates list of TimeCells to compare
        listToCompare.add(new TimeCell(cellToInsert, cellToInsert.getEnd().plusHours(1)));
        listToCompare.add(new TimeCell(cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert, cellToInsert.getEnd().minusHours(1)));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert.getEnd().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert.getEnd().minusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert.getEnd().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert.getEnd().minusHours(1), cellToInsert));
        
        //Fills up list of TimeCellSpliterationResult
        //31 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(0).addPushedOut(new TimeCell(listToCompare.get(0), cellToInsert.getEnd()));
        splitResults.get(0).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(0).addToInsert(new TimeCell(cellToInsert.getEnd(), listToCompare.get(0)));
        
        //33 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(1).addPushedOut(new TimeCell(listToCompare.get(1)));
        splitResults.get(1).addToInsert(new TimeCell(cellToInsert));
        
        //32 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(2).addPushedOut(new TimeCell(listToCompare.get(2)));
        splitResults.get(2).addToInsert(new TimeCell(cellToInsert, listToCompare.get(2).getEnd()));
        splitResults.get(2).addToInsertionLeft(new TimeCell(listToCompare.get(2).getEnd(), cellToInsert));
        
        //21 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(3).addPushedOut(new TimeCell(cellToInsert.getStart(), cellToInsert.getEnd(), listToCompare.get(3)));
        splitResults.get(3).addToInsert(new TimeCell(listToCompare.get(3), cellToInsert.getStart()));
        splitResults.get(3).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(3).addToInsert(new TimeCell(cellToInsert.getEnd(), listToCompare.get(3)));
        
        //23 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(4).addPushedOut(new TimeCell(cellToInsert.getStart(), cellToInsert.getEnd(), listToCompare.get(4)));
        splitResults.get(4).addToInsert(new TimeCell(listToCompare.get(4), cellToInsert.getStart()));
        splitResults.get(4).addToInsert(new TimeCell(cellToInsert));
        
        //22 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(5).addPushedOut(new TimeCell(cellToInsert.getStart(), listToCompare.get(5)));
        splitResults.get(5).addToInsert(new TimeCell(listToCompare.get(5), cellToInsert.getStart()));
        splitResults.get(5).addToInsert(new TimeCell(cellToInsert, listToCompare.get(5).getEnd()));
        splitResults.get(5).addToInsertionLeft(new TimeCell(listToCompare.get(5).getEnd(), cellToInsert));
        
        //11 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(6).addPushedOut(new TimeCell(listToCompare.get(6),cellToInsert.getEnd()));
        splitResults.get(6).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(6).addToInsert(new TimeCell(cellToInsert.getEnd(), listToCompare.get(6)));
        
        //13 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(7).addPushedOut(new TimeCell(listToCompare.get(7),cellToInsert.getEnd()));
        splitResults.get(7).addToInsert(new TimeCell(cellToInsert));
        
        //12 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(8).addPushedOut(new TimeCell(listToCompare.get(8)));
        splitResults.get(8).addToInsert(new TimeCell(cellToInsert, listToCompare.get(8).getEnd()));
        splitResults.get(8).addToInsertionLeft(new TimeCell(listToCompare.get(8).getEnd(), cellToInsert));
    }

    /**
     * Test of split method, of class PushingOutLogic.
     * @throws java.lang.Exception
     */
    @Test
    public void testSplit() throws Exception {
        for (int i = 0; i < 9; i ++){
            assertTrue(
                    splitResults.get(i).equals(
                    splitLogic.split(cellToInsert, listToCompare.get(i)))
            );
        }
    }
    
    
            

}

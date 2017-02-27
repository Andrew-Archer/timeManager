/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import timemanager.actors.Manager;
import timemanager.actors.Person;
import timemanager.actors.Worker;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/**
 *
 * @author razan
 */
public class TimeCellTest {
    //Creating TimeCells to compare
    private List<TimeCell> listToCompare = new ArrayList<>();
    private TimeCell cellToInsert;
    public TimeCellTest() throws EndBeforeStartException, ZeroLengthException {
        LocalDateTime startOfCellToInsert = LocalDateTime.now();
        cellToInsert = new TimeCell(
                startOfCellToInsert,
                startOfCellToInsert.plusHours(3),
                startOfCellToInsert,
                new Manager("Robert"),
                new Worker("Sad", TypeOfWork.ANY),
                TypeOfWork.ANY);
        listToCompare.add(new TimeCell(cellToInsert, cellToInsert.getEnd().plusHours(1)));
        listToCompare.add(new TimeCell(cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert, cellToInsert.getEnd().minusHours(1)));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert.getEnd().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(1), cellToInsert.getEnd().minusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert.getEnd().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().plusHours(1), cellToInsert.getEnd().minusHours(1), cellToInsert));
        //Here go TimeCells which is not overlapping with the cellToInsert
        //Go before the cellToInsert
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(2), cellToInsert.getStart(), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getStart().minusHours(2), cellToInsert.getStart().minusHours(1), cellToInsert));
        //Go after the cellToInsert
        listToCompare.add(new TimeCell(cellToInsert.getEnd(), cellToInsert.getEnd().plusHours(2), cellToInsert));
        listToCompare.add(new TimeCell(cellToInsert.getEnd().plusHours(1), cellToInsert.getEnd().plusHours(2), cellToInsert));
    }

    
    /**
     * Try to create TimeCell with the end equals to the start.
     * It passes the test if ZeroLengthException is thrown.
     */
    @Test
    public void testZeroLengthException(){
        boolean result = false;
        try{
            new TimeCell(
                    LocalDateTime.now(),
                    LocalDateTime.now(),
            LocalDateTime.now(),
            new Person("James"), 
            new Worker("Fred", TypeOfWork.ANY) {},
            TypeOfWork.ANY);
        }catch(ZeroLengthException ex){
            result = true;
        }catch(EndBeforeStartException ex){
            result = false;
        }
        assertTrue(result);
    }
    
    /**
     * Try to create TimeCell with the end before the start. It passes the test
     * if EndBeforeStartException is thrown.
     */
    @Test
    public void testEndBeforeStartException() {
        boolean result = false;
        try {
            new TimeCell(
                    LocalDateTime.now(),
                    LocalDateTime.now().minusDays(1),
                    LocalDateTime.now(),
                    new Person("James"),
                    new Worker("Fred", TypeOfWork.ANY),
                    TypeOfWork.ANY);
        } catch (ZeroLengthException ex) {
            result = false;
        } catch (EndBeforeStartException ex) {
            result = true;
        }
        assertTrue(result);
    }
    
    
    /**
     * Test of getOverlappingType method, of class TimeCell.
     */
    @Test
    public void testGetOverlappingType() {
        //Tests oerlapping type
        assertEquals(31, cellToInsert.getOverlappingType(listToCompare.get(0)));
        assertEquals(33, cellToInsert.getOverlappingType(listToCompare.get(1)));
        assertEquals(32, cellToInsert.getOverlappingType(listToCompare.get(2)));
        assertEquals(21, cellToInsert.getOverlappingType(listToCompare.get(3)));
        assertEquals(23, cellToInsert.getOverlappingType(listToCompare.get(4)));
        assertEquals(22, cellToInsert.getOverlappingType(listToCompare.get(5)));
        assertEquals(11, cellToInsert.getOverlappingType(listToCompare.get(6)));
        assertEquals(13, cellToInsert.getOverlappingType(listToCompare.get(7)));
        assertEquals(12, cellToInsert.getOverlappingType(listToCompare.get(8)));
    }

    /**
     * 
     */
    @Test
    public void testIsOverlapping(){
        for (int i = 0; i < listToCompare.size(); i ++){
            if (i < 9){
                assertEquals(true, cellToInsert.isOverlapping(listToCompare.get(i)));
            }else{
                assertEquals(false, cellToInsert.isOverlapping(listToCompare.get(i)));
            }
        }
    }
    
    /**
     * Test of getOverlappingTimeCells method, of class TimeCell.
     */
    @Test
    public void testGetOverlappingTimeCells() {
        assertEquals(9, cellToInsert.getOverlappingTimeCells(listToCompare).size());
    }

}

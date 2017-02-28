/*
 * Copyright (C) 2017 razan.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import timemanager.actors.Manager;
import timemanager.actors.Worker;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/**
 *
 * @author razan
 */
public class TimeCellSpliterationResultTest {
    private List<TimeCellSpliterationResult> splitResults = new ArrayList<>();
    private List<TimeCell> listToCompare = new ArrayList<>();
    private TimeCell cellToInsert;
    public TimeCellSpliterationResultTest() throws
            EndBeforeStartException,
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
        
        //31 case
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(0).addPushedOut(new TimeCell(listToCompare.get(0), cellToInsert.getEnd()));
        splitResults.get(0).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(0).addToInsert(new TimeCell(cellToInsert.getEnd(), listToCompare.get(0)));
        
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(1).addPushedOut(new TimeCell(listToCompare.get(0), cellToInsert.getEnd()));
        splitResults.get(1).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(1).addToInsert(new TimeCell(cellToInsert.getEnd(), listToCompare.get(0)));
        
        splitResults.add(new TimeCellSpliterationResult());
        splitResults.get(2).addPushedOut(new TimeCell(cellToInsert));
        splitResults.get(2).addToInsert(new TimeCell(cellToInsert));
        splitResults.get(2).addToInsert(new TimeCell(cellToInsert));
    }

    /**
     * Test of equals method, of class TimeCellSpliterationResult.
     */
    @Test
    public void testEquals() {
        System.out.println(splitResults.get(0));
        System.out.println(splitResults.get(1));
        System.out.println(splitResults.get(2));
        assertTrue(splitResults.get(0).equals(splitResults.get(1)));
        //assertFalse(splitResults.get(0).equals(splitResults.get(2)));
    }

    
    @Test
    public void testArrayListEqulity(){
        LocalDateTime initialDateTime = LocalDateTime.now();
        
        List<LocalDateTime> L1 = new ArrayList<>();
        
        List<LocalDateTime> L2 = new ArrayList<>();
        
        L1.add(initialDateTime.plusDays(1));
        L2.add(initialDateTime.plusDays(1));
        
        assertTrue(L1.equals(L2));
    }
}

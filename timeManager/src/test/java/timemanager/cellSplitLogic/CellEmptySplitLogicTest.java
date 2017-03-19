/*
 * Copyright (C) 2017 Andrew.
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
package timemanager.cellSplitLogic;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import timemanager.TimeCell;
import timemanager.TimeCellSpliterationResult;
import timemanager.TypeOfWork;
import timemanager.actors.Manager;
import timemanager.actors.Worker;

/**
 *
 * @author Andrew
 */
public class CellEmptySplitLogicTest {
    
    public CellEmptySplitLogicTest() {
    }

    /**
     * Test of split method, of class CellEmtpySplitLogic.
     */
    @Test
    public void testSplit() throws Exception {
        //Test case when the inserted TimeCell is shorter than
        //the replaced TimeCell.
        //Creating the inserted TimeCell
        TimeCell cellToInsert = new TimeCell(
                LocalDateTime.of(1, 1, 1, 0, 0),
                LocalDateTime.of(1, 1, 1, 2, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        );
        
        //Creating the replaced TimeCell
        TimeCell replacedCell = new TimeCell(
                LocalDateTime.of(1, 1, 1, 5, 0),
                LocalDateTime.of(1, 1, 1, 8, 0),
                LocalDateTime.of(1, 1, 1, 0, 0),
                new Manager("Jack"),
                null,
                TypeOfWork.ANY
        );
        
        //Create the tested sample of CellEmptySplitLogic
        CellEmtpySplitLogic instance = new CellEmtpySplitLogic();
        
        //Create the expected result
        TimeCellSpliterationResult expResult = new TimeCellSpliterationResult();
        //Add expected TimeCells into the expResult
        expResult.addToInsert( new TimeCell(
                LocalDateTime.of(1, 1, 1, 5, 0),
                LocalDateTime.of(1, 1, 1, 7, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        ));
        expResult.addToInsert( new TimeCell(
                LocalDateTime.of(1, 1, 1, 7, 0),
                LocalDateTime.of(1, 1, 1, 8, 0),
                LocalDateTime.of(1, 1, 1, 0, 0),
                new Manager("Jack"),
                null,
                TypeOfWork.ANY
        ));
        //Run tested method
        TimeCellSpliterationResult result = instance.split(cellToInsert, replacedCell);
        //Check result
        assertEquals(expResult, result);
        
        //Test case when both TimeCells have the same duration
        //Creating the inserted TimeCell
        cellToInsert = new TimeCell(
                LocalDateTime.of(1, 1, 1, 0, 0),
                LocalDateTime.of(1, 1, 1, 2, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        );
        
        //Creating the replaced TimeCell
        replacedCell = new TimeCell(
                LocalDateTime.of(1, 1, 1, 7, 0),
                LocalDateTime.of(1, 1, 1, 9, 0),
                LocalDateTime.of(1, 1, 1, 0, 0),
                new Manager("Jack"),
                null,
                TypeOfWork.ANY
        );
        
        //Clear expected result
        expResult.getToInsert().clear();
        //Add expected TimeCells into the expResult
        expResult.addToInsert( new TimeCell(
                LocalDateTime.of(1, 1, 1, 7, 0),
                LocalDateTime.of(1, 1, 1, 9, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        ));
        
        //Run tested method
        result = instance.split(cellToInsert, replacedCell);
        //Check result
        assertEquals(expResult, result);
        
        //Test case when the inserted TimeCell
        //is longer than the replaced TimeCell
        //Creating the inserted TimeCell
        cellToInsert = new TimeCell(
                LocalDateTime.of(1, 1, 1, 0, 0),
                LocalDateTime.of(1, 1, 1, 2, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        );
        
        //Creating the replaced TimeCell
        replacedCell = new TimeCell(
                LocalDateTime.of(1, 1, 1, 8, 0),
                LocalDateTime.of(1, 1, 1, 9, 0),
                LocalDateTime.of(1, 1, 1, 0, 0),
                new Manager("Jack"),
                null,
                TypeOfWork.ANY
        );
        
        //Clear expected result
        expResult.getToInsert().clear();
        //Add expected TimeCells into the expResult
        expResult.addToInsert( new TimeCell(
                LocalDateTime.of(1, 1, 1, 8, 0),
                LocalDateTime.of(1, 1, 1, 9, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        ));
        
        expResult.addToInsertionLeft( new TimeCell(
                LocalDateTime.of(1, 1, 1, 1, 0),
                LocalDateTime.of(1, 1, 1, 2, 0),
                LocalDateTime.of(1, 1, 1, 1, 0),
                new Manager("Jack"),
                new Worker("Andy", TypeOfWork.ANY),
                TypeOfWork.ANY
        ));
        
        //Run tested method
        result = instance.split(cellToInsert, replacedCell);
        //Check result
        assertEquals(expResult, result);
    }
    
}

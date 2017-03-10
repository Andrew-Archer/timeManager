/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.cellSplitLogic;

import timemanager.TimeCell;
import timemanager.TimeCellSpliterationResult;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

public class CellEmtySplitLogic implements CellSplitLogic {

    @Override
    public TimeCellSpliterationResult split(
            TimeCell cellToInsert,
            TimeCell replacedCell) 
            throws
            EndBeforeStartException,
            ZeroLengthException {
        
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        
        //If inserted cell is vider than TimeCell to replace
        if (cellToInsert.getDuration().compareTo(replacedCell.getDuration()) <  0){
            result.addToInsert(new TimeCell(
                replacedCell.getStart(),
                replacedCell.getStart().plus(cellToInsert.getDuration()),
                cellToInsert
            ));
            result.addToInsertionLeft(new TimeCell(
                cellToInsert.getStart().plus(cellToInsert.getDuration()),
                cellToInsert));
            return result;
        }
        
        //If inserted cell is shorter than TimeCell to replace
        if (cellToInsert.getDuration().compareTo(replacedCell.getDuration()) >0){
            result.addToInsert(new TimeCell(
                replacedCell.getStart(),
                replacedCell.getStart().plus(cellToInsert.getDuration()),
                cellToInsert
            ));
            result.addToInsert(new TimeCell(
                    replacedCell.getStart().plus(cellToInsert.getDuration()),
                    replacedCell.getEnd(),
                    replacedCell
            ));
            return result;
        }
        
        //If both cell have the sae duration
        result.addToInsert(new TimeCell(replacedCell, cellToInsert));
        return result;
    }

}

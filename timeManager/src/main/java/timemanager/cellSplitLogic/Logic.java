/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.cellSplitLogic;

import java.util.List;
import timemanager.TimeCell;
import timemanager.TimeCellSpliterationResult;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/**
 *
 * @author razan
 */
public interface Logic {

    /**
     *
     * @param cellToInsert
     * @param replacedCell
     * @return
     * @throws timemanager.exceptions.EndBeforeStartException
     * @throws timemanager.exceptions.ZeroLengthException
     */
    public List<TimeCell> split(
            TimeCell cellToInsert,
            List<TimeCell> replacedCell) throws EndBeforeStartException, ZeroLengthException;
}

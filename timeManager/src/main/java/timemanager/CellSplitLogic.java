/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

/**
 *
 * @author razan
 */
public interface CellSplitLogic {

    /**
     *
     * @param cellToInsert
     * @param replacedCell
     * @return
     * @throws timemanager.EndBeforeStartException
     * @throws timemanager.ZeroLengthException
     */
    public TimeCellSpliterationResult split(
            TimeCell cellToInsert,
            TimeCell replacedCell) throws EndBeforeStartException, ZeroLengthException;
}

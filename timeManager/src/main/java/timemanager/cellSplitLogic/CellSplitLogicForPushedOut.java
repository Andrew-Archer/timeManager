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

public class CellSplitLogicForPushedOut implements CellSplitLogic {
    
    @Override
    public TimeCellSpliterationResult split(TimeCell cellToInsert, TimeCell replacedCell)
            throws EndBeforeStartException, ZeroLengthException {
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        if (replacedCell.isAssigned()) {
            switch (cellToInsert.getOverlappingType(replacedCell)) {
                case 11:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));

                    //There is nothing left to insert
                  //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 13:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                    //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                     *Needn't go out of the loop since this is the last iteration case,
                     *because the next TimeCell in the main graph
                     *do not overlap the cellToInsert.
                     */
                    break;
                case 12:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.addToInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
                case 21:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));

                    //There is nothing left to insert
                    //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                     *Needn't go out of the loop since this is the last iteration case,
                     *because the next TimeCell in the main graph
                     *do not overlap the cellToInsert.
                     */
                    break;
                case 23:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                  //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 22:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.addToInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
                case 31:
                    //To insert into the graph
                	result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));

                    //There is nothing left to insert
                  //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 33:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                    //So adds nothing to insertion left
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 32:
                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.addToInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
            }
        } else {
            result = new CellEmtySplitLogic().split(cellToInsert, replacedCell);
        }
        return result;
    }
    
}

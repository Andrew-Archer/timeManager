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

public class CellSplitLogicForWorkRequest implements CellSplitLogic {
    
    @Override
    public TimeCellSpliterationResult split(TimeCell cellToInsert, TimeCell replacedCell)
            throws EndBeforeStartException, ZeroLengthException {
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        if (replacedCell.isAssigned()) {
            switch (cellToInsert.getOverlappingType(replacedCell)) {
                case 11:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell, cellToInsert.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 13:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 12:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
                case 21:
                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            cellToInsert.getStart(),
                            cellToInsert.getEnd(),
                            replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 23:
                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            cellToInsert.getStart(),
                            cellToInsert.getEnd(),
                            replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 22:
                    //Pushed out
                    result.addPushedOut(new TimeCell(cellToInsert.getStart(), replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
                case 31:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell, cellToInsert.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));
                    result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));
                    

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 33:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the cellToInsert.
                     */
                    break;
                case 32:
                    //Pushed out
                    result.addPushedOut(new TimeCell(replacedCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                    break;
            }
        } else {
            result.add(new CellEmtpySplitLogic().split(cellToInsert, replacedCell));
        }
        return result;
    }
    
}

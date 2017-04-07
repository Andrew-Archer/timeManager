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

public class PushingOutLogic implements Logic {
    
    private TimeCellSpliterationResult combineRequestWithAssignedOverlapping(
            TimeCell request,
            TimeCell overlappingCell) throws
                                            EndBeforeStartException,
                                            ZeroLengthException{
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        switch (request.getOverlappingType(overlappingCell)) {
                case 11:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell, request.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request));
                    result.addToInsert(new TimeCell(request.getEnd(), overlappingCell));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 13:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 12:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request, overlappingCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(overlappingCell.getEnd(), request));
                    break;
                case 21:
                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            request.getStart(),
                            request.getEnd(),
                            overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(overlappingCell, request.getStart()));
                    result.addToInsert(new TimeCell(request));
                    result.addToInsert(new TimeCell(request.getEnd(), overlappingCell));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 23:
                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            request.getStart(),
                            request.getEnd(),
                            overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(overlappingCell, request.getStart()));
                    result.addToInsert(new TimeCell(request));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 22:
                    //Pushed out
                    result.addPushedOut(new TimeCell(request.getStart(), overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(overlappingCell, request.getStart()));
                    result.addToInsert(new TimeCell(request, overlappingCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(overlappingCell.getEnd(), request));
                    break;
                case 31:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell, request.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request));
                    result.addToInsert(new TimeCell(request.getEnd(), overlappingCell));
                    

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 33:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request));

                    //There is nothing left to insert
                    //result.setInsertionLeft(null);
                    /*Exit from loop because there is nothing to insert have left.
                 *Needn't go out of the loop since this is the last iteration case,
                 *because the next TimeCell in the main graph
                 *do not overlap the request.
                     */
                    break;
                case 32:
                    //Pushed out
                    result.addPushedOut(new TimeCell(overlappingCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(request, overlappingCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(overlappingCell.getEnd(), request));
                    break;
            }
        return result;
    }
    
    private TimeCellSpliterationResult combineRequestWithNotAssignedOverlapping(
            TimeCell request,
            TimeCell overlappingCell) throws
                                            EndBeforeStartException,
                                            ZeroLengthException{
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();

        //If the inserted TimeCell is shorter than the TimeCell to replace
        if (request.getDuration().compareTo(overlappingCell.getDuration()) < 0) {
            result.addToInsert(new TimeCell(
                    overlappingCell.getStart(),
                    overlappingCell.getStart().plus(request.getDuration()),
                    request
            ));
            result.addToInsert(new TimeCell(
                    overlappingCell.getStart().plus(request.getDuration()),
                    overlappingCell));
            return result;
        }

        //If the inserted TimeCell is wider than the TimeCell to replace
        if (request.getDuration().compareTo(overlappingCell.getDuration()) > 0) {
            result.addToInsert(new TimeCell(
                    overlappingCell.getStart(),
                    overlappingCell.getEnd(),
                    request
            ));
            result.addToInsertionLeft(new TimeCell(
                    request.getStart().plus(overlappingCell.getDuration()),
                    request.getEnd(),
                    request
            ));
            return result;
        }

        //If both cell have the sae duration
        result.addToInsert(new TimeCell(overlappingCell, request));
        return result;
    }
    
    private TimeCellSpliterationResult doSomeThingWithPushedOut(
            TimeCellSpliterationResult result,
            TimeCell request,
            List<TimeCell> graph) throws EndBeforeStartException, ZeroLengthException {
        // Inserting pushed out TimeCells
        // Get TimeCell with the same name as.
        if (!result.getPushedOut().isEmpty()) {

            List<TimeCell> timeCellsListForGivenWorker;
            timeCellsListForGivenWorker = request.getTimeCellsAssignedTo(graph);
            for (TimeCell pushedOut : result.getPushedOut()) {
                while (pushedOut != null) {
                    TimeCellSpliterationResult temp;
                    CellSplitLogicForPushedOut splitLogicForPushed = new CellSplitLogicForPushedOut();
                    temp = splitLogicForPushed.split(pushedOut, timeCellsListForGivenWorker.get(0));

                    if (!temp.getInsertionLeftList().isEmpty()) {
                        pushedOut = temp.getInsertionLeft();
                        temp.getInsertionLeftList().clear();
                    } else {//There is nothig left from the pushedOut after plit method
                        pushedOut = null;
                    }
                    //Remove replaced by pushedOut cell for given worker
                    timeCellsListForGivenWorker.remove(0);
                    result.add(temp);
                }
            }
            result.getToInsert().addAll(timeCellsListForGivenWorker);
        }
        return result;
    }
    
    @Override
    public List<TimeCell> split(
            TimeCell request,
            List<TimeCell> graph)
            throws
            EndBeforeStartException,
            ZeroLengthException {
        //Here we put the resut
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();

        //Extract overlapping cell from the graph
        List<TimeCell> overlappingCells = request.getOverlappingTimeCells(graph);

        //Process every overlapping cell
        for (TimeCell overlappingCell : overlappingCells) {

            if (overlappingCell.isAssigned()) {
                result.add(combineRequestWithAssignedOverlapping(request,overlappingCell));
          
            } else {
                result.add(combineRequestWithNotAssignedOverlapping(request, overlappingCell));
            }
        }
        //Process pushed out cells
        result = doSomeThingWithPushedOut(result, request, graph);
        return result.getToInsert();
    }
    
}

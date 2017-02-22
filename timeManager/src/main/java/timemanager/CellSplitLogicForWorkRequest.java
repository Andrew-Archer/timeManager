/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

public class CellSplitLogicForWorkRequest implements CellSplitLogic {

    @Override
    public TimeCellSpliterationResult split(TimeCell cellToInsert, TimeCell replacedCell)
            throws EndBeforeStartException, ZeroLengthException {
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        switch (cellToInsert.getOverlappingType(replacedCell)) {
            case 11:
                //Pushed out
                result.addPushedOut(new TimeCell(replacedCell, cellToInsert.getEnd()));

                //To insert into the graph
                result.addToInsert(new TimeCell(replacedCell.getStart(), cellToInsert));
                result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));
                result.addToInsert(new TimeCell(cellToInsert, replacedCell.getStart()));

                //There is nothing left to insert
                result.setInsertionLeft(null);
                //Exit from loop because there is nothing to insert have left
                //Needn't go out of loop since it's last iteration case
                break;
            case 13:
                //Pushed out
                result.addPushedOut(replacedCell);

                //To insert into the graph
                result.addToInsert(new TimeCell(replacedCell.getStart(), cellToInsert));
                result.addToInsert(new TimeCell(cellToInsert, replacedCell.getStart()));

                //There is nothing left to insert
                result.setInsertionLeft(null);
                //Exit from loop because there is nothing to insert have left
                //Needn't go out of loop since it's last iteration case
                break;
            case 12:
                //Pushed out
                result.addPushedOut(replacedCell);

                //To insert into the graph
                result.addToInsert(new TimeCell(
                        replacedCell.getStart(),
                        replacedCell.getEnd(),
                        cellToInsert));
                result.addToInsert(new TimeCell(cellToInsert, replacedCell.getStart()));

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
                result.setInsertionLeft(null);
                break;
            case 23:

                //Pushed out
                result.addPushedOut(new TimeCell(
                        cellToInsert.getStart(),
                        cellToInsert.getEnd(),
                        replacedCell));

                //To insert into the graph
                result.addToInsert(new TimeCell(replacedCell, cellToInsert.getStart()));
                result.addToInsert(cellToInsert);

                //There is nothing left to insert
                result.setInsertionLeft(null);
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
                result.addToInsert(new TimeCell(cellToInsert.getEnd(), replacedCell));
                result.addToInsert(cellToInsert);

                //There is nothing left to insert
                result.setInsertionLeft(null);
                break;
            case 33:
                //Pushed out
                result.addPushedOut(replacedCell);

                //To insert into the graph
                result.addToInsert(cellToInsert);

                //There is nothing left to insert
                result.setInsertionLeft(null);
                break;
            case 32:
                //Pushed out
                result.addPushedOut(replacedCell);

                //To insert into the graph
                result.addToInsert(new TimeCell(cellToInsert, replacedCell.getEnd()));

                //Left part of the TimeCell to insert
                result.setInsertionLeft(new TimeCell(replacedCell.getEnd(), cellToInsert));
                break;
        }
        return result;
    }

}

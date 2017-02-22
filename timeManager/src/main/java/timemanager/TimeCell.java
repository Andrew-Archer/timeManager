package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCell implements Comparable {

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime creationTime;
    private Person creator;
    private Worker executor = null;
    private TypeOfWork typeOfWork = TypeOfWork.ANY;

    public TimeCell(
            TimeCell original,
            LocalDateTime newEnd) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(
                original.getStart(),
                newEnd,
                original.getCreationTime(),
                original.getCreator(),
                original.getExecutor(),
                original.getTypeOfWork());
    }

    public TimeCell(
            LocalDateTime newStart,
            TimeCell original) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(
                newStart,
                original.getEnd(),
                original.getCreationTime(),
                original.getCreator(),
                original.getExecutor(),
                original.getTypeOfWork());
    }

    public TimeCell(
            LocalDateTime aStart,
            LocalDateTime anEnd,
            Person aCreator) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(
                aStart,
                anEnd,
                LocalDateTime.now(),
                aCreator,
                null,
                TypeOfWork.ANY);
    }

    public TimeCell(
            LocalDateTime aStart,
            LocalDateTime anEnd,
            Person aCreator,
            TypeOfWork aTypeOfWork) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(
                aStart,
                anEnd,
                LocalDateTime.now(),
                aCreator,
                null,
                aTypeOfWork);
    }

    public TimeCell(TimeCell aTimeCell) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(
                aTimeCell.getStart(),
                aTimeCell.getEnd(),
                aTimeCell.getCreationTime(),
                aTimeCell.getCreator(),
                aTimeCell.getExecutor(),
                aTimeCell.getTypeOfWork());
    }

    /**
     * Main constructor. All other constructors must call it<br>
     * since this constructor check some business rules.
     */
    public TimeCell(
            LocalDateTime aStart,
            LocalDateTime anEnd,
            LocalDateTime aCreationTime,
            Person aCreator,
            Worker anExecutor,
            TypeOfWork aTypeOfWork) throws
            EndBeforeStartException,
            ZeroLengthException {

        if (anEnd.isBefore(aStart)) {
            throw new EndBeforeStartException();
        } else if (anEnd.equals(aStart)) {
            throw new ZeroLengthException();
        } else {
            start = aStart;
            end = anEnd;
            creationTime = aCreationTime;
            creator = aCreator;
            executor = anExecutor;
            typeOfWork = aTypeOfWork;
        }
    }

    public TimeCell(
            LocalDateTime aStart,
            LocalDateTime anEnd,
            TimeCell aTimeCell) throws
            EndBeforeStartException,
            ZeroLengthException {

        this(
                aStart,
                anEnd,
                aTimeCell.getCreationTime(),
                aTimeCell.getCreator(),
                aTimeCell.getExecutor(),
                aTimeCell.getTypeOfWork());
    }

    /**
     *
     * @param graphToInsertTo
     * @param aCellToInsert
     * @param aValidator
     * @return 
     * @throws Exception
     */
    public TimeCellSpliterationResult splitTimeCells(
            List<TimeCell> graphToInsertTo,
            TimeCell aCellToInsert,
            PeriodValidator aValidator) throws
            Exception {

        //Get overlapping with the aCellToInsert cells from the graphToInsertTo.
        List<TimeCell> overlappingOfGraphAndToInsertCell;
        overlappingOfGraphAndToInsertCell = aCellToInsert.getOverlappingTimeCells(graphToInsertTo);

        //Here we put splitted parts to return.
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();

        //Initializing left insertion with aCellToInsert
        result.setInsertionLeft(aCellToInsert);

        //Fills up result
        for (TimeCell aTimeCell : overlappingOfGraphAndToInsertCell) {
        	//TODO Put the switch into a separate method 
            switch (result.getInsertionLeft().getOverlappingType(aTimeCell)) {
                case 11:
                    //Pushed out
                    result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
                    result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
                    result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    //Exit from loop because there is nothing to insert have left
                    //Needn't go out of loop since it's last iteration case
                    break;
                case 13:
                    //Pushed out
                    result.addPushedOut(aTimeCell);

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
                    result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    //Exit from loop because there is nothing to insert have left
                    //Needn't go out of loop since it's last iteration case
                    break;
                case 12:
                    //Pushed out
                    result.addPushedOut(aTimeCell);

                    //To insert into the graph
                    result.addToInsert(new TimeCell(
                            aTimeCell.getStart(),
                            aTimeCell.getEnd(),
                            aCellToInsert));
                    result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
                    break;
                case 21:
                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            aCellToInsert.getStart(),
                            aCellToInsert.getEnd(),
                            aTimeCell));
                    
                    //To insert into the graph
                    result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
                    result.addToInsert(new TimeCell(aCellToInsert));
                    result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    break;
                case 23:

                    //Pushed out
                    result.addPushedOut(new TimeCell(
                            aCellToInsert.getStart(),
                            aCellToInsert.getEnd(),
                            aTimeCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
                    result.addToInsert(aCellToInsert);
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    break;
                case 22:
                    //Pushed out
                    result.addPushedOut(new TimeCell(aCellToInsert.getStart(), aTimeCell));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
                    result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
                    break;
                case 31:
                    //Pushed out
                    result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
                    result.addToInsert(aCellToInsert);
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    break;
                case 33:
                    //Pushed out
                    result.addPushedOut(aTimeCell);

                    //To insert into the graph
                    result.addToInsert(aCellToInsert);
                    
                    //There is nothing left to insert
                    result.setInsertionLeft(null);
                    break;
                case 32:
                    //Pushed out
                    result.addPushedOut(aTimeCell);

                    //To insert into the graph
                    result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));

                    //Left part of the TimeCell to insert
                    result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
                    break;
            }
        }
        //Copies InsertionLeft to ToInsert
        result.pack();
        
        //Extraction pushedOut for processing
        List<TimeCell> pushedOut = result.getPushedOut();
        
        //We can remove pushed out TimeCells we don't need it anymore
        result.getPushedOut().clear();
        
		for (TimeCell timeCellPushedOut : pushedOut) {
			overlappingOfGraphAndToInsertCell = timeCellPushedOut.getOverlappingTimeCells(
					graphToInsertTo,
					aCellToInsert.getExecutor());
			result.setInsertionLeft(timeCellPushedOut);
			for (TimeCell aTimeCell : overlappingOfGraphAndToInsertCell) {

				switch (timeCellPushedOut.getOverlappingType(aTimeCell)) {
				case 11:
					// Pushed out
					result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
					result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
					result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));

					// There is nothing left to insert
					result.setInsertionLeft(null);
					// Exit from loop because there is nothing to insert have
					// left
					// Needn't go out of loop since it's last iteration case
					break;
				case 13:
					// Pushed out
					result.addPushedOut(aTimeCell);

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
					result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));

					// There is nothing left to insert
					result.setInsertionLeft(null);
					// Exit from loop because there is nothing to insert have
					// left
					// Needn't go out of loop since it's last iteration case
					break;
				case 12:
					// Pushed out
					result.addPushedOut(aTimeCell);

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell.getStart(), aTimeCell.getEnd(), aCellToInsert));
					result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getStart()));

					// Left part of the TimeCell to insert
					result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
					break;
				case 21:
					// Pushed out
					result.addPushedOut(new TimeCell(aCellToInsert.getStart(), aCellToInsert.getEnd(), aTimeCell));

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
					result.addToInsert(new TimeCell(aCellToInsert));
					result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));

					// There is nothing left to insert
					result.setInsertionLeft(null);
					break;
				case 23:

					// Pushed out
					result.addPushedOut(new TimeCell(aCellToInsert.getStart(), aCellToInsert.getEnd(), aTimeCell));

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
					result.addToInsert(aCellToInsert);

					// There is nothing left to insert
					result.setInsertionLeft(null);
					break;
				case 22:
					// Pushed out
					result.addPushedOut(new TimeCell(aCellToInsert.getStart(), aTimeCell));

					// To insert into the graph
					result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
					result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));

					// Left part of the TimeCell to insert
					result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
					break;
				case 31:
					// Pushed out
					result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));

					// To insert into the graph
					result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
					result.addToInsert(aCellToInsert);

					// There is nothing left to insert
					result.setInsertionLeft(null);
					break;
				case 33:
					// Pushed out
					result.addPushedOut(aTimeCell);

					// To insert into the graph
					result.addToInsert(aCellToInsert);

					// There is nothing left to insert
					result.setInsertionLeft(null);
					break;
				case 32:
					// Pushed out
					result.addPushedOut(aTimeCell);

					// To insert into the graph
					result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));

					// Left part of the TimeCell to insert
					result.setInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
					break;
				}
			}
		}
        return result;
    }


    
    @Override
    public String toString(){
        return "start: " + getStart() + "\n" +
                "end:"  + getEnd() + "\n" +
                "executor: " + getExecutor().getName() +
                " creator: " + getCreator().getName();
    }
    /**
     * There are only 9 types of overlapping is available, since there are no
     * zero length TimeCells.
     *
     * @param aTimeCell
     * @return
     */
    public int getOverlappingType(TimeCell aTimeCell) {
        int startType = 30;
        int endType = 3;

        if (getStart().isBefore(aTimeCell.getStart())) {
            startType = 10;
        } else if (getStart().isAfter(aTimeCell.getStart())) {
            startType = 20;
        }

        if (getEnd().isBefore(aTimeCell.getEnd())) {
            endType = 1;
        } else if (getEnd().isAfter(aTimeCell.getEnd())) {
            startType = 2;
        }

        return startType + endType;
    }

    //TODO move into the TimeManager
    /**
     * Extracts overlapping cells from listOfTimeCells
     * does not matter who is executor. 
     * @param listOfTimeCells - list from which removes overlapping {@code TimeCell}s.
     * @return {@code TimeCell}s list overlapping with current the {@code TimeCell}
     * sorted by start time.
     */
    public List<TimeCell> getOverlappingTimeCells(List<TimeCell> listOfTimeCells) {
        List<TimeCell> result = new ArrayList<>();

        for (TimeCell timeCell : listOfTimeCells) {
            if (isOverlapping(timeCell)) {
                result.add(timeCell);
            }
        }
        listOfTimeCells.removeAll(result);
        result.sort(null);
        return result;
    }
    
    /**
     * Extracts overlapping {@code TimeCell}s from listOfTimeCells
     * for the given executor. 
     * @param listOfTimeCells - list from which removes overlapping {@code TimeCell}s.
     * @param anExecutor - 
     * {@code Worker} must fit {@code Worker} of {@code TimeCell} in listOfTimeCells to overlap.
     * @return {@code TimeCell}s list overlapping with current the {@code TimeCell}
     * sorted by start time.
     */
    public List<TimeCell> getOverlappingTimeCells(
    		List<TimeCell> listOfTimeCells,
    		Worker anExecutor) {
        List<TimeCell> result = new ArrayList<>();

        for (TimeCell timeCell : listOfTimeCells) {
            if (isOverlapping(timeCell) &&
            		timeCell.getExecutor().equals(anExecutor)) {
                result.add(timeCell);
            }
        }
        listOfTimeCells.removeAll(result);
        result.sort(null);
        return result;
    }
    
    /**
     * Checks weather the current {@code TimeCell} is overlapping with the given {@code TimeCell}.
     * @param aTimeCell a TimeCell to check for overlapping.
     * @return {@code true} if {@code aTimeCell} is overlapping and {@code false} if it's not.
     */
    public boolean isOverlapping(TimeCell aTimeCell) {
        return !((aTimeCell.getStart().isBefore(getStart().plusNanos(1))
                && aTimeCell.getEnd().isBefore(getStart().plusNanos(1)))
                || (aTimeCell.getStart().isAfter(getEnd())
                && aTimeCell.getEnd().isAfter(getEnd())));
    }

    /**
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return the creationTime
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return the creator
     */
    public Person getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Person creator) {
        this.creator = creator;
    }

    /**
     * @return the executor
     */
    public Worker getExecutor() {
        return executor;
    }

    /**
     * @param executor the executor to set
     */
    public void setExecutor(Worker executor) {
        this.executor = executor;
    }

    /**
     * @return the typeOfWork
     */
    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    /**
     * @param typeOfWork the typeOfWork to set
     */
    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    @Override
    public int compareTo(Object o) {
        return start.compareTo(((TimeCell) o).start);
    }

}

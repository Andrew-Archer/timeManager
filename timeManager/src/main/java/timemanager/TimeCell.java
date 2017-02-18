package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCell {

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
                                            ZeroLengthException{
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
                                        ZeroLengthException{
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
                                        ZeroLengthException{
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
                                            ZeroLengthException{
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
        
        if (end.isBefore(start)){
            throw new EndBeforeStartException();
        }else if (end.equals(start)){
            throw new ZeroLengthException(); 
        }else{
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
     * This method should return
     * 1) List of TimeCell when workers wont to work.
     * 2) List of TimeCell pushed from graph Of Work/
     * 3) TimeCell left and not inserted in graph of work
     * But it's better to take a graph, than pull overlapping cells,
     * and process it.
	 * @throws Exception 
     */
    public TimeCellSpliterationResult splitTimeCells(
    		List<TimeCell> graphToInsertTo,
    		TimeCell aCellToInsert,
    		PeriodValidator aValidator) throws
    											Exception {
    	
        //Get overlapping with the aCellToInsert cells from the graphToInsertTo.
    	//TODO Don't forget to delete aCellToInsert. after copying into the TimeManager
    	List<TimeCell> overlappingOfGraphAndToInsertCell = aCellToInsert.getOverlappingTimeCells(aCellToInsert, graphToInsertTo);
    	
    	//Removing from the graph overlapping cells.
        graphToInsertTo.removeAll(overlappingOfGraphAndToInsertCell);
        
        //Here we put splitted parts to return.
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();
        
        //Initializing left insertion with aCellToInsert
        result.addInsertionLeft(aCellToInsert);
        
        //Fills up result
        for (TimeCell aTimeCell : overlappingOfGraphAndToInsertCell){
        	//Removes aTimeCell to make the overlappingOfGraphAndToInsertCell suitable as argument for recursive
        	//call of the splitTimeCells().
        	overlappingOfGraphAndToInsertCell.remove(aTimeCell);
        	
        	switch (aCellToInsert.getOverlappingType(aTimeCell)) {
            	case 11:
            		//Pushed out
            		result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
            		result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
            		
            		//Left part of the TimeCell to insert
            		result.rewriteInsertionLeft(new TimeCell(aCellToInsert, aTimeCell.getStart()));
            		break;
            	case 13:
            		//Pushed out
            		result.addPushedOut(aTimeCell);
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aTimeCell.getStart(), aCellToInsert));
            		
            		//Left part of the TimeCell to insert
            		result.rewriteInsertionLeft(new TimeCell(aCellToInsert, aTimeCell.getStart()));
            		break;
            	case 12:
            		//Pushed out
            		result.addPushedOut(aTimeCell);
                
            		//To insert into the graph
            		result.addToInsert(new TimeCell(
            												aTimeCell.getStart(),
            												aTimeCell.getEnd(),
            												aCellToInsert));
            		
            		//Recursive call should go here
            		//Recursion won't work.
            		result.addAll(splitTimeCells(
            				overlappingOfGraphAndToInsertCell,
            				new TimeCell(aCellToInsert, aTimeCell.getStart()),
            				aValidator));
            		
            		result.rewriteInsertionLeft(new TimeCell(aTimeCell.getEnd(),aCellToInsert));
            		throw new Exception("Case 12 check the recursion.");
            		//break;
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
            	
            		//Since nothing to insert is left
            		//May be we should insert overlappingOfGraphAndToInsertCell into timeCellToInsertParts
            		//We can't return since we have added pushedOutOfGraphTimeCells
            		throw new Exception("Case 21 we need to go out of for loop.");
            		//return timeCellToInsertParts;
            	case 23:
            		
            		//Pushed out
            		result.addPushedOut(new TimeCell(
            													aCellToInsert.getStart(),
            													aCellToInsert.getEnd(),
            													aTimeCell));
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
            		result.addToInsert(aCellToInsert);
            		//Since nothing to insert is left
            		//May be we should insert overlappingOfGraphAndToInsertCell into timeCellToInsertParts
            		//We can't return since we have added pushedOutOfGraphTimeCells
            		throw new Exception("Case 23 we need to go out of for loop.");
            		//return timeCellToInsertParts;
            	case 22:
            		//Pushed out
            		result.addPushedOut(new TimeCell(aCellToInsert.getStart(), aTimeCell));
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aTimeCell, aCellToInsert.getStart()));
            		result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));
            		
            		//Left part of the TimeCell to insert
            		result.rewriteInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
            		break;
            	case 31:
            		//Pushed out
            		result.addPushedOut(new TimeCell(aTimeCell, aCellToInsert.getEnd()));
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aCellToInsert.getEnd(), aTimeCell));
            		result.addToInsert(aCellToInsert);
            		//Since nothing to insert is left
            		//May be we should insert overlappingOfGraphAndToInsertCell into timeCellToInsertParts
            		//We can't return since we have added pushedOutOfGraphTimeCells
            		throw new Exception("Case 31 we need to go out of for loop.");
            		//return timeCellToInsertParts;
            	case 33:
            		//Pushed out
            		result.addPushedOut(aTimeCell);
            		
            		//To insert into the graph
            		result.addToInsert(aCellToInsert);
            		//Since nothing to insert is left
            		//May be we should insert overlappingOfGraphAndToInsertCell into timeCellToInsertParts
            		//We can't return since we have added pushedOutOfGraphTimeCells
            		throw new Exception("Case 33 we need to go out of for loop.");
            		//return timeCellToInsertParts;
            	case 32:
            		//Pushed out
            		result.addPushedOut(aTimeCell);
            		
            		//To insert into the graph
            		result.addToInsert(new TimeCell(aCellToInsert, aTimeCell.getEnd()));
            		
            		//Left part of the TimeCell to insert
            		result.rewriteInsertionLeft(new TimeCell(aTimeCell.getEnd(), aCellToInsert));
            		break;
        	}
        }
        return result;
    }
    
    /**
     * There are only 9 types of overlapping is available,
     * since there are no zero length TimeCells.
     * @param aTimeCell
     * @return 
     */
    
    private int getOverlappingType(TimeCell aTimeCell){
        int startType = 30;
        int endType = 3;
        
        if (getStart().isBefore(aTimeCell.getStart())){
            startType = 10;
        }else if (getStart().isAfter(aTimeCell.getStart())){
            startType = 20;
        }
                
        if (getEnd().isBefore(aTimeCell.getEnd())){
            endType = 1;
        }else if (getEnd().isAfter(aTimeCell.getEnd())){
            startType = 2;
        }
        
        return startType + endType;
    }
    
    //TODO move into the TimeManager
    public List<TimeCell> getOverlappingTimeCells(TimeCell aTimeCell, List<TimeCell> listOfTimeCells){
        List<TimeCell> result = new ArrayList<>();
        
        for (TimeCell timeCell : listOfTimeCells){
            if (aTimeCell.isOverlapping(timeCell)){
               result.add(timeCell) ;
            }
        }
        
        return result;
    }
    
    public boolean isOverlapping(TimeCell aTimeCell){
    	if (
    			(aTimeCell.getStart().isBefore(getStart().plusNanos(1)) &&
    			aTimeCell.getEnd().isBefore(getStart().plusNanos(1)))
    			||
    			(aTimeCell.getStart().isAfter(getEnd()) &&
    			aTimeCell.getEnd().isAfter(getEnd()))
    					
    		){
    		return false;    		
    	}else{
    		return true;
    	}
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


}

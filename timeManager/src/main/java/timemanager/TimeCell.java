package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCell implements Cloneable {

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime creationTime;
    
    public TimeCell(
            TimeCell original,
            LocalDateTime newEnd) throws 
                                            EndBeforeStartException,
                                            ZeroLengthException{
        this(original.getStart(), newEnd, original.getCreationTime());
    }
    
    public TimeCell(
            LocalDateTime newStart,
            TimeCell original) throws
            EndBeforeStartException,
            ZeroLengthException {
        this(newStart, original.getEnd(), original.getCreationTime());
    }

    public TimeCell(
            LocalDateTime start,
            LocalDateTime end) throws 
                                        EndBeforeStartException,
                                        ZeroLengthException{
        this(start, end, LocalDateTime.now());
    }
    
    @Override
    public Object clone() throws
                                    CloneNotSupportedException{
        TimeCell clone = (TimeCell) super.clone();
        
        clone.setStart(getStart());
        clone.setEnd(getEnd());
        clone.setStart(getCreationTime());

        return clone;
    }

    public TimeCell(TimeCell aTimeCell) throws 
                                            EndBeforeStartException,
                                            ZeroLengthException{
        this(
                aTimeCell.getStart(),
                aTimeCell.getEnd(),
                aTimeCell.getCreationTime());
    }
    public TimeCell(
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime creationTime) throws
                                                EndBeforeStartException,
                                                ZeroLengthException {
        
        if (end.isBefore(start)){
            throw new EndBeforeStartException();
        }else if (end.equals(start)){
            throw new ZeroLengthException(); 
        }else{
            this.start = start;
            this.end = end;
            this.creationTime = creationTime;
        }
    }
    
    @Deprecated
      public void splitTimeCells(TimeCell cellToSplit) throws
                                                        EndBeforeStartException,
                                                        ZeroLengthException {
        List<TimeCell> splittedCell = new ArrayList<>();
        List<TimeCell> thisSplittedCell = new ArrayList<>();

        switch (getOverlapingType(cellToSplit)) {
            case 11:
                splittedCell.add(new TimeCell(cellToSplit, getEnd()));
                splittedCell.add(new TimeCell(getEnd(), cellToSplit));
                
                thisSplittedCell.add(new TimeCell(this, cellToSplit.getStart()));
                thisSplittedCell.add(new TimeCell(cellToSplit.getStart(), this));
                break;
            case 13:
                splittedCell.add(new TimeCell(cellToSplit));
                
                thisSplittedCell.add(new TimeCell(this, cellToSplit.getStart()));
                thisSplittedCell.add(new TimeCell(cellToSplit.getStart(), this));
                break;
            case 12:
                splittedCell.add(new TimeCell(cellToSplit));
                
                thisSplittedCell.add(new TimeCell(this, cellToSplit.getStart()));
                thisSplittedCell.add(new TimeCell(
                                                    cellToSplit.getStart(),
                                                    cellToSplit.getEnd(),
                                                    getCreationTime()));
                thisSplittedCell.add(new TimeCell(cellToSplit.getEnd(),this));
                break;
            case 21:
            	splittedCell.add(new TimeCell(cellToSplit, getStart()));
            	splittedCell.add(new TimeCell(
            									getStart(),
            									getEnd(),
            									cellToSplit.getCreationTime()));
            	splittedCell.add(new TimeCell(getEnd(), cellToSplit));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 23:
            	splittedCell.add(new TimeCell(cellToSplit, getStart()));
            	splittedCell.add(new TimeCell(
            									getStart(),
            									getEnd(),
            									cellToSplit.getCreationTime()));
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 22:
            	splittedCell.add(new TimeCell(cellToSplit, getStart()));
            	splittedCell.add(new TimeCell(getStart(), cellToSplit));
            	thisSplittedCell.add(new TimeCell(
            										getStart(),
            										cellToSplit.getEnd(),
            										getCreationTime()));
            	thisSplittedCell.add(new TimeCell(cellToSplit.getEnd(), this));
                break;
            case 31:
            	splittedCell.add(new TimeCell(cellToSplit, getEnd()));
            	splittedCell.add(new TimeCell(getEnd(), cellToSplit));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 33:
            	splittedCell.add(new TimeCell(cellToSplit));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 32:
            	splittedCell.add(new TimeCell(cellToSplit));
            	
            	thisSplittedCell.add(new TimeCell(this, cellToSplit.getEnd()));
            	thisSplittedCell.add(new TimeCell(cellToSplit.getEnd(), this));
                break;
        }
    }
    
    /**
     * There are only 9 types of overlaping is available,
     * since there are no zero length TimeCells.
     * @param aTimeCell
     * @return 
     */
    
    public int getOverlapingType(TimeCell aTimeCell){
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
    public List<TimeCell> getOverlapingTimeCells(List<TimeCell> listOfTimeCells){
        List<TimeCell> result = new ArrayList<>();
        
        for (TimeCell timeCell : listOfTimeCells){
            if (isOverlaping(timeCell)){
               result.add(timeCell) ;
            }
        }
        
        return result;
    }

    public Boolean fit(TimeCell aTimeCell) {
        return getStart().equals(aTimeCell.getStart()) && getEnd().equals(aTimeCell.getEnd());
    }
    
    public Boolean isIncludedIn(LocalDateTime start, LocalDateTime end) {
        return getStart().isAfter(start) && getEnd().isBefore(end);
    }
    
        public boolean isOverlaping(TimeCell timeCell) {
        return (
                this.getStart().plusNanos(1).isAfter(timeCell.getEnd())
                || 
                this.getEnd().minusNanos(1).isBefore(timeCell.getStart())
                );
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

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
}

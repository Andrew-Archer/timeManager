package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCell {

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime creationTime;

    public TimeCell(
            LocalDateTime start,
            LocalDateTime end) throws 
                                        EndBeforeStartException,
                                        ZeroLengthException{
        this(start, end, LocalDateTime.now());
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

    public boolean isOverlaping(TimeCell timeCell) {
        return (
                this.getStart().plusNanos(1).isAfter(timeCell.getEnd())
                || 
                this.getEnd().minusNanos(1).isBefore(timeCell.getStart())
                );
    }
}

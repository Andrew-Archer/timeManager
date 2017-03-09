package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import timemanager.actors.Manager;

import timemanager.actors.Person;
import timemanager.actors.Worker;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

public class TimeCell implements Comparable<TimeCell>, Cloneable {

    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime creationTime;
    private Manager creator;
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

    @Override
    protected TimeCell clone() throws CloneNotSupportedException {
        TimeCell clone = (TimeCell)super.clone();
        clone.start = start;
        clone.end = end;
        clone.creationTime = creationTime;
        clone.creator = creator.clone();
        clone.executor = executor.clone();
        clone.typeOfWork = typeOfWork;
        return clone;
    }
    
    

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((typeOfWork == null) ? 0 : typeOfWork.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeCell other = (TimeCell) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (executor == null) {
			if (other.executor != null)
				return false;
		} else if (!executor.equals(other.executor))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (typeOfWork != other.typeOfWork)
			return false;
		return true;
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
            Manager aCreator) throws
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
            Manager aCreator,
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
     * 
     * @param aStart
     * @param anEnd
     * @param aCreationTime
     * @param aCreator
     * @param anExecutor
     * @param aTypeOfWork
     * @throws timemanager.exceptions.EndBeforeStartException
     * @throws timemanager.exceptions.ZeroLengthException
     */
    public TimeCell(
            LocalDateTime aStart,
            LocalDateTime anEnd,
            LocalDateTime aCreationTime,
            Manager aCreator,
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

    public boolean isAssigned(){
        return !(executor == null);
    }
    
    public boolean isNotAssigned(){
        return executor == null;
    }
    
    @Override
    public String toString(){
        return "start: " + getStart() + "\n" +
                "end:   "  + getEnd() + "\n" +
                "creationTime: " + getCreationTime() + "\n" +
                "executor: " + (getExecutor()==null?"not assigned":getExecutor().getName()) +
                " creator: " + getCreator().getName() + "\n";
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
            endType = 2;
        }

        return startType + endType;
    }

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
     * Checks weather the current {@code TimeCell} is overlapping with the given {@code TimeCell}.
     * @param aTimeCell a TimeCell to check for overlapping.
     * @return {@code true} if {@code aTimeCell} is overlapping and {@code false} if it's not.
     */
    public boolean isOverlapping(TimeCell aTimeCell) {
        return !(
                    (
                        aTimeCell.getStart().isBefore(getStart().plusNanos(1))
                        && 
                        aTimeCell.getEnd().isBefore(getStart().plusNanos(1))
                    )
                ||
                    (
                        aTimeCell.getStart().isAfter(getEnd().minusNanos(1))
                        &&
                        aTimeCell.getEnd().isAfter(getEnd().minusNanos(1))
                    )
                );
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
    public Manager getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Manager creator) {
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
    public int compareTo(TimeCell o) {
        return start.compareTo(o.start);
    }

}
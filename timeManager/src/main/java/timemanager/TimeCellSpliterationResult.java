package timemanager;

import java.util.ArrayList;
import java.util.List;

public class TimeCellSpliterationResult {

    /**
     * @return the insertionLeft
     */
    public TimeCell getInsertionLeft() {
        return insertionLeft;
    }

    /**
     * @param insertionLeft the insertionLeft to set
     */
    public void setInsertionLeft(TimeCell insertionLeft) {
        this.insertionLeft = insertionLeft;
    }
	
	public TimeCellSpliterationResult(){
		super();
		toInsert = new ArrayList<TimeCell>();
		pushedOut = new ArrayList<TimeCell>();
		insertionLeft = null;
	}
	
	private List<TimeCell> toInsert;
	private List<TimeCell> pushedOut;
	private TimeCell insertionLeft;
	
	public void addToInsert(TimeCell aTimeCell){
		toInsert.add(aTimeCell);
	}
	
	public void addToInsert(ArrayList<TimeCell> aTimeCell){
		toInsert.addAll(aTimeCell);
	}
	
	public void removeToInsert(TimeCell aTimeCell){
		toInsert.remove(aTimeCell);
	}
	
	public void removeToInsert(ArrayList<TimeCell> aTimeCell){
		toInsert.removeAll(aTimeCell);
	}
	
	public void addPushedOut(TimeCell aTimeCell){
		pushedOut.add(aTimeCell);
	}
	
	public void addPushedOut(ArrayList<TimeCell> aTimeCell){
		pushedOut.addAll(aTimeCell);
	}
	
	public void removePushedOut(TimeCell aTimeCell){
		pushedOut.remove(aTimeCell);
	}
	
	/**
	 * Copies insertionLeft to toInsert,
	 * and sets insertionLeft to null
	 */
	public void pack(){
		addToInsert(getInsertionLeft());
		setInsertionLeft(null);		
	}
	
	public void removePushedOut(ArrayList<TimeCell> aTimeCell){
		pushedOut.removeAll(aTimeCell);
	}
        
	/**
	 * @return the toInsert
	 */
	public List<TimeCell> getToInsert() {
		return toInsert;
	}
	/**
	 * @param toInsert the toInsert to set
	 */
	public void setToInsert(List<TimeCell> toInsert) {
		this.toInsert = toInsert;
	}
	/**
	 * @return the pushedOut
	 */
	public List<TimeCell> getPushedOut() {
		return pushedOut;
	}
	/**
	 * @param pushedOut the pushedOut to set
	 */
	public void setPushedOut(List<TimeCell> pushedOut) {
		this.pushedOut = pushedOut;
	}
}

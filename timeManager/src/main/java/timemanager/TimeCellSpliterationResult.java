package timemanager;

import java.util.ArrayList;
import java.util.List;

public class TimeCellSpliterationResult {
	
	public TimeCellSpliterationResult(){
		super();
		toInsert = new ArrayList<TimeCell>();
		pushedOut = new ArrayList<TimeCell>();
		insertionLeft = new ArrayList<TimeCell>();
	}
	
	private List<TimeCell> toInsert;
	private List<TimeCell> pushedOut;
	private List<TimeCell> insertionLeft;
	
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
	
	public void removePushedOut(ArrayList<TimeCell> aTimeCell){
		pushedOut.removeAll(aTimeCell);
	}
	
	public void addInsertionLeft(TimeCell aTimeCell){
		insertionLeft.add(aTimeCell);
	}
	
	public void addInsertionLeft(ArrayList<TimeCell> aTimeCell){
		insertionLeft.addAll(aTimeCell);
	}
	
	public void removeInsertionLeft(TimeCell aTimeCell){
		insertionLeft.remove(aTimeCell);
	}
	
	public void removeInsertionLeft(ArrayList<TimeCell> aTimeCell){
		insertionLeft.removeAll(aTimeCell);
	}
	
	public void rewriteInsertionLeft(TimeCell aTimeCell){
		insertionLeft.clear();
		insertionLeft.add(aTimeCell);
	}
	
	public void rewriteInsertionLeft(List<TimeCell> aTimeCell){
		insertionLeft.clear();
		insertionLeft.addAll(aTimeCell);
	}
	
        public boolean isNotEnough(){
            return !insertionLeft.isEmpty();
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
	/**
	 * @return the insertionLeft
	 */
	public List<TimeCell> getInsertionLeft() {
		return insertionLeft;
	}
	/**
	 * @param insertionLeft the insertionLeft to set
	 */
	public void setInsertionLeft(List<TimeCell> insertionLeft) {
		this.insertionLeft = insertionLeft;
	}



}

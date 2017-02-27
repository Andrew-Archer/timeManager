package timemanager;

import java.util.ArrayList;
import java.util.List;

import timemanager.exceptions.UnimplementedMethod;

/**
 *
 * @author razan
 */
public class TimeCellSpliterationResult{

    public boolean equals(TimeCellSpliterationResult result){
        return toInsert.equals(result.getToInsert()) &&
                pushedOut.equals(result.getPushedOut()) &&
                insertionLeft.equals(result.getInsertionLeftList());
    }
    
    public TimeCellSpliterationResult(){
        toInsert = new ArrayList<>();
        pushedOut = new ArrayList<>();;
        insertionLeft = new ArrayList<>();
    }
    /**
     * @return last element of insertionLeft list as {@code TimeCell}.
     */
    public TimeCell getInsertionLeft() {
        return insertionLeft.get(insertionLeft.size() - 1);
    }

    /**
     * @param insertionLeft the insertionLeft to set
     */
    public void setInsertionLeft(TimeCell insertionLeft) {
    	this.insertionLeft.clear();
        this.insertionLeft.add(insertionLeft);
    }

    public TimeCellSpliterationResult(
            List<TimeCell> toInsert,
            List<TimeCell> pushedOut,
            List<TimeCell> insertionLeft) {
        super();
        this.toInsert = toInsert;
        this.pushedOut = pushedOut;
        this.insertionLeft = insertionLeft;
    }
    
    public void addToInsertionLeft(TimeCell aTimeCell){
    	insertionLeft.add(aTimeCell);
    }
    
    public List<TimeCell> getInsertionLeftList(){
    	return insertionLeft;
    }

    private List<TimeCell> toInsert;
    private List<TimeCell> pushedOut;
    private List<TimeCell> insertionLeft;

    public void addToInsert(TimeCell aTimeCell) {
        toInsert.add(aTimeCell);
    }

    public void addToInsert(ArrayList<TimeCell> aTimeCell) {
        toInsert.addAll(aTimeCell);
    }

    public void removeToInsert(TimeCell aTimeCell) {
        toInsert.remove(aTimeCell);
    }

    public void removeToInsert(ArrayList<TimeCell> aTimeCell) {
        toInsert.removeAll(aTimeCell);
    }

    public void addPushedOut(TimeCell aTimeCell) {
        pushedOut.add(aTimeCell);
    }
    
    public List<TimeCell> packItToList(List<TimeCell> toInsert){
    	throw new UnimplementedMethod();
    }
    
    public void addPushedOut(ArrayList<TimeCell> aTimeCell) {
        pushedOut.addAll(aTimeCell);
    }

    public void removePushedOut(TimeCell aTimeCell) {
        pushedOut.remove(aTimeCell);
    }

    /**
     * Copies insertionLeft to toInsert, and sets insertionLeft to null
     */
    public void pack() {
        addToInsert(getInsertionLeft());
        insertionLeft.remove(insertionLeft.size() - 1);
    }

    public void removePushedOut(ArrayList<TimeCell> aTimeCell) {
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

    public void add(TimeCellSpliterationResult result) {
        pushedOut.addAll(result.getPushedOut());
        toInsert.addAll(result.getToInsert());
        insertionLeft.addAll(result.getInsertionLeftList());
    }
}

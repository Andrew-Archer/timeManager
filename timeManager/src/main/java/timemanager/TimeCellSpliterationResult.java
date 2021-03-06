package timemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import timemanager.exceptions.UnimplementedMethod;

/**
 *
 * @author razan
 */
public class TimeCellSpliterationResult{

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.toInsert);
        hash = 41 * hash + Objects.hashCode(this.pushedOut);
        hash = 41 * hash + Objects.hashCode(this.insertionLeft);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeCellSpliterationResult other = (TimeCellSpliterationResult) obj;
        if (!Objects.equals(this.toInsert, other.toInsert)) {
            return false;
        }
        if (!Objects.equals(this.pushedOut, other.pushedOut)) {
            return false;
        }
        if (!Objects.equals(this.insertionLeft, other.insertionLeft)) {
            return false;
        }
        return true;
    }

   


    
    public TimeCellSpliterationResult(){
        toInsert = new ArrayList<TimeCell>();
        pushedOut = new ArrayList<TimeCell>();;
        insertionLeft = new ArrayList<TimeCell>();
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

    @Override
    public String toString(){
        String result = "SpliterationResult\n";
        result = result + "pushedOut\n";
        for (TimeCell timeCell :pushedOut){
            result = result + timeCell;
        }
        result = result + "toInsert\n";
        for (TimeCell timeCell :toInsert){
            result = result + timeCell;
        }
        result = result + "pushedOut\n";
        for (TimeCell timeCell :pushedOut){
            result = result + timeCell;
        }
        
        return result;
    }
    
    public void add(TimeCellSpliterationResult result) {
        pushedOut.addAll(result.getPushedOut());
        toInsert.addAll(result.getToInsert());
        insertionLeft.addAll(result.getInsertionLeftList());
    }
}

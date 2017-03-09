package timemanager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import timemanager.actors.Person;
import timemanager.actors.Worker;
import timemanager.cellSplitLogic.CellSplitLogic;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
import timemanager.graphGenerator.GraphGenerator;
import timemanager.graphGenerator.TestGraphGenerator;
import timemanager.validation.PeriodValidator;

public class TimeManager {

	// For example one hour
	private final int minimunPeriodOfWorkInHours = 1;
	// For example hours in a day
	private final int subItervalLength = minimunPeriodOfWorkInHours * 8;
	private List<TimeCell> timeCellsAvailable = new ArrayList<>();
	private List<TimeCell> workRequestTimeCell = new ArrayList<>();
	private List<TimeCell> actualWorkGraph = new ArrayList<>();

	/**
	 * @return the actualWorkGraph
	 */
	public List<TimeCell> getActualWorkGraph() {
		return actualWorkGraph;
	}

	/**
	 * @param actualWorkGraph the actualWorkGraph to set
	 */
	public void setActualWorkGraph(List<TimeCell> actualWorkGraph) {
		this.actualWorkGraph = actualWorkGraph;
	}

	
            /**
     * Extracts {@code TimeCell}s with given {@code Worker} from listOfTimeCells.
     * @param listOfTimeCells - list from which removes overlapping {@code TimeCell}s.
     * @param anExecutor 
     * {@code Worker} must fit {@code Worker} of {@code TimeCell} in listOfTimeCells.
     * @return {@code TimeCell}s list for given {@code Worker} or nor assigned
     * sorted by start time.
     */
    public List<TimeCell> getTimeCellsAssignedTo(Worker anExecutor) {
        List<TimeCell> result = new ArrayList<>();
        //time cell executor can be null so you may get null pointer exception
        for (TimeCell timeCell : actualWorkGraph) {
            if (timeCell.isNotAssigned()) {
                result.add(timeCell);
            }else if(timeCell.getExecutor().equals(anExecutor)){
                result.add(timeCell);
            }
        }
        actualWorkGraph.removeAll(result);
        result.sort(null);
        return result;
    }
        
	/**
	 *
	 * @param actualWorkGraph
	 * @param aCellToInsert
	 * @param aValidator
	 * @param splitLogic
	 * @return
	 * @throws Exception
	 */
	public void splitTimeCells(
			TimeCell aCellToInsert,
			PeriodValidator aValidator,
			CellSplitLogic splitLogic,
			CellSplitLogic splitLogicForPushed) throws Exception {

		// Get overlapping with the aCellToInsert cells from the
		// actualWorkGraph.
		List<TimeCell> overlappingOfGraphAndToInsertCell;
		overlappingOfGraphAndToInsertCell = aCellToInsert.getOverlappingTimeCells(actualWorkGraph);
                //Test code
                System.out.println("Overlapping cells=======================");
                for (TimeCell timeCell: overlappingOfGraphAndToInsertCell){
                    System.out.println(timeCell);
                }
                System.out.println("Overlapping cells=======================");

		// Here we put splitted parts to return.
		TimeCellSpliterationResult result = new TimeCellSpliterationResult();

		// Initializing insertionLeft with aCellToInsert
		result.setInsertionLeft(aCellToInsert);

		// Inserting TimeCell to insert
		for (TimeCell aTimeCell : overlappingOfGraphAndToInsertCell) {
			result.add(splitLogic.split(result.getInsertionLeft(), aTimeCell));
		}
                
		// Inserting pushed out TimeCells
		// Get TimeCell with the same name as.
                if (!result.getPushedOut().isEmpty()){
                    List<TimeCell> timeCellsListForGivenWorker;
                    timeCellsListForGivenWorker = getTimeCellsAssignedTo(aCellToInsert.getExecutor());
                    for (TimeCell pushedOut : result.getPushedOut()) {
                            for (TimeCell assinedForWorker : timeCellsListForGivenWorker) {
				result.add(splitLogicForPushed.split(pushedOut, assinedForWorker));
                            }
                    }
                }
		actualWorkGraph.addAll(result.getToInsert());
                actualWorkGraph.sort(null);
	}

    /**
     *
     * @param graphGenerator
     * @throws timemanager.exceptions.ZeroLengthException
     * @throws timemanager.exceptions.EndBeforeStartException
     */
    public void generateFairGraphOfWork(GraphGenerator graphGenerator) throws
            ZeroLengthException,
            EndBeforeStartException {
        actualWorkGraph = graphGenerator.generate();
    }
        
}

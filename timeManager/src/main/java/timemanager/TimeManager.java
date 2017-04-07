package timemanager;

import java.util.ArrayList;
import java.util.List;
import timemanager.actors.Person;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
import timemanager.graphGenerator.GraphGenerator;
import timemanager.validation.PeriodValidator;
import timemanager.cellSplitLogic.Logic;

public class TimeManager {

	// For example one hour
	private final int minimunPeriodOfWorkInHours = 1;
	// For example hours in a day
	private final int subItervalLength = minimunPeriodOfWorkInHours * 8;
	private final List<TimeCell> timeCellsAvailable = new ArrayList<>();
	private final List<TimeCell> workRequestTimeCell = new ArrayList<>();
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
     * @param anExecutor 
     * {@code Worker} must fit {@code Worker} of {@code TimeCell} in listOfTimeCells.
     * @return {@code TimeCell}s list for given {@code Worker} or nor assigned
     * sorted by start time.
     */
    public List<TimeCell> getTimeCellsAssignedTo(Person anExecutor) {
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
         * @param request
	 * @param aValidator
	 * @param splitLogic
	 * @throws Exception
	 */
	public void splitTimeCells(
			TimeCell request,
			PeriodValidator aValidator,
			Logic splitLogic) throws Exception {

	
		actualWorkGraph.addAll(splitLogic.split(
                            request,
                            actualWorkGraph));
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

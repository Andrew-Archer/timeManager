package timemanager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import timemanager.actors.Person;
import timemanager.cellSplitLogic.CellSplitLogic;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
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
	 *Test implementation to get fair graph of work.
	 * @param start the beginning of a work interval.
	 * @param end the finish of a work interval.
	 * @param creator that one how creates a work (manager).
	 * @throws timemanager.exceptions.ZeroLengthException
	 */
	public void generateFairGraphOfWork(
			LocalDateTime start,
			LocalDateTime end,
			long minimunPeriodOfWorkInHours,
			Person creator)
					throws ZeroLengthException,
						EndBeforeStartException{
		// To hold fair graph of work
		List<TimeCell> fairGraphOfWork = new ArrayList<>();
		
		long numberOfIntervalsToGenerate = Duration.between(start, end).toHours()/minimunPeriodOfWorkInHours;

		// Fills up fair graph of work
		for (long i = 0; i < numberOfIntervalsToGenerate; i += minimunPeriodOfWorkInHours) {
			try {
				fairGraphOfWork.add(new TimeCell(start.plusHours(i), start.plusHours(i + minimunPeriodOfWorkInHours), LocalDateTime.now(),
						creator, null, TypeOfWork.ANY));
			} catch (EndBeforeStartException ex) {
				Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ZeroLengthException ex) {
				Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		actualWorkGraph = fairGraphOfWork;
	}

	/**
	 *
	 * @param graphToInsertTo
	 * @param aCellToInsert
	 * @param aValidator
	 * @param splitLogic
	 * @return
	 * @throws Exception
	 */
	public List<TimeCell> splitTimeCells(
			List<TimeCell> graphToInsertTo,
			TimeCell aCellToInsert,
			PeriodValidator aValidator,
			CellSplitLogic splitLogic,
			CellSplitLogic splitLogicForPushed) throws Exception {

		// Get overlapping with the aCellToInsert cells from the
		// graphToInsertTo.
		List<TimeCell> overlappingOfGraphAndToInsertCell;
		overlappingOfGraphAndToInsertCell = aCellToInsert.getOverlappingTimeCells(graphToInsertTo);

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
		List<TimeCell> timeCellsListForGivenWorker;
		timeCellsListForGivenWorker = aCellToInsert.getTimeCellsAssignedTo(graphToInsertTo,
				aCellToInsert.getExecutor());
		for (TimeCell pushedOut : result.getPushedOut()) {
			for (TimeCell assinedForWorker : timeCellsListForGivenWorker) {
				result.add(splitLogicForPushed.split(pushedOut, assinedForWorker));
			}
		}
		return result.getToInsert();
	}
}

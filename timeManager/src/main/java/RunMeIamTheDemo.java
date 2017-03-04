import java.time.LocalDateTime;

import timemanager.TimeCell;
import timemanager.TimeManager;
import timemanager.TypeOfWork;
import timemanager.actors.Manager;
import timemanager.actors.Worker;
import timemanager.cellSplitLogic.CellSplitLogicForPushedOut;
import timemanager.cellSplitLogic.CellSplitLogicForWorkRequest;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;
import timemanager.graphGenerator.TestGraphGenerator;

public class RunMeIamTheDemo {

	public static void main(String[] args) throws
		Exception {
		TimeManager timeManager = new TimeManager();
		
		timeManager.generateFairGraphOfWork(new TestGraphGenerator(
				LocalDateTime.now(),
				LocalDateTime.now().plusHours(8),
				1,
				new Manager("Jack")));
		
		System.out.println("Generated fair graph of work =========================");
		for(TimeCell timeCell : timeManager.getActualWorkGraph()){
                        System.out.println("____________________________________");
			System.out.println(timeCell);
		}
		System.out.println("Generated fair graph of work =========================\n");
		
		TimeCell timeCellToInsert = new TimeCell(
				timeManager.getActualWorkGraph().get(1).getStart(),
				timeManager.getActualWorkGraph().get(5).getEnd(),
				LocalDateTime.now(),
				new Manager("Sam"),
				new Worker("Jane", TypeOfWork.ANY),
				 TypeOfWork.ANY);
		
		timeManager.splitTimeCells(
				timeManager.getActualWorkGraph(),
				timeCellToInsert,
				null,
				new CellSplitLogicForWorkRequest(),
				new CellSplitLogicForPushedOut());
		
		System.out.println("Generated fair graph after insertion =========================");
		for(TimeCell timeCell : timeManager.getActualWorkGraph()){
			System.out.println(timeCell);
		}
		System.out.println("Generated fair graph after insertion =========================");
	}
}

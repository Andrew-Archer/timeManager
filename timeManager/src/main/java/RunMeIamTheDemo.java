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
                LocalDateTime startTime = LocalDateTime.of(1, 1, 1, 0, 0);
		
                //generates test graph
		timeManager.generateFairGraphOfWork(new TestGraphGenerator(
				startTime,
				startTime.plusHours(8),
				1,
				new Manager("Jack")));
                
                //assign time cell to the test person
		timeManager.getActualWorkGraph().get(2).setExecutor(new Worker("Karen", TypeOfWork.ANY));
                
		System.out.println("Generated fair graph of work =========================");
		for(TimeCell timeCell : timeManager.getActualWorkGraph()){
			System.out.println(timeCell);
		}
		System.out.println("Generated fair graph of work =========================\n");
		
                //Creates cell for insertion
		TimeCell timeCellToInsert = new TimeCell(
				startTime.plusHours(1),
				startTime.plusHours(3),
				LocalDateTime.now(),
				new Manager("Sam"),
				new Worker("Jane", TypeOfWork.ANY),
				 TypeOfWork.ANY);
                
                System.out.println("Cell to insert =========================\n");
                System.out.println(timeCellToInsert);
                System.out.println("Cell to insert =========================\n");
		
                //inserts cell into graph
		timeManager.splitTimeCells(
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

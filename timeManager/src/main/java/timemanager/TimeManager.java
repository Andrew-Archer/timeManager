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
import timemanager.validation.PeriodValidator;

public class TimeManager {

    //For example one hour
    private final int minimunPeriodOfWorkInHours = 1;
    //For example hours in a day
    private final int subItervalLength = minimunPeriodOfWorkInHours * 8;
    private List<TimeCell> timeCellsAvailable = new ArrayList<>();
    private List<TimeCell> workRequestTimeCell = new ArrayList<>();
    private final List<TimeCell> actualWorkGraph = new ArrayList<>();

    /**
     * Calculates List of workers who sent queries for work. Searches for
     * workers in workersTImeWishList.
     * @param start start point of search TimeCell assigned to executors.
     * @param end end point of search.
     * @return List of workers who sent queries for to work in a given period.
     */
    public List<Worker> getWorkersAvailableInPeriod(LocalDateTime start, LocalDateTime end) {
        List<Worker> workersList = new ArrayList<>();
        for (TimeCell workRequest : workRequestTimeCell) {
            if (workRequest.isIncludedIn(start.minusNanos(1), end.plusNanos(1))
                    && !workersList.contains(workRequest.getExecutor())) {
                workersList.add(workRequest.getExecutor());
            }
        }
        return workersList;
    }


    /**
     *
     *
     *
     * @param start
     * @param end
     * @param workersList
     * @param creator
     * @return
     * @throws timemanager.exceptions.ZeroLengthException
     */

    public List<TimeCell> getFairGraphOfWork(
            LocalDateTime start,
            LocalDateTime end,
            List<Worker> workersList,
            Person creator) throws ZeroLengthException {
        //To hold fair graph of work
        List<TimeCell> fairGraphOfWork = new ArrayList<>();

        //number of hours in the interval between start and end
        long numberOfHours = Duration.between(start, end).toHours();

        //Fills up fair graph of work
        for (LocalDateTime i = start; i.isBefore(end); i.plusHours(minimunPeriodOfWorkInHours)){
            for (Worker worker: workersList){
                try {
                    fairGraphOfWork.add(new TimeCell(
                            start,
                            start.plusHours(minimunPeriodOfWorkInHours),
                            LocalDateTime.now(),
                            creator,
                            null,
                            TypeOfWork.ANY));
                } catch (EndBeforeStartException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ZeroLengthException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return fairGraphOfWork;

    }

    

    public List<TimeCellOfJob> getActualGraphOfWork(

            LocalDateTime start,

            LocalDateTime end) throws

            CloneNotSupportedException{

        List<TimeCellOfJob> fairGraphOfWork = getFairGraphOfWork(start, end);

        return null;

    }



    public void addTimeCellAvailable(TimeCellOfJob newJob) {

        //TODO

    }



    public void removeTimeCellAvailable(TimeCellOfJob aJob) {



    }



    public void addTimeCellRequest(TimeCellOfWorkerTime timeCellRequest) {

        timeCellsRequest.add(timeCellRequest);

    }



    public void removeTimeCellRequest(TimeCellOfWorkerTime timeCellRequest) {



    }



    public void organizeTimeCells() {



    }*/
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
            CellSplitLogic splitLogicForPushed) throws
            Exception {

        //Get overlapping with the aCellToInsert cells from the graphToInsertTo.
        List<TimeCell> overlappingOfGraphAndToInsertCell;
        overlappingOfGraphAndToInsertCell = aCellToInsert.getOverlappingTimeCells(graphToInsertTo);

        //Here we put splitted parts to return.
        TimeCellSpliterationResult result = new TimeCellSpliterationResult();

        //Initializing insertionLeft with aCellToInsert
        result.setInsertionLeft(aCellToInsert);

        //Inserting TimeCell to insert
        for (TimeCell aTimeCell : overlappingOfGraphAndToInsertCell) {
            result.add(splitLogic.split(result.getInsertionLeft(), aTimeCell));
        }
        //Inserting pushed out TimeCells
        //Get TimeCell with the same name as.
        List<TimeCell> timeCellsListForGivenWorker;
        timeCellsListForGivenWorker = aCellToInsert.getTimeCellsAssignedTo(
                graphToInsertTo,
                aCellToInsert.getExecutor());
        for (TimeCell pushedOut : result.getPushedOut()) {
            for (TimeCell assinedForWorker : timeCellsListForGivenWorker) {
                result.add(splitLogicForPushed.split(pushedOut, assinedForWorker));
            }
        }
        return result.getToInsert();
    }
}

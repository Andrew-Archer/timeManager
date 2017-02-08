package timemanager;



import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeManager {
    //For example one hour
    private final int minimnPeriodOfWorkInHours = 1;
    //For example hours in a day
    private final int subItervalLength = minimnPeriodOfWorkInHours * 8;
    private List<TimeCellAvailable> timeCellsAvailable = new ArrayList<>();
    private List<TimeCellRequest> timeCellsRequest = new ArrayList<>();
    private List<TimeCell> actualWorkGraph = new ArrayList<>();

    /**
     * Calculates List of workers who sent queries for work. Searches for
     * workers in workersTImeWishList.
     *
     * @param start
     * @param end
     * @return List of workers who sent queries for work in a given period.
     */
    public List<Worker> getWorkersAvailableInPeriod(LocalDateTime start, LocalDateTime end) {
        List<Worker> workersList = new ArrayList<>();
        
        for (TimeCellRequest timeCellRequest : timeCellsRequest){
            if (timeCellRequest.isIncludedIn(start.minusNanos(1), end.plusNanos(1)) &&
                   !workersList.contains(timeCellRequest.getWorker())){
                workersList.add(timeCellRequest.getWorker());
            }
        }
        return workersList;
    }

    public void addTimeCellToActualWorkGraph(TimeCell aTimeCell){
        List<TimeCell> originalList = new ArrayList<>();
        List<TimeCell> listToPushIntoActualGraph = new ArrayList<>();
        if (actualWorkGraph.isEmpty()){
            actualWorkGraph = getFairGraphOfWork(LocalDateTime.MAX, LocalDateTime.MIN);
        }
        
        originalList = aTimeCell.getOverlapingTimeCells(actualWorkGraph);
        
        for (TimeCell timeCell : originalList){
            
        }
        
    }
    
    public void splitTimeCells(TimeCell modifiedCell, TimeCell insertionCell) throws EndBeforeStartException, ZeroLengthException {
        List<TimeCell> splitedModifiedCell = new ArrayList<>();
        List<TimeCell> splitedInsertionCell = new ArrayList<>();

        switch (insertionCell.getOverlapingType(modifiedCell)) {
            case 11:
                splitedModifiedCell.add(new TimeCell(
                                                    modifiedCell,
                                                    insertionCell.getEnd()
                                                    )
                                        );
                splitedModifiedCell.add(new TimeCell(
                                                    insertionCell.getEnd(),
                                                    modifiedCell));
                break;
            case 13:
                break;
            case 12:
                break;
            case 21:
                break;
            case 23:
                break;
            case 22:
                break;
            case 31:
                break;
            case 33:
                break;
            case 32:
                break;

        }
    }
    /**
     *
     * @param start
     * @param end
     * @return 
     */
    public List<TimeCell> getFairGraphOfWork(LocalDateTime start, LocalDateTime end) {
        final List<Worker> workersList = getWorkersAvailableInPeriod(start, end);
        
        //To hold fair graph of work
        List<TimeCell> fairGraphOfWork = new ArrayList<>();
        
        //number of hours in the interval between start and end
        long numberOfHours = Duration.between(start, end).toHours();

        //Fills up fair graph of work
        for (LocalDateTime i = start; i.isBefore(end); i.plusHours(minimnPeriodOfWorkInHours)){
            for (Worker worker: workersList){
                try {
                    fairGraphOfWork.add(new TimeCellRequest(
                            start,
                            start.plusHours(minimnPeriodOfWorkInHours),
                            LocalDateTime.now(),
                            worker));
                } catch (EndBeforeStartException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ZeroLengthException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fairGraphOfWork;
    }
    
    public List<TimeCell> getActualGraphOfWork(LocalDateTime start, LocalDateTime end){
        List<TimeCell> fairGraphOfWork = getFairGraphOfWork(start, end);
        return null;
    }

    public void addTimeCellAvailable(TimeCellAvailable newJob) {
        //TODO
    }

    public void removeTimeCellAvailable(TimeCellAvailable aJob) {

    }

    public void addTimeCellRequest(TimeCellRequest timeCellRequest) {
        timeCellsRequest.add(timeCellRequest);
    }

    public void removeTimeCellRequest(TimeCellRequest timeCellRequest) {

    }

    public void organizeTimeCells() {

    }
}

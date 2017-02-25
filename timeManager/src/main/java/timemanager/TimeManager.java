package timemanager;



import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    //For example one hour
    private final int minimnPeriodOfWorkInHours = 1;
    //For example hours in a day
    private final int subItervalLength = minimnPeriodOfWorkInHours * 8;
    private List<TimeCell> timeCellsAvailable = new ArrayList<>();
    private List<TimeCell> timeCellsRequest = new ArrayList<>();
    private final List<TimeCell> actualWorkGraph = new ArrayList<>();

    /**
     * Calculates List of workers who sent queries for work. Searches for
     * workers in workersTImeWishList.
     *
     * @param start
     * @param end
     * @return List of workers who sent queries for work in a given period.
     */
    /*public List<Worker> getWorkersAvailableInPeriod(LocalDateTime start, LocalDateTime end) {
        List<Worker> workersList = new ArrayList<>();
        
        for (TimeCell timeCellRequest : timeCellsRequest){
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
    
    /**
     *
     * @param start
     * @param end
     * @return 
     */
    /*public List<TimeCellOfJob> getFairGraphOfWork(LocalDateTime start, LocalDateTime end) throws CloneNotSupportedException {
        final List<Worker> workersList = getWorkersAvailableInPeriod(start, end);
        
        //To hold fair graph of work
        List<TimeCellOfJob> fairGraphOfWork = new ArrayList<>();
        
        //number of hours in the interval between start and end
        long numberOfHours = Duration.between(start, end).toHours();

        //Fills up fair graph of work
        for (LocalDateTime i = start; i.isBefore(end); i.plusHours(minimnPeriodOfWorkInHours)){
            for (Worker worker: workersList){
                try {
                    fairGraphOfWork.add(new TimeCellOfJob(
                            start,
                            start.plusHours(minimnPeriodOfWorkInHours),
                            LocalDateTime.now(),
                            null,
                            TypeOfWork.ANY,
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
}

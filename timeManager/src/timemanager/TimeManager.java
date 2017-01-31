package timemanager;



import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    //For example one hour
    private final int minimnPeriodOfWorkInHours = 1;
    //For example hours in a day
    private final int subItervalLength = minimnPeriodOfWorkInHours * 8;
    private List<TimeCellAvailable> timeCellsAvailable = new ArrayList<>();
    private List<TimeCellRequest> timeCellsRequest = new ArrayList<>();
    private List<TimeCellRequest> workersTimeActualList = new ArrayList<>();

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

    /**
     *
     * @param start
     * @param end
     * @return 
     */
    public List<TimeCellRequest> getFairGraphOfWork(LocalDateTime start, LocalDateTime end) {
        final List<Worker> workersList = getWorkersAvailableInPeriod(start, end);
        
        //To hold fair graph of work
        List<TimeCellRequest> fairGraphOfWork = new ArrayList<>();
        
        //number of hours in the interval between start and end
        long numberOfHours = Duration.between(start, end).toHours();

        //Fills up fair graph of work
        for (LocalDateTime i = start; i.isBefore(end); i.plusHours(minimnPeriodOfWorkInHours)){
            for (Worker worker: workersList){
                    fairGraphOfWork.add(new TimeCellRequest(
                        start,
                        start.plusHours(minimnPeriodOfWorkInHours),
                        LocalDateTime.now(),
                        worker));
            }
        }
        return fairGraphOfWork;
    }
    
    public List<TimeCellRequest> getActualGraphOfWork(LocalDateTime start, LocalDateTime end){
        List<TimeCellRequest> fairGraphOfWork = getFairGraphOfWork(start, end);
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

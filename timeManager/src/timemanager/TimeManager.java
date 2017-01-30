package timemanager;



import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    //For example one hour
    private final int minimnPeriodOfWork = 1;
    //For example hours in a day
    private final int subItervalLength = minimnPeriodOfWork * 8;
    private List<TimeCellLookingForWorker> jobList;
    private List<TimeCellLookingForJob> workersTimeWishList;
    private List<TimeCellLookingForJob> workersTimeActualList;

    /**
     * Calculates List of workers who sent queries for work. Searches for
     * workers in workersTImeWishList.
     *
     * @return List of workers who sent queries for work in a given period.
     */
    private List<Worker> getListOfworkersInPeriod(int start, int end) {
        List<Worker> workersList = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            if (!workersList.contains(workersTimeWishList.get(i).getWorker())) {
                workersList.add(workersTimeWishList.get(i).getWorker());
            }
        }
        return workersList;
    }

    /**
     *
     * @param numberOfWorkers
     */
    private List<TimeCellLookingForJob> getFairGraphOfWork(int start, int end) {
        final List<Worker> workersList = getListOfworkersInPeriod(start, end);
        //To hold fair graph of work
        List<TimeCellLookingForJob> fairGraphOfWork = new ArrayList<>();

        //Number of periods units per worker
        int N = (end - start) / workersList.size();
        //Number of work units per in subinterval per worker
        int n = subItervalLength/workersList.size();
        if (n < minimnPeriodOfWork) {
            n = minimnPeriodOfWork;
        }

        //Fills up fair graph of work
        int i = start;
        while (i <= end) {
            for (Worker worker : workersList){
                for (int j = 1; j <= n; n++){
                    fairGraphOfWork.add(new TimeCellLookingForJob(worker));
                    i++;
                }
            }
        }
        return fairGraphOfWork;
    }

    public void addNewJob(TimeCellLookingForWorker newJob) {

    }

    public void removeJob(TimeCellLookingForWorker aJob) {

    }

    public void addNewTimeCellLookingForJob(TimeCellLookingForJob timeCellLookingForJob) {

    }

    public void removeNewTimeCellLookingForJob(TimeCellLookingForJob timeCellLookingForJob) {

    }

    public void organizeTimeCells() {

    }
}

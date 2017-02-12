package timemanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeCellOfWorkerTime extends TimeCell {

    /**
     * @return the typeOfWork
     */
    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    @Override
    public TimeCellOfWorkerTime clone() throws CloneNotSupportedException {
        TimeCellOfWorkerTime clone;
        clone = (TimeCellOfWorkerTime) super.clone();
        clone.worker = worker.clone();
        clone.typeOfWork = typeOfWork;

        return clone;
    }

    /**
     * @param typeOfWork the typeOfWork to set
     */
    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
    private Worker worker;
    private TypeOfWork typeOfWork;

    public TimeCellOfWorkerTime(
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime creationTime,
            Worker aWorker,
            TypeOfWork aTypeOfWork) throws
            EndBeforeStartException,
            ZeroLengthException,
            CloneNotSupportedException {
        super(start, end, creationTime);
        worker = aWorker.clone();
        typeOfWork = aTypeOfWork;
    }

    public TimeCellOfWorkerTime(
            TimeCellOfWorkerTime aTimeCellOfWorkerTime,
            LocalDateTime end) throws
            EndBeforeStartException,
            ZeroLengthException,
            CloneNotSupportedException {
        this(
                aTimeCellOfWorkerTime.getStart(),
                end,
                aTimeCellOfWorkerTime.getCreationTime(),
                aTimeCellOfWorkerTime.getWorker(),
                aTimeCellOfWorkerTime.getTypeOfWork());
    }

    public TimeCellOfWorkerTime(
            LocalDateTime start,
            TimeCellOfWorkerTime aTimeCellOfWorkerTime) throws
            EndBeforeStartException,
            ZeroLengthException,
            CloneNotSupportedException {
        this(
                start,
                aTimeCellOfWorkerTime.getEnd(),
                aTimeCellOfWorkerTime.getCreationTime(),
                aTimeCellOfWorkerTime.getWorker(),
                aTimeCellOfWorkerTime.getTypeOfWork());
    }

    /**
     *
     * @param timeCellOfJob
     * @throws timemanager.EndBeforeStartException
     * @throws timemanager.ZeroLengthException
     * @throws java.lang.CloneNotSupportedException
     */
    public void splitTimeCells(TimeCellOfJob timeCellOfJob) throws
            EndBeforeStartException,
            ZeroLengthException,
            CloneNotSupportedException {
        List<TimeCell> jobList = new ArrayList<>();
        List<TimeCell> workerTimeList = new ArrayList<>();

        switch (getOverlapingType(timeCellOfJob)) {
            case 11:
                jobList.add(new TimeCellOfJob(timeCellOfJob, getEnd()));
                jobList.add(new TimeCellOfJob(getEnd(), timeCellOfJob));

                workerTimeList.add(new TimeCellOfWorkerTime(
                        this,
                        timeCellOfJob.getStart()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getStart(),
                        this));
                break;
            case 13:
                jobList.add(timeCellOfJob.clone());

                workerTimeList.add(new TimeCellOfWorkerTime(
                        this,
                        timeCellOfJob.getStart()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getStart(),
                        this));
                break;
            case 12:
                jobList.add(timeCellOfJob.clone());

                workerTimeList.add(new TimeCellOfWorkerTime(
                        this,
                        timeCellOfJob.getStart()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getStart(),
                        timeCellOfJob.getEnd(),
                        getCreationTime(),
                        getWorker(),
                        getTypeOfWork()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getEnd(),
                        this));
                break;
            case 21:
                jobList.add(new TimeCellOfJob(timeCellOfJob, getStart()));
                jobList.add(new TimeCellOfJob(
                        getStart(),
                        getEnd(),
                        timeCellOfJob.getCreationTime(),
                        timeCellOfJob.getCreator(),
                        timeCellOfJob.getTypeOfWork(),
                        timeCellOfJob.getAssinedTo()));
                jobList.add(new TimeCellOfJob(getEnd(), timeCellOfJob));

                workerTimeList.add(this.clone());
                break;
            case 23:
                jobList.add(new TimeCellOfJob(timeCellOfJob, getStart()));
                jobList.add(new TimeCellOfJob(
                        getStart(),
                        getEnd(),
                        timeCellOfJob.getCreationTime(),
                        timeCellOfJob.getCreator(),
                        timeCellOfJob.getTypeOfWork(),
                        timeCellOfJob.getAssinedTo()));
                workerTimeList.add(this.clone());
                break;
            case 22:
                jobList.add(new TimeCellOfJob(timeCellOfJob, getStart()));
                jobList.add(new TimeCellOfJob(getStart(), timeCellOfJob));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        this,
                        timeCellOfJob.getEnd()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getEnd(),
                        this));
                break;
            case 31:
                jobList.add(new TimeCellOfJob(timeCellOfJob, getEnd()));
                jobList.add(new TimeCellOfJob(getEnd(), timeCellOfJob));

                workerTimeList.add(this.clone());
                break;
            case 33:
                jobList.add(timeCellOfJob.clone());

                workerTimeList.add(this.clone());
                break;
            case 32:
                jobList.add(timeCellOfJob.clone());

                workerTimeList.add(new TimeCellOfWorkerTime(
                        this,
                        timeCellOfJob.getEnd()));
                workerTimeList.add(new TimeCellOfWorkerTime(
                        timeCellOfJob.getEnd(),
                        this));
                break;
        }
    }

    public TimeCellOfWorkerTime(
            LocalDateTime start,
            LocalDateTime end,
            Worker aWorker) throws
            EndBeforeStartException,
            ZeroLengthException {
        super(start, end);
        worker = aWorker;
        typeOfWork = TypeOfWork.ANY;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}

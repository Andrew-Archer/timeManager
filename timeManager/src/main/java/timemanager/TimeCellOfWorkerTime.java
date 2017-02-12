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
    public TimeCellOfWorkerTime clone() throws CloneNotSupportedException{
        TimeCellOfWorkerTime clone;
        clone = (TimeCellOfWorkerTime)super.clone();
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
                                        ZeroLengthException{
            super(start, end, creationTime);
            worker = aWorker;
            typeOfWork = aTypeOfWork;
        }

    /**
     *
     * @param timeCellOfJob
     */
        public void splitTimeCells(TimeCellOfJob timeCellOfJob) throws EndBeforeStartException, ZeroLengthException{
        List<TimeCell> splittedCell = new ArrayList<>();
        List<TimeCell> thisSplittedCell = new ArrayList<>();

        switch (getOverlapingType(timeCellOfJob)) {
            case 11:
                splittedCell.add(new TimeCellOfJob(timeCellOfJob, getEnd()));
                splittedCell.add(new TimeCell(getEnd(), timeCellOfJob));
                
                thisSplittedCell.add(new TimeCell(this, timeCellOfJob.getStart()));
                thisSplittedCell.add(new TimeCell(timeCellOfJob.getStart(), this));
                break;
            case 13:
                splittedCell.add(new TimeCell(timeCellOfJob));
                
                thisSplittedCell.add(new TimeCell(this, timeCellOfJob.getStart()));
                thisSplittedCell.add(new TimeCell(timeCellOfJob.getStart(), this));
                break;
            case 12:
                splittedCell.add(new TimeCell(timeCellOfJob));
                
                thisSplittedCell.add(new TimeCell(this, timeCellOfJob.getStart()));
                thisSplittedCell.add(new TimeCell(
                                                    timeCellOfJob.getStart(),
                                                    timeCellOfJob.getEnd(),
                                                    getCreationTime()));
                thisSplittedCell.add(new TimeCell(timeCellOfJob.getEnd(),this));
                break;
            case 21:
            	splittedCell.add(new TimeCell(timeCellOfJob, getStart()));
            	splittedCell.add(new TimeCell(
            									getStart(),
            									getEnd(),
            									timeCellOfJob.getCreationTime()));
            	splittedCell.add(new TimeCell(getEnd(), timeCellOfJob));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 23:
            	splittedCell.add(new TimeCell(timeCellOfJob, getStart()));
            	splittedCell.add(new TimeCell(
            									getStart(),
            									getEnd(),
            									timeCellOfJob.getCreationTime()));
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 22:
            	splittedCell.add(new TimeCell(timeCellOfJob, getStart()));
            	splittedCell.add(new TimeCell(getStart(), timeCellOfJob));
            	thisSplittedCell.add(new TimeCell(
            										getStart(),
            										timeCellOfJob.getEnd(),
            										getCreationTime()));
            	thisSplittedCell.add(new TimeCell(timeCellOfJob.getEnd(), this));
                break;
            case 31:
            	splittedCell.add(new TimeCell(timeCellOfJob, getEnd()));
            	splittedCell.add(new TimeCell(getEnd(), timeCellOfJob));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 33:
            	splittedCell.add(new TimeCell(timeCellOfJob));
            	
            	thisSplittedCell.add(new TimeCell(this));
                break;
            case 32:
            	splittedCell.add(new TimeCell(timeCellOfJob));
            	
            	thisSplittedCell.add(new TimeCell(this, timeCellOfJob.getEnd()));
            	thisSplittedCell.add(new TimeCell(timeCellOfJob.getEnd(), this));
                break;
        }
    }
        
        public TimeCellOfWorkerTime(
                LocalDateTime start,
                LocalDateTime end,
                Worker aWorker) throws
                                        EndBeforeStartException,
                                        ZeroLengthException{
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

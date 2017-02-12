package timemanager;

import java.time.LocalDateTime;

public class TimeCellOfJob extends TimeCell {

    /**
     * @return the assinedTo
     */
    public Worker getAssinedTo() {
        return assinedTo;
    }

    /**
     * @param assinedTo the assinedTo to set
     */
    public void setAssinedTo(Worker assinedTo) {
        this.assinedTo = assinedTo;
    }

    private Manager creator;
    private Worker assinedTo;
    private TypeOfWork typeOfWork;
   
    @Override
    public TimeCellOfJob clone() throws CloneNotSupportedException{
        TimeCellOfJob clone = (TimeCellOfJob) super.clone();
        clone.typeOfWork = typeOfWork;
        clone.assinedTo = assinedTo.clone();
        clone.creator = creator.clone();
        
        return clone;
    }
    
    public TimeCellOfJob(
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime creationTime,
            Manager creator,
            TypeOfWork typeOfWork) throws
                                            EndBeforeStartException,
                                            ZeroLengthException{
        super(start, end, creationTime);
        this.creator = creator;
        this.typeOfWork = typeOfWork;
    }
    
        public TimeCellOfJob(
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime aCreationTime,
            Manager aCreator,
            TypeOfWork aTypeOfWork,
            Worker assinedTo) throws
                                        EndBeforeStartException,
                                        ZeroLengthException,
                                        CloneNotSupportedException{
        super(start, end, aCreationTime);
        creator = aCreator.clone();
        typeOfWork = aTypeOfWork;
        this.assinedTo = assinedTo.clone();
        
    }
        
        public TimeCellOfJob(
                TimeCellOfJob aTimeCell,
                LocalDateTime anEnd) throws 
                                            EndBeforeStartException,
                                            ZeroLengthException,
                                            CloneNotSupportedException{
            this(
                    aTimeCell.getStart(),
                    anEnd,
                    aTimeCell.getCreationTime(),
                    aTimeCell.getCreator(),
                    aTimeCell.getTypeOfWork(),
                    aTimeCell.getAssinedTo());  
        }
        
                public TimeCellOfJob(
                LocalDateTime aStart, TimeCellOfJob aTimeCell) throws 
                                            EndBeforeStartException,
                                            ZeroLengthException,
                                            CloneNotSupportedException{
            this(
                    aStart,
                    aTimeCell.getEnd(),
                    aTimeCell.getCreationTime(),
                    aTimeCell.getCreator(),
                    aTimeCell.getTypeOfWork(),
                    aTimeCell.getAssinedTo());  
        }


    public Manager getCreator() {
        return creator;
    }

    public void setCreator(Manager creator) {
        this.creator = creator;
    }

    /**
     * @return the typeOfWork
     */
    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    /**
     * @param typeOfWork the typeOfWork to set
     */
    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

}

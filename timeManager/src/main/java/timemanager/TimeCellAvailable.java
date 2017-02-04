package timemanager;

import java.time.LocalDateTime;

public class TimeCellAvailable extends TimeCell {

    private Manager creator;
    private TypeOfWork typeOfWork;
    
    public TimeCellAvailable(
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
    
        public TimeCellAvailable(
            LocalDateTime start,
            LocalDateTime end,
            Manager creator) throws
                                        EndBeforeStartException,
                                        ZeroLengthException{
        super(start, end);
        this.creator = creator;
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

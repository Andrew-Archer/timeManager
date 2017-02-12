package timemanager;



public class Worker extends Person {
    public Worker(String aName, TypeOfWork typeOfWork){
        super(aName);
        this.typeOfWork = typeOfWork;
    }
	private TypeOfWork typeOfWork;

    /**
     * @return the typeOfWork
     */
    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }
    
    @Override
    public Worker clone() throws CloneNotSupportedException{
        Worker clone;
        clone = (Worker) super.clone();
        clone.typeOfWork = typeOfWork;
        return clone;
        
    }
    /**
     * @param typeOfWork the typeOfWork to set
     */
    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
}

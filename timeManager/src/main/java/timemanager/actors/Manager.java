package timemanager.actors;



public class Manager extends Person {

    public Manager(String aName) {
        super(aName);
    }
    
    @Override
    public Manager clone() throws CloneNotSupportedException{
        Manager clone;
        clone = (Manager)super.clone();
        return clone;
    }
}

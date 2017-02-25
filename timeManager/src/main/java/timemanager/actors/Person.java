package timemanager.actors;



public class Person implements Cloneable {
	private String name;
        
        public Person (String aName){
            name = aName;
        }
        
	public String getName() {
		return name;
	}
        
        @Override
        public Person clone() throws CloneNotSupportedException{
            Person clone;
            clone = (Person)super.clone();
            clone.name = name;
            return clone;
        }

	public void setName(String name) {
		this.name = name;
	}

}

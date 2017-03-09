package timemanager.actors;

import java.util.Objects;



public class Person implements Cloneable {
	private String name;
        
        public Person (String aName){
            name = aName;
        }

        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
        
	public String getName() {
		return name;
	}
        
        /*Override
        public Person clone() throws CloneNotSupportedException{
            Person clone;
            clone = (Person)super.clone();
            clone.name = name;
            return clone;
        }*/

	public void setName(String name) {
		this.name = name;
	}

}

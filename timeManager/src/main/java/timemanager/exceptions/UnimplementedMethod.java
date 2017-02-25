package timemanager.exceptions;

public class UnimplementedMethod extends UnsupportedOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UnimplementedMethod(){
		super("You have to write code to implement this method.");
	}
	

}

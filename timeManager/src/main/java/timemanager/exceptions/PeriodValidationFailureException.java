/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager.exceptions;

/**
 *
 * @author razan
 */
public class PeriodValidationFailureException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3896179340208883088L;
	private static final String DEFAULT_MESSAGE
            = "You try to violate period restrictions.";

    public PeriodValidationFailureException() {
        super(DEFAULT_MESSAGE);
    }
}

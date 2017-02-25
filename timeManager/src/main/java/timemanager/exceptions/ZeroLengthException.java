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
public class ZeroLengthException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6033644399475678329L;
	private static final String DEFAULT_MESSAGE
            = "It's not allowed to created TimeCell with the with" +
            "the end equal to the start.";

    public ZeroLengthException() {
        super(DEFAULT_MESSAGE);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

/**
 *
 * @author razan
 */
public class EndBeforeStartException extends Exception {

    private static final String DEFAULT_MESSAGE
            = "It's not allowed to created TimeCell with the end before the start.";

    public EndBeforeStartException() {
        super(DEFAULT_MESSAGE);
    }
}

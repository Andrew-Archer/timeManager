
import java.time.LocalDateTime;
import timemanager.TimeCell;
import timemanager.TypeOfWork;
import timemanager.actors.Person;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author razan
 */
public class sample {
    public static void main(String[] arg) throws EndBeforeStartException, ZeroLengthException{
        TimeCell original = new TimeCell(
        LocalDateTime.now(),
        LocalDateTime.now().plusHours(1),
        LocalDateTime.now(),
        new Person("Jack"),
        null,
        TypeOfWork.ANY);
        
        System.out.println(original);
    }
    
    public static void modify(TimeCell aTimeCell){
        
    }
    
}

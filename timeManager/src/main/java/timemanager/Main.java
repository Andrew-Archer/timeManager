/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.time.LocalDateTime;

import timemanager.actors.Person;
import timemanager.actors.Worker;

/**
 *
 * @author razan
 */
public class Main {
    public static void main(String[] arg) throws Exception{
        TimeCell aTimeCell = new TimeCell(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now(),
                new Person("Fred"),
                new Worker("James", TypeOfWork.ANY),
                TypeOfWork.ANY);
        
        System.out.println(aTimeCell);
    }
    
}

package timemanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author razan
 */
public class ConsolePrinter {
    
    public void printGraphOfWork(List<TimeCellLookingForJob> graphOfWork){
        for (TimeCellLookingForJob timeCellLookingForJob : graphOfWork){
            System.out.println(timeCellLookingForJob.getWorker().toString());
        }        
    }
    
}
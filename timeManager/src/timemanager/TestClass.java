package timemanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author razan
 */
public class TestClass {
    public static void main(String[] arg){
        ConsolePrinter cp = new ConsolePrinter();
        List<TimeCellLookingForJob> graphOfWork = new ArrayList<>();
        graphOfWork.add(new TimeCellLookingForJob(new Worker("Ja")));
        graphOfWork.add(new TimeCellLookingForJob(new Worker("Jan")));
        graphOfWork.add(new TimeCellLookingForJob(new Worker("Jane")));
        graphOfWork.add(new TimeCellLookingForJob(new Worker("Janet")));
        graphOfWork.add(new TimeCellLookingForJob(new Worker("John")));
        cp.printGraphOfWork(graphOfWork);
    }
}

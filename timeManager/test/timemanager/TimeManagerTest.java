/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanager;

import java.util.List;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author razan
 */
public class TimeManagerTest {

    /**
     * Test of addNewJob method, of class TimeManager.
     */
    @Test
    public void testGetListOfworkersInPeriod() {
        TimeManager TM = new TimeManager();
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("Jack")));
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("Joe")));
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("John")));

        assertTrue(TM.getListOfworkersInPeriod(0, 2).size() == 3);

        assertTrue(TM.getListOfworkersInPeriod(0, 2).size() == 3);
        assertTrue(TM.getListOfworkersInPeriod(0, 1).size() == 2);
        assertTrue(TM.getListOfworkersInPeriod(1, 2).size() == 2);
        assertTrue(TM.getListOfworkersInPeriod(0, 0).size() == 1);
        assertTrue(TM.getListOfworkersInPeriod(2, 2).size() == 1);
    }

    @Test
    public void testGetFairGraphOfWork() {
        TimeManager TM = new TimeManager();
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("Jack")));
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("Joe")));
        TM.addNewTimeCellLookingForJob(new TimeCellLookingForJob(new Worker("John")));

        List<TimeCellLookingForJob> graphOfWork = TM.getFairGraphOfWork(0, 5);
        //assertTrue(graphOfWork.size() == 6);

        List<Worker> workersList = TM.getListOfworkersInPeriod(0, 5);

        for (int i = 0; i < workersList.size(); i++) {
            System.out.print(i);
            for (int j = i * 2; j <= 1 + i * 2; j++) {
                System.out.print(j);
                assertTrue(workersList.get(i).getName().equals((graphOfWork.get(j).getWorker().getName())));
            }
            System.out.println();
        }

    }

}

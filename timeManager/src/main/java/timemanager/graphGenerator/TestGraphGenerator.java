/*
 * Copyright (C) 2017 Andrew.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package timemanager.graphGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanager.TimeCell;
import timemanager.TimeManager;
import timemanager.TypeOfWork;
import timemanager.actors.Person;
import timemanager.exceptions.EndBeforeStartException;
import timemanager.exceptions.ZeroLengthException;


public class TestGraphGenerator implements GraphGenerator {

    private LocalDateTime start;
    private LocalDateTime end;
    long nunimumPeriodOfWorkInHours;
    Person creator;

    public TestGraphGenerator(LocalDateTime start, LocalDateTime end, long nunimumPeriodOfWorkInHours, Person creator) {
        this.start = start;
        this.end = end;
        this.nunimumPeriodOfWorkInHours = nunimumPeriodOfWorkInHours;
        this.creator = creator;
    }
    
    

    /**
     *
     * @param start
     * @param end
     * @param minimunPeriodOfWorkInHours
     * @param creator
     * @return
     * @throws ZeroLengthException
     * @throws EndBeforeStartException
     */
    @Override
    public List<TimeCell> generate()
					throws ZeroLengthException,
						EndBeforeStartException{
		// To hold fair graph of work
		List<TimeCell> fairGraphOfWork = new ArrayList<>();
		
		long numberOfIntervalsToGenerate = (Duration.between(start, end).toHours() + 1)/nunimumPeriodOfWorkInHours;

		try {
		// Fills up fair graph of work
		for (long i = 0; i < numberOfIntervalsToGenerate; i ++ ) {

				fairGraphOfWork.add(new TimeCell(
						start.plusHours(i*nunimumPeriodOfWorkInHours),
						start.plusHours((i+1)*nunimumPeriodOfWorkInHours),
						LocalDateTime.now(),
						creator,
						null,
						TypeOfWork.ANY));
		}
			} catch (EndBeforeStartException ex) {
				Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ZeroLengthException ex) {
				Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		return fairGraphOfWork;
	}

    
}

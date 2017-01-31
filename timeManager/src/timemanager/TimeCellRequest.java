package timemanager;

import java.time.LocalDateTime;

public class TimeCellRequest extends TimeCell {
	private Worker worker;
        
        public TimeCellRequest(
                LocalDateTime start,
                LocalDateTime end,
                LocalDateTime creationTime,
                Worker aWorker){
            super(start, end, creationTime);
            worker = aWorker;
        }
        
        public TimeCellRequest(
                LocalDateTime start,
                LocalDateTime end,
                Worker aWorker){
            super(start, end);
            worker = aWorker;
        }

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
}

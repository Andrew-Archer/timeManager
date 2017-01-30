package timemanager;



public class TimeCellLookingForJob extends TimeCell {
	private Worker worker;
        
        public TimeCellLookingForJob(Worker aWorker){
            worker = aWorker;
        }

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
        
        public String toString(){
            return worker.getName();
        }
}

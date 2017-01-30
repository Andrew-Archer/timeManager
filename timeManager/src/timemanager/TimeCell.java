package timemanager;



import java.util.Date;

public class TimeCell {
	private Date start;			
	private Date end;
	protected Date creationTime;
	
	public Boolean fit(TimeCell aTimeCell){
		return getStart().equals(aTimeCell.getStart()) && getEnd().equals(aTimeCell.getEnd());
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}

package bean;

import java.sql.Date;

public class FeesBean extends StudentsBean {
	
	private int paidFees;
	private Date startDate;
	private Date endDate;
	private String status;
	private int stdId;
	public FeesBean(int paidFees, Date startDate, Date endDate, String status, int stdId) {
		super();
		this.paidFees = paidFees;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.stdId = stdId;
	}
	public int getPaidFees() {
		return paidFees;
	}
	public void setPaidFees(int paidFees) {
		this.paidFees = paidFees;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStdId() {
		return stdId;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	
}

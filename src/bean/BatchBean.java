package bean;

public class BatchBean {
	
	private int bId;
	private String batchName;
	private int durationInMnths;
	private int fees;
	public BatchBean() {
		// TODO Auto-generated constructor stub
	}
	public BatchBean(int bId, String name, int durationInMnths, int fees) {
		super();
		this.bId = bId;
		this.batchName = name;
		this.durationInMnths = durationInMnths;
		this.fees = fees;
	}
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String name) {
		this.batchName = name;
	}
	public int getDurationInMnths() {
		return durationInMnths;
	}
	public void setDurationInMnths(int durationInMnths) {
		this.durationInMnths = durationInMnths;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	
}

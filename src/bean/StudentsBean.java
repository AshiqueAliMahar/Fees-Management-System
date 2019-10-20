package bean;

import java.sql.Date;

public class StudentsBean extends BatchBean {
	
	private int sId;
	private String sName;
	private String fathersName;
	private String email;
	private String address;
	private Date dob;
	private int bID;
	private byte [] pic;
	public StudentsBean() {
		// TODO Auto-generated constructor stub
	}
	public StudentsBean(int id, String name, String fathersName, String email, String address, Date dob, int bID,byte [] pic) {
		super();
		this.sId = id;
		this.sName = name;
		this.fathersName = fathersName;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.bID = bID;
		this.setPic(pic);
	}
	public int getId() {
		return sId;
	}
	public void setId(int id) {
		this.sId = id;
	}
	public String getName() {
		return sName;
	}
	public void setName(String name) {
		this.sName = name;
	}
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getBID() {
		return bID;
	}
	public void setBID(int bID) {
		this.bID = bID;
	}
	public byte [] getPic() {
		return pic;
	}
	public void setPic(byte [] pic) {
		this.pic = pic;
	}
	
}

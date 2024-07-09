package Model;

import java.util.Date;

public class InterviewSchedule {
	private int scheduleid;
	private Date startdatetime;
	private Date enddatetime;
	private String importance;
	private String execplace;
	private int staffid1;
	private int staffid2;
	private int staffid3;
	private int applicantid;
	private String remarks;
	private Date createdate;
	private Date updatedate;
	private Date deletedate;

	public InterviewSchedule(int intScheduleid, Date startDateTime, int intStaffid1, int intStaffid2,
					int intStaffid3, int intApplicantId, String strRemarks) {
				setScheduleid(intScheduleid);
				setStartdatetime(startDateTime);
				setStaffid1(intStaffid1);
				setStaffid2(intStaffid2);
				setStaffid3(intStaffid3);
				setApplicantid(intApplicantId);
				setRemarks(strRemarks);
	}
	public int getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}
	public Date getStartdatetime() {
		return startdatetime;
	}
	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}
	public Date getEnddatetime() {
		return enddatetime;
	}
	public void setEnddatetime(Date enddatetime) {
		this.enddatetime = enddatetime;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getExecplace() {
		return execplace;
	}
	public void setExecplace(String execplace) {
		this.execplace = execplace;
	}
	public int getStaffid1() {
		return staffid1;
	}
	public void setStaffid1(int staffid1) {
		this.staffid1 = staffid1;
	}
	public int getStaffid2() {
		return staffid2;
	}
	public void setStaffid2(int staffid2) {
		this.staffid2 = staffid2;
	}
	public int getStaffid3() {
		return staffid3;
	}
	public void setStaffid3(int staffid3) {
		this.staffid3 = staffid3;
	}
	public int getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(int applicantid) {
		this.applicantid = applicantid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public Date getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(Date deletedate) {
		this.deletedate = deletedate;
	}
}

package Model;

import java.util.Date;

public class Staff {

	private Integer staffId;
	private String staffName;
	private Integer departmentid;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Staff(Integer intStaffId, String strStaffName) {
		setStaffId(intStaffId);
		setStaffName(strStaffName);
	}

	@Override
	public String toString() {
		return staffName;
	}
}

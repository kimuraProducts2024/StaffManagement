package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StaffTable {
	private IntegerProperty staffid;
	private StringProperty staffnaame;
	private StringProperty gender;
	private IntegerProperty age;
	private StringProperty telephone;
	private StringProperty department;
	private StringProperty post;
	public StaffTable(int staffid, String staffnaame, String gender, int age,
			String telephone, String department, String post) {
		super();
		this.staffid = new SimpleIntegerProperty(staffid);
		this.staffnaame = new SimpleStringProperty(staffnaame);
		this.gender = new SimpleStringProperty(gender);
		this.age = new SimpleIntegerProperty(age);
		this.telephone = new SimpleStringProperty(telephone);
		this.department = new SimpleStringProperty(department);
		this.post = new SimpleStringProperty(post);
	}
	public IntegerProperty getStaffid() {
		return staffid;
	}
	public void setStaffid(IntegerProperty staffid) {
		this.staffid = staffid;
	}
	public StringProperty getStaffnaame() {
		return staffnaame;
	}
	public void setStaffnaame(StringProperty staffnaame) {
		this.staffnaame = staffnaame;
	}
	public StringProperty getGender() {
		return gender;
	}
	public void setGender(StringProperty gender) {
		this.gender = gender;
	}
	public IntegerProperty getAge() {
		return age;
	}
	public void setAge(IntegerProperty age) {
		this.age = age;
	}
	public StringProperty getTelephone() {
		return telephone;
	}
	public void setTelephone(StringProperty telephone) {
		this.telephone = telephone;
	}
	public StringProperty getDepartment() {
		return department;
	}
	public void setDepartment(StringProperty department) {
		this.department = department;
	}
	public StringProperty getPost() {
		return post;
	}
	public void setPost(StringProperty post) {
		this.post = post;
	}
}

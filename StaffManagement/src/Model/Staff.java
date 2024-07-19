package Model;

import java.util.Date;

public class Staff {

	private Integer staffId;
	private String staffName;
	private Integer departmentid;
	private String gender;
	private Integer age;
	private String nationality;
	private String telephone;
	private String email;
	private Integer postcode;
	private String prefectures;
	private String municipalities;
	private String address;
	private Long mynumber;
	private Integer postid;
	private Integer salaryid;
	private Date joindate;
	private Date retiredate;
	private String remarks;
	private String emergencyname;
	private String emergencysequel;
	private Long emergencypostcode;
	private String emergencytelephone;
	private String emergencyprefectures;
	private String emergencymunicipalities;
	private String emergencyaddress;
	private String spouse;
	private String support;
	private String education;
	private String qualification1;
	private String qualification2;
	private String qualification3;
	private String qualification4;
	private String qualification5;
	private String qualification6;
	private String qualification7;
	private String qualification8;
	private Long socialinsurancenumber;
	private Long basicpensionnumber;
	private String fainancialinstitude;
	private String fainancialinstitudekana;
	private Integer fainancialinstitudecode;
	private String branchname;
	private String branchnamekana;
	private Integer branchcode;
	private String depositkind;
	private Integer accountnumber;
	private String accountname;
	private String accountnamekana;
	private String recipientname;
	private String recipientnamekana;
	private String staffimage;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;

	public Staff(Integer intStaffId, String strStaffName) {
		setStaffId(intStaffId);
		setStaffName(strStaffName);
	}

	public Staff(String staffName, String gender, Integer age, String nationality,
			String telephone, String email, Integer postcode, String prefectures, String municipalities, String address,
			Long mynumber, Integer departmentid, Integer postid, Integer salaryid, Date joindate, Date retiredate, String remarks,
			String emergencyname, String emergencysequel, Long emergencypostcode, String emergencytelephone,
			String emergencyprefectures, String emergencymunicipalities, String emergencyaddress, String spouse,
			String support, String education, String qualification1, String qualification2, String qualification3,
			String qualification4, String qualification5, String qualification6, String qualification7,
			Long socialinsurancenumber, Long basicpensionnumber, String fainancialinstitude,
			String fainancialinstitudekana, Integer fainancialinstitudecode, String branchname, String branchnamekana,
			Integer branchcode, String depositkind, Integer accountnumber, String accountname, String accountnamekana,
			String recipientname, String recipientnamekana, String staffimage) {
		super();
		this.staffName = staffName;
		this.departmentid = departmentid;
		this.gender = gender;
		this.age = age;
		this.nationality = nationality;
		this.telephone = telephone;
		this.email = email;
		this.postcode = postcode;
		this.prefectures = prefectures;
		this.municipalities = municipalities;
		this.address = address;
		this.mynumber = mynumber;
		this.postid = postid;
		this.salaryid = salaryid;
		this.joindate = joindate;
		this.retiredate = retiredate;
		this.remarks = remarks;
		this.emergencyname = emergencyname;
		this.emergencysequel = emergencysequel;
		this.emergencypostcode = emergencypostcode;
		this.emergencytelephone = emergencytelephone;
		this.emergencyprefectures = emergencyprefectures;
		this.emergencymunicipalities = emergencymunicipalities;
		this.emergencyaddress = emergencyaddress;
		this.spouse = spouse;
		this.support = support;
		this.education = education;
		this.qualification1 = qualification1;
		this.qualification2 = qualification2;
		this.qualification3 = qualification3;
		this.qualification4 = qualification4;
		this.qualification5 = qualification5;
		this.qualification6 = qualification6;
		this.qualification7 = qualification7;
		this.socialinsurancenumber = socialinsurancenumber;
		this.basicpensionnumber = basicpensionnumber;
		this.fainancialinstitude = fainancialinstitude;
		this.fainancialinstitudekana = fainancialinstitudekana;
		this.fainancialinstitudecode = fainancialinstitudecode;
		this.branchname = branchname;
		this.branchnamekana = branchnamekana;
		this.branchcode = branchcode;
		this.depositkind = depositkind;
		this.accountnumber = accountnumber;
		this.accountname = accountname;
		this.accountnamekana = accountnamekana;
		this.recipientname = recipientname;
		this.recipientnamekana = recipientnamekana;
		this.staffimage = staffimage;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getPrefectures() {
		return prefectures;
	}

	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}

	public String getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(String municipalities) {
		this.municipalities = municipalities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMynumber() {
		return mynumber;
	}

	public void setMynumber(Long mynumber) {
		this.mynumber = mynumber;
	}

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public Integer getSalaryid() {
		return salaryid;
	}

	public void setSalaryid(Integer salaryid) {
		this.salaryid = salaryid;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Date getRetiredate() {
		return retiredate;
	}

	public void setRetiredate(Date retiredate) {
		this.retiredate = retiredate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEmergencyname() {
		return emergencyname;
	}

	public void setEmergencyname(String emergencyname) {
		this.emergencyname = emergencyname;
	}

	public String getEmergencysequel() {
		return emergencysequel;
	}

	public void setEmergencysequel(String emergencysequel) {
		this.emergencysequel = emergencysequel;
	}

	public Long getEmergencypostcode() {
		return emergencypostcode;
	}

	public void setEmergencypostcode(Long emergencypostcode) {
		this.emergencypostcode = emergencypostcode;
	}

	public String getEmergencytelephone() {
		return emergencytelephone;
	}

	public void setEmergencytelephone(String emergencytelephone) {
		this.emergencytelephone = emergencytelephone;
	}

	public String getEmergencyprefectures() {
		return emergencyprefectures;
	}

	public void setEmergencyprefectures(String emergencyprefectures) {
		this.emergencyprefectures = emergencyprefectures;
	}

	public String getEmergencymunicipalities() {
		return emergencymunicipalities;
	}

	public void setEmergencymunicipalities(String emergencymunicipalities) {
		this.emergencymunicipalities = emergencymunicipalities;
	}

	public String getEmergencyaddress() {
		return emergencyaddress;
	}

	public void setEmergencyaddress(String emergencyaddress) {
		this.emergencyaddress = emergencyaddress;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public String getQualification3() {
		return qualification3;
	}

	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}

	public String getQualification4() {
		return qualification4;
	}

	public void setQualification4(String qualification4) {
		this.qualification4 = qualification4;
	}

	public String getQualification5() {
		return qualification5;
	}

	public void setQualification5(String qualification5) {
		this.qualification5 = qualification5;
	}

	public String getQualification6() {
		return qualification6;
	}

	public void setQualification6(String qualification6) {
		this.qualification6 = qualification6;
	}

	public String getQualification7() {
		return qualification7;
	}

	public void setQualification7(String qualification7) {
		this.qualification7 = qualification7;
	}

	public String getQualification8() {
		return qualification8;
	}

	public void setQualification8(String qualification8) {
		this.qualification8 = qualification8;
	}

	public Long getSocialinsurancenumber() {
		return socialinsurancenumber;
	}

	public void setSocialinsurancenumber(Long socialinsurancenumber) {
		this.socialinsurancenumber = socialinsurancenumber;
	}

	public Long getBasicpensionnumber() {
		return basicpensionnumber;
	}

	public void setBasicpensionnumber(Long basicpensionnumber) {
		this.basicpensionnumber = basicpensionnumber;
	}

	public String getFainancialinstitude() {
		return fainancialinstitude;
	}

	public void setFainancialinstitude(String fainancialinstitude) {
		this.fainancialinstitude = fainancialinstitude;
	}

	public String getFainancialinstitudekana() {
		return fainancialinstitudekana;
	}

	public void setFainancialinstitudekana(String fainancialinstitudekana) {
		this.fainancialinstitudekana = fainancialinstitudekana;
	}

	public Integer getFainancialinstitudecode() {
		return fainancialinstitudecode;
	}

	public void setFainancialinstitudecode(Integer fainancialinstitudecode) {
		this.fainancialinstitudecode = fainancialinstitudecode;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getBranchnamekana() {
		return branchnamekana;
	}

	public void setBranchnamekana(String branchnamekana) {
		this.branchnamekana = branchnamekana;
	}

	public Integer getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(Integer branchcode) {
		this.branchcode = branchcode;
	}

	public String getDepositkind() {
		return depositkind;
	}

	public void setDepositkind(String depositkind) {
		this.depositkind = depositkind;
	}

	public Integer getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(Integer accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountnamekana() {
		return accountnamekana;
	}

	public void setAccountnamekana(String accountnamekana) {
		this.accountnamekana = accountnamekana;
	}

	public String getRecipientname() {
		return recipientname;
	}

	public void setRecipientname(String recipientname) {
		this.recipientname = recipientname;
	}

	public String getRecipientnamekana() {
		return recipientnamekana;
	}

	public void setRecipientnamekana(String recipientnamekana) {
		this.recipientnamekana = recipientnamekana;
	}

	public String getStaffimage() {
		return staffimage;
	}

	public void setStaffimage(String staffimage) {
		this.staffimage = staffimage;
	}

	@Override
	public String toString() {
		return staffName;
	}
}

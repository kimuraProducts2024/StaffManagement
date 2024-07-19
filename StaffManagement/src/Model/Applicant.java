package Model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Applicant {
	private IntegerProperty applicantid;
	private StringProperty applicantname;
	private StringProperty gender;
	private IntegerProperty age;
	private StringProperty nationality;
	private StringProperty telephone;
	private StringProperty conducted;
	private StringProperty result;
	private StringProperty remarks;
	private IntegerProperty documentkinds;
	private Date createdate;
	private Date updatedate;
	private Date deletedate;

	public Applicant(IntegerProperty intApplicantId, StringProperty strApplicantName) {
		setApplicantid(intApplicantId);
		setApplicantname(strApplicantName);
	}

	public Applicant(int intApplicantId, String strApplicantName, String strGender, Integer intAge,
			 String strTelephone, String strConducted, String strResult, String strRemarks) {
		setApplicantid(new SimpleIntegerProperty(intApplicantId));
		setApplicantname(new SimpleStringProperty(strApplicantName));
		setGender(new SimpleStringProperty(strGender));
		setAge(new SimpleIntegerProperty(intAge));
		setTelephone(new SimpleStringProperty(strTelephone));
		setConducted(new SimpleStringProperty(strConducted));
		setResult(new SimpleStringProperty(strResult));
		setRemarks(new SimpleStringProperty(strRemarks));
	}

	public Applicant(int intApplicantId, String strApplicantName, String strGender, Integer intAge,
			String strNationality, String strTelephone, String strConducted,
			String strResult, String strRemarks, int intDocumentkinds) {
		setApplicantid(new SimpleIntegerProperty(intApplicantId));
		setApplicantname(new SimpleStringProperty(strApplicantName));
		setGender(new SimpleStringProperty(strGender));
		setAge(new SimpleIntegerProperty(intAge));
		setNationality(new SimpleStringProperty(strNationality));
		setTelephone(new SimpleStringProperty(strTelephone));
		setConducted(new SimpleStringProperty(strConducted));
		setResult(new SimpleStringProperty(strResult));
		setRemarks(new SimpleStringProperty(strRemarks));
		setDocumentkinds(new SimpleIntegerProperty(intDocumentkinds));
	}

	public IntegerProperty getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(IntegerProperty applicantid) {
		this.applicantid = applicantid;
	}
	public StringProperty getApplicantname() {
		return applicantname;
	}
	public void setApplicantname(StringProperty applicantname) {
		this.applicantname = applicantname;
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
	public StringProperty getNationality() {
		return nationality;
	}
	public void setNationality(StringProperty nationality) {
		this.nationality = nationality;
	}
	public StringProperty getTelephone() {
		return telephone;
	}
	public void setTelephone(StringProperty telephone) {
		this.telephone = telephone;
	}
	public void setConducted(StringProperty conducted) {
		this.conducted = conducted;
	}
	public StringProperty getConducted() {
		return conducted;
	}
	public void setResult(StringProperty result) {
		this.result = result;
	}
	public StringProperty getResult() {
		return result;
	}
	public StringProperty getRemarks() {
		return remarks;
	}
	public void setRemarks(StringProperty remarks) {
		this.remarks = remarks;
	}
	public IntegerProperty getDocumentkinds() {
		return documentkinds;
	}
	public void setDocumentkinds(IntegerProperty documentkinds) {
		this.documentkinds = documentkinds;
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

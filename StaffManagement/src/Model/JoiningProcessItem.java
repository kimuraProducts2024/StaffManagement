package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JoiningProcessItem {
	private IntegerProperty applicantid;
	private StringProperty applicantname;
	private StringProperty documentkinds;
	private StringProperty joinstatus;
	private StringProperty telephone;
	private StringProperty shortagedoc;
	private StringProperty remarks;
	public JoiningProcessItem(int applicantid, String applicantname, String documentkinds,
			String joinstatus, String telephone, String shortagedoc, String remarks) {
		super();
		this.applicantid = new SimpleIntegerProperty(applicantid);
		this.applicantname = new SimpleStringProperty(applicantname);
		this.documentkinds = new SimpleStringProperty(documentkinds);
		this.joinstatus = new SimpleStringProperty(joinstatus);
		this.telephone = new SimpleStringProperty(telephone);
		this.shortagedoc = new SimpleStringProperty(shortagedoc);
		this.remarks = new SimpleStringProperty(remarks);
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
	public StringProperty getDocumentkinds() {
		return documentkinds;
	}
	public void setDocumentkinds(StringProperty documentkinds) {
		this.documentkinds = documentkinds;
	}
	public StringProperty getJoinstatus() {
		return joinstatus;
	}
	public void setJoinstatus(StringProperty joinstatus) {
		this.joinstatus = joinstatus;
	}
	public StringProperty getTelephone() {
		return telephone;
	}
	public void setTelephone(StringProperty telephone) {
		this.telephone = telephone;
	}
	public StringProperty getshortagedoc() {
		return shortagedoc;
	}
	public void setshortagedoc(StringProperty shortagedoc) {
		this.shortagedoc = shortagedoc;
	}
	public StringProperty getRemarks() {
		return remarks;
	}
	public void setRemarks(StringProperty remarks) {
		this.remarks = remarks;
	}

}

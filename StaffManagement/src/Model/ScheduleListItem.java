package Model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScheduleListItem {

	private IntegerProperty scheduleId;
	private ObjectProperty<LocalDateTime> scheduleDateTime;
	private IntegerProperty applicantId;
	private StringProperty applicantName;

	public ScheduleListItem(int intScheduleId, LocalDateTime dateScheduleDateTime, int intApplicantId, String strApplicantName) {
		this.setScheduleId(new SimpleIntegerProperty(intScheduleId));
		this.setScheduleDateTime(new SimpleObjectProperty<LocalDateTime>(dateScheduleDateTime));
		this.setApplicantId(new SimpleIntegerProperty(intApplicantId));
		this.setApplicantName(new SimpleStringProperty(strApplicantName));
	}

	public IntegerProperty getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(IntegerProperty scheduleId) {
		this.scheduleId = scheduleId;
	}

	public ObjectProperty<LocalDateTime> getScheduleDateTime() {
		return scheduleDateTime;
	}

	public void setScheduleDateTime(ObjectProperty<LocalDateTime> scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	public IntegerProperty getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(IntegerProperty applicantId) {
		this.applicantId = applicantId;
	}

	public StringProperty getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(StringProperty applicantName) {
		this.applicantName = applicantName;
	}
}

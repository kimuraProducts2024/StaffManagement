package application;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Staff;
import Service.DataManageService;
import common.CommonFunc;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ScheduleDetailController implements Initializable {

	@FXML
	private TextField txtSearchScheId;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField txtSearchHour;
	@FXML
	private TextField txtSearchMinute;
	@FXML
	private TextField txtSearchAplId;
	@FXML
	private TextField txtSearchAplName;
	@FXML
	private Label lblScheduleId;
	@FXML
	private TextField txtMeetingMonth;
	@FXML
	private TextField txtMeetingDay;
	@FXML
	private TextField txtStartHour;
	@FXML
	private TextField txtStartMinute;
	@FXML
	private TextField txtEndHour;
	@FXML
	private TextField txtEndMinute;
	@FXML
	private TextField txtImportance;
	@FXML
	private TextField txtExecPlace;
	@FXML
	private ComboBox<Staff> cmbInterviewer1;
	@FXML
	private ComboBox<Staff> cmbInterviewer2;
	@FXML
	private ComboBox<Staff> cmbInterviewer3;
	@FXML
	private TextField txtApplicantName;
	@FXML
	private TextArea txtRemarks;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnClear;

	private static DataManageService dms;
	private static CommonFunc comFunc;

	public static int intTargetScheduleID = 0;

	// 担当者リスト
	private Map<Integer, Staff> mapStaff = new HashMap<Integer, Staff>();

	// 初期化処理
	// 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		try {
			dms = DataManageService.createInstance();
			comFunc = CommonFunc.createInstance();

			// 担当者コンボボックス設定
			setIntevierwerCombo();
			// 各値設定処理
			initDisplay();

			// 面接日程管理画面から遷移した場合
			if(intTargetScheduleID != 0) {
				// 登録非活性化
				// 更新、削除ボタン活性化
				btnRegist.setDisable(true);
				btnUpdate.setDisable(false);
				btnDelete.setDisable(false);
			} else {
				// 登録活性化
				// 更新、削除ボタン非活性化
				btnRegist.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 担当者コンボボックス設定
	// 引数
	// なし
	// 戻り値
	// なし
	private void setIntevierwerCombo() throws SQLException {

		// 社員テーブルから対象のデータを取得
		ResultSet rs = dms.getStaffData();

		// 空欄の値設定
		Staff sEmpty = new Staff(0, "");
		cmbInterviewer1.getItems().add(sEmpty);
		cmbInterviewer2.getItems().add(sEmpty);
		cmbInterviewer3.getItems().add(sEmpty);

		// 各コンボボックスの項目を設定
		while(rs.next()) {
			Staff objStaff = new Staff(rs.getInt("STAFFID"), rs.getString("STAFFNAME"));
			mapStaff.put(rs.getInt("STAFFID"), objStaff);
			cmbInterviewer1.getItems().add(objStaff);
			cmbInterviewer2.getItems().add(objStaff);
			cmbInterviewer3.getItems().add(objStaff);
		}

		// 初期選択状態を設定
		cmbInterviewer1.getSelectionModel().select(0);
		cmbInterviewer2.getSelectionModel().select(0);
		cmbInterviewer3.getSelectionModel().select(0);
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {
		// メインメニューから遷移した場合
		if(intTargetScheduleID == 0) {
			// 面接日程テーブルから、IDを取得
			int intScheduleId = dms.getScheduleIdData();
			lblScheduleId.setText(String.valueOf(intScheduleId));
		}
		// 面接日程管理画面から遷移した場合
		else
		{
			// 面接日程テーブル、応募者テーブルのデータ取得
			ResultSet rs = dms.getScheduleApplicantData(intTargetScheduleID);

			// 取得したデータを元に、各項目の値を設定
			while(rs.next()) {
				Date dtStart = rs.getTimestamp("STARTDATETIME");									// 面接開始日時
				Date dtEnd = rs.getTimestamp("ENDDATETIME");										// 面接終了日時
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDate startDate = dtStart.toInstant().atZone(zoneId).toLocalDate();
				Instant instantSt = dtStart.toInstant();
				Instant instantEd = dtEnd.toInstant();
				LocalDateTime startDateTime = LocalDateTime.ofInstant(instantSt, ZoneId.systemDefault());
				LocalDateTime endDateTime = LocalDateTime.ofInstant(instantEd, ZoneId.systemDefault());
				String strMeetingMonth = String.format("%02d", startDate.getMonthValue());		// 実施月
				String strMeetingDay = String.format("%02d", startDate.getDayOfMonth());			// 実施日
				String strStartHour = String .format("%02d", startDateTime.getHour());			// 開始時
				String strStartMinute = String.format("%02d", startDateTime.getMinute());		// 開始分
				String strEndHour = String.format("%02d", endDateTime.getHour());					// 終了時
				String strEndMinute = String.format("%02d", endDateTime.getMinute());			// 終了分

				lblScheduleId.setText(String.valueOf(rs.getInt("SCHEDULEID")));
				txtMeetingMonth.setText(strMeetingMonth);
				txtMeetingDay.setText(strMeetingDay);
				txtStartHour.setText(strStartHour);
				txtStartMinute.setText(strStartMinute);
				txtEndHour.setText(strEndHour);
				txtEndMinute.setText(strEndMinute);
				txtImportance.setText(rs.getString("IMPORTANCE"));
				txtExecPlace.setText(rs.getString("EXECPLACE"));
				if(rs.getInt("STAFFID1") != 0) {
					cmbInterviewer1.getSelectionModel().select(mapStaff.get(rs.getInt("STAFFID1")));
				} else {
					cmbInterviewer1.getSelectionModel().select(0);
				}
				if(rs.getInt("STAFFID2") != 0) {
					cmbInterviewer2.getSelectionModel().select(mapStaff.get(rs.getInt("STAFFID2")));
				} else {
					cmbInterviewer2.getSelectionModel().select(0);
				}
				if(rs.getInt("STAFFID3") != 0) {
					cmbInterviewer3.getSelectionModel().select(mapStaff.get(rs.getInt("STAFFID3")));
				} else {
					cmbInterviewer3.getSelectionModel().select(0);
				}
				txtApplicantName.setText(rs.getString("APPLICANTNAME"));
				txtRemarks.setText(rs.getString("REMARKS"));
			}
		}
	}
}

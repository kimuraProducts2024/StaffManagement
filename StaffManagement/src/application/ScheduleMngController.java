package application;

import java.io.IOException;
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
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Staff;
import Service.DataManageService;
import application.MessageBoxController.PropType;
import common.CommonFunc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ScheduleMngController implements Initializable {

	@FXML
	private Label lblNowYear;
	@FXML
	private Label lblNowMonth;
	@FXML
	private Label lblSun1;
	@FXML
	private Label lblSun2;
	@FXML
	private Label lblSun3;
	@FXML
	private Label lblSun4;
	@FXML
	private Label lblSun5;
	@FXML
	private Label lblSun6;
	@FXML
	private Label lblMon1;
	@FXML
	private Label lblMon2;
	@FXML
	private Label lblMon3;
	@FXML
	private Label lblMon4;
	@FXML
	private Label lblMon5;
	@FXML
	private Label lblMon6;
	@FXML
	private Label lblTue1;
	@FXML
	private Label lblTue2;
	@FXML
	private Label lblTue3;
	@FXML
	private Label lblTue4;
	@FXML
	private Label lblTue5;
	@FXML
	private Label lblTue6;
	@FXML
	private Label lblWed1;
	@FXML
	private Label lblWed2;
	@FXML
	private Label lblWed3;
	@FXML
	private Label lblWed4;
	@FXML
	private Label lblWed5;
	@FXML
	private Label lblWed6;
	@FXML
	private Label lblThu1;
	@FXML
	private Label lblThu2;
	@FXML
	private Label lblThu3;
	@FXML
	private Label lblThu4;
	@FXML
	private Label lblThu5;
	@FXML
	private Label lblThu6;
	@FXML
	private Label lblFri1;
	@FXML
	private Label lblFri2;
	@FXML
	private Label lblFri3;
	@FXML
	private Label lblFri4;
	@FXML
	private Label lblFri5;
	@FXML
	private Label lblFri6;
	@FXML
	private Label lblSat1;
	@FXML
	private Label lblSat2;
	@FXML
	private Label lblSat3;
	@FXML
	private Label lblSat4;
	@FXML
	private Label lblSat5;
	@FXML
	private Label lblSat6;
	@FXML
	private Hyperlink linkScheSun11;
	@FXML
	private Hyperlink linkScheSun12;
	@FXML
	private Hyperlink linkScheSun13;
	@FXML
	private Hyperlink linkScheSun21;
	@FXML
	private Hyperlink linkScheSun22;
	@FXML
	private Hyperlink linkScheSun23;
	@FXML
	private Hyperlink linkScheSun31;
	@FXML
	private Hyperlink linkScheSun32;
	@FXML
	private Hyperlink linkScheSun33;
	@FXML
	private Hyperlink linkScheSun41;
	@FXML
	private Hyperlink linkScheSun42;
	@FXML
	private Hyperlink linkScheSun43;
	@FXML
	private Hyperlink linkScheSun51;
	@FXML
	private Hyperlink linkScheSun52;
	@FXML
	private Hyperlink linkScheSun53;
	@FXML
	private Hyperlink linkScheSun61;
	@FXML
	private Hyperlink linkScheSun62;
	@FXML
	private Hyperlink linkScheSun63;
	@FXML
	private Hyperlink linkScheMon11;
	@FXML
	private Hyperlink linkScheMon12;
	@FXML
	private Hyperlink linkScheMon13;
	@FXML
	private Hyperlink linkScheMon21;
	@FXML
	private Hyperlink linkScheMon22;
	@FXML
	private Hyperlink linkScheMon23;
	@FXML
	private Hyperlink linkScheMon31;
	@FXML
	private Hyperlink linkScheMon32;
	@FXML
	private Hyperlink linkScheMon33;
	@FXML
	private Hyperlink linkScheMon41;
	@FXML
	private Hyperlink linkScheMon42;
	@FXML
	private Hyperlink linkScheMon43;
	@FXML
	private Hyperlink linkScheMon51;
	@FXML
	private Hyperlink linkScheMon52;
	@FXML
	private Hyperlink linkScheMon53;
	@FXML
	private Hyperlink linkScheMon61;
	@FXML
	private Hyperlink linkScheMon62;
	@FXML
	private Hyperlink linkScheMon63;
	@FXML
	private Hyperlink linkScheTue11;
	@FXML
	private Hyperlink linkScheTue12;
	@FXML
	private Hyperlink linkScheTue13;
	@FXML
	private Hyperlink linkScheTue21;
	@FXML
	private Hyperlink linkScheTue22;
	@FXML
	private Hyperlink linkScheTue23;
	@FXML
	private Hyperlink linkScheTue31;
	@FXML
	private Hyperlink linkScheTue32;
	@FXML
	private Hyperlink linkScheTue33;
	@FXML
	private Hyperlink linkScheTue41;
	@FXML
	private Hyperlink linkScheTue42;
	@FXML
	private Hyperlink linkScheTue43;
	@FXML
	private Hyperlink linkScheTue51;
	@FXML
	private Hyperlink linkScheTue52;
	@FXML
	private Hyperlink linkScheTue53;
	@FXML
	private Hyperlink linkScheTue61;
	@FXML
	private Hyperlink linkScheTue62;
	@FXML
	private Hyperlink linkScheTue63;
	@FXML
	private Hyperlink linkScheWed11;
	@FXML
	private Hyperlink linkScheWed12;
	@FXML
	private Hyperlink linkScheWed13;
	@FXML
	private Hyperlink linkScheWed21;
	@FXML
	private Hyperlink linkScheWed22;
	@FXML
	private Hyperlink linkScheWed23;
	@FXML
	private Hyperlink linkScheWed31;
	@FXML
	private Hyperlink linkScheWed32;
	@FXML
	private Hyperlink linkScheWed33;
	@FXML
	private Hyperlink linkScheWed41;
	@FXML
	private Hyperlink linkScheWed42;
	@FXML
	private Hyperlink linkScheWed43;
	@FXML
	private Hyperlink linkScheWed51;
	@FXML
	private Hyperlink linkScheWed52;
	@FXML
	private Hyperlink linkScheWed53;
	@FXML
	private Hyperlink linkScheWed61;
	@FXML
	private Hyperlink linkScheWed62;
	@FXML
	private Hyperlink linkScheWed63;
	@FXML
	private Hyperlink linkScheThu11;
	@FXML
	private Hyperlink linkScheThu12;
	@FXML
	private Hyperlink linkScheThu13;
	@FXML
	private Hyperlink linkScheThu21;
	@FXML
	private Hyperlink linkScheThu22;
	@FXML
	private Hyperlink linkScheThu23;
	@FXML
	private Hyperlink linkScheThu31;
	@FXML
	private Hyperlink linkScheThu32;
	@FXML
	private Hyperlink linkScheThu33;
	@FXML
	private Hyperlink linkScheThu41;
	@FXML
	private Hyperlink linkScheThu42;
	@FXML
	private Hyperlink linkScheThu43;
	@FXML
	private Hyperlink linkScheThu51;
	@FXML
	private Hyperlink linkScheThu52;
	@FXML
	private Hyperlink linkScheThu53;
	@FXML
	private Hyperlink linkScheThu61;
	@FXML
	private Hyperlink linkScheThu62;
	@FXML
	private Hyperlink linkScheThu63;
	@FXML
	private Hyperlink linkScheFri11;
	@FXML
	private Hyperlink linkScheFri12;
	@FXML
	private Hyperlink linkScheFri13;
	@FXML
	private Hyperlink linkScheFri21;
	@FXML
	private Hyperlink linkScheFri22;
	@FXML
	private Hyperlink linkScheFri23;
	@FXML
	private Hyperlink linkScheFri31;
	@FXML
	private Hyperlink linkScheFri32;
	@FXML
	private Hyperlink linkScheFri33;
	@FXML
	private Hyperlink linkScheFri41;
	@FXML
	private Hyperlink linkScheFri42;
	@FXML
	private Hyperlink linkScheFri43;
	@FXML
	private Hyperlink linkScheFri51;
	@FXML
	private Hyperlink linkScheFri52;
	@FXML
	private Hyperlink linkScheFri53;
	@FXML
	private Hyperlink linkScheFri61;
	@FXML
	private Hyperlink linkScheFri62;
	@FXML
	private Hyperlink linkScheFri63;
	@FXML
	private Hyperlink linkScheSat11;
	@FXML
	private Hyperlink linkScheSat12;
	@FXML
	private Hyperlink linkScheSat13;
	@FXML
	private Hyperlink linkScheSat21;
	@FXML
	private Hyperlink linkScheSat22;
	@FXML
	private Hyperlink linkScheSat23;
	@FXML
	private Hyperlink linkScheSat31;
	@FXML
	private Hyperlink linkScheSat32;
	@FXML
	private Hyperlink linkScheSat33;
	@FXML
	private Hyperlink linkScheSat41;
	@FXML
	private Hyperlink linkScheSat42;
	@FXML
	private Hyperlink linkScheSat43;
	@FXML
	private Hyperlink linkScheSat51;
	@FXML
	private Hyperlink linkScheSat52;
	@FXML
	private Hyperlink linkScheSat53;
	@FXML
	private Hyperlink linkScheSat61;
	@FXML
	private Hyperlink linkScheSat62;
	@FXML
	private Hyperlink linkScheSat63;
	@FXML
	private Button btnYearMonthBefore;
	@FXML
	private Button btnYearMonthNext;
	@FXML
	private TextField txtMeetingMonth;
	@FXML
	private TextField txtMeetingDay;
	@FXML
	private TextField txtStartHour;
	@FXML
	private TextField txtStartMinute;
	@FXML
	private ComboBox<Staff> cmbInterviewer1;
	@FXML
	private ComboBox<Staff> cmbInterviewer2;
	@FXML
	private ComboBox<Staff> cmbInterviewer3;
	@FXML
	private TextField txtApplicant;
	@FXML
	private TextArea txtRemarks;
	@FXML
	private Button btnDetail;
	@FXML
	private Button btnRegist;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private AnchorPane anchorPane;

	private static DataManageService dms;
	private static CommonFunc comFunc;

	// カレンダーに設定される年月
	private LocalDate displayDate;

	// カレンダーの予定リンクIDと、面接日程テーブルのIDの値
	private Map<String, Integer> mapLinkIDScheduleID = new HashMap<String, Integer>();

	// 担当者リスト
	private Map<Integer, Staff> mapStaff = new HashMap<Integer, Staff>();

	// クリックしたリンクの、面接日程テーブルのID
	private int intTargetScheduleID = 0;

	// 初期化処理
	// 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			dms = DataManageService.createInstance();
			comFunc = CommonFunc.createInstance();

			// 今日の日付を設定
			displayDate = LocalDate.now();

			// 画面初期化処理
			initDisplay();

			// 担当者コンボボックス設定
			setIntevierwerCombo();

			// ↓ボタン非活性化
			btnYearMonthBefore.setDisable(true);

			// 登録ボタン非活性化
			// 詳細画面へ、更新、削除ボタン非活性化
			btnDetail.setDisable(true);
			btnRegist.setDisable(false);
			btnUpdate.setDisable(true);
			btnDelete.setDisable(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnYearMonthBefore_Click(ActionEvent event) throws SQLException {

		displayDate = displayDate.minusMonths(1L);

		// 画面初期化処理
		initDisplay();

		// ↓ボタン非活性化
		btnYearMonthBefore.setDisable(true);

		// ↑ボタン活性化
		btnYearMonthNext.setDisable(false);

		// 登録ボタン非活性化
		// 詳細画面へ、更新、削除ボタン非活性化
		btnDetail.setDisable(true);
		btnRegist.setDisable(false);
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
	}

	// ↑ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnYearMonthNext_Click(ActionEvent event) throws SQLException {

		displayDate = displayDate.plusMonths(1L);

		// 画面初期化処理
		initDisplay();

		// ↓ボタン活性化
		btnYearMonthBefore.setDisable(false);

		// ↑ボタン非活性化
		btnYearMonthNext.setDisable(true);

		// 登録ボタン活性化
		// 詳細画面へ、更新、削除ボタン非活性化
		btnDetail.setDisable(true);
		btnRegist.setDisable(false);
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
	}

	// スケジュールリンククリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void hyperlink_Click(ActionEvent event) throws SQLException {

		// 面接日程テーブルのID初期化
		intTargetScheduleID = 0;

		// クリックしたリンクの文字列が空欄の場合
		if(((Hyperlink)event.getSource()).getText().equals("")) {
			// 処理を終了する
			return;
		}

		// クリックしたリンクのIDから、面接日程テーブルのIDを取得
		intTargetScheduleID = mapLinkIDScheduleID.get(((Hyperlink)event.getSource()).getId());

		// 面接日程テーブル、応募者テーブルのデータ取得
		ResultSet rs = dms.getScheduleApplicantData(intTargetScheduleID);

		// 取得したデータを元に、各項目の値を設定
		while(rs.next()) {
			Date dt = rs.getTimestamp("STARTDATETIME");										// 面接開始日時の値
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate startDate = dt.toInstant().atZone(zoneId).toLocalDate();
			Instant instant = dt.toInstant();
			LocalDateTime startDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			String strFirstMonth = String.format("%02d", startDate.getMonthValue()); 	// 月
			String strFirstDay = String.format("%02d", startDateTime.getDayOfMonth());	// 日
			String strHour = String.format("%02d", startDateTime.getHour());				// 時
			String strMinute = String.format("%02d", startDateTime.getMinute());			// 分
			Staff stf1 = null;
			Staff stf2 = null;
			Staff stf3 = null;
			if(rs.getInt("STAFFID1") != 0) {
				stf1 = mapStaff.get(rs.getInt("STAFFID1"));
			}
			if(rs.getInt("STAFFID2") != 0) {
				stf2 = mapStaff.get(rs.getInt("STAFFID2"));
			}
			if(rs.getInt("STAFFID3") != 0) {
				stf3 = mapStaff.get(rs.getInt("STAFFID3"));
			}

			txtMeetingMonth.setText(strFirstMonth);
			txtMeetingDay.setText(strFirstDay);
			txtStartHour.setText(strHour);
			txtStartMinute.setText(strMinute);
			if(rs.getInt("STAFFID1") == 0) {
				cmbInterviewer1.getSelectionModel().select(0);
			} else {
				cmbInterviewer1.getSelectionModel().select(stf1);
			}
			if(rs.getInt("STAFFID2") == 0) {
				cmbInterviewer2.getSelectionModel().select(0);
			} else {
				cmbInterviewer2.getSelectionModel().select(stf2);
			}
			if(rs.getInt("STAFFID3") == 0) {
				cmbInterviewer3.getSelectionModel().select(0);
			} else {
				cmbInterviewer3.getSelectionModel().select(stf3);
			}
			txtApplicant.setText(rs.getString("APPLICANTNAME"));
			txtRemarks.setText(rs.getString("REMARKS"));
		}

		// 登録ボタン非活性化
		// 詳細画面へ、更新、削除ボタン活性化
		btnDetail.setDisable(false);
		btnRegist.setDisable(true);
		btnUpdate.setDisable(false);
		btnDelete.setDisable(false);
	}

	// 登録ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnRegist_Click(ActionEvent event) throws IOException, SQLException {

		// 入力チェック
		// 入力値が不正の場合
		if(isInputErrorCheck()) {
			return;
		}

		// 確認メッセージ表示
	    Alert alrt = new Alert(AlertType.CONFIRMATION);
	    alrt.setTitle("登録");
	    alrt.setHeaderText(null);
	    alrt.setContentText("登録してよろしいですか？");
	    Optional<ButtonType> result = alrt.showAndWait();
	    //OKボタンがクリックされたら
	    if (result.get() == ButtonType.OK) {

	    	// 面接日、開始時間から開始日時を作成
	    	String strStartDate = lblNowYear.getText() + "/" + txtMeetingMonth.getText() +
	    							"/" + txtMeetingDay.getText() + " " + txtStartHour.getText() +
	    							":" + txtStartMinute.getText() + ":00";
	    	String strEndDate = lblNowYear.getText() + "/" + txtMeetingMonth.getText() +
									"/" + txtMeetingDay.getText() + " "
									+ String.valueOf(Integer.parseInt(txtStartHour.getText()) + 1) +
									":" + txtStartMinute.getText() + ":00";

	    	// 入力された値で、データ登録処理を実施
	    	int resultCount = dms.insertScheduleApplicantData(strStartDate, strEndDate,
	    							cmbInterviewer1.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				cmbInterviewer2.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				cmbInterviewer3.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				txtApplicant.getText(), txtRemarks.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "登録が完了しました。");
		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "登録処理が失敗しました。");
		    }
	    }
	}

	// 更新ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnUpdate_Click(ActionEvent event) throws IOException, SQLException {

		// 入力チェック
		// 入力値が不正の場合
		if(isInputErrorCheck()) {
			return;
		}

		// 確認メッセージ表示
	    Alert alrt = new Alert(AlertType.CONFIRMATION);
	    alrt.setTitle("更新");
	    alrt.setHeaderText(null);
	    alrt.setContentText("更新してよろしいですか？");
	    Optional<ButtonType> result = alrt.showAndWait();
	    //OKボタンがクリックされたら
	    if (result.get() == ButtonType.OK) {

	    	// 面接日、開始時間から開始日時を作成
	    	String strStartDate = lblNowYear.getText() + "/" + txtMeetingMonth.getText() +
	    							"/" + txtMeetingDay.getText() + " " + txtStartHour.getText() +
	    							":" + txtStartMinute.getText() + ":00";
	    	String strEndDate = lblNowYear.getText() + "/" + txtMeetingMonth.getText() +
									"/" + txtMeetingDay.getText() + " "
									+ String.valueOf(Integer.parseInt(txtStartHour.getText()) + 1) +
									":" + txtStartMinute.getText() + ":00";

	    	// 入力された値で、データ更新処理を実施
	    	int resultCount = dms.updateScheduleApplicantData(intTargetScheduleID, strStartDate, strEndDate,
	    							cmbInterviewer1.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				cmbInterviewer2.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				cmbInterviewer3.getSelectionModel().getSelectedItem().getStaffId(),
	    			 				txtApplicant.getText(), txtRemarks.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "更新が完了しました。");
		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "更新処理が失敗しました。");
		    }
	    }
	}

	// 削除ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnDelete_Click(ActionEvent event) throws IOException, SQLException {

		// 確認メッセージ表示
	    Alert alrt = new Alert(AlertType.CONFIRMATION);
	    alrt.setTitle("削除");
	    alrt.setHeaderText(null);
	    alrt.setContentText("削除してよろしいですか？");
	    Optional<ButtonType> result = alrt.showAndWait();
	    //OKボタンがクリックされたら
	    if (result.get() == ButtonType.OK) {

	    	// 入力された値で、データ削除処理を実施
	    	int resultCount = dms.deleteScheduleData(intTargetScheduleID);
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "削除が完了しました。");
		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "削除処理が失敗しました。");
		    }
	    }
	}

	// 詳細画面へボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	private void btnDetail_Click(ActionEvent event) throws IOException {
		ScheduleDetailController.intTargetScheduleID = intTargetScheduleID;
		BorderPane bp = (BorderPane)anchorPane.parentProperty().get();
		Parent root = FXMLLoader.load(getClass().getResource("ScheduleDetail.fxml"));
		bp.setCenter(root);
	}

	// カレンダー初期化
	// 引数
	// なし
	// 戻り値
	// なし
	private void initElements() {
		lblNowYear.setText(""); lblNowMonth.setText("");
		lblSun1.setText(""); lblSun2.setText(""); lblSun3.setText(""); lblSun4.setText(""); lblSun5.setText(""); lblSun6.setText("");
		lblMon1.setText(""); lblMon2.setText(""); lblMon3.setText(""); lblMon4.setText(""); lblMon5.setText(""); lblMon6.setText("");
		lblTue1.setText(""); lblTue2.setText(""); lblTue3.setText(""); lblTue4.setText(""); lblTue5.setText(""); lblTue6.setText("");
		lblWed1.setText(""); lblWed2.setText(""); lblWed3.setText(""); lblWed4.setText(""); lblWed5.setText(""); lblWed6.setText("");
		lblThu1.setText(""); lblThu2.setText(""); lblThu3.setText(""); lblThu4.setText(""); lblThu5.setText(""); lblThu6.setText("");
		lblFri1.setText(""); lblFri2.setText(""); lblFri3.setText(""); lblFri4.setText(""); lblFri5.setText(""); lblFri6.setText("");
		lblSat1.setText(""); lblSat2.setText(""); lblSat3.setText(""); lblSat4.setText(""); lblSat5.setText(""); lblSat6.setText("");
		linkScheSun11.setText(""); linkScheSun12.setText(""); linkScheSun13.setText(""); linkScheSun21.setText(""); linkScheSun22.setText(""); linkScheSun23.setText("");
		linkScheSun31.setText(""); linkScheSun32.setText(""); linkScheSun33.setText(""); linkScheSun41.setText(""); linkScheSun42.setText(""); linkScheSun43.setText("");
		linkScheSun51.setText(""); linkScheSun52.setText(""); linkScheSun53.setText(""); linkScheSun61.setText(""); linkScheSun62.setText(""); linkScheSun63.setText("");
		linkScheMon11.setText(""); linkScheMon12.setText(""); linkScheMon13.setText(""); linkScheMon21.setText(""); linkScheMon22.setText(""); linkScheMon23.setText("");
		linkScheMon31.setText(""); linkScheMon32.setText(""); linkScheMon33.setText(""); linkScheMon41.setText(""); linkScheMon42.setText(""); linkScheMon43.setText("");
		linkScheMon51.setText(""); linkScheMon52.setText(""); linkScheMon53.setText(""); linkScheMon61.setText(""); linkScheMon62.setText(""); linkScheMon63.setText("");
		linkScheTue11.setText(""); linkScheTue12.setText(""); linkScheTue13.setText(""); linkScheTue21.setText(""); linkScheTue22.setText(""); linkScheTue23.setText("");
		linkScheTue31.setText(""); linkScheTue32.setText(""); linkScheTue33.setText(""); linkScheTue41.setText(""); linkScheTue42.setText(""); linkScheTue43.setText("");
		linkScheTue51.setText(""); linkScheTue52.setText(""); linkScheTue53.setText(""); linkScheTue61.setText(""); linkScheTue62.setText(""); linkScheTue63.setText("");
		linkScheWed11.setText(""); linkScheWed12.setText(""); linkScheWed13.setText(""); linkScheWed21.setText(""); linkScheWed22.setText(""); linkScheWed23.setText("");
		linkScheWed31.setText(""); linkScheWed32.setText(""); linkScheWed33.setText(""); linkScheWed41.setText(""); linkScheWed42.setText(""); linkScheWed43.setText("");
		linkScheWed51.setText(""); linkScheWed52.setText(""); linkScheWed53.setText(""); linkScheWed61.setText(""); linkScheWed62.setText(""); linkScheWed63.setText("");
		linkScheThu11.setText(""); linkScheThu12.setText(""); linkScheThu13.setText(""); linkScheThu21.setText(""); linkScheThu22.setText(""); linkScheThu23.setText("");
		linkScheThu31.setText(""); linkScheThu32.setText(""); linkScheThu33.setText(""); linkScheThu41.setText(""); linkScheThu42.setText(""); linkScheThu43.setText("");
		linkScheThu51.setText(""); linkScheThu52.setText(""); linkScheThu53.setText(""); linkScheThu61.setText(""); linkScheThu62.setText(""); linkScheThu63.setText("");
		linkScheFri11.setText(""); linkScheFri12.setText(""); linkScheFri13.setText(""); linkScheFri21.setText(""); linkScheFri22.setText(""); linkScheFri23.setText("");
		linkScheFri31.setText(""); linkScheFri32.setText(""); linkScheFri33.setText(""); linkScheFri41.setText(""); linkScheFri42.setText(""); linkScheFri43.setText("");
		linkScheFri51.setText(""); linkScheFri52.setText(""); linkScheFri53.setText(""); linkScheFri61.setText(""); linkScheFri62.setText(""); linkScheFri63.setText("");
		linkScheSat11.setText(""); linkScheSat12.setText(""); linkScheSat13.setText(""); linkScheSat21.setText(""); linkScheSat22.setText(""); linkScheSat23.setText("");
		linkScheSat31.setText(""); linkScheSat32.setText(""); linkScheSat33.setText(""); linkScheSat41.setText(""); linkScheSat42.setText(""); linkScheSat43.setText("");
		linkScheSat51.setText(""); linkScheSat52.setText(""); linkScheSat53.setText(""); linkScheSat61.setText(""); linkScheSat62.setText(""); linkScheSat63.setText("");
		txtMeetingMonth.setText(""); txtMeetingDay.setText(""); txtStartHour.setText(""); txtStartMinute.setText("");
		cmbInterviewer1.getSelectionModel().select(0); cmbInterviewer2.getSelectionModel().select(0); cmbInterviewer3.getSelectionModel().select(0);
		txtApplicant.setText(""); txtRemarks.setText(""); mapLinkIDScheduleID.clear(); intTargetScheduleID = 0;
	}

	// 入力エリア初期化処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initInputArea() {
		txtMeetingMonth.setText("");
		txtMeetingDay.setText("");
		txtStartHour.setText("");
		txtStartMinute.setText("");
		cmbInterviewer1.getSelectionModel().select(0);
		cmbInterviewer2.getSelectionModel().select(0);
		cmbInterviewer3.getSelectionModel().select(0);
		txtApplicant.setText("");
		txtRemarks.setText("");
	}

	// 画面初期化処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {
    	// 各カレンダー、各スケジュール初期化
		initElements();
		// 現在年月ラベル設定
		lblNowYear.setText(String.valueOf(displayDate.getYear()));
		lblNowMonth.setText(String.valueOf(displayDate.getMonthValue()));
		// カレンダーの日付を設定
		setCalendarDays(displayDate);
    	// カレンダーの予定を設定
		setCalendarSchedule(displayDate);
		// 入力エリアの初期化
    	initInputArea();
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

		// 各コンボボックスに項目を設定
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

	// 入力値チェック処理
	// 引数
	// LocalDate nowDate
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

		// 面接日が空欄の場合
		if(txtMeetingMonth.getText().equals("") ||
				txtMeetingDay.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "面接日が空欄です。");

			// 面接日にフォーカス
			txtMeetingMonth.requestFocus();

			return true;
		}

		// 開始時間が空欄の場合
		if(txtStartHour.getText().equals("") ||
				txtStartMinute.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "開始時間が空欄です。");

			// 開始時間にフォーカス
			txtStartHour.requestFocus();

			return true;
		}

		// 担当者がいずれも未選択の場合
		if(cmbInterviewer1.getSelectionModel().getSelectedIndex() == 0 &&
				cmbInterviewer2.getSelectionModel().getSelectedIndex() == 0 &&
				cmbInterviewer3.getSelectionModel().getSelectedIndex() == 0) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "担当者が未選択です。");

			// 開始時間にフォーカス
			cmbInterviewer1.requestFocus();

			return true;
		}

		// 応募者が空欄の場合
		if(txtApplicant.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "応募者が空欄です。");

			// 応募者にフォーカス
			txtApplicant.requestFocus();

			return true;
		}

		return false;
	}

	// カレンダー日付設定処理
	// 引数
	// LocalDate nowDate
	// 戻り値
	// なし
	private void setCalendarDays(LocalDate argDate) {

		LocalDate firstDate = argDate.withDayOfMonth(1); 							// 月の1日
		int intfirstDayOfWeek = firstDate.getDayOfWeek().getValue();				// 月の1日の曜日(数値)
		int intNowYear = argDate.getYear();										// 今年
		int intNowMonth = argDate.getMonthValue();								// 今月

		// 月の1日の曜日で場合分け
		switch(intfirstDayOfWeek) {
		// 月曜日の場合
		case 1:
			                      lblSun2.setText("7");  lblSun3.setText("14"); lblSun4.setText("21"); lblSun5.setText("28");
			lblMon1.setText("1"); lblMon2.setText("8");  lblMon3.setText("15"); lblMon4.setText("22");
			lblTue1.setText("2"); lblTue2.setText("9");  lblTue3.setText("16"); lblTue4.setText("23");
			lblWed1.setText("3"); lblWed2.setText("10"); lblWed3.setText("17"); lblWed4.setText("24");
			lblThu1.setText("4"); lblThu2.setText("11"); lblThu3.setText("18"); lblThu4.setText("25");
			lblFri1.setText("5"); lblFri2.setText("12"); lblFri3.setText("19"); lblFri4.setText("26");
			lblSat1.setText("6"); lblSat2.setText("13"); lblSat3.setText("20"); lblSat4.setText("27");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblMon5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblMon5.setText("29"); lblTue5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblMon5.setText("29"); lblTue5.setText("30"); lblWed5.setText("31");
				break;
			}
			break;
		// 火曜日の場合
		case 2:
			                      lblSun2.setText("6");  lblSun3.setText("13"); lblSun4.setText("20"); lblSun5.setText("27");
			                      lblMon2.setText("7");  lblMon3.setText("14"); lblMon4.setText("21"); lblMon5.setText("28");
			lblTue1.setText("1"); lblTue2.setText("8");  lblTue3.setText("15"); lblTue4.setText("22");
			lblWed1.setText("2"); lblWed2.setText("9");  lblWed3.setText("16"); lblWed4.setText("23");
			lblThu1.setText("3"); lblThu2.setText("10"); lblThu3.setText("17"); lblThu4.setText("24");
			lblFri1.setText("4"); lblFri2.setText("11"); lblFri3.setText("18"); lblFri4.setText("25");
			lblSat1.setText("5"); lblSat2.setText("12"); lblSat3.setText("19"); lblSat4.setText("26");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblTue5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblTue5.setText("29"); lblWed5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblTue5.setText("29"); lblWed5.setText("30"); lblThu5.setText("31");
				break;
			}
			break;
		// 水曜日の場合
		case 3:
			                      lblSun2.setText("5");  lblSun3.setText("12"); lblSun4.setText("19"); lblSun5.setText("26");
			                      lblMon2.setText("6");  lblMon3.setText("13"); lblMon4.setText("20"); lblMon5.setText("27");
			                      lblTue2.setText("7");  lblTue3.setText("14"); lblTue4.setText("21"); lblTue5.setText("28");
			lblWed1.setText("1"); lblWed2.setText("8");  lblWed3.setText("15"); lblWed4.setText("22");
			lblThu1.setText("2"); lblThu2.setText("9");  lblThu3.setText("16"); lblThu4.setText("23");
			lblFri1.setText("3"); lblFri2.setText("10"); lblFri3.setText("17"); lblFri4.setText("24");
			lblSat1.setText("4"); lblSat2.setText("11"); lblSat3.setText("18"); lblSat4.setText("25");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblMon5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblMon5.setText("29"); lblTue5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblMon5.setText("29"); lblTue5.setText("30"); lblWed5.setText("31");
				break;
			}
			break;
		// 木曜日の場合
		case 4:
			                      lblSun2.setText("4");  lblSun3.setText("11"); lblSun4.setText("18"); lblSun5.setText("25");
			                      lblMon2.setText("5");  lblMon3.setText("12"); lblMon4.setText("19"); lblMon5.setText("26");
			                      lblTue2.setText("6");  lblTue3.setText("13"); lblTue4.setText("20"); lblTue5.setText("27");
			                      lblWed2.setText("7");  lblWed3.setText("14"); lblWed4.setText("21"); lblWed5.setText("28");
			lblThu1.setText("1"); lblThu2.setText("8");  lblThu3.setText("15"); lblThu4.setText("22");
			lblFri1.setText("2"); lblFri2.setText("9");  lblFri3.setText("16"); lblFri4.setText("23");
			lblSat1.setText("3"); lblSat2.setText("10"); lblSat3.setText("17"); lblSat4.setText("24");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblThu5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblThu5.setText("29"); lblFri5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblThu5.setText("29"); lblFri5.setText("30"); lblSat5.setText("31");
				break;
			}
			break;
		// 金曜日の場合
		case 5:
			                      lblSun2.setText("3"); lblSun3.setText("10"); lblSun4.setText("17"); lblSun5.setText("24");
			                      lblMon2.setText("4"); lblMon3.setText("11"); lblMon4.setText("18"); lblMon5.setText("25");
			                      lblTue2.setText("5"); lblTue3.setText("12"); lblTue4.setText("19"); lblTue5.setText("26");
			                      lblWed2.setText("6"); lblWed3.setText("13"); lblWed4.setText("20"); lblWed5.setText("27");
			                      lblThu2.setText("7"); lblThu3.setText("14"); lblThu4.setText("21"); lblThu5.setText("28");
			lblFri1.setText("1"); lblFri2.setText("8"); lblFri3.setText("15"); lblFri4.setText("22");
			lblSat1.setText("2"); lblSat2.setText("9"); lblSat3.setText("16"); lblSat4.setText("23");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblFri5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblFri5.setText("29"); lblSat5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblFri5.setText("29"); lblSat5.setText("30"); lblSun6.setText("31");
				break;
			}
			break;
		// 土曜日の場合
		case 6:
			                      lblSun2.setText("2"); lblSun3.setText("9");  lblSun4.setText("16"); lblSun5.setText("23");
			                      lblMon2.setText("3"); lblMon3.setText("10"); lblMon4.setText("17"); lblMon5.setText("24");
			                      lblTue2.setText("4"); lblTue3.setText("11"); lblTue4.setText("18"); lblTue5.setText("25");
			                      lblWed2.setText("5"); lblWed3.setText("12"); lblWed4.setText("19"); lblWed5.setText("26");
			                      lblThu2.setText("6"); lblThu3.setText("13"); lblThu4.setText("20"); lblThu5.setText("27");
			                      lblFri2.setText("7"); lblFri3.setText("14"); lblFri4.setText("21"); lblFri5.setText("28");
			lblSat1.setText("1"); lblSat2.setText("8"); lblSat3.setText("15"); lblSat4.setText("22");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblSun5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblSun5.setText("29"); lblMon6.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblSun5.setText("29"); lblMon6.setText("30"); lblTue6.setText("31");
				break;
			}
			break;
		// 日曜日の場合
		case 7:
			lblSun1.setText("1"); lblSun2.setText("8");  lblSun3.setText("15"); lblSun4.setText("22");
			lblMon1.setText("2"); lblMon2.setText("9");  lblMon3.setText("16"); lblMon4.setText("23");
			lblTue1.setText("3"); lblTue2.setText("10"); lblTue3.setText("17"); lblTue4.setText("24");
			lblWed1.setText("4"); lblWed2.setText("11"); lblWed3.setText("18"); lblWed4.setText("25");
			lblThu1.setText("5"); lblThu2.setText("12"); lblThu3.setText("19"); lblThu4.setText("26");
			lblFri1.setText("6"); lblFri2.setText("13"); lblFri3.setText("20"); lblFri4.setText("27");
			lblSat1.setText("7"); lblSat2.setText("14"); lblSat3.setText("21"); lblSat4.setText("28");
			// うるう年の2月の場合：29日の月
			//「年を4で割り切れる」かつ『「年を100で割り切れない」または「年を400で割り切れる」』
			if(intNowMonth == 2 &&
					intNowYear % 4 == 0 && (intNowYear % 100 != 0 || intNowYear % 400 == 0)) {
				lblSun5.setText("29");
				break;
			}
			// 4、6、9、11月の場合：30日の月
			if(intNowMonth == 4 || intNowMonth == 6 || intNowMonth == 9 || intNowMonth == 11) {
				lblSun5.setText("29"); lblMon5.setText("30");
				break;
			}
			// 1、3、5、7、8、10、12月の場合：31日の月
			if(intNowMonth == 1 || intNowMonth == 3 || intNowMonth == 5 || intNowMonth == 7 ||
					intNowMonth == 8 || intNowMonth == 10 || intNowMonth == 12) {
				lblSun5.setText("29"); lblMon5.setText("30"); lblTue5.setText("31");
				break;
			}
			break;
		}
	}

	// カレンダースケジュール設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void setCalendarSchedule(LocalDate displayDate) throws SQLException {

		String strNowYearMonth = String.valueOf(displayDate.getYear()) + "-"
				+ String.format("%02d", displayDate.getMonthValue());

		// スケジュールデータ取得
		ResultSet rs =dms.getScheduleData(strNowYearMonth);

		while(rs.next()) {

			Date dt = rs.getTimestamp("STARTDATETIME");										// 面接開始日時の値
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate startDate = dt.toInstant().atZone(zoneId).toLocalDate();
			Instant instant = dt.toInstant();
			LocalDateTime startDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			LocalDate firstDate = startDate.withDayOfMonth(1); 							// 月の1日
			int intfirstDayOfWeek = firstDate.getDayOfWeek().getValue();					// 月の1日の曜日(数値)
			int intDate = startDateTime.getDayOfMonth();									// 日にち
			String hourMinute = String.format("%02d", startDateTime.getHour()) + ":"
					+ String.format("%02d", startDateTime.getMinute());					// 時:分(文字列)

			// 各スケジュールをリンクに設定
			// 月の1日の曜日で場合分け
			switch(intfirstDayOfWeek) {
				// 月曜日の場合
				case 1:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheMon11.getText().equals("")) {
								linkScheMon11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon12.getText().equals("")) {
								linkScheMon12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheTue11.getText().equals("")) {
								linkScheTue11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue12.getText().equals("")) {
								linkScheTue12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheWed11.getText().equals("")) {
								linkScheWed11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed12.getText().equals("")) {
								linkScheWed12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheThu11.getText().equals("")) {
								linkScheThu11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu12.getText().equals("")) {
								linkScheThu12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 火曜日の場合
				case 2:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheTue11.getText().equals("")) {
								linkScheTue11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue12.getText().equals("")) {
								linkScheTue12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheWed11.getText().equals("")) {
								linkScheWed11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed12.getText().equals("")) {
								linkScheWed12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheThu11.getText().equals("")) {
								linkScheThu11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu12.getText().equals("")) {
								linkScheThu12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheThu51.getText().equals("")) {
								linkScheThu51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu52.getText().equals("")) {
								linkScheThu52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 水曜日の場合
				case 3:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheWed11.getText().equals("")) {
								linkScheWed11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed12.getText().equals("")) {
								linkScheWed12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheThu11.getText().equals("")) {
								linkScheThu11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu12.getText().equals("")) {
								linkScheThu12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheThu51.getText().equals("")) {
								linkScheThu51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu52.getText().equals("")) {
								linkScheThu52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheFri51.getText().equals("")) {
								linkScheFri51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri52.getText().equals("")) {
								linkScheFri52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 木曜日の場合
				case 4:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheThu11.getText().equals("")) {
								linkScheThu11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu12.getText().equals("")) {
								linkScheThu12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheThu51.getText().equals("")) {
								linkScheThu51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu52.getText().equals("")) {
								linkScheThu52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheFri51.getText().equals("")) {
								linkScheFri51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri52.getText().equals("")) {
								linkScheFri52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheSat51.getText().equals("")) {
								linkScheSat51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat52.getText().equals("")) {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 金曜日の場合
				case 5:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheThu51.getText().equals("")) {
								linkScheThu51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu52.getText().equals("")) {
								linkScheThu52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheFri51.getText().equals("")) {
								linkScheFri51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri52.getText().equals("")) {
								linkScheFri52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheSat51.getText().equals("")) {
								linkScheSat51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat52.getText().equals("")) {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheSun61.getText().equals("")) {
								linkScheSun61.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun61.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun62.getText().equals("")) {
								linkScheSun62.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun62.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun63.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun63.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 土曜日の場合
				case 6:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheWed51.getText().equals("")) {
								linkScheWed51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed52.getText().equals("")) {
								linkScheWed52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheThu51.getText().equals("")) {
								linkScheThu51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu52.getText().equals("")) {
								linkScheThu52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheFri51.getText().equals("")) {
								linkScheFri51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri52.getText().equals("")) {
								linkScheFri52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheSat51.getText().equals("")) {
								linkScheSat51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat52.getText().equals("")) {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat52.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheSun61.getText().equals("")) {
								linkScheSun61.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun61.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun62.getText().equals("")) {
								linkScheSun62.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun62.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun63.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun63.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheMon61.getText().equals("")) {
								linkScheMon61.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon61.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon62.getText().equals("")) {
								linkScheMon62.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon62.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon63.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon63.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
				// 日曜日の場合
				case 7:
					// 面接開始日で場合分け
					switch(intDate) {
						case 1:
							if(linkScheSun11.getText().equals("")) {
								linkScheSun11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun12.getText().equals("")) {
								linkScheSun12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 2:
							if(linkScheMon11.getText().equals("")) {
								linkScheMon11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon12.getText().equals("")) {
								linkScheMon12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 3:
							if(linkScheTue11.getText().equals("")) {
								linkScheTue11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue12.getText().equals("")) {
								linkScheTue12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 4:
							if(linkScheWed11.getText().equals("")) {
								linkScheWed11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed12.getText().equals("")) {
								linkScheWed12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 5:
							if(linkScheThu11.getText().equals("")) {
								linkScheThu11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu12.getText().equals("")) {
								linkScheThu12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 6:
							if(linkScheFri11.getText().equals("")) {
								linkScheFri11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri12.getText().equals("")) {
								linkScheFri12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 7:
							if(linkScheSat11.getText().equals("")) {
								linkScheSat11.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat11.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat12.getText().equals("")) {
								linkScheSat12.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat12.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat13.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat13.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 8:
							if(linkScheSun21.getText().equals("")) {
								linkScheSun21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun22.getText().equals("")) {
								linkScheSun22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 9:
							if(linkScheMon21.getText().equals("")) {
								linkScheMon21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon22.getText().equals("")) {
								linkScheMon22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 10:
							if(linkScheTue21.getText().equals("")) {
								linkScheTue21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue22.getText().equals("")) {
								linkScheTue22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 11:
							if(linkScheWed21.getText().equals("")) {
								linkScheWed21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed22.getText().equals("")) {
								linkScheWed22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 12:
							if(linkScheThu21.getText().equals("")) {
								linkScheThu21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu22.getText().equals("")) {
								linkScheThu22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 13:
							if(linkScheFri21.getText().equals("")) {
								linkScheFri21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri22.getText().equals("")) {
								linkScheFri22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 14:
							if(linkScheSat21.getText().equals("")) {
								linkScheSat21.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat21.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat22.getText().equals("")) {
								linkScheSat22.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat22.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat23.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat23.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 15:
							if(linkScheSun31.getText().equals("")) {
								linkScheSun31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun32.getText().equals("")) {
								linkScheSun32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 16:
							if(linkScheMon31.getText().equals("")) {
								linkScheMon31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon32.getText().equals("")) {
								linkScheMon32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 17:
							if(linkScheTue31.getText().equals("")) {
								linkScheTue31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue32.getText().equals("")) {
								linkScheTue32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 18:
							if(linkScheWed31.getText().equals("")) {
								linkScheWed31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed32.getText().equals("")) {
								linkScheWed32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 19:
							if(linkScheThu31.getText().equals("")) {
								linkScheThu31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu32.getText().equals("")) {
								linkScheThu32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 20:
							if(linkScheFri31.getText().equals("")) {
								linkScheFri31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri32.getText().equals("")) {
								linkScheFri32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 21:
							if(linkScheSat31.getText().equals("")) {
								linkScheSat31.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat31.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat32.getText().equals("")) {
								linkScheSat32.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat32.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat33.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat33.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 22:
							if(linkScheSun41.getText().equals("")) {
								linkScheSun41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun42.getText().equals("")) {
								linkScheSun42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 23:
							if(linkScheMon41.getText().equals("")) {
								linkScheMon41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon42.getText().equals("")) {
								linkScheMon42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 24:
							if(linkScheTue41.getText().equals("")) {
								linkScheTue41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue42.getText().equals("")) {
								linkScheTue42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 25:
							if(linkScheWed41.getText().equals("")) {
								linkScheWed41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheWed42.getText().equals("")) {
								linkScheWed42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheWed43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheWed43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 26:
							if(linkScheThu41.getText().equals("")) {
								linkScheThu41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheThu42.getText().equals("")) {
								linkScheThu42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheThu43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheThu43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 27:
							if(linkScheFri41.getText().equals("")) {
								linkScheFri41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheFri42.getText().equals("")) {
								linkScheFri42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheFri43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheFri43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 28:
							if(linkScheSat41.getText().equals("")) {
								linkScheSat41.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat41.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSat42.getText().equals("")) {
								linkScheSat42.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat42.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSat43.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSat43.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 29:
							if(linkScheSun51.getText().equals("")) {
								linkScheSun51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheSun52.getText().equals("")) {
								linkScheSun52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheSun53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheSun53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 30:
							if(linkScheMon51.getText().equals("")) {
								linkScheMon51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheMon52.getText().equals("")) {
								linkScheMon52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheMon53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheMon53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
						case 31:
							if(linkScheTue51.getText().equals("")) {
								linkScheTue51.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue51.getId(), rs.getInt("SCHEDULEID"));
							} else if (linkScheTue52.getText().equals("")) {
								linkScheTue52.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue52.getId(), rs.getInt("SCHEDULEID"));
							} else {
								linkScheTue53.setText(hourMinute);
								mapLinkIDScheduleID.put(linkScheTue53.getId(), rs.getInt("SCHEDULEID"));
							}
							break;
					}
					break;
			}
		}
	}
}

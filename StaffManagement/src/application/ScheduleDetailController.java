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
import java.util.ResourceBundle;

import Model.ScheduleListItem;
import Model.Staff;
import Service.DataManageService;
import application.MessageBoxController.MessageResponse;
import application.MessageBoxController.PropType;
import common.CommonFunc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	public static int intScheduleId = 0;

	// 担当者リスト
	private Map<Integer, Staff> mapStaff = new HashMap<Integer, Staff>();
	// スケジュール候補リスト


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

			// 面接日程ID以外の項目を初期化
			setItemsFromRs(PropClearType.firstShow);
		}
		// 面接日程管理画面から遷移した場合
		else
		{
			// 面接日程テーブル、応募者テーブルのデータ取得
			ResultSet rs = dms.getScheduleApplicantData(intTargetScheduleID);

			// 取得したデータを元に、各項目の値を設定
			while(rs.next()) {
				setItemsFromRs(rs);
			}
		}
	}

	// 検索ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
	@FXML
	private void btnSearch_Click(ActionEvent event) throws IOException, SQLException {

		// listItemsを初期化
		ScheduleDetailListController.initListItems();

		// 入力がない場合
		if(txtSearchScheId.getText().equals("") && datePicker.getValue() == null && txtSearchHour.getText().equals("") &&
				txtSearchMinute.getText().equals("") && txtSearchAplId.getText().equals("") && txtSearchAplName.getText().equals("")) {
					// 検索せず処理を終了する
					return;
		}

		// 検索条件からデータを取得
		ResultSet rs = dms.getScheduleListData(txtSearchScheId.getText(), datePicker.getValue(),
							txtSearchHour.getText() + ":" + txtSearchMinute.getText(),
							txtSearchAplId.getText(), txtSearchAplName.getText());

		int rsCount = 0;

		while(rs.next()) {
			Date date = rs.getTimestamp("STARTDATETIME");
			Instant instantSt = date.toInstant();
			LocalDateTime startDateTime = LocalDateTime.ofInstant(instantSt, ZoneId.systemDefault());

			ScheduleListItem sItem
				= new ScheduleListItem(rs.getInt("SCHEDULEID"), startDateTime,
						rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"));
			ScheduleDetailListController.getListItems().add(sItem);

			rsCount++;
		}

		// データ取得結果が2件以上の場合
		if(rsCount > 1) {
			// スケジュール候補リストを表示
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("ScheduleDetailList.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.showAndWait();

			// スケジュール候補リストでキャンセルを押した場合
			if(ScheduleDetailListController.selectedId == 0) {
				// 検索せず処理を終了する
				return;
			}

			// スケジュール候補リストの選択値からデータ取得
			rs = dms.getScheduleApplicantData(ScheduleDetailListController.selectedId);
			while(rs.next()) {
				// 画面の各項目設定
				setItemsFromRs(rs);
			}

			// 登録非活性化
			// 更新、削除ボタン活性化
			btnRegist.setDisable(true);
			btnUpdate.setDisable(false);
			btnDelete.setDisable(false);
		}
		// データ取得結果が1件の場合
		else if(rsCount == 1) {

			 // 検索条件からデータを取得
			 ResultSet rset = dms.getScheduleListData(txtSearchScheId.getText(), datePicker.getValue(),
					txtSearchHour.getText() + ":" + txtSearchMinute.getText(), txtSearchAplId.getText(), txtSearchAplName.getText());
			while(rset.next()) {
				// 画面の各項目設定
				setItemsFromRs(rset);
			}

			// 登録非活性化
			// 更新、削除ボタン活性化
			btnRegist.setDisable(true);
			btnUpdate.setDisable(false);
			btnDelete.setDisable(false);
		}
		// 上記以外の場合
		else {
			return;
		}
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
		comFunc.showMessage("MessageBox", PropType.Confirm, "登録してよろしいですか？");

		// OKボタンをクリックした場合
		if(comFunc.getResponse() == MessageResponse.OK) {
			int nowYear = LocalDate.now().getYear();

			// 面接日、時間から開始日時、終了日時を作成
			String strStartDate = String.valueOf(nowYear) + "/" + txtMeetingMonth.getText() +
									"/" + txtMeetingDay.getText() + " " + txtStartHour.getText() +
									":" + txtStartMinute.getText() + ":00";
			String strEndDate = String.valueOf(nowYear) + "/" + txtMeetingMonth.getText() +
									"/" + txtMeetingDay.getText() + " " + txtEndHour.getText() +
									":" + txtEndMinute.getText() + ":00";

			// 入力された値で、データ登録処理を実施
			int resultCount = dms.insertScheduleAplcantDetail(
										Integer.parseInt(lblScheduleId.getText()), strStartDate, strEndDate,
										txtImportance.getText(), txtExecPlace.getText(),
										cmbInterviewer1.getSelectionModel().getSelectedItem().getStaffId(),
						 				cmbInterviewer2.getSelectionModel().getSelectedItem().getStaffId(),
						 				cmbInterviewer3.getSelectionModel().getSelectedItem().getStaffId(),
						 				txtApplicantName.getText(), txtRemarks.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "登録が完了しました。");
		    	// IDの初期化
		    	intTargetScheduleID = 0;
		    	intScheduleId = 0;

		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "登録処理が失敗しました。");
		    }
		}
	}

	// 更新ボタンクリックイベント
	// 引数
	// event
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
		comFunc.showMessage("MessageBox", PropType.Confirm, "更新してよろしいですか？");

		// OKボタンをクリックした場合
	    if (comFunc.getResponse() == MessageResponse.OK) {
	    	int nowYear = LocalDate.now().getYear();

	    	// 面接日、開始時間から開始日時を作成
	    	String strStartDate = String.valueOf(nowYear) + "/" + txtMeetingMonth.getText() +
	    							"/" + txtMeetingDay.getText() + " " + txtStartHour.getText() +
	    							":" + txtStartMinute.getText() + ":00";
	    	String strEndDate = String.valueOf(nowYear) + "/" + txtMeetingMonth.getText() +
									"/" + txtMeetingDay.getText() + " "
									+ String.valueOf(Integer.parseInt(txtStartHour.getText()) + 1) +
									":" + txtStartMinute.getText() + ":00";

	    	// 入力された値で、データ更新処理を実施
	    	int resultCount = dms.updateScheduleAplcantDetail(
										Integer.parseInt(lblScheduleId.getText()), strStartDate, strEndDate,
										txtImportance.getText(), txtExecPlace.getText(),
										cmbInterviewer1.getSelectionModel().getSelectedItem().getStaffId(),
						 				cmbInterviewer2.getSelectionModel().getSelectedItem().getStaffId(),
						 				cmbInterviewer3.getSelectionModel().getSelectedItem().getStaffId(),
						 				txtApplicantName.getText(), txtRemarks.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "更新が完了しました。");
		    	// IDの初期化
		    	intTargetScheduleID = 0;
		    	intScheduleId = 0;

		    	// 画面初期化
		    	initDisplay();

				// 登録活性化
				// 更新、削除ボタン非活性化
				btnRegist.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "更新処理が失敗しました。");
		    }
	    }
	}

	// 削除ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
	@FXML
	private void btnDelete_Click(ActionEvent event) throws IOException, SQLException {
		// 確認メッセージ表示
		comFunc.showMessage("MessageBox", PropType.Confirm, "削除してよろしいですか？");

		// OKボタンをクリックした場合
		if (comFunc.getResponse() == MessageResponse.OK) {

	    	// 入力された値で、データ削除処理を実施
	    	int resultCount = dms.deleteScheduleData(Integer.parseInt(lblScheduleId.getText()));
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "削除が完了しました。");
		    	// IDの初期化
		    	intTargetScheduleID = 0;
		    	intScheduleId = 0;

		    	// 画面初期化
		    	initDisplay();

				// 登録活性化
				// 更新、削除ボタン非活性化
				btnRegist.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "削除処理が失敗しました。");
		    }
	    }
	}

	// 値クリアボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
	@FXML
	private void btnClear_Click(ActionEvent event) throws IOException, SQLException {
		// 面接日程テーブルから、IDを取得
		int intScheduleId = dms.getScheduleIdData();
		lblScheduleId.setText(String.valueOf(intScheduleId));

		// 面接日程ID以外の項目を初期化
		setItemsFromRs(PropClearType.firstShow);

		// 登録活性化
		// 更新、削除ボタン非活性化
		btnRegist.setDisable(false);
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
	}

	// 画面の各項目設定処理
	// 引数
	// event
	// 戻り値
	// なし
	private void setItemsFromRs(ResultSet rs) throws SQLException {
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

	// 画面の各項目初期設定処理
	// 引数
	// event
	// 戻り値
	// なし
	private void setItemsFromRs(PropClearType propType) {

		txtSearchScheId.setText("");
		datePicker.setValue(null);;
		txtSearchHour.setText("");
		txtSearchMinute.setText("");
		txtSearchAplId.setText("");
		txtSearchAplName.setText("");
		// 値クリアボタンを押下した場合
		if(propType == PropClearType.valueClear) {
			lblScheduleId.setText("");
		}

		txtMeetingMonth.setText("");
		txtMeetingDay.setText("");
		txtStartHour.setText("");
		txtStartMinute.setText("");
		txtEndHour.setText("");
		txtEndMinute.setText("");
		txtImportance.setText("");
		txtExecPlace.setText("");
		cmbInterviewer1.getSelectionModel().select(0);
		cmbInterviewer2.getSelectionModel().select(0);
		cmbInterviewer3.getSelectionModel().select(0);
		txtApplicantName.setText("");
		txtRemarks.setText("");
	}


	// 入力値チェック処理
	// 引数
	// なし
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

		// 面接日が空欄の場合
		if(txtMeetingMonth.getText().equals("") ||
				txtMeetingDay.getText().equals("")) {

			// エラーメッセージを表示
			comFunc.showMessage("MessageBox", PropType.Error, "面接日が空欄です。");

			// 面接日にフォーカス
			txtMeetingMonth.requestFocus();

			return true;
		}

		// 開始時間が空欄の場合
		if(txtStartHour.getText().equals("") ||
				txtStartMinute.getText().equals("")) {

			// エラーメッセージを表示
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

		// 応募者名が空欄の場合
		if(txtApplicantName.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "応募者が空欄です。");

			// 応募者にフォーカス
			txtApplicantName.requestFocus();

			return true;
		}

		return false;
	}

	// 画面項目クリア処理タイプ
	public enum PropClearType {
		firstShow,					// メインメニューから遷移
		valueClear					// 値クリアボタンを押下
	}
}

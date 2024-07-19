package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Applicant;
import Model.Documents;
import Service.DataManageService;
import application.MessageBoxController.MessageResponse;
import application.MessageBoxController.PropType;
import common.CommonConst;
import common.CommonFunc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApplicantDetailController implements Initializable {

    @FXML
    private ComboBox<String> cmbSearchConducted;
    @FXML
    private ComboBox<String> cmbSearchGender;
    @FXML
    private ComboBox<String> cmbSearchResult;
    @FXML
    private ComboBox<String> cmbConducted;
    @FXML
    private ComboBox<Documents> cmbDocumentKinds;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private ComboBox<String> cmbNationality;
    @FXML
    private ComboBox<String> cmbResult;
    @FXML
    private Label lblApplicantId;
    @FXML
    private TextField txtSearchAge;
    @FXML
    private TextField txtSearchAplId;
    @FXML
    private TextField txtSearchAplName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtApplicantName;
    @FXML
    private TextArea txtRemarks;
    @FXML
    private TextField txtTelephone;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRegist;
    @FXML
    private Button btnUpdate;

	public static int intTargetApplicantId = 0;

	private static DataManageService dms;
	private static CommonFunc comFunc;


	// 書類区分リスト
	private Map<String, Documents> mapDocuments = new HashMap<String, Documents>();


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

			// 各種コンボボックス設定
			setCombobox();
			// 各値設定処理
			initDisplay();

			// 応募者一覧画面から遷移した場合
			if(intTargetApplicantId != 0) {
				// 登録ボタン非活性化
				// 更新、削除ボタン活性化
				btnRegist.setDisable(true);
				btnUpdate.setDisable(false);
				btnDelete.setDisable(false);
			} else {
				// 登録ボタン活性化
				// 更新、削除ボタン非活性化
				btnRegist.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 各種コンボボックス設定
	// 引数
	// なし
	// 戻り値
	// なし
	private void setCombobox() throws SQLException {

		// 書類テーブルから対象のデータを取得
		ResultSet rs = dms.getDocumentsData();

		// 空欄の値設定
		Documents sEmpty = new Documents(0, "");
		cmbDocumentKinds.getItems().add(sEmpty);

		// 書類区分コンボボックスの項目を設定
		while(rs.next()) {
			Documents objDocument = new Documents(rs.getInt("DOCUMENTSID"), rs.getString("DOCUMENTKINDS"));
			mapDocuments.put(rs.getString("DOCUMENTKINDS"), objDocument);
			cmbDocumentKinds.getItems().add(objDocument);
		}

		// 書類区分以外のコンボボックスの項目設定
		cmbSearchGender.getItems().add("");
		cmbSearchGender.getItems().add(CommonConst.male);
		cmbSearchGender.getItems().add(CommonConst.female);
		cmbSearchConducted.getItems().add("");
		cmbSearchConducted.getItems().add(CommonConst.conductComplete);
		cmbSearchConducted.getItems().add(CommonConst.conductNotComplete);
		cmbSearchResult.getItems().add("");
		cmbSearchResult.getItems().add(CommonConst.reqluted);
		cmbSearchResult.getItems().add(CommonConst.notAdapted);
		cmbGender.getItems().add("");
		cmbGender.getItems().add(CommonConst.male);
		cmbGender.getItems().add(CommonConst.female);
		cmbNationality.getItems().add("");
		cmbNationality.getItems().add(CommonConst.domestic);
		cmbNationality.getItems().add(CommonConst.foreign);
		cmbConducted.getItems().add("");
		cmbConducted.getItems().add(CommonConst.conductComplete);
		cmbConducted.getItems().add(CommonConst.conductNotComplete);
		cmbResult.getItems().add("");
		cmbResult.getItems().add(CommonConst.reqluted);
		cmbResult.getItems().add(CommonConst.notAdapted);

		// 初期選択状態を設定
		cmbDocumentKinds.getSelectionModel().select(0);
		cmbSearchGender.getSelectionModel().select(0);
		cmbSearchConducted.getSelectionModel().select(0);
		cmbSearchResult.getSelectionModel().select(0);
		cmbGender.getSelectionModel().select(0);
		cmbNationality.getSelectionModel().select(0);
		cmbConducted.getSelectionModel().select(0);
		cmbResult.getSelectionModel().select(0);
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {
		// メインメニューから遷移した場合
		if(intTargetApplicantId == 0) {
			// 応募者テーブルから、IDを取得
			int intApplicantId = dms.getApplicantIdData();
			lblApplicantId.setText(String.valueOf(intApplicantId));

			// 応募者ID以外の項目を初期化
			setItemsFromRs(PropClearType.firstShow);
		}
		// 応募者一覧画面から遷移した場合
		else
		{
			// 応募者テーブルのデータ取得
			ResultSet rs = dms.getApplicantData(intTargetApplicantId);

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
		ApplicantDetailListController.initListItems();

		// 入力がない場合
		if(txtSearchAplId.getText().equals("") && txtSearchAplName.getText().equals("") &&
				cmbSearchGender.getSelectionModel().getSelectedIndex() == 0 &&
				txtSearchAge.getText().equals("") && cmbSearchConducted.getSelectionModel().getSelectedIndex() == 0 &&
				cmbSearchResult.getSelectionModel().getSelectedIndex() == 0) {
					// 検索せず処理を終了する
					return;
		}

		int intSearchAplId = txtSearchAplId.getText().equals("") ? 0 : Integer.parseInt(txtSearchAplId.getText());
		int intSearchAge = txtSearchAge.getText().equals("") ? 0 : Integer.parseInt(txtSearchAge.getText());

		// 検索条件からデータを取得
		ResultSet rs = dms.getApplicantData(intSearchAplId, txtSearchAplName.getText(),
									cmbSearchGender.getSelectionModel().getSelectedItem(),
									intSearchAge, cmbSearchConducted.getSelectionModel().getSelectedItem(),
									cmbSearchResult.getSelectionModel().getSelectedItem());

		int rsCount = 0;

		while(rs.next()) {

			Applicant aItem
				= new Applicant(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"), rs.getString("GENDER"),
						rs.getInt("AGE"), rs.getString("TELEPHONE"), rs.getString("CONDUCTED"),
						rs.getString("RESULT"), rs.getString("REMARKS"));
			ApplicantDetailListController.getListItems().add(aItem);

			rsCount++;
		}

		// データ取得結果が2件以上の場合
		if(rsCount > 1) {
			// スケジュール候補リストを表示
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("ApplicantDetailList.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.showAndWait();

			// スケジュール候補リストでキャンセルを押した場合
			if(ApplicantDetailListController.selectedId == 0) {
				// 検索せず処理を終了する
				return;
			}

			// 応募者候補リストの選択値からデータ取得
			rs = dms.getApplicantData(ApplicantDetailListController.selectedId);
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

			intSearchAplId = txtSearchAplId.getText().equals("") ? 0 : Integer.parseInt(txtSearchAplId.getText());
			intSearchAge = txtSearchAge.getText().equals("") ? 0 : Integer.parseInt(txtSearchAge.getText());

			// 検索条件からデータを取得
			ResultSet rset = dms.getApplicantData(intSearchAplId, txtSearchAplName.getText(),
										cmbSearchGender.getSelectionModel().getSelectedItem(),
										intSearchAge, cmbSearchConducted.getSelectionModel().getSelectedItem(),
										cmbSearchResult.getSelectionModel().getSelectedItem());
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

			int intApplicantId = lblApplicantId.getText().equals("") ? 0 : Integer.parseInt(lblApplicantId.getText());
			int intAge = txtAge.getText().equals("") ? 0 : Integer.parseInt(txtAge.getText());

			// 入力された値で、データ登録処理を実施
			int resultCount = dms.insertScheduleAplcantDetail(
										intApplicantId,
										txtApplicantName.getText(),
										cmbGender.getSelectionModel().getSelectedItem(),
										intAge,
										cmbNationality.getSelectionModel().getSelectedItem(),
										txtTelephone.getText(),
										cmbConducted.getSelectionModel().getSelectedItem(),
										cmbResult.getSelectionModel().getSelectedItem(),
										txtRemarks.getText(),
										cmbDocumentKinds.getSelectionModel().getSelectedItem().getDocumentkinds());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "登録が完了しました。");
		    	// IDの初期化
		    	intTargetApplicantId = 0;

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

			int intApplicantId = lblApplicantId.getText().equals("") ? 0 : Integer.parseInt(lblApplicantId.getText());
			int intAge = txtAge.getText().equals("") ? 0 : Integer.parseInt(txtAge.getText());

	    	// 入力された値で、データ更新処理を実施
			int resultCount = dms.updateScheduleAplcantDetail(
										intApplicantId,
										txtApplicantName.getText(),
										cmbGender.getSelectionModel().getSelectedItem(),
										intAge,
										cmbNationality.getSelectionModel().getSelectedItem(),
										txtTelephone.getText(),
										cmbConducted.getSelectionModel().getSelectedItem(),
										cmbResult.getSelectionModel().getSelectedItem(),
										txtRemarks.getText(),
										cmbDocumentKinds.getSelectionModel().getSelectedItem().getDocumentkinds());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "更新が完了しました。");
		    	// IDの初期化
		    	intTargetApplicantId = 0;

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
	    	int resultCount = dms.deleteApplicantData(Integer.parseInt(lblApplicantId.getText()));
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "削除が完了しました。");
		    	// IDの初期化
		    	intTargetApplicantId = 0;

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
		// 応募者テーブルから、IDを取得
		int intApplicantId = dms.getApplicantIdData();
		lblApplicantId.setText(String.valueOf(intApplicantId));

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

		lblApplicantId.setText(String.valueOf(rs.getInt("APPLICANTID")));
		txtApplicantName.setText(rs.getString("APPLICANTNAME"));
		if(!rs.getString("GENDER").equals(null)) {
			cmbGender.getSelectionModel().select(rs.getString("GENDER"));
		} else {
			cmbGender.getSelectionModel().select(0);
		}
		if(!(rs.getInt("AGE") == 0)) {
			txtAge.setText(String.valueOf(rs.getInt("AGE")));
		} else {
			txtAge.setText("");
		}
		if(!rs.getString("NATIONALITY").equals(null)) {
			cmbNationality.getSelectionModel().select(rs.getString("NATIONALITY"));
		} else {
			cmbNationality.getSelectionModel().select(0);
		}
		txtTelephone.setText(rs.getString("TELEPHONE"));
		if(!rs.getString("CONDUCTED").equals(null)) {
			cmbConducted.getSelectionModel().select(rs.getString("CONDUCTED"));
		} else {
			cmbConducted.getSelectionModel().select(0);
		}
		if(!rs.getString("RESULT").equals(null)) {
			cmbResult.getSelectionModel().select(rs.getString("RESULT"));
		} else {
			cmbResult.getSelectionModel().select(0);
		}
		if(!rs.getString("DOCUMENTKINDS").equals(null)) {

			cmbDocumentKinds.getSelectionModel().select(mapDocuments.get(rs.getString("DOCUMENTKINDS")));
		} else {
			cmbDocumentKinds.getSelectionModel().select(0);
		}
		txtRemarks.setText(rs.getString("REMARKS"));
	}

	// 画面の各項目初期設定処理
	// 引数
	// event
	// 戻り値
	// なし
	private void setItemsFromRs(PropClearType propType) {

		txtSearchAplId.setText("");
		txtSearchAplName.setText("");
		cmbSearchGender.getSelectionModel().select(0);
		txtSearchAge.setText("");
		cmbSearchConducted.getSelectionModel().select(0);
		cmbSearchResult.getSelectionModel().select(0);
		// 値クリアボタンを押下した場合
		if(propType == PropClearType.valueClear) {
			lblApplicantId.setText("");
		}
		txtApplicantName.setText("");
		cmbGender.getSelectionModel().select(0);
		txtAge.setText("");
		cmbNationality.getSelectionModel().select(0);
		txtTelephone.setText("");
		cmbConducted.getSelectionModel().select(0);
		cmbResult.getSelectionModel().select(0);
		cmbDocumentKinds.getSelectionModel().select(0);
		txtRemarks.setText("");
	}


	// 入力値チェック処理
	// 引数
	// なし
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

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

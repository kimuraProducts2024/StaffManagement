package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Department;
import Model.Post;
import Model.Salary;
import Model.Staff;
import Service.DataManageService;
import application.MessageBoxController.MessageResponse;
import application.MessageBoxController.PropType;
import common.CommonConst;
import common.CommonFunc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class StaffDetailController implements Initializable {

    @FXML private AnchorPane anchorPane; @FXML private Button btnClear; @FXML private Button btnDelete;
    @FXML private Button btnRegist; @FXML private Button btnUpdate; @FXML private ComboBox<Department> cmbDepartment;
    @FXML private ComboBox<String> cmbGender; @FXML private ComboBox<String> cmbNationality; @FXML private ComboBox<Post> cmbPost;
    @FXML private ComboBox<Salary> cmbSalary; @FXML private DatePicker dateJoin; @FXML private DatePicker dateRetire;
    @FXML private Label lblStaffId; @FXML private TextField txtAccountName; @FXML private TextField txtAccountNameKana;
    @FXML private TextField txtAccountNumber; @FXML private TextArea txtAddress; @FXML private TextField txtAge;
    @FXML private TextField txtBasicPensionNumber; @FXML private TextField txtBranchCode;
    @FXML private TextField txtBranchName; @FXML private TextField txtBranchNameKana; @FXML private TextField txtDepositKind;
    @FXML private TextField txtEducation; @FXML private TextField txtEmail; @FXML private TextArea txtEmergencyAddress;
    @FXML private TextField txtEmergencyMunicip; @FXML private TextField txtEmergencyPostCode; @FXML private TextField txtEmergencyPrefectures;
    @FXML private TextField txtEmergencySequel; @FXML private TextField txtEmergencyTelephone; @FXML private TextField txtEmergencyname;
    @FXML private TextField txtFainancialInstitudeCode; @FXML private TextField txtFainancialInstitudeKana; @FXML private TextField txtFainancialInstitudeName;
    @FXML private TextField txtMunicipalities; @FXML private TextField txtMynumber; @FXML private TextField txtPostCode;
    @FXML private TextField txtPrefetcures; @FXML private TextField txtQualification1; @FXML private TextField txtQualification2;
    @FXML private TextField txtQualification3; @FXML private TextField txtQualification4; @FXML private TextField txtQualification5;
    @FXML private TextField txtQualification6; @FXML private TextField txtQualification7; @FXML private TextField txtRecipientName;
    @FXML private TextField txtRecipientNameKana; @FXML private TextArea txtRemarks; @FXML private TextField txtSocialNumber;
    @FXML private TextField txtSpouse; @FXML private TextField txtStaffName; @FXML private TextField txtSupport;
    @FXML private TextField txtTelephone; @FXML private TextField txtTitleId; @FXML private TextField txtTitleName;
    @FXML private Button btnFileChooser; @FXML private TextField txtFilePath; @FXML private ImageView StaffImage;

	public static int intSelectedStaffId = 0;
	private String strStaffImageName = "";

	private static DataManageService dms;
	private static CommonFunc comFunc;

    // 部署リスト
    private Map<Integer, Department> mapDepartment = new HashMap<Integer, Department>();

	// 役職リスト
	private Map<Integer, Post> mapPost = new HashMap<Integer, Post>();

	// 給料リスト
	private Map<Integer, Salary> mapSalary = new HashMap<Integer, Salary>();

    // 初期化処理
    // 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
    @Override
    public void initialize(URL location, ResourceBundle rosource) {

    	try {
    		// インスタンス生成処理
			dms = DataManageService.createInstance();
			comFunc = CommonFunc.createInstance();

			// 各種コンボボックス設定
			setCombobox();
			// 各値設定処理
			initDisplay();

			// 従業員一覧画面から遷移した場合
			if(intSelectedStaffId != 0) {
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

		// 部署テーブルから対象のデータを取得
		ResultSet rsDept = dms.getDepartmentData();

		// 空欄の値設定
		Department dEmpty = new Department(0, "");
		mapDepartment.put(0, dEmpty);
		cmbDepartment.getItems().add(dEmpty);

		// 部署コンボボックスの項目を設定
		while(rsDept.next()) {
			Department objDepartment = new Department(rsDept.getInt("DEPARTMENTID"), rsDept.getString("DEPARTMENTNAME"));
			mapDepartment.put(rsDept.getInt("DEPARTMENTID"), objDepartment);
			cmbDepartment.getItems().add(objDepartment);
		}

		// 手続ステータステーブルから対象のデータを取得
		ResultSet rsPost = dms.getPostData();

		// 空欄の値設定
		Post pEmpty = new Post(0, "");
		mapPost.put(0, pEmpty);
		cmbPost.getItems().add(pEmpty);

		// 手続ステータコンボボックスの項目を設定
		while(rsPost.next()) {
			Post objPost = new Post(rsPost.getInt("POSTID"), rsPost.getString("POSTNAME"));
			mapPost.put(rsPost.getInt("POSTID"), objPost);
			cmbPost.getItems().add(objPost);
		}

		// 給料種類テーブルから対象のデータを取得
		ResultSet rsSalary = dms.getSalaryData();

		// 空欄の値設定
		Salary sEmpty = new Salary(0, 0);
		mapSalary.put(0, sEmpty);
		cmbSalary.getItems().add(sEmpty);

		// 手続ステータコンボボックスの項目を設定
		while(rsSalary.next()) {
			Salary objSalary = new Salary(rsSalary.getInt("SALARYID"), rsSalary.getInt("SALARY"));
			mapSalary.put(rsSalary.getInt("SALARYID"), objSalary);
			cmbSalary.getItems().add(objSalary);
		}

		// 書類区分以外のコンボボックスの項目設定
		cmbGender.getItems().add("");
		cmbGender.getItems().add(CommonConst.male);
		cmbGender.getItems().add(CommonConst.female);
		cmbNationality.getItems().add("");
		cmbNationality.getItems().add(CommonConst.domestic);
		cmbNationality.getItems().add(CommonConst.foreign);

		// 初期選択状態を設定
		cmbDepartment.getSelectionModel().select(0);
		cmbPost.getSelectionModel().select(0);
		cmbSalary.getSelectionModel().select(0);
		cmbGender.getSelectionModel().select(0);
		cmbNationality.getSelectionModel().select(0);
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {
		// 一覧－詳細画面へボタン押下から遷移した場合
		if(intSelectedStaffId != 0) {

			// 応募者テーブルのデータ取得
			ResultSet rs = dms.getStaffData(intSelectedStaffId);

			// 取得したデータを元に、各項目の値を設定
			while(rs.next()) {
				setItemsFromRs(rs);
			}
		}
		// メインメニュー、一覧－新規登録ボタン押下から遷移した場合
		else {
			// 画面項目クリア
			clearDisplay();
		}
	}


	// 顔写真選択ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	@FXML
	void btnFileChooser_Click(ActionEvent event) {

		 //単一ファイルを選択
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle( "ファイル選択" );
		 File f1 = fileChooser.showOpenDialog( null );
		 txtFilePath.appendText(f1.getAbsolutePath());
		 StaffImage.setImage(new Image(f1.toURI().toString(), 60, 60, false, false));
		 // ファイル名（拡張子なし）の取得
		 strStaffImageName = f1.getName().substring(0, f1.getName().indexOf("."));
	}

	// 値クリアボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnClear_Click(ActionEvent event) {

    	// 画面項目クリア処理
    	clearDisplay();
    }

	// 新規登録ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnRegist_Click(ActionEvent event) throws IOException, SQLException {

		// 入力チェック
		// 入力値が不正の場合
		if(isInputErrorCheck()) {
			return;
		}

		// 確認メッセージ表示
		comFunc.showMessage("MessageBox", PropType.Confirm, "登録してよろしいですか？");

		// OKボタンをクリックした場合
		if(comFunc.getResponse() == MessageResponse.OK) {

			// 入力された値で、データ登録処理を実施
			int resultCount = dms.insertStaffData(createStaff());

		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "登録が完了しました。");
		    	// IDの初期化
		    	intSelectedStaffId = 0;
		    	strStaffImageName = "";

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
    void btnUpdate_Click(ActionEvent event) throws IOException, SQLException {

		// 入力チェック
		// 入力値が不正の場合
		if(isInputErrorCheck()) {
			return;
		}

		// 確認メッセージ表示
		comFunc.showMessage("MessageBox", PropType.Confirm, "更新してよろしいですか？");

		// OKボタンをクリックした場合
		if(comFunc.getResponse() == MessageResponse.OK) {

			// 入力された値で、データ更新処理を実施
			int resultCount = dms.updateStaffData(intSelectedStaffId, createStaff());

		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "更新が完了しました。");
		    	// IDの初期化
		    	intSelectedStaffId = 0;
		    	strStaffImageName = "";

		    	// 画面初期化
		    	initDisplay();
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
    void btnDelete_Click(ActionEvent event) throws IOException, SQLException {

		// 入力チェック
		// 入力値が不正の場合
		if(isInputErrorCheck()) {
			return;
		}

		// 確認メッセージ表示
		comFunc.showMessage("MessageBox", PropType.Confirm, "削除してよろしいですか？");

		// OKボタンをクリックした場合
		if(comFunc.getResponse() == MessageResponse.OK) {

			// 入力された値で、データ削除処理を実施
			int resultCount = dms.deleteStaffData(intSelectedStaffId);

		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "削除が完了しました。");
		    	// IDの初期化
		    	intSelectedStaffId = 0;
		    	strStaffImageName = "";

		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "削除処理が失敗しました。");
		    }
		}
    }

	// 画面項目クリア処理
	// 引数
	// event
	// 戻り値
	// なし
    private void clearDisplay() {

		// タイトルエリア
		txtTitleId.setText("");
		txtTitleName.setText("");
		StaffImage.setImage(new Image(Paths.get("bin/StaffImgs/SampleImage.png").toUri().toString(), 60, 60, false, false));

		// 基本情報タブ
		lblStaffId.setText("");
		txtStaffName.setText("");
		cmbGender.getSelectionModel().select(0);
		txtAge.setText("");
		cmbNationality.getSelectionModel().select(0);
		txtTelephone.setText("");
		txtEmail.setText("");
		txtPostCode.setText("");
		txtPrefetcures.setText("");
		txtMunicipalities.setText("");
		txtAddress.setText("");
		txtMynumber.setText("");
		cmbDepartment.getSelectionModel().select(0);
		cmbPost.getSelectionModel().select(0);
		cmbSalary.getSelectionModel().select(0);
		dateJoin.setValue(null);
		dateRetire.setValue(null);
		txtRemarks.setText("");

		// 付帯情報タブ
		txtEmergencyname.setText("");
		txtEmergencySequel.setText("");
		txtEmergencyTelephone.setText("");
		txtEmergencyPostCode.setText("");
		txtEmergencyPrefectures.setText("");
		txtEmergencyMunicip.setText("");
		txtEmergencyAddress.setText("");
		txtSpouse.setText("");
		txtSupport.setText("");
		txtEducation.setText("");
		txtQualification1.setText("");
		txtQualification2.setText("");
		txtQualification3.setText("");
		txtQualification4.setText("");
		txtQualification5.setText("");
		txtQualification6.setText("");
		txtQualification7.setText("");

		// 口座情報タブ
		txtSocialNumber.setText("");
		txtBasicPensionNumber.setText("");
		txtFainancialInstitudeCode.setText("");
		txtFainancialInstitudeName.setText("");
		txtFainancialInstitudeKana.setText("");
		txtBranchCode.setText("");
		txtBranchName.setText("");
		txtBranchNameKana.setText("");
		txtDepositKind.setText("");
		txtAccountNumber.setText("");
		txtAccountName.setText("");
		txtAccountNameKana.setText("");
		txtRecipientName.setText("");
		txtRecipientNameKana.setText("");
    }

	// 画面の各項目設定処理
	// 引数
	// event
	// 戻り値
	// なし
	private void setItemsFromRs(ResultSet rs) throws SQLException {

		// タイトルエリア
		txtTitleId.setText(String.valueOf(rs.getInt("STAFFID")));
		txtTitleName.setText(rs.getString("STAFFNAME"));
		if(!rs.getString("STAFFIMAGE").equals("")) {
			StaffImage.setImage(new Image(Paths.get("bin/StaffImgs/" +
					rs.getString("STAFFIMAGE") + ".png").toUri().toString(), 60, 60, false, false));
			strStaffImageName = rs.getString("STAFFIMAGE");
		}

		// 基本情報タブ
		lblStaffId.setText(String.valueOf(rs.getInt("STAFFID")));
		txtStaffName.setText(rs.getString("STAFFNAME"));
		if(!rs.getString("GENDER").equals("")) {
			cmbGender.getSelectionModel().select(rs.getString("GENDER"));
		}
		if(!(rs.getInt("AGE") == 0)) {
			txtAge.setText(String.valueOf(rs.getInt("AGE")));
		}
		if(!rs.getString("NATIONALITY").equals(null)) {
			cmbNationality.getSelectionModel().select(rs.getString("NATIONALITY"));
		}
		txtTelephone.setText(rs.getString("TELEPHONE"));
		txtEmail.setText(rs.getString("EMAIL"));
		if(rs.getInt("POSTCODE") != 0) {
			txtPostCode.setText(rs.getString("POSTCODE"));
		}
		txtPrefetcures.setText(rs.getString("PREFECTURES"));
		txtMunicipalities.setText(rs.getString("MUNICIPALITIES"));
		txtAddress.setText(rs.getString("ADDRESS"));
		if(rs.getLong("MYNUMBER") != 0) {
			txtMynumber.setText(String.valueOf(rs.getLong("MYNUMBER")));
		}
		if(rs.getInt("DEPARTMENTID") != 0) {
			cmbDepartment.getSelectionModel().select(mapDepartment.get(rs.getInt("DEPARTMENTID")));
		}
		if(rs.getInt("POSTID") != 0) {
			cmbPost.getSelectionModel().select(mapPost.get(rs.getInt("POSTID")));
		}
		if(rs.getInt("SALARYID") != 0) {
			cmbSalary.getSelectionModel().select(mapSalary.get(rs.getInt("SALARYID")));
		}
		if(rs.getDate("JOINDATE") != null) {
			Date joinDt = rs.getTimestamp("JOINDATE");
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate joinDate = joinDt.toInstant().atZone(zoneId).toLocalDate();
			dateJoin.setValue(joinDate);
		}
		if(rs.getDate("RETIREDATE") != null) {
			Date retireDt = rs.getTimestamp("RETIREDATE");
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate retiareDate = retireDt.toInstant().atZone(zoneId).toLocalDate();
			dateRetire.setValue(retiareDate);
		}
		txtRemarks.setText(rs.getString("REMARKS"));

		// 付帯情報タブ
		txtEmergencyname.setText(rs.getString("EMERGENCYNAME"));
		txtEmergencySequel.setText(rs.getString("EMERGENCYSEQUEL"));
		txtEmergencyTelephone.setText(rs.getString("EMERGENCYTELEPHONE"));
		if(rs.getInt("EMERGENCYPOSTCODE") != 0) {
			txtEmergencyPostCode.setText(String.valueOf(rs.getInt("EMERGENCYPOSTCODE")));
		}
		txtEmergencyPrefectures.setText(rs.getString("EMERGENCYPREFECTURES"));
		txtEmergencyMunicip.setText(rs.getString("EMERGENCYMUNICIPALITIES"));
		txtEmergencyAddress.setText(rs.getString("EMERGENCYADDRESS"));
		txtSpouse.setText(rs.getString("SPOUSE"));
		txtSupport.setText(rs.getString("SUPPORT"));
		txtEducation.setText(rs.getString("EDUCATION"));
		txtQualification1.setText(rs.getString("QUALIFICATION1"));
		txtQualification2.setText(rs.getString("QUALIFICATION2"));
		txtQualification3.setText(rs.getString("QUALIFICATION3"));
		txtQualification4.setText(rs.getString("QUALIFICATION4"));
		txtQualification5.setText(rs.getString("QUALIFICATION5"));
		txtQualification6.setText(rs.getString("QUALIFICATION6"));
		txtQualification7.setText(rs.getString("QUALIFICATION7"));

		// 口座情報タブ
		if(rs.getInt("SOCIALINSURANCENUMBER") != 0) {
			txtSocialNumber.setText(String.valueOf(rs.getInt("SOCIALINSURANCENUMBER")));
		}
		if(rs.getLong("BASICPENSIONNUMBER") != 0) {
			txtBasicPensionNumber.setText(String.valueOf(rs.getLong("BASICPENSIONNUMBER")));
		}
		if(rs.getInt("FAINANCIALINSTITUDECODE") != 0) {
			txtFainancialInstitudeCode.setText(String.valueOf(rs.getInt("FAINANCIALINSTITUDECODE")));
		}
		txtFainancialInstitudeName.setText(rs.getString("FAINANCIALINSTITUDE"));
		txtFainancialInstitudeKana.setText(rs.getString("FAINANCIALINSTITUDEKANA"));
		if(rs.getInt("BRANCHCODE") != 0) {
			txtBranchCode.setText(String.valueOf(rs.getInt("BRANCHCODE")));
		}
		txtBranchName.setText(rs.getString("BRANCHNAME"));
		txtBranchNameKana.setText(rs.getString("BRANCHNAMEKANA"));
		txtDepositKind.setText(rs.getString("DEPOSITKIND"));
		if(rs.getInt("ACCOUNTNUMBER") != 0) {
			txtAccountNumber.setText(String.valueOf(rs.getInt("ACCOUNTNUMBER")));
		}
		txtAccountName.setText(rs.getString("ACCOUNTNAME"));
		txtAccountNameKana.setText(rs.getString("ACCOUNTNAMEKANA"));
		txtRecipientName.setText(rs.getString("RECIPIENTNAME"));
		txtRecipientNameKana.setText(rs.getString("RECIPIENTNAMEKANA"));
	}

	// 入力値チェック処理
	// 引数
	// なし
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

		// 従業員名が空欄の場合
		if(txtStaffName.getText().equals("")) {

			// エラーメッセージを表示
			comFunc.showMessage("MessageBox", PropType.Error, "従業員名が空欄です。");

			// 従業員名にフォーカス
			txtStaffName.requestFocus();

			return true;
		}

		return false;
	}

	// Staffインスタンス生成処理
	// 引数
	// event
	// 戻り値
	// なし
    private Staff createStaff() {

    	int intAge = txtAge.getText().equals("") ? 0 : Integer.parseInt(txtAge.getText());
    	int intPostCode = txtPostCode.getText().equals("") ? 0 : Integer.parseInt(txtPostCode.getText());
    	long longMynumber = txtMynumber.getText().equals("") ? 0 : Long.parseLong(txtMynumber.getText());
    	Date joinDate = dateJoin.getValue() == null ? null :
    							Date.from(dateJoin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Date retireDate = dateRetire.getValue() == null ? null :
    							Date.from(dateRetire.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Long longEmergencyPostCode = txtEmergencyPostCode.getText().equals("") ? 0 : Long.parseLong(txtEmergencyPostCode.getText());
    	Long longSocialnumber = txtSocialNumber.getText().equals("") ? 0 : Long.parseLong(txtSocialNumber.getText());
    	Long longBasicNumber = txtBasicPensionNumber.getText().equals("") ? 0 : Long.parseLong(txtBasicPensionNumber.getText());
    	int intFinancialCode = txtFainancialInstitudeCode.getText().equals("") ? 0 : Integer.parseInt(txtFainancialInstitudeCode.getText());
    	int intBranchCode = txtBranchCode.getText().equals("") ? 0 : Integer.parseInt(txtBranchCode.getText());
    	int intAccountNumber = txtAccountNumber.getText().equals("") ? 0 : Integer.parseInt(txtAccountNumber.getText());

    	Staff staff = new Staff(
    			// 基本情報タブ
    			txtStaffName.getText(),
    			cmbGender.getSelectionModel().getSelectedItem(),
    			intAge,
    			cmbNationality.getSelectionModel().getSelectedItem(),
    			txtTelephone.getText(),
    			txtEmail.getText(),
    			intPostCode,
    			txtPrefetcures.getText(),
    			txtMunicipalities.getText(),
    			txtAddress.getText(),
    			longMynumber,
    			cmbDepartment.getSelectionModel().getSelectedItem().getDepartmentid(),
    			cmbPost.getSelectionModel().getSelectedItem().getPostit(),
    			cmbSalary.getSelectionModel().getSelectedItem().getSalaryid(),
    			joinDate,
    			retireDate,
    			txtRemarks.getText(),
    			// 付帯情報タブ
    			txtEmergencyname.getText(),
    			txtEmergencySequel.getText(),
    			longEmergencyPostCode,
    			txtEmergencyTelephone.getText(),
    			txtEmergencyPrefectures.getText(),
    			txtEmergencyMunicip.getText(),
    			txtEmergencyAddress.getText(),
    			txtSpouse.getText(),
    			txtSupport.getText(),
    			txtEducation.getText(),
    			txtQualification1.getText(),
    			txtQualification2.getText(),
    			txtQualification3.getText(),
    			txtQualification4.getText(),
    			txtQualification5.getText(),
    			txtQualification6.getText(),
    			txtQualification7.getText(),
    			// 口座情報タブ
    			longSocialnumber,
    			longBasicNumber,
    			txtFainancialInstitudeName.getText(),
    			txtFainancialInstitudeKana.getText(),
    			intFinancialCode,
    			txtBranchName.getText(),
    			txtBranchNameKana.getText(),
    			intBranchCode,
    			txtDepositKind.getText(),
    			intAccountNumber,
    			txtAccountName.getText(),
    			txtAccountNameKana.getText(),
    			txtRecipientName.getText(),
    			txtRecipientNameKana.getText(),
    			strStaffImageName
    			);

		return staff;
    }
}

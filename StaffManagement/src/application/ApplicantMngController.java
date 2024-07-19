package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Applicant;
import Service.DataManageService;
import common.CommonConst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ApplicantMngController implements Initializable {

	@FXML
	private AnchorPane anchorPane;
    @FXML
    private TextField txtSearchAplId;
    @FXML
    private TextField txtSearchAplName;
    @FXML
    private TableView<Applicant> table;
    @FXML
    private TableColumn<Applicant, Integer> colApplicantId;
    @FXML
    private TableColumn<Applicant, String> colApplicantName;
    @FXML
    private TableColumn<Applicant, String> colGender;
    @FXML
    private TableColumn<Applicant, Integer> colAge;
    @FXML
    private TableColumn<Applicant, String> colTelephone;
    @FXML
    private TableColumn<Applicant, String> colConducted;
    @FXML
    private TableColumn<Applicant, String> colResult;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnDetail;
    @FXML
    private ComboBox<String> cmbConducted;
    @FXML
    private ComboBox<String> cmbResult;

    private DataManageService dms;

	// 初期化処理
	// 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
	@Override
	public void initialize(URL url, ResourceBundle bundle) {

		try {
			// 各変数の初期化
			ApplicantDetailController.intTargetApplicantId = 0;
			dms = DataManageService.createInstance();

			// 詳細画面へボタン非活性化
			btnDetail.setDisable(true);

			// 画面初期化処理
			initDisplay();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {

		// 面接実施、合否コンボボックス設定
		setIntevierwerCombo();

		// 応募者一覧テーブル設定
		setApplicantTable();
	}

	// 面接実施、合否コンボボックス設定
	// 引数
	// なし
	// 戻り値
	// なし
	private void setIntevierwerCombo() {

		// 空欄の値設定
		cmbConducted.getItems().add("");
		cmbResult.getItems().add("");

		// 各コンボボックスの項目を設定
		cmbConducted.getItems().add(CommonConst.conductComplete);
		cmbConducted.getItems().add(CommonConst.conductNotComplete);
		cmbResult.getItems().add(CommonConst.reqluted);
		cmbResult.getItems().add(CommonConst.notAdapted);

		// 初期選択状態を設定
		cmbConducted.getSelectionModel().select(0);
		cmbResult.getSelectionModel().select(0);
	}

	// 応募者一覧テーブル設定
	// 引数
	// なし
	// 戻り値
	// なし
	void setApplicantTable() throws SQLException {

		List<Applicant> applicantList = new ArrayList<Applicant>();

		// テーブル初期設定
		colApplicantId.setCellValueFactory(p -> p.getValue().getApplicantid().asObject());
		colApplicantName.setCellValueFactory(p -> p.getValue().getApplicantname());
		colGender.setCellValueFactory(p -> p.getValue().getGender());
		colAge.setCellValueFactory(p -> p.getValue().getAge().asObject());
		colTelephone.setCellValueFactory(p -> p.getValue().getTelephone());
		colConducted.setCellValueFactory(p -> p.getValue().getConducted());
		colResult.setCellValueFactory(p -> p.getValue().getResult());

		// 応募者テーブルの全件データ取得
		ResultSet rs = dms.getApplicantData();

		while(rs.next()) {
			Applicant aplct = new Applicant(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"), rs.getString("GENDER"),
									rs.getInt("AGE"), rs.getString("TELEPHONE"), rs.getString("CONDUCTED"),
									rs.getString("RESULT"), rs.getString("REMARKS"));

			applicantList.add(aplct);
		}

		// 応募者テーブルにデータ設定
		ObservableList<Applicant> addList = FXCollections.observableArrayList(applicantList);
		table.getItems().addAll(addList);
	}

	// テーブル選択時処理
	// 引数
	// event：MouseEvent
	// 戻り値
	// なし
	@FXML
	void table_Clicked(MouseEvent event) {

		// 選択行の位置取得
		int intSelectedIndex = table.getSelectionModel().getSelectedIndex();

		// 行未選択の場合
		if(intSelectedIndex == -1) {
			// 詳細画面へボタン非活性化
			btnDetail.setDisable(true);

			return;
		}

		// 詳細画面へボタン活性化
		btnDetail.setDisable(false);
	}

	// 値クリアボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnClear_Click(ActionEvent event) {

    	txtSearchAplId.setText("");
    	txtSearchAplName.setText("");
    	cmbConducted.getSelectionModel().select(0);
    	cmbResult.getSelectionModel().select(0);
    }

	// 検索ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnSearch_Click(ActionEvent event) throws NumberFormatException, SQLException {

    	// 詳細画面へボタン非活性化
		btnDetail.setDisable(true);

    	// 応募者一覧をクリアする
    	table.getItems().clear();

		List<Applicant> applicantList = new ArrayList<Applicant>();

    	// 入力がない場合
    	if(txtSearchAplId.getText().equals("") && txtSearchAplName.getText().equals("") &&
    			cmbConducted.getSelectionModel().getSelectedIndex() == 0 &&
    			cmbResult.getSelectionModel().getSelectedIndex() == 0) {

    		// 応募者テーブルの全件データ取得
    		ResultSet rs = dms.getApplicantData();

    		while(rs.next()) {
    			Applicant aplct = new Applicant(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"), rs.getString("GENDER"),
    									rs.getInt("AGE"), rs.getString("TELEPHONE"), rs.getString("CONDUCTED"),
    									rs.getString("RESULT"), rs.getString("REMARKS"));

    			applicantList.add(aplct);
    		}

    		// 応募者テーブルにデータ設定
    		ObservableList<Applicant> addList = FXCollections.observableArrayList(applicantList);
    		table.getItems().addAll(addList);

			return;
    	}

    	int searchAplId = Integer.parseInt(txtSearchAplId.getText().equals("") ? "0": txtSearchAplId.getText());

		// 検索条件からデータを取得
    	ResultSet rs = dms.getApplicantData(searchAplId,
    						txtSearchAplName.getText(),
    						cmbConducted.getSelectionModel().getSelectedItem(),
    						cmbResult.getSelectionModel().getSelectedItem());

		while(rs.next()) {
			Applicant aplct = new Applicant(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"), rs.getString("GENDER"),
									rs.getInt("AGE"), rs.getString("TELEPHONE"), rs.getString("CONDUCTED"),
									rs.getString("RESULT"), rs.getString("REMARKS"));

			applicantList.add(aplct);
		}

		// 応募者テーブルにデータ設定
		ObservableList<Applicant> addList = FXCollections.observableArrayList(applicantList);
		table.getItems().addAll(addList);
    }

	// 詳細画面へボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnDetail_Click(ActionEvent event) throws IOException {

		// 選択行の位置取得
		int intSelectedIndex = table.getSelectionModel().getSelectedIndex();

		// 行未選択の場合
		if(intSelectedIndex == -1) {
			return;
		}

		// 選択行の取得
		Applicant selectedRow = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		// 選択行IDを取得
		ApplicantDetailController.intTargetApplicantId = selectedRow.getApplicantid().intValue();
		BorderPane bp = (BorderPane)anchorPane.parentProperty().get();
		Parent root = FXMLLoader.load(getClass().getResource("ApplicantDetail.fxml"));
		bp.setCenter(root);
    }
}

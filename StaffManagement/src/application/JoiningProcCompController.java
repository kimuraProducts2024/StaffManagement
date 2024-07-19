package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Model.Documents;
import Model.JoinStatus;
import Model.JoiningProcessItem;
import Service.DataManageService;
import application.MessageBoxController.MessageResponse;
import application.MessageBoxController.PropType;
import common.CommonFunc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class JoiningProcCompController implements Initializable {

    @FXML
    private Button btnClear;
    @FXML
    private Button btnComplete;
    @FXML
    private Button btnUpdate;
    @FXML
    private ComboBox<JoinStatus> cmbJoinStatus;
    @FXML
    private ComboBox<Documents> cmbSearchDocKbn;
    @FXML
    private ComboBox<JoinStatus> cmbSearchStatus;
    @FXML
    private TableColumn<JoiningProcessItem, Integer> colApplicantId;
    @FXML
    private TableColumn<JoiningProcessItem, String> colApplicantName;
    @FXML
    private TableColumn<JoiningProcessItem, String> colDocumentKinds;
    @FXML
    private TableColumn<JoiningProcessItem, String> colJoinStatus;
    @FXML
    private TableColumn<JoiningProcessItem, String> colRemarks;
    @FXML
    private TableColumn<JoiningProcessItem, String> colShortageDoc;
    @FXML
    private TableColumn<JoiningProcessItem, String> colTelephone;
    @FXML
    private TableView<JoiningProcessItem> table;
    @FXML
    private TextField txtApplicantId;
    @FXML
    private TextField txtApplicantName;
    @FXML
    private TextField txtDocumentKinds;
    @FXML
    private TextArea txtRemarks;
    @FXML
    private TextField txtSearchAplId;
    @FXML
    private TextField txtSearchAplName;
    @FXML
    private TextField txtShortageDoc;

	private static DataManageService dms;
	private static CommonFunc comFunc;

	private static List<JoiningProcessItem> listItems;
	private ObservableList<JoiningProcessItem> joinProcListItems;

    public static int selectedId = 0;

    // 書類区分リスト
    private Map<String, Documents> mapDocuments = new HashMap<String, Documents>();

	// 手続きステータスリスト
	private Map<String, JoinStatus> mapJoinStatus = new HashMap<String, JoinStatus>();

    // 初期化処理
    // 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
    @Override
    public void initialize(URL location, ResourceBundle resource) {

		try {

			// インスタンス生成処理
			dms = DataManageService.createInstance();
			comFunc = new CommonFunc();

			// 選択行の初期化
			selectedId = 0;

			// 各列の設定
			colApplicantId.setCellValueFactory(p -> p.getValue().getApplicantid().asObject());
			colApplicantName.setCellValueFactory(p -> p.getValue().getApplicantname());
			colDocumentKinds.setCellValueFactory(p -> p.getValue().getDocumentkinds());
			colJoinStatus.setCellValueFactory(p -> p.getValue().getJoinstatus());
			colTelephone.setCellValueFactory(p -> p.getValue().getTelephone());
			colShortageDoc.setCellValueFactory(p -> p.getValue().getshortagedoc());
			colRemarks.setCellValueFactory(p -> p.getValue().getRemarks());

			// 各種コンボボックス設定
			setCombobox();
			// 各値設定処理
			initDisplay();
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
		ResultSet rsDoc = dms.getDocumentsPartData();

		// 空欄の値設定
		Documents dEmpty = new Documents(0, "");
		mapDocuments.put("", dEmpty);
		cmbSearchDocKbn.getItems().add(dEmpty);

		// 書類区分コンボボックスの項目を設定
		while(rsDoc.next()) {
			Documents objDocument = new Documents(rsDoc.getInt("DOCUMENTSID"), rsDoc.getString("DOCUMENTKINDS"));
			mapDocuments.put(rsDoc.getString("DOCUMENTKINDS"), objDocument);
			cmbSearchDocKbn.getItems().add(objDocument);
		}

		// 手続ステータステーブルから対象のデータを取得
		ResultSet rsJoin = dms.getJoinStatusData();

		// 空欄の値設定
		JoinStatus sEmpty = new JoinStatus(0, "");
		cmbSearchStatus.getItems().add(sEmpty);
		mapJoinStatus.put("", sEmpty);
		cmbJoinStatus.getItems().add(sEmpty);

		// 手続ステータコンボボックスの項目を設定
		while(rsJoin.next()) {
			JoinStatus objJoinStatus = new JoinStatus(rsJoin.getInt("JOINSTATUSID"), rsJoin.getString("JOINSTATUSNAME"));
			mapJoinStatus.put(rsJoin.getString("JOINSTATUSNAME"), objJoinStatus);
			cmbSearchStatus.getItems().add(objJoinStatus);
			cmbJoinStatus.getItems().add(objJoinStatus);
		}

		// 初期選択状態を設定
		cmbJoinStatus.getSelectionModel().select(0);
		cmbSearchDocKbn.getSelectionModel().select(0);
		cmbSearchStatus.getSelectionModel().select(0);
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {

		listItems = new ArrayList<JoiningProcessItem>();

		// 各項目を初期化
		txtSearchAplId.setText("");
		txtSearchAplName.setText("");
		cmbSearchDocKbn.getSelectionModel().select(0);
		cmbSearchStatus.getSelectionModel().select(0);
		table.getSelectionModel().clearSelection();
		txtApplicantId.setText("");
		txtApplicantName.setText("");
		txtDocumentKinds.setText("");
		cmbJoinStatus.getSelectionModel().select(0);
		txtShortageDoc.setText("");
		txtRemarks.setText("");

		// テーブル設定用データ取得
		ResultSet rs = dms.getJoiningProcessData();
		while(rs.next()) {
			JoiningProcessItem docObj = new JoiningProcessItem(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"),
											rs.getString("DOCUMENTKINDS"), rs.getString("JOINSTATUSNAME"), rs.getString("TELEPHONE"),
											rs.getString("SHORTAGEDOC"), rs.getString("REMARKS"));
			listItems.add(docObj);
		}

		// テーブルに値設定
		joinProcListItems = FXCollections.observableArrayList(listItems);
		table.setItems(joinProcListItems);

		// 更新、手続き完了ボタン非活性化
		btnUpdate.setDisable(true);
		btnComplete.setDisable(true);
	}

	// 書類テーブルクリック
	// 引数
	// MouseEvent： event
	// 戻り値
	// なし
    @FXML
    void table_Clicked(MouseEvent event) throws SQLException {

    	// 選択行の位置取得
    	int intSelectedIndex = table.getSelectionModel().getSelectedIndex();

    	// 行未選択の場合
    	if(intSelectedIndex == -1) {
    		return;
    	}

    	// 選択行の取得
    	JoiningProcessItem jPiObj = table.getItems().get(intSelectedIndex);
    	// 選択行のIDを取得
    	selectedId = jPiObj.getApplicantid().intValue();
    	// 手続データ取得
    	ResultSet rs = dms.getJoiningProcessData(selectedId);
    	// 選択した行のデータを各項目に設定
    	while(rs.next()) {
    		txtApplicantId.setText(String.valueOf(rs.getInt("APPLICANTID")));
    		txtApplicantName.setText(rs.getString("APPLICANTNAME"));
    		txtDocumentKinds.setText(rs.getString("DOCUMENTKINDS"));
    		cmbJoinStatus.getSelectionModel().select(mapJoinStatus.get(rs.getString("JOINSTATUSNAME")));
    		txtShortageDoc.setText(rs.getString("SHORTAGEDOC"));
    		txtRemarks.setText(rs.getString("REMARKS"));
    	}

    	// 更新、手続き完了ボタン活性化
		btnUpdate.setDisable(false);
		btnComplete.setDisable(false);
    }

	// 値クリアボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnSearchClear_Click(ActionEvent event) {

    	selectedId = 0;

		txtSearchAplId.setText("");
		txtSearchAplName.setText("");
		cmbSearchDocKbn.getSelectionModel().select(0);
		cmbSearchStatus.getSelectionModel().select(0);
    }

	// 検索ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnSearch_Click(ActionEvent event) throws SQLException {

		listItems = new ArrayList<JoiningProcessItem>();

		// 入力がない場合
		if(txtSearchAplId.getText().equals("") && txtSearchAplName.getText().equals("") &&
				cmbSearchDocKbn.getSelectionModel().getSelectedIndex() == 0 &&
				cmbSearchStatus.getSelectionModel().getSelectedIndex() == 0) {
					// テーブル設定用データ取得
					ResultSet rs = dms.getJoiningProcessData();
					while(rs.next()) {
						JoiningProcessItem docObj = new JoiningProcessItem(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"),
														rs.getString("DOCUMENTKINDS"), rs.getString("JOINSTATUSNAME"), rs.getString("TELEPHONE"),
														rs.getString("SHORTAGEDOC"), rs.getString("REMARKS"));
						listItems.add(docObj);
					}

					// テーブルに値設定
					joinProcListItems = FXCollections.observableArrayList(listItems);
					table.setItems(joinProcListItems);

					return;
		}

		int intSearchAplId = txtSearchAplId.getText().equals("") ? 0 : Integer.parseInt(txtSearchAplId.getText());

		// 検索条件からデータを取得
		ResultSet rs = dms.getJoiningProcessData(intSearchAplId, txtSearchAplName.getText(),
													cmbSearchDocKbn.getSelectionModel().getSelectedItem().getDocumentkinds(),
													cmbSearchStatus.getSelectionModel().getSelectedItem().getJoinstatusid());

		while(rs.next()) {
			JoiningProcessItem docObj = new JoiningProcessItem(rs.getInt("APPLICANTID"), rs.getString("APPLICANTNAME"),
											rs.getString("DOCUMENTKINDS"), rs.getString("JOINSTATUSNAME"), rs.getString("TELEPHONE"),
											rs.getString("SHORTAGEDOC"), rs.getString("REMARKS"));
			listItems.add(docObj);
		}

		// テーブルに値設定
		joinProcListItems = FXCollections.observableArrayList(listItems);
		table.setItems(joinProcListItems);

		// 更新、手続き完了ボタン非活性化
		btnUpdate.setDisable(true);
		btnComplete.setDisable(true);
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

			int intApplicantId = txtApplicantId.getText().equals("") ? 0 : Integer.parseInt(txtApplicantId.getText());

	    	// 入力された値で、データ更新処理を実施
			int resultCount = dms.updateJoiningProcess(
										intApplicantId,
										cmbJoinStatus.getSelectionModel().getSelectedItem().getJoinstatusid(),
										txtShortageDoc.getText(),
										txtRemarks.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "更新が完了しました。");
		    	// IDの初期化
		    	selectedId = 0;

		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "更新処理が失敗しました。");
		    }
	    }
	}

	// 手続き完了ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnComplete_Click(ActionEvent event) throws IOException, SQLException {

    	// 完了対象者一覧が未選択の場合
    	if(selectedId == 0) {
    		return;
    	}

		// 確認メッセージ表示
		comFunc.showMessage("MessageBox", PropType.Confirm, "手続き完了してよろしいですか？");

		// OKボタンをクリックした場合
	    if (comFunc.getResponse() == MessageResponse.OK) {

	    	// 入力された値で、データ更新処理を実施
			boolean boolSucsess = dms.completeJoiningProcess(selectedId);
		    if(boolSucsess) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "手続きが完了しました。");
		    	// IDの初期化
		    	selectedId = 0;

		    	// 画面初期化
		    	initDisplay();
		    } else {
		    	comFunc.showMessage("MessageBox", PropType.Error, "手続き完了処理が失敗しました。");
		    }
	    }

    }

	// 値クリアボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
	@FXML
    void btnClear_Click(ActionEvent event) {

		// 各項目を初期化
		table.getSelectionModel().clearSelection();
		txtApplicantId.setText("");
		txtApplicantName.setText("");
		txtDocumentKinds.setText("");
		cmbJoinStatus.getSelectionModel().select(0);
		txtShortageDoc.setText("");
		txtRemarks.setText("");

		// 更新、手続き完了ボタン非活性化
		btnUpdate.setDisable(true);
		btnComplete.setDisable(true);
    }

	// 入力値チェック処理
	// 引数
	// なし
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

		// 手続きステータスが未選択の場合
		if(cmbJoinStatus.getSelectionModel().getSelectedIndex() == 0) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "手続きステータスが空欄です。");

			// 手続きステータスにフォーカス
			cmbJoinStatus.requestFocus();

			return true;
		}

		return false;
	}
}

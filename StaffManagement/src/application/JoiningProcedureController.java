package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.DocumentsTable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class JoiningProcedureController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<DocumentsTable, Integer> colDocumentsId;
    @FXML
    private TableColumn<DocumentsTable, String> colDocumentsKind;
    @FXML
    private TableColumn<DocumentsTable, String> colDocument1;
    @FXML
    private TableColumn<DocumentsTable, String> colDocument2;
    @FXML
    private TableColumn<DocumentsTable, String> colDocument3;
    @FXML
    private TableView<DocumentsTable> table;
    @FXML
    private TextField txtDocument1;
    @FXML
    private TextField txtDocument2;
    @FXML
    private TextField txtDocument3;
    @FXML
    private TextField txtDocumentKinds;
    @FXML
    private TextField txtDocumentsId;
    @FXML
    private TextField txtSearchDoc1;
    @FXML
    private TextField txtSearchDoc2;
    @FXML
    private TextField txtSearchDoc3;
    @FXML
    private TextField txtSearchDocId;
    @FXML
    private TextField txtSearchDocKinds;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRegist;
    @FXML
    private Button btnUpdate;

	private static DataManageService dms;
	private static CommonFunc comFunc;

	private static List<DocumentsTable> listItems;
	private ObservableList<DocumentsTable> DocumentsListItems;

    public static int selectedId = 0;

    // 初期化処理
    // 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
    @Override
    public void initialize(URL location, ResourceBundle resoruce) {

		try {

			// インスタンス生成処理
			dms = DataManageService.createInstance();
			comFunc = new CommonFunc();

			// 選択行の初期化
			selectedId = 0;

			// 各列の設定
			colDocumentsId.setCellValueFactory(p -> p.getValue().getDocumentsid().asObject());
			colDocumentsKind.setCellValueFactory(p -> p.getValue().getDocumentkinds());
			colDocument1.setCellValueFactory(p -> p.getValue().getDocument1());
			colDocument2.setCellValueFactory(p -> p.getValue().getDocument2());
			colDocument3.setCellValueFactory(p -> p.getValue().getDocument3());

			// 各値設定処理
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

		listItems = new ArrayList<DocumentsTable>();

		// 各項目を初期化
    	txtSearchDocId.setText("");
    	txtSearchDocKinds.setText("");
    	txtSearchDoc1.setText("");
    	txtSearchDoc2.setText("");
    	txtSearchDoc3.setText("");
		table.getSelectionModel().clearSelection();
		txtDocumentsId.setText("");
		txtDocumentKinds.setText("");
		txtDocument1.setText("");
		txtDocument2.setText("");
		txtDocument3.setText("");

		// テーブル設定用データ取得
		ResultSet rs = dms.getDocumentsData();
		while(rs.next()) {
			DocumentsTable docObj = new DocumentsTable(rs.getInt("DOCUMENTSID"), rs.getString("DOCUMENTKINDS"),
											rs.getString("DOCUMENT1"), rs.getString("DOCUMENT2"), rs.getString("DOCUMENT3"));
			listItems.add(docObj);
		}

		// テーブルに値設定
		DocumentsListItems = FXCollections.observableArrayList(listItems);
		table.setItems(DocumentsListItems);

		// 登録ボタン活性化
		// 更新、削除ボタン非活性化
		btnRegist.setDisable(false);
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
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
    	DocumentsTable docObj = table.getItems().get(intSelectedIndex);
    	// 選択行のIDを取得
    	selectedId = docObj.getDocumentsid().intValue();
    	// 書類データ取得
    	ResultSet rs = dms.getDocumentsData(selectedId);
    	// 選択した行のデータを各項目に設定
    	while(rs.next()) {
    		txtDocumentsId.setText(String.valueOf(rs.getInt("DOCUMENTSID")));
    		txtDocumentKinds.setText(rs.getString("DOCUMENTKINDS"));
    		txtDocument1.setText(rs.getString("DOCUMENT1"));
    		txtDocument2.setText(rs.getString("DOCUMENT2"));
    		txtDocument3.setText(rs.getString("DOCUMENT3"));
    	}

		// 登録ボタン非活性化
		// 更新、削除ボタン活性化
		btnRegist.setDisable(true);
		btnUpdate.setDisable(false);
		btnDelete.setDisable(false);
    }

	// 値クリアボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnSearchClear_Click(ActionEvent event) {

    	selectedId = 0;

    	txtSearchDocId.setText("");
    	txtSearchDocKinds.setText("");
    	txtSearchDoc1.setText("");
    	txtSearchDoc2.setText("");
    	txtSearchDoc3.setText("");
    	table.getSelectionModel().clearSelection();
    }

	// 検索ボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnSearch_Click(ActionEvent event) throws SQLException {

    	selectedId = 0;

    	// listItemsを初期化
    	listItems = new ArrayList<DocumentsTable>();

    	// 入力がない場合
    	if(txtSearchDocId.getText().equals("") && txtSearchDocKinds.getText().equals("") &&
    			txtSearchDoc1.getText().equals("") && txtSearchDoc2.getText().equals("") && txtSearchDoc3.getText().equals("")) {
    		// テーブル設定用データ取得
    		ResultSet rs = dms.getDocumentsData();
    		while(rs.next()) {
    			DocumentsTable docObj = new DocumentsTable(rs.getInt("DOCUMENTSID"), rs.getString("DOCUMENTKINDS"),
    											rs.getString("DOCUMENT1"), rs.getString("DOCUMENT2"), rs.getString("DOCUMENT3"));
    			listItems.add(docObj);
    		}

    		// テーブルに値設定
    		DocumentsListItems = FXCollections.observableArrayList(listItems);
    		table.setItems(DocumentsListItems);
			return;
    	}

    	int intSearchDocId = txtSearchDocId.getText().equals("") ? 0 : Integer.parseInt(txtSearchDocId.getText());

    	// 検索条件からデータを取得
    	ResultSet rs = dms.getDocumentsData(intSearchDocId, txtSearchDocKinds.getText(), txtSearchDoc1.getText(),
    									txtSearchDoc2.getText(), txtSearchDoc3.getText());
		while(rs.next()) {
			DocumentsTable docObj = new DocumentsTable(rs.getInt("DOCUMENTSID"), rs.getString("DOCUMENTKINDS"),
											rs.getString("DOCUMENT1"), rs.getString("DOCUMENT2"), rs.getString("DOCUMENT3"));
			listItems.add(docObj);
		}

		// テーブルに値設定
		DocumentsListItems = FXCollections.observableArrayList(listItems);
		table.setItems(DocumentsListItems);
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

			int intDocumentsId = txtDocumentsId.getText().equals("") ? 0 : Integer.parseInt(txtDocumentsId.getText());

			// 入力された値で、データ登録処理を実施
			int resultCount = dms.insertDocumentsData(
										intDocumentsId,
										txtDocumentKinds.getText(),
										txtDocument1.getText(),
										txtDocument2.getText(),
										txtDocument3.getText());
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "登録が完了しました。");
		    	// IDの初期化
		    	selectedId = 0;

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

			int intDocumentsId = txtDocumentsId.getText().equals("") ? 0 : Integer.parseInt(txtDocumentsId.getText());

	    	// 入力された値で、データ更新処理を実施
			int resultCount = dms.updateDocumentsData(
										intDocumentsId,
										txtDocumentKinds.getText(),
										txtDocument1.getText(),
										txtDocument2.getText(),
										txtDocument3.getText());
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

			int intDocumentsId = txtDocumentsId.getText().equals("") ? 0 : Integer.parseInt(txtDocumentsId.getText());

	    	// 入力された値で、データ削除処理を実施
	    	int resultCount = dms.deleteDocumentsData(intDocumentsId);
		    if(resultCount > 0) {
		    	comFunc.showMessage("MessageBox", PropType.Info, "削除が完了しました。");
		    	// IDの初期化
		    	selectedId = 0;

		    	// 画面初期化
		    	initDisplay();
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

		// IDの初期化
    	selectedId = 0;

		// 各項目を初期化
    	table.getSelectionModel().clearSelection();
		txtDocumentsId.setText("");
		txtDocumentKinds.setText("");
		txtDocument1.setText("");
		txtDocument2.setText("");
		txtDocument3.setText("");

		// 登録活性化
		// 更新、削除ボタン非活性化
		btnRegist.setDisable(false);
		btnUpdate.setDisable(true);
		btnDelete.setDisable(true);
	}

	// 入力値チェック処理
	// 引数
	// なし
	// 戻り値
	// true：入力値にエラー箇所あり
	// false：入力値にエラー箇所なし
	private boolean isInputErrorCheck() throws IOException {

		// 書類IDが空欄の場合
		if(txtDocumentsId.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "書類IDが空欄です。");

			// 書類IDにフォーカス
			txtDocumentsId.requestFocus();

			return true;
		}

		// 書類区分が空欄の場合
		if(txtDocumentKinds.getText().equals("")) {

			// エラーメッセージを表示する
			comFunc.showMessage("MessageBox", PropType.Error, "書類区分が空欄です。");

			// 書類区分にフォーカス
			txtDocumentKinds.requestFocus();

			return true;
		}

		return false;
	}
}

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

import Model.Department;
import Model.Post;
import Model.StaffTable;
import Service.DataManageService;
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

public class StaffMngController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDetail;
    @FXML
    private Button btnRegist;
    @FXML
    private Button btnSearch;
    @FXML
    private ComboBox<Department> cmbSearchDept;
    @FXML
    private ComboBox<Post> cmbSearchPost;
    @FXML
    private TableColumn<StaffTable, Integer> colAge;
    @FXML
    private TableColumn<StaffTable, String> colDepartment;
    @FXML
    private TableColumn<StaffTable, String> colGender;
    @FXML
    private TableColumn<StaffTable, String> colPost;
    @FXML
    private TableColumn<StaffTable, Integer> colStaffId;
    @FXML
    private TableColumn<StaffTable, String> colStaffName;
    @FXML
    private TableColumn<StaffTable, String> colTelephone;
    @FXML
    private TableView<StaffTable> table;
    @FXML
    private TextField txtSearchStaffId;
    @FXML
    private TextField txtSearchStaffName;

	private static DataManageService dms;

	private List<StaffTable> listItems;
	private ObservableList<StaffTable> StaffListItems;

    public int selectedId = 0;

    // 部署リスト
    private Map<Integer, Department> mapDepartment = new HashMap<Integer, Department>();

	// 役職リスト
	private Map<Integer, Post> mapPost = new HashMap<Integer, Post>();

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

			// 各列の設定
			colStaffId.setCellValueFactory(p -> p.getValue().getStaffid().asObject());
			colStaffName.setCellValueFactory(p -> p.getValue().getStaffnaame());
			colGender.setCellValueFactory(p -> p.getValue().getGender());
			colAge.setCellValueFactory(p -> p.getValue().getAge().asObject());
			colTelephone.setCellValueFactory(p -> p.getValue().getTelephone());
			colDepartment.setCellValueFactory(p -> p.getValue().getDepartment());
			colPost.setCellValueFactory(p -> p.getValue().getPost());

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

		// 部署テーブルから対象のデータを取得
		ResultSet rsDept = dms.getDepartmentData();

		// 空欄の値設定
		Department dEmpty = new Department(0, "");
		mapDepartment.put(0, dEmpty);
		cmbSearchDept.getItems().add(dEmpty);

		// 部署コンボボックスの項目を設定
		while(rsDept.next()) {
			Department objDepartment = new Department(rsDept.getInt("DEPARTMENTID"), rsDept.getString("DEPARTMENTNAME"));
			mapDepartment.put(rsDept.getInt("DEPARTMENTID"), objDepartment);
			cmbSearchDept.getItems().add(objDepartment);
		}

		// 手続ステータステーブルから対象のデータを取得
		ResultSet rsPost = dms.getPostData();

		// 空欄の値設定
		Post pEmpty = new Post(0, "");
		mapPost.put(0, pEmpty);
		cmbSearchPost.getItems().add(pEmpty);

		// 手続ステータコンボボックスの項目を設定
		while(rsPost.next()) {
			Post objPost = new Post(rsPost.getInt("POSTID"), rsPost.getString("POSTNAME"));
			mapPost.put(rsPost.getInt("POSTID"), objPost);
			cmbSearchPost.getItems().add(objPost);
		}

		// 初期選択状態を設定
		cmbSearchDept.getSelectionModel().select(0);
		cmbSearchPost.getSelectionModel().select(0);
	}

	// 各値設定処理
	// 引数
	// なし
	// 戻り値
	// なし
	private void initDisplay() throws SQLException {

		listItems = new ArrayList<StaffTable>();

		// 各項目を初期化
		txtSearchStaffId.setText("");
		txtSearchStaffName.setText("");
		cmbSearchDept.getSelectionModel().select(0);
		cmbSearchPost.getSelectionModel().select(0);
		table.getSelectionModel().clearSelection();

		// テーブル設定用データ取得
		ResultSet rs = dms.getStaffListData();
		while(rs.next()) {
			StaffTable staffObj = new StaffTable(rs.getInt("STAFFID"), rs.getString("STAFFNAME"), rs.getString("GENDER"), rs.getInt("AGE"),
													rs.getString("TELEPHONE"), rs.getString("DEPARTMENTNAME"), rs.getString("POSTNAME"));
			listItems.add(staffObj);
		}

		// テーブルに値設定
		StaffListItems = FXCollections.observableArrayList(listItems);
		table.setItems(StaffListItems);

		// 新規登録ボタン活性化、詳細画面へボタン非活性化
		btnRegist.setDisable(false);
		btnDetail.setDisable(true);
	}

	// 従業員テーブルクリック
	// 引数
	// MouseEvent： event
	// 戻り値
	// なし
    @FXML
    void table_Clicked(MouseEvent event) {

    	// 選択行の位置取得
    	int intSelectedIndex = table.getSelectionModel().getSelectedIndex();

    	// 行未選択の場合
    	if(intSelectedIndex == -1) {
    		return;
    	}

    	// 選択行の取得
    	StaffTable objStaff = table.getItems().get(intSelectedIndex);
    	// 選択行のIDを取得
    	selectedId = objStaff.getStaffid().intValue();

    	// 詳細画面へボタン活性化
		btnDetail.setDisable(false);
    }

	// 値クリアボタンクリックイベント
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
    @FXML
    void btnClear_Click(ActionEvent event) {
		txtSearchStaffId.setText("");
		txtSearchStaffName.setText("");
		cmbSearchDept.getSelectionModel().select(0);
		cmbSearchPost.getSelectionModel().select(0);
    }

	// 検索ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnSearch_Click(ActionEvent event) throws SQLException {

		listItems = new ArrayList<StaffTable>();

		// 入力がない場合
		if(txtSearchStaffId.getText().equals("") && txtSearchStaffName.getText().equals("") &&
				cmbSearchDept.getSelectionModel().getSelectedIndex() == 0 &&
				cmbSearchPost.getSelectionModel().getSelectedIndex() == 0) {
				// テーブル設定用データ取得
				ResultSet rs = dms.getStaffListData();
				while(rs.next()) {
					StaffTable objStaff = new StaffTable(rs.getInt("STAFFID"), rs.getString("STAFFNAME"), rs.getString("GENDER"), rs.getInt("AGE"),
															rs.getString("TELEPHONE"), rs.getString("DEPARTMENTNAME"), rs.getString("POSTNAME"));
					listItems.add(objStaff);
				}

				// テーブルに値設定
				StaffListItems = FXCollections.observableArrayList(listItems);
				table.setItems(StaffListItems);

					return;
		}

		int intSearchStaffId = txtSearchStaffId.getText().equals("") ? 0 : Integer.parseInt(txtSearchStaffId.getText());

		// 検索条件からデータを取得
		ResultSet rs = dms.getStaffListData(intSearchStaffId, txtSearchStaffName.getText(),
												cmbSearchDept.getSelectionModel().getSelectedItem().getDepartmentid(),
												cmbSearchPost.getSelectionModel().getSelectedItem().getPostit());

		while(rs.next()) {
			StaffTable objStaff = new StaffTable(rs.getInt("STAFFID"), rs.getString("STAFFNAME"), rs.getString("GENDER"), rs.getInt("AGE"),
															rs.getString("TELEPHONE"), rs.getString("DEPARTMENTNAME"), rs.getString("POSTNAME"));
			listItems.add(objStaff);
		}

		// テーブルに値設定
		StaffListItems = FXCollections.observableArrayList(listItems);
		table.setItems(StaffListItems);
    }

	// 新規登録ボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnRegist_Click(ActionEvent event) throws IOException {
		BorderPane bp = (BorderPane)anchorPane.parentProperty().get();
		Parent root = FXMLLoader.load(getClass().getResource("StaffDetail.fxml"));
		bp.setCenter(root);
    }

	// 詳細画面へボタンクリックイベント
	// 引数
	// event
	// 戻り値
	// なし
    @FXML
    void btnDetail_Click(ActionEvent event) throws IOException {
    	StaffDetailController.intSelectedStaffId = selectedId;
    	BorderPane bp = (BorderPane)anchorPane.parentProperty().get();
		Parent root = FXMLLoader.load(getClass().getResource("StaffDetail.fxml"));
		bp.setCenter(root);
    }
}

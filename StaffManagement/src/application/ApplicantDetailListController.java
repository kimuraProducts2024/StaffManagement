package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Applicant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ApplicantDetailListController implements Initializable {

    @FXML
    private Button btnListCancel;

    @FXML
    private Button btnListSelect;

    @FXML
    private TableColumn<Applicant, Integer> colAge;

    @FXML
    private TableColumn<Applicant, Integer> colApplicantId;

    @FXML
    private TableColumn<Applicant, String> colApplicantName;

    @FXML
    private TableColumn<Applicant, String> colConducted;

    @FXML
    private TableColumn<Applicant, String> colGender;

    @FXML
    private TableColumn<Applicant, String> colResult;

    @FXML
    private TableView<Applicant> tableApplicantList;


	private static List<Applicant> listItems;
	private ObservableList<Applicant> applicantListItems;

    public static int selectedId = 0;

	// 初期化処理
	// 引数
	// URL： location
	// ResourceBundle： resources
	// 戻り値
	// なし
	@Override
	public void initialize(URL location, ResourceBundle resource) {

		// 選択行IDの初期化
		selectedId = 0;

		// 各列の設定
		colAge.setCellValueFactory(p -> p.getValue().getAge().asObject());
		colApplicantId.setCellValueFactory(p -> p.getValue().getApplicantid().asObject());
		colApplicantName.setCellValueFactory(p -> p.getValue().getApplicantname());
		colConducted.setCellValueFactory(p -> p.getValue().getConducted());
		colGender.setCellValueFactory(p -> p.getValue().getGender());
		colResult.setCellValueFactory(p -> p.getValue().getResult());

		// テーブルに値設定
		applicantListItems = FXCollections.observableArrayList(listItems);
		tableApplicantList.setItems(applicantListItems);
	}

	// キャンセルボタンクリック
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	public void btnListCancel_Click(ActionEvent event) {

		// 画面を閉じる
		btnListCancel.getScene().getWindow().hide();
	}

	// 選択ボタンクリック
	// 引数
	// ActionEvent： event
	// 戻り値
	// なし
	public void btnListSelect_Click(ActionEvent event) {

		// 選択行の位置取得
		int intSelectedIndex = tableApplicantList.getSelectionModel().getSelectedIndex();

		// 行未選択の場合
		if(intSelectedIndex == -1) {
			return;
		}

		// 選択行の取得
		Applicant selectedRow = tableApplicantList.getItems().get(tableApplicantList.getSelectionModel().getSelectedIndex());
		// 選択行IDを取得
		selectedId = selectedRow.getApplicantid().intValue();

		// 画面を閉じる
		btnListSelect.getScene().getWindow().hide();
	}

	// listItemsの初期化
	// 引数
	// なし
	// 戻り値
	// なし
	public static void initListItems() {
		listItems = new ArrayList<Applicant>();
	}

	// listItemsの取得
	// 引数
	// なし
	// 戻り値
	// なし
	public static List<Applicant> getListItems() {
		return listItems;
	}

}

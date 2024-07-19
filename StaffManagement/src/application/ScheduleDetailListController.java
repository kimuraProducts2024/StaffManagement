package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.ScheduleListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScheduleDetailListController implements Initializable {

    @FXML
    private TableView<ScheduleListItem> tableScheduleList;
    @FXML
    private TableColumn<ScheduleListItem, Integer> colScheduleId;
    @FXML
    private TableColumn<ScheduleListItem, LocalDateTime> colStartDateTime;
    @FXML
    private TableColumn<ScheduleListItem, Integer> colApplicantId;
    @FXML
    private TableColumn<ScheduleListItem, String> colApplicantName;
    @FXML
    private Button btnListSelect;
    @FXML
    private Button btnListCancel;

    private static List<ScheduleListItem> listItems;
    private ObservableList<ScheduleListItem> scheduleListItems;

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
		colScheduleId.setCellValueFactory(p -> p.getValue().getScheduleId().asObject());
		colStartDateTime.setCellFactory(column -> {
	        TableCell<ScheduleListItem, LocalDateTime> cell = new TableCell<ScheduleListItem, LocalDateTime>() {
	            private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	            @Override
	            protected void updateItem(LocalDateTime item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                    this.setText(format.format(item));

	                }
	            }
	        };

	        return cell;
	    });
		colStartDateTime.setCellValueFactory(p -> p.getValue().getScheduleDateTime());
		colApplicantId.setCellValueFactory(p -> p.getValue().getApplicantId().asObject());
		colApplicantName.setCellValueFactory(p -> p.getValue().getApplicantName());

		// テーブルに値設定
		scheduleListItems = FXCollections.observableArrayList(listItems);
		tableScheduleList.setItems(scheduleListItems);
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
		int intSelectedIndex = tableScheduleList.getSelectionModel().getSelectedIndex();

		// 行未選択の場合
		if(intSelectedIndex == -1) {
			return;
		}

		// 選択行の取得
		ScheduleListItem selectedRow = tableScheduleList.getItems().get(tableScheduleList.getSelectionModel().getSelectedIndex());
		// 選択行IDを取得
		selectedId = selectedRow.getScheduleId().intValue();

		// 画面を閉じる
		btnListSelect.getScene().getWindow().hide();
	}

	// listItemsの初期化
	// 引数
	// なし
	// 戻り値
	// なし
	public static void initListItems() {
		listItems = new ArrayList<ScheduleListItem>();
	}

	// listItemsの取得
	// 引数
	// なし
	// 戻り値
	// なし
	public static List<ScheduleListItem> getListItems() {
		return listItems;
	}
}

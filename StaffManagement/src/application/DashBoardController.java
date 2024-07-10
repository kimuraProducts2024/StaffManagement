package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashBoardController implements Initializable {

	private static String loginUserName;
	private static int loginUserAuthority;

	@FXML
	private BorderPane bp;

	@FXML
	private AnchorPane ap;

	@FXML
	private Label lblTitle1;

	@FXML
	private Label lblMainMenuTitle;

	@FXML
	private Label lblLoginUserName;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private ImageView imgViewLogout;

	public static void setLoginUserName(String userName) {
		loginUserName = userName;
	}

	public static void setLoginUserAuthority(int userAuthority) {
		loginUserAuthority = userAuthority;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(loginUserAuthority == 1) {
			imgViewLogo.setImage(new Image(Paths.get("bin/imgs/AdminUser.png").toUri().toString()));
		} else {
			imgViewLogo.setImage(new Image(Paths.get("bin/imgs/LoginUser.png").toUri().toString()));
		}

		lblLoginUserName.setText("ようこそ" + loginUserName + "さん");
	}

	// メインメニューボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void MainMenuBtn_Click(MouseEvent event) {
		bp.setCenter(ap);
	}

	// スケジュール管理ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void ScheduleMngBtn_Click(MouseEvent event) throws IOException {
		loadPage("ScheduleMng");
	}

	// 応募者管理ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void applicantMngBtn_Click(MouseEvent event) throws IOException {
		loadPage("ApplicationMng");
	}

	// 入社手続き管理ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void joiningProcedureBtn_Click(MouseEvent event) throws IOException {
		loadPage("JoiningProcedure");
	}

	// 従業員管理ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void staffMngBtn_Click(MouseEvent event) throws IOException {
		loadPage("StaffMng");
	}

	// 各種帳票出力ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void variousLedgerBtn_Click(MouseEvent event) throws IOException {
		loadPage("VariousLedger");
	}

	// 面接日程管理ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void scheduleBtn_Click(ActionEvent event) throws IOException {
		loadPage("ScheduleMng");
	}

	// 面接日程詳細ボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void scheduleDetailBtn_Click(ActionEvent event) throws IOException {
		loadPage("ScheduleDetail");
	}







	// 画面表示設定処理
	// 引数
	// page：fxmlのファイル名
	// 戻り値
	// なし
	private void loadPage(String page) throws IOException {
		Parent root = null;
		root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
		bp.setCenter(root);
	}

	// ログアウトボタンクリック
	// 引数
	// なし
	// 戻り値
	// なし
	@FXML
	public void logoutBtn_Click() {
		Platform.exit();
	}
}

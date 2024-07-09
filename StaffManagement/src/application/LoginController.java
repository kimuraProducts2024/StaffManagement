package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Service.DataManageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// ログインコントローラクラス
public class LoginController {

	private static DataManageService dms;

	@FXML
	private Label lblTitle1;

	@FXML
	private Label lblTitle2;

	@FXML
	private Label lblTitle3;

	@FXML
	private Label lblTitle4;

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblPassWord;

	@FXML
	private Label lblMessage;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField txtPassWord;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnCancel;

	// ログインボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void loginBtn_Click(ActionEvent event) throws SQLException, IOException{

		dms = DataManageService.createInstance();

		// メッセージ削除
		lblMessage.setText("");

		// ユーザ名、パスワードチェック
		if(!inputErrorLogin()) {
			// 入力エラーの場合、処理終了
			return;
		}

		// ログイン成功
		// ログインユーザ名、ユーザ権限の設定
		ResultSet rsUser = dms.getData("USER");
		while(rsUser.next()) {
			if(rsUser.getString("USERID").equals(txtUserName.getText())) {
				DashBoardController.setLoginUserName(rsUser.getString("USERID"));
				DashBoardController.setLoginUserAuthority(rsUser.getInt("AUTHORITY"));
			}
		}

		// メイン画面を表示する
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

		// ログイン画面を閉じる
		btnLogin.getScene().getWindow().hide();
	}

	// ログイン画面の入力値チェック
	// 引数
	// strUserName：ユーザ名テキスト
	// strPassWord：パスワードテキスト
	// 戻り値
	// true：チェックエラー
	// faluse：エラーなし
	public boolean inputErrorLogin() throws SQLException {

		String strUserName = txtUserName.getText();
		String strPassWord = txtPassWord.getText();


		// ユーザー名が空欄の場合
		if(strUserName.equals("")) {

			// エラーメッセージを表示
			lblMessage.setText("ユーザ名が空欄です。");
			return false;
		}

		// パスワードが空欄の場合
		if(strPassWord.equals("")) {

			// エラーメッセージを表示
			lblMessage.setText("パスワードが空欄です。");
			return false;
		}

		// ユーザ名がデータに存在しない場合
		if(!dms.existUserData(strUserName)) {

			// エラーメッセージを表示
			lblMessage.setText("入力したユーザは存在しません。");
			return false;
		}

		// パスワードが異なる場合
		if(!dms.checkPassWord(strUserName, strPassWord)) {

			// エラーメッセージを表示
			lblMessage.setText("パスワードが異なります。");
			return false;
		}

		// ログイン成功
		return true;
	}

	// キャンセルボタンクリック
	// 引数
	// event：ActionEvent
	// 戻り値
	// なし
	@FXML
	public void cancelBtn_Click(ActionEvent event) {
		Stage stage = (Stage)btnCancel.getScene().getWindow();
		stage.close();
	}
}

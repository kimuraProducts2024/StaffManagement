package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import application.MessageBoxController;
import application.MessageBoxController.MessageResponse;
import application.MessageBoxController.PropType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommonFunc {

	private static CommonFunc commonFunc;

	public static CommonFunc createInstance() {
		commonFunc = new CommonFunc();
		return commonFunc;
	}

	// btnButton2クリックイベント
 	// 引数
 	// strFxmlName：表示するFXMLファイルの名前
	// propType：メッセージの種類
	// strMessage：表示するメッセージの内容
 	// 戻り値
 	// なし
	public void showMessage(String strFxmlName, PropType propType, String strMessage) throws IOException {
		MessageBoxController.setPropType(propType);
		MessageBoxController.setStrMessage(strMessage);

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/" + strFxmlName + ".fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.showAndWait();
	}

	// btnButton2クリックイベント
 	// 引数
 	// なし
 	// 戻り値
	// MessageResponse：MessageBoxControllerクラスのresponseの値
	public MessageResponse getResponse() {
		return MessageBoxController.response;
	}

	// btnButton2クリックイベント
 	// 引数
 	// strFileName:ダウンロード対象のファイル名（拡張子付き）
	// divFileLength:ファイル長調整値
 	// 戻り値
 	// なし
	public void download(String strFileName) throws IOException {
		// 確認メッセージ
    	showMessage("MessageBox", PropType.Confirm, "ダウンロードしますか？");

		Path originalFile = Paths.get(Paths.get("bin/documents/" + strFileName).toUri().toString().substring(8,
				Paths.get("bin/application/" + strFileName).toUri().toString().length() - 2));
		Path targetFile = Paths.get("C:\\Users\\kimur\\Downloads\\" + strFileName);
		Files.copy(originalFile, targetFile, StandardCopyOption.REPLACE_EXISTING);

		// 完了メッセージ
    	showMessage("MessageBox", PropType.Info, "ダウンロードが完了しました。");
	}
}

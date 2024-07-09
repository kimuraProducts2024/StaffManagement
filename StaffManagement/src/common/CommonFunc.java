package common;

import java.io.IOException;

import application.MessageBoxController;
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

	public void showMessage(String strFxmlName, PropType propType, String strMessage) throws IOException {
		MessageBoxController.setPropType(propType);
		MessageBoxController.setStrMessage(strMessage);

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/" + strFxmlName + ".fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}
}

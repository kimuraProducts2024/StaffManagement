package application;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class MessageBoxController implements Initializable {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblMessage;

    @FXML
    private Button btnButton1;

    @FXML
    private Button btnButton2;

    @FXML
    private ImageView imageView;

    private static PropType propType;

    private static String strMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
			if (propType == PropType.Info) {
				lblTitle.setText("お知らせ");
				lblMessage.setTextFill(Color.BLACK);
				imageView.setImage(new Image(Paths.get("bin/imgs/Info.png").toUri().toString()));
				btnButton1.setVisible(false);
				btnButton2.setText("OK");
			} else if (propType == PropType.Confirm) {
				lblTitle.setText("確認");
				lblMessage.setTextFill(Color.BLACK);
				imageView.setImage(new Image(Paths.get("bin/imgs/Confirm.png").toUri().toString()));
				btnButton1.setText("OK");
				btnButton2.setText("キャンセル");
			} else if (propType == PropType.Warning) {
				lblTitle.setText("警告");
				lblMessage.setTextFill(Color.BLACK);
				imageView.setImage(new Image(Paths.get("bin/imgs/Warning.png").toUri().toString()));
				btnButton1.setVisible(false);
				btnButton2.setText("OK");
			} else {
				lblTitle.setText("エラー");
				lblMessage.setTextFill(Color.RED);
				imageView.setImage(new Image(Paths.get("bin/imgs/Error.png").toUri().toString()));
				btnButton1.setVisible(false);
				btnButton2.setText("OK");
			}
			lblMessage.setText(strMessage);
    }

    // btnButton1クリックイベント
 	// 引数
 	// event：ActionEvent
 	// 戻り値
 	// なし
    @FXML
    public MessageResponse btnButton1_Click(ActionEvent event) {
    	return MessageResponse.OK;
    }

    // btnButton2クリックイベント
 	// 引数
 	// event：ActionEvent
 	// 戻り値
 	// なし
    @FXML
    public MessageResponse btnButton2_Click(ActionEvent event) {
    	btnButton2.getScene().getWindow().hide();
    	if(propType == PropType.Info || propType == PropType.Warning) {
    		return MessageResponse.OK;
    	} else {
    		return MessageResponse.Cancel;
    	}
    }

    public static void setPropType(PropType pPropType) {
    	propType = pPropType;
    }

    public static PropType getPropType() {
    	return propType;
    }

    public static void setStrMessage(String message) {
    	strMessage = message;
    }

    public static String getStrMessage() {
    	return strMessage;
    }

    // メッセージの種類
    public static enum PropType {
    	Info,
    	Confirm,
    	Warning,
    	Error
    }

    // クリックしたボタンの種類
    public static enum MessageResponse {
    	OK,
    	Cancel
    }
}

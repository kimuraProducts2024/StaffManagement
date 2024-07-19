package application;

import java.io.IOException;
import java.nio.file.Paths;

import common.CommonFunc;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RetireDocsController {

    @FXML
    private ImageView image1;

	private static CommonFunc comFunc = CommonFunc.createInstance();

    @FXML
    void image1MouseEntered(MouseEvent event) {
    	image1.setImage(new Image(Paths.get("bin/imgs/Download.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image1MouseExited(MouseEvent event) {
    	image1.setImage(new Image(Paths.get("bin/imgs/WordOutput.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image1MouseClicked(MouseEvent event) throws IOException {

    	// ダウンロード処理
    	comFunc.download("退職証明書.doc");
    }
}

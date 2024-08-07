package application;

import java.io.IOException;
import java.nio.file.Paths;

import common.CommonFunc;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class InterviewDocsController {

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

	private static CommonFunc comFunc = CommonFunc.createInstance();

    @FXML
    void image1MouseEntered(MouseEvent event) {
    	image1.setImage(new Image(Paths.get("bin/imgs/Download.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image1MouseExited(MouseEvent event) {
    	image1.setImage(new Image(Paths.get("bin/imgs/ExcelOutput.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image1MouseClicked(MouseEvent event) throws IOException {

    	// ダウンロード処理
    	comFunc.download("面接シート.xlsx");
    }

    @FXML
    void image2MouseEntered(MouseEvent event) {
    	image2.setImage(new Image(Paths.get("bin/imgs/Download.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image2MouseExited(MouseEvent event) {
    	image2.setImage(new Image(Paths.get("bin/imgs/ExcelOutput.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image2MouseClicked(MouseEvent event) throws IOException {

    	// ダウンロード処理
    	comFunc.download("面接評価シート.xls");
    }

    @FXML
    void image3MouseEntered(MouseEvent event) {
    	image3.setImage(new Image(Paths.get("bin/imgs/Download.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image3MouseExited(MouseEvent event) {
    	image3.setImage(new Image(Paths.get("bin/imgs/ExcelOutput.png").toUri().toString(), 79, 72, false, false));
    }

    @FXML
    void image3MouseClicked(MouseEvent event) throws IOException {

    	// ダウンロード処理
    	comFunc.download("面接後アンケート.xlsx");
    }
}

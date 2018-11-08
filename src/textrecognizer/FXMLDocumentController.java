/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textrecognizer;

import com.recognition.software.jdeskew.ImageDeskew;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Alexs
 */
public class FXMLDocumentController implements Initializable
{

    String fullText;

    Stage stage;

    @FXML
    private Button buttonChose;
    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane anchorePane;
    @FXML
    private TextField textFieldX;
    @FXML
    private TextField textFieldY;
    @FXML
    private TextField textFieldWidth;
    @FXML
    private TextField textFieldHeight;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("rus");

        buttonChose.setOnMousePressed((event) ->
        {

            stage = (Stage) anchorePane.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            try
            {
                // fullText = tesseract.doOCR(selectedFile);
                //fullText = tesseract.doOCR(selectedFile, new Rectangle(0, 0, 100, 100));
                //fullText = tesseract.doOCR(new File("d:/prog/java/TextRecognizer/text-photographed.jpg"), new Rectangle(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()), Integer.parseInt(textFieldWidth.getText()), Integer.parseInt(textFieldHeight.getText())));
                fullText = tesseract.doOCR(selectedFile, new Rectangle(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()), Integer.parseInt(textFieldWidth.getText()), Integer.parseInt(textFieldHeight.getText())));
            }
            catch (TesseractException ex)
            {
                ex.printStackTrace();
            }
            textArea.setText(fullText);
        });

    }

}

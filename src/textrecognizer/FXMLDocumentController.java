/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textrecognizer;

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
    
    Stage stage ;

    @FXML
    private Button buttonChose;
    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane anchorePane;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("rus");

        buttonChose.setOnMousePressed((event) ->
        {
            stage =(Stage) anchorePane.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            try
            {
                fullText = tesseract.doOCR(selectedFile);
            }
            catch (TesseractException ex)
            {
                ex.printStackTrace();
            }
            textArea.setText(fullText);

        });

    }

}

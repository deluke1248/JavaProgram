package com.network.javagroup7;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Button btn_addUser;
    @FXML
    private TextArea q1;
    @FXML
    private TextArea q2;
    @FXML
    private TextArea q3;
    @FXML
    private TextArea q4;
    @FXML
    private TextArea q5;
    @FXML
    private Button btn_save;
    @FXML
    private TextField txt_qtsCode;
    @FXML
    private Button btn_saveFile;
    @FXML
    private Button btn_openFile;
    @FXML
    private TextField txt_paperName;
    @FXML
    private ImageView ivFile;
    @FXML
    private Button btn_logout;
    @FXML
    private Label lb_welcomeName;

    final FileChooser fc = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "Log in", null);
            }
        });


        btn_openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                fc.setTitle("My file chooser");
                // set the initial directory
                fc.setInitialDirectory(new File(System.getProperty("user.home")));


                fc.setInitialDirectory(new File(System.getProperty("user.home")));

                // reset all filters
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif", "*.pdf"));

                File file = fc.showOpenDialog(null);
                if(file != null) {
                    ivFile.setImage(new Image(file.toURI().toString()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("A file is invalid");
                    alert.show();
                }

            }
        });



        btn_saveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.addPaper(event, txt_paperName.getText(), String.valueOf(ivFile.getImage()));
            }
        });


        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_qtsCode.getText().trim().isEmpty() && !q1.getText().trim().isEmpty()){
                    DBUtils.set_qst(actionEvent, txt_qtsCode.getText(), q1.getText());
                    txt_qtsCode.clear();
                    q1.clear();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter a question code and a question");
                    alert.show();

                }

               // DBUtils.set_qst(actionEvent, txt_qtsCode.getText(), q1.getText());
            }
        });

    }
}

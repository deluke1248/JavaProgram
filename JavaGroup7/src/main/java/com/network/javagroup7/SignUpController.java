package com.network.javagroup7;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button btn_signup;
    @FXML
    private Button btn_login;
    @FXML
    private RadioButton rb_male;
    @FXML
    private RadioButton rb_female;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_department;
    @FXML
    private TextField txt_program;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_male.setToggleGroup(toggleGroup);
        rb_female.setToggleGroup(toggleGroup);

        rb_male.setSelected(true);

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if (!txt_fname.getText().trim().isEmpty() && !txt_lname.getText().trim().isEmpty() && !txt_department.getText().trim().isEmpty()
                && !txt_program.getText().trim().isEmpty() && !txt_username.getText().trim().isEmpty() && !txt_password.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, txt_fname.getText(), txt_lname.getText(), txt_department.getText(), txt_program.getText(),
                            toggleName, txt_username.getText(), txt_password.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up");
                    alert.show();
                }
            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event, "login.fxml", "Log in", null);
            }
        });

    }
}

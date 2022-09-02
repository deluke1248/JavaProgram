package com.network.javagroup7;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private TextField txt_search;
    @FXML
    private TextArea q2;
    @FXML
    private TextArea q1;
    @FXML
    private TextArea q3;
    @FXML
    private TextArea q4;
    @FXML
    private TextArea q5;
    @FXML
    private Button btn_save;
    @FXML
    public TextField txt_qtsCode;
    @FXML
    private TableColumn tb_paperName;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableView tb_view;
    //    @FXML
//    private Hyperlink hl_backward;
//    @FXML
//    private Hyperlink hl_forward;
//    @FXML
//    private Hyperlink hl_reload;
//    @FXML
//    private Hyperlink hl_home;
//    @FXML
//    private TextField txt_search;
//    @FXML
//    private Hyperlink hl_search;
    @FXML
    private Button btn_logout;
    @FXML
    private Label lb_welcomeName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //the code to logout
        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "Log in", null);
            }
        });



        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    String search = txt_qtsCode.getText();
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                            "root", "Maroba1248@");

                    preparedStatement = connection.prepareStatement("SELECT q1 FROM set_qst WHERE qst_code = ?");
                    preparedStatement.setString(1, search);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()==true){
                        String qst1 = resultSet.getString(1);

                        q1.setText(qst1);


                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });
    }


    //displaying user info after login
    public void setUserInformation(String username){
        lb_welcomeName.setText(username);
    }
}

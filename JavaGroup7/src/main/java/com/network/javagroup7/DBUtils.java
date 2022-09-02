package com.network.javagroup7;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root = null;

        if (username != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                DashboardController dashboardController = loader.getController();
                dashboardController.setUserInformation(username);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 960, 700));
        stage.show();
    }


    public static void signUpUser(ActionEvent event, String fName, String lName, String department, String program,
                                  String gender, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {

                String sql = "INSERT INTO users (fName, lName, department, program, gender, username, password) VALUES (?,?,?,?,?,?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, fName);
                psInsert.setString(2, lName);
                psInsert.setString(3, department);
                psInsert.setString(4, program);
                psInsert.setString(5, gender);
                psInsert.setString(6, username);
                psInsert.setString(7, password);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new user was inserted successfully!");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("A new user added successfully!");
                    alert.show();

                }

                changeScene(event, "dashboard.fxml", "Dashboard", username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }





    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)){
                        changeScene(event, "dashboard.fxml", "Welcome", username);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }








    public static void logInAdmin(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");
            preparedStatement = connection.prepareStatement("SELECT password FROM admin WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)){
                        changeScene(event, "adminDashboard.fxml", "Welcome Admin", null);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }






    public static void addPaper(ActionEvent event, String paperName, String paper){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM question WHERE paperName = ?");
            psCheckUserExist.setString(1, paperName);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("paper already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Question paper already exists!");
                alert.show();
            } else {

                String sql = "INSERT INTO question (paperName, paper) VALUES (?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, paperName);
                psInsert.setString(2, paper);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new Question paper was inserted successfully!");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("A new question paper added successfully!");
                    alert.show();

                }

                //changeScene(event, "dashboard.fxml", "Dashboard", username);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }


    public static void search(ActionEvent event, Integer id, String paperName, String paper){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");

            String sql = "SELECT id, paperName, paper FROM question";
            resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()){

                Integer qId = resultSet.getInt("id");
                String qPaperName = resultSet.getString("paperName");
                String qPaper = resultSet.getString("paper");


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }





    public static void set_qst(ActionEvent event, String qst_code, String q1){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3360/java_group7_db",
                    "root", "Maroba1248@");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM set_qst WHERE qst_code = ?");
            psCheckUserExist.setString(1, qst_code);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Question code  exist");
                alert.show();
            } else {

                String sql = "INSERT INTO set_qst (qst_code, q1) VALUES (?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, qst_code);
                psInsert.setString(2, q1);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new user was inserted successfully!");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("New question added successfully!");
                    alert.show();

                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }
}

package GUI.GUIController;


import Controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.UserController.*;
import GUI.GUIController.MainApp;
import model.User;


public class SignInController implements Initializable{
    private UserController usercontroller;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button SignInButton;

    @FXML
    private Button ToRegisterButton;

    @FXML
    private Text tips;

    private Socket socket = null;
    private PrintWriter output = null;

    @FXML
    public void ToMain(ActionEvent event) {
        usercontroller = new UserController();

        String UserName = username.getText();
        String Password = password.getText();


        if(usercontroller.userLogin(UserName, Password)){

            if(usercontroller.isAdmin(UserName)){
                try {
                    socket = new Socket("localhost",1056);
                    output = new PrintWriter(socket.getOutputStream());
                    output.println(UserName);
                    output.flush();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                MainApp.gotoTeacherPage();
            }
            else{
                try {
                    socket = new Socket("localhost",1056);
                    output = new PrintWriter(socket.getOutputStream());
                    output.println(UserName);
                    output.flush();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                MainApp.gotoStudentPage();
            }
        }
        else{
            tips.setText("登录失败，用户名或密码错误。");
        }
    }

    @FXML
    public void ToRegister(ActionEvent event) {
        MainApp.gotoSignUpPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

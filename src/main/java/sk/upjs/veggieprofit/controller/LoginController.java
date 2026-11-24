package sk.upjs.veggieprofit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sk.upjs.veggieprofit.App;
import sk.upjs.veggieprofit.Factory;
import sk.upjs.veggieprofit.service.AuthService;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label messageLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    private final AuthService authService = Factory.INSTANCE.getAuthService();

    @FXML
    void onLogin(ActionEvent event) {
        try {
            authService.login(usernameField.getText(), passwordField.getText());
            switchScene ("/main.fxml");
        }
        catch (IllegalArgumentException e){
            showError(e.getMessage());
        }
        catch (Exception e) {
            showError("Chyba: " + e.getMessage());
        }
    }

    @FXML
    void onOpenRegistration(ActionEvent event) {
        try{
            switchScene("/registration.fxml");
        }
        catch (Exception e){
            showError("Nepodarilo sa otvoriť registráciu.");
        }
    }

    @FXML
    void initialize() {
        assert messageLabel != null : "fx:id=\"messageLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";

    }

    private void switchScene(String fxmlPath) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    private void showError(String text){

        messageLabel.getStyleClass().removeAll("message-success", "message-error");
        messageLabel.getStyleClass().add("message-error");
        messageLabel.setText(text);
    }
}

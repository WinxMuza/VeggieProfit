package sk.upjs.veggieprofit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class RegistrationController {

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordAgainField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    private final AuthService authService = Factory.INSTANCE.getAuthService();
    @FXML
    void onBackToLogin(ActionEvent event) {
        try{
            switchScene("/login.fxml");
        }
        catch (Exception e){
            showError("Nepodarilo sa otvoriť prihlásenie.");
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        if (!passwordField.getText().equals(passwordAgainField.getText())){
            showError("Heslá sa nezhodujú!");
            return;
        }
        try{
            authService.register(usernameField.getText(), passwordField.getText());
            passwordField.clear();
            passwordAgainField.clear();
            usernameField.clear();
            showSuccess("Registrácia úspešná, teraz sa prihláste.");
        }
        catch (IllegalArgumentException e){
            showError(e.getMessage());
        }
        catch (Exception e) {
            showError("Chyba: " + e.getMessage());
        }
    }

    @FXML
    void initialize() {
        assert messageLabel != null : "fx:id=\"messageLabel\" was not injected: check your FXML file 'registration.fxml'.";
        assert passwordAgainField != null : "fx:id=\"passwordAgainField\" was not injected: check your FXML file 'registration.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'registration.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'registration.fxml'.";
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

    private void showSuccess(String text){
        messageLabel.getStyleClass().removeAll("message-success", "message-error");
        messageLabel.getStyleClass().add("message-success");
        messageLabel.setText(text);
    }
}

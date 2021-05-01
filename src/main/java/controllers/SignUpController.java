package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpController extends Controller
{
    public static final String VALID_EMAIL = "";

    @FXML
    public TextField nameInput;
    @FXML
    public TextField passwordInput;
    @FXML
    public TextField emailInput;
    @FXML
    public TextField confirmPasswordInput;

    public void goToHomeScene(MouseEvent mouseEvent) throws IOException
    {
        switchScene(mouseEvent, HOME_SCENE);
    }

    public void validateRegistration(MouseEvent mouseEvent)
    {
        try {
            goToHomeScene(mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

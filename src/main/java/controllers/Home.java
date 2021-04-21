package controllers;

import database.UserDataReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.w3c.dom.Element;

import java.io.IOException;
import java.nio.file.Paths;

public class Home extends Controller
{
    public static final String FLASHCARD_PATH = "./src/main/java/components/flashcard.fxml";

    @FXML
    public ChoiceBox flashcardsThemes;
    @FXML
    public FlowPane flashcardBox;

    private UserDataReader userDataReader;

    @FXML
    public void initialize()
    {
        this.userDataReader = new UserDataReader();
        addFlashcards();
    }

    @FXML
    public void goToNotificationsScene(MouseEvent mouseEvent)
    {
        switchScene(mouseEvent, NOTIFICATIONS_SCENE);
    }

    @FXML
    public void exitApplication(MouseEvent mouseEvent)
    {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void addFlashcards()
    {
        Element flashcards = (Element) this.userDataReader.userDataDocument.getElementsByTagName("flashcards").item(0);
        int flashcardListLength = flashcards.getElementsByTagName("flashcard").getLength();
        for (int flashcardIndex = 0; flashcardIndex < flashcardListLength; flashcardIndex++) {
            FlowPane flashcardFxml = null;
            try {
                FXMLLoader loader = new FXMLLoader(Paths.get(FLASHCARD_PATH).toUri().toURL());
                flashcardFxml = loader.load();
                Flashcard flashcardController = loader.getController();
                flashcardController.setFlashcardProperties(flashcardIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.flashcardBox.getChildren().add(flashcardFxml);
        }
    }
}
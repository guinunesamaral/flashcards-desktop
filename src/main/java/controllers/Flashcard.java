package controllers;

import database.UserDataReader;
import database.UserDataWriter;
import database.WriterOptions;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.w3c.dom.Element;

import java.io.IOException;
import java.nio.file.Paths;

public class Flashcard extends Controller
{
    @FXML
    public FlowPane flashcardWrapper;
    @FXML
    public Pane flashcardAnchorPane;
    @FXML
    public Label flashcardTitle;
    @FXML
    public Label flashcardDescription;
    @FXML
    public Pane flashcardToolBar;

    private UserDataReader userDataReader;
    private String flashcardId;
    private RotateTransition rotateTransition;

    @FXML
    public void initialize()
    {
        this.userDataReader = new UserDataReader();
        this.rotateTransition = new RotateTransition();
    }

    public void setFlashcardProperties(int flashcardIndex)
    {
        this.flashcardAnchorPane.getStyleClass().addAll("green-bg");
        this.flashcardAnchorPane.onMouseClickedProperty().set(mouseEvent -> setFlashcardBehaviorOnMouseClick());
        setFlashcardComponentsProperties(flashcardIndex);
    }

    public void setFlashcardComponentsProperties(int flashcardIndex)
    {
        Element user = this.userDataReader.getRootElements();
        Element flashcards = this.userDataReader.getTagElements(user, "flashcards", 0);
        Element flashcard = this.userDataReader.getTagElements(flashcards, "flashcard", flashcardIndex);
        this.flashcardId = this.userDataReader.getTagTextContent(flashcard, "_id");

        setTitleProperties(this.userDataReader.getTagTextContent(flashcard, "title"));
        setDescriptionProperties(this.userDataReader.getTagTextContent(flashcard, "description"));
        setRotateProperties();
    }

    public void setTitleProperties(String title)
    {
        this.flashcardTitle.setText(title);
    }

    public void setDescriptionProperties(String description)
    {
        this.flashcardDescription.setText(description);
        this.flashcardDescription.visibleProperty().set(false);
    }

    public void setRotateProperties()
    {
        this.rotateTransition.setDuration(Duration.millis(1000));
        this.rotateTransition.setAxis(Rotate.Y_AXIS);
        this.rotateTransition.setCycleCount(1);
        this.rotateTransition.setNode(flashcardAnchorPane);
    }

    public void setFlashcardBehaviorOnMouseClick()
    {
        this.rotateTransition.play();
        if (this.flashcardTitle.visibleProperty().get()) {
            this.flashcardTitle.visibleProperty().set(false);
            this.flashcardDescription.visibleProperty().set(true);
            this.flashcardToolBar.visibleProperty().set(false);
        } else {
            this.flashcardTitle.visibleProperty().set(true);
            this.flashcardDescription.visibleProperty().set(false);
            this.flashcardToolBar.visibleProperty().set(true);
        }
        if (this.flashcardAnchorPane.getStyleClass().contains("green-bg")) {
            this.flashcardAnchorPane.getStyleClass().remove("green-bg");
            this.flashcardAnchorPane.getStyleClass().add("red-bg");
        } else {
            this.flashcardAnchorPane.getStyleClass().remove("red-bg");
            this.flashcardAnchorPane.getStyleClass().add("green-bg");
        }
    }

    @FXML
    public void removeFlashcard(MouseEvent mouseEvent)
    {
        UserDataWriter userDataWriter = new UserDataWriter(WriterOptions.USE_EXISTING_FILE);
        // This action removes the flashcard from the user-data file
        userDataWriter.removeFlashcard(this.flashcardId);
        switchScene(mouseEvent, HOME_SCENE);
    }

    @FXML
    public void shareFlashcard(MouseEvent mouseEvent)
    {
        switchScene(mouseEvent, SHARE_FLASHCARD);
    }

    @FXML
    public void updateFlashcard(MouseEvent mouseEvent)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Paths.get(UPDATE_FLASHCARD).toUri().toURL());
            loader.load();
            UpdateFlashcard updateFlashcardController = loader.getController();
            updateFlashcardController.setFlashcardId(this.flashcardId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switchScene(mouseEvent, UPDATE_FLASHCARD);
    }
}
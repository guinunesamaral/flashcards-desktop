package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class Controller
{
    public static final String SIGN_IN_SCENE = "./src/main/resources/scenes/sign-in.fxml";
    public static final String SIGN_UP_SCENE = "./src/main/resources/scenes/sign-up.fxml";
    public static final String REDEFINE_PASSWORD_SCENE = "./src/main/resources/scenes/redefine-password.fxml";
    public static final String HOME_SCENE = "./src/main/resources/scenes/home.fxml";
    public static final String NOTIFICATIONS_SCENE = "./src/main/resources/scenes/notifications.fxml";
    public static final String STUDY_SCENE = "./src/main/resources/scenes/study.fxml";
    public static final String ADD_FLASHCARD_SCENE = "./src/main/resources/scenes/add-flashcard.fxml";
    public static final String FLASHCARD = "./src/main/resources/components/flashcard.fxml";
    public static final String RECEIVED_FLASHCARD = "./src/main/resources/components/received-flashcard.fxml";
    public static final String EXPANDED_FLASHCARD_SCENE = "./src/main/resources/components/expanded-flashcard.fxml";
    public static final String SHARE_FLASHCARD_SCENE = "./src/main/resources/scenes/share-flashcard.fxml";
    public static final String UPDATE_FLASHCARD_SCENE = "./src/main/resources/scenes/update-flashcard.fxml";

    public void switchScene(MouseEvent mouseEvent,
                            String sceneFxmlPath)
    {
        try {
            Scene scene = new Scene(FXMLLoader.load(Paths.get(sceneFxmlPath).toUri().toURL()));
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage openScene(String sceneFxmlPath, String stageTitle)
    {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(Paths.get(sceneFxmlPath).toUri().toURL()));
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(stageTitle);
            stage.setResizable(false);
            stage.show();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeCurrentScene(MouseEvent mouseEvent)
    {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.close();
    }

    public void updateHomeScene()
    {
        try {
            FXMLLoader loader = new FXMLLoader(Paths.get(HOME_SCENE).toUri().toURL());
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

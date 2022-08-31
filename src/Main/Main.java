package Main;

import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Platform.setImplicitExit(false);
        Parent startWindow = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getClassLoader().getResource("Main/MainUI/main.fxml")));
        window.initStyle(StageStyle.UNDECORATED);
        window.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(startWindow, 472, 362);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().
                getResource("Main/MainUI/combo_style.css")).toExternalForm());

        window.setScene(scene);
        window.setMinWidth(472);
        window.setMinHeight(362);
        window.getIcons().add(new Image("file:///" + System.getProperty("user.dir") + "\\resources\\logo.jpg"));
        window.show();
    }

}


package Main.ProfileUI;

import Main.Profile.ProfileProperties;
import com.jfoenix.controls.JFXButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;

public class ProfileController implements Initializable {

    @FXML
    private JFXButton profile;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView steam;
    @FXML
    private ImageView resolution;
    @FXML
    private ImageView game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profile.setText(ProfileProperties.getTitle());

        Icon steamIcon = ProfileProperties.getSteam();
        ImageIcon steamImageIcon = (ImageIcon) steamIcon;
        Image steamFXImage = SwingFXUtils.toFXImage(convertToBufferedImage(steamImageIcon.getImage()), null);
        steam.setImage(steamFXImage);

        Icon resolutionIcon = ProfileProperties.getResolution();
        ImageIcon resolutionImageIcon = (ImageIcon) resolutionIcon;
        Image resolutionFXImage = SwingFXUtils.toFXImage(convertToBufferedImage(resolutionImageIcon.getImage()), null);
        resolution.setImage(resolutionFXImage);

        if (ProfileProperties.getGame() != null) {
            game.setImage(new Image(ProfileProperties.getGame().toURI().toString()));
        } else {
            game.setImage(new Image(new java.io.File("images\\unknown.png").toURI().toString()));
        }

        ProfileProperties.getPanes().add(pane);
    }

    private BufferedImage convertToBufferedImage(java.awt.Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return newImage;
    }


}

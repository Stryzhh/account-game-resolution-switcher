package Main.Profile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;

public class ProfileProperties {

    private static String title;
    private static Icon steam;
    private static Icon resolution;
    private static File game;
    private static final ArrayList<AnchorPane> panes = new ArrayList<>();
    private static int x;
    private static int y;

    public static void setAll(String title, Icon steam, Icon resolution, File game) {
        ProfileProperties.title = title;
        ProfileProperties.steam = steam;
        ProfileProperties.resolution = resolution;
        ProfileProperties.game = game;
    }

    public static String getTitle() {
        return title;
    }

    public static Icon getSteam() {
        return steam;
    }

    public static Icon getResolution() {
        return resolution;
    }

    public static File getGame() {
        return game;
    }

    public static ArrayList<AnchorPane> getPanes() {
        return panes;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void addY() {
        ProfileProperties.y++;
    }

    public static void addX() {
        ProfileProperties.x++;
    }

    public static void setY(int y) {
        ProfileProperties.y = y;
    }

    public static void setX(int x) {
        ProfileProperties.x = x;
    }

}

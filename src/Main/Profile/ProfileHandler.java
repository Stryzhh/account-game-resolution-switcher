package Main.Profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import net.jimmc.jshortcut.JShellLink;
import net.sf.image4j.codec.ico.ICOEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileHandler {

    public static Text status;
    private Profile profile;
    private AnchorPane pane;
    private final String title;
    private final String steam;
    private final String resolution;
    private final String gameID;
    private final String dir = System.getProperty("user.dir");
    private final String home = System.getProperty("user.home");

    private static final ArrayList<Profile> profiles = new ArrayList<>();
    private static final ArrayList<JFXButton> profileInterfaces = new ArrayList<>();
    public static AnchorPane profileWindow = new AnchorPane();
    public static String steamEXE = "";

    public ProfileHandler() {
        this.title = null;
        this.steam = null;
        this.resolution = null;
        this.gameID = null;
        this.profile = null;
        this.pane = null;
    }

    public ProfileHandler(String title, String steam, String resolution, String game) {
        this.title = title;
        this.steam = steam;
        this.resolution = resolution;
        this.gameID = game;
        this.profile = null;
        this.pane = null;
    }

    public ProfileHandler(Profile profile, AnchorPane pane) {
        this.profile = profile;
        this.pane = pane;
        this.title = profile.getProfileTitle();
        this.steam = profile.getSteamLNK();
        this.resolution = profile.getResolutionBATCH();
        this.gameID = profile.getGameID();
    }

    public void create() throws IOException {
        profile = new Profile("", title, steam, resolution, gameID, "", "", "");

        profiles.add(profile);
        update();
        String exec = createEXEC();
        String ico = createICO();
        String shortcut = createShortcut(exec, ico);

        profile.setProfileIcon(ico);
        profile.setBatch(exec);
        profile.setShortcut(shortcut);
        createJSON();
    }

    private void createJSON() throws IOException {
        FileWriter writer = new FileWriter(dir + "\\resources\\profiles\\" + title + ".json");
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        writer.write(g.toJson(profile));
        writer.close();
    }

    public void draw() {
        ProfileProperties.setX(0);
        ProfileProperties.setY(0);

        for (Profile profile : ProfileHandler.getProfiles()) {
            try {
                Icon steamIcon = FileSystemView.getFileSystemView().getSystemIcon(new File(profile.getSteamLNK()));
                Icon resolutionIcon = FileSystemView.getFileSystemView().getSystemIcon(new File(profile.getResolutionBATCH()));

                if (profile.getGameIcon().equals("")) {
                    ProfileProperties.setAll(profile.getProfileTitle(), steamIcon, resolutionIcon, new File(
                            System.getProperty("user.dir") + "\\resources\\icons\\game\\no_game.png"));
                } else {
                    ProfileProperties.setAll(profile.getProfileTitle(), steamIcon, resolutionIcon,
                            new File(profile.getGameIcon()));
                }

                JFXButton profileUI = FXMLLoader.load(Objects.requireNonNull(ProfileHandler.class
                        .getClassLoader().getResource("Main/ProfileUI/profile.fxml")));
                profileUI.setLayoutX((ProfileProperties.getX() * 116) + 7);
                profileUI.setLayoutY((ProfileProperties.getY() * 138) + 7);
                profileUI.setCursor(Cursor.HAND);
                ProfileProperties.addX();

                profileWindow.getChildren().add(profileUI);
                ProfileHandler.getProfileInterfaces().add(profileUI);
                interact(profileUI);

                if (ProfileProperties.getY() == 1 && ProfileProperties.getX() == 4) {
                    break;
                } else if (ProfileProperties.getX() == 4) {
                    ProfileProperties.addY();
                    ProfileProperties.setX(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (ProfileProperties.getY() == 1 && ProfileProperties.getX() == 4) {
            System.out.println("reached max");
        } else {
            try {
                Icon steamIcon = FileSystemView.getFileSystemView().getSystemIcon(new File(profile.getSteamLNK()));
                Icon resolutionIcon = FileSystemView.getFileSystemView().getSystemIcon(new File(profile.getResolutionBATCH()));

                if (gameID.equals("0")) {
                    profile.setGameIcon("");
                } else {
                    profile.setGameIcon(getSteamIcon(gameID, profile.getProfileTitle()).getAbsolutePath());
                }

                if (profile.getGameIcon().equals("")) {
                    ProfileProperties.setAll(profile.getProfileTitle(), steamIcon, resolutionIcon, new File(
                            System.getProperty("user.dir") + "\\resources\\icons\\game\\no_game.png"));
                } else {
                    ProfileProperties.setAll(profile.getProfileTitle(), steamIcon, resolutionIcon,
                            new File(profile.getGameIcon()));
                }

                JFXButton profileUI = FXMLLoader.load(Objects.requireNonNull(getClass()
                        .getClassLoader().getResource("Main/ProfileUI/profile.fxml")));
                profileUI.setLayoutX((ProfileProperties.getX() * 116) + 7);
                profileUI.setLayoutY((ProfileProperties.getY() * 138) + 7);
                profileUI.setCursor(Cursor.HAND);
                ProfileProperties.addX();

                getProfileWindow().getChildren().add(profileUI);
                profileInterfaces.add(profileUI);
                interact(profileUI);

                if (ProfileProperties.getX() == 4) {
                    ProfileProperties.addY();
                    ProfileProperties.setX(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File getSteamIcon(String game, String title) throws IOException {
        String request = "https://store.steampowered.com/api/appdetails?appids=" + game + "&format=json";

        URL api = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) api.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            JSONObject json = new JSONObject(reader.readLine());

            URL url = new URL(json.getJSONObject(game).getJSONObject("data").getString("header_image"));
            File file = new File(System.getProperty("user.dir") + "\\resources\\icons\\game\\" + title + "Game.jpg");

            BufferedImage image = ImageIO.read(url);
            int quarter = image.getWidth() / 4;
            BufferedImage resizedImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(image.getSubimage(quarter, 0, quarter * 2, image.getHeight()), 0, 0,
                    64, 64, null);
            graphics2D.dispose();
            ImageIO.write(resizedImage, "jpg", file);

            return file;
        }
    }

    public String createEXEC() throws IOException {
        String name = dir + "\\resources\\exec\\" + title + ".bat";
        File file = new File(name);

        FileOutputStream output = new FileOutputStream(file);
        DataOutputStream input = new DataOutputStream(output);
        String gameCommand;
        if (gameID.equals("0")) {
            gameCommand = "\"" + System.getProperty("user.dir") + "\\resources\\shortcuts\\game\\no-game-load.lnk" + "\"";
        } else {
            gameCommand = "\"" + steamEXE + "\" -applaunch " + gameID;
        }
        input.writeBytes("\"" + steam + "\"\n" + "timeout /T 5" + "\n\"" + resolution + "\"\n" + "timeout /T 5" + "\n" + gameCommand);
        input.close();
        output.close();

        return name;
    }

    public String createICO() throws IOException {
        pane = ProfileProperties.getPanes().get(ProfileProperties.getPanes().size() - 1);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        BufferedImage bufferedImage = new BufferedImage(85, 79, BufferedImage.TYPE_INT_ARGB);
        SwingFXUtils.fromFXImage(pane.snapshot(parameters, new WritableImage(85, 79)), bufferedImage);
        WritableImage oi = SwingFXUtils.toFXImage(bufferedImage, new WritableImage(85, 79));
        BufferedImage renderedImage = SwingFXUtils.fromFXImage(oi, null);

        String icon = dir + "\\resources\\icons\\" + profile.getProfileTitle() + ".ico";
        ICOEncoder.write(renderedImage, new File(icon));

        return icon;
    }

    public String createShortcut(String bat, String icon) {
        JShellLink link = new JShellLink();

        String sc = dir + "\\resources\\shortcuts\\profile\\";
        File shortcut = new File(sc);
        link.setFolder(shortcut.getAbsolutePath());
        link.setName(title);
        link.setPath(JShellLink.getDirectory("") + bat);
        link.setIconLocation(JShellLink.getDirectory("") + icon);
        link.save();

        return sc + title;
    }

    public void load() throws IOException {
        Runtime.getRuntime().exec("cmd /c start \"\" " + profile.getBatch());
        status.setText("Status: Profile loaded");
    }

    public void createDesktop() throws IOException {
        Files.copy(Path.of(profile.getShortcut()), Path.of(home + "\\Desktop\\" + profile.getProfileTitle()
                + ".lnk"), StandardCopyOption.REPLACE_EXISTING);
        status.setText("Status: Desktop Shortcut created");
    }

    public void createStartMenu() throws IOException {
        Files.copy(Path.of(profile.getShortcut()), Path.of(home + "\\AppData\\Roaming\\Microsoft\\Windows\\" +
                "Start Menu\\Programs\\" + profile.getProfileTitle() + ".lnk"), StandardCopyOption.REPLACE_EXISTING);
        status.setText("Status: Start Menu Shortcut created");
    }

    public void delete() {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(dir + "\\resources\\profiles\\" + profile.getProfileTitle() + ".json"));
        files.add(new File(profile.getGameIcon()));
        files.add(new File(profile.getBatch()));
        files.add(new File(profile.getShortcut()));
        files.add(new File(profile.getProfileIcon()));
        files.add(new File(home + "\\AppData\\Roaming\\Microsoft\\Windows\\" + "Start Menu\\Programs\\" +
                profile.getProfileTitle() + ".lnk"));
        files.add(new File(home + "\\Desktop\\" + profile.getProfileTitle() + ".lnk"));

        for (File file : files) {
            if (file.delete()) {
                status.setText("Status: " + profile.getProfileTitle() + " Deleted");
            }
        }

        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i) == profile) {
                profiles.remove(profile);

                if (ProfileProperties.getX() <= 3) {
                    ProfileProperties.setX(ProfileProperties.getX() - 1);
                } else if (ProfileProperties.getX() == 4) {
                    ProfileProperties.setX(ProfileProperties.getX() - 1);
                    ProfileProperties.setY(0);
                }
            }
        }

        for (int i = 0; i < profileInterfaces.size(); i++) {
            profileWindow.getChildren().remove(profileInterfaces.get(i));
        }

        profileInterfaces.clear();
        draw();
    }

    public static void interact(JFXButton button) {
        button.setOnMouseClicked(e -> {
            for (int i = 0; i < getProfileInterfaces().size(); i++) {
                if (getProfileInterfaces().get(i) == button) {
                    Profile profile = getProfiles().get(i);
                    ProfileHandler loader = new ProfileHandler(profile, ProfileProperties.getPanes().get(i));

                    if (e.getButton() == MouseButton.PRIMARY) {
                        try {
                            loader.load();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        ContextMenu menu = new ContextMenu();
                        AnchorPane pane = new AnchorPane();

                        MenuItem load = new MenuItem("Load Profile");
                        MenuItem desktop = new MenuItem("Create Desktop Shortcut");
                        MenuItem startMenu = new MenuItem("Create StartMenu Shortcut");
                        MenuItem delete = new MenuItem("Delete Profile");

                        menu.getItems().addAll(load, desktop, startMenu, delete);
                        button.setContextMenu(menu);
                        menu.show(pane, e.getScreenX(), e.getScreenY());

                        load.setOnAction(actionEvent -> {
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });

                        desktop.setOnAction(actionEvent -> {
                            try {
                                loader.createDesktop();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });

                        startMenu.setOnAction(actionEvent -> {
                            try {
                                loader.createStartMenu();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });

                        delete.setOnAction(actionEvent -> loader.delete());
                    }
                }
            }
        });
    }

    public static void setProfileWindow(AnchorPane profileWindow) {
        ProfileHandler.profileWindow = profileWindow;
    }

    public static AnchorPane getProfileWindow() {
        return profileWindow;
    }

    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public static ArrayList<JFXButton> getProfileInterfaces() {
        return profileInterfaces;
    }

}

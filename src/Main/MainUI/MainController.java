package Main.MainUI;

import Main.Config;
import Main.Profile.Profile;
import Main.Profile.ProfileHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.jimmc.jshortcut.JShellLink;

public class MainController implements Initializable {

    @FXML
    private AnchorPane intro;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane top;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private AnchorPane createPane;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private AnchorPane helpPane;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton settings;
    @FXML
    private JFXButton help;
    @FXML
    private ImageView settingsIcon;
    @FXML
    private ImageView helpIcon;
    @FXML
    private ImageView addIcon;
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField steamEXE;
    @FXML
    private TextField tcno;
    @FXML
    private TextField resolutionSwitcher;
    @FXML
    private ImageView steamEXEIcon;
    @FXML
    private ImageView resolutionSwitcherIcon;
    @FXML
    private ImageView tcnoIcon;
    @FXML
    private JFXButton steamEXEFolder;
    @FXML
    private ImageView steamEXEFolderIcon;
    @FXML
    private JFXButton resolutionSwitcherFolder;
    @FXML
    private ImageView resolutionSwitcherFolderIcon;
    @FXML
    private JFXButton tcnoFolder;
    @FXML
    private ImageView tcnoFolderIcon;
    @FXML
    private TextField title;
    @FXML
    private TextField steam;
    @FXML
    private TextField resolution;
    @FXML
    private TextField game;
    @FXML
    private ImageView titleIcon;
    @FXML
    private ImageView steamIcon;
    @FXML
    private ImageView resolutionIcon;
    @FXML
    private ImageView gameIcon;
    @FXML
    private JFXButton steamFolder;
    @FXML
    private ImageView steamFolderIcon;
    @FXML
    private JFXButton resolutionFolder;
    @FXML
    private ImageView resolutionFolderIcon;
    @FXML
    private JFXButton minimize;
    @FXML
    private ImageView minimizeIcon;
    @FXML
    private JFXButton close;
    @FXML
    private ImageView closeIcon;
    @FXML
    private ComboBox<String> language;
    @FXML
    private JFXCheckBox startup;
    @FXML
    private JFXButton save;
    @FXML
    private ImageView YT;
    @FXML
    private ImageView resGH;
    @FXML
    private ImageView tcnoGH;
    @FXML
    private ImageView steamDB;
    @FXML
    private Text YTTutorial;
    @FXML
    private Text resRepo;
    @FXML
    private Text tcnoRepo;
    @FXML
    private Text steamDBLink;
    @FXML
    private Text status;
    @FXML
    private JFXButton dirEXE;
    @FXML
    private JFXButton dirTcno;
    @FXML
    private JFXButton dirRes;
    @FXML
    private ImageView dirEXEIcon;
    @FXML
    private ImageView dirTcnoIcon;
    @FXML
    private ImageView dirResIcon;
    @FXML
    private TextField introEXE;
    @FXML
    private ImageView introSteam;
    @FXML
    private Text issues;
    @FXML
    private JFXButton introFolder;
    @FXML
    private ImageView introFolderIcon;
    @FXML
    private JFXCheckBox noGame;
    @FXML
    private JFXCheckBox noRes;
    @FXML
    private JFXCheckBox noAccount;

    private final Desktop desktop = Desktop.getDesktop();
    private Config config;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadConfig();
        } catch (IOException e) {
            status.setText("Status: Couldn't load config");
        }

        if (config.getFirstTime()) {
            intro.toFront();
            disableButtons(true);

            FileChooser exe = new FileChooser();
            exe.getExtensionFilters().add(new FileChooser.ExtensionFilter("Steam Executable", "*.exe"));

            introFolder.setOnAction(e -> {
                File file = exe.showOpenDialog(profilePane.getScene().getWindow());

                if (file != null) {
                    if (file.getName().equals("steam.exe")) {
                        introEXE.setText(file.getAbsolutePath());
                        config.setSteamEXE(file.getAbsolutePath());
                        config.setFirstTime(false);
                        ProfileHandler.steamEXE = config.getSteamEXE();
                        updateConfig();
                        disableButtons(false);
                        profilePane.toFront();
                        status.setText("Status: Set steam.exe location");
                    } else {
                        status.setText("Status: Not steam.exe");
                    }
                }
            });
        } else {
            profilePane.toFront();
            ProfileHandler.steamEXE = config.getSteamEXE();
        }

        ProfileHandler.status = status;

        String dir = System.getProperty("user.dir") + "\\resources\\images\\";
        YT.setImage(new Image(new java.io.File(dir + "youtube.png").toURI().toString()));
        resGH.setImage(new Image(new java.io.File(dir + "github.png").toURI().toString()));
        tcnoGH.setImage(new Image(new java.io.File(dir + "github.png").toURI().toString()));
        steamDB.setImage(new Image(new java.io.File(dir + "steamDB.png").toURI().toString()));
        homeIcon.setImage(new Image(new java.io.File(dir + "home.png").toURI().toString()));
        addIcon.setImage(new Image(new java.io.File(dir + "add.png").toURI().toString()));
        settingsIcon.setImage(new Image(new java.io.File(dir + "settings.png").toURI().toString()));
        helpIcon.setImage(new Image(new java.io.File(dir + "help.png").toURI().toString()));
        steamEXEIcon.setImage(new Image(new java.io.File(dir + "steam.png").toURI().toString()));
        tcnoIcon.setImage(new Image(new java.io.File(dir + "tcno.png").toURI().toString()));
        resolutionSwitcherIcon.setImage(new Image(new java.io.File(dir + "res-switcher.png").toURI().toString()));
        steamEXEFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        tcnoFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        resolutionSwitcherFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        titleIcon.setImage(new Image(new java.io.File(dir + "title.png").toURI().toString()));
        steamIcon.setImage(new Image(new java.io.File(dir + "steam.png").toURI().toString()));
        resolutionIcon.setImage(new Image(new java.io.File(dir + "resolution.png").toURI().toString()));
        gameIcon.setImage(new Image(new java.io.File(dir + "game.png").toURI().toString()));
        steamFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        resolutionFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        dirEXEIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        dirTcnoIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        dirResIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        introFolderIcon.setImage(new Image(new java.io.File(dir + "folder.png").toURI().toString()));
        introSteam.setImage(new Image(new java.io.File(dir + "steam.png").toURI().toString()));
        minimizeIcon.setImage(new Image(new java.io.File(dir + "minimize.png").toURI().toString()));
        closeIcon.setImage(new Image(new java.io.File(dir + "close.png").toURI().toString()));

        home.setCursor(Cursor.HAND);
        add.setCursor(Cursor.HAND);
        settings.setCursor(Cursor.HAND);
        help.setCursor(Cursor.HAND);
        YTTutorial.setCursor(Cursor.HAND);
        resRepo.setCursor(Cursor.HAND);
        tcnoRepo.setCursor(Cursor.HAND);
        steamDBLink.setCursor(Cursor.HAND);
        minimize.setCursor(Cursor.HAND);
        close.setCursor(Cursor.HAND);
        language.setCursor(Cursor.HAND);
        startup.setCursor(Cursor.HAND);
        tcnoFolder.setCursor(Cursor.HAND);
        steamEXEFolder.setCursor(Cursor.HAND);
        resolutionFolder.setCursor(Cursor.HAND);
        resolutionSwitcherFolder.setCursor(Cursor.HAND);
        dirEXE.setCursor(Cursor.HAND);
        dirTcno.setCursor(Cursor.HAND);
        dirRes.setCursor(Cursor.HAND);
        steamFolder.setCursor(Cursor.HAND);
        introFolder.setCursor(Cursor.HAND);
        issues.setCursor(Cursor.HAND);
        save.setCursor(Cursor.HAND);
        noAccount.setCursor(Cursor.HAND);
        noRes.setCursor(Cursor.HAND);
        noGame.setCursor(Cursor.HAND);

        noOptionListeners();

        home.setOnAction(e -> profilePane.toFront());

        add.setOnAction(e -> {
            title.clear();
            steam.clear();
            resolution.clear();
            game.clear();
            noAccount.setSelected(false);
            noGame.setSelected(false);
            noRes.setSelected(false);
            createPane.toFront();
        });

        settings.setOnAction(e -> settingsPane.toFront());

        help.setOnAction(e -> helpPane.toFront());

        YTTutorial.setOnMouseClicked(e -> {
            try {
                desktop.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"));
                status.setText("Status: Loaded Tutorial");
            } catch (IOException | URISyntaxException ex) {
                status.setText("Status: Couldn't load youtube video");
            }
        });

        resRepo.setOnMouseClicked(e -> {
            try {
                desktop.browse(new URI("https://github.com/Stryzhh/resolution-switcher"));
                status.setText("Status: Loaded repository");
            } catch (IOException | URISyntaxException ex) {
                status.setText("Status: Couldn't load repository");
            }
        });

        tcnoRepo.setOnMouseClicked(e -> {
            try {
                desktop.browse(new URI("https://github.com/TcNobo/TcNo-Acc-Switcher"));
                status.setText("Status: Loaded repository");
            } catch (IOException | URISyntaxException ex) {
                status.setText("Status: Couldn't load repository");
            }
        });

        steamDBLink.setOnMouseClicked(e -> {
            try {
                desktop.browse(new URI("https://steamdb.info/apps/"));
                status.setText("Status: Loaded steamDB");
            } catch (IOException | URISyntaxException ex) {
                status.setText("Status: Couldn't load steamDB");
            }
        });

        issues.setOnMouseClicked(e -> {
            try {
                desktop.browse(new URI("https://www.youtube.com/watch?v=6_a_yU2nc3Y&ab_channel=GameSkiller"));
                status.setText("Status: Loaded steam.exe tutorial");
            } catch (IOException | URISyntaxException ex) {
                status.setText("Status: Couldn't load steam.exe tutorial");
            }
        });

        dirEXE.setOnAction(e -> {
            try {
                Desktop.getDesktop().open(new File(new File(config.getSteamEXE()).getParent()));
            } catch (IOException ex) {
                status.setText("Status: No Folder Specified");
            }
        });

        dirTcno.setOnAction(e -> {
            File file = new File(config.getAccount());

            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    status.setText("Status: No Folder Specified");
                }
            }
        });

        dirRes.setOnAction(e -> {
            File file = new File(config.getResolution());

            if (file.exists()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    status.setText("Status: No Folder Specified");
                }
            }
        });

        tcno.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                ContextMenu menu = new ContextMenu();
                AnchorPane pane = new AnchorPane();

                javafx.scene.control.MenuItem delete = new javafx.scene.control.MenuItem("Delete Directory");

                menu.getItems().addAll(delete);
                tcno.setContextMenu(menu);
                menu.show(pane, e.getScreenX(), e.getScreenY());

                delete.setOnAction(ev -> {
                    config.setAccount("");
                    tcno.setText("");
                    updateConfig();
                    status.setText("Status: Deleted Directory");
                });
            }
        });

        resolutionSwitcher.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                ContextMenu menu = new ContextMenu();
                AnchorPane pane = new AnchorPane();

                javafx.scene.control.MenuItem delete = new javafx.scene.control.MenuItem("Delete Directory");

                menu.getItems().addAll(delete);
                resolutionSwitcher.setContextMenu(menu);
                menu.show(pane, e.getScreenX(), e.getScreenY());

                delete.setOnAction(ev -> {
                    config.setResolution("");
                    resolutionSwitcher.setText("");
                    updateConfig();
                    status.setText("Status: Deleted Directory");
                });
            }
        });

        createListeners();
        settingsListeners();

        for (File file : Objects.requireNonNull(new File("resources\\profiles").listFiles())) {
            try {
                ProfileHandler.getProfiles().add(new Gson().fromJson(Files.readString(Path.of(file.getPath())), Profile.class));
            } catch (IOException e) {
                status.setText("Status: Couldn't load some profiles");
            }
        }

        ProfileHandler.setProfileWindow(profilePane);
        new ProfileHandler().draw();

        main.setOnMousePressed(pressEvent -> top.setOnMouseDragged(dragEvent -> {
            main.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            main.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));

        minimize.setOnAction(e -> {
            Stage stage = (Stage) profilePane.getScene().getWindow();
            stage.setIconified(true);
        });

        close.setOnAction(e -> System.exit(1));

    }

    private void createListeners() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Executable Files",
                "*.exe", "*.lnk", "*.url"));

        steamFolder.setOnAction(e -> {
            if (new File(config.getAccount()).exists()) {
                chooser.setInitialDirectory(new File(config.getAccount()));
            } else {
                chooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File file = chooser.showOpenDialog(profilePane.getScene().getWindow());

            if (file != null) {
                steam.setText(file.getAbsolutePath());
                noAccount.setSelected(false);
            }
        });

        resolutionFolder.setOnAction(e -> {
            if (new File(config.getResolution()).exists()) {
                chooser.setInitialDirectory(new File(config.getResolution()));
            } else {
                chooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File file = chooser.showOpenDialog(profilePane.getScene().getWindow());

            if (file != null) {
                resolution.setText(file.getAbsolutePath());
                noRes.setSelected(false);
            }
        });

        game.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                ContextMenu menu = new ContextMenu();
                AnchorPane pane = new AnchorPane();

                javafx.scene.control.MenuItem paste = new javafx.scene.control.MenuItem("Paste");

                menu.getItems().addAll(paste);
                game.setContextMenu(menu);
                menu.show(pane, e.getScreenX(), e.getScreenY());

                paste.setOnAction(ev -> {
                    Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable t = c.getContents(this);

                    if (t != null) {
                        try {
                            game.setText((String) t.getTransferData(DataFlavor.stringFlavor));
                            noGame.setSelected(false);
                            status.setText("Status: Pasted Game ID");
                        } catch (Exception ex){
                            status.setText("Status: Couldn't paste");
                        }
                    }
                });
            }
        });

        save.setOnAction(e -> {
            if (!title.getText().equals("") && !steam.getText().equals("") &&
                    !resolution.getText().equals("") && !game.getText().equals("")) {
                try {
                    saveProfile();
                } catch (IOException ex) {
                    status.setText("Status: Couldn't save profile");
                    ex.printStackTrace();
                }
            } else {
                status.setText("Status: Please fill in all fields");
            }
        });
    }

    private void settingsListeners(){
        FileChooser exe = new FileChooser();
        exe.getExtensionFilters().add(new FileChooser.ExtensionFilter("Steam Executable", "*.exe"));

        steamEXEFolder.setOnAction(e -> {
            File file = exe.showOpenDialog(profilePane.getScene().getWindow());

            if (file != null) {
                if (file.getName().equals("steam.exe")) {
                    steamEXE.setText(file.getAbsolutePath());
                    config.setSteamEXE(file.getAbsolutePath());
                    updateConfig();
                    status.setText("Status: Set steam.exe location");
                }
            }
        });

        tcnoFolder.setOnAction(e -> {
            File file = exe.showOpenDialog(profilePane.getScene().getWindow());

            if (file != null) {
                if (file.getName().equals("TcNo Account Switcher.exe")) {
                    tcno.setText(file.getParent() + "\\");
                    config.setAccount(file.getParent() + "\\");
                    updateConfig();
                    status.setText("Status: Set TcNo Account Switcher.exe location");
                }
            }
        });

        resolutionSwitcherFolder.setOnAction(e -> {
            File file = exe.showOpenDialog(profilePane.getScene().getWindow());

            if (file != null) {
                if (file.getName().equals("resolution-switcher.exe")) {
                    resolutionSwitcher.setText(file.getParent() + "\\");
                    config.setResolution(file.getParent() + "\\");
                    updateConfig();
                    status.setText("Status: Set resolution-switcher.exe location");
                }
            }
        });

        startup.setOnAction(e -> {
            try {
                handleStartup();
            } catch (IOException ex) {
                status.setText("Status: Couldn't create startup");
            }
        });
    }

    private void handleStartup() throws IOException {
        File folder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft" +
                "\\Windows\\Start Menu\\Programs\\Startup");
        File batch = new File("account-resolution-game-switcher.bat");

        if (startup.isSelected()) {
            FileOutputStream output = new FileOutputStream(batch);
            DataOutputStream input = new DataOutputStream(output);
            input.writeBytes(System.getProperty("user.dir") + "\\account-resolution-game-switcher.exe");
            input.close();
            output.close();

            JShellLink link = new JShellLink();
            String filePath = JShellLink.getDirectory("") + batch.getAbsolutePath();
            link.setFolder(folder.getAbsolutePath());
            link.setName(batch.getName());
            link.setPath(filePath);
            link.save();

            status.setText("Status: Program successfully added to startup list");
        } else {
            File startupFile = new File(System.getProperty("user.home") +
                    "/AppData/Roaming/Microsoft/Windows/Start Menu/Programs/Startup/account-resolution-game-switcher.bat.lnk");
            if (!startupFile.delete() || !batch.delete()) {
                status.setText("Status: Something went wrong :/");
            }
        }

        config.setStartup(startup.isSelected());
        updateConfig();
    }

    private void loadConfig() throws IOException {
        config = new Gson().fromJson(Files.readString(Path.of(System.getProperty("user.dir") +
                "\\resources\\config.json")), Config.class);

        language.setValue(config.getLanguage());
        startup.setSelected(config.getStartup());
        steamEXE.setText(config.getSteamEXE());
        tcno.setText(config.getAccount());
        resolutionSwitcher.setText(config.getResolution());

        status.setText("Status: Config Loaded");
    }

    private void disableButtons(boolean value) {
        add.setDisable(value);
        home.setDisable(value);
        settings.setDisable(value);
        help.setDisable(value);
    }

    private void updateConfig() {
        try {
            FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\resources\\config.json");
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            writer.write(g.toJson(config));
            writer.close();
        } catch (Exception ex) {
            status.setText("Status: Couldn't update config");
        }
    }

    private void noOptionListeners() {
        noAccount.setOnAction(e -> {
            if (noAccount.isSelected()) {
                steam.setText(System.getProperty("user.dir") + "\\resources\\shortcuts\\account\\no-account-change.lnk");
            } else {
                steam.setText("");
            }
        });
        noRes.setOnAction(e -> {
            if (noRes.isSelected()) {
                resolution.setText(System.getProperty("user.dir") + "\\resources\\shortcuts\\resolution\\no-res-change.lnk");
            } else {
                resolution.setText("");
            }
        });
        noGame.setOnAction(e -> {
            if (noGame.isSelected()) {
                game.setText("0");
            } else {
                game.setText("");
            }
        });
    }

    private void saveProfile() throws IOException {
        ProfileHandler handler = new ProfileHandler(title.getText().replaceAll("\\s+",""), steam.getText(),
                resolution.getText(), game.getText());
        handler.create();
        profilePane.toFront();
        noAccount.setSelected(false);
        noRes.setSelected(false);
        noGame.setSelected(false);
        status.setText("Status: Profile Saved");
    }

}

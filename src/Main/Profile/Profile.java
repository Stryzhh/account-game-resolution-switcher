package Main.Profile;

public class Profile {

    private String profileIcon;
    private final String profileTitle;
    private final String steamLNK;
    private final String resolutionBATCH;
    private final String gameID;
    private String gameIcon;
    private String batch;
    private String shortcut;

    public Profile(String profileIcon, String profileTitle, String steamCommand, String resolutionCommand,
                   String gameID, String gameIcon, String batch, String shortcut) {
        this.profileIcon = profileIcon;
        this.profileTitle = profileTitle;
        this.steamLNK = steamCommand;
        this.resolutionBATCH = resolutionCommand;
        this.gameID = gameID;
        this.gameIcon = gameIcon;
        this.batch = batch;
        this.shortcut = shortcut;
    }

    public String getProfileTitle() {
        return profileTitle;
    }

    public String getSteamLNK() {
        return steamLNK;
    }

    public String getResolutionBATCH() {
        return resolutionBATCH;
    }

    public String getGameID() {
        return gameID;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut + ".lnk";
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

}

package Main;

public class Config {

    private boolean firstTime;
    private String language;
    private boolean startup;
    private  String steam;
    private String account;
    private String resolution;

    public Config(boolean firstTime, String language, boolean startup, String steamEXE, String account_switcher, String resolution_switcher) {
        this.firstTime = firstTime;
        this.language = language;
        this.startup = startup;
        this.steam = steamEXE;
        this.account = account_switcher;
        this.resolution = resolution_switcher;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public boolean getFirstTime() {
        return firstTime;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setStartup(boolean startup) {
        this.startup = startup;
    }

    public boolean getStartup() {
        return startup;
    }

    public void setSteamEXE(String steamEXE) {
        steam = steamEXE;
    }

    public String getSteamEXE() {
        return steam;
    }

    public void setAccount(String account_switcher) {
        this.account = account_switcher;
    }

    public String getAccount() {
        return account;
    }

    public void setResolution(String resolution_switcher) {
        this.resolution = resolution_switcher;
    }

    public String getResolution() {
        return resolution;
    }

}

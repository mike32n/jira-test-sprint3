
import java.io.IOException;

public class Utils {
    final static String BASE_URL = "https://jira.codecool.codecanvas.hu/";
    final static String WEBDRIVER = "webdriver.chrome.driver";
    final static String CHROME_DRIVER_LOCATION = "/usr/bin/chromedriver";
    final static String PROFILE_PAGE = "https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa";
    final static String ALTERNATIVE_LOGIN_PAGE = "https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa";
    static String username;
    static String password;

    static {
        try {
            username = ReadLoginProperties.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            password = ReadLoginProperties.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


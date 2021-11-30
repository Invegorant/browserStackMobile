package config.setup;

import com.codeborne.selenide.Configuration;
import config.drivers.BrowserstackMobileDriver;
import config.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.helpers.Attach.getSessionId;

public class TestBase {

    public static String userName = null,
            mobileKey = null,
            appURL = null;

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        initVars();
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    private static void initVars() {
        BrowserStackConfig credentials =
                ConfigFactory.create(BrowserStackConfig.class);

        userName = credentials.userName();
        mobileKey = credentials.mobileKey();
        appURL = credentials.appURL();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
//        Attach.browserConsoleLogs();

        closeWebDriver();

        Attach.attachVideo(sessionId);
    }
}

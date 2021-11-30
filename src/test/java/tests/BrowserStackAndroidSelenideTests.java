package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.setup.TestBase;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


@Tag("selenide_android")
public class BrowserStackAndroidSelenideTests extends TestBase {

    /*** LOCATORS AND TEST DATA ***/
    private final ElementsCollection SEARCH_RESULTS = $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    private final SelenideElement SEARCH_WIKIPEDIA_TAB = $(MobileBy.AccessibilityId("Search Wikipedia")),
            SEARCH_FIELD = $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")),
            WIKIPEDIA_PAGE = $(MobileBy.id("org.wikipedia.alpha:id/view_page_title_text"));

    private final String github = "Github";


    @Test
    @Feature("Wikipedia Search")
    @Description("Successful search in wikipedia android app")
    void searchTest() {
        step("Type search", () -> {
            SEARCH_WIKIPEDIA_TAB.click();
            SEARCH_FIELD.val("BrowserStack");
        });
        step("Verify content found", () -> {
            SEARCH_RESULTS.shouldHave(sizeGreaterThan(0));
        });
    }


    @Test
    @Feature("Wikipedia Search")
    @Description("Search of Selenide Github")
    void searchSelenideGithub() {
        step("Type Selenide Github in search field", () -> {
            SEARCH_WIKIPEDIA_TAB.click();
            SEARCH_FIELD.val(github);
        });
        step("Find and open Github", () -> {
            SEARCH_RESULTS.findBy(text(github)).shouldBe(visible, Duration.ofSeconds(30)).click();
        });
        step("Check correct Github page was opened", () -> {
            WIKIPEDIA_PAGE.shouldHave(text(github), Duration.ofSeconds(30));
        });
    }
}
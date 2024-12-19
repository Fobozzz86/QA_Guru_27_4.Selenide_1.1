package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void shouldFindSelenideRepositorySoftAssertions() {
//        Разработайте следующий автотест:
//        - Откройте страницу Selenide в Github
//        - Перейдите в раздел Wiki проекта
//        - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
//        - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        open("/selenide/selenide/");
        $("#wiki-tab").click();
        $("#wiki-body").$$("li").shouldHave(itemWithText("Soft assertions"));
        $(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));

    }
}

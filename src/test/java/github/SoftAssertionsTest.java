package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void shouldFindSelenideRepositorySoftAssertions() {
//        Разработайте следующий автотест:
//        - Откройте страницу Selenide в Github
//        - Перейдите в раздел Wiki проекта
//        - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
//        - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        open("https://github.com/");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div.Box-sc-g0xbh4-0.flszRz").first().$(byText("selenide")).click();
        $(byText("Wiki")).click();
        $("#wiki-body").shouldHave(text("Soft assertions"));
        $(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));

        // более короткий Тест, но без шагов прохода до раздела Wiki
        open("https://github.com/selenide/selenide/wiki");
        $(".markdown-body").shouldHave(text("Soft assertions"));
        $(byText("Soft assertions")).click(); // byText ищет элемент по тексту
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));

    }
}

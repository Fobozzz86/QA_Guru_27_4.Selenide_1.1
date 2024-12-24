package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SelenideRepositorySearchTest {

  @BeforeAll
  static void beforeAll() {
    Configuration.pageLoadStrategy = "eager";
  }

  @Test
  void shouldFindSelenideRepositoryAtTheTop() {

    // Открыть Github.com
    open("https://github.com/");
    // Открыть страницу Selenide в Github
    $("[placeholder='Search or jump to...']").click();
    $("#query-builder-test").setValue("selenide_1").pressEnter();

    // Перейти в раздел Wiki проекта
    // нашли элемент блока результата поиска и кликнули на первый элемент в поиске
    $$("div.Box-sc-g0xbh4-0.flszRz").first().$(byText("selenide_1")).click(); // в элементе должно быть сразу 2 class
    // $$("div [class='Box-sc-g0xbh4-0 flszRz']").first().$(byText("selenide")).click(); тоже верный локатор
    // $$("div.Box-sc-g0xbh4-0.flszRz").first().$("a").click(); тоже верный локатор

    // Убедиться, что перешли на страницу selenide / selenide
    $("#repository-container-header").shouldHave(text("selenide / selenide")); // "selenide\n/\nselenide"

    sleep(5000); // тоже тайм-аут чтобы не сразу закрылся браузер

    // ARRANGE - Подготовить
    // ACT - Действие
    // ACT
    // (ASSERT) - Проверка
    // ACT
    // ASSERT
  }
}

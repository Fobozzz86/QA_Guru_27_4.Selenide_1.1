package github.homework;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckSolutionsWithHoverTest {

    /* 1. На главной странице GitHub выберите меню Solutions -> Enterprize с помощью команды hover для Solutions.
     Убедитесь что загрузилась нужная страница (например что заголовок - "Build like the best."
     */
    @Test
    void hoverSolutionsTest() {
        open("https://github.com/");
        $(byTagAndText("button", "Solutions")).hover();
        $(byTagAndText("a", "Enterprise")).click();
        //        $(withText("Build like the best")).shouldBe(visible); // элемент с текстом "The AI-powered developer platform" видимый
        $(".application-main").shouldHave(text("The AI-powered developer platform"));
    }
    }
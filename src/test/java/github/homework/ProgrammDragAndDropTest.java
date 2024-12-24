package github.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class ProgrammDragAndDropTest {

    private final String URL = "https://the-internet.herokuapp.com/drag_and_drop";
    private SelenideElement columnA = $(byId("column-a"));
    private SelenideElement columnB = $(byId("column-b"));
    private SelenideElement headerA = $(byTagAndText("header", "A"));
    private SelenideElement headerB = $(byTagAndText("header", "B"));


    @Test
    void CheckDragAndDrop() {  // самый простой тест
        open("https://the-internet.herokuapp.com/drag_and_drop");
        //       $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));
        $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));

        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-b").hover();
        actions().clickAndHold().moveByOffset(-200, 0).release().perform();
        $("#column-a").shouldHave(exactText("B"));
    }

    @Test
    public void dragDropTest() { // более замороченный тест, но интересный
        open(URL);
        columnB.shouldHave(text(headerB.getText()));
        columnA.dragAndDrop(DragAndDropOptions.to(columnB));
        columnB.shouldHave(text(headerA.getText()));

    }
}
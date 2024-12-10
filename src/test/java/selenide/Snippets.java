package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common.....
public class Snippets {

  void browser_command_examples() {
    open("https://google.com");
    open("/customer/orders"); // удобно так делать на проектах, где есть разные среды Test, Prod и тд.
    // -Dselenide.baseUrl=http://123.23.23.1
    open("/", AuthenticationType.BASIC,
            new BasicAuthCredentials("", "user", "password"));

    Selenide.back(); // кнопка "Назад"
    Selenide.refresh(); // кнопка "Перезагрузить"

    Selenide.clearBrowserCookies(); // Очистка Cookies
    Selenide.clearBrowserLocalStorage(); // Очистка Local Storage
    executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet

    Selenide.confirm(); // OK in alert dialogs // Подтверждение во всплывающих окнах
    Selenide.dismiss(); // Cancel in alert dialogs // Отмена во всплывающих окнах

    Selenide.closeWindow(); // close active tab
    Selenide.closeWebDriver(); // close browser completely

    Selenide.switchTo().frame("new"); // Переход во фрейм по имени или селктору
    Selenide.switchTo().defaultContent(); // return from frame back to the main DOM  // Переход во фрейм по умолчанию

    Selenide.switchTo().window("The Internet");

    var cookie = new Cookie("foo", "bar");
    WebDriverRunner.getWebDriver().manage().addCookie(cookie);


  }

  void selectors_examples() {
    $("div").click();
    element("div").click();

    $("div", 2).click();
    // Если нужен не первый n-ый div, то можно указать его индекс
    // Важно помнить, что нумерация начинается с нуля
    // В примере найдется третий div

    $x("//h1/div").click();
    $(byXpath("//h1/div")).click();

    $(byText("full text")).click(); // Поиск по полной строке
    $(withText("ull tex")).click(); // Поиск по подстроке

    // Поиск по тегу и тексту одновременно
    $(byTagAndText("div", "full text"));
    $(withTagAndText("div", "ull text"));

    $("").parent(); // По родителю
    $("").sibling(1); // Поиск по дочерним элементам (сверху вниз)
    $("").preceding(1); // То же, что и sibling, но снизу вверх
    $("").closest("div"); // Ищет предков элемента снизу вверх
    $("").ancestor("div"); // То же, что и closest // the same as closest
    $("div:last-child"); // Поиск по псевдоселекторам

    $("div").$("h1").find(byText("abc")).click();
    // very optional
    $(byAttribute("abc", "x")).click();
    $("[abc=x]").click();

    $(byId("mytext")).click();
    $("#mytext").click();

    $(byClassName("red")).click();
    $(".red").click();
  }

  void actions_examples() {
    $("").click();
    $("").doubleClick();
    $("").contextClick();

    $("").hover();

    $("").setValue("text");
    $("").append("text");
    $("").clear();
    $("").setValue(""); // clear

    $("div").sendKeys("c"); // hotkey c on element
    actions().sendKeys("c").perform(); //hotkey c on whole application
    actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
    $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

    $("").pressEnter();
    $("").pressEscape();
    $("").pressTab();


    // complex actions with keybord and mouse, example
    actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

    // old html actions don't work with many modern frameworks
    $("").selectOption("dropdown_option");
    $("").selectRadio("radio_options");

  }

  void assertions_examples() {
    $("").shouldBe(visible);
    $("").shouldNotBe(visible);
    $("").shouldHave(text("abc"));
    $("").shouldNotHave(text("abc"));
    $("").should(appear);
    $("").shouldNot(appear);


    //longer timeouts
    $("").shouldBe(visible, Duration.ofSeconds(30));

  }

  void conditions_examples() {
    $("").shouldBe(visible);
    $("").shouldBe(hidden);

    $("").shouldHave(text("abc"));
    $("").shouldHave(exactText("abc"));
    $("").shouldHave(textCaseSensitive("abc"));
    $("").shouldHave(exactTextCaseSensitive("abc"));
    $("").should(matchText("[0-9]abc$"));

    $("").shouldHave(cssClass("red"));
    $("").shouldHave(cssValue("font-size", "12"));

    $("").shouldHave(value("25"));
    $("").shouldHave(exactValue("25"));
    $("").shouldBe(empty);

    $("").shouldHave(attribute("disabled"));
    $("").shouldHave(attribute("name", "example"));
    $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

    $("").shouldBe(checked); // for checkboxes

    // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
    $("").should(exist);

    // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
    $("").shouldBe(disabled);
    $("").shouldBe(enabled);
  }

  void collections_examples() {

    $$("div"); // does nothing!

    $$x("//div"); // by XPath

    // selections
    $$("div").filterBy(text("123")).shouldHave(size(1));
    $$("div").excludeWith(text("123")).shouldHave(size(1));

    $$("div").first().click();
    elements("div").first().click();
    // $("div").click();
    $$("div").last().click();
    $$("div").get(1).click(); // the second! (start with 0)
    $("div", 1).click(); // same as previous
    $$("div").findBy(text("123")).click(); //  finds first

    // assertions
    $$("").shouldHave(size(0));
    $$("").shouldBe(CollectionCondition.empty); // the same

    $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
    $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

    $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
    $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

    $$("").shouldHave(itemWithText("Gamma")); // only one text

    $$("").shouldHave(sizeGreaterThan(0));
    $$("").shouldHave(sizeGreaterThanOrEqual(1));
    $$("").shouldHave(sizeLessThan(3));
    $$("").shouldHave(sizeLessThanOrEqual(2));


  }

  void file_operation_examples() throws FileNotFoundException {

    File file1 = $("a.fileLink").download(); // only for <a href=".."> links
    File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

    File file = new File("src/test/resources/readme.txt");
    $("#file-upload").uploadFile(file);
    $("#file-upload").uploadFromClasspath("readme.txt");
    // don't forget to submit!
    $("uploadButton").click();
  }

  void javascript_examples() {
    executeJavaScript("alert('selenide')");
    executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
    long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

  }
}


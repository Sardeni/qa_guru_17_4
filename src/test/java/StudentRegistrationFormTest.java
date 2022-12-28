import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void studentRegistrationFormTest() {
        String name = "Pavel";
        String lastName = "Emelianov";
        String email = "pavel@gmail.com";
        String phoneNumber = "1234567891";
        String currentAddress = "Tbilsi, 16 Metekhi Street";

        open("/automation-practice-form");
        $(".practice-form-appear").shouldHave(text("Student Registration form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(phoneNumber);
        $("[#dateOfBirthInput]").click();
        $(".class=react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__day--005:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-2").click();
        $("#uploadPicture").uploadFromClasspath("images/brad_pitt.png");
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();

        $(".modal-dialog modal-lg").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text(name + " " + lastName), (text(email)) );
    }
}

package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.RegistrationScreen.*;
import screens.SearchScreen;
import screens.SearchScreen.*;
import screens.SplashScreen;
import utils.UserFactory;


public class RegistrationTests extends TestBase{
    SearchScreen searchScreen;
    RegistrationTests registrationTests;
    User user;
    RegistrationScreen registrationScreen;

    @BeforeMethod
    public void goToRegistrationPositiveTest(){
        new SplashScreen(driver).goToSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        registrationScreen = new RegistrationScreen(driver);
        user = UserFactory.positiveUser();
    }

    @Test
    public void registrationPositiveTest(){
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckbox();
        registrationScreen.clickYalla();
        Assert.assertTrue(searchScreen
                .validatePopUpRegistrationSuccess("Registration success!"));
    }

    @Test
    public void registrationEmptyFieldNameNegativeTest(){
        user.setFirstName("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckbox();
        registrationScreen.clickYalla();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("All fields must be filled and agree terms"));
    }
}

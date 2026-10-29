package mobile_tests;

import dto.Car;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;
import utils.IGetAllCars;

import java.util.List;

import static utils.PropertiesReader.getProperty;

public class DeleteCarTests extends TestBase implements IGetAllCars {
        AddNewCarScreen addNewCarScreen;
        MyCarsScreen myCarsScreen;

        @BeforeMethod
        public void goToLoginScreen() {
            new SplashScreen(driver).goToSearchScreen();
            SearchScreen searchScreen = new SearchScreen(driver);
            searchScreen.clickBtnDots();
            searchScreen.clickBtnLogin();
            LoginScreen loginScreen = new LoginScreen(driver);
            User user = User.builder()
                    .username(getProperty("base.properties", "email"))
                    .password(getProperty("base.properties", "password"))
                    .build();
            loginScreen.typeLoginForm(user);
            loginScreen.clickBtnYallaLogin();
            searchScreen.clickBtnDots();
            searchScreen.clickBtnMyCars();
            myCarsScreen = new MyCarsScreen(driver);
            myCarsScreen.clickBtnAddNewCar();
            addNewCarScreen = new AddNewCarScreen(driver);
            Car car = CarFactory.positiveCar();
            addNewCarScreen.typeAddNewCarForm(car);
            addNewCarScreen.clickBtnAddCar();
        }

        @Test
    public void deletePositiveTest() {
            MyCarsScreen  myCarsScreen = new MyCarsScreen(driver);
            List<String> listBeforeDelete = myCarsScreen.readCarNumberList();
            myCarsScreen.deleteCar();
            myCarsScreen.clickBtnYes();
            List<String> listAfterDelete = myCarsScreen.readCarNumberList();
            Assert.assertNotEquals(listAfterDelete, listBeforeDelete);
        }

        @Test
        public void deletePositiveTestWithCheckCountOfCars() {
            int countBeforeDelete = getCarsCount();
            System.out.println(countBeforeDelete);
            myCarsScreen.deleteCar();
            myCarsScreen.clickBtnYes();
            int countAfterDelete = getCarsCount();
            System.out.println(countAfterDelete);
            Assert.assertNotEquals(countAfterDelete, countBeforeDelete);
        }
}

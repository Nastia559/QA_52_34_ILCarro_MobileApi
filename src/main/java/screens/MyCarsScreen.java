package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement btnAddNewCar;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Car was added!']")
    WebElement messageCarWasAdded;
    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;
    @AndroidFindBy(id = "com.telran.ilcarro:id/myCarSerialTxt")
    List<WebElement> carNumberList;

    public void clickBtnAddNewCar(){
        clickWait(btnAddNewCar);
    }

    public void clickBtnYes(){
        clickWait(btnYes);
    }

    public boolean validateMessageCarWasAdded(String text){
        return isTextInElementPresent(messageCarWasAdded, text);
    }

    public void deleteCar(){
        pause(3000);
        swipe(width/10, height/40*9, width/10*9, height/40*9);
    }

    public List<String> readCarNumberList(){
        List<String> list = new ArrayList<>();
        for (WebElement e: carNumberList){
            list.add(e.getText());
        }
        return list;
    }
}

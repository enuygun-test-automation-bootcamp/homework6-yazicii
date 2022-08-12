import framework.ConfigReader;
import framework.DriverSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.HomePage;

public class FavoritesTest {
    static WebDriver driver;
    HomePage homePage;
    String savedWishTitle = "";

    @BeforeClass
    public void setup(){
        driver = DriverSetup.initialize_Driver(ConfigReader.initialize_Properties().get("browser").toString());
        homePage = new HomePage(driver);
    }

    @Step
    @Description
    @Test(priority = 1)
    public void checkAddToWishListSuccessfully(){
        savedWishTitle = homePage.addItemToWishList();
    }

    @Step
    @Description
    @Test(priority = 2)
    public void checkWishList(){
        homePage.checkWishInFavorList(savedWishTitle);
    }

    //User clicks the 24/7 support button and sends an email at the bottom of the page.
    @Step
    @Description
    @Test(priority = 3)
    public void supportThrowEmail(){
        homePage.checkThrowEmail();
    }

    //User clicks drop down menu and click button 'For man'
    @Step
    @Description
    @Test(priority = 4)
    public void checkDropDownMegaMenuToMenMenu(){
        homePage.dropDownMegaMenuToMenMenu();
    }


}

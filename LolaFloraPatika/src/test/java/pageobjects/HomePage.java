package pageobjects;

import framework.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;
    Helper elementHelper;
    By searchInput = By.id("search-input");
    By searchIcon = By.cssSelector("button[type=submit]");
    By wishListIcon = By.cssSelector("a[data-tooltip=\"Add to Wishlist\"]");
    By imageBox = By.cssSelector(".tt-image-box");
    By favorites = By.cssSelector("div[data-tooltip=\"Favorites\"]");
    By titleOfWish = By.xpath("//*/h2[@class='tt-title']");
    By titleOfResWish = By.xpath("//*[@id=\"products\"]/div[1]/div/div[2]/span");
    By dropDownMegaMenu =By.xpath("//*[text()='HAPPY BIRTHDAY']");
    By menMenu = By.xpath("//*/div/ul/li[1]/a[text()=\"Men\"]");
    By checkMen =By.xpath("//*/a[text()=\" For man\"]");
    By supportElement = By.xpath("//*[contains(text(),'SUPPORT 24/7 ')]");
    By inputEmail= By.xpath("//*/input[@name='email']");
    By checkSupportPage=By.xpath("//*[text()='ANSWERS TO YOUR QUESTIONS']");
    By joginButton =By.xpath("//*/button[text()='JOIN US']");



    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new Helper(driver);
    }

    public String addItemToWishList() {
        this.elementHelper.findElement(searchInput).click();
        this.elementHelper.typeForInput("rose", this.elementHelper.findElement(searchInput));
        this.elementHelper.findElement(searchIcon).click();
        this.elementHelper.hoverMover(this.elementHelper.findElement(imageBox));
        this.elementHelper.findElements(wishListIcon).get(0).click();
        return this.elementHelper.findElements(titleOfResWish).get(0).getText();
    }

    public void checkWishInFavorList(String expectedWish) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-250)");
        this.elementHelper.findElement(favorites).click();
        Assert.assertEquals(expectedWish, this.elementHelper.findElement(titleOfWish).getText());

    }

    //User clicks button 'Support 24/7' and throw the mail
    public void checkThrowEmail() {
        driver.findElement(By.xpath("//*[@id='tt-header']//*/a/img[1][@class='loading']")).click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,4000)");
        this.elementHelper.findElement(supportElement).click();
        Assert.assertEquals(elementHelper.findElement(checkSupportPage).getText(),"ANSWERS TO YOUR QUESTIONS");
        jse.executeScript("window.scrollBy(0,4000)");
        this.elementHelper.findElement(inputEmail).click();
        this.elementHelper.findElement(inputEmail).sendKeys("tester.o1yazicii@gmail.com");
        this.elementHelper.findElement(joginButton).click();

    }

    //User clicks drop down menu and click button 'For man'
    public void dropDownMegaMenuToMenMenu(){
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(dropDownMegaMenu)).perform();
        driver.findElement(menMenu).click();
        Assert.assertEquals(elementHelper.findElement(checkMen).getText(),"For man");

    }



}

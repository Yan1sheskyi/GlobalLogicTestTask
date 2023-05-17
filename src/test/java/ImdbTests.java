import org.imdb.driver.WebDriverCash;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ImdbTests{
    protected static WebDriver driver;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = WebDriverCash.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");
    }

    @Test
    public void descriptionTest() {
        Assertions.assertEquals("IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows", driver.getTitle());
        driver.findElement(By.id("suggestion-search")).sendKeys("Back to the future");
        WebElement searchItem = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='react-autowhatever-navSuggestionSearch--item-0']/a")));
        searchItem.click();
        WebElement filmDescription = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='plot-xl']")));
        Assertions.assertEquals("Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.", filmDescription.getText());
    }

    @AfterAll
    public static void driverQuit() {
        driver.quit();
    }
}

package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import java.net.URL


class PageFactoryFBSignUp {
    var baseUrl = "https://www.facebook.com/"
    private lateinit var driver: WebDriver

    @BeforeTest
    fun launchBrowser(){
        println("Launching a browser")
        //driver = BrowsersList.getRandomBrowser().getDriver()
        val firefoxCapability = FirefoxOptions()
        firefoxCapability.setCapability("se:name", "MyCurrentTest")
        driver = RemoteWebDriver(URL("http://192.168.0.109:4444"), firefoxCapability)
    }

    @Test
    @Throws(InterruptedException::class)
    fun goToRegistrationScreen() {
        println("Opening FB start page")
        driver.get(baseUrl)

        Thread.sleep(2000)

        val startPage = FBStartPage(driver)
        startPage.goToRegistrationScreen()
    }

    @Test(dependsOnMethods = ["goToRegistrationScreen"])
    @Throws(InterruptedException::class)
    fun findAndFillAllElements() {
        val regForm = RegistrationForm(driver)
        regForm.FillElements("asdas@asd.asd", pass = "asd123dsa")
        regForm.checkAppropriateTextInInputField()
        regForm.signUpButtonIsVisibleAndClick()
    }

    @AfterTest
    fun terminateBrowser() {
        Thread.sleep(5000)
        driver.close()
    }




}
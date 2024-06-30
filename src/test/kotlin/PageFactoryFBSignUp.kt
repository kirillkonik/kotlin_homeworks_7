package pages

import BrowsersList
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test


class PageFactoryFBSignUp {
    var baseUrl = "https://www.facebook.com/"
    private lateinit var driver: WebDriver

    @BeforeTest
    fun launchBrowser(){
        println("Launching a browser")
        driver = BrowsersList.getRandomBrowser().getDriver()
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
        regForm.FillElements()
        regForm.checkAppropriateTextInInputField()
        regForm.signUpButtonIsVisibleAndClick()
    }

    @AfterTest
    fun terminateBrowser() {
        driver.close()
    }




}
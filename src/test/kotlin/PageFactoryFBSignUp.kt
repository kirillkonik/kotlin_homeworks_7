package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.net.URL

object TestData {

    @JvmStatic
    @DataProvider(name = "registrationData")
    fun createRegistrationData(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("Joe", "Trump", "Kkkk@gmail.com", "Kkkk@gmail.com", "asd123dsa", "23", "3", "1998"),
        )
    }
}

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


    @Test(dataProvider = "registrationData", dataProviderClass = TestData::class, dependsOnMethods = ["goToRegistrationScreen"])
    @Throws(InterruptedException::class)
    fun findAndFillAllElements(name: String, surname: String, emailOrNumber: String, confirmEmail: String,
                               password: String, birthday: String, birthMonth: String, birthYear: String) {
        val registrationForm = RegistrationForm(driver)

        registrationForm.enterName(name)
        registrationForm.enterSurname(surname)
        registrationForm.enterEmailOrNumber(emailOrNumber)
        registrationForm.enterConfirmEmail(confirmEmail)
        registrationForm.enterPassword(password)
        registrationForm.selectBirthday(birthday)
        registrationForm.selectBirthMonth(birthMonth)
        registrationForm.selectBirthYear(birthYear)
        registrationForm.selectMaleGenderInRadioBtn()
        registrationForm.checkAppropriateTextInInputField()
        registrationForm.signUpButtonIsVisibleAndClick()
    }

    @AfterTest
    fun terminateBrowser() {
        Thread.sleep(5000)
        driver.close()
    }




}
package pages

import BrowserType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class PageFactoryFBSignUp {
    var baseUrl = "https://www.facebook.com/ "
    var drivers = mutableListOf<WebDriver>()


    var name = "Joe"
    var surname = "Trump"
    var emailOrNumber = "Kkkk@gmail.com"
    var confirmEmail = "Kkkk@gmail.com"
    var password = "asd123dsa"
    var birthday = "23"
    var birthMonth = "2"
    var birthYear = "1998"

    private fun getDriver(browser: BrowserType): WebDriver {
        val driver: WebDriver = when(browser) {
            BrowserType.CHROME -> ChromeDriver()
            BrowserType.FF -> FirefoxDriver()

        }
        drivers.add(driver)
        return driver
    }

    @DataProvider(name = "dataProviderDrivers", parallel = true)
    fun getDrivers(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(BrowserType.CHROME),
            arrayOf(BrowserType.FF)
        )
    }


   /* @BeforeTest
    fun launchBrowser(browser: BrowserType) {
        WebDriverManager.firefoxdriver().setup()
        println("Launching a browser")
        val driver = BrowsersList.getRandomBrowser().getDriver()
        //val firefoxCapability = FirefoxOptions()
        //firefoxCapability.setCapability("se:name", "MyCurrentTest")
        //driver = RemoteWebDriver(URL("http://localhost:4444"), firefoxCapability)

        println("Opening FB start page")
        //driver[url]
        Thread.sleep(2000)

    }*/


    @Test(dataProvider = "dataProviderDrivers")
    @Throws(InterruptedException::class)
    fun facebookSignUpTest(browser: BrowserType) {
        //WebDriverManager.firefoxdriver().setup()
        println("Launching a browser")
        val driver = getDriver(browser)
        //val firefoxCapability = FirefoxOptions()
        //firefoxCapability.setCapability("se:name", "MyCurrentTest")
        //driver = RemoteWebDriver(URL("http://localhost:4444"), firefoxCapability)

        println("Opening FB start page")
        driver[baseUrl]

        Thread.sleep(2000)

        val startPage = FBStartPage(driver)
        startPage.goToRegistrationScreen()

        Thread.sleep(2000)

        val registrationForm = RegistrationForm(driver)

        Thread.sleep(2000)

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
        //driver.close()
        for(driver in drivers){
            driver.quit()
        }
    }




}
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.testng.annotations.*

class TestNGAnnotationKotlin {
    var baseUrl = "https://www.facebook.com/"
    lateinit var driver: WebDriver

    @BeforeTest
    fun launchBrowser() {
        println("launching Chrome browser")
        driver = BrowsersList.getRandomBrowser().getDriver()
        driver.get(baseUrl)
    }

    @Test
    @Throws(InterruptedException::class)
    fun goToRegistrationScreen() {
        val signUpBtnOnMainScreen: WebElement = driver.findElement(By.xpath("//*[@data-testid = 'open-registration-form-button']"))
        signUpBtnOnMainScreen.click()
        // Waiting for form rendering
        Thread.sleep(1000)
    }

    @Test(dependsOnMethods = ["goToRegistrationScreen"])
    @Throws(InterruptedException::class)
    fun findAndFillAllElements() {
        val name: WebElement = driver.findElement(By.name("firstname"))
        val surname: WebElement = driver.findElement(By.name("lastname"))
        val emailOrNumber: WebElement = driver.findElement(By.name("reg_email__"))
        val confirmEmail: WebElement = driver.findElement(By.name("reg_email_confirmation__"))
        val password: WebElement = driver.findElement(By.name("reg_passwd__"))
        val birthday: WebElement = driver.findElement(By.name("birthday_day"))
        val birthMonth: WebElement = driver.findElement(By.name("birthday_month"))
        val birthYear: WebElement = driver.findElement(By.name("birthday_year"))
        val maleRadio: WebElement = driver.findElement(By.xpath("//*[@name='sex' and @value ='2']"))

        // Setting up data for sign up
        name.sendKeys("Kkkk")
        surname.sendKeys("Kkkk")
        emailOrNumber.sendKeys("Kkkk@gmail.com")
        Thread.sleep(500)
        confirmEmail.sendKeys("Kkkk@gmail.com")
        password.sendKeys("111qweQWE!@#")

        val birthdayDropdown = Select(birthday)
        birthdayDropdown.selectByValue("23")

        val birthMonthDropdown = Select(birthMonth)
        birthMonthDropdown.selectByIndex(3)

        val birthYearDropdown = Select(birthYear)
        birthYearDropdown.selectByValue("1998")
        maleRadio.click()
    }

    @Test(dependsOnMethods = ["findAndFillAllElements"])
    @Throws(InterruptedException::class)
    fun checkAppropriateTextInInputField() {
        val emailOrNumber: WebElement = driver.findElement(By.name("reg_email__"))
        val confirmEmail: WebElement = driver.findElement(By.name("reg_email_confirmation__"))
        val appropriateTextEmailOrNumber = emailOrNumber.getAttribute("value")
        val appropriateTextConfirmEmail = confirmEmail.getAttribute("value")
        val appropriateText = "Kkkk@gmail.com"
        if (appropriateTextEmailOrNumber == appropriateText && appropriateTextConfirmEmail == appropriateText) {
            println("The appropriate text is in the input field.")
        } else {
            println("The appropriate text is not in the input field.")
        }
    }

    @Test(dependsOnMethods = ["checkAppropriateTextInInputField"])
    @Throws(InterruptedException::class)
    fun signUpButtonIsVisibleAndClick() {
        val signUpButton: WebElement = driver.findElement(By.name("websubmit"))
        if (signUpButton.isDisplayed) {
            signUpButton.click()
            Thread.sleep(5000)
        }
    }

    @AfterTest
    fun terminateBrowser() {
        driver.close()
    }
}

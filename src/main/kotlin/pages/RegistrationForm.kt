package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Select

class RegistrationForm(private val driver: WebDriver) {
    init {
        PageFactory.initElements(driver,this)
    }
        @FindBy(name = "firstname")
        var name: WebElement? = null
        val surname: WebElement = driver.findElement(By.name("lastname"))
        val emailOrNumber: WebElement = driver.findElement(By.name("reg_email__"))
        val confirmEmail: WebElement = driver.findElement(By.name("reg_email_confirmation__"))
        val password: WebElement = driver.findElement(By.name("reg_passwd__"))
        val birthday: WebElement = driver.findElement(By.name("birthday_day"))
        val birthMonth: WebElement = driver.findElement(By.name("birthday_month"))
        val birthYear: WebElement = driver.findElement(By.name("birthday_year"))
        val maleRadio: WebElement = driver.findElement(By.xpath("//*[@name='sex' and @value ='2']"))

    fun FillElements(){
        name?.sendKeys("Kkkk")
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

    fun signUpButtonIsVisibleAndClick() {
        val signUpButton: WebElement = driver.findElement(By.name("websubmit"))
        if (signUpButton.isDisplayed) {
            signUpButton.click()
            Thread.sleep(5000)
        }
    }

}
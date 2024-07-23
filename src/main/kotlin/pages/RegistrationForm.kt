package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Select

class RegistrationForm(private val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(name = "firstname")
    lateinit var name: WebElement

    @FindBy(name = "lastname")
    lateinit var surname: WebElement

    @FindBy(name = "reg_email__")
    lateinit var emailOrNumber: WebElement

    @FindBy(name = "reg_email_confirmation__")
    lateinit var confirmEmail: WebElement

    @FindBy(name = "reg_passwd__")
    lateinit var password: WebElement

    @FindBy(name = "birthday_day")
    lateinit var birthday: WebElement

    @FindBy(name = "birthday_month")
    lateinit var birthMonth: WebElement

    @FindBy(name = "birthday_year")
    lateinit var birthYear: WebElement

    @FindBy(xpath = "//*[@name='sex' and @value ='2']")
    lateinit var maleRadio: WebElement

    @FindBy(name = "websubmit")
    lateinit var signUpButton: WebElement

    fun enterName(name: String) {
        this.name.sendKeys(name)
    }

    fun enterSurname(surname: String) {
        this.surname.sendKeys(surname)
    }

    fun enterEmailOrNumber(emailOrNumber: String) {
        this.emailOrNumber.sendKeys(emailOrNumber)
        Thread.sleep(500)
    }

    fun enterConfirmEmail(confirmEmail: String) {
        this.confirmEmail.sendKeys(confirmEmail)
    }

    fun enterPassword(password: String) {
        this.password.sendKeys(password)
    }

    fun selectBirthday(day: String) {
        val birthdayDropdown = Select(birthday)
        birthdayDropdown.selectByValue(day)
    }

    fun selectBirthMonth(month: String) {
        val birthdayDropdown = Select(birthMonth)
        birthdayDropdown.selectByValue(month)
    }

    fun selectBirthYear(year: String) {
        val birthdayDropdown = Select(birthYear)
        birthdayDropdown.selectByValue(year)
    }

    fun selectMaleGenderInRadioBtn() {
        this.maleRadio.click()
    }



   /* fun fillElements(email: String, pass: String) {
       // name.sendKeys("Kkkk")
       // surname.sendKeys("Kkkk")
       // emailOrNumber.sendKeys(email)
        Thread.sleep(500)
        //confirmEmail.sendKeys("Kkkk@gmail.com")
        //password.sendKeys(pass)

        *//*val birthdayDropdown = Select(birthday)
        birthdayDropdown.selectByValue("23")
        val birthMonthDropdown = Select(birthMonth)
        birthMonthDropdown.selectByIndex(3)
        val birthYearDropdown = Select(birthYear)
        birthYearDropdown.selectByValue("1998") *//*

       // maleRadio.click()
    }*/

    fun checkAppropriateTextInInputField() {
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
        if (signUpButton.isDisplayed) {
            signUpButton.click()
            Thread.sleep(5000)
        }
    }

}
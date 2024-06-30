package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class FBStartPage(driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }
    @FindBy(xpath = "//*[@data-testid = 'open-registration-form-button']")
    val signUpBtnOnMainScreen: WebElement? = null

    @Throws(InterruptedException::class)
    fun goToRegistrationScreen() {
        signUpBtnOnMainScreen!!.click()
        // Waiting for form rendering
        Thread.sleep(1000)
    }
}
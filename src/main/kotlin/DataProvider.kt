import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DataProviderKotlin {
    private val drivers = mutableListOf<WebDriver>()

    enum class BrowserType {
        CHROME, FF
    }

    private fun getDriver(browser: BrowserType): WebDriver {
        val driver: WebDriver = when(browser) {
            BrowserType.CHROME -> ChromeDriver()
            BrowserType.FF -> FirefoxDriver()
        }
        drivers.add(driver)
        return driver
    }

    @DataProvider(name = "dp")
    fun getDrivers(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(BrowserType.CHROME),
            arrayOf(BrowserType.FF)
        )
    }

    @Test(dataProvider = "dp")
    @Throws(InterruptedException::class)
    fun searchTest(browser: BrowserType) {
        val driver = getDriver(browser)
        driver.get("https://www.bing.com/")
        Thread.sleep(2000)

        val textToSearch = "git merge vs rebase"

        val etSearch: WebElement = driver.findElement(By.xpath("//*[@name='q']"))
        etSearch.sendKeys(textToSearch)
        etSearch.submit()
        Thread.sleep(3_000)
        println("Title is ${driver.title}")

        Assert.assertTrue(driver.title.contains(textToSearch), "Oops, wrong title displayed")
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUp() {
//        if(drivers.contains<Any>(BrowserType.CHROME)) {
//            ChromeDriver().quit()
//        }
//        if (drivers.contains<Any>(BrowserType.FF)) {
//            FirefoxDriver().quit()
//        }
        for(driver in drivers){
            driver.quit()
        }
    }


}



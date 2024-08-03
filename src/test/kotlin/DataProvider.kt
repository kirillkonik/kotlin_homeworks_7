
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.net.URL

class DataProviderKotlin {
    private val drivers = mutableListOf<WebDriver>()

    /*private fun getDriver(browser: BrowserType): WebDriver {
        val driver: WebDriver = when(browser) {
            BrowserType.CHROME -> ChromeDriver()
            BrowserType.FF -> FirefoxDriver()

        }
        drivers.add(driver)
        return driver
    }*/

    @DataProvider(name = "dp", parallel = true)
    fun getSearchServices(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("https://www.google.com"),
            arrayOf("https://www.bing.com/"),
            arrayOf("https://duckduckgo.com/")
        )
    }

    @Test(dataProvider = "dp")
    @Throws(InterruptedException::class)
    fun searchTest(url: String) {
        val firefoxCapability = FirefoxOptions()
        firefoxCapability.setCapability("se:name", "SearchTest in $url search service")
        val driver = RemoteWebDriver(URL("http://localhost:4444"), firefoxCapability)

        driver.get(url)
        Thread.sleep(2000)

        val textToSearch = "Selenium"

        val etSearch = driver.findElement(By.xpath("//*[@name='q']"))
        etSearch.sendKeys(textToSearch)
        etSearch.submit()
        Thread.sleep(3_000)
        println("Title is ${driver.title}")

        Assert.assertTrue(driver.title.contains(textToSearch), "Oops, wrong title displayed")

        if(url.contains("bing")) {
            Assert.assertTrue(false, "Planned failure of $url")
        }
        Thread.sleep(3000)

        driver.quit()

    }


}



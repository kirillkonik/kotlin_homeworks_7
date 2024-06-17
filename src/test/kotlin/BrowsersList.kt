import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.*

enum class BrowsersList {
    CHROME {
        override fun getDriver(): WebDriver {
            return ChromeDriver()
        }
    },
    FIREFOX {
        override fun getDriver(): WebDriver {
            return FirefoxDriver()
        }
    }/*,
    CHROME_NEXUS {
        override fun getDriver(): WebDriver {
            val mobileEmulation = mapOf("deviceName" to "Nexus 5")
            val chromeOptions = ChromeOptions()
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation)
            return ChromeDriver(chromeOptions)
        }
    },
    CHROME_IPHONE_14_PRO_MAX {
        override fun getDriver(): WebDriver {
            val mobileEmulation = mapOf("deviceName" to "iPhone 14 Pro Max")
            val chromeOptions = ChromeOptions()
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation)
            return ChromeDriver(chromeOptions)
        }
    }*/;

    abstract fun getDriver(): WebDriver

    companion object {
        private val random = Random()
        fun getRandomBrowser(): BrowsersList {
            val values = entries
            return values[random.nextInt(values.size)]
        }
    }
}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

reportsDir = "target/geb-reports"

driver = { 
    def driver = new HtmlUnitDriver()
    driver.javascriptEnabled = true
    driver
}

environments {
    // run as "grails -Dgeb.env=chrome test-app -functional"
    chrome {
        driver = { new ChromeDriver() }
    }

    // run as "grails -Dgeb.env=firefox test-app -functional"
    firefox {
        driver = { new FirefoxDriver() }
    }
}

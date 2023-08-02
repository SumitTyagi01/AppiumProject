package org.Framework.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.Framework.pageObjects.android.FormPage;
import org.Framework.utils.AppiumUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;
    public FormPage formPage;

    @BeforeMethod(alwaysRun = true)
    public void ConfigureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream  fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\Framework\\resources\\data.properties");
        prop.load(fis);
        String ipAddress=System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        String portNumber=prop.getProperty("port");

        serviceBuilder= startAppiumServer(ipAddress,Integer.parseInt(portNumber));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("C:\\Users\\Sumit\\Documents\\ChromeDriverAppium\\chromedriver.exe");
       // options.setApp("C:\\Users\\Sumit\\IdeaProjects\\Appium\\src\\test\\resources\\ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\Framework\\resources\\General-Store.apk");

       driver = new AndroidDriver(serviceBuilder.getUrl(), options);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       formPage = new FormPage(driver);


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        driver.quit();
        serviceBuilder.stop();
    }
}

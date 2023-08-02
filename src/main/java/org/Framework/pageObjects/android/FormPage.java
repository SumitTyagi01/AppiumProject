package org.Framework.pageObjects.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.Framework.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {

        AndroidDriver driver;

        public FormPage(AndroidDriver driver){
                super(driver);
                this.driver = driver;
                PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        }


        @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
        private WebElement nameField;

        @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
        private WebElement femaleRadioButton;

        @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
        private WebElement maleRadioButton;

        @AndroidFindBy(id="android:id/text1")
        private WebElement clickOnCountryDropDown;

        @AndroidFindBy(id = "android:id/text1")
        private WebElement countrySelect;

        @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
        private WebElement letsShopButton;

        public void setActivity(){
                try {
                        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
                        driver.startActivity(activity);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void setNameField(String name){
           nameField.sendKeys(name);
           driver.hideKeyboard();
        }

        public void setGender(String gender){
                if(gender.contains("female"))
                        femaleRadioButton.click();
                else
                        maleRadioButton.click();
        }

        public void setCountrySelection(String countryName){
                countrySelect.click();
                scrollToText(countryName);
                driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
        }

        public ProductCatalogue submitForm(){
                letsShopButton.click();
                return new ProductCatalogue(driver);

        }



}

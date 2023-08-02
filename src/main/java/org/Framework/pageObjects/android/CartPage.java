package org.Framework.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.Framework.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> numberOfProductsOnCartPage;

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmountdisplayed;

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id="android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;

    double amount;
    public double calculateAmountOfProducts(){
        int numberOfElements = numberOfProductsOnCartPage.size();
        List<String> itemsList = new ArrayList<>();
        for (int i=0;i<numberOfElements;i++){
            itemsList.add(numberOfProductsOnCartPage.get(i).getText());
            System.out.println(itemsList.get(i));

        }

        for (int i=0;i<itemsList.size();i++) {
            amount = amount + Float.parseFloat(itemsList.get(i).replace("$"," ").trim());
        }

        return amount;

    }

    double totalAmount;
    public double validateAmountOnCartPageWithCalculatedAmount(double amount) {
        totalAmount = Float.parseFloat(totalAmountdisplayed.getText().replace("$", " ").trim());
        System.out.println("Amount displayed is: " + totalAmount);
        return totalAmount;
    }

    public void acceptTermsConditions(){
        longPressAction(terms);
        acceptButton.click();
    }

    public void submitOrder(){
        checkBox.click();
        proceedBtn.click();
    }

}

package org.Framework.pageObjects.android;


import org.Framework.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc4_Hybrid extends BaseTest {

//    @BeforeMethod
//    public void preSetup() throws InterruptedException {
//       formPage.setActivity();
//    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(HashMap<String,String> input ) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemsToCartByIndex(0);
        productCatalogue.addItemsToCartByIndex(0);
        CartPage cartPage = productCatalogue.goToCartPage();
        double amount = cartPage.calculateAmountOfProducts();
        double totalAmount = cartPage.validateAmountOnCartPageWithCalculatedAmount(amount);
        Assert.assertEquals(amount,totalAmount);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();

        Thread.sleep(6000);

        //driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data =getJsonData(System.getProperty("user.dir")+"//src//test//java//org//Framework//testData//eCommerce.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

}

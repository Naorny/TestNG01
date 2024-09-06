package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class Tests {
    WebDriver driver;

    @BeforeMethod
    void logIn() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\huliaieva\\Downloads\\selenium webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

    }

    @Test
    void comparingPrices () {
        String price_t_shirt_list = driver.findElement(By.xpath("//a[@id=\"item_3_title_link\"]/parent::div/following-sibling::div[contains(@class, \"pricebar\")]/div")).getText();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();

        String price_sauce_lab_fleece_jacket_list = driver.findElement(By.xpath("//a[@id=\"item_5_title_link\"]/parent::div/following-sibling::div[contains(@class, \"pricebar\")]/div")).getText();
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();

        String price_sauce_lab_bike_light_list = driver.findElement(By.xpath("//a[@id=\"item_0_title_link\"]/parent::div/following-sibling::div[contains(@class, \"pricebar\")]/div")).getText();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        String price_t_shirt_cart = driver.findElement(By.xpath("//a[@id=\"item_3_title_link\"]/following-sibling::div[contains(@class, \"item_pricebar\")]/div")).getText();
        String price_sauce_lab_fleece_jacket_cart = driver.findElement(By.xpath("//a[@id=\"item_5_title_link\"]/following-sibling::div[contains(@class, \"item_pricebar\")]/div")).getText();
        String price_sauce_lab_bike_light_cart = driver.findElement(By.xpath("//a[@id=\"item_0_title_link\"]/following-sibling::div[contains(@class, \"item_pricebar\")]/div")).getText();

        Assert.assertEquals(price_t_shirt_list, price_t_shirt_cart);
        Assert.assertEquals(price_sauce_lab_fleece_jacket_list, price_sauce_lab_fleece_jacket_cart);
        Assert.assertEquals(price_sauce_lab_bike_light_list, price_sauce_lab_bike_light_cart);
    }

    @AfterMethod
    void reset() {
        driver.findElement(By.className("bm-burger-button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("reset_sidebar_link")).click();
        driver.findElement(By.id("inventory_sidebar_link")).click();
    }

    @AfterClass
    void quit() {
        driver.quit();
    }
}
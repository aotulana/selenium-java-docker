package web.pages;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDescriptionPage {

    private final WebDriver driver;
    private final By aboutItem = By.id("feature-bullets");

    public ItemDescriptionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void printAboutItemText() {
        String text = driver.findElement(aboutItem).getText();
        System.out.println(text);
    }
}

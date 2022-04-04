package web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import web.pages.ItemDescriptionPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Navigation {

    private final WebDriver driver;
    private final By hamburgerMenu = By.id("nav-hamburger-menu");
    private final By filterSectionTitle = By.cssSelector(".a-size-base.a-color-base.a-text-bold");
    private final By sortMenu = By.id("s-result-sort-select");

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public Navigation openHamburgerMenu() {
        driver.findElement(hamburgerMenu).click();
        return this;
    }

    public Navigation selectDepartment(String departmentLinkText) {
        driver.findElement(By.linkText(departmentLinkText)).click();
        return this;
    }

    public Navigation selectCategory(String categoryLinkText) {
        driver.findElement(By.linkText(categoryLinkText)).click();
        return this;
    }

    public Navigation filterBy(String sectionTitle, String filterCheckbox) {
        List<WebElement> filterSection = driver.findElements(filterSectionTitle)
                .stream()
                .filter(webElement -> webElement.getText().equals(sectionTitle))
                .collect(Collectors.toList());

        if (filterSection.size() == 0) {
            throw new NoSuchElementException(sectionTitle + " filter section not found");
        }

        filterSection.get(0).findElement(By.xpath("//span[text()='" + filterCheckbox + "']")).click();
        return this;
    }

    public Navigation sortResultBy(String sortCriterion) {
        Select sortOptions = new Select(driver.findElement(sortMenu));
        sortOptions.selectByVisibleText(sortCriterion);
        return this;
    }

    public ItemDescriptionPage selectItemFromResult(int positionOfItem) {
        driver.findElement(By.cssSelector("div[data-cel-widget='search_result_" + positionOfItem + "']")).click();

        ArrayList<String> currentTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(currentTabs.get(1));

        return new ItemDescriptionPage(driver);
    }
}

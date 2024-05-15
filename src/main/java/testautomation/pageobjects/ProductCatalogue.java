package testautomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomation.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// addToCart = prod.findElement(By.cssSelector("button.btn.w-10")).click();
	// driver.findElement(By.cssSelector(".ng-animating")))
	// PageFactory
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement myCart;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button.btn.w-10");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public CartPage addProductToCart(String productName) {
		// WebElement prod = getProductByName(productName);
		getProductByName(productName).findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}

package com.wdg.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.wdg.common.CommonMethods;
import com.wdg.properties.ReadProperties;

public class Core {

	public static Boolean login(WebDriver driver) {

		// check login username present
		WebElement elementUsername = CommonMethods.checkElemetPresent(driver, "//input[@id='loginname']", 2);
		System.out.println("check login username present");

		// check login password present
		WebElement elementpassword = CommonMethods.checkElemetPresent(driver, "//input[@id='password']", 2);
		System.out.println("check login password present");

		// check login submit button present
		WebElement elementSubmit = CommonMethods.checkElemetPresent(driver,
				"//form[@name='frmLogin']//a[@class='place-order']", 2);
		System.out.println(" check login submit button present");

		if (elementUsername == null || elementpassword == null || elementSubmit == null) {
			System.out.println("Login form elements not found");
			return false;
		}
		System.out.println("Login form elements found");

		Map<String, String> cridentials = ReadProperties.readProperty();

		System.out.println("User name : " + cridentials);
		// Enter username
		elementUsername.sendKeys(cridentials.get("tpUsername"));
		System.out.println("Enter username");

		// Enter password
		elementpassword.sendKeys(cridentials.get("tpPassword"));
		System.out.println("Enter password");

		// submit login
		elementSubmit.submit();
		System.out.println("submit login");
		return true;
	}

	public static Boolean navigateToAddClick(WebDriver driver) throws InterruptedException {

		// check popup present
		WebElement elementPopup = CommonMethods.checkElemetPresent(driver, "//a[@type='button']", 2);
		System.out.println("check popup present");

		if (elementPopup == null) {
			System.out.println("Popup not present");
			return false;
		}

		// Click on close Popup
		elementPopup.click();

		// check banner cuts present
		WebElement elementBannercuts = CommonMethods.checkElemetPresent(driver, "//h4[contains(text(),'Banner Cuts')]",
				2);
		System.out.println("check banner cuts present");

		if (elementBannercuts == null) {
			System.out.println("banner cuts element not present");
			return false;
		}

		System.out.println("Visibility of Banner Cut : " + elementBannercuts.isEnabled());

		// This is because of this error : Element Not Visible
		driver.get("https://bannercuts.com/?m=ads&a=AdzCtrl");

		// elementBannercuts.click();

		// check Addview present
		WebElement elementAddview = CommonMethods.checkElemetPresent(driver, "//h4[contains(text(),'Ads View')]", 2);
		System.out.println("check Addview present");

		if (elementAddview == null) {
			System.out.println("Addview element not present");
			return false;
		}

		// Click on Addview element
		elementAddview.click();

		// start package upgrade

		// check Addview present

		Select dropdownSelect = selectDropdown(driver);

		List<WebElement> AllOPtions = dropdownSelect.getOptions();

		List<String> indexValues = new ArrayList<>();
		for (WebElement individualElement : AllOPtions) {

			if (!individualElement.getAttribute("value").equals("0")) {
				indexValues.add(individualElement.getAttribute("value"));

				System.out.println("Individual values are : " + individualElement.getAttribute("value"));
			}
		}

		for (String indexValue : indexValues) {

			dropdownSelect = selectDropdown(driver);
			dropdownSelect.selectByValue(indexValue);

			int packageAdds = getPackageAdds(driver);
			int viewedAdds = getViewedAdds(driver);

			int addsToView = packageAdds - viewedAdds;

			System.out.println("Actual remaining adds : " + addsToView);

			addsToView = 4;
			if (addsToView > 0) {

				AddClickIterator(driver, addsToView);
			}
		}

		// end package upgrade

		// Select My Plan
		// dropdownSelect.selectByValue("4");
		// System.out.println("Select My Plan");

		return true;

	}

	@SuppressWarnings("finally")
	public static int getPackageAdds(WebDriver driver) {

		int value = 0;
		try {
			WebElement elementAddCount = CommonMethods.checkElemetPresent(driver,
					"//div[@class='post-ad-form']//div[9]//div[1]", 2);
			System.out.println("check package adds count present");

			if (elementAddCount == null) {
				System.out.println("package adds count not present");
				// return dropdownSelect;
			} else {
				String addCount = elementAddCount.getText();
				value = Integer.parseInt(addCount);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return value;
		}
	}

	@SuppressWarnings("finally")
	public static int getViewedAdds(WebDriver driver) {
		int value = 0;
		try {
			WebElement elementAddCount = CommonMethods.checkElemetPresent(driver,
					"//div[@class='post-ad-form']//div[11]//div[1]", 2);
			System.out.println("check viewed adds count present");

			if (elementAddCount == null) {
				System.out.println("viewed adds count not present");
				// return dropdownSelect;
			} else {
				String addCount = elementAddCount.getText();
				value = Integer.parseInt(addCount);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return value;
		}
	}

	@SuppressWarnings("finally")
	public static Select selectDropdown(WebDriver driver) {
		// check Addview present
		Select dropdownSelect = null;
		try {
			WebElement elementDropdown = CommonMethods.checkElemetPresent(driver, "//select[@id='plan']", 2);
			System.out.println("check elementDropdown present");

			if (elementDropdown == null) {
				System.out.println("Dropdown element not present");
				//return dropdownSelect;
			} else {
				dropdownSelect = new Select(elementDropdown);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return dropdownSelect;
		}

	}

	public static Boolean AddClickIterator(WebDriver driver, int iteration) throws InterruptedException {
		Boolean state = false;

		// Iterate for given number
		System.out.println("Iterate for given number");
		for (int i = 0; i < iteration; i++) {
			state = AddClicker(driver);

			if (!state)
				break;
		}
		return state;
	}

	public static Boolean AddClicker(WebDriver driver) throws InterruptedException {

		// check add present
		WebElement elementAdd = CommonMethods.checkElemetPresent(driver, "//a[@href='javascript:confAdd(0,2)']", 2);
		System.out.println("check add present");

		if (elementAdd == null) {
			System.out.println("Add not present");
			return false;
		}

		// Get original Handle
		String originalHandle = driver.getWindowHandle();

		// Click on element add
		System.out.println("Click on element add");
		elementAdd.click();

		WebElement elementVerifyAdd = CommonMethods.checkElemetPresent(driver, "//input[@id='submit2']", 2);
		System.out.println("check VerifyAdd present");

		if (elementVerifyAdd == null) {
			System.out.println("elementVerifyAdd not present");
			return false;
		}

		// Click on elementVerifyAdd
		elementVerifyAdd.click();

		// This is to pretend as a human

		Thread.sleep(4000);
		// driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);

		// Switch to second window
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());

		// driver.switchTo().window(browserTabs.get(1));

		// WebElement elementEmail = CommonMethods.checkElemetPresent(driver,
		// "//input[@id='mce-EMAIL']", 2);
		// System.out.println("check elementEmail present");

		// if (elementEmail == null) {
		// System.out.println("elementEmail not present");
		// return false;
		// }

		System.out.println("Click on elementVerifyAdd");

		// Do something to open new tabs

		for (String handle : browserTabs) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);

		WebElement elementGoNextImage = CommonMethods.checkElemetPresent(driver, "//input[@id='submit3']", 2);
		System.out.println("check elementGoNextImage present");

		if (elementGoNextImage == null) {
			System.out.println("elementGoNextImage not present");
			return false;
		}
		elementGoNextImage.click();

		return true;
	}
}

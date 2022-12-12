package com.swiggy;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class PurchaseProduct {

public static AndroidDriver <AndroidElement> driver;
DesiredCapabilities caps = new DesiredCapabilities();
	
	@BeforeTest
	public void launchAppExample() throws MalformedURLException
	{
			File appdir = new File("src/com/swiggy");
			File app = new File(appdir,"in-swiggy-android-1019-61658870-1e5ecd06e7aa947e7ec92bbb8ef13d8f.apk");
			
			caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "9.0");
            caps.setCapability("noReset", "true");
            //caps.setCapability("noReset", "true");
            
          //Usually the App package name and the App activity will be provided by the developers
            
            caps.setCapability("appPackage", "in.swiggy.android");
            caps.setCapability("appActivity", "in.swiggy.android.activities.MealsActivity");
            
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
	}
	
	@Test
	public void purchaseProduct() throws InterruptedException
	{
		//Location not granted automatically but placed manually
		driver.findElementById("in.swiggy.android:id/tv_primary_cta").click();
		Thread.sleep(5000);
		driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
		Thread.sleep(5000);
		AndroidElement cityName=driver.findElementById("in.swiggy.android:id/location_description");
		cityName.sendKeys("Dehradun");
		Thread.sleep(5000);
		
		//Select Location
        Thread.sleep(2000);
		TouchAction Action=new TouchAction(driver);
		Action.press(PointOption.point(250,450)).release().perform();
		Thread.sleep(3000);
		driver.findElementById("in.swiggy.android:id/google_place_search_title_text1").click();
		Thread.sleep(3000);
		
		//Select Dehradun Annotation
		driver.findElementById("in.swiggy.android:id/address_annotation_textview").click();
		Thread.sleep(3000);
		
		//Select & Proceed
		Action.press(PointOption.point(500,2150)).release().perform();
		Thread.sleep(3000);
		
		//Input of 'Pizza' to be searched
		AndroidElement foodName=driver.findElementById("in.swiggy.android:id/disc_search_bar_container");
		cityName.sendKeys("Pizza");
		
		//Selecting the option 'Pizza'
		driver.findElementByAccessibilityId("Pizza").click();
		Thread.sleep(3000);
		
		//Adding to Cart
		Action.press(PointOption.point(850,1300)).release().perform();
		Thread.sleep(3000);
		
		//Checkout Button Click
		driver.findElementById("in.swiggy.android:id/checkoutText").click();
		Thread.sleep(5000);
		
		System.out.println("Product Added Successfully in the Cart");
	}	
	
}

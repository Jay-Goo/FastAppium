package com.fastappium.sample;


import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.context.annotation.Description;

import java.util.List;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * ================================================
 * 作    者：JayGoo
 * 版    本：
 * 创建日期：2017/10/31
 * 描    述:
 * ================================================
 */
public class LoginTest extends ZhikeMarkBaseTest{


    @Override
    public void setUp() throws Exception {
        super.setUp();
//        driver.startActivity(new Activity(appPackage,appActivity));
//        sleep(5);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    @Test
    @Description("测试登录")
    public void testLogin(){

        testSearchSite();

        List<AndroidElement> loginEditTextList = driver.findElementsByClassName("android.widget.EditText");
        loginEditTextList.get(0).sendKeys("17191191681");
        loginEditTextList.get(1).sendKeys("123456");

        driver.findElementByXPath("//*[contains(@text,'登录')]").click();

        sleep(1);

        //验证点击后的界面是否是登录成功后的界面
        assertEquals(".control.activity.common.MainActivity",driver.currentActivity());

        testLogout();
    }


    @Ignore
    @Test
    @Description("测试首次登录的App功能引导页")
    public void testFirstGuide(){
        driver.resetApp();

        driver.swipeToLeft();
        driver.swipeToLeft();

        sleep(1);

        //用xpath的方式寻找到text值为'立即体验'的控件，然后点击

        driver.findElementByXPath("//*[contains(@text,'立即体验')]").click();

        sleep(1);

        //验证点击后的界面是否是选择站点界面
        assertEquals(driver.currentActivity(),
                ".control.activity.common.ChooseAreaActivity");

    }

    @Test
    @Description("退出登录")
    public void testLogout(){

        if (driver.currentActivity().contains("MainActivity")){

            //找到我的Tab
            List<WebElement> bottomChildTabList = driverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("com.smartstudy.smartmark:id/bottomTab")))
                    .findElements(By.className("android.widget.RelativeLayout"));
            bottomChildTabList.get(bottomChildTabList.size() - 1).click();

            driverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("com.smartstudy.smartmark:id/logout_btn")))
                    .click();

            //点击确定
            driverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("com.smartstudy.smartmark:id/MessageDialogButton2")))
                    .click();

            sleep(2);

            assertNotEquals(".control.activity.common.MainActivity",driver.currentActivity());
        }else {
            testLogin();
        }
    }

    @Test
    @Description("通过搜索框搜索站点")
    public void testSearchSite(){

        driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.smartstudy.smartmark:id/base_right_imageBtn1")))
                .click();

        //找到搜索输入框，输入要搜索的内容
        List<WebElement> searchEditTextList =  driverWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.EditText")));
        searchEditTextList.get(0).sendKeys("智能批改");

        //验证搜索内容，点击搜索后的站点
        List<WebElement> searchResultTextList = driverWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.smartstudy.smartmark:id/area_tv_school")));
        assertNotEquals(0,searchResultTextList.size());
        searchResultTextList.get(0).click();

        //验证点击后的界面是否是登录界面
        assertEquals(".control.activity.common.LoginActivity",driver.currentActivity());
    }

    @Test
    @Description("通过滚动搜索站点")
    public void testScrollSearchSite(){

        driver.snapShot();

        //滚动搜索站点
        driver.scrollElementByXPath("//*[contains(@text,'智能批改')]").click();

        sleep(1);

        //验证点击后的界面是否是登录成功后的界面
        assertEquals(".control.activity.common.LoginActivity",driver.currentActivity());
        driver.snapShot();
    }


}

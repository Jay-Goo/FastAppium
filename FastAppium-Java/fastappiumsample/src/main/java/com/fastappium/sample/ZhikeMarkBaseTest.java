package com.fastappium.sample;

import com.fastappium.java.FastBaseTest;

/**
 * ================================================
 * 作    者：JayGoo
 * 版    本：
 * 创建日期：2017/11/13
 * 描    述: 智课批改基础测试类，用于
 * ================================================
 */
public class ZhikeMarkBaseTest extends FastBaseTest{

    @Override
    public String setAppActivity() {
        return ".control.activity.common.SplashActivity";
    }

    @Override
    public String setAppName() {
        return "zhike_mark.apk";
    }

    @Override
    public String setAppPackage() {
        return "com.smartstudy.smartmark";
    }

    @Override
    public String setDriverServerUrl() {
        return "http://127.0.0.1:4723/wd/hub";
    }

}

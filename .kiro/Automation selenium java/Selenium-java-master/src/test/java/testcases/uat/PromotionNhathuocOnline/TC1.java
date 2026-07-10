package testcases.uat.PromotionNhathuocOnline;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest1;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestListener;

@Listeners(TestListener.class)
public class TC1 extends BaseTest1 {

    private static final String SDT           = "0835089254";
    private static final String PASSWORD      = "123456";
    private static final String PRODUCT_CODE  = "00501988";
    private static final String PRICE_SALE    = "205.600";
    private static final String PRICE_ORIGIN  = "257.000";
    private static final String DISCOUNT_AMT  = "51.400";
    private static final String DISCOUNT_PCT  = "20%";

    @Override
    protected String getBaseUrl() {
        return "https://uat.nhathuoclongchau.com.vn";
    }

    @Override
    @BeforeMethod
    public void setup(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String desc = result.getMethod().getDescription();
        if (desc != null && !desc.isEmpty()) { testName = testName + " - " + desc; }
        test = extent.createTest(testName);

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        if (utils.ConfigReader.getInstance().isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-save-password-bubble");

        driver = new ChromeDriver(options);
        driver.get(getBaseUrl());

        wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test(priority = 1, description = "FLOW - Login va verify giam gia 20% SP 00501988 tren Nha Thuoc Long Chau Online UAT")
    public void TC01() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        /* =========================
         * TC01 - DONG POPUP + CLICK DANG NHAP BANG JS
         * ========================= */
        ExtentTest tc01 = test.createNode("TC01 - Dong popup va click Dang nhap");
        //Thread.sleep(2500);

        // Xoa tat ca overlay/popup bang JS truoc khi click
        js.executeScript(
            "document.querySelectorAll('[class*=\"overlay\"],[class*=\"backdrop\"],[class*=\"mask\"]')" +
            ".forEach(function(el){ el.style.display='none'; });\n" +
            "document.querySelectorAll('[class*=\"popup\"],[class*=\"modal\"]')" +
            ".forEach(function(el){ if(el.offsetHeight > 200) el.style.display='none'; });"
        );
        Thread.sleep(400);

        // Dong cookie
        try {
            WebElement btn = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Chap nhan tat ca')]")));
            js.executeScript("arguments[0].click();", btn);
            Thread.sleep(300);
        } catch (Exception ignored) {}


        // đóng pop_up


        //  WebElement btnClose = wait.until(
        //     ExpectedConditions.elementToBeClickable(
        //         By.xpath("//svg[contains(@class,'size-3.5')]/ancestor::div[1]")
        //     )
        // );
        // btnClose.click();

        WebElement btndangnhap = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='ml-2 text-body1 font-medium text-white cursor-pointer']")
            )
        );
        btndangnhap.click();

        Thread.sleep(2000);
        tc01.pass("Da click Dang nhap");


        /* =========================
         * TC02 -Chọn ID
         * ========================= */
        ExtentTest tc02 = test.createNode("TC2 - Chọn ID + Nhap SDT \" + SDT)");

        WebElement ID = wait.until(
            ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='border-stroke-disable hover:bg-white-2 rounded-full border bg-white p-3 leading-[1] cursor-pointer']")));
        ID.click();
        //Thread.sleep(millis: 300);


        /* =========================
         * TC02 - NHAP SDT
         * ========================= */
       

        WebElement inputSdt = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@placeholder='Nhap so dien thoai' or @placeholder[contains(.,'dien thoai')] or @type='tel' or @id='username']")));
        inputSdt.clear();
        inputSdt.sendKeys(SDT);
        Thread.sleep(300);
        tc02.pass("TC2 - Chọn ID + Nhap SDT \" + SDT");

        /* =========================
         * TC03 - CLICK TIEP TUC
         * ========================= */
        ExtentTest tc03 = test.createNode("TC03 - Click Tiep tuc");

            WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(2));

        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.primary-button.on-enter")
            )
        ).click();
      //  Thread.sleep(2000);
        tc03.pass("Da click Tiep tuc");

        /* =========================
         * TC04 - FPT ID: NHAP MAT KHAU
         * ========================= */
        ExtentTest tc04 = test.createNode("TC04 - FPT ID - Nhap mat khau");

        // Neu co man hinh chon account
        try {
            WebElement btnTiepTucFpt = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[contains(.,'Tiep tuc dang nhap voi')]")));
            js.executeScript("arguments[0].click();", btnTiepTucFpt);
            Thread.sleep(2000);
            tc04.pass("Da click Tiep tuc dang nhap voi " + SDT);
        } catch (Exception e) {
            tc04.info("Khong co man hinh chon account");
        }

        // Nhap mat khau
        try {
            WebElement inputPass = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='otp-input']")));
            inputPass.clear();
            inputPass.sendKeys(PASSWORD);
            Thread.sleep(300);

            WebElement btnLogin = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit'] | //button[contains(.,'Dang nhap')]")));
            js.executeScript("arguments[0].click();", btnLogin);
            Thread.sleep(3000);
            tc04.pass("Da dang nhap thanh cong");
        } catch (Exception e) {
            tc04.fail("Loi dang nhap: " + e.getMessage());
        }

        // Doi redirect ve Long Chau
        try {
            wait.until(ExpectedConditions.urlContains("nhathuoclongchau.com.vn"));
            Thread.sleep(1500);
        } catch (Exception ignored) {}

        /* =========================
         * TC05 - TIM KIEM SP 00501988
         * ========================= */
        ExtentTest tc05 = test.createNode("TC05 - Tim kiem SP " + PRODUCT_CODE);

        // Dong popup sau login neu co
        try {
            js.executeScript(
                "document.querySelectorAll('[class*=\"popup\"],[class*=\"modal\"]')" +
                ".forEach(function(el){ if(el.offsetHeight > 200) el.style.display='none'; });"
            );
            Thread.sleep(300);
        } catch (Exception ignored) {}

        WebElement searchBox = driver.findElement(
                        By.cssSelector("input[name='search']"));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(PRODUCT_CODE);
        Thread.sleep(500);
// click sản phẩm đầu tiên
        WebElement product =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("(//a[contains(@href,'00501988')])[1]")
                        )
                );

        product.click();
        tc05.pass("Da click SP " + PRODUCT_CODE);
            Thread.sleep(1500);
        
        /* =========================
         * Chọn mua sản phẩm
         * ========================= */
              driver.findElement(
    By.xpath("//a[contains(@href,'fohepta-400g-36190.html')]")
).click();


        /* =========================
         * TC06 - VERIFY TRANG DETAIL
         * ========================= */
        ExtentTest tc06 = test.createNode("TC06 - Verify gia giam 20%");

        String detailSource = driver.getPageSource();

        if (detailSource.contains(PRICE_SALE))    tc06.pass("PASS - Gia ban = " + PRICE_SALE + "d");
        else                                       tc06.fail("FAIL - Khong thay gia ban " + PRICE_SALE + "d");

        if (detailSource.contains(PRICE_ORIGIN))  tc06.pass("PASS - Gia goc = " + PRICE_ORIGIN + "d");
        else                                       tc06.fail("FAIL - Khong thay gia goc " + PRICE_ORIGIN + "d");

        if (detailSource.contains(DISCOUNT_PCT))   tc06.pass("PASS - Badge giam " + DISCOUNT_PCT);
        else                                       tc06.fail("FAIL - Khong thay badge " + DISCOUNT_PCT);

        /* =========================
         * TC07 - CLICK CHON MUA
         * ========================= */
        ExtentTest tc07 = test.createNode("TC07 - Click Chon mua");

        WebElement btnChonMua = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Chon mua')]")));
        js.executeScript("arguments[0].click();", btnChonMua);
        Thread.sleep(2000);
        tc07.pass("Da click Chon mua");

        /* =========================
         * TC08 - VAO GIO HANG
         * ========================= */
        ExtentTest tc08 = test.createNode("TC08 - Vao gio hang");

        try {
            WebElement btnXemGio = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(.,'Xem gio hang')] | //button[contains(.,'Xem gio hang')]")));
            js.executeScript("arguments[0].click();", btnXemGio);
        } catch (Exception e) {
            driver.get("https://uat.nhathuoclongchau.com.vn/gio-hang");
        }

        wait.until(ExpectedConditions.urlContains("gio-hang"));
        Thread.sleep(1500);
        tc08.pass("Da vao gio hang");

        /* =========================
         * TC09 - VERIFY GIO HANG
         * ========================= */
        ExtentTest tc09 = test.createNode("TC09 - Verify gio hang giam 20%");

        String cartSource = driver.getPageSource();

        if (cartSource.contains("Fohepta") || cartSource.contains(PRODUCT_CODE))
            tc09.pass("PASS - SP " + PRODUCT_CODE + " co trong gio hang");
        else
            tc09.fail("FAIL - Khong thay SP trong gio hang");

        if (cartSource.contains(PRICE_SALE))     tc09.pass("PASS - Thanh tien = " + PRICE_SALE + "d");
        else                                     tc09.fail("FAIL - Khong thay thanh tien " + PRICE_SALE + "d");

        if (cartSource.contains(DISCOUNT_AMT))   tc09.pass("PASS - Giam = " + DISCOUNT_AMT + "d");
        else                                     tc09.fail("FAIL - Khong thay giam " + DISCOUNT_AMT + "d");

        if (cartSource.contains(PRICE_ORIGIN))   tc09.pass("PASS - Tong goc = " + PRICE_ORIGIN + "d");
        else                                     tc09.fail("FAIL - Khong thay tong goc " + PRICE_ORIGIN + "d");

        if (cartSource.contains(DISCOUNT_PCT) || cartSource.contains("Giam gia 20"))
            tc09.pass("PASS - Hien thi giam " + DISCOUNT_PCT);
        else
            tc09.fail("FAIL - Khong hien thi giam " + DISCOUNT_PCT);

        test.pass("PASS - TC1 verify FLASHSALE 20%: " + PRODUCT_CODE +
                " | " + PRICE_ORIGIN + "d -> " + PRICE_SALE + "d (giam " + DISCOUNT_AMT + "d)");
    }
}
package testcases.ci.Promotion;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest1;
import listeners.TestListener;



@Listeners(TestListener.class)
public class TC4 extends BaseTest1 {

        @Override
        protected String getBaseUrl() {
                return "https://ci-promotion.frt.vn/manager-promotion-list";
        }

        // =================================================
        // FULL FLOW - TẠO CTKM (TC01 → TC06)
        // =================================================
        @Test(priority = 1, description = "FLOW 1 - CTKM Đơn hàng", enabled = false)

        public void testCreatePromotionFlowNhomCTKMSanphamCI() {

                /*
                 * =========================
                 * TC01 - LOGIN
                 * =========================
                 */
                ExtentTest tc01 = test.createNode("TC01 - Login hệ thống");

                tc01.info("Nhập username");
                WebElement userNameBox = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.name("LoginInput.UserNameOrEmailAddress")));
                userNameBox.clear();
                userNameBox.sendKeys("giant");

                tc01.info("Nhập password");
                WebElement passwordBox = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.name("LoginInput.Password")));
                passwordBox.clear();
                passwordBox.sendKeys("********");

                tc01.info("Click đăng nhập");
                driver.findElement(By.id("kt_login_signin_submit")).click();

                wait.until(ExpectedConditions.urlContains("manager"));
                Assert.assertTrue(driver.getCurrentUrl().contains("manager"),
                                "Login FAILED");

                tc01.pass("Login thành công");

                /*
                 * =========================
                 * TC02 - TẠO CTKM
                 * =========================
                 */
                ExtentTest tc02 = test.createNode("TC02 - Tạo CTKM");

                tc02.info("Click button tạo CTKM");
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'actionHeader')]"))).click();

                tc02.info("Nhập tên CTKM");

                String promoName = "import ĐƠN HÀNG__25052026_115716";

                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("promotiongeneralinfor_name"))).sendKeys(promoName);

                tc02.info("Nhập tên CTKM: " + promoName);

                tc02.pass("Tạo CTKM OK");

                /*
                 * =========================
                 * TC03 - THỜI GIAN + PHƯƠNG THỨC
                 * =========================
                 */

                // Lấy ngày hiện tại
                String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                ExtentTest tc03 = test.createNode("TC03 - Chọn thời gian & phương thức");

                // Click range picker
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-picker-range')]"))).click();

                // Click ngày bắt đầu
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@title='" + today + "']"))).click();

                // Click ngày kết thúc (cùng ngày)
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@title='" + today + "']"))).click();

                // Chọn phương thức
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-selector')]"))).click();

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[text()='Zalo']"))).click();

                tc03.pass("Chọn thời gian & phương thức OK");

                /*
                 * =========================
                 * TC04 - THUỘC CHIẾN DỊCH
                 * =========================
                 */
                ExtentTest tc04 = test.createNode("TC04 - Thuộc chiến dịch");

                WebElement campaignBox = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.id("promotiongeneralinfor_campaignId")));
                campaignBox.sendKeys("CD-0326-042");

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option') and contains(.,'CD-0326-042')]")))
                                .click();

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(),'Tiếp theo')]"))).click();

                tc04.pass("Hoàn tất màn 1");

                /*
                 * =========================
                 * TC05 - NHÓM CTKM
                 * =========================
                 */
                ExtentTest tc05 = test.createNode("TC05 - Nhóm CTKM");

                WebElement nhom = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.id("promotionClassId")));
                nhom.sendKeys("Sản Phẩm");

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Sản phẩm')]")))
                                .click();

                tc05.pass("Chọn nhóm CTKM OK");

                /*
                 * =========================
                 * TC06 - LOẠI CTKM
                 * =========================
                 */
                ExtentTest tc06 = test.createNode("TC06 - Loại CTKM");

                WebElement loai = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("promotionTypeID")));
                loai.click();
                loai.sendKeys("Giảm giá sản phẩm");

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Giảm giá sản phẩm')]")))
                                .click();

                tc06.pass("Chọn loại CTKM OK");

                /*
                 * =========================
                 * TC07 - KHU VỰC HIỂN THỊ
                 * =========================
                 */
                ExtentTest tc07 = test.createNode("TC07 - Khu vực hiển thị khuyến mãi");
                // cách bắt ant design element dropdown

                By displayAreaDropdownBy = By.xpath(
                                "//label[contains(text(),'Khu vực hiển thị khuyến mãi')]" +
                                                "/ancestor::div[contains(@class,'ant-form-item')]" +
                                                "//div[contains(@class,'ant-select-selector')]");

                WebElement displayAreaDropdown = wait.until(
                                ExpectedConditions.elementToBeClickable(displayAreaDropdownBy));

                displayAreaDropdown.click();

                // ✅ CLICK OPTION SAU KHI MỞ DROPDOWN
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Khuyến mãi sản phẩm chính')]")))
                                .click();

                tc07.pass("Chọn khu vực hiển thị khuyến mãi OK");

                ExtentTest tc08 = test.createNode("TC08 - Chi phí phòng ban");

                // UPLOAD ẢNH
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Luôn tìm input file (dù UI đã đổi)
                WebElement uploadInput = wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                                By.xpath("//input[@type='file']")));

                // Path ảnh trong project
                Path imagePath = Paths.get(
                                System.getProperty("user.dir"),
                                "src", "test", "resources", "images", "upload.png");

                // Upload
                uploadInput.sendKeys(imagePath.toAbsolutePath().toString());

                // 1️⃣ CLICK DROPDOWN
                By dropdownCPPB = By.xpath(
                                "//label[contains(text(),'Chi phí phòng ban')]" +
                                                "/ancestor::div[contains(@class,'ant-form-item')]" +
                                                "//div[contains(@class,'ant-select-selector')]");

                WebElement dropdownElement = wait.until(
                                ExpectedConditions.elementToBeClickable(dropdownCPPB));
                dropdownElement.click();

                // 2️⃣ CLICK OPTION
                By optionCPPB = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]"
                                                +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Phòng kiểm soát nghành hàng / KSNH']");

                wait.until(ExpectedConditions.elementToBeClickable(optionCPPB)).click();

                tc08.pass("Chọn Chi phí phòng ban OK");

                // 3️⃣ NEXT
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(),'Tiếp theo')]"))).click();

                // Step 3
                ExtentTest tc09 = test.createNode("Loại đầu vào");
                By dropdownLDV = By.xpath(
                                "//div[contains(@class,'ant-col ant-col-3')]" +
                                                "//div[contains(@class,'ant-select-selector')]");
                WebElement dropdownElement1 = wait.until(
                                ExpectedConditions.elementToBeClickable(dropdownLDV));
                dropdownElement1.click();

                By optionLDVBy = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]"
                                                +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Mã sản phẩm']");

                wait.until(ExpectedConditions.elementToBeClickable(optionLDVBy)).click();

                tc09.pass("Loại đầu vào");

                ExtentTest tc10 = test.createNode("Phép toán");
                By dropdownPT = By.xpath(
                                "//div[contains(@class,'ant-col ant-col-2')]" +
                                                "//div[contains(@class,'ant-select-selector')]");
                WebElement dropdownElement2 = wait.until(
                                ExpectedConditions.elementToBeClickable(dropdownPT));
                dropdownElement2.click();

                By optionPTBy = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]"
                                                +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Bằng']");

                wait.until(ExpectedConditions.elementToBeClickable(optionPTBy)).click();

                tc10.pass("Phép toán  OK");

                ExtentTest tc11 = test.createNode("Giá trị");

                // CLICK DROPDOWN
                By dropdownGT = By.id(
                                "inputID"// div[contains(@class,'ant-select')]"
                );

                WebElement dropdownElement4 = wait.until(
                                ExpectedConditions.elementToBeClickable(dropdownGT));
                dropdownElement4.click();

                By inputSearchBy = By.xpath(
                                "//input[@placeholder='Tìm kiếm mã, tên' and contains(@class,'ant-input')]");

                WebElement inputSearch = wait.until(
                                ExpectedConditions.elementToBeClickable(inputSearchBy));

                inputSearch.clear();
                inputSearch.sendKeys("00001958");

                // CHỌN OPTION TRONG TABLE
                By optionGT = By.xpath(
                                "//td[contains(@class,'ant-table-cell') and contains(@class,'ant-table-selection-column')]");

                wait.until(ExpectedConditions.elementToBeClickable(optionGT)).click();

                // CLICK OK
                WebElement btnOK = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.xpath("//button//span[text()='OK']")));
                btnOK.click();

                tc11.pass("Gía tri OK");

                ExtentTest tc12 = test.createNode("Loại đầu ra");
                By dropdownLDR = By.id("includeInput_0_groupCode_0_outputQualifierId");
                WebElement dropdownElement5 = wait.until(
                                ExpectedConditions.elementToBeClickable(dropdownLDR));
                dropdownElement5.click();

                By optionLDR = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]"
                                                +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Giảm theo giá cố định']");

                wait.until(ExpectedConditions.elementToBeClickable(optionLDR)).click();

                tc12.pass("Loại đầu ra  OK");

                ExtentTest tc13 = test.createNode("TC11 - Nhập giá trị");

                By inputGiaTriBy = By.xpath(
                                "//input[@placeholder='Nhập giảm theo giá cố định']");

                WebElement inputGiaTri = wait.until(
                                ExpectedConditions.elementToBeClickable(inputGiaTriBy));

                inputGiaTri.sendKeys("20000");

                tc13.pass("Nhập giá trị thành công");

                // CLICK XÁC NHẬN
                WebElement btnXACNHAN = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.xpath("//button[.//span[normalize-space()='Xác nhận']]")));
                btnXACNHAN.click();

                // Pop_up đồng ý
                By btnDongYBy = By.xpath(
                                "//div[contains(@class,'ant-modal-footer')]//button[.//span[normalize-space()='Đồng ý']]");

                WebElement btnDongY = wait.until(
                                ExpectedConditions.elementToBeClickable(btnDongYBy));
                btnDongY.click();

        }

        // FLOW CTKM ĐƠN HÀNG
        @Test(priority = 2, description = "FLOW 2 - CTKM Đơn hàng - Tổng tiền theo sản phẩm")
        public void testCreatePromotionFlowNhomCTKMDonhang() throws InterruptedException {

                ExtentTest tc01 = test.createNode("TC01 - Login hệ thống");
                tc01.info("Nhập username");
                WebElement userNameBox2 = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.name("LoginInput.UserNameOrEmailAddress")));
                userNameBox2.clear();
                userNameBox2.sendKeys("giant");

                tc01.info("Nhập password");
                WebElement passwordBox2 = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.name("LoginInput.Password")));
                passwordBox2.clear();
                passwordBox2.sendKeys("********");

                tc01.info("Click đăng nhập");
                driver.findElement(By.id("kt_login_signin_submit")).click();
                wait.until(ExpectedConditions.urlContains("manager"));
                tc01.pass("Login thành công");

                ExtentTest tc02 = test.createNode("TC02 - Tạo CTKM");
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'actionHeader')]"))).click();

                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
                String promoName2 = "Đơn hàng - Tổng tiền theo sản phẩm_" + LocalDateTime.now().format(formatter2);/// NOTE NAME
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("promotiongeneralinfor_name"))).sendKeys(promoName2);
                tc02.pass("Tạo CTKM OK: " + promoName2);

                ExtentTest tc03 = test.createNode("TC03 - Thời gian");
                String today2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-picker-range')]"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@title='" + today2 + "']"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//td[@title='" + today2 + "']"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-selector')]"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[text()='Zalo']"))).click();
                tc03.pass("Thời gian OK");

                ExtentTest tc04 = test.createNode("TC04 - Chiến dịch");
                WebElement campaignBox2 = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.id("promotiongeneralinfor_campaignId")));
                campaignBox2.sendKeys("CD-0326-042");
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option') and contains(.,'CD-0326-042')]")))
                                .click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(),'Tiếp theo')]"))).click();
                tc04.pass("Chiến dịch OK");

                ExtentTest tc05 = test.createNode("TC05 - Nhóm CTKM: Đơn hàng");
                WebElement nhom2 = wait.until(
                                ExpectedConditions.elementToBeClickable(By.id("promotionClassId")));
                nhom2.sendKeys("Đơn hàng");
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Đơn hàng')]")))
                                .click();
                tc05.pass("Nhóm CTKM OK");

                ExtentTest tc06 = test.createNode("TC06 - Loại CTKM: Tổng Tiền Đơn Hàng Theo Sản phẩm");
                WebElement loai2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("promotionTypeID")));
                loai2.click();
                loai2.sendKeys("Tổng Tiền Đơn Hàng Theo Sản phẩm");
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Tổng Tiền Đơn Hàng Theo Sản phẩm')]")))
                                .click();
                tc06.pass("Loại CTKM OK");

                ExtentTest tc07 = test.createNode("TC07 - Khu vực hiển thị");
                By displayArea2 = By.xpath(
                                "//label[contains(text(),'Khu vực hiển thị khuyến mãi')]" +
                                                "/ancestor::div[contains(@class,'ant-form-item')]" +
                                                "//div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(displayArea2)).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-select-item-option-content') and contains(.,'Khuyến mãi có liên quan đến giá trị bill')]")))
                                .click();
                tc07.pass("Khu vực hiển thị OK");

                ExtentTest tc08 = test.createNode("TC08 - Chi phí phòng ban");
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement uploadInput2 = wait2.until(
                                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
                Path imagePath2 = Paths.get(System.getProperty("user.dir"),
                                "src", "test", "resources", "images", "upload.png");
                uploadInput2.sendKeys(imagePath2.toAbsolutePath().toString());

                By dropdownCPPB2 = By.xpath(
                                "//label[contains(text(),'Chi phí phòng ban')]" +
                                                "/ancestor::div[contains(@class,'ant-form-item')]" +
                                                "//div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownCPPB2)).click();
                By optionCPPB2 = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]" +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Phòng kiểm soát nghành hàng / KSNH']");
                wait.until(ExpectedConditions.elementToBeClickable(optionCPPB2)).click();
                tc08.pass("Chi phí phòng ban OK");

                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(),'Tiếp theo')]"))).click();

                // Step 3 - Thiết lập điều kiện
                ExtentTest tc09 = test.createNode("TC09 - Điều kiện đầu vào: Tổng đơn hàng");
                
                // Click dropdown "Chọn điều kiện"
                By dropdownDK = By.xpath(
                                "(//div[contains(@class,'ant-select-selector')]//span[contains(text(),'Chọn điều kiện') or contains(text(),'Chọn điểu kiện')])[1]/ancestor::div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownDK)).click();
                // Chọn "Tổng đơn hàng"
                By optionDK = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]" +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "contains(.,'Tổng đơn hàng')]");
                wait.until(ExpectedConditions.elementToBeClickable(optionDK)).click();
                tc09.pass("Điều kiện đầu vào OK");

                ExtentTest tc10 = test.createNode("TC10 - Phép toán: Nhỏ hơn hoặc bằng");
                // Click dropdown Phép toán
                By dropdownPT2 = By.xpath(
                                "(//div[contains(@class,'ant-select-selector')]//span[contains(text(),'Chọn phép') or contains(text(),'phép toán')])[1]/ancestor::div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownPT2)).click();
                By optionPT2 = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]" +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "contains(.,'Nhỏ hơn hoặc bằng')]");
                wait.until(ExpectedConditions.elementToBeClickable(optionPT2)).click();
                tc10.pass("Phép toán OK");

                ExtentTest tc11 = test.createNode("TC11 - Giá trị: 300000");
                WebElement giaTriInput = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@placeholder='Giá trị' or @placeholder='Nhập giá trị' or @placeholder='Nhập số tiền']")));
                giaTriInput.sendKeys("300000");
                tc11.pass("Giá trị OK");

                // Thêm điều kiện vào: Mã Nhóm hàng
                ExtentTest tc11b = test.createNode("TC11b - Thêm điều kiện: Mã Nhóm hàng");
                
                // Click "+ Thêm điều kiện vào"
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(),'Thêm điều kiện vào') or contains(text(),'Thêm điều kiện và')]"))).click();
                
                // Click dropdown dòng mới
                Thread.sleep(1000);
                By dropdownLoai2 = By.xpath(
                                "(//div[contains(@class,'ant-select-selector')]//span[contains(text(),'Chọn điều kiện') or contains(text(),'Chọn điểu kiện')])[last()]/ancestor::div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownLoai2)).click();
                
                // Gõ "Mã Nhóm" rồi nhấn ENTER để chọn
                Thread.sleep(500);
                WebElement activeSearchInput = driver.switchTo().activeElement();
                activeSearchInput.sendKeys("Mã Nhóm");
                Thread.sleep(1000);
                activeSearchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
                
                // Chọn Phép toán: Chứa - dùng ID trực tiếp
                Thread.sleep(1000);
                // Click vào select element bằng ID
                WebElement phepToanSelect = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("includeInput_0_groupCode_1_operatorId")));
                phepToanSelect.click();
                Thread.sleep(500);
                phepToanSelect.sendKeys("Chứa");
                Thread.sleep(1000);
                phepToanSelect.sendKeys(org.openqa.selenium.Keys.ENTER);
                
                // Click nút "Sửa" bên cạnh ô "Chọn nhóm hàng" để mở popup
                Thread.sleep(1000);
                // Tìm input placeholder="Chọn nhóm hàng" rồi click addon "Sửa" bên cạnh
                WebElement maNhomBtn = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@placeholder='Chọn nhóm hàng']/following-sibling::span[contains(@class,'ant-input-group-addon')] | //input[@placeholder='Chọn nhóm hàng']/parent::*//span[contains(@class,'ant-input-group-addon')]")));
                maNhomBtn.click();
                
                // Đợi popup xuất hiện
                Thread.sleep(1500);
                
                // Search "CHẤT GÂY NGHIỆN" trong popup bên trái
                WebElement searchNhom = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("(//div[contains(@class,'ant-modal')]//input[contains(@placeholder,'Tìm') or contains(@placeholder,'tìm')])[1]")));
                searchNhom.clear();
                searchNhom.sendKeys("CHẤT GÂY NGHIỆN");
                Thread.sleep(1500);
                
                // Click dấu + bên cạnh "CHẤT GÂY NGHIỆN"
                WebElement addNhom = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-modal')]//tr[contains(.,'CHẤT GÂY NGHIỆN')]//span[contains(@class,'anticon-plus') or contains(@class,'anticon-plus-circle')]")));
                addNhom.click();
                Thread.sleep(500);
                
                // Click OK
                WebElement btnOKNhom = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-modal')]//button[.//span[text()='OK']]")));
                btnOKNhom.click();
                Thread.sleep(500);
                
                tc11b.pass("Thêm Mã Nhóm hàng: CHẤT GÂY NGHIỆN OK");

                // Điều kiện đầu ra
                ExtentTest tc12 = test.createNode("TC12 - Loại đầu ra: Phiếu Mua Hàng");
                // Click dropdown Loại (dòng đầu ra)
                By dropdownLoaiOut = By.xpath(
                                "(//div[contains(@class,'ant-select-selector')]//span[contains(text(),'Chọn loại') or contains(text(),'Chọn Loại')])[1]/ancestor::div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownLoaiOut)).click();
                By optionLoaiOut = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]" +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "contains(.,'Phiếu Mua Hàng')]");
                wait.until(ExpectedConditions.elementToBeClickable(optionLoaiOut)).click();
                tc12.pass("Loại đầu ra OK");

                ExtentTest tc13 = test.createNode("TC13 - Phép toán đầu ra: Bằng");
                By dropdownPTOut = By.xpath(
                                "(//div[contains(@class,'ant-select-selector')]//span[contains(text(),'Chọn phép') or contains(text(),'phép toán')])[1]/ancestor::div[contains(@class,'ant-select-selector')]");
                wait.until(ExpectedConditions.elementToBeClickable(dropdownPTOut)).click();
                By optionPTOut = By.xpath(
                                "//div[contains(@class,'ant-select-dropdown') and not(contains(@style,'display: none'))]" +
                                                "//div[contains(@class,'ant-select-item-option-content') and " +
                                                "normalize-space()='Bằng']");
                wait.until(ExpectedConditions.elementToBeClickable(optionPTOut)).click();
                tc13.pass("Phép toán đầu ra OK");

                ExtentTest tc14 = test.createNode("TC14 - Giá trị PMH: 00332266");
                // Click nút "Sửa" ở dòng Điều kiện đầu ra (Chọn phiếu mua hàng)
                WebElement giaTriBtn = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@placeholder='Chọn phiếu mua hàng']/following-sibling::span[contains(@class,'ant-input-group-addon')] | //input[contains(@placeholder,'phiếu mua hàng')]/parent::*//span[contains(@class,'addon')]")));
                giaTriBtn.click();
                
                // Đợi popup xuất hiện
                Thread.sleep(2000);
                
                // Nhập mã vào ô tìm kiếm bên trái
                WebElement searchPMH = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-modal')]//input[@placeholder='Tìm kiếm mã, tên' or contains(@placeholder,'Tìm')]")));
                searchPMH.clear();
                searchPMH.sendKeys("00332266");
                Thread.sleep(2000);
                
                // Click dấu + bên cạnh kết quả
                WebElement addPMH = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-modal')]//tr[contains(.,'00332266')]//span[contains(@class,'anticon-plus')] | //div[contains(@class,'ant-modal')]//tr[contains(.,'00332266')]//td[1]")));
                addPMH.click();
                Thread.sleep(1000);
                
                // Click OK
                WebElement btnOK = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'ant-modal')]//button[.//span[text()='OK']]")));
                btnOK.click();
                Thread.sleep(1000);
                tc14.pass("Giá trị PMH 00332266 OK");

                ExtentTest tc15 = test.createNode("TC15 - Số lượng: 1");
                WebElement soLuong = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@placeholder='Số lượng' or contains(@placeholder,'số lượng')]")));
                soLuong.clear();
                soLuong.sendKeys("1");
                tc15.pass("Số lượng OK");

                // Xác nhận
                WebElement btnXACNHAN2 = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                                By.xpath("//button[.//span[normalize-space()='Xác nhận']]")));
                btnXACNHAN2.click();

                By btnDongY2 = By.xpath(
                                "//div[contains(@class,'ant-modal-footer')]//button[.//span[normalize-space()='Đồng ý']]");
                wait.until(ExpectedConditions.elementToBeClickable(btnDongY2)).click();

                test.pass("✅ Hoàn thành tạo CTKM Đơn Hàng - Tổng Tiền Đơn Hàng");
        }

}

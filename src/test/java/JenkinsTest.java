import framework.Browser;
import framework.TestData;
import framework.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static framework.Browser.properties;

public class JenkinsTest {

    WebDriver driver;
    TestData dataReader = new TestData();

    @BeforeTest
    public void startBrowser() {
        driver = Browser.getInstance();
        driver.get(properties.getParameter("Url"));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void stopBrowser() {
        driver.quit();
    }

    @Test
    public void jenkinsTest() {
        //Авторизация
        User user = new User();
        SoftAssert softAssert = new SoftAssert();
        AuthorisationPage authorisationPage = PageFactory.initElements(driver, AuthorisationPage.class);

        //У всех кнопок (элемент типа button), которые нужно кликать в основной части задания, цвет фона = #4b758b.
        softAssert.assertEquals(authorisationPage.getButtonHexColor(authorisationPage.getConfirmButton()), dataReader.getParameter("buttonColor"), "Button color is wrong!");
        authorisationPage.authorisationSuccess(user.getUserForLogination());
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        //При клике по ссылке с текстом «ENABLE AUTO REFRESH» эта ссылка пропадает, а вместо неё появляется ссылка с текстом
        //«DISABLE AUTO REFRESH». При клике по ссылке с текстом «DISABLE AUTO REFRESH» эта ссылка пропадает, а вместо неё
        //появляется ссылка с текстом «ENABLE AUTO REFRESH». Т.е. эти две ссылки циклически сменяют друг друга.
        for (int i = 0; i < 3; i++) {
            mainPage.disableAutoRefresh();
            softAssert.assertEquals(mainPage.getAutoRefreshButton().getText(), "Включить автообновление страниц", "Wrong button appears!");
            mainPage.enableAutoRefresh();
            softAssert.assertEquals(mainPage.getAutoRefreshButton().getText(), "Отключить автообновление страниц", "Wrong button appears!");
        }

        //После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
        //и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
        mainPage.buttonClick(driver, mainPage.getManageButton());
        ManagePage managePage = PageFactory.initElements(driver, ManagePage.class);
        Assert.assertTrue(managePage.isElementDisplayed(managePage.getTestField1()), "Element [dt] with text [«Manage Users»] not found!");
        Assert.assertTrue(managePage.isElementDisplayed(managePage.getTestField2()), "Element [dd] with text [«Create/delete/modify users that can log in to this Jenkins»] not found!");

        //После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
        //становится доступна ссылка «Create User».
        managePage.buttonClick(driver, managePage.getManageUsersButton());
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        Assert.assertTrue(manageUsersPage.isElementDisplayed(manageUsersPage.getAddUsersButton()), "Link with text [«Create User»] not found!");

        //После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями
        //типа password, причём все поля должны быть пустыми.
        manageUsersPage.buttonClick(driver, manageUsersPage.getAddUsersButton());
        AddUserPage addUserPage = PageFactory.initElements(driver, AddUserPage.class);
        Assert.assertTrue(addUserPage.checkForm(), "Required form not found or not empty");

        //У всех кнопок (элемент типа button), которые нужно кликать в основной части задания, цвет фона = #4b758b.
        softAssert.assertEquals(addUserPage.getButtonHexColor(addUserPage.getConfirmButton()), dataReader.getParameter("buttonColor"), "Button color is wrong!");

        //При попытке создать пользователя с пустым (незаполненным) именем на странице появляется текст
        //«"" is prohibited as a full name for security reasons.»
        addUserPage.authorisationWithoutName(user.getTestUser());
        softAssert.assertEquals(addUserPage.getErrorMessage().getText(), addUserPage.getExpectedErrorMessage(), "Wrong error message!");

        //После заполнения полей формы («Username» = «someuser», «Password» = «somepassword»,
        //«Confirm password» = «somepassword», «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom»)
        //и клика по кнопке с надписью «Create User» на странице появляется строка таблицы (элемент tr), в которой есть
        //ячейка (элемент td) с текстом «someuser».
        addUserPage.authorisationSuccess(user.getTestUser());
        Assert.assertTrue(manageUsersPage.isElementDisplayed(manageUsersPage.getSomeUserTable()), "Table with cell with text [«someuser»] not found!");

        //После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст
        //«Are you sure about deleting the user from Jenkins?».
        manageUsersPage.buttonClick(driver, manageUsersPage.getSomeUserDeleteButton());
        DeleteUserPage deleteUserPage = PageFactory.initElements(driver, DeleteUserPage.class);
        softAssert.assertTrue(deleteUserPage.getErrorMessage().getText().contains(deleteUserPage.getExpectedErrorMesssage()), "Wrong error message!");

        //У всех кнопок (элемент типа button), которые нужно кликать в основной части задания, цвет фона = #4b758b.
        softAssert.assertEquals(deleteUserPage.getButtonHexColor(deleteUserPage.getConfirmButton()), dataReader.getParameter("buttonColor"), "Button color is wrong!");

        //После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr),
        //с ячейкой (элемент td) с текстом «someuser». На странице отсутствует ссылка с
        //атрибутом href равным «user/someuser/delete».
        deleteUserPage.buttonClick(driver, deleteUserPage.getConfirmButton());
        softAssert.assertFalse(manageUsersPage.isElementDisplayed(manageUsersPage.getSomeUserTable()), "Element [td] with text [«someuser»] is found!");
        softAssert.assertFalse(manageUsersPage.isElementDisplayed(manageUsersPage.getSomeUserDeleteButton()),"Link with text [«user/someuser/delete»] is found!");

        //{На той же странице, без выполнения каких бы то ни было действий}. На странице отсутствует ссылка с
        //атрибутом href равным «user/admin/delete».
        softAssert.assertFalse(manageUsersPage.isElementDisplayed(manageUsersPage.getAdminDeleteButton()),"Link with text [«user/admin/delete»] is found!");
    }
}
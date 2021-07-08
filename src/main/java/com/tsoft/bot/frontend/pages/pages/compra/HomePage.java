package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.OpenCartObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HomePage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String msg;
    String url;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }
    public void abrirUrl(String casoPrueba) throws  Throwable{
        msg = "Se ingresa a la URL y vamos al inicio de sesión";
        try{
            int contadorPagina = Integer.parseInt(casoPrueba) - 1;
            url = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_URL);
            driver.get(url);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            System.out.println(msg);
            click(driver, OpenCartObjects.DESPLEGABLE_CUENTA);
            sleep(1000);
            click(driver,OpenCartObjects.BTN_LOGIN);
            stepPass(driver, msg);
        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarUrl() throws Throwable{
        msg = "Finalmente validamos que nos encontramos en la página inicial";
        try {
            Assert.assertEquals(url,driver.getCurrentUrl());
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            driver.quit();
        } catch (IOException we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo al validar URL final : " + we.getMessage());
            generateWord.sendText("Fallo al validar URL final");
            generateWord.addImageToWord(driver);
        }
    }
}

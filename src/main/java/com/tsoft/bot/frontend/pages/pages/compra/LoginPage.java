package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.OpenCartObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.List;

public class LoginPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String msg;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }

    public void iniciarSesion(String casoPrueba) throws Throwable {
        msg = "Se ingresan credenciales registradas y se inicia sesión";
        try {
            int contadorPagina = Integer.parseInt(casoPrueba) - 1;
            String correo = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_USER);
            String contrasena = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_PASSWORD);

            typeText(driver, OpenCartObjects.ENTRADA_CORREOSESION, correo);
            typeText(driver, OpenCartObjects.ENTRADA_CONTRASENASESION, contrasena);

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            click(driver, OpenCartObjects.BTN_INICIARSESION);

        }catch (Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al iniciar sesión: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }

    }
}

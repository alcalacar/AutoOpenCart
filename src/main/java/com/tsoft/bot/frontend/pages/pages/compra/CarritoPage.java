package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.OpenCartObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class CarritoPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String msg;
    public CarritoPage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }
    public void ingresarCarrito() throws Throwable{
        msg = "Ingresamos al carrito";
        try{
            click(driver, OpenCartObjects.BTN_CARRITO);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al ingresar al carrito: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarPrecios() throws Throwable{
        msg = "Validamos la suma de precios";
        try{
            double suma = 0.00;

            List<WebElement> filas = driver.findElements(OpenCartObjects.FILA_PRECIOCARRITO);
            for (int i =0;i<filas.size();i++)
            {
                String precioFila= driver.findElement(By.cssSelector("form tbody tr:nth-child(" + (i+1) + ") td:last-child")).getText();
                double precio = formatoDouble(precioFila);
                suma += precio;
            }

            String total = getText(driver,OpenCartObjects.TXT_TOTALCARRITO);
            Assert.assertEquals(formatoDouble(total), suma);
            scrollElement(driver,OpenCartObjects.BTN_COMPRAR);

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);

            click(driver,OpenCartObjects.BTN_COMPRAR);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al verificar la suma de precios en el carrito: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }


}

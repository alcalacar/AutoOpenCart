package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.OpenCartObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.aspectj.apache.bcel.classfile.Module;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CheckoutPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String msg;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }
    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }
    public void ingresarDireccion(String casoPrueba) throws Throwable{
        msg = "Ingresamos una nueva dirección de cobro";

        try{
            int contadorPagina = Integer.parseInt(casoPrueba) - 1;
            String nombre = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_NAME);
            String apellido = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_LASTNAME);
            String direccion = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_ADRESS);
            String ciudad = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_CITY);
            String provincia = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_STATE);
            String pais = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_COUNTRY);
            String codpostal = getData().get(contadorPagina).get(ExcelDataObjects.COLUMN_POSTALCODE);

            click(driver,OpenCartObjects.RADIO_NUEVADIRECCION);
            typeText(driver, OpenCartObjects.ENTRADA_NOMBRE,nombre);
            typeText(driver, OpenCartObjects.ENTRADA_APELLIDO,apellido);
            typeText(driver, OpenCartObjects.ENTRADA_DIRECCION,direccion);
            typeText(driver, OpenCartObjects.ENTRADA_CIUDAD,ciudad);
            typeText(driver, OpenCartObjects.ENTRADA_CODPOSTAL,codpostal);
            //click(driver,OpenCartObjects.SELECCIONAR_PAIS);
            selectByVisibleText(driver,OpenCartObjects.SELECCIONAR_PAIS,pais);
            sleep(500);
            //click(driver,OpenCartObjects.SELECCIONAR_PROVINCIA);
            selectByVisibleText(driver,OpenCartObjects.SELECCIONAR_PROVINCIA,provincia);


            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);

            click(driver,OpenCartObjects.BTN_DIRECCIONCOBRO);

        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en ingreso de dirección de cobro : " + we.getMessage());
            generateWord.sendText("Fallo en ingreso de dirección de cobro");
            generateWord.addImageToWord(driver);
        }
    }

    public void seleccionarEnvio() throws Throwable{
        msg = "Seleccionamos el tipo de envío";
        try {
            click(driver,OpenCartObjects.RADIO_DIRACTUALENVIO);
            click(driver,OpenCartObjects.BTN_DIRECCIONENVIO);
            sleep(500);
            click(driver,OpenCartObjects.RADIO_TIPOENVIO);

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);

            click(driver,OpenCartObjects.BTN_TIPOENVIO);

            } catch (IOException we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en selección tipo de envio : " + we.getMessage());
            generateWord.sendText("Fallo en selección tipo de envio");
            generateWord.addImageToWord(driver);
        }
    }
    public void seleccionarPago() throws Throwable{
        msg = "Seleccionamos el tipo de pago";
        try {
            click(driver,OpenCartObjects.RADIO_TIPOPAGO);
            click(driver,OpenCartObjects.CHCK_TOS);

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);

            click(driver,OpenCartObjects.BTN_TIPOPAGO);
            sleep(500);
        } catch (IOException we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en selección tipo de envio : " + we.getMessage());
            generateWord.sendText("Fallo en selección tipo de envio");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarPagoEnvio() throws Throwable{
        msg = "Validamos la suma de precios más envío";
        try{
            double suma = 0.00;
            String subtotal = getText(driver,OpenCartObjects.TXT_SUBTOTALCHECKOUT);
            String envio = getText(driver,OpenCartObjects.TXT_ENVIOCHECKOUT);
            String total = getText(driver,OpenCartObjects.TXT_TOTALCHECKOUT);
            List<WebElement> filas = driver.findElements(OpenCartObjects.FILA_PRECIOCHECKOUT);

            for (int i =0;i<filas.size();i++)
            {
                String precioFila= driver.findElement(By.cssSelector("tbody:nth-child(2) tr:nth-child(" + (i+1) + ") > td.text-right:last-child")).getText();
                double precio = formatoDouble(precioFila);
                suma += precio;
            }
            Assert.assertEquals(formatoDouble(subtotal), suma);
            Assert.assertEquals(formatoDouble(total), (suma + formatoDouble(envio)));

            scrollElement(driver,OpenCartObjects.BTN_CONFIRMARORDEN);

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            click(driver,OpenCartObjects.BTN_CONFIRMARORDEN);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al validar la suma de precios mas envio: " + we.getMessage());
            stepFail(driver, "Fallo en la suma de precios mas envio: " + we.getMessage());
            generateWord.sendText("Fallo en la suma de precios mas envio");
            generateWord.addImageToWord(driver);
        }
    }
    public void validarCompraExitosa() throws Throwable{
        msg = "Validamos la compra exitosa y clic a continuar";
        try {

            sleep(5000);
            Assert.assertEquals(getText(driver, OpenCartObjects.TXT_COMPRAEXITOSA),"Your order has been placed!");

            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);

            click(driver,OpenCartObjects.BTN_CONTINUAR);
            sleep(500);
        } catch (IOException we) {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo al validar el exito de la compra : " + we.getMessage());
            generateWord.sendText("Fallo al validar el exito de la compra");
            generateWord.addImageToWord(driver);
        }
    }

}

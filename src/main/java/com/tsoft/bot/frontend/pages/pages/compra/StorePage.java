package com.tsoft.bot.frontend.pages.pages.compra;

import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.OpenCartObjects;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;

public class StorePage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String msg;
    public StorePage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }

    public void agregarLP3065() throws Throwable{

        try {
            ingresarSeccionLaptops();
            msg = "Agregamos la laptop HP LP3065 al carrito";
            scrollElement(driver,OpenCartObjects.IMG_LP3065);
            click(driver,OpenCartObjects.BTN_LP3065);
            sleep(3000);
            click(driver, OpenCartObjects.BTN_ANNADIRCARRITO);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al agregar laptop HP LP3065: " + we.getMessage());
            stepFail(driver, "Fallo al agregar laptop HP LP3065: " + we.getMessage());
            generateWord.sendText("Fallo al agregar laptop HP LP3065:");
            generateWord.addImageToWord(driver);
        }
    }

    public void agregarMacBook() throws Throwable{

        try {
            ingresarSeccionLaptops();
            msg = "Agregamos la laptop MacBook al carrito";
            scrollElement(driver,OpenCartObjects.IMG_MACBOOK);
            click(driver,OpenCartObjects.BTN_MACBOOK);
            sleep(3000);
            click(driver, OpenCartObjects.BTN_ANNADIRCARRITO);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al agregar Laptop MacBook: " + we.getMessage());
            stepFail(driver, "Fallo al agregar Laptop MacBook: " + we.getMessage());
            generateWord.sendText("Fallo al agregar Laptop MacBook:");
            generateWord.addImageToWord(driver);
        }
    }

    public void agregarD300() throws Throwable{

        try {
            ingresarSeccionCameras();
            msg = "Agregamos la cámara Nikon D300 al carrito";
            scrollElement(driver,OpenCartObjects.IMG_D300);
            click(driver,OpenCartObjects.BTN_D300);
            sleep(3000);
            click(driver, OpenCartObjects.BTN_ANNADIRCARRITO);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al agregar cámara Nikon D300: " + we.getMessage());
            stepFail(driver, "Fallo al agregar cámara Nikon D300: " + we.getMessage());
            generateWord.sendText("Fallo al agregar cámara Nikon D300:");
            generateWord.addImageToWord(driver);
        }
    }

    public void ingresarSeccionLaptops() throws Throwable{
        msg = "Ingresamos a la sección de Laptops & Notebooks desde la barra de navegación";
        try{
            click(driver, OpenCartObjects.DESPLEGABLE_LAPTOPS);
            sleep(500);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
            click(driver,OpenCartObjects.BTN_TODOSPRODUCTOS);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al ingresar a la seccion Laptops & Notebooks: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void ingresarSeccionCameras() throws Throwable{
        msg = "Ingresamos a la sección de Cameras desde la barra de navegación";
        try{
            click(driver, OpenCartObjects.BTN_CAMERAS);
            System.out.println(msg);
            stepPass(driver, msg);
            generateWord.sendText(msg);
            generateWord.addImageToWord(driver);
        }catch(Exception we){
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo al ingresar a la sección Cameras: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }
}

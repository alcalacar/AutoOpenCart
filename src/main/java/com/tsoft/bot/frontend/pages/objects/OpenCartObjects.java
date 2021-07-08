package com.tsoft.bot.frontend.pages.objects;

import org.openqa.selenium.By;

public class OpenCartObjects {
    private OpenCartObjects(){
    }
    //Homepage
    public static final By DESPLEGABLE_CUENTA = By.cssSelector("a[title='My Account']");
    public static final By BTN_LOGIN = By.linkText("Login");

    //LoginPage
    public static final By ENTRADA_CORREOSESION = By.id("input-email");
    public static final By ENTRADA_CONTRASENASESION = By.id("input-password");
    public static final By BTN_INICIARSESION = By.cssSelector("input[value='Login']");

    //StorePage
    public static final By BTN_TODOSPRODUCTOS = By.linkText("Show All Laptops & Notebooks");
    public static final By BTN_LP3065 = By.linkText("HP LP3065");
    public static final By BTN_MACBOOK = By.linkText("MacBook");
    public static final By BTN_D300 = By.linkText("Nikon D300");
    public static final By BTN_ANNADIRCARRITO = By.id("button-cart");
    public static final By BTN_CAMERAS = By.linkText("Cameras");
    public static final By DESPLEGABLE_LAPTOPS = By.linkText("Laptops & Notebooks");
    public static final By IMG_LP3065 = By.cssSelector("img[title='HP LP3065']");
    public static final By IMG_D300 = By.cssSelector("img[title='Nikon D300']");
    public static final By IMG_MACBOOK = By.cssSelector("img[title='MacBook']");

    //CarritoPage
    public static final By BTN_CARRITO = By.cssSelector("a[title='Shopping Cart']");
    public static final By BTN_COMPRAR = By.linkText("Checkout");
    public static final By FILA_PRECIOCARRITO = By.cssSelector("form tbody tr td:last-child");
    public static final By TXT_TOTALCARRITO = By.cssSelector("div.row:nth-child(7) tr:last-child td:last-child");


    //CheckoutPage
    //Cobro
    public static final By RADIO_NUEVADIRECCION = By.cssSelector("input[value='new']");
    public static final By ENTRADA_NOMBRE = By.id("input-payment-firstname");
    public static final By ENTRADA_APELLIDO = By.id("input-payment-lastname");
    public static final By ENTRADA_DIRECCION = By.id("input-payment-address-1");
    public static final By ENTRADA_CIUDAD = By.id("input-payment-city");
    public static final By ENTRADA_CODPOSTAL = By.id("input-payment-postcode");
    public static final By SELECCIONAR_PAIS = By.cssSelector("#input-payment-country");
    public static final By SELECCIONAR_PROVINCIA = By.cssSelector("#input-payment-zone");
    public static final By BTN_DIRECCIONCOBRO = By.cssSelector("#button-payment-address");
    //Envio
    public static final By RADIO_DIRACTUALENVIO = By.cssSelector("input[value='existing']");
    public static final By RADIO_TIPOENVIO = By.cssSelector("input[value='flat.flat']");
    public static final By RADIO_TIPOPAGO = By.cssSelector("div.radio input[value='cod']");
    public static final By BTN_DIRECCIONENVIO = By.cssSelector("#button-shipping-address");
    public static final By BTN_TIPOENVIO = By.cssSelector("#button-shipping-method");
    public static final By CHCK_TOS = By.cssSelector("input[name='agree']");
    public static final By BTN_TIPOPAGO = By.cssSelector("#button-payment-method");
    //Validacion Checkout
    public static final By FILA_PRECIOCHECKOUT = By.cssSelector("tbody:nth-child(2) tr > td.text-right:last-child");
    public static final By TXT_SUBTOTALCHECKOUT = By.cssSelector("tfoot tr:nth-child(1) td.text-right:last-child");
    public static final By TXT_ENVIOCHECKOUT = By.cssSelector("tfoot tr:nth-child(2) td.text-right:last-child");
    public static final By TXT_TOTALCHECKOUT = By.cssSelector("tfoot tr:nth-child(3) td.text-right:last-child");
    public static final By BTN_CONFIRMARORDEN = By.id("button-confirm");
    //Validacion Compra
    public static final By TXT_COMPRAEXITOSA = By.cssSelector("div#content h1");
    public static final By BTN_CONTINUAR = By.linkText("Continue");



}

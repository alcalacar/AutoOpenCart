package com.tsoft.bot.frontend.steps.compra;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.compra.*;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import org.openqa.selenium.WebDriver;

public class CompraSteps {
    private WebDriver driver;
    private final HomePage homePage = new HomePage(driver);
    private final LoginPage loginPage = new LoginPage(driver);
    private final StorePage storePage = new StorePage(driver);
    private final CarritoPage carritoPage = new CarritoPage(driver);
    private final CheckoutPage checkoutPage = new CheckoutPage(driver);

    public CompraSteps() {
        this.driver = Hook.getDriver();
    }

    @Dada("^La pagina principal de OpenCart \"([^\"]*)\"$")
    public void laPaginaPrincipalDeOpenCart(String casoPrueba) throws Throwable {
        homePage.abrirUrl(casoPrueba);
    }

    @Cuando("^Iniciamos sesion \"([^\"]*)\"$")
    public void iniciamosSesion(String casoPrueba) throws Throwable {
        loginPage.iniciarSesion(casoPrueba);
    }

    @Y("^Agregamos tres productos al carrito$")
    public void agregamosTresProductosAlCarrito() throws Throwable {
        storePage.agregarLP3065();
        storePage.agregarMacBook();
        storePage.agregarD300();
    }

    @Y("^Validamos el costo del carrito$")
    public void validamosElCostoDelCarrito() throws Throwable {
        carritoPage.ingresarCarrito();
        carritoPage.validarPrecios();
    }

    @Y("^Realizamos la compra \"([^\"]*)\"$")
    public void realizamosLaCompra(String casoPrueba) throws Throwable {
        checkoutPage.ingresarDireccion(casoPrueba);
        checkoutPage.seleccionarEnvio();
        checkoutPage.seleccionarPago();
        checkoutPage.validarPagoEnvio();
    }

    @Entonces("^Validamos el exito de la compra$")
    public void validamosElExitoDeLaCompra() throws Throwable {
        checkoutPage.validarCompraExitosa();
    }

    @Y("^regresamos a la pagina principal de OpenCart$")
    public void regresamosALaPaginaPrincipalDeOpenCart() throws Throwable {
        homePage.validarUrl();
    }
}

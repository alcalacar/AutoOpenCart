# language: es
  Característica: Compra en OpenCart

    @Compra
    Esquema del escenario: Compra realizada correctamente
      Dada La pagina principal de OpenCart "<caso_prueba>"
      Cuando Iniciamos sesion "<caso_prueba>"
      Y Agregamos tres productos al carrito
      Y Validamos el costo del carrito
      Y Realizamos la compra "<caso_prueba>"
      Entonces Validamos el exito de la compra
      Y regresamos a la pagina principal de OpenCart

      Ejemplos:
        | caso_prueba |
        | 1           |


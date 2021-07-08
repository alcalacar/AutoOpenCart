Feature: Login MCSS

  @Login
  Scenario Outline: Login MCSS
    Given Usuario ingresa url selecciona Dealer User y dealer login"<caso_prueba>"
    When Usuario ingresa usuario y contrasena "<caso_prueba>"
    Then Se valida el inicio de sesion
    Examples:
      | caso_prueba |
      |           1 |
package br.unifor.frontendcalclogica.domain.model

/**
 * Modelo de uma tecla do teclado da calculadora.
 *      label = Texto exibido na tecla.
        type = Tipo (controla cor e comportamento).
        span = Quantas colunas a tecla ocupa no grid.
 */
data class CalcKey(
    val label: String,
    val type: KeyType = KeyType.NORMAL,
    val span: Int = 1
)

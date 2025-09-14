package br.unifor.frontendcalclogica.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.unifor.frontendcalclogica.domain.model.CalcKey
import br.unifor.frontendcalclogica.domain.model.KeyType
import br.unifor.frontendcalclogica.domain.validation.ExpressionValidator
import br.unifor.frontendcalclogica.ui.theme.components.CalculatorKey

/**
 * Tela principal da calculadora
 */
@Composable
fun MainMenuScreen(
    onGoToSolution: () -> Unit
) {
    var expression by remember { mutableStateOf("(A→B) ∧ (B→A)") }

    // Dica: Se for usar a borda vermelha, altere showError para true quando desejar
    var showError by remember { mutableStateOf(false) }

    val bgTop = Color(0xFF0B1936)
    val bgBottom = Color(0xFF183F87)
    val panelBorder = Color(0xFF1C3E7C)
    val panelBg = Color(0xFF000000)
    val visor = Color(0xFF0B0F14)

    val keyWhite = Color(0xFFF5F5F5)
    val keyGray = Color(0xFF5A5F67)
    val green = Color(0xFF1DB954)
    val red = Color(0xFFC62828)
    val orange = Color(0xFFF59E0B)

    val topOffset = 105.dp
    val gapVisorPainel = 50.dp

    val keys = listOf(
        // Linha 1 (operadores + V)
        CalcKey("(", KeyType.OP), CalcKey(")", KeyType.OP),
        CalcKey("→", KeyType.OP), CalcKey("↔", KeyType.OP),
        CalcKey("V", KeyType.TRUE_CONST),

        // Linha 2 (operadores + F)
        CalcKey("¬", KeyType.OP), CalcKey("∧", KeyType.OP),
        CalcKey("∨", KeyType.OP), CalcKey("⊻", KeyType.OP),
        CalcKey("F", KeyType.FALSE_CONST),

        // Letras (brancas)
        CalcKey("A"), CalcKey("B"), CalcKey("C"), CalcKey("D"), CalcKey("E"),
        CalcKey("F"), CalcKey("G"), CalcKey("H"), CalcKey("I"), CalcKey("J"),
        CalcKey("K"), CalcKey("L"), CalcKey("M"), CalcKey("N"), CalcKey("O"),
        CalcKey("P"), CalcKey("Q"), CalcKey("R"), CalcKey("S"), CalcKey("T"),
        CalcKey("U"), CalcKey("V"), CalcKey("W"), CalcKey("X"), CalcKey("Y"),

        // Última linha
        CalcKey("Z"),
        CalcKey("C", KeyType.CLEAR),
        CalcKey("⌫", KeyType.BACKSPACE),
        CalcKey("=", KeyType.EQUALS, span = 2)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(bgTop, bgBottom)))
            .padding(horizontal = 16.dp, vertical = topOffset)
    ) {
        // VISOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp), clip = false)
                .clip(RoundedCornerShape(12.dp))
                .background(visor)
                .then(
                    if (showError) Modifier.border(
                        BorderStroke(2.dp, Color(0xFFFF4D4D)),
                        RoundedCornerShape(12.dp)
                    ) else Modifier
                )
                .padding(horizontal = 16.dp, vertical = 2.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = expression,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }

        Spacer(Modifier.height(gapVisorPainel))

        // PAINEL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .shadow(8.dp, RoundedCornerShape(10.dp), clip = false)
                .clip(RoundedCornerShape(10.dp))
                .border(3.dp, panelBorder, RoundedCornerShape(10.dp))
                .background(panelBg)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                items(
                    items = keys,
                    span = { item -> GridItemSpan(item.span) }
                ) { key ->
                    val bg = when (key.type) {
                        KeyType.OP -> keyGray
                        KeyType.TRUE_CONST, KeyType.EQUALS -> green
                        KeyType.FALSE_CONST, KeyType.BACKSPACE -> red
                        KeyType.CLEAR, KeyType.ACTION_ORANGE -> orange
                        else -> keyWhite
                    }
                    val fg =
                        if (key.type in listOf(
                                KeyType.TRUE_CONST,
                                KeyType.FALSE_CONST,
                                KeyType.CLEAR,
                                KeyType.BACKSPACE,
                                KeyType.EQUALS,
                                KeyType.ACTION_ORANGE
                            )
                        ) Color.White else Color.Black

                    CalculatorKey(
                        label = key.label,
                        bg = bg,
                        fg = fg,
                        onClick = {
                            when (key.type) {
                                KeyType.CLEAR -> {
                                    expression = ""
                                    showError = false
                                }
                                KeyType.BACKSPACE -> {
                                    if (expression.isNotEmpty()) {
                                        expression = expression.dropLast(1).trimEnd()
                                    }
                                    showError = false
                                }
                                KeyType.EQUALS -> {
                                    val ok = ExpressionValidator.isExpressionValid(expression)
                                    if (ok) onGoToSolution() else showError = true
                                }
                                else -> {
                                    // Espaça quando já existe conteúdo (estético)
                                    expression += if (expression.isEmpty()) key.label else " ${key.label}"
                                    showError = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(if (key.span == 2) 2f else 1f)
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))
    }
}

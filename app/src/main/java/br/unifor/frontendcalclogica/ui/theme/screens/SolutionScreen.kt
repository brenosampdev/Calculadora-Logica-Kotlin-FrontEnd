package br.unifor.frontendcalclogica.ui.theme.screens

import TruthTable.TruthTableExecutor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import br.unifor.frontendcalclogica.ui.theme.components.TruthTable
import data.ResponseTable
import logicFunctions.Tokenizer

/**
 * Tela de Solução.
 */
@Composable
fun SolutionScreen(expression: String, onBack: () -> Unit) {
    val gradientTop = Color(0xFF0B1936)
    val gradientBottom = Color(0xFF183F87)
    val deepPanel = Color(0xFF0B0F14)

    var isNavigatingBack by remember { mutableStateOf(false) }

    val exprForLib = remember(expression) { normalizeForLib(expression) }

    val calcResult = remember(exprForLib) {
        runCatching {
            val token = Tokenizer.generate(exprForLib)
            TruthTableExecutor.run(token) as ResponseTable
        }
    }
    val table = calcResult.getOrNull()
    val hadError = calcResult.isFailure

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(gradientTop, gradientBottom)))
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(
            onClick = {
                isNavigatingBack = true
                onBack()
            },
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text("⟵ Voltar", color = Color.White, fontSize = 16.sp)
        }

        Text(
            "EXPRESSÃO:",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp), clip = false)
                .clip(RoundedCornerShape(12.dp))
                .background(deepPanel),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = expression.ifBlank { " " },
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            "TABELA VERDADE",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(8.dp), clip = false)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(12.dp)
                .wrapContentHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            when {
                table != null -> {
                    TruthTable(
                        table = table,
                        use01 = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                hadError && !isNavigatingBack -> {
                    Text("Não foi possível gerar a tabela.", color = Color.Black)
                }
                else -> {
                    Spacer(Modifier.height(0.dp))
                }
            }
        }
    }
}

private fun normalizeForLib(expr: String): String {
    return expr
        .replace("¬", "~")
        // .replace("∨", "v")   // se quiser forçar ASCII
        // .replace("⊻", "^")
        .trim()
}

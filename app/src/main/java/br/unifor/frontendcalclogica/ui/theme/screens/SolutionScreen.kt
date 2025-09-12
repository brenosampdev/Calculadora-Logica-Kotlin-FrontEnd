package br.unifor.frontendcalclogica.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tela de Solução.
 */
@Composable
fun SolutionScreen(onBack: () -> Unit) {
    val gradientTop = Color(0xFF0B1936)
    val gradientBottom = Color(0xFF183F87)
    val deepPanel = Color(0xFF0B0F14)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(gradientTop, gradientBottom)))
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = onBack, modifier = Modifier.padding(top = 4.dp)) {
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
                "A→B",
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
                .fillMaxWidth() // largura ocupa a tela inteira
                .shadow(8.dp, RoundedCornerShape(8.dp), clip = false)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(12.dp)
                .wrapContentHeight(), // altura se ajusta ao texto
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .wrapContentWidth(), // largura se ajusta ao conteúdo
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "V   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F\n" +
                            "V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V\n" +
                            "F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F   V   F\n" +
                            "F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F\n" +
                            "F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F\n" +
                            "F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F\n" +
                            "F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F" ,
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )
            }
        }

    }
}

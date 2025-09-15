package br.unifor.frontendcalclogica.ui.theme.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ResponseTable

@Composable
fun TruthTable(
    table: ResponseTable,
    modifier: Modifier = Modifier,
    use01: Boolean = true
) {
    fun anyToBool(any: Any?): Boolean? = when (any) {
        is Boolean -> any
        is java.lang.Boolean -> any.booleanValue()
        is String -> when (any.trim().uppercase()) {
            "1","T","TRUE","V" -> true
            "0","F","FALSE"    -> false
            else -> null
        }
        else -> null
    }
    fun fmt(b: Boolean?): String = when (b) {
        true  -> if (use01) "1" else "V"
        false -> if (use01) "0" else "F"
        null  -> "?"
    }
    fun keyName(key: Any?): String {
        if (key == null) return "?"
        if (key is String) return key
        return try {
            val m = key::class.java.methods.firstOrNull { it.name == "getByName" && it.parameterCount == 0 }
                ?: key::class.java.methods.firstOrNull { it.name == "byName" && it.parameterCount == 0 }
            (m?.invoke(key) as? String) ?: key.toString()
        } catch (_: Exception) {
            key.toString()
        }
    }

    val vars: List<String> = remember(table) {
        val assigns = table.assignments as? List<*> ?: emptyList<Any>()
        assigns
            .filterIsInstance<Map<*, *>>()
            .flatMap { it.keys }
            .map { keyName(it) }
            .distinct()
            .sorted()
    }

    val mono = FontFamily.Monospace
    val cellW = 56.dp
    val resultW = cellW * 2
    val cellPad = 8.dp

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                vars.forEach { v ->
                    Box(
                        modifier = Modifier
                            .width(cellW)
                            .padding(cellPad),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            v,
                            fontFamily = mono,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            maxLines = 1
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(resultW)
                        .padding(cellPad),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Resultado",
                        fontFamily = mono,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1
                    )
                }
            }

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val assigns = (table.assignments as? List<*>)?.reversed() ?: emptyList()
                val results = (table.results as? List<*>)?.reversed() ?: emptyList()
                val rows = maxOf(assigns.size, results.size)

                for (i in 0 until rows) {
                    val row = assigns.getOrNull(i) as? Map<*, *>
                    val res = anyToBool(results.getOrNull(i))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        vars.forEach { varName ->
                            val value = when {
                                row == null -> null
                                row.containsKey(varName) -> anyToBool(row[varName])
                                else -> {
                                    val k = row.keys.firstOrNull { keyName(it) == varName }
                                    anyToBool(row[k])
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .width(cellW)
                                    .padding(cellPad),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    fmt(value),
                                    fontFamily = mono,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(resultW)
                                .padding(cellPad),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                fmt(res),
                                fontFamily = mono,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = if (res == true) MaterialTheme.colorScheme.primary else Color.Black
                            )
                        }
                    }
                }
            }

            val classify = try { table.classify.value } catch (_: Exception) { null }
            if (!classify.isNullOrBlank()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Classificação: $classify",
                    fontFamily = mono,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

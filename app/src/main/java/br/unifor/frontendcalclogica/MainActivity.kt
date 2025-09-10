package br.unifor.frontendcalclogica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.unifor.frontendcalclogica.navigation.AppNavHost
import br.unifor.frontendcalclogica.ui.theme.theme.FrontEndCalcLogicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontEndCalcLogicaTheme {
                AppNavHost() // Tudo
            }
        }
    }
}
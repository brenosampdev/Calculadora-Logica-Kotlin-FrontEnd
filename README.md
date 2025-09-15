# 📐 Calculadora Lógica

Este é um projeto Android desenvolvido em **Kotlin** que implementa uma **calculadora lógica** com interface gráfica.  
O app permite criar e avaliar expressões lógicas, visualizar tabelas-verdade e trabalhar com operações como **negação, conjunção, disjunção, implicação** e **bicondicional**.

---

## ✨ Funcionalidades

- Entrada de expressões lógicas personalizadas  
- Geração automática de **tabela-verdade**  
- Suporte a diferentes operadores lógicos:
  - `¬` → negação
  - `∧` → conjunção (E)
  - `∨` → disjunção (OU)
  - `→` → implicação
  - `↔` → bicondicional
- Alternar entre exibição em **0/1** ou **V/F**  
- Interface feita em **Jetpack Compose**  

---

## 🛠️ Tecnologias usadas

- [Kotlin](https://kotlinlang.org/)  
- [Android Studio](https://developer.android.com/studio)  
- [Jetpack Compose](https://developer.android.com/jetpack/compose)  
- Estrutura de **árvores lógicas** e **RPN (Notação Polonesa Reversa)** para avaliação de expressões  

---

## 🚀 Como rodar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/brenosampdev/Calculadora-Logica-Kotlin-FrontEnd.git
   ```
2. Abra o projeto no **Android Studio**.  
3. Aguarde o **Gradle sync** finalizar.  
4. Rode em um emulador Android ou em um dispositivo físico.  

---

## 📂 Estrutura do projeto

```
Calculadora-Logica-Kotlin-FrontEnd/
 ├── app/                   # Código principal do app Android
 │   ├── java/              # Código em Kotlin
 │   ├── res/               # Recursos (layouts, ícones, cores, strings)
 │   └── AndroidManifest.xml
 ├── build.gradle
 ├── settings.gradle
 └── README.md
```

---

## 📄 Licença

Este projeto é distribuído sob a licença MIT.  
Sinta-se livre para usar, modificar e compartilhar.  

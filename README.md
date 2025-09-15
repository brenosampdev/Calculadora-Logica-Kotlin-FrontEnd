# 🧮 Calculadora Lógica - Frontend (Kotlin + Jetpack Compose)

Este projeto é a **interface gráfica (frontend)** de uma calculadora lógica, desenvolvida em **Kotlin** utilizando **Android Studio** e **Jetpack Compose**.  

⚠️ **Importante:** aqui está apenas o **frontend** — a parte visual e de navegação.  
A lógica completa de avaliação de expressões será implementada em outra etapa do projeto.

---

## 🎨 Design no Figma

O layout original da interface foi criado no Figma:  
👉 [Acessar o design no Figma](https://www.figma.com/design/qyOJ1C2QeQYZF7m2MfWWRr/Figma-CalcLógica?node-id=2-15&t=ilxKOfR9YebuSF2m-0)

O objetivo foi replicar fielmente o design do Figma, respeitando:
- **Paleta de cores** (gradientes, botões verdes/vermelhos/laranjas);
- **Tipografia**;
- **Organização dos botões** (operadores, constantes lógicas, letras A-Z);
- **Experiência de uso** semelhante a uma calculadora real.

---

## ⚙️ Estrutura do Projeto

A estrutura foi organizada em camadas para manter clareza e boas práticas:

```
app/src/main/java/br/unifor/frontendcalclogica/
├─ MainActivity.kt          
├─ navigation/              
├─ domain/                  
│  ├─ model/                
│  └─ validation/           
└─ ui/
   ├─ components/           
   ├─ screens/             
   └─ theme/                
```

---

## 🖥️ Funcionalidades atuais

- **Tela inicial (MainMenuScreen):**
  - Campo (visor) para exibir a expressão lógica digitada.
  - Teclado customizado com:
    - **Operadores lógicos** (¬, ∧, ∨, →, ↔, ↓);
    - **Constantes** (`V` = verdadeiro, `F` = falso);
    - **Letras A-Z** para representar proposições;
    - **Botões especiais**:
      - `C` (limpar);
      - `⌫` (backspace);
      - `=` (validar expressão e navegar para a tela de solução).
  - Validação leve (só checa se parênteses estão balanceados e se a expressão não termina com operador).

- **Tela de solução (SolutionScreen):**
  - Mostra a expressão enviada.
  - Exibe uma tabela verdade **estática** de exemplo (será substituída por geração real no futuro).
  - Botão "⟵ Voltar" para retornar ao menu.

---

## 🚀 Como executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/brenosampdev/Calculadora-Logica-Kotlin-FrontEnd.git
   ```

2. Abra o projeto no **Android Studio**.

3. Execute em um **emulador** ou **dispositivo físico Android**.

---


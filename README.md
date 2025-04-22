
---

# 🧮 Calculator Application

## 📘 Project Overview
This is a Java-based calculator with a user-friendly **JavaFX** interface that supports mathematical expression processing in **infix**, **postfix**, and **prefix** notation. The application enables users to convert, evaluate, and manage expressions interactively or through file input.

---

## ✨ Key Features

- ✅ Infix ↔ Postfix Conversion  
- ✅ Postfix → Prefix Conversion  
- ✅ Expression Evaluation (Infix, Postfix, Prefix)  
- ✅ File Input for Batch Processing  
- ✅ Keyboard Input for Manual Evaluation  
- ✅ Section-based Expression Navigation  

---

## 📁 Project Structure

### 🔧 Core Logic – `Calculator` Class
Handles the core mathematical operations:

| Method | Description |
|--------|-------------|
| `infixToPostfix()` | Converts infix to postfix notation |
| `evaluatePost()`   | Evaluates a postfix expression |
| `evaluateprefix()` | Evaluates a prefix expression |
| `posfixToPrefix()` | Converts postfix to prefix notation |

---

### 🏗️ Supporting Data Structures

- **`Stack`** – Custom stack for parsing and evaluation  
- **`Node`** – Basic building block for stack and list operations  
- **`Cursor`** – Cursor-based linked list (array-simulated pointers)  
- **`Section`** – Manages grouped expressions for batch processing  

---

### 🖥️ User Interface Components

- **`HelloApplication`** – Entry point of the JavaFX application  
- **`HelloController`** – Controller for handling UI interactions (FXML-based)

---

## 🚀 Getting Started

### 📋 Prerequisites
- Java Development Kit (JDK) 11 or higher  
- JavaFX SDK installed and configured  
- Maven (for build and dependency management)

---

### 🛠️ Installation

```bash
# 1. Clone the repository
git clone https://github.com/your-repo/calculator-app.git

# 2. Navigate into the project directory
cd calculator-app

# 3. Build the project
mvn clean install

# 4. Run the application
mvn javafx:run
```

---

## 🎮 How to Use

### 📂 File Input

1. Open the app, go to the **Read** menu → Select **File**
2. Choose an input file (in supported XML-like format)
3. Use **Next** and **Previous** to navigate expression sections

**Input Format Example:**
```xml
<section>
  <infix>
    <equation>2+3*4</equation>
    <equation>5-2/1</equation>
  </infix>
  <postfix>
    <equation>23+4*</equation>
  </postfix>
</section>
```

---

### ⌨️ Keyboard Input

1. Go to the **Read** menu → Select **Keyboard**
2. Choose either **Infix** or **Postfix**
3. Enter your expression
4. Click **Print** to evaluate and display the result

---

## 🧠 Behind the Scenes

### 🧮 Expression Conversion – Shunting Yard Algorithm
Used to convert infix to postfix by:
- Reading tokens left to right
- Managing operator precedence with a stack
- Producing output in postfix format

### ⚙️ Expression Evaluation
- Operands are pushed onto a stack
- Operators pop operands, compute, and push the result back
- Final stack value is the expression result

### 🧵 Cursor-Based Linked List
- Mimics pointer behavior in an array
- Dynamically manages memory allocation and node reuse
- Used for efficient list navigation and section management

---

## 🧪 Troubleshooting

| Issue | Solution |
|-------|----------|
| File load failure | Ensure file format matches the expected XML-like structure |
| Invalid expression | Check for supported operators and valid syntax |
| JavaFX errors | Verify that JavaFX SDK is correctly configured with your IDE |

---

## 🤝 Contributing
Contributions are welcome!  
Submit issues or pull requests to help improve this project.

---

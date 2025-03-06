document.addEventListener("DOMContentLoaded", () => {
    const display = document.getElementById("display");
    const buttons = Array.from(document.getElementsByClassName("btn"));
    const clearButton = document.getElementById("clear");
    const equalsButton = document.getElementById("equals");
  
    // Event listener for button clicks
    buttons.forEach((button) => {
      button.addEventListener("click", (e) => {
        const value = e.target.getAttribute("data-value");
        if (display.value === "Error") {
          display.value = "";
        }
        display.value += value;
      });
    });
  
    // Event listener for clear button
    clearButton.addEventListener("click", () => {
      display.value = "";
    });
  
    // Event listener for equals button
    equalsButton.addEventListener("click", () => {
      try {
        display.value = evaluateExpression(display.value);
      } catch {
        display.value = "Error";
      }
    });
  
    // Function to safely evaluate the expression
    function evaluateExpression(expression) {
      // Allow only numbers, operators, and parentheses
      const safeExpression = expression.replace(/[^0-9+\-*/().]/g, "");
      return eval(safeExpression); // Using eval safely for basic math operations
    }
  });
  
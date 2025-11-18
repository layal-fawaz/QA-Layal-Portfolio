# ☕ Coffee Maker Unit Testing Project

📋 **Overview**  
This project contains **116 comprehensive JUnit 4 unit tests** for the Coffee Maker application.  
As a **QA Tester**, I validated all core classes for **correct functionality, exception handling, and boundary conditions** using **IntelliJ IDEA** and measured code coverage with **JaCoCo**.

---

🎯 **Project Goals**  
✅ Unit tests for all core classes: **Recipe, Inventory, CoffeeMaker, RecipeBook**  
✅ Validate **normal, invalid, and edge-case inputs**  
✅ Achieve **high code coverage** and verify correctness  

---

🧪 **Tested Classes & Focus**  

1. **Recipe**  
- Tested setters: Coffee, Milk, Sugar, Chocolate, Price  
- Checked exception handling for invalid input  
- Validated `equals()`, `hashCode()`, and `toString()`  

2. **Inventory**  
- Added units and validated negative/non-numeric inputs  
- Checked `useIngredients()` and `enoughIngredients()`  
- Verified inventory updates after operations  

3. **CoffeeMaker**  
- Recipe management: add, edit, delete  
- Making coffee: payment validation, inventory decrements, error handling  

4. **RecipeBook**  
- Adding, deleting, editing recipes  
- Validated capacity limit (max 3 recipes)  

---

📊 **Test Statistics (JaCoCo Coverage)**  

| Class        | Lines | % Covered | Branches | % Branch Covered |
|-------------|-------|-----------|---------|----------------|
| Main        | 517   | 0%        | 46      | 0%             |
| Recipe      | 7217  | 97%       | 422     | 85%            |
| Inventory   | 258   | 100%      | 26      | 100%           |
| RecipeBook  | 107   | 100%      | 16      | 100%           |
| CoffeeMaker | 83    | 100%      | 6       | 100%           |

✅ **All 116 tests passed successfully**, ensuring correctness and stability of the application.

---

### ⚙️ How to Run & Generate Coverage Report

1. Download the project from GitHub (either as a ZIP file or clone the repo).  
2. Open a Terminal or PowerShell in the project directory.  
3. Run the following command:  
```bash
./gradlew clean test jacocoTestReport
./build/reports/jacoco/test/html/index.html

🧠 Testing Approach

Valid and invalid input tests

Boundary and edge-case testing (zero, negative values)

Exception handling verification

High code coverage verification using JaCoCo

👤 Author
Layal Alhusseini – QA / Unit Testing

📅 November 2025

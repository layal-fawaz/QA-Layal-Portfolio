# ☕ Coffee Maker Unit Testing Project

## 📋 Overview  
This project contains **77 comprehensive JUnit 4 unit tests** for the Coffee Maker application.  
As a **QA Engineer**, I validated **all core classes** for correct functionality, exception handling, and boundary conditions using **IntelliJ IDEA** and measured code coverage with **JaCoCo**.  

Testing was performed on:  
- **Buggy Version:** The original implementation with intentional bugs.  
- **Golden Version:** Correct implementation with all issues fixed.  

All tests **passed successfully** on both versions, confirming correctness and reliability of the golden version.  

---

## 🎯 Project Goals  
- ✅ Unit tests for all core classes: `Recipe`, `Inventory`, `CoffeeMaker`, `RecipeBook`.  
- ✅ Validate normal, invalid, and edge-case inputs.  
- ✅ Achieve **high code coverage** and verify correctness.  
- ✅ Detect potential bugs in the buggy implementation.  

---

## 🧪 Tested Classes & Focus  

### 1. Recipe  
- Tested setters: Coffee, Milk, Sugar, Chocolate, Price  
- Checked exception handling for invalid input  
- Validated `equals()`, `hashCode()`, and `toString()`  

### 2. Inventory  
- Added units and validated negative/non-numeric inputs  
- Checked `useIngredients()` and `enoughIngredients()`  
- Verified inventory updates after operations  

### 3. CoffeeMaker  
- Recipe management: add, edit, delete  
- Making coffee: payment validation, inventory decrements, error handling  

### 4. RecipeBook  
- Adding, deleting, editing recipes  
- Validated capacity limit (max 3 recipes)  

---

## 📊 Test Statistics (JaCoCo Coverage)  

| Class        | 📝 Instructions | 🔀 Branches | ✅ Status |
|-------------|----------------|------------|-----------|
| CoffeeMaker | 100%           | 80%        | Covered   |
| Inventory   | 100%           | 96%        | Covered   |
| Recipe      | 97%            | 85%        | Covered   |
| RecipeBook  | 100%           | 100%       | Covered   |
| Main        | 0%             | 0%         | Not Tested |

> All **77 tests passed successfully** on both the buggy and golden versions, ensuring that the golden version is fully correct and stable.  

---

## 🧠 Testing Approach  
- ✅ Valid and invalid input tests  
- ✅ Boundary and edge-case testing (zero, negative values)  
- ✅ Exception handling verification  
- ✅ High code coverage verification using **JaCoCo**  

---

## ✅ Results  
- **Tests Passed:** 77/77 ✅  
- **Coverage:** High and comprehensive for all core classes (except Main)  
- **Bugs in Buggy Version:** Verified; all identified issues were resolved in the golden version  

---

## 👤 Author  
**Layal Alhusseini** – QA / Unit Testing  

📅 November 2025

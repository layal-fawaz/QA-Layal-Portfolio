
# 💻 Code Quality Analysis Project

## 📋 Overview
Analyzed and improved code quality for an **E-Learning Platform** using static analysis tools.

---

## 🛠️ Tools Used
- CheckStyle – Style checking tool  
- SpotBugs – Bug detection tool  
- Java – Programming language  

---

## 🚀 What Was Done

### 1️⃣ CheckStyle Analysis – Fixed  more than 5 Style Issues
- **Class Naming:** `elearningPlatform` → `ELearningPlatform`  
- **Line Length:** Split long lines  
- **Magic Number:** Replaced `1000000` with constant `STD_NUM`  
- **Redundant Modifier:** Removed unnecessary `public` on constructor  
- **Indentation:** Changed from 4 spaces to 2 (Google Style)  

### 2️⃣ SpotBugs Analysis – Fixed more than 3 Bugs
- **Bad Exception Handling:** Throw proper exception  
- **Useless Infinite Loop:** Corrected loop condition  
- **Default Encoding Issue:** Added `StandardCharsets.UTF_8`  

### 3️⃣ Code Refactoring – Fixed 4 Code Smells
- **Magic Numbers:** Changed raw numbers to named constants  
- **Unclear Method Name:** Renamed `s()` to meaningful name, improved exception handling  
- **Unused Code:** Removed unused `HashMap<String, String>`  
- **Mixed Responsibilities:** Moved printing logic to `StudentPrinter` class, separated business logic from presentation  

---

## ✅ Results
- All style violations fixed  
- All bugs resolved  
- Code is cleaner and more maintainable  
- Follows Java best practices

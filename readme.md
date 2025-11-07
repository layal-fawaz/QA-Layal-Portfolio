# Student Grade Processor 📊

A Java project for processing student grades with comprehensive testing using JUnit and PIT Mutation Testing.

## 📄 Description

This project processes student grades and calculates various statistics including:
- Total number of students
- Number of passing students
- Average score
- Grade distribution (A, B, C, D, F)
- Bonus points support

## 📁 Project Contents

- `src/` - Source code
- `test/` - JUnit test cases
- `StudentGradeProcessor explanation.pdf` - Detailed explanation of the project and test cases

## 🧪 Testing

The project includes **5 comprehensive test cases** using JUnit framework:

### Test Case 1: Basic Functionality
Tests the complete grade processing workflow and verifies:
- Total student count
- Number of passing students
- Average score calculation (with 0.01 delta for floating-point precision)
- Grade distribution across all categories (A, B, C, D, F)

### Test Case 2: Null Value Handling
Ensures the system correctly ignores null values and only counts valid inputs.

### Test Case 3: Score Capping
Verifies that bonus points don't cause scores to exceed 100.

### Test Case 4: Constructor Validation
Tests edge cases including negative values. This test revealed that the constructor lacks input validation.

### Test Case 5: Additional Coverage
- Tests the `equals()` method
- Validates the `toString()` method

## 🔬 Mutation Testing

PIT (Pitest) mutation testing was applied to ensure high test quality.

**Before applying test coverage:** Some test cases were not covered.

**After improvements:** Additional test cases were added to achieve better coverage.

## 📊 Results

For detailed explanations of each test case, implementation details, and mutation testing results, please refer to the attached PDF: `StudentGradeProcessor explanation.pdf`

## 🛠️ Technologies Used

- Java
- JUnit
- PIT Mutation Testing

## 🚀 How to Run
```bash
# Run tests
mvn test

# Run mutation testing
mvn org.pitest:pitest-maven:mutationCoverage
```

## 📖 Documentation

See `StudentGradeProcessor explanation.pdf` for:
- Detailed test case explanations
- Code implementation
- Mutation testing results (before/after)
- Test coverage analysis

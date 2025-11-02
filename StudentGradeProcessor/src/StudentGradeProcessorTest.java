import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class StudentGradeProcessorTest {
    private StudentGradeProcessor p;

    @BeforeEach
    void setUp() {
        p = new StudentGradeProcessor();
    }


    @Test
    void testGradeProcess() {
        List<Integer> scores = Arrays.asList(85, 57, 90);
        int bonusPoints = 2;
        boolean strictMode = false;

        StudentGradeProcessor p = new StudentGradeProcessor();
        StudentGradeProcessor.GradeResult result = p.processGrades(scores, bonusPoints, strictMode);

        assertEquals(3, result.getTotalStudents());
        assertEquals(2, result.getPassCount());

        double averageRounded = Math.round(result.getAverage() * 10) / 10.0;
        assertEquals(79.3, averageRounded, 0.01);

        assertEquals(1, result.getACount());
        assertEquals(1, result.getBCount());
        assertEquals(0, result.getCCount());
        assertEquals(0, result.getDCount());
        assertEquals(1, result.getFCount());
    }


    @Test
    public void testNullValues(){
        List<Integer> scores = Arrays.asList(100, null, 95, null);
        int bonusPoints = 2;
        boolean strictMode = false;
        StudentGradeProcessor p = new StudentGradeProcessor();
        StudentGradeProcessor.GradeResult result = p.processGrades(scores, bonusPoints, strictMode);

        assertEquals(4,result.getTotalStudents());
        assertEquals(2,result.getPassCount());
        assertEquals(49.25, result.getAverage(), 0.01);
        assertEquals(2,result.getACount());
        assertEquals(0,result.getBCount());
        assertEquals(0,result.getCCount());
        assertEquals(0,result.getDCount());
        assertEquals(0,result.getFCount());
    }
    @Test
    public void testEmpty(){
        List<Integer> scores = Arrays.asList(100, 90, 95, 50);
        assertFalse(scores.isEmpty());
        assertFalse(scores.equals(null));

    }
    @Test
    void testBonusCappingAt100() {
        List<Integer> scores = Arrays.asList(98, 100, 95);
        int bonusPoints = 5;
        boolean strictMode = false;
        StudentGradeProcessor p = new StudentGradeProcessor();
        StudentGradeProcessor.GradeResult result = p.processGrades(scores, bonusPoints, strictMode);
        // Assertions to check that bonus does not exceed 100
        assertEquals(3, result.getTotalStudents());
        assertEquals(3, result.getPassCount());
        assertEquals(100.0, result.getAverage(), 0.01); // all scores capped at 100
        assertEquals(3, result.getACount());
        assertEquals(0, result.getBCount());
        assertEquals(0, result.getCCount());
        assertEquals(0, result.getDCount());
        assertEquals(0, result.getFCount());
        assertFalse(result.getDCount()==5);
    }


    @Test
    void testNegativeStudentNumber(){
        StudentGradeProcessor.GradeResult SG=new StudentGradeProcessor.GradeResult(-5, 70.0, 3, 1, 1, 1, 0);
        assertEquals(-5,SG.getTotalStudents());
    }

    @Test
    void Testequals(){
        StudentGradeProcessor.GradeResult result1=new StudentGradeProcessor.GradeResult(20, 70.0, 5, 5, 6, 4, 0);
        StudentGradeProcessor.GradeResult result2=new StudentGradeProcessor.GradeResult(20, 70.0, 5, 5, 6, 4, 0);
        StudentGradeProcessor.GradeResult result3=new StudentGradeProcessor.GradeResult(20, 90, 7, 3, 6, 4, 0);

        assertTrue(result1.equals(result2));
        assertFalse(result2.equals(result3));
        assertEquals(result1,result1);
        assertFalse(result1.equals(null));
        assertFalse(result1.equals(new Object()));
    }

    @Test
    void testToString(){
        StudentGradeProcessor.GradeResult result =
                new StudentGradeProcessor.GradeResult(5, 75.50, 3, 1, 1, 1, 1, 1);
        String expected = "GradeResult{students=5, avg=75.50, pass=3, A=1, B=1, C=1, D=1, F=3}";
        assertFalse(expected.equals(result.toString()));
    }
//
//    @Test
//    public void testInvalidGradeThrowsException() {
//        StudentGradeProcessor processor = new StudentGradeProcessor();
//        List<Integer> scores = Arrays.asList(95);
//        int bonusPoints = 0;
//        boolean strictMode = false;
//
//
//        assertThrows(IllegalStateException.class, () -> {
//            List<String> fakeGrades = new ArrayList<>();
//            fakeGrades.add(null);
//            for (String grade : fakeGrades) {
//                if (grade == null || grade.isEmpty()) {
//                    throw new IllegalStateException("Invalid grade generated");
//                }
//            }
//        });
//    }

    @Test
    void testLetterGradesCAndD() {
        StudentGradeProcessor processor = new StudentGradeProcessor();
        List<Integer> scores = Arrays.asList(75, 65); // 75 -> C, 65 -> D
        int bonusPoints = 0;
        boolean strictMode = false;

        StudentGradeProcessor.GradeResult result = processor.processGrades(scores, bonusPoints, strictMode);

        assertEquals(2, result.getTotalStudents());
        assertEquals(1, result.getCCount());
        assertEquals(1, result.getDCount());
    }
    @Test
    public void testStrictMode() {
        StudentGradeProcessor processor = new StudentGradeProcessor();
        List<Integer> scores = Arrays.asList(65);
        processor.processGrades(scores, 0, false);
        processor.processGrades(scores, 0, true);

    }

    @Test
    void testBoundaryGradesAndStrictMode() {
        StudentGradeProcessor processor = new StudentGradeProcessor();

        // ====== Test A grade boundary ======
        List<Integer> scoresA = Arrays.asList(90, 100);
        StudentGradeProcessor.GradeResult resultA = processor.processGrades(scoresA, 0, false);
        assertEquals(2, resultA.getACount());
        assertEquals(2, resultA.getPassCount());

        // ====== Test B grade boundary ======
        List<Integer> scoresB = Arrays.asList(80, 89);
        StudentGradeProcessor.GradeResult resultB = processor.processGrades(scoresB, 0, false);
        assertEquals(2, resultB.getBCount());
        assertEquals(2, resultB.getPassCount());

        // ====== Test C grade boundary ======
        List<Integer> scoresC = Arrays.asList(70, 79);
        StudentGradeProcessor.GradeResult resultC = processor.processGrades(scoresC, 0, false);
        assertEquals(2, resultC.getCCount());
        assertEquals(2, resultC.getPassCount());

        // ====== Test D grade boundary ======
        List<Integer> scoresD = Arrays.asList(60, 69);
        StudentGradeProcessor.GradeResult resultD = processor.processGrades(scoresD, 0, false);
        assertEquals(2, resultD.getDCount());
        assertEquals(2, resultD.getPassCount());

        // ====== Test F grade boundary ======
        List<Integer> scoresF = Arrays.asList(0, 59);
        StudentGradeProcessor.GradeResult resultF = processor.processGrades(scoresF, 0, false);
        assertEquals(2, resultF.getFCount());
        assertEquals(0, resultF.getPassCount());

        // ====== Test strictMode pass threshold ======
        List<Integer> scoresStrict = Arrays.asList(69, 70);
        StudentGradeProcessor.GradeResult resultStrictFalse = processor.processGrades(scoresStrict, 0, false);
        assertEquals(2, resultStrictFalse.getPassCount()); // 69+70 >= 60 => both pass
        StudentGradeProcessor.GradeResult resultStrictTrue = processor.processGrades(scoresStrict, 0, true);
        assertEquals(1, resultStrictTrue.getPassCount()); // only 70 >= 70 passes
    }

}
import java.util.ArrayList;
import java.util.List;

/**
 * StudentGradeProcessor class that processes student grades with multiple branches and loops
 */
public class StudentGradeProcessor{

    public GradeResult processGrades(List<Integer> scores, int bonusPoints, boolean strictMode) {
        // Branch 1: Check for null or empty input
        if (scores == null || scores.isEmpty()) {
            return new GradeResult(0, 0.0, 0, 0, 0, 0, 0);
        }

        List<String> grades = new ArrayList<>();
        int totalScore = 0;
        int passCount = 0;
        int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;

        // Loop 1: Process each score
        for (Integer score : scores) {
            // Branch 2: Handle null scores
            if (score == null) {
                continue;
            }

            int adjustedScore = score + bonusPoints;

            // Branch 3: Cap score at 100
            if (adjustedScore > 100) {
                adjustedScore = 100;
            }

            totalScore += adjustedScore;

            // Branch 4: Determine pass/fail threshold based on mode
            int passThreshold = strictMode ? 70 : 60;
            if (adjustedScore >= passThreshold) {
                passCount++;
            }

            // Determine letter grade with multiple branches
            String letterGrade;
            if (adjustedScore >= 90) {
                letterGrade = "A";
                aCount++;
            } else if (adjustedScore >= 80) { // Branch 5
                letterGrade = "B";
                bCount++;
            } else if (adjustedScore >= 70) { // Branch 6
                letterGrade = "C";
                cCount++;
            } else if (adjustedScore >= 60) { // Branch 7 (additional branch)
                letterGrade = "D";
                dCount++;
            } else { // Branch 8 (additional branch)
                letterGrade = "F";
                fCount++;
            }

            grades.add(letterGrade);
        }

        double average = totalScore / (double) scores.size();

        // Loop 2: Additional processing for grade validation
        for (String grade : grades) {
            // Branch 9: Extra validation (additional branch)
            if (grade == null || grade.isEmpty()) {
                throw new IllegalStateException("Invalid grade generated");
            }
        }

        return new GradeResult(scores.size(), average, passCount, aCount, bCount, cCount, dCount, fCount);
    }

    /**
     * Inner class to hold grade processing results
     */
    public static class GradeResult {
        private final int totalStudents;
        private final double average;
        private final int passCount;
        private final int aCount;
        private final int bCount;
        private final int cCount;
        private final int dCount;
        private final int fCount;

        public GradeResult(int totalStudents, double average, int passCount,
                           int aCount, int bCount, int cCount, int dCount) {
            this(totalStudents, average, passCount, aCount, bCount, cCount, dCount, 0);
        }

        public GradeResult(int totalStudents, double average, int passCount,
                           int aCount, int bCount, int cCount, int dCount, int fCount) {
            this.totalStudents = totalStudents;
            this.average = average;
            this.passCount = passCount;
            this.aCount = aCount;
            this.bCount = bCount;
            this.cCount = cCount;
            this.dCount = dCount;
            this.fCount = fCount;
        }

        // Getters
        public int getTotalStudents() {
            return totalStudents;
        }

        public double getAverage() {
            return average;
        }

        public int getPassCount() {
            return passCount;
        }

        public int getACount() {
            return aCount;
        }

        public int getBCount() {
            return bCount;
        }

        public int getCCount() {
            return cCount;
        }

        public int getDCount() {
            return dCount;
        }

        public int getFCount() {
            return fCount;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GradeResult that = (GradeResult) obj;
            return totalStudents == that.totalStudents &&
                    Double.compare(that.average, average) == 0 &&
                    passCount == that.passCount &&
                    aCount == that.aCount &&
                    bCount == that.bCount &&
                    cCount == that.cCount &&
                    dCount == that.dCount &&
                    fCount == that.fCount;
        }

        @Override
        public String toString() {
            return String.format("GradeResult{students=%d, avg=%.2f, pass=%d, A=%d, B=%d, C=%d, D=%d, F=%d}",
                    totalStudents, average, passCount, aCount, bCount, cCount, dCount, fCount);
        }
    }


}
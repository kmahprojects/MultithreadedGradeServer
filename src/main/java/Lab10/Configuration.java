package Lab10;
/**
 * Configuration is an interface that aids in outputting letter grades
 * @author: Kevin Mah
 *  */
public interface Configuration {
    /**
     * variables used to hold the position of grades and to control what input users may use to close the connection
     *  */
    int QUIT = -1;
    int F = 0;
    int D = 1;
    int C_MINUS = 2;
    int C = 3;
    int C_PLUS = 4;
    int B_MINUS = 5;
    int B = 6;
    int B_PLUS = 7;
    int A_MINUS = 8;
    int A = 9;
    int A_PLUS = 10;
    String[] letterGrades = {"F", "D", "C-", "C", "C+", "B-", "B", "B+", "A-", "A", "A+"};
    /**
     * calculateGrade returns a letter grade from a total mark
     * @param x: a letter grade
     * @return: a String representing the letter grade
     *  */
    static String calculateGrade(int x){
        if (x == -1){
            return "QUIT";
        }
        if (x >= 90) return letterGrades[A_PLUS];
        if (x >= 85) return letterGrades[A];
        if (x >= 80) return letterGrades[A_MINUS];
        if (x >= 77) return letterGrades[B_PLUS];
        if (x >= 73) return letterGrades[B];
        if (x >= 70) return letterGrades[B_MINUS];
        if (x >= 67) return letterGrades[C_PLUS];
        if (x >= 63) return letterGrades[C];
        if (x >= 60) return letterGrades[C_MINUS];
        if (x >= 50) return letterGrades[D];
        return letterGrades[F];
    }
}

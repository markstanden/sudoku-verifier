import java.util.ArrayList;
import java.util.List;

public abstract class SampleFormData {
    public static final String VALID_REGULAR = "R0-C0=1&R0-C1=2&R0-C2=3&R0-C3=4&R0-C4=5&R0-C5=6&R0-C6=7&R0-C7=8&R0-C8=9&R1-C0=4&R1-C1=5&R1-C2=6&R1-C3=7&R1-C4=8&R1-C5=9&R1-C6=1&R1-C7=2&R1-C8=3&R2-C0=7&R2-C1=8&R2-C2=9&R2-C3=1&R2-C4=2&R2-C5=3&R2-C6=4&R2-C7=5&R2-C8=6&R3-C0=2&R3-C1=3&R3-C2=4&R3-C3=5&R3-C4=6&R3-C5=7&R3-C6=8&R3-C7=9&R3-C8=1&R4-C0=5&R4-C1=6&R4-C2=7&R4-C3=8&R4-C4=9&R4-C5=1&R4-C6=2&R4-C7=3&R4-C8=4&R5-C0=8&R5-C1=9&R5-C2=1&R5-C3=2&R5-C4=3&R5-C5=4&R5-C6=5&R5-C7=6&R5-C8=7&R6-C0=3&R6-C1=4&R6-C2=5&R6-C3=6&R6-C4=7&R6-C5=8&R6-C6=9&R6-C7=1&R6-C8=2&R7-C0=6&R7-C1=7&R7-C2=8&R7-C3=9&R7-C4=1&R7-C5=2&R7-C6=3&R7-C7=4&R7-C8=5&R8-C0=9&R8-C1=1&R8-C2=2&R8-C3=3&R8-C4=4&R8-C5=5&R8-C6=6&R8-C7=7&R8-C8=8";

    public static String createTestString(int[][] testGrid) {
        List<String> result = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                result.add(String.format("R%d-C%d=%d", row, col, testGrid[row][col]));
            }
        }
        return String.join("&", result);
    }
}
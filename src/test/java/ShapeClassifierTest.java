import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeClassifierTest {

    /**
     * This is an example test 
     */
    @DisplayName("Example Test")
    @Test
    public void example() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        assertNotEquals("Yes", answer);
    }

    @DisplayName("Valid Input: Small Line")
    @Test
    public void testSmallLine() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes: ", answer, "Expected correct classification for a small line.");
    }

    @DisplayName("Valid Input: Large Circle")
    @Test
    public void testLargeCircle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Circle,Large,Yes,200");
        assertEquals("Yes: ", answer, "Expected correct classification for a large circle.");
    }

    @DisplayName("Incorrect Guess: Shape")
    @Test
    public void testIncorrectShapeGuess() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Square,Large,Yes,10,20,10,20");
        assertEquals("No: Suggestion=Rectangle", answer, "Expected incorrect shape guess to suggest Rectangle.");
    }

    @DisplayName("Invalid Input: Missing Dimensions")
    @Test
    public void testMissingDimensions() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Rectangle,Large,Yes");
        assertEquals("ERROR: Invalid input format", answer, "Expected error for missing dimensions.");
    }

    @DisplayName("Edge Case: Minimal Input")
    @Test
    public void testMinimalInput() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Line,Small,Yes,0");
        assertEquals("Yes: ", answer, "Expected correct classification for minimal input (zero-length line).");
    }

    @DisplayName("Invalid Input: Non-Numeric Dimensions")
    @Test
    public void testNonNumericDimensions() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Rectangle,Large,Yes,a,b,c,d");
        assertEquals("ERROR: Invalid dimensions", answer, "Expected error for non-numeric dimensions.");
    }

    @DisplayName("Boundary Case: Small Rectangle")
    @Test
    public void testSmallRectangle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Rectangle,Small,Yes,2,2,2,2");
        assertEquals("Yes: ", answer, "Expected correct classification for small rectangle with perimeter 8.");
    }

    @DisplayName("Special Case: Degenerate Triangle")
    @Test
    public void testDegenerateTriangle() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Triangle,Small,Yes,1,1,3");
        assertEquals("ERROR: Invalid triangle dimensions", answer, "Expected error for degenerate triangle.");
    }

    @DisplayName("Concurrency Testing: Exceed Wrong Guess Limit")
    @Test
    public void testExceedWrongGuessLimit() {
        ShapeClassifier s = new ShapeClassifier();
        s.evaluateGuess("Line,Small,Yes,5"); // Correct guess
        s.evaluateGuess("Rectangle,Large,Yes,10,10"); // Wrong shape
        s.evaluateGuess("Circle,Small,Yes,100"); // Wrong size
        String answer = s.evaluateGuess("Equilateral,Large,Yes,1,1,1"); // Wrong size again
        assertEquals("ERROR: Bad guess limit Exceeded", answer, "Expected error for exceeding wrong guess limit.");
    }
}
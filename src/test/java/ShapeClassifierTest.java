import static org.junit.Assert.*;
import org.junit.Test;

public class ShapeClassifierTest {
    @Test
    public void testValidLine() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes", result);
    }

    @Test
    public void testInvalidShapeGuess() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Hexagon,Small,Yes,5,5");
        assertTrue(result.contains("No: Suggestion=Line"));
    }

    @Test
    public void testBoundaryBelowParams() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Rectangle,Large,Yes,0,0,0,0");
        assertEquals("No", result);
    }

    @Test
    public void testBoundaryAboveParams() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Square,Large,Yes,4096,4096,4096,4096");
        assertEquals("No", result);
    }

    @Test
    public void testWrongSize() {
        ShapeClassifier classifier = new ShapeClassifier();
        String result = classifier.evaluateGuess("Circle,Medium,Yes,5,5");
        assertTrue(result.contains("Wrong Size"));
    }
}

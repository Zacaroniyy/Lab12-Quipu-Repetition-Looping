import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class TestOutputMatching {

    /**
     * This test class checks the output matching based on specified match types.
     */

    // Define a constant list of input-output pairs, their corresponding match types, and test case names
    private static final Map<String, String[]> TEST_CASES = new HashMap<>();
    static {
        // Example test cases:
        // TEST_CASES.put("Test Case 1", new String[]{"MyClass1", "input1", "expected_output1", "Exact"});
        // TEST_CASES.put("Test Case 2", new String[]{"MyClass2", "input2", "expected_output2", "Match"});
        // Add more test cases as needed
    }

    @Test
    void testOutputMatching() {
        /**
         * Test method to check output matching for each test case.
         */
        for (Map.Entry<String, String[]> entry : TEST_CASES.entrySet()) {
            String testCaseName = entry.getKey();
            String className = entry.getValue()[0];
            String input = entry.getValue()[1];
            String expectedOutput = entry.getValue()[2];
            String matchType = entry.getValue()[3];

            // Capture stdout
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            InputStream originalIn = System.in;
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Run the class and capture stdout
            try {
                Class<?> classObj = Class.forName(className);
                classObj.getMethod("main", String[].class).invoke(null, (Object) null);
                String actualOutput = outputStream.toString().trim();

                // Perform the corresponding assertion based on the match type
                switch (matchType) {
                    case "Exact":
                        assertEquals(expectedOutput, actualOutput, "Exact match failed for " + testCaseName);
                        break;
                    case "Match":
                        assertTrue(actualOutput.contains(expectedOutput), "Match failed for " + testCaseName);
                        break;
                    case "Regex":
                        assertTrue(Pattern.matches(expectedOutput, actualOutput), "Regex match failed for " + testCaseName);
                        break;
                    default:
                        fail("Invalid match type for " + testCaseName);
                }
            } catch (Exception e) {
                fail("Error running " + className + " with input: " + input);
            } finally {
                System.setIn(originalIn);
            }
        }
    }
}

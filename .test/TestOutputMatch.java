import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
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
        TEST_CASES.put("Test 4", new String[]{"Main", "999\nN", "Please enter a number between 0 and 999: Hundreds   = 9\nTens       = 9\nOnes       = 9\n\nYour quipu:\n             ___\n              |\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              |\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              |\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              *\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]: ", "match"});
        TEST_CASES.put("Test 5", new String[]{"Main", "4\nN", "Please enter a number between 0 and 999: Hundreds   = 0\nTens       = 0\nOnes       = 4\n\nYour quipu:\n             ___\n              |\n              |\n              |\n              *\n              *\n              *\n              *\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]: ", "match"});
        TEST_CASES.put("Test 2", new String[]{"Main", "25\nN", "Please enter a number between 0 and 999: Hundreds   = 0\nTens       = 2\nOnes       = 5\n\nYour quipu:\n             ___\n              |\n              |\n              *\n              *\n              |\n              *\n              *\n              *\n              *\n              *\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]: ", "match"});
        TEST_CASES.put("Test 1", new String[]{"Main", "0\nN", "Please enter a number between 0 and 999: Hundreds   = 0\nTens       = 0\nOnes       = 0\n\nYour quipu:\n             ___\n              |\n              |\n              |\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]:", "match"});
        TEST_CASES.put("Test 3", new String[]{"Main", "345\nY\n123\nN", "Please enter a number between 0 and 999: Hundreds   = 3\nTens       = 4\nOnes       = 5\n\nYour quipu:\n             ___\n              |\n              *\n              *\n              *\n              |\n              *\n              *\n              *\n              *\n              |\n              *\n              *\n              *\n              *\n              *\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]: Please enter a number between 0 and 999: Hundreds   = 1\nTens       = 2\nOnes       = 3\n\nYour quipu:\n             ___\n              |\n              *\n              |\n              *\n              *\n              |\n              *\n              *\n              *\n              |\n              ‾\n\nWould you like to make another quipu? [Y/N]: ", "match"});
    }

    @Test
    void testOutputMatching() {
        /*
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
                    case "exact":
                        assertEquals(expectedOutput, actualOutput, "Exact match failed for " + testCaseName);
                        break;
                    case "match":
                        assertTrue(actualOutput.contains(expectedOutput), "Match failed for " + testCaseName +
                                "\n" + actualOutput + " does not contain " + expectedOutput);
                        break;
                    case "regex":
                        assertTrue(Pattern.matches(expectedOutput, actualOutput), "Regex match failed for " + testCaseName +
                                "\n" + actualOutput + " does is not matched by pattern " + expectedOutput);
                        break;
                    default:
                        fail("Invalid match type for " + testCaseName);
                }
            } catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e)
            {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e)
            {
                throw new RuntimeException(e);
            } finally {
                System.setIn(originalIn);
            }
        }
    }
}

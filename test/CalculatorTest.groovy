import org.junit.Assert
import org.junit.Test

/**
 * @author Klaus Bayrhammer
 */
class CalculatorTest {
    @Test
    void testEmptyAddEqualsZero() {
        assert Calculator.add("") == 0
    }

    @Test
    void testSingleArgumentAsIs() {
        assert Calculator.add("1") == 1
    }

    @Test
    void testAddTwoArguments() {
        assert Calculator.add("1,2") == 3
    }

    @Test
    void testNewlinesAsDelimiter() {
        assert Calculator.add("1\n2") == 3
    }

    @Test
    void testCustomDelimiter() {
        assert Calculator.add("//x\n1x2") == 3
    }

    @Test
    void testIgnoreNumbersBiggerThanThousand() {
        assert Calculator.add("1,1001") == 1
    }

    @Test
    void testCustomDelimiterWithLengthTwo() {
        assert Calculator.add("//[xx]\n1xx2") == 3
    }

    @Test
    void testMultipleCustomDelimitersWithLengthTwo() {
        assert Calculator.add("//[xx][yy]\n1xx2yy3") == 6
    }

    @Test
    void testExceptionOnNegativeNumbers() {
        try {
            Calculator.add("-1,-2")
            Assert.fail()
        } catch (Exception e) {
            assert e.getMessage().equals("Negatives not supported (-1,-2)")
        }
    }
}

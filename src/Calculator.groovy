/**
 * @author Klaus Bayrhammer
 */
class Calculator {
    def static add(String input) {
        def tokens = tokenize(input)
        def numbers = collectNumbers(tokens)
        validateNegativeValues(numbers)
        numbers.sum()
    }

    private static String[] tokenize(String input) {
        String delimiter = ",|\\n"
        if (input.startsWith("//")) {
            def indexOfEndOfFirstLine = input.indexOf("\n")
            def delimiterTokens = input.substring(2, indexOfEndOfFirstLine).split("\\]\\[")
            delimiter = "[" + delimiterTokens.join("|") + "]"
            input = input.substring(indexOfEndOfFirstLine + 1)
        }
        input.split(delimiter)
    }

    private static ArrayList<Integer> collectNumbers(String[] tokens) {
        tokens.collect { it.isEmpty() ? 0 : Integer.valueOf(it) }.findAll { it <= 1000 }
    }

    private static void validateNegativeValues(ArrayList<Integer> numbers) {
        if (numbers.any { it < 0 }) {
            throw new Exception("Negatives not supported (" + numbers.findAll { it < 0 }.join(",") + ")")
        }
    }
}

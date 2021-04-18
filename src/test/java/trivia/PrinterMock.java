package trivia;

public class PrinterMock extends Printer {

    private String wholePrintedText = "";

    @Override
    public void print(final String text) {
        wholePrintedText += text + "\r\n";
    }

    String flushAndReturnOutput() {
        final String output = wholePrintedText;
        wholePrintedText = "";
        return output;
    }

}

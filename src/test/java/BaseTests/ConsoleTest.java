package BaseTests;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class ConsoleTest {

    private ByteArrayOutputStream console;

    protected String getStrippedConsole() {
        return console.toString().stripTrailing();
    }

    @BeforeEach
    public void setUp() {

        // Redirect the stdout to our PrintStream
        // to monitor console output.
        console = new ByteArrayOutputStream();

        // Characters written to STDOUT get sent to our
        // PrintStream which converts it to bytes and sends
        // to our output stream.
        System.setOut(new PrintStream(console));
    }
}
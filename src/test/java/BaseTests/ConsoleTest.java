package BaseTests;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public abstract class ConsoleTest {

    private ByteArrayOutputStream console;

    protected String getStrippedConsole() {
        return console.toString().stripTrailing();
    }

    protected Stream<String> consoleToStream() {
        return console.toString().lines();
    }

    @BeforeEach
    protected void setUp() {
        /* Redirect the stdout to our print stream
         * to monitor console output. */
        console = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(console);
        System.setOut(ps);
    }
}
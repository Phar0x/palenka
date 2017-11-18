package sk.palenka.main;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final Logger LOG = Logger.getLogger(MainTest.class);

    private Main m = new Main();
    @Test
    void printSomething() {
        Integer x = m.printSomething();
        assertNotNull(x);
        assertEquals(x, new Integer(42));
        LOG.debug("Test succesfull :)");
    }

}

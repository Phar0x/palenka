package sk.palenka.main;


import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.print("Caute buzny!");
        LOG.debug("Test Log");
    }

    public Integer printSomething() {
        System.out.println("printing random shit");
        return 42;
    }
}

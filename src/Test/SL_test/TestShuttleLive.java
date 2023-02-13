package SL_test;

import model.ShuttleLive;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import java.time.LocalDate;

import static org.junit.Assert.*;

class TestShuttleLive {

    static ShuttleLive shuttlelive;

    @BeforeClass
    public static void initTest(){

        shuttlelive=ShuttleLive.getInstance();
    }

    @Test
    public void testInserisciNuovoUtente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovoUtente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",new Date(1999-05-30));
            assertNotNull(shuttlelive.getUtenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }

    }

    @Test
    public void testInserisciNuovoAutista() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovoAutista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",new Date(1999-05-30));
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }

    }
}
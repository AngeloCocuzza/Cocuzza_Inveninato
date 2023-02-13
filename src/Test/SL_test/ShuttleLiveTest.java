package SL_test;

import model.ShuttleLive;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

class ShuttleLiveTest {

    static ShuttleLive shuttlelive;
    @BeforeClass
    void initTest() {
        shuttlelive= ShuttleLive.getInstance();
    }

    @Test
    void testInserisciNuovoUtente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovoUtente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertNotNull(shuttlelive.getUtenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testInserisciNuovoAutista() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovoAutista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456", java.sql.Date.valueOf("1999-05-30"));
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testInserisciPatente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciPatente("1234",shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2018-05-30"),java.sql.Date.valueOf("2028-05-30"),"AM");
            assertNotNull(shuttlelive.getPatenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

}
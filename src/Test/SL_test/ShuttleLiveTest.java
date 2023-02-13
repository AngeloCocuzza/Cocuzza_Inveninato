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
            System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testInserisciVeicolo() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciVeicolo("xy325fj",shuttlelive.getAutistaCorrente().getUsername(), "bmw","x3","nero", Integer.valueOf("6"));
            System.out.println(shuttlelive.getVeicoloCorrente() + "ciao");
            assertNotNull(shuttlelive.getVeicoloCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testInserisciPatente() {    ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {        shuttlelive.inserisciPatente("12345cd",shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2018-05-30"),java.sql.Date.valueOf("2027-05-30"),"AM");
            System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());    }
        catch (Exception e) {        fail("Unexpected exception");    }
    }}
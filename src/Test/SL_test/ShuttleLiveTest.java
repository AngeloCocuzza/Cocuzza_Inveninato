package SL_test;

import model.ShuttleLive;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
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
        try {
            assertNull(shuttlelive.inserisciNuovoUtente("antonio","antonio@hotmail.it","antonio","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            assertNull(shuttlelive.inserisciNuovoUtente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "email o username già in uso");
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
        try {
            assertNull(shuttlelive.inserisciNuovoAutista("antonio","antonio@hotmail.it","antonio","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            assertNull(shuttlelive.inserisciNuovoAutista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "email o username già in uso");
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
        try {
            assertNull(shuttlelive.inserisciVeicolo("xy32g",shuttlelive.getAutistaCorrente().getUsername(), "bmw","x3","nero", Integer.valueOf("6")));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"targa non valida");
        }
        try{assertNull(shuttlelive.inserisciVeicolo("xy325fj",shuttlelive.getAutistaCorrente().getUsername(), "audi","a4","nero", Integer.valueOf("6")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"veicolo già registrato");
        }
    }

    @Test
    void testInserisciPatente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();

        try {
            shuttlelive.inserisciPatente("12345cd",shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2018-05-30"),java.sql.Date.valueOf("2027-05-30"),"AM");
            System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());    }
        catch (Exception e) {
            fail("Unexpected exception");    }
    }
    @Test
    void testLoginAutista() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.loginAutista("antonio@hotmail.it","antonio99");
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertNull(shuttlelive.loginAutista("antonio@hotmail.it","anto"));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "password troppo breve");
        }
        try {

            assertNull(shuttlelive.loginAutista("antoni@hotmail.it","antonio98"));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "autista non trovato");
        }}
        @Test
        void testLoginUtente() {
            ShuttleLive shuttlelive=ShuttleLive.getInstance();
            try {
                shuttlelive.loginUtente("antonio@hotmail.it","antonio99");
                assertNotNull(shuttlelive.getUtenteCorrente());
            } catch (Exception e) {
                fail("Unexpected exception");
            }
            try {
                assertNull(shuttlelive.loginUtente("antonio@hotmail.it","anto"));
                fail("Expected exception");
            } catch (Exception e){
                assertEquals(e.getMessage(), "password troppo breve");
            }
            try {
                assertNull(shuttlelive.loginUtente("antoni@hotmail.it","antonio98"));
                fail("Expected exception");
            } catch (Exception e){
                assertEquals(e.getMessage(), "utente non trovato");
            }
    }

    @Test
    void testInserisciNuovaDisponibilita() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            assertNotNull(shuttlelive.getDisponibilitaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2022-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"data non valida");
        }
        try{
            assertNull(shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"data già occupata");
        }
    }

}

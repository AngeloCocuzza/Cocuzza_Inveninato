package SL_test;

import model.*;
import org.junit.After;
import org.junit.AfterClass;
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
        shuttlelive = ShuttleLive.getInstance();
    }

    @Test
    void testInserisciNuovoUtente() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovoUtente("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
            assertNotNull(shuttlelive.getUtenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertNull(shuttlelive.inserisciNuovoUtente("", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            assertNull(shuttlelive.inserisciNuovoUtente("antonio", "antonio@hotmail.it", "antonio", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            assertNull(shuttlelive.inserisciNuovoUtente("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email o username già in uso");
        }
    }

    @Test
    void testInserisciNuovoAutista() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();

        try {
            shuttlelive.inserisciNuovoAutista("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
            System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertEquals(null, shuttlelive.inserisciNuovoAutista("", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            assertEquals(null, shuttlelive.inserisciNuovoAutista("antonio", "antonio@hotmail.it", "antonio", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            assertEquals(null, shuttlelive.inserisciNuovoAutista("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30")));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "email o username già in uso");
        }
    }

    @Test
    void testInserisciVeicolo() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();
        try {
            Veicolo veicolo = new Veicolo();
            shuttlelive.inserisciVeicolo("xy325fj", "bmw", "x3", "nero", Integer.valueOf("6"), shuttlelive.getAutistaCorrente());
            assertNotNull(shuttlelive.getVeicoloCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Veicolo veicolo = new Veicolo();
            assertEquals(null, shuttlelive.inserisciVeicolo("xy323xg", "", "x3", "nero", Integer.valueOf("6"), shuttlelive.getAutistaCorrente()));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            Veicolo veicolo = new Veicolo();
            assertEquals(null, shuttlelive.inserisciVeicolo("xy323xg", "bmw", "x3", "nero", Integer.valueOf("-6"), shuttlelive.getAutistaCorrente()));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "il numero dei posti non può essere negativo");
        }

        try {
            Veicolo veicolo = new Veicolo();
            assertEquals(null, shuttlelive.inserisciVeicolo("x33xg", "bmw", "x3", "nero", Integer.valueOf("6"), shuttlelive.getAutistaCorrente()));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "targa non valida");
        }
        Veicolo veicolo = new Veicolo();
        try {
            assertEquals(null, shuttlelive.inserisciVeicolo("xy325fj", "audi", "a4", "nero", Integer.valueOf("6"), shuttlelive.getAutistaCorrente()));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "veicolo già registrato");
        }
    }

    @Test
    void testInserisciPatente() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();

        try {
            Patente patente = new Patente();
            shuttlelive.inserisciPatente("12345cd", java.sql.Date.valueOf("2018-05-30"), java.sql.Date.valueOf("2027-05-30"), "AM", shuttlelive.getAutistaCorrente());
            //System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Patente patente = new Patente();
            shuttlelive.inserisciPatente("", java.sql.Date.valueOf("2018-05-30"), java.sql.Date.valueOf("2027-05-30"), "AM", shuttlelive.getAutistaCorrente());

            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
    }

    @Test
    void testLoginUtente() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();

        try {
            shuttlelive.loginUtente("antonio@hotmail.it", "antonio99");
            assertNotNull(shuttlelive.getUtenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            shuttlelive.loginUtente("", "antonio99");
            fail("Unexpected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            assertNull(shuttlelive.loginUtente("antonio@hotmail.it", "anto"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "password troppo breve");
        }
        try {
            assertNull(shuttlelive.loginUtente("antoni@hotmail.it", "antonio98"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "utente non trovato");
        }
    }

    @Test
    void testLoginAutista() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();
        try {
            assertNotNull(shuttlelive.loginAutista("antonio@hotmail.it", "antonio99"));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertNull(shuttlelive.loginAutista("", "antonio99"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            assertNull(shuttlelive.loginAutista("antonio@hotmail.it", "anto"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "password troppo breve");
        }
        try {

            assertNull(shuttlelive.loginAutista("antoni@hotmail.it", "antonio98"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "autista non trovato");
        }
    }

    @Test
    void testInserisciNuovaDisponibilita() {
        ShuttleLive shuttlelive = ShuttleLive.getInstance();
        try {
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            assertNotNull(shuttlelive.getDisponibilitaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), null, LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), java.sql.Date.valueOf("2022-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(), "data non valida");
        }
        try {
            assertEquals(null, shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania"));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "data già occupata");
        }
    }

    @Test
    void testCercaAutistiDisponibili() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            shuttlelive.cercaAutistiDisponibili("Catania","Pisa", java.sql.Date.valueOf("2023-04-04"), LocalTime.parse("16:00:00"));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertNull(shuttlelive.cercaAutistiDisponibili("Catania","Pisa", java.sql.Date.valueOf("2023-02-02"), LocalTime.parse("16:00:00")));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "data non valida");
        }

    }
}


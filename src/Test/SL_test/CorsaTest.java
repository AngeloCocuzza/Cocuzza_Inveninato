package SL_test;

import model.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

class CorsaTest {
    static Corsa corsa;
    static CorseController controller;

    @BeforeClass
    void initTest() {
        controller = CorseController.getInstance();
    }
    @Test
    void testutenteSingoloByName() {
        CorseController controller=CorseController.getInstance();
        System.out.println(controller.utenteSingoloByName("antonio"));

    }

    @Test
    void testSelezionaViaggioProgrammato() {
        CorseController controller=CorseController.getInstance();

        try {
            controller.selezionaViaggioProgrammato("concerto Ligabue", Date.valueOf("2023-04-04"));
            assertNotNull(controller.getViaggicorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }


        try {
            controller.selezionaViaggioProgrammato("", Date.valueOf("2023-04-04"));
            assertNull(controller.getViaggicorrente());
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
        try {
            controller.selezionaViaggioProgrammato("concerto Ligabue", Date.valueOf("2023-01-01"));
            assertNull(controller.getViaggicorrente());
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"data non valida");
        }


    }



    @Test
    void testVeicoloSingoloByName() {
        CorseController controller=CorseController.getInstance();
        System.out.println(controller.veicoloSingoloByName("xy325fj"));

    }

    @Test
    void testInserisciCorsaprogrammata() {
        CorseController controller=CorseController.getInstance();
        Address address = new Address("Catania","Pisa","via","corso",80);
        Veicolo veicolo = new Veicolo("xy325fj","antonio","fiat","fiesta","blu",6);
        ViaggioProgrammato viaggio = new ViaggioProgrammato(1,veicolo,java.sql.Date.valueOf("2023-04-04"),LocalTime.parse("16:00:00"),address,"mole",veicolo.getN_posti());
        Utente utente=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
        controller.inserisciCorsaProgrammata(viaggio,utente);
    }
    @Test
    void testDiminuisciPostiDisponibili() {
        CorseController controller=CorseController.getInstance();
        Address address = new Address("Catania","Pisa","via","corso",80);
        Veicolo veicolo = new Veicolo("xy325fj","antonio","fiat","fiesta","blu",6);
        ViaggioProgrammato viaggio = new ViaggioProgrammato(1,veicolo,java.sql.Date.valueOf("2023-04-04"),LocalTime.parse("16:00:00"),address,"mole",veicolo.getN_posti());
        System.out.println(viaggio.getPostiDisponibili());
        controller.diminuisciPostiDisponibili(viaggio);
        System.out.println(viaggio.getPostiDisponibili());
    }

}
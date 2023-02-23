package SL_test;

import model.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    void testcaricaCorseViaggiByUtente() {
        CorseController controller=CorseController.getInstance();
        Utente user=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
        System.out.println(controller.caricaCorseViaggiByUtente(user));
    }

    @Test
    void testcaricaCorseViaggiByAutista() {
        CorseController controller=CorseController.getInstance();
        Autista autista = new Autista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456", java.sql.Date.valueOf("1999-05-30"));
        System.out.println(controller.caricaCorseViaggiByAutista(autista));
    }

    @Test
    void testcancCorsa() {
        CorseController controller=CorseController.getInstance();
        Utente user=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
        Address indirizzo=new Address("Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
        Corsa corsa=new Corsa(controller.autistaSingoloByName("antonio"),controller.veicoloSingoloByName("xy325fj"), java.sql.Date.valueOf("2022-04-04"), LocalTime.parse("16:00:00"), indirizzo, 300, user);
        controller.cancellaCorsa(corsa,user);
    }

    @Test
    void testcancViaggio() {
        CorseController controller=CorseController.getInstance();
        Utente user=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
        Address indirizzo=new Address("Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
        ViaggioProgrammato viaggio=new ViaggioProgrammato(controller.autistaSingoloByName("antonio"),controller.veicoloSingoloByName("xy325fj"),java.sql.Date.valueOf("2023-04-04"), LocalTime.parse("16:00:00"),indirizzo,10,"concerto Ligabue",50);
        controller.cancellaViaggio(viaggio,user);
    }

    @Test
    void testinserisciRecensione() {
        CorseController controller=CorseController.getInstance();
        Utente user=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
        Address indirizzo=new Address("Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
        CorsaViaggio viaggio=new ViaggioProgrammato(1,controller.autistaSingoloByName("antonio"),controller.veicoloSingoloByName("xy325fj"),java.sql.Date.valueOf("2023-04-04"), LocalTime.parse("16:00:00"),indirizzo,10,"concerto Ligabue",50);
        CorsaViaggio corsa=new Corsa(1,controller.autistaSingoloByName("antonio"),controller.veicoloSingoloByName("xy325fj"), java.sql.Date.valueOf("2022-04-04"), LocalTime.parse("16:00:00"), indirizzo, 300, user);
        try {
            controller.inserisciRecensione(corsa,5,"buono");
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            controller.inserisciRecensione(viaggio,5,"buono");
        } catch (Exception e) {
            fail("Unexpected exception");
        }

        ///errore inserimento
        try {
            controller.inserisciRecensione(corsa,10,"buono");
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"il voto deve essere compreso tra 1 e 5");
        }
        try {
            controller.inserisciRecensione(viaggio,10,"buono");
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"il voto deve essere compreso tra 1 e 5");
        }
        try {
            controller.inserisciRecensione(corsa,10,"");
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
        try {
            controller.inserisciRecensione(viaggio,10,"");
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }

    }

    @Test
    void testselezionaRecensioniAutista() {
        CorseController controller=CorseController.getInstance();
        System.out.println(controller.selezionaRecensioniAutista("antonio"));
    }

    @Test
    void testcreaCorsa() {
        CorseController controller = CorseController.getInstance();
        Utente utente = new Utente("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
        Autista autista = new Autista("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
        Veicolo veicolo = new Veicolo("xy325fj", "bmw", "x3", "nero", Integer.valueOf("50"));
        Corsa corsa;
        try{
            corsa = controller.creaCorsa(veicolo,autista,utente,"Catania","Pisa",java.sql.Date.valueOf("2022-04-04"), LocalTime.parse("16:00:00"),"via Gabriele d'annunzio","via ortdegli ulivi 7");
            assertNotNull(corsa);
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testInserisciCorsa() {
        CorseController controller = CorseController.getInstance();
        Utente utente = new Utente("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
        Autista autista = new Autista("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
        Veicolo veicolo = new Veicolo("xy325fj", "bmw", "x3", "nero", Integer.valueOf("50"));
        Address address = new Address("Catania","Pisa","via","corso",20);
        Corsa corsa = new Corsa(autista,veicolo,java.sql.Date.valueOf("2022-04-04"), LocalTime.parse("16:00:00"),address,utente);
        try {

            assertNotNull(controller.inserisciCorsa(corsa));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        corsa.setData_partenza(null);
        try {
            assertNull(controller.inserisciCorsa(corsa));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }


    }
    @Test
    void testInserisciViaggio() {
        CorseController controller=CorseController.getInstance();
        Autista autista = new Autista("antonio", "antonio@hotmail.it", "antonio99", "antonio", "inveninato", "3288323456", java.sql.Date.valueOf("1999-05-30"));
        Map<String,Veicolo> veicoli = new HashMap<>();

        Veicolo veicolo = new Veicolo("xy325fj", "bmw", "x3", "nero", Integer.valueOf("50"));
        veicoli.put(veicolo.getTarga(),veicolo);
        autista.setVeicoli(veicoli);
        try {
            controller.inserisciViaggio(autista,veicolo.getTarga(),"concerto Ligabue", Float.valueOf(50),LocalTime.parse("16:00:00"),java.sql.Date.valueOf("2023-04-04"), "Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
            assertNotNull(controller.inserisciViaggio(autista,veicolo.getTarga(),"concerto Ligabue", Float.valueOf(50),LocalTime.parse("16:00:00"),java.sql.Date.valueOf("2023-04-04"), "Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            controller.inserisciViaggio(autista,veicolo.getTarga(),"", Float.valueOf(50),LocalTime.parse("16:00:00"),java.sql.Date.valueOf("2023-04-04"), "Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
            assertNull(controller.getViaggioCorrente());
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
    }

    @Test
    void testGetDiscount() {
        CorseController controller=CorseController.getInstance();
        Address address = new Address("Catania","Pisa","via","corso",80);
        Veicolo veicolo = new Veicolo("xy325fj","antonio","fiat","fiesta","blu",6);

        Corsa corsa = new Corsa(veicolo,java.sql.Date.valueOf("2023-04-04"),LocalTime.parse("16:00:00"),address);
        corsa.setPrezzo(corsa.getFee());
    }
}

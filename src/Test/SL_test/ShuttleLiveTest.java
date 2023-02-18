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
        shuttlelive= ShuttleLive.getInstance();
    }

    @Test
    void testInserisciNuovoUtente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            Utente utente=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            shuttlelive.inserisciNuovoUtente(utente);
            assertNotNull(shuttlelive.getUtenteCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Utente utente=new Utente("","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertNull(shuttlelive.inserisciNuovoUtente(utente));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            Utente utente=new Utente("antonio","antonio@hotmail.it","antonio","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertNull(shuttlelive.inserisciNuovoUtente(utente));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            Utente utente=new Utente("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertNull(shuttlelive.inserisciNuovoUtente(utente));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "email o username già in uso");
        }
    }

    @Test
    void testInserisciNuovoAutista() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();

        try {
            Autista autista = new Autista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456", java.sql.Date.valueOf("1999-05-30"));
            shuttlelive.inserisciNuovoAutista(autista);
            System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Autista autista = new Autista("","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertEquals(null, shuttlelive.inserisciNuovoAutista(autista));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "riempire tutti i campi");
        }
        try {
            Autista autista = new Autista("antonio","antonio@hotmail.it","antonio","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertEquals(null, shuttlelive.inserisciNuovoAutista(autista));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "password troppo corta");
        }
        try {
            Autista autista = new Autista("antonio","antonio@hotmail.it","antonio99","antonio","inveninato","3288323456",java.sql.Date.valueOf("1999-05-30"));
            assertEquals(null, shuttlelive.inserisciNuovoAutista(autista));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "email o username già in uso");
        }
    }
    @Test
    void testInserisciVeicolo() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            Veicolo veicolo = new Veicolo("xy325fj",shuttlelive.getAutistaCorrente().getUsername(), "bmw","x3","nero", Integer.valueOf("6"));
            shuttlelive.inserisciVeicolo(veicolo);
            System.out.println(shuttlelive.getVeicoloCorrente() + "ciao");
            assertNotNull(shuttlelive.getVeicoloCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Veicolo veicolo = new Veicolo("xy323xg",shuttlelive.getAutistaCorrente().getUsername(), "","x3","nero", Integer.valueOf("6"));
            assertEquals(null, shuttlelive.inserisciVeicolo(veicolo));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
        try {
            Veicolo veicolo = new Veicolo("xy323xg",shuttlelive.getAutistaCorrente().getUsername(), "bmw","x3","nero", Integer.valueOf("-6"));
            assertEquals(null, shuttlelive.inserisciVeicolo(veicolo));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"il numero dei posti non può essere negativo");
        }

        try {
            Veicolo veicolo = new Veicolo("x33xg",shuttlelive.getAutistaCorrente().getUsername(), "bmw","x3","nero", Integer.valueOf("6"));
            assertEquals(null, shuttlelive.inserisciVeicolo(veicolo));
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"targa non valida");
        }
        Veicolo veicolo = new Veicolo("xy325fj",shuttlelive.getAutistaCorrente().getUsername(), "audi","a4","nero", Integer.valueOf("6"));
        try{
            assertEquals(null, shuttlelive.inserisciVeicolo(veicolo));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"veicolo già registrato");
        }
    }

    @Test
    void testInserisciPatente() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();

        try {
            Patente patente=new Patente("12345cd",shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2018-05-30"),java.sql.Date.valueOf("2027-05-30"),"AM");
            shuttlelive.inserisciPatente(patente);
            //System.out.println(shuttlelive.getAutistaCorrente() + "ciao");
            assertNotNull(shuttlelive.getAutistaCorrente());
        }
        catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Patente patente=new Patente("",shuttlelive.getAutistaCorrente().getUsername(),java.sql.Date.valueOf("2018-05-30"),java.sql.Date.valueOf("2027-05-30"),"AM");
            shuttlelive.inserisciPatente(patente);

            fail("Expected exception");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
    }

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
                shuttlelive.loginUtente("","antonio99");
                fail("Unexpected exception");
            } catch (Exception e) {
                assertEquals(e.getMessage(),"riempire tutti i campi");
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
     void testLoginAutista() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            assertNotNull(shuttlelive.loginAutista("antonio@hotmail.it","antonio99"));
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            assertNull(shuttlelive.loginAutista("","antonio99"));
            fail("Expected exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "riempire tutti i campi");
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
    void testInserisciNuovaDisponibilita() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        try {
            Disponibilita disp = new Disponibilita(java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), disp);
            assertNotNull(shuttlelive.getDisponibilitaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
        try {
            Disponibilita disp = new Disponibilita(null, LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), disp);
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }
        try {
            Disponibilita disp = new Disponibilita(java.sql.Date.valueOf("2022-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(),disp);
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"data non valida");
        }
        try{
            Disponibilita disp = new Disponibilita(java.sql.Date.valueOf("2023-05-05"), LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"), "Catania");
            assertEquals(null, shuttlelive.inserisciNuovaDisponibilita(shuttlelive.getAutistaCorrente(), disp));
            fail("Expected exception");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"data già occupata");
        }
    }
    @Test
    void testInserisciCorsa() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        Address indirizzo=new Address("Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
        Corsa corsa=new Corsa(shuttlelive.getAutistaCorrente(), shuttlelive.getVeicoloCorrente(), java.sql.Date.valueOf("2022-04-04"), LocalTime.parse("16:00:00"), indirizzo, 300, shuttlelive.getUtenteCorrente());

        try {
            shuttlelive.inserisciCorsa(corsa);
            assertNotNull(shuttlelive.getCorsaCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }


        indirizzo.setIndirizzo_destinazione("");
        corsa.setAddress(indirizzo);
        try {
            shuttlelive.inserisciCorsa(corsa);
            assertNull(shuttlelive.getCorsaCorrente());
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
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



@Test
    void testInserisciViaggio() {
        ShuttleLive shuttlelive=ShuttleLive.getInstance();
        Address indirizzo=new Address("Catania","Pisa","via Gabriele d'annunzio","via ortdegli ulivi 7",300);
        ViaggioProgrammato viaggio=new ViaggioProgrammato(shuttlelive.getAutistaCorrente(),shuttlelive.getVeicoloCorrente(),java.sql.Date.valueOf("2023-04-04"), LocalTime.parse("16:00:00"),indirizzo,10,"concerto Ligabue",50);
        try {
            shuttlelive.inserisciViaggio(viaggio);
            assertNotNull(shuttlelive.getViaggioCorrente());
        } catch (Exception e) {
            fail("Unexpected exception");
        }


        indirizzo.setIndirizzo_destinazione("");
        viaggio.setAddress(indirizzo);
        try {
            shuttlelive.inserisciViaggio(viaggio);
            assertNull(shuttlelive.getViaggioCorrente());
            fail("Expected exception");

        } catch (Exception e) {
            assertEquals(e.getMessage(),"riempire tutti i campi");
        }


    }}



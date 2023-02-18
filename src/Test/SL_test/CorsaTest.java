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
    void testGetPrezzo() {
        CorseController controller=CorseController.getInstance();
        Address address = new Address("Catania","Pisa","via","corso",80);

        Corsa corsa = new Corsa(controller.getAutistacorrente().getVeicoli().get("xy325fj"),java.sql.Date.valueOf("2023-04-04"),LocalTime.parse("16:00:00"),address);
        corsa.setPrezzo(corsa.getFee());
        System.out.println(LocalDate.parse("2023-04-04").getDayOfWeek());
        System.out.println(corsa.getPrezzo());
        System.out.println(corsa);
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



}
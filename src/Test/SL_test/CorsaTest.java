package SL_test;

import model.Corsa;
import model.CorseController;
import model.ShuttleLive;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
        Corsa corsa = new Corsa(controller.veicoloSingoloByName("xy325fj"),"Catania","Pisa",java.sql.Date.valueOf("2023-04-04"),"Via","Corso",LocalTime.parse("16:00:00"),80);
        corsa.setPrezzo(corsa.getFee());
        System.out.println(corsa.getPrezzo());
    }
}
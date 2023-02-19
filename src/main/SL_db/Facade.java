package SL_db;

import model.ShuttleLive;

public class Facade {
    public static Facade facade;

    public static Facade getInstance() {
        if(facade == null)
            facade = new Facade();
        else
            System.out.println("Istanza già creata");
        return facade;
    }


}

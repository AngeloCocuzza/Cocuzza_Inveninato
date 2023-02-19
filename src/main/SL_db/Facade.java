package SL_db;

import model.*;
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
    public void salvaPatente(Patente patente) {
        try {
            PatenteDao.getInstance().insertPatente(patente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }
    public List<Autista> caricaAutisti(Patente patente) {
        try {
            PatenteDao.getInstance().insertPatente(patente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Operazione non andata a buon fine");
        }
    }


}

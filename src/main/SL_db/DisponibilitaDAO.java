package SL_db;

import model.Autista;
import model.Disponibilita;
import model.Veicolo;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DisponibilitaDAO {

    public static DisponibilitaDAO dispdao;

    public static DisponibilitaDAO getInstance() {
        if (dispdao == null)
            dispdao = new DisponibilitaDAO();
        else
            System.out.println("Istanza già creata");
        return dispdao;
    }
    public void insertDisponibilita(Disponibilita disp,Autista autista) {
        String sql = "insert into disponibilita values (?,?,?,?,?)";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, autista.getUsername());
                statement.setDate(2, (Date) disp.getGiorno_disponibilita());
                statement.setTime(3, Time.valueOf(disp.getOra_inizio()));
                statement.setTime(4, Time.valueOf(disp.getOra_fine()));
                statement.setString(5, disp.getCitta_partenza());
                statement.executeUpdate();
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Autista> selectAutistiDisponibili(String partenza,Date data_partenza, LocalTime ora) {
        String sql = "select username,email,password,nome,cognome,telefono,datanascita from disponibilita join autisti on disponibilita.autista=autisti.username  where citta_partenza=? AND giorno_disponibilita=? AND TIMEDIFF(?,ora_inizio)>=0 AND TIMEDIFF(?,ora_fine)<=0";
        List<Autista> autistiDisp=new ArrayList<>();

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,partenza );
                statement.setDate(2, (Date) data_partenza);
                statement.setTime(3, Time.valueOf(ora));
                statement.setTime(4, Time.valueOf(ora));

                ResultSet rs=statement.executeQuery();
                while(rs.next()){
                    Autista autista=new Autista(rs.getString("username"), rs.getString("email"),rs.getString("password"),rs.getString("nome") ,rs.getString("cognome"),rs.getString("telefono"),rs.getDate("datanascita")    );
                    autistiDisp.add(autista);
                }
            } else {
                System.out.println("connessione fallita");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return autistiDisp;

    }

    public List<Disponibilita> allDisponibilita() {
        List<Disponibilita> alldisp = new ArrayList<>();
        String sql = "select * from disponibilita";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Disponibilita dispo = new Disponibilita( rs.getDate("giorno_disponibilita"), LocalTime.parse(rs.getString("ora_inizio")), LocalTime.parse(rs.getString("ora_fine")),rs.getString("citta_partenza"));
                    alldisp.add(dispo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alldisp;
    }
    public List<Disponibilita> allDisponibilitaByAutista(String autista) {
        List<Disponibilita> alldisp = new ArrayList<>();
        String sql = "select * from disponibilita where autista=?";

        try {
            Connection conn = DBConnect.getConnection();
            if(conn!=null) {
                System.out.println("connessione con successo");
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,autista);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Disponibilita dispo = new Disponibilita( rs.getDate("giorno_disponibilita"), LocalTime.parse(rs.getString("ora_inizio")), LocalTime.parse(rs.getString("ora_fine")),rs.getString("citta_partenza"));
                    alldisp.add(dispo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alldisp;
    }
}

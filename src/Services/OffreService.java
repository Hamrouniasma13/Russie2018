/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IOffreService;
import Technique.DataSource;
import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OffreService implements IOffreService {
    
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    
    private static List<Offre> list = new ArrayList<>();
    
    
    @Override
    public void createOffre(Offre o) {
        try {
            String req = "INSERT INTO Offre (Titre_offre,Type_offre,Date_alleer,Dateretour) VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, o.getTitre_offre());
            st.setString(2, o.getType_offre());
            st.setString(3,  o.getDate_alleer());
            st.setString(4,  o.getDateretour());
            st.executeUpdate();
            System.out.println("Ajout Offre effectué avec succès");
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Offre> getAll() {
        ArrayList<Offre> listOffre = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Offre");
            while (rs.next()) {
                Offre e =new Offre(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                listOffre.add(e);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOffre;
    }

   
    @Override
    public void updateOffre(Offre o) {
          try {
            String req = "UPDATE `offre` SET `Titre_offre` = ?, `Type_offre`= ?, `Date_alleer`= ?, `Dateretour`= ? where `id`=? ";
            PreparedStatement st = conn.prepareStatement(req);
            
            st.setString(1, o.getTitre_offre());
            st.setString(2, o.getType_offre());
            st.setString(3, o.getDate_alleer());
            st.setString(4, o.getDateretour());
            st.setInt(5, o.getId());
           
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteOffre(int id) {
        try {
            String req = "DELETE from `offre` where `id`=? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
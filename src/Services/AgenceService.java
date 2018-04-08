/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IAgenceService;
import Technique.DataSource;
import entities.Agence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public  class AgenceService implements IAgenceService {
    
    
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    @Override
    public void createAgence(Agence a) {
        try {
            String req = "INSERT INTO Agence (nomAgence,adresseAgence,ContactAgence,chaine,nom_image) VALUES (?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, a.getNomAgence());
            st.setString(2, a.getAdresseAgence());
            st.setInt(3, a.getContactAgence());
            st.setString(4,a.getChaine());
            st.setString(5, a.getNom_image());
            st.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Agence> getAll(String word) {
        ArrayList<Agence> listAgence = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `agence` WHERE  `nomAgence` LIKE '%"+word+"%' ");
            while (rs.next()) {
                listAgence.add(new Agence(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAgence;
    }

    @Override
    public void update(Agence a) {
        try {
            String req = "UPDATE `Agence` SET `nomAgence` = ?, `AdresseAgence`= ?,`ContactAgence`= ?, `chaine`= ?,`nom_image`= ? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, a.getNomAgence());
            st.setString(2, a.getAdresseAgence());
            st.setInt(3, a.getContactAgence());
            st.setString(4, a.getChaine());
            st.setString(5, a.getNom_image());
            st.setInt(6, a.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req= "DELETE FROM `Agence` WHERE `Agence`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

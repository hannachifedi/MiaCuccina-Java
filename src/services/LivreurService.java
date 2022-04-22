/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.MyDB;

/**
 *
 * @author hanna
 */
public class LivreurService implements ILivreur<Livreur>{
    Connection connexion;
    Statement stm;
    

    public LivreurService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterL(Livreur l) throws SQLException {
    String req = "INSERT INTO `livreur` (`nom_liv`, `prenom_liv`, `num_tel_liv`, `Region`, `mat_liv`, `disponibilite_liv`) VALUES ( '"
    + l.getNom_liv()+ "', '" + l.getPrenom_liv()+ "', '" + l.getNum_tel_liv()+ "', '" +l.getRegion()+ "', '" +l.getMat_liv()+ "', '" +l.getDisponibilite_liv()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void ajouterLiv(Livreur l) throws SQLException {
        String req = "INSERT INTO `livreur` (`nom_liv`, `prenom_liv`, `num_tel_liv`, `Region`, `mat_liv`, `disponibilite_liv`) "
                + "VALUES ( ?, ?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, l.getNom_liv());
        ps.setString(2, l.getPrenom_liv());
        ps.setString(3, l.getNum_tel_liv());
        ps.setString(4, l.getRegion());
        ps.setString(5, l.getMat_liv());
        ps.setString(6, l.getDisponibilite_liv());
        
        
        ps.executeUpdate();
    }

    @Override
    public List<Livreur> afficherlivreur() throws SQLException {
     List<Livreur> livreurs = new ArrayList<>();
        String req = "select * from livreur";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livreur l = new Livreur(rst.getInt("id_livreur"),//or rst.getInt(1)
                    rst.getString("nom_liv"),
                    rst.getString("prenom_liv"),
                    rst.getString("num_tel_liv"),
                    rst.getString("Region"),
                    rst.getString("mat_liv"),
                    rst.getString("disponibilite_liv")
            
            );
            livreurs.add(l);
        }
        return livreurs;
    }
    
    @Override
    public ObservableList<Livreur> getAll() throws SQLException {
        ObservableList <Livreur> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `livreur` ";

        try {
            PreparedStatement pst = connexion.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Livreur(rs.getInt("id_livreur"),rs.getString("nom_liv"),rs.getString("prenom_liv"),rs.getString("num_tel_liv"),rs.getString("Region"),rs.getString("mat_liv"),rs.getString("disponibilite_liv")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }
    
    @Override
    public void deleteLivreur(int id_livreur) throws SQLException {
        String requete = "DELETE FROM livreur WHERE id_livreur=" + id_livreur;
        Statement ste = connexion.createStatement();
        ste.executeUpdate(requete);
        System.out.println("Livreur supprime avec succes");
    }

    @Override
    public void modifierL(Livreur l, int id_livreur) throws SQLException {
      String req = "UPDATE livreur SET   nom_liv = ?, prenom_liv = ?,num_tel_liv=?,Region = ?,Mat_liv = ?  where id_livreur = " + id_livreur;
        PreparedStatement pre = connexion.prepareStatement(req);
     
        pre.setString(1, l.getNom_liv());
        pre.setString(2, l.getPrenom_liv());
        pre.setString(3, l.getNum_tel_liv());
        pre.setString(4, l.getRegion());
        pre.setString(5, l.getMat_liv());
       
        pre.executeUpdate();
    }

    
    
    
    }

    
    


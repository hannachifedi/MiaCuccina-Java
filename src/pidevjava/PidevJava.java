/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Livraison;
import entities.Livreur;
import entities.Personne;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.LivraisonService;
import services.LivreurService;
import services.PersonneService;

/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        Livreur l = new Livreur("fedi","hannachi","25459399","tunis","tunis","oui");
//        
//        LivreurService sp = new LivreurService();
//        
//        try {
//            sp.ajouterLiv(l);
//            System.out.println("ajout avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
          
         Livraison livr = new Livraison("test", 0,74,"t");
        
        LivraisonService sp = new LivraisonService();
        
        try {
            sp.ajouterLivrai(livr);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            System.out.println(sp.afficherlivraison());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}

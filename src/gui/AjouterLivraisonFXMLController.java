/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class AjouterLivraisonFXMLController implements Initializable {

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfadresse;
//    @FXML
//    private TextField tfidliv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutLivraison(ActionEvent event) {
        Livraison liv = new Livraison(tfdescription.getText(),
                (Integer.parseInt(tfetat.getText())),
                (tfadresse.getText())
//                (Integer.parseInt(tfidliv.getText()))
                
                        
                 
        );
        LivraisonService ls = new LivraisonService();
        try {
            ls.ajouterLivrai(liv);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Livraison  ajoutée");
            alert.show();
            tfdescription.setText(""); 
            tfetat.setText("");
//            tfidliv.setText("");
            tfadresse.setText("");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}


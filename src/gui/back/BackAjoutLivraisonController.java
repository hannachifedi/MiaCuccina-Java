/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Livraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class BackAjoutLivraisonController implements Initializable {

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfadresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btndashboardd(MouseEvent event) {
    }

    @FXML
    private void btnListeTable(MouseEvent event) {
    }

    @FXML
    private void btnListeEpmlacement(MouseEvent event) {
    }

    @FXML
    private void btnListeReservation(MouseEvent event) {
    }

    @FXML
    private void btnlListeEvent(MouseEvent event) {
    }

    @FXML
    private void btnListePanier(MouseEvent event) {
    }

    @FXML
    private void btnListeCommandes(MouseEvent event) {
    }

    @FXML
    private void btnListecategorie(MouseEvent event) {
    }

    @FXML
    private void btnListeProduit(MouseEvent event) {
    }

    @FXML
    private void btnListeLivreur(MouseEvent event) {
    }

    @FXML
    private void btnListeLivraison(MouseEvent event) {
    }

    @FXML
    private void btnListeUser(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AjoutLivraison(MouseEvent event) {
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

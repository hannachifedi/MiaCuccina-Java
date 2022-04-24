/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Livreur;
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
import javax.swing.JOptionPane;
import services.LivreurService;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class BackAjoutLivreurController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField tlivtel;
    @FXML
    private TextField tregion;
    @FXML
    private TextField tmatricule;
    @FXML
    private TextField tdispo;

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
    private void btnAjouterLivreur(MouseEvent event) {
        if (!(tnom.getText().isEmpty() || tprenom.getText().isEmpty() || tlivtel.getText().isEmpty() || tregion.getText().isEmpty()|| tmatricule.getText().isEmpty()|| tdispo.getText().isEmpty())) {
        Livreur L = new Livreur(tnom.getText(), tprenom.getText(),tlivtel.getText(),tregion.getText(),tmatricule.getText(),tdispo.getText());
        LivreurService ps = new LivreurService();
        try {
            ps.ajouterLiv(L);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Livreur ajoutée");
            alert.show();
            tnom.setText("");
            tprenom.setText("");
            tlivtel.setText("");
            tregion.setText("");
            tmatricule.setText("");
            tdispo.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         else {
             JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
//             System.out.println("Veuillez remplir tous les champs");
         }
    }
    
}

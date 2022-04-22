/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livreur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.LivreurService;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class AjouterLivreurFXMLController implements Initializable {

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
    private void btnAjouterLivreur(ActionEvent event) {
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

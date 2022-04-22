/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personne;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import entities.Livreur;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javax.swing.JOptionPane;
import services.LivreurService;
import services.PersonneService;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class AjouterPFXMLController implements Initializable {

    @FXML
    private Label lusername;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private Label lListeP;
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
    public void SetUsername(String username) {
        lusername.setText(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void BtnAjouterP(ActionEvent event) {
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

    @FXML
    private void AfficherListeLivreurs(ActionEvent event) {
        LivreurService ps=new LivreurService();
        try {
            lListeP.setText(ps.afficherlivreur().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
    
    public boolean verifOnlyWords(String chaine) {
        return chaine.matches("[A-Za-z]+");
    }
    
    public boolean verifOnlyNumbers(String chaine) {
        return chaine.matches("[0-9]+");
    }

}

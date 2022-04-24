/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.back;

import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.LivraisonService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class BackLivraisonFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Etat;
    @FXML
    private TableColumn<?, ?> Adresse;
    @FXML
    private TableColumn<?, ?> id_livraison;
    @FXML
    private Button removeBtn;
    
    ObservableList<Livraison>  livraisonListe = FXCollections.observableArrayList();
    @FXML
    private TableView<Livraison> tableViewlivraison;
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
       removeBtn.setOnMouseClicked(event -> {
                           try{ 
                               Livraison livraison = (Livraison) tableViewlivraison.getSelectionModel().getSelectedItem();
                               String  query = "DELETE FROM livraison WHERE id_livraison  ="+livraison.getId_livraison();
                              Connection connection = MyDB.getInstance().getConnexion();
                              PreparedStatement preparedStatement = connection.prepareStatement(query);
                              preparedStatement.execute();
                           } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
    }    

    @FXML
    private void btndashboardd(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("src/gui/back/AllFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void btnListeProduit(MouseEvent event) {
    }

    @FXML
    private void btnListeCategorie(MouseEvent event) {
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
    private void btnrefresh(MouseEvent event) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            livraisonListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM livraison");
        while(rs.next()){
            livraisonListe.add(new Livraison(rs.getInt("id_livraison"),rs.getString("description_livraison"),rs.getString("Adresse_livraison"),rs.getInt("etat_livraison")));
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            ObservableList<Livraison> livraisons;
        try {
            livraisons = new LivraisonService().getAll();
        
            
            id_livraison.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
            Description.setCellValueFactory(new PropertyValueFactory<>("description_livraison"));
            Etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
//            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse_livraison"));
            tableViewlivraison.setItems(livraisons);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnAjouter(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BackAjoutLivraison.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
//            stage.setTitle("Ajouter produit");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Modifierliv(ActionEvent event) throws SQLException, SQLException {
         LivraisonService sp = new LivraisonService();
        
        
        String description= tfdescription.getText();
        int etat =(Integer.parseInt(tfetat.getText()));
        String adresse = tfadresse.getText();
        
        if (description.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "la description  ne doit pas etre vide");
        }
          else if (adresse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "l adresse  ne doit pas etre vide");
    } 
        else {
              Livraison liv=  new Livraison(description,etat,adresse);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.modifierLiv(liv,1);
 
            JOptionPane.showMessageDialog(null, "livraison modifiée !");
    }
    }
    
}

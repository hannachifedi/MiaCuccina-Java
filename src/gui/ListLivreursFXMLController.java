/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.LivreurService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class ListLivreursFXMLController implements Initializable {

    @FXML
    private TableView<Livreur> tableViewlivreur;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> Prenom;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> Region;
    @FXML
    private TableColumn<?, ?> Matricule;
    @FXML
    private TableColumn<?, ?> Dispo;
    @FXML
    private TableColumn<?, ?> id_livreur;
    @FXML
    private Button removeBtn;
    @FXML
    private Button refreshbtn;
    @FXML
    private Pane banner;
    
    ObservableList<Livreur>  livreurListe = FXCollections.observableArrayList();
    ObservableList<Livreur>  livreur = FXCollections.observableArrayList();
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicReference<Livreur> commandeAtR = new AtomicReference<>(new Livreur());
//        showCommandes();
        

//        updateBtn.setOnAction(e->{
//            Livreur liv = tableViewlivreur.getSelectionModel().getSelectedItem();
//            if(liv != null){
//                commandeAtR.set(liv);
//                tfstate.setText(String.valueOf(liv.getEtatcommande()));
//               /* tfdesc.setText(produit.getDescription());
//                tfref.setText(produit.getRef());
//                tfprice.setText(String.valueOf(produit.getPrice()));
//                tfimage.setText(produit.getImage());
//                tfquantity.setText(String.valueOf(produit.getQuantity()));*/
//            }
//        });
//        setUpdateBtn.setOnAction(e->{
//            commandeService updateSer = new commandeService();
//            try {
//                if(updateSer.update(Integer.parseInt(numCommande.getText()))){
//                    showCommandes();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//
//
//        });
        
        
        removeBtn.setOnMouseClicked(event -> {
                           try{ 
                               Livreur livreur = (Livreur) tableViewlivreur.getSelectionModel().getSelectedItem();
                               String  query = "DELETE FROM livreur WHERE id_livreur  ="+livreur.getId_livreur();
                              Connection connection = MyDB.getInstance().getConnexion();
                              PreparedStatement preparedStatement = connection.prepareStatement(query);
                              preparedStatement.execute();
                           } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });

    }
    
    
    @FXML
    public void showCommandes() {
            
        try {
            ObservableList<Livreur> livreurs = new LivreurService().getAll();
        
            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
            tableViewlivreur.setItems(livreur);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void refreshTable(MouseEvent event) {
        try {
            
           Connection con = MyDB.getInstance().getConnexion();
            livreurListe.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM livreur");
        while(rs.next()){
         livreurListe.add(new Livreur(rs.getInt("id_livreur"),rs.getString("nom_liv"),rs.getString("prenom_liv"),rs.getString("num_tel_liv"),rs.getString("Region"),rs.getString("mat_liv"),rs.getString("disponibilite_liv")));
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            ObservableList<Livreur> livreurs;
        try {
            livreurs = new LivreurService().getAll();
        
            id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom_liv"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_liv"));
            numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel_liv"));
            Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
            Matricule.setCellValueFactory(new PropertyValueFactory<>("mat_liv"));
            Dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_liv"));
            tableViewlivreur.setItems(livreurs);
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void Ajout(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLivreurFXML.fxml"));
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
    private void Modifier(MouseEvent event) throws SQLException {
        LivreurService sp = new LivreurService();
        
        
        String nom= tnom.getText();
        String prenom = tprenom.getText();
        String num_tel_liv = tlivtel.getText();
        String Region =tregion.getText();
        String matricule = tmatricule.getText();
        String dispo = tdispo.getText();
        
        if (nom.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "le nom  ne doit pas etre vide");
        }  else if (prenom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le prenom ne doit pas etre vide");
    }
          else if (num_tel_liv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la numero tel ne doit pas etre vide");
    } 
          else if (Region.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la region ne doit pas etre vide");
    }
          else if (matricule.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la matricule ne doit pas etre vide");
          }
          else if (dispo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "la dispo ne doit pas etre vide");}
        else {
              Livreur l=  new Livreur(nom,prenom,num_tel_liv,Region,matricule,dispo);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.modifierL(l,71);
 
            JOptionPane.showMessageDialog(null, "livreur modifiée !");
    }
    }
    }
    
   

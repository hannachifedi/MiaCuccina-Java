/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
import entities.Livreur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.LivraisonService;
import services.LivreurService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author hanna
 */
public class ListLivraisonsFXMLController implements Initializable {
    
    @FXML
    private TableView<Livraison> tableViewlivraison;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Etat;
    @FXML
    private TableColumn<?, ?> Adresse;
    @FXML
    private Button removeBtn;
    @FXML
    private Button refreshbtn;
    
    ObservableList<Livraison>  livraisonListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> id_livraison;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfidliv;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    AtomicReference<Livraison> commandeAtR = new AtomicReference<>(new Livraison());
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
    
    
    public void showCommandes() {
            
        try {
            ObservableList<Livraison> livraisons = new LivraisonService().getAll();
        
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
    private void refreshTable(MouseEvent event) {
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
    private void Modifierliv(ActionEvent event) {
        LivraisonService sp = new LivraisonService();
        
        
        String description= tfdescription.getText();
//        int etat =tfetat.getText();
        String adresse = tfadresse.getText();
        
        if (description.isEmpty()) { 
            JOptionPane.showMessageDialog(null, "la description  ne doit pas etre vide");
//        }  else if (etat.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "l etat ne doit pas etre vide");
    }
          else if (adresse.isEmpty()) {
            JOptionPane.showMessageDialog(null, "l adresse  ne doit pas etre vide");
    } 
        else {
              Livraison liv=  new Livraison(description,etat,adresse);
            //String s = "select id_produit from produit where nom='"+tfnom.getText()+"'";
            //int a = Integer.parseInt(s);
         sp.modifierLiv(liv,44);
 
            JOptionPane.showMessageDialog(null, "livraison modifiée !");
    }
    }
    }
    
  

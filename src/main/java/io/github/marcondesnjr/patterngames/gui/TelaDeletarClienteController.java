/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.patterngames.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bitbucket.marcondesads.patterngames.api.modelo.Cliente;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaDeletarClienteController implements Initializable {
    @FXML
    private TableColumn<Cliente, String> cpfColumn;
    @FXML
    private TableColumn<Cliente, String> loginColumn;
    @FXML
    private TableColumn<Cliente, String> nomeColumn;
    @FXML
    private TableColumn<Cliente, String> EmailColumn;
    @FXML
    private TableView<Cliente> clienteTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpfColumn.setCellValueFactory(new PropertyValueFactory("cpf"));
        loginColumn.setCellValueFactory(new PropertyValueFactory("login"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory("cpf"));
        clienteTableView.setItems(FXCollections.observableArrayList(LocacaoManager.getClientes()));
    }    

    @FXML
    private void excluirActionHandler(ActionEvent event) {
        Cliente c = clienteTableView.getSelectionModel().getSelectedItem();
        try {
            LocacaoManager.excluirCliente(c.getLogin());
            clienteTableView.getItems().remove(c);
            DialogMessage.showDialog("Cliente removido com sucesso", "Sucesso");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaDeletarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro grave");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

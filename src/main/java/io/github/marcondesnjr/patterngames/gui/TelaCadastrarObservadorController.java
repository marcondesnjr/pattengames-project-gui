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
import javax.security.auth.login.AccountException;
import javax.swing.JOptionPane;
import org.bitbucket.marcondesads.patterngames.api.modelo.Cliente;
import org.bitbucket.marcondesads.patterngames.api.modelo.Jogo;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaCadastrarObservadorController implements Initializable {
    @FXML
    private TableColumn<Cliente, String> cpfColumn;
    @FXML
    private TableColumn<Cliente, String> loginColumn;
    @FXML
    private TableColumn<Jogo, Integer> idColumn;
    @FXML
    private TableColumn<Jogo, String> nomeColumn;
    @FXML
    private TableView<Cliente> clienteTableView;
    @FXML
    private TableView<Jogo> jogoTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpfColumn.setCellValueFactory(new PropertyValueFactory("cpf"));
        loginColumn.setCellValueFactory(new PropertyValueFactory("login"));
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
        clienteTableView.setItems(FXCollections.observableArrayList(LocacaoManager.getClientes()));
        jogoTableView.setItems(FXCollections.observableArrayList(LocacaoManager.getJogos()));
    }    

    @FXML
    private void AdicionarActionHandler(ActionEvent event) {
        Cliente c = clienteTableView.getSelectionModel().getSelectedItem();
        String senha = JOptionPane.showInputDialog("Digite a sua senha");
        Jogo j = jogoTableView.getSelectionModel().getSelectedItem();
        try{
            LocacaoManager.adicionarObservador(c.getLogin(), senha, j);
            String str = new StringBuilder("O cliente ").append(c.getNome())
                    .append(" será avisado quando o jogo ").append(j.getNome()).append(" estiver disponível").toString();
                    
            DialogMessage.showDialog(str, "Sucesso");
        } catch (PersistenceException ex) {
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro");
            Logger.getLogger(TelaCadastrarObservadorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccountException ex) {
            DialogMessage.showDialog("A senha digitada está incorreta", "Erro");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

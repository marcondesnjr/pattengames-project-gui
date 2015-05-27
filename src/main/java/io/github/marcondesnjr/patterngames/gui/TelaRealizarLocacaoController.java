/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.patterngames.gui;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.security.auth.login.AccountException;
import javax.swing.JOptionPane;
import org.bitbucket.marcondesads.patterngames.api.modelo.AlocacaoException;
import org.bitbucket.marcondesads.patterngames.api.modelo.Cliente;
import org.bitbucket.marcondesads.patterngames.api.modelo.EstadoJogoEnum;
import org.bitbucket.marcondesads.patterngames.api.modelo.Jogo;
import org.bitbucket.marcondesads.patterngames.api.modelo.Locacao;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoFactory;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaRealizarLocacaoController implements Initializable {
    @FXML
    private TableColumn<Jogo, Integer> idColumn;
    @FXML
    private TableColumn<Jogo, String> nomeColumn;
    @FXML
    private TableColumn<Cliente, String> loginColumn;
    @FXML
    private Label precoLabel;
    @FXML
    private Label entregaLabel;
    @FXML
    private TableView<Jogo> jogoTableView;
    @FXML
    private TableView<Cliente> clienteTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
        loginColumn.setCellValueFactory(new PropertyValueFactory("login"));
//        jogoTableView.setItems(FXCollections.observableArrayList());
        for(Jogo jg: LocacaoManager.getJogos()){
            if(jg.getEstado() == EstadoJogoEnum.DISPONIVEL)
                jogoTableView.getItems().add(jg);
        }
        clienteTableView.setItems(FXCollections.observableArrayList(LocacaoManager.getClientes()));
        
        jogoTableView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Jogo>() {

            @Override
            public void changed(ObservableValue<? extends Jogo> observable, Jogo oldValue, Jogo newValue) {
                if(clienteTableView.getSelectionModel().getSelectedItem() != null){
                    try {
                        Locacao loc = LocacaoFactory.getNewLocacao(clienteTableView.getSelectionModel().getSelectedItem(),
                                newValue);
                        precoLabel.setText(String.format("%.2f R$", loc.calcularValor()));
                        entregaLabel.setText(loc.calcularEntrega().toString());
                    } catch (AlocacaoException ex) {
                        Logger.getLogger(TelaRealizarLocacaoController.class.getName()).log(Level.SEVERE, null, ex);
                        DialogMessage.showDialog("O jogo já está alocado", "Erro");
                    }
                    
                }
            }
        });// Listener jogoTable View
        
        clienteTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {

            @Override
            public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
                if(jogoTableView.getSelectionModel().getSelectedItem() != null){
                    try {
                        Locacao loc = LocacaoFactory.getNewLocacao(newValue, jogoTableView.getSelectionModel().getSelectedItem());
                        precoLabel.setText(String.format("%.2f R$", loc.calcularValor()));
                        entregaLabel.setText(loc.calcularEntrega().toString());
                    } catch (AlocacaoException ex) {
                        Logger.getLogger(TelaRealizarLocacaoController.class.getName()).log(Level.SEVERE, null, ex);
                        DialogMessage.showDialog("O jogo já está alocado", "Erro");
                    }
                    
                }
            }
        });// Listner clienteTableView
        
        
    }    

    @FXML
    private void finalizarActionHandler(ActionEvent event) {       
        Cliente cli = clienteTableView.getSelectionModel().getSelectedItem();
        Jogo jg = jogoTableView.getSelectionModel().getSelectedItem();
        String senha = JOptionPane.showInputDialog("Digite sua senha");
        try {
            LocacaoManager.realizarLocacao(cli.getLogin(),senha,jg);
            jogoTableView.getItems().remove(jg);
            DialogMessage.showDialog("Locação realizada com sucesso", "Sucesso");
        } catch (AlocacaoException ex) {
            DialogMessage.showDialog("O jogo não pode ser alocado", "Erro");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaRealizarLocacaoController.class.getName()).log(Level.SEVERE, null, ex);
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro");
        } catch (AccountException ex) {
            DialogMessage.showDialog("Login ou senha incorretos", "Erro");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

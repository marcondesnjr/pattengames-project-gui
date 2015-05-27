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
import org.bitbucket.marcondesads.patterngames.api.modelo.EstadoJogo;
import org.bitbucket.marcondesads.patterngames.api.modelo.Jogo;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaDeletarJogoController implements Initializable {
    @FXML
    private TableColumn<Jogo, Integer> idColumn;
    @FXML
    private TableColumn<Jogo, String> nomeColumn;
    @FXML
    private TableView<Jogo> jogoTableView;
    @FXML
    private TableColumn<Jogo, EstadoJogo> estadoColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nome"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory("estado"));
        jogoTableView.setItems(FXCollections.observableArrayList(LocacaoManager.getJogos()));
    }    

    @FXML
    private void excluirActionHandler(ActionEvent event) {
        Jogo j = jogoTableView.getSelectionModel().getSelectedItem();
        try{
            LocacaoManager.excluirJogo(j.getId());
            jogoTableView.getItems().remove(j);
            DialogMessage.showDialog("Jogo excluído com sucesso!", "Sucesso");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaDeletarJogoController.class.getName()).log(Level.SEVERE, null, ex);
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro grave");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

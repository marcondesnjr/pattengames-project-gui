/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.patterngames.gui;

import io.github.marcondesnjr.patterngames.gui.bean.ObsBean;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.bitbucket.marcondesads.patterngames.api.modelo.Observer;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaDeletarObservadorController implements Initializable {
    @FXML
    private TableColumn<ObsBean, String> loginColumn;
    @FXML
    private TableColumn<ObsBean, String> nomeColumn;
    @FXML
    private TableView<ObsBean> obsTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ObsBean> list = FXCollections.observableArrayList();
        for(Jogo jg: LocacaoManager.getJogos()){
            for(Observer cli: jg.getObservers()){
                ObsBean obs = new ObsBean();
                obs.setNomeJogo(jg.getNome());
                obs.setLoginCli(((Cliente) cli).getLogin());
                list.add(obs);
            }
        }
        loginColumn.setCellValueFactory(new PropertyValueFactory("loginCli"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nomeJogo"));
        obsTableView.setItems(list);
    }    

    @FXML
    private void ExcluirActionHandler(ActionEvent event) {
        ObsBean obs = obsTableView.getSelectionModel().getSelectedItem();
        Jogo jg= null;
        for(Jogo jogo: LocacaoManager.getJogos()){
            if(jogo.getNome().equals(obs.getNomeJogo())){
                jg = jogo;
                break;
            }
        }
        String senha = JOptionPane.showInputDialog("Digite sua senha");
        
        try {
            LocacaoManager.removerObservador(obs.getLoginCli(),senha,jg);
            obsTableView.getItems().remove(obs);
            DialogMessage.showDialog("Observador removido com sucesso", "Sucesso");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaDeletarObservadorController.class.getName()).log(Level.SEVERE, null, ex);
             DialogMessage.showDialog("Ocorreu um erro no sistema","Erro grave");
        } catch (AccountException ex) {
            DialogMessage.showDialog("A senha digitada está incorreta", "Erro");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

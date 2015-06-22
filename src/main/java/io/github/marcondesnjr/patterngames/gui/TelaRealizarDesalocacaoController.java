/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.patterngames.gui;

import io.github.marcondesnjr.patterngames.gui.bean.LocBean;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bitbucket.marcondesads.patterngames.api.modelo.AlocacaoException;
import org.bitbucket.marcondesads.patterngames.api.modelo.Locacao;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaRealizarDesalocacaoController implements Initializable {
    @FXML
    private TableColumn<LocBean, String> loginColumn;
    @FXML
    private TableColumn<LocBean, String> nomeColumn;
    @FXML
    private Label precoLabel;
    @FXML
    private Label multaLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private TableView<LocBean> locTableView;
    @FXML
    private TableColumn<LocBean, Integer> idColumn;
    @FXML
    private TableColumn<LocBean, Integer> idJogoColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory("loginCli"));
        idJogoColumn.setCellValueFactory(new PropertyValueFactory("idJogo"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory("nomeJogo"));
        
        for(Locacao loc: LocacaoManager.getLocacoes()){
            LocBean locB = new LocBean();
            locB.setId(loc.getId());
            locB.setLoginCli(loc.getCliente().getLogin());
            locB.setIdJogo(loc.getJogo().getId());
            locB.setNomeJogo(loc.getJogo().getNome());
            locTableView.getItems().add(locB);
        }
        
        locTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocBean>() {

            @Override
            public void changed(ObservableValue<? extends LocBean> observable, LocBean oldValue, LocBean newValue) {
                Locacao loc = LocacaoManager.localizaLocacao(newValue.getId());
                precoLabel.setText(String.format("%.2f", loc.calcularValor()));
                multaLabel.setText(String.format("%.2f", loc.calcularMulta()));
                totalLabel.setText(String.format("%.2f", loc.calcularValor() + loc.calcularMulta()));
            }
        });
        
    }    

    @FXML
    private void devolverActionHandler(ActionEvent event) {
        LocBean locB = locTableView.getSelectionModel().getSelectedItem();
        try {
            LocacaoManager.realizarDesalocacao(locB.getId());
            locTableView.getItems().remove(locB);
            DialogMessage.showDialog("Entrega realizada com sucesso", "Sucesso");
        } catch (AlocacaoException ex) {
            DialogMessage.showDialog("Este jogo não está alocado", "Sucesso");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaRealizarDesalocacaoController.class.getName()).log(Level.SEVERE, null, ex);
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

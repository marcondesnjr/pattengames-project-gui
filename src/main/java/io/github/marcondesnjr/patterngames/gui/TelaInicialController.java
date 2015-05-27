package io.github.marcondesnjr.patterngames.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TelaInicialController implements Initializable {
    
    private Label label;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cadastrarClienteActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarCliente.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void cadastrarJogoActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarJogos.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void observarActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarObservador.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void deletarClienteActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaDeletarCliente.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void deletarJogoActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaDeletarJogo.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void deletarObservadorActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaDeletarObservador.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void realizarLocActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaRealizarLocacao.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void realizarDesalocActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaRealizarDesalocacao.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }

    @FXML
    private void inicioActionHandler(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/fxml/TelaEntrada.fxml"));
            MainApp.getBPane().setCenter(p);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            Menssagem.erroGrave();
        }
    }
}

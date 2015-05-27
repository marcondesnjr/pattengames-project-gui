package io.github.marcondesnjr.patterngames.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.bitbucket.marcondesads.patterngames.api.modelo.Jogo;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaCadastrarJogosController implements Initializable {
    @FXML
    private TextField nomeTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void concluirActionHandler(ActionEvent event) {
        String nome = nomeTextField.getText();
        try{
            LocacaoManager.cadastrarJogo(new Jogo(nome));
            DialogMessage.showDialog("Jogo cadastrado com sucesso", "Sucesso");
        } catch (PersistenceException ex) {
            Logger.getLogger(TelaCadastrarJogosController.class.getName()).log(Level.SEVERE, null, ex);
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro grave");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

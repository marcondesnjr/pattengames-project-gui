package io.github.marcondesnjr.patterngames.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bitbucket.marcondesads.patterngames.api.modelo.Cliente;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.ClienteInvalido;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.LocacaoManager;
import org.bitbucket.marcondesads.patterngames.api.modelo.controle.PersistenceException;

/**
 * FXML Controller class
 *
 * @author José Marcondes do Nascimento Junior
 */
public class TelaCadastrarClienteController implements Initializable {
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField senhaPassField;
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cpfTextField;
    @FXML
    private TextField emailTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void concluirActionHandler(ActionEvent event) {
        String login = loginTextField.getText();
        String senha = senhaPassField.getText();
        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();       
        try {
            LocacaoManager.cadastrarCliente(new Cliente(login, senha, nome, cpf, email));
            DialogMessage.showDialog("Cliente cadastrado com sucesso", "Sucesso");
        }catch (ClienteInvalido ex) {
            DialogMessage.showDialog(ex.getMessage(), "Cliente inválido");
        }catch(PersistenceException ex){
            DialogMessage.showDialog("Ocorreu um erro no sistema", "Erro");
        }
    }

    @FXML
    private void voltarActionHandler(ActionEvent event) {
    }
    
}

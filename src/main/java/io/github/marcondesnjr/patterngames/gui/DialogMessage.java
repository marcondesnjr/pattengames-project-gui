/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.patterngames.gui;

import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class DialogMessage {
    public static void showDialog(String msg, String titulo){
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public static void showPasswordDialog(String msg, String titulo){
        
    }
}

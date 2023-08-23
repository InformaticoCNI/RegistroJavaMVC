/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Usuarios;
import Models.UsuariosDao;
import Views.FrmLogin;
import Views.PanelAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class LoginControllers implements  ActionListener{
    private Usuarios us ;
    private  UsuariosDao usDao;
    private FrmLogin  views;

    public LoginControllers(Usuarios us, UsuariosDao usDao, FrmLogin views) {
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.BtnLogin.addActionListener(this);
        this.views.BtnCancelar.addActionListener(this);
        this.views.setLocationRelativeTo(null);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== views.BtnLogin) {
            if (views.TxtUsuario.getText().equals("")||String.valueOf(views.TxtClave.getPassword()).equals("") ) {
                JOptionPane.showMessageDialog(null, "Los Campos estan Vacios ");
                
            }else{
                String usuario=views.TxtUsuario.getText();
                String clave=String.valueOf(views.TxtClave.getPassword());
               us= usDao.login(usuario, clave);
                if (us.getUsuario()!=null) {
                    PanelAdmin admin= new PanelAdmin( us.getId(),us.getNombre());
                    admin.setVisible(true);
                    this.views.dispose();
                }else{
                      JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrecta!!☻ ");
                }
            }
        }else{
           int pregunta=   JOptionPane.showConfirmDialog(null, "Estas seguro que desea salir ", "Pregunta", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (pregunta==0) {
                System.exit(0);
                
            }
        }
    }
    
    
    
}

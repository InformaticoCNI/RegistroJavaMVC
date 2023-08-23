/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

/**
 *
 * @author Eliseo
 */
public class ConfigControllers implements MouseInputListener {
private PanelAdmin views;

    public ConfigControllers(PanelAdmin views) {
        this.views = views;
        this.views.JLebelUsuario.addMouseListener(this);
        this.views.JLebelParticipante.addMouseListener(this);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()== views.JLebelUsuario) {
            views.JPanelUsuarios.setBackground(new Color(255,51,51));
        }else if(e.getSource()== views.JLebelUsuario){
             views.JPanelUsuarios.setBackground(new Color(255,51,51));
        }
        ///Fin usuario
         if (e.getSource()== views.JLebelParticipante) {
            views.JPanelHome.setBackground(new Color(255,51,51));
        }else if(e.getSource()== views.JLebelParticipante){
             views.JPanelHome.setBackground(new Color(255,51,51));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
          if (e.getSource()== views.JLebelUsuario) {
            views.JPanelUsuarios.setBackground(new Color(255,255,255));
        }else if(e.getSource()== views.JLebelUsuario){
             views.JPanelUsuarios.setBackground(new Color(255,255,255));
        }
          ///fin de usuario
             if (e.getSource()== views.JLebelParticipante) {
            views.JPanelHome.setBackground(new Color(255,255,255));
        }else if(e.getSource()== views.JLebelParticipante){
             views.JPanelHome.setBackground(new Color(255,255,255));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        
        
    }
    
}

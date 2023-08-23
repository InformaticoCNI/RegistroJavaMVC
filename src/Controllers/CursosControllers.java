/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Cursos;
import Models.CursosDao;
import Models.Tables;
import Models.Usuarios;
import Models.UsuariosDao;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Usuario
 */
public class CursosControllers implements ActionListener, MouseListener ,KeyListener{
    
private Cursos crs;
private CursosDao crsDao;
private PanelAdmin views;
        DefaultTableModel modelo=new DefaultTableModel();
   
    
public void  CursosControllers (Cursos us, CursosDao usDao, PanelAdmin views){
          this.crs = us;
        this.crsDao = usDao;
        this.views = views;
        this.views.setLocationRelativeTo(null);
        this.views.BtnRegistarUser.addActionListener(this);
        this.views.BtnModificarUser.addActionListener(this);
        this.views.BtnNuevoUser.addActionListener(this);
        this.views.JMenuEliminarUsers.addActionListener(this);
        this.views.JMenuReingresarUsers.addActionListener(this);
        this.views.TxtBuscarCur.addKeyListener(this);
        this.views.JLebelUsuario.addMouseListener(this);
        
        this.views.jTableCursos.addMouseListener(this);
        listarCursos();
    
}      
    public void listarCursos (){
        Tables color =new Tables();
        views.jTableCursos.setDefaultRenderer(views.jTableCursos.getColumnClass(0), color);
        List<Cursos> lista=crsDao.ListaCursos(views.TxtBuscarCur.getText());
        modelo=(DefaultTableModel) views.jTableCursos.getModel();
        Object[]ob=new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0]=lista.get(i).getId();
            ob[1]=lista.get(i).getCodigo();
            ob[2]=lista.get(i).getNombre();
            ob[3]=lista.get(i).getFecha();  
            ob[4]=lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.jTableCursos.setModel(modelo);
        JTableHeader header=views.jTableCursos.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }
    
    
    
    
      
      
      
    public void limpiarTable(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
} 

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    if (e.getSource()== views.jTableCursos) {
            int fila=views.jTableCursos.rowAtPoint(e.getPoint());
            views.TxtIdUser.setText(views.TableUsuario.getValueAt(fila, 0).toString());
            views.TxtNombreUser.setText(views.TableUsuario.getValueAt(fila, 1).toString());
            views.TxtUsuarioUser.setText(views.TableUsuario.getValueAt(fila, 2).toString());
            views.CbxRolUser.setSelectedItem(views.TableUsuario.getValueAt(fila, 3).toString());
            views.TxtPasswordUser.setEnabled(false);
            views.BtnRegistarUser.setEnabled(false);
            views.BtnModificarUser.setEnabled(true);
        }else if (e.getSource()==views.JLebelUsuario) {
           views.jTabbedPane1.setSelectedIndex(1);
           
        }
    
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    
    
    
    
}

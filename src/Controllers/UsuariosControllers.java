/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class UsuariosControllers implements ActionListener, MouseListener ,KeyListener{
private Usuarios us;
private UsuariosDao usDao;
private PanelAdmin views;
        DefaultTableModel modelo=new DefaultTableModel();
   
    public UsuariosControllers(Usuarios us, UsuariosDao usDao, PanelAdmin views) {
       ///este es metodo contructor 
        
        this.us = us;
        this.usDao = usDao;
        this.views = views;
        this.views.setLocationRelativeTo(null);
        this.views.BtnRegistarUser.addActionListener(this);
        this.views.BtnModificarUser.addActionListener(this);
        this.views.BtnNuevoUser.addActionListener(this);
        this.views.JMenuEliminarUsers.addActionListener(this);
        this.views.JMenuReingresarUsers.addActionListener(this);
        this.views.TxtBuscarUser.addKeyListener(this);
        this.views.JLebelUsuario.addMouseListener(this);
        
        this.views.TableUsuario.addMouseListener(this);
        listarUsuarios();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== views.BtnRegistarUser) {
            if (views.TxtNombreUser.getText().equals("")||views.TxtUsuarioUser.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "Todos loa campos son obligatorios");
                
            }else   {
                us.setNombre(views.TxtNombreUser.getText());
                us.setUsuario(views.TxtUsuarioUser.getText());
                us.setClave(String.valueOf(views.TxtPasswordUser.getPassword()));
                us.setRol(views.CbxRolUser.getSelectedItem().toString());
                if ( usDao.registar(us)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario Registrado con exito☻");
                }else   {
                    JOptionPane.showMessageDialog(null, "Error al Registrar usuario☻");
                }

            }
            
        }else    if (e.getSource()== views.BtnModificarUser) {
            if (views.TxtNombreUser.getText().equals("")||views.TxtUsuarioUser.getText().equals("")) {
                
                JOptionPane.showMessageDialog(null, "Todos loa campos son obligatorios");
                
            }else   {
                us.setNombre(views.TxtNombreUser.getText());
                us.setUsuario(views.TxtUsuarioUser.getText());
                us.setRol(views.CbxRolUser.getSelectedItem().toString());
                us.setId(Integer.parseInt(views.TxtIdUser.getText()));
                if ( usDao.modificar(us)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario Modificado con exito☻");
                }else   {
                    JOptionPane.showMessageDialog(null, "Error al Modificar usuario☻");
                }

            }
            
        }else if (e.getSource()==views.JMenuEliminarUsers) {
            if (views.TxtIdUser.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar☻");
            }else{
                int id=Integer.parseInt(views.TxtIdUser.getText());
                if (usDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar();
                     JOptionPane.showMessageDialog(null, "Usuario Eliminado ☻");
                }else{
                     JOptionPane.showMessageDialog(null, "Error al  eliminar usuario☻");
                }
            }
        }else if (e.getSource()==views.JMenuReingresarUsers) {
            if (views.TxtIdUser.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar☻");
            }else{
                int id=Integer.parseInt(views.TxtIdUser.getText());
                if (usDao.accion("Activo", id)) {
                    limpiarTable();
                    listarUsuarios();
                    limpiar();
                     JOptionPane.showMessageDialog(null, "Usuario Reingresado ☻");
                }else{
                     JOptionPane.showMessageDialog(null, "Error al  reingresar usuario☻");
                }
            }
        }else  {
            limpiar();
        } 

    }
    
    public void  listarUsuarios (){
        Tables color =new Tables();
        views.TableUsuario.setDefaultRenderer(views.TableUsuario.getColumnClass(0), color);
        List<Usuarios> lista=usDao.ListaUsuarios(views.TxtBuscarUser.getText());
        modelo=(DefaultTableModel) views.TableUsuario.getModel();
        Object[]ob=new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0]=lista.get(i).getId();
            ob[1]=lista.get(i).getNombre();
            ob[2]=lista.get(i).getUsuario();  
            //ob[3]=lista.get(i).getClave();
            ob[3]=lista.get(i).getRol();
            ob[4]=lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableUsuario.setModel(modelo);
        JTableHeader header=views.TableUsuario.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);
    }
    
    public void limpiarTable(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i=i-1;
        }
}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()== views.TableUsuario) {
            int fila=views.TableUsuario.rowAtPoint(e.getPoint());
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
        if (e.getSource()==views.TxtBuscarUser) {
            limpiarTable();
            listarUsuarios();
            
        }
    }
    
    
    
    public void limpiar(){
        views.TxtIdUser.setText("");
        views.TxtNombreUser.setText("");
        views.TxtUsuarioUser.setText("");
        views.TxtPasswordUser.setText("");
        views.TxtPasswordUser.setEnabled(true);
         views.BtnRegistarUser.setEnabled(true);
        
    }
    
    
    
    
    
}

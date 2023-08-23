/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author usuario
 */
public class UsuariosDao {
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public Usuarios login (String usuario ,String clave){
        String sql ="SELECT * FROM usuarios WHERE usuario=? AND clave=?";
        Usuarios us = new  Usuarios();
         
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs= ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setNombre(rs.getString("nombre"));
                us.setUsuario(rs.getString("usuario"));
                us.setClave(rs.getString("clave"));
                us.setRol(rs.getString("rol"));
                us.setEstado(rs.getString("estado"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return us;  
    }
    
    
    public boolean registar(Usuarios us){
        String sql="INSERT INTO usuarios (nombre,usuario,clave,rol) VALUES (?,?,?,?)";
        try {
            con=cn.getConnection();
            ps = con.prepareStatement(sql);
            
            con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getUsuario());
            ps.setString(3, us.getClave());
            ps.setString(4, us.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public List ListaUsuarios(String valor){
        List<Usuarios> listaUsers=new ArrayList();
        String sql ="SELECT * FROM usuarios ORDER BY estado ASC";
        String buscar="SELECT * FROM usuarios WHERE usuario LIKE '%"+valor+"%' OR nombre LIKE '%"+valor+"%' ";
        try {
            con=cn.getConnection();
            if (valor.equalsIgnoreCase("")) {
                ps= con.prepareStatement(sql);
           rs= ps.executeQuery(); 
            }else   {
                 ps= con.prepareStatement(buscar);
           rs= ps.executeQuery();
            }
          
           while    (rs.next()){
               Usuarios us=new Usuarios();
               us.setId(rs.getInt("id"));
               us.setNombre(rs.getString("nombre"));
               us.setUsuario(rs.getString("usuario"));
               us.setClave(rs.getString("clave"));
               us.setRol(rs.getString("rol"));
               us.setEstado(rs.getString("estado"));
               listaUsers.add(us);
               
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
       return listaUsers ;
    }
   
    public boolean modificar(Usuarios us){
        String sql="UPDATE usuarios SET nombre=?,usuario=?,rol=? WHERE id=?";
        try {
            con=cn.getConnection();
            ps = con.prepareStatement(sql);
            
            con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getUsuario());
            ps.setString(3, us.getRol());
            ps.setInt(4, us.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }   
    
    public boolean accion(String estado,int id ){
        String sql="UPDATE  usuarios SET estado =? WHERE id=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eliseo
 */
public class CursosDao {
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public List ListaCursos(String valor){
          List<Cursos> listaCursos=new ArrayList();
        String sql ="SELECT * FROM cursos ORDER BY estado ASC";
        String buscar="SELECT * FROM cursos WHERE codigo LIKE '%"+valor+"%' OR nombre LIKE '%"+valor+"%' ";
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
               Cursos crs=new Cursos();
               crs.setId(rs.getInt("id"));
               crs.setCodigo(rs.getString("codigo"));
               crs.setNombre(rs.getString("nombre"));
               crs.setFecha(rs.getString("fecha"));
               crs.setEstado(rs.getString("estado"));
               listaCursos.add(crs);
               
           }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
       return listaCursos ;
    }
}

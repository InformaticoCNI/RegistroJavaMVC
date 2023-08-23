/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Eliseo
 */
public class Tables extends  DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(
            JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
         super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, col); 
         if (jtable.getValueAt(row, col).toString().equals("Inactivo")) {
             setBackground(Color.red);//para cambiar la celda de color 
             setForeground(Color.white);
            
        }else{
             setBackground(Color.white);
             setForeground(Color.black);//para cambiar color de texto
         }
         return this;
    }
   
}

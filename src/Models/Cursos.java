
package Models;

/**
 *
 * @author Usuario
 */
public class Cursos {
    private int id;
    private String codigo;
    private String nombre;
    private String fecha;
    private String estado;

    public Cursos(int id, String codigo, String nombre, String fecha ,String estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado=estado;
    }

    Cursos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}

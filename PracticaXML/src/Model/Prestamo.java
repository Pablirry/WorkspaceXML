package Model;

public class Prestamo {

    private int id;
    private int idUsuario;
    private int idLibro;
    private String fechaInicio;
    private String fechaFin;
    
    public Prestamo(int id, int idUsuario, int idLibro, String fechaInicio, String fechaFin) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", fechaInicio="
                + fechaInicio + ", fechaFin=" + fechaFin + "]";
    }

}
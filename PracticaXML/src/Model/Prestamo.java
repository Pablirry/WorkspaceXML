package Model;

public class Prestamo {

    private String id;
    private String idUsuario;
    private String idLibro;
    private String fechaInicio;
    private String fechaFin;
    
    public Prestamo(String id, String idUsuario, String idLibro, String fechaInicio, String fechaFin) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
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

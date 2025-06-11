package veterinaria.businesslogic.dto;

public class VeterinarioDTO {
    private int cedula;
    private String nombreCompleto;
    private String telefono;
    private String nombreDeUsuario;

    public VeterinarioDTO() {}

    public VeterinarioDTO(int cedula, String nombreCompleto, String telefono, String nombreDeUsuario) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
    
    
}
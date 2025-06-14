package veterinaria.businesslogic.dto;

public class SecretariaDTO {
    private int numeroDeINE;
    private String nombreCompleto;
    private Integer telefono;
    private String nombreDeUsuario;

    public SecretariaDTO(int numeroDeINE, String nombreCompleto, Integer telefono, String nombreDeUsuario) {
        this.numeroDeINE = numeroDeINE;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.nombreDeUsuario = nombreDeUsuario;
    }
    
    public int getNumeroDeINE() {
        return numeroDeINE;
    }

    public void setNumeroDeINE(int numeroDeINE) {
        this.numeroDeINE = numeroDeINE;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }
}
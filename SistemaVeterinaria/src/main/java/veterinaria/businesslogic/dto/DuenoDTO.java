package veterinaria.businesslogic.dto;

public class DuenoDTO {
    private int idDuenio;
    private String telefono;
    private String nombreCompleto;
    private String email;
    private String ine;
    private String username;
    private String password;

    public DuenoDTO() {}

    public DuenoDTO(int idDuenio, String telefono, String nombreCompleto, String email) {
        this.idDuenio = idDuenio;
        this.telefono = telefono;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

    public int getIdDuenio() { return idDuenio; }
    public void setIdDuenio(int idDuenio) { this.idDuenio = idDuenio; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    @Override
    public String toString() {
        return nombreCompleto;
    }   
}
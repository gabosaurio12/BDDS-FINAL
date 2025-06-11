package veterinaria.businesslogic.dto;

public class DireccionDuenoDTO {
    private int idCasa;        
    private String calle;
    private String colonia;
    private String numero;    
    private int idDuenio;    

    public DireccionDuenoDTO() {}

    public DireccionDuenoDTO(int idCasa, String calle, String colonia, String numero, int idDuenio) {
        this.idCasa = idCasa;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.idDuenio = idDuenio;
    }

    public DireccionDuenoDTO(String calle, String colonia, String numero, int idDuenio) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.idDuenio = idDuenio;
    }

    // Getters y Setters
    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(int idDuenio) {
        this.idDuenio = idDuenio;
    }

    @Override
    public String toString() {
        return "DireccionDuenoDTO{" +
                "idCasa=" + idCasa +
                ", calle='" + calle + '\'' +
                ", colonia='" + colonia + '\'' +
                ", numero='" + numero + '\'' +
                ", idDuenio=" + idDuenio +
                '}';
    }
}
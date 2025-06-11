package veterinaria.businesslogic.dto;

public class DireccionVeterinarioDTO {
    private int idCasa;
    private String numero;
    private String calle;
    private String colonia;
    private Integer cedula;

    public DireccionVeterinarioDTO() {
    }

    public DireccionVeterinarioDTO(int idCasa, String numero, String calle, String colonia, Integer cedula) {
        this.idCasa = idCasa;
        this.numero = numero;
        this.calle = calle;
        this.colonia = colonia;
        this.cedula = cedula;
    }

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

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
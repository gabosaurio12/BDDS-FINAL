package veterinaria.businesslogic.dto;

public class DireccionSecretariaDTO {
    private int numeroDeCasa;
    private String calle;
    private String colonia;
    private Integer numeroDeINE;

    public DireccionSecretariaDTO() {
    }

    public DireccionSecretariaDTO(int numeroDeCasa, String calle, String colonia, Integer numeroDeINE) {
        this.numeroDeCasa = numeroDeCasa;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroDeINE = numeroDeINE;
    }

    public int getNumeroDeCasa() {
        return numeroDeCasa;
    }

    public void setNumeroDeCasa(int numeroDeCasa) {
        this.numeroDeCasa = numeroDeCasa;
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

    public Integer getNumeroDeINE() {
        return numeroDeINE;
    }

    public void setNumeroDeINE(Integer numeroDeINE) {
        this.numeroDeINE = numeroDeINE;
    }
}
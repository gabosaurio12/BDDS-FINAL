package veterinaria.businesslogic.dto;

import java.sql.Date;

public class MascotaDTO {
    private int idMascota;
    private String nombre;
    private Date fechaDeNacimiento;
    private String color;
    private Double peso;
    private String especie;
    private int idDuenio;

    public MascotaDTO() {}

    public MascotaDTO(int idMascota, String nombre, Date fechaDeNacimiento, String color, Double peso, int idDuenio) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.color = color;
        this.peso = peso;
        this.idDuenio = idDuenio;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(int idDuenio) {
        this.idDuenio = idDuenio;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
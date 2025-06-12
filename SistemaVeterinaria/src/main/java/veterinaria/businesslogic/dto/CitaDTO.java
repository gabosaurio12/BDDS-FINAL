package veterinaria.businesslogic.dto;

public class CitaDTO {
    private int idCita;
    private String tratamiento;
    private String motivoDeConsulta;
    private String estadoDeCita;
    private int idMascota;
    private int idDueno;
    private int idAgenda;
    private int idFechaHora;
    private int idEnfermedad;

    public CitaDTO() {}

    public CitaDTO(int idCita, String tratamiento, String motivoDeConsulta, String estadoDeCita, int idMascota, int idDueno, int idAgenda, int idFechaHora, int idEnfermedad) {
        this.idCita = idCita;
        this.tratamiento = tratamiento;
        this.motivoDeConsulta = motivoDeConsulta;
        this.estadoDeCita = estadoDeCita;
        this.idMascota = idMascota;
        this.idDueno = idDueno;
        this.idAgenda = idAgenda;
        this.idFechaHora = idFechaHora;
        this.idEnfermedad = idEnfermedad;
    }
    
    

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getMotivoDeConsulta() {
        return motivoDeConsulta;
    }

    public void setMotivoDeConsulta(String motivoDeConsulta) {
        this.motivoDeConsulta = motivoDeConsulta;
    }

    public String getEstadoDeCita() {
        return estadoDeCita;
    }

    public void setEstadoDeCita(String estadoDeCita) {
        this.estadoDeCita = estadoDeCita;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public int getIdFechaHora() {
        return idFechaHora;
    }

    public void setIdFechaHora(int idFechaHora) {
        this.idFechaHora = idFechaHora;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }    
}
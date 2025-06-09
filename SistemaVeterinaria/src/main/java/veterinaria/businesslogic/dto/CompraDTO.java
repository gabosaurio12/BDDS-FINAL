package veterinaria.businesslogic.dto;

public class CompraDTO {
    private int idDuenio;
    private int idProducto;

    public CompraDTO() {
    }

    public CompraDTO(int idDuenio, int idProducto) {
        this.idDuenio = idDuenio;
        this.idProducto = idProducto;
    }

    public int getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(int idDuenio) {
        this.idDuenio = idDuenio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraDTO compraDTO = (CompraDTO) o;
        return idDuenio == compraDTO.idDuenio &&
               idProducto == compraDTO.idProducto;
    }

    @Override
    public int hashCode() {
        int result = idDuenio;
        result = 31 * result + idProducto;
        return result;
    }
}
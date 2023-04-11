package mx.com.upiicsa.na2.NA210.response;

import lombok.Data;
import mx.com.upiicsa.na2.NA210.model.entity.GerenteModel;

import java.util.List;

@Data
public class GerenteRespuesta {
    private List<GerenteModel> contenido;
    private int numeroPagina;
    private int sizePagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;

}
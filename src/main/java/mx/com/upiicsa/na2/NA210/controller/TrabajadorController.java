package mx.com.upiicsa.na2.NA210.controller;

import mx.com.upiicsa.na2.NA210.model.entity.TrabajadorModel;
import mx.com.upiicsa.na2.NA210.response.TrabajadorRespuesta;
import mx.com.upiicsa.na2.NA210.service.interfaces.ITrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/na2/trabajadores")
@CrossOrigin(origins = "http://localhost:4200")
public class TrabajadorController {
    @Autowired
    private ITrabajadorService sTrabajador;

    @PreAuthorize("hasRole('ADMIN') OR  hasRole('GERENTE')")
    @GetMapping
    public ResponseEntity<?> listarTrabajadores(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int numeroPagina,
            @RequestParam(value = "pageSize",defaultValue = "10", required = false) int sizePagina){
        return new ResponseEntity<>(sTrabajador.obtenerTrabajadores(numeroPagina,sizePagina),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTrabajadorByNumero_Trabajador(@PathVariable(name = "id") long numero_trabajador){
        return new ResponseEntity(sTrabajador.obtenerTrabajadorById(numero_trabajador),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR  hasRole('GERENTE')")
    @PostMapping
    public ResponseEntity<?> crearTrabajador(@Valid @RequestBody TrabajadorModel trabajadorDTO){
        return new ResponseEntity<>(sTrabajador.crearTrabajador(trabajadorDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') OR  hasRole('GERENTE')")
    @PutMapping
    public ResponseEntity<?> actaulizarTrabajador(@Valid @RequestBody TrabajadorModel trabajadorDTO){
         sTrabajador.actualizarTrabajador(trabajadorDTO);
        return new ResponseEntity<>("Actualizada",HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') OR  hasRole('GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTrabajador(@PathVariable(name = "id") long numero_trabajador){
        sTrabajador.eliminarTrabajador(numero_trabajador);
        return new ResponseEntity<>("Trabajador eliminado con exito",HttpStatus.OK);
    }
}
package mx.com.upiicsa.na2.NA210.controller;

import mx.com.upiicsa.na2.NA210.model.entity.TareaModel;
import mx.com.upiicsa.na2.NA210.service.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class TareaController {

    @Autowired
    private ITareaService sTarea;

    @GetMapping("/na2/trabajadores/{id_trabajador}/tareas/")
    public ResponseEntity<?> listarTareasTrabajador(@PathVariable(value = "id_trabajador") long id_trabajador){
        return new ResponseEntity<>(sTarea.findTareasTrabajador(id_trabajador),HttpStatus.OK);
    }
    @GetMapping("/na2/tareas/")
    public ResponseEntity<?> listarTareasTrabajador(){
        return new ResponseEntity<>(sTarea.findAllTareas(),HttpStatus.OK);
    }
    @GetMapping("/na2/tareas/{id_tarea}")
    public ResponseEntity<?> obtenerTareaByID(@PathVariable(value = "id_tarea") long id_tarea){;
        return new ResponseEntity<>(sTarea.findTareaByID(id_tarea), HttpStatus.OK);
    }

    @PostMapping("/na2/trabajadores/{id_trabajador}/tareas/")
    public ResponseEntity<?> asignarTarea(@PathVariable(value = "id_trabajador") long id_trabajador, @Valid @RequestBody TareaModel tareaDTO){
        return new ResponseEntity<>(sTarea.createTarea(id_trabajador,tareaDTO),HttpStatus.CREATED);
    }

    @PutMapping("/na2/trabajadores/{id_trabajador}/tareas/{id_tarea}")
    public ResponseEntity<?> actualizarTarea(@PathVariable(value = "id_trabajador") long id_trabajador,@PathVariable(value = "id_tarea") long id_tarea,@Valid @RequestBody TareaModel tareaDTO){
        sTarea.updateTarea(id_trabajador,tareaDTO);
        return new ResponseEntity<>("tareaActualizada",HttpStatus.NO_CONTENT);
    }
}
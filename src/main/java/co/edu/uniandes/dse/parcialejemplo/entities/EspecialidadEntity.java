package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
@Getter
@Setter
public class EspecialidadEntity extends BaseEntity{

    private String nombre;
    private String descripcion;

    @PodamExclude
    @ManyToMany
    List<MedicoEntity> medicos = new ArrayList<>();
    
}

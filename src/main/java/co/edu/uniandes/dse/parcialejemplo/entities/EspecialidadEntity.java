package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Getter
@Setter
@Entity
public class EspecialidadEntity extends BaseEntity{

    private String nombre;
    private String descripcion;

    @PodamExclude
    @ManyToMany
    ArrayList<MedicoEntity> medicos = new ArrayList<>();
    
}

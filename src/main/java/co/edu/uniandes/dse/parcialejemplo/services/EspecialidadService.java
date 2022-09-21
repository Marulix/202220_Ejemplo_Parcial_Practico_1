package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {
    @Autowired
    EspecialidadRepository especialidadRepository;

    /**
     * Se encarga de crear una especialidad en la base de datos
     * 
     * @param especialidad
     * @return Un objeto de especialidadEntity
     * @throws IllegalOperationException
     */
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws IllegalOperationException{
        log.info("Inicia proceso de creacion de una Especialidad");
        String descripcion = especialidad.getDescripcion();
        if(descripcion.length() < 10){
            throw new IllegalOperationException("Descripcion menor a 10 caracteres");
        }

        return especialidadRepository.save(especialidad);
    }
    
}

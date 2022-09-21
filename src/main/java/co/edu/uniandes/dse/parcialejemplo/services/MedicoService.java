package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    /**
     * Se encarga de crear un medico en la base de datos
     * 
     * @param medico
     * @return Un objeto de medicoEntity
     * @throws IllegalOperationException
     */
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException{
        log.info("Inicia proceso de creacion de un Medico");
        String registroMedico = medico.getRegistroMedico();
        if(!registroMedico.startsWith("RM")){
            throw new IllegalOperationException("Registro medico no empieza con RM");
        }

        return medicoRepository.save(medico);
    }
    
}

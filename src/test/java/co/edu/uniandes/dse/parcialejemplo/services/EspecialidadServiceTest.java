package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Especialidad
 *
 * @author ISIS2603
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
public class EspecialidadServiceTest {

    @Autowired
	private EspecialidadService especialidadService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<EspecialidadEntity> especialidadList = new ArrayList<>();

	/**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from EspecialidadEntity");
		entityManager.getEntityManager().createQuery("delete from EspecialidadEntity");
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {
		for (int i = 0; i < 3; i++) {
			EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
			entityManager.persist(especialidadEntity);
			especialidadList.add(especialidadEntity);
		}

		MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
		entityManager.persist(medicoEntity);
		medicoEntity.getEspecialidades().add(especialidadList.get(0));
		especialidadList.get(0).getMedicos().add(medicoEntity);
	}

	/**
	 * Prueba para crear una Especialidad
	 * @throws IllegalOperationException
	 */
	@Test
	void testCreateEspecialidad() throws IllegalOperationException {
		EspecialidadEntity newEntity = factory.manufacturePojo(EspecialidadEntity.class);
		newEntity.setDescripcion("Esta especialidad es la mas basica");
		EspecialidadEntity result = especialidadService.createEspecialidad(newEntity);
		assertNotNull(result);
		EspecialidadEntity entity = entityManager.find(EspecialidadEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getDescripcion(), entity.getDescripcion());

	}

	/**
	 * Prueba para crear un Medico con RM invalido
	 */
	@Test
	void testCreateBookWithNoValidISBN() {
		assertThrows(IllegalOperationException.class, () -> {
			EspecialidadEntity newEntity = factory.manufacturePojo(EspecialidadEntity.class);
			newEntity.setDescripcion(" ");
			especialidadService.createEspecialidad(newEntity);
		});
	}

    
}

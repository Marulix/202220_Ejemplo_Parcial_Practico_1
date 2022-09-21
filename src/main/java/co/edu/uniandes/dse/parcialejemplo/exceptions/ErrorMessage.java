package co.edu.uniandes.dse.parcialejemplo.exceptions;


public final class ErrorMessage {
	public static final String MEDICO_NOT_FOUND = "El medico con el id provisto no fue encontrado";
	public static final String ESPECIALIDAD_NOT_FOUND = "La especializacion con el id provisto no fue encontrada";


	private ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
}
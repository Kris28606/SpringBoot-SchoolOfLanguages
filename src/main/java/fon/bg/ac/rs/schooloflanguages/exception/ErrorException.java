package fon.bg.ac.rs.schooloflanguages.exception;

/**
 * Korisnicki definisan exception koji nasledjuju ApplicationException
 * @author Kristina
 *
 */
public class ErrorException extends ApplicationException{

	/**
	 * Konstruktor koji prihvata poruku
	 * @param message - Poruka za exception
	 */
	public ErrorException(String message) {
		super(message);
	}
	
	

}

package de.quoss.template;

/**
 * Exception to be used in case of error
 * 
 * @author Clemens Quoss
 *
 */
public class TemplateException extends Exception {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with message
	 * 
	 * @param msg
	 *            message
	 */
	public TemplateException(String msg) {
		super(msg);
	}

	/**
	 * Constructor with exception
	 * 
	 * @param e
	 *            exception
	 */
	public TemplateException(Exception e) {
		super(e);
	}

	/**
	 * Constructor with message and exception
	 * 
	 * @param msg
	 *            message
	 * @param e
	 *            exception
	 */
	public TemplateException(String msg, Exception e) {
		super(msg, e);
	}

}

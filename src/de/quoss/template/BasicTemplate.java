package de.quoss.template;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Template with logging and command line checking
 * 
 * @author Clemens Quoss
 *
 */
public class BasicTemplate {

	/**
	 * Class name
	 */
	private static final String CLASS_NAME = BasicTemplate.class.getCanonicalName();

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

	/**
	 * Standard constructor
	 */
	public BasicTemplate() {
		super();
	}

	/**
	 * Process logic
	 * 
	 * @param args
	 *            command line arguments
	 * @throws TemplateException
	 *             in case of error
	 */
	public void process(String[] args) throws TemplateException {

		// Start message
		LOGGER.log(Level.INFO, "Start");

		// Check command line arguments
		if (args.length != 0) {
			throw new TemplateException(String.format("USAGE: %s", CLASS_NAME));
		}

		// End message
		LOGGER.log(Level.INFO, "End");

	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {

		try {
			new BasicTemplate().process(args);
		} catch (TemplateException e) {
			LOGGER.log(Level.SEVERE, "Error calling process logic", e);
			System.exit(1);
		}

	}

}

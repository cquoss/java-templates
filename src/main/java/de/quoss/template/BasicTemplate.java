package de.quoss.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicTemplate.class);

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
		LOGGER.info("Start");

		// Check command line arguments
		if (args.length != 0) {
			throw new TemplateException(String.format("USAGE: %s", CLASS_NAME));
		}

		// End message
		LOGGER.info("End");

	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		int status = 0;
		try {
			new BasicTemplate().process(args);
		} catch (TemplateException e) {
			LOGGER.error("Error calling process logic", e);
			status = 1;
		}
		System.exit(status);
	}

}

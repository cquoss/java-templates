package de.quoss.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Template with logging, command line checking and property functions
 * 
 * @author Clemens Quoss
 *
 */
public class PropertyTemplate {

	/**
	 * Class name
	 */
	private static final String CLASS_NAME = PropertyTemplate.class.getCanonicalName();

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

	/**
	 * Properties
	 */
	private static Properties properties = new Properties();

	/**
	 * Standard constructor
	 */
	public PropertyTemplate() {
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

		// Load properties
		loadProperties();

		// Get and log example optional property
		String optionalPropertyValue = getPropertyValue("optional", "---XXX---");
		if (!optionalPropertyValue.equals("---XXX---")) {
			LOGGER.log(Level.INFO, CLASS_NAME.concat(".optional: {0}"), new Object[] { optionalPropertyValue });
		}

		// Get and log example mandatory property
		LOGGER.log(Level.INFO, CLASS_NAME.concat(".mandatory: {0}"),
				new Object[] { getPropertyValue("mandatory", null) });

		// End message
		LOGGER.log(Level.INFO, "End");

	}

	/**
	 * Load properties from file located in class path
	 * 
	 * @throws TemplateException
	 *             in case of error
	 */
	private void loadProperties() throws TemplateException {
		try {
			String propertyFileName = CLASS_NAME.concat(".properties");
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream == null) {
				throw new TemplateException(String.format("Unable to locate file in class path: %s", propertyFileName));
			} else {
				properties.load(inputStream);
			}
		} catch (IOException e) {
			throw new TemplateException("Loading properties failed", e);
		}
	}

	/**
	 * Get property value or default value. If default value is null the property is
	 * mandatory.
	 * 
	 * @param key
	 *            property key
	 * @param defaultValue
	 *            default value
	 * @return String property value
	 * @throws TemplateException
	 *             in case of error
	 */
	private String getPropertyValue(String key, String defaultValue) throws TemplateException {
		String fullKey = CLASS_NAME.concat(".").concat(key);
		String value = properties.getProperty(fullKey, defaultValue);
		if (value == null) {
			throw new TemplateException(String.format("Mandatory property not found: %s", fullKey));
		}
		return value;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {

		try {
			new PropertyTemplate().process(args);
		} catch (TemplateException e) {
			LOGGER.log(Level.SEVERE, "Error calling process logic", e);
			System.exit(1);
		}

	}

}

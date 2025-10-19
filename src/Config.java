 /** 
 * Julian Williams-Goldberg
 * CS245 (EJ)
 */
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Reads configuration from a property file.
 */
public class Config {
    
    private Properties props;
    
    /**
     * Creates a Config and loads properties from the file.
     */
    public Config(String filename) {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(filename);
            props.load(fis);
            fis.close();
        } catch (Exception e) {
            System.out.println("Could not load " + filename);
        }
    }
    
    /**
     * Gets an integer property value.
     */
    public int getInt(String key, int def) {
        String val = props.getProperty(key);
        if (val == null) return def;
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return def;
        }
    }
    
    /**
     * Gets a double property value.
     */
    public double getDouble(String key, double def) {
        String val = props.getProperty(key);
        if (val == null) return def;
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return def;
        }
    }
    
    /**
     * Gets a string property value.
     */
    public String getString(String key, String def) {
        return props.getProperty(key, def);
    }
}
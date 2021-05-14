package Props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private static final Logger LOG = LoggerFactory.getLogger(Props.class);

    private static Properties properties;
    static InputStream inputStream = null;

    public static void loadConfigProperties() throws IOException {
        properties = new Properties();

        try{
            inputStream = new FileInputStream("src/main/resources/config/config.properties");
            properties.load(inputStream);
        }catch (FileNotFoundException e){
            LOG.error(e.getMessage());
        } finally {
            try{
                if(inputStream != null)
                    inputStream.close();
            }catch (IOException e){
                LOG.error(e.getMessage());
            }
        }
    }


    public static String getProp(String key){
        if(key == null || key.isEmpty())
            return "";
        else{
            return properties.getProperty(key);
        }
    }

}

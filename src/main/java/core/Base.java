package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Base {
    public Map<String, String> jsonFileToMap(String filePath) {
        InputStream inputStream = null;
        HashMap<String, String> result = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            inputStream = classLoader.getResourceAsStream(filePath);
            if (inputStream == null) {
                return null;
            }
            result = new ObjectMapper().readValue(inputStream, HashMap.class);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return result;
    }

    public void info(String msg){
        LoggerFactory.getLogger(this.getClass().getName()).info(getCurrentDateTime()+":"+msg);
    }

    public void warn(String msg){
        LoggerFactory.getLogger(this.getClass().getName()).warn(getCurrentDateTime()+":"+msg);
    }

    public void debug(String msg){
        LoggerFactory.getLogger(this.getClass().getName()).debug(getCurrentDateTime()+":"+msg);
    }

    /**
     * returns current date and time in dd-mm-yyyy hh:mm:ss format
     * @return
     */
    public String getCurrentDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
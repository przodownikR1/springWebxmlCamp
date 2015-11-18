package pl.java.scalatech.components;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropMessages {

    private static Properties properties = new Properties();

    static {
        properties.put("slawek", "Borowiec");
        properties.put("piotr", "gilewski");
        properties.put("zosia", "samosia");
        properties.put("obiad", "zupa");
        System.err.println("############################################### init property");
    }

    private String properKey = "slawek";

    public String getValueByKey(String key) {
        return properties.getProperty(key);
    }

    public String getProperKey() {
        return properKey;
    }

    public void setProperKey(String properKey) {
        this.properKey = properKey;
    }

}
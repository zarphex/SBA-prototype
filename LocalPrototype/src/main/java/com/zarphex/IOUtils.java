package com.zarphex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class IOUtils {
    public static Properties readPropertiesFile(String configFile) {
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(configFile));
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        return appProps;
    }
}

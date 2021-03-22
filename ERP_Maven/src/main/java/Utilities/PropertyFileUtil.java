package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
public static String getValueForKey(String key)throws Throwable
{
	Properties confingProperties =new Properties();
	confingProperties .load(new FileInputStream("E:\\10OClockOJT\\ERP_Maven\\PropertyFile\\Envionment.properties"));
	return confingProperties.getProperty(key);
}
}

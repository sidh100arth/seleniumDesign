package seleniumDesign.DataProviderPackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class jsonToMap {

	
	
	public List<HashMap<String , String>> convertJsonToMap() throws IOException {
		
		String file = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//seleniumDesign//DataProviderPackage//datafile.json"),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String , String>> data = mapper.readValue(file, new TypeReference<List<HashMap<String , String>>>(){});
		
		return data;
	}
}

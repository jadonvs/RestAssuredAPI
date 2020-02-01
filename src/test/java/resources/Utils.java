package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	String userDir=System.getProperty("user.dir");
	RequestSpecification reqSpec;
	
	public RequestSpecification getRequestSpecificationBuilder() throws IOException
	{
		if (reqSpec==null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec=new RequestSpecBuilder()
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").setBaseUri(getGlobalVariable("BaseURI")).setContentType(ContentType.JSON).build();		
			
		}
		return reqSpec;
	}
	
	
	public ResponseSpecification getResponseSpecificationBuilder()
	{
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resSpec;
	}
	
	public String getGlobalVariable(String key) throws IOException
	{
		
		FileInputStream fis=new FileInputStream(userDir+"/src/test/java/resources/configuration.properties");
		Properties prop=new Properties();
		prop.load(fis);
		return prop.get(key).toString();
	}
	
	public String convertRawReposneToJsonAndGetValue(Response res, String pathOfElement)
	{
		String responseString=res.asString();
		JsonPath js=new JsonPath(responseString);
		String valueFromJson=js.get(pathOfElement);
		return valueFromJson;
	}
	
	

}

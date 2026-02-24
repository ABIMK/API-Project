package resources;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Random;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility {
	
	public static RequestSpecification baseReqSpec;
	public static ResponseSpecification baseResSpec;

	public RequestSpecification baseRequestSpec() throws IOException {
		if(baseReqSpec==null) {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		baseReqSpec = new RequestSpecBuilder().setBaseUri(getPropertyValue("baseUrl")).addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return baseReqSpec;
		}
		return baseReqSpec;
	}
	
	public ResponseSpecification baseResSpec(int statusCode)  {
		
		if(baseResSpec==null) {
		baseResSpec =new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).build();
		}
		return baseResSpec;
		
	}
	
	public static String getPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/resources/config.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public static String getValueFromJson(Response response, String key) {
		String res=response.asString();
		JsonPath js = new JsonPath(res);
		String value = js.getString(key);
		return value;
	}
	
	public static int getRandomNumbers(int numStrLen) {
		String nums = "";
		Random _random = new Random();
		StringBuilder sb = new StringBuilder(numStrLen);
		if (numStrLen > 10)
			numStrLen = 10;
		for (int i = 0; i < numStrLen; i++) {

			int num = _random.nextInt(9);
			sb.append(num);
			nums = sb.toString();

		}
		return Integer.parseInt(nums);
	}
	
	public static String getRandomString(int strLen) {
		// chose a Character random from this String
		String str = "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(strLen);

		for (int i = 0; i < strLen; i++) {

			// generate a random number between
			// 0 to String variable length
			int index = (int) (str.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(str.charAt(index));

		}

		return sb.toString();
	}
	
	public static String getRandomNameGenerate() {

		String[] name = { "Shiva", "Shakthi", "Stalin", "Surya", "Mani", "Mahesh", "MKS", "Mithun", "Kiran", "Raj",
				"Stefan", "Rose", "Murugan", "Max", "Anderson", "Smith", "Sofia", "Dark", "Denvar", "Chifu", "Cyndrala",
				"Kaif", "Khan", "Patan", "Salman", "Vijay", "karthi" };

		Random no = new Random();
		int num = no.nextInt(name.length - 1);

		System.out.println(name[num]);
		return name[num];

	}
}

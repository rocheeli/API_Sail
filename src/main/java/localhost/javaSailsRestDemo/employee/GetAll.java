package localhost.javaSailsRestDemo.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Sample class that gets all entries from employee API
 * 
 * @author Elizabeth Roche
 * @since 2016-09-07
 */

public class GetAll
{
	/**
	 * The URLof the API we want to connect to
	 */
	protected static String endpoint = "http://localhost:1337/employee";
	/**
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";
	

	public static void main(String[] args)
	{
		try
		{
			URL newEmployee = new URL(endpoint );
			HttpURLConnection connection = (HttpURLConnection) newEmployee.openConnection();
			connection.setRequestMethod("GET");

			// if we did not get 200 (success) throw an exception
			if (connection.getResponseCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code: " + connection.getResponseCode());
			}
			// read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// loop of buffer line by line until it returns null meaning no more
			// lines
			while (br.readLine() != null)
			{
				// print out each line to screen
				System.out.println(br.readLine());
			}

			// close connection to API
			connection.disconnect();

		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
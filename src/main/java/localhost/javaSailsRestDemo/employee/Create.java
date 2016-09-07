package localhost.javaSailsRestDemo.employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Sample class that posts to employee API
 * 
 * @author Elizabeth Roche
 * @since 2016-09-07
 */

public class Create
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
			// The first name of new employee (at least 2 letters)
			String firstName = "Elizabeth";

			// The last name of new employee ( at least 2 letters)
			String lastName = "Roche";

			// email of new employee (address@email.com)
			String email = "bethsroche@gmail.com";

			// home phone # of new employee (###-##-####)
			String homePhone = "301-421-0171";

			// cell phone # of new employee (###-##-####)
			String cellPhone = "301-742-3663";

			// employee password (at least one cap, one lower case, number, and eight char)
						String password = "Ab123456";
			
			// active status of new employee (1 or 0)
			String active = "1";


			// creates the URL parameters as a string encoding them with the
			// defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset), URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset), URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset), URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)

			);

			// creates a new URL out of the endpoint and
			// queryString
			URL newEmployee = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) newEmployee.openConnection();
			connection.setRequestMethod("POST");

			// if we did not get 201 (success) throw an exception
			if (connection.getResponseCode() != 201)
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
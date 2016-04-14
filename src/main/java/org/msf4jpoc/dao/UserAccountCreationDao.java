/**
 * 
 */
package org.msf4jpoc.dao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.msf4jpoc.bean.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster; 
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
/**
 * @author Kishorekumar_N
 *
 */
public class UserAccountCreationDao {
	private static final Logger log = LoggerFactory.getLogger(UserAccountCreationDao.class);
	
	public String insertUserAccount(UserAccount userAccount) {
		
		Properties prop = new Properties();
		int accNum = 0;
		boolean result = false;
		try {
			System.out.println("reading ............");
			InputStream inputStream = 
				    getClass().getClassLoader().getResourceAsStream("env.properties");
					//Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties");
		
		// load a properties file
			prop.load(inputStream);

		// get the property value and print it out
		System.out.println(prop.getProperty("Cassandra_IP"));
		System.out.println(prop.getProperty("KEYSPACE"));
		

		
		String serverIp = prop.getProperty("Cassandra_IP");
	    String keyspace = prop.getProperty("KEYSPACE");
	    Cluster cluster = Cluster.builder().addContactPoints(serverIp).build();  
	    Session session = cluster.connect(keyspace);
	    
	    log.info("*********Retreive ID from table *************");
	    String cqlStatement = "SELECT max(User_ID) as User_ID  FROM sample.users";
	    for (Row row : session.execute(cqlStatement)) {
	       accNum  = row.getInt("User_ID");
	             }
	    accNum = accNum + 1;
	    String insertQuery = "insert into users (User_ID,FirstName,LastName,dateofbirth,email,city,ZIPCode,State) values ("+accNum+", '"+userAccount.getFirstName()+"','"+userAccount.getLastName()+"','"+userAccount.getDateOfBirth()+"', '"+userAccount.getEmail()+"', '"+userAccount.getAddress().getCity()+"',"+userAccount.getAddress().getZipCode()+", '"+userAccount.getAddress().getState()+"');";
	    log.info("insertQuery.."+insertQuery);
	    ResultSet results  = session.execute(insertQuery);
	    result =  results.wasApplied();
	   
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ? "Account created successfully": "Account not created";
	}

}

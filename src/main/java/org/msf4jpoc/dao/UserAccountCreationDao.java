/**
 * 
 */
package org.msf4jpoc.dao;
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
	
	public String insertUserAccount(UserAccount userAccount){
		
		int accNum = 0;
		boolean result = false;
		String serverIp = "127.0.0.1";
	    String keyspace = "sample";
	    Cluster cluster = Cluster.builder().addContactPoints(serverIp).build();  
	    Session session = cluster.connect(keyspace);
	    
	    log.info("*********Retreive ID from table *************");
	    String cqlStatement = "SELECT max(User_ID) as User_ID  FROM sample.users";
	    for (Row row : session.execute(cqlStatement)) {
	       accNum  = row.getInt("User_ID");
	             }
	    accNum = accNum + 1;
	    String insertQuery = "insert into users (User_ID,FirstName,LastName,dateofbirth,EmailAddress,city,ZIPCode,State) values ("+accNum+", '"+userAccount.getFirstName()+"','"+userAccount.getLastName()+"','"+userAccount.getDateOfBirth()+"', '"+userAccount.getEmail()+"', '"+userAccount.getAddress().getCity()+"',"+userAccount.getAddress().getZipCode()+", '"+userAccount.getAddress().getState()+"');";
	    log.info("insertQuery.."+insertQuery);
	    ResultSet results  = session.execute(insertQuery);
	    result =  results.wasApplied();
	   
		return result ? "Account created successfully": "Account not created";
	}

}

package in.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

import in.abc.bo.CustomerBO;

public class CustomerMySQLDAOImpl implements ICustomerDAO {

	
	private static final String CUSTOMER_INSERT_QUERY = "INSERT INTO SPRING_CUSTOMER(CNAME,CADDR,PAMT,INTRAMT)values(?,?,?,?)";
	private DataSource dataSource;
	
	static {
		System.out.println("CustomerMySQLDAOImpl.class file is loading...");
	}

	public CustomerMySQLDAOImpl(DataSource dataSource) {
		System.out.println("CustomerMySQLDAOImpl :: 1 param constructor...");
		this.dataSource = dataSource;
		

	}

	@Override
	public int insert(CustomerBO bo) {
		int count = 0 ;
		try (Connection connection = dataSource.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(CUSTOMER_INSERT_QUERY);
			pstmt.setString(1, bo.getCustomerName());
			pstmt.setString(2, bo.getCustomerAddress());
			pstmt.setFloat(3, bo.getPamt());
			pstmt.setFloat(4, bo.getInterestAmt());
			
			 count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return count;
	}

}

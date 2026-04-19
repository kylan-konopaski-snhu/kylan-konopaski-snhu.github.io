/*
 * Author: Kylan Konopaski
 * Version: 1.2
 * Date updated: 3/30/2026
 */
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

/*
 * ContactStorage is responsible for storing and retrieving Contact objects.
 * This class separates data management from business logic.
 */
public class ContactStorage {
	
	private Connection connection;
	
	public ContactStorage() {
		try {
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection("jdbc:sqlite:contacts.db");
			
			Statement statement = connection.createStatement();
			
			// Create table if it doesn't exist
			statement.execute("CREATE TABLE IF NOT EXISTS contacts (" +
					"id INTEGER PRIMARY KEY, " +
					"firstName TEXT, " +
					"lastName TEXT, " +
					"phone TEXT, " +
					"address TEXT)");
			
			// Sync ID counter with DB
			ResultSet resultSet = statement.executeQuery("SELECT MAX(id) as maxId FROM contacts");
			if (resultSet.next()) {
				int maxId = resultSet.getInt("maxId");
				Contact.setIdCounter(maxId + 1);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Adds a new contact to database.
	 */
	public void add(Contact contact) {
		String sql = "INSERT INTO contacts (id, firstName, lastName, phone, address) VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, contact.getContactId());
			preparedStatement.setString(2, contact.getFirstName());
			preparedStatement.setString(3, contact.getLastName());
			preparedStatement.setString(4, contact.getPhone());
			preparedStatement.setString(5, contact.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Updates the first name of a contact in DB
	 */
	public void updateFirstName(int id, String firstName) {
		String sql = "UPDATE contacts SET firstName = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Updates the last name of a contact in DB
	 */
	public void updateLastName(int id, String lastName) {
		String sql = "UPDATE contacts SET lastName = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, lastName);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Updates the first name of a contact in DB
	 */
	public void updatePhone(int id, String phone) {
		String sql = "UPDATE contacts SET phone = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, phone);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Updates the first name of a contact in DB
	 */
	public void updateAddress(int id, String address) {
		String sql = "UPDATE contacts SET address = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, address);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Finds a contact by its ID.
	 */
	public Contact findById(int contactId) {
		String sql = "SELECT * FROM contacts WHERE id = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, contactId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return new Contact(
					resultSet.getInt("id"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("phone"),
					resultSet.getString("address")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Reusable search method that queries DB based on parameter input
	 */
	private List<Contact> executeSearchQuery(String sql, String parameter) {
		List<Contact> results = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, "%" + parameter.toLowerCase() + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				results.add(new Contact(
					resultSet.getInt("id"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("phone"),
					resultSet.getString("address")	
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	/*
	 * Search all properties by keyword
	 */
	public List<Contact> findByKeyword(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		String sql = "SELECT * FROM contacts WHERE " +
				"LOWER(firstName) LIKE ? OR " +
				"LOWER(lastName) LIKE ? OR " +
				"LOWER(phone) LIKE ? OR " +
				"LOWER(address) LIKE ?";
		
		List<Contact> results = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			String param = "%" + keyword.toLowerCase() + "%";
			
			preparedStatement.setString(1, param);
			preparedStatement.setString(2, param);
			preparedStatement.setString(3, param);
			preparedStatement.setString(4, param);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				results.add(new Contact(
					resultSet.getInt("id"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("phone"),
					resultSet.getString("address")	
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	/*
	 * Searches for a contact by first name.
	 */
	public List<Contact> findByFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		String sql = "SELECT * FROM contacts WHERE LOWER(firstName) LIKE ?";
		return executeSearchQuery(sql, firstName);
	}
	
	/*
	 * Searches for a contact by last name.
	 */
	public List<Contact> findByLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		String sql = "SELECT * FROM contacts WHERE LOWER(lastName) LIKE ?";
		return executeSearchQuery(sql, lastName);
	}
	
	/*
	 * Searches for a contact by phone.
	 */
	public List<Contact> findByPhone(String phone) {
		if (phone == null || phone.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		String sql = "SELECT * FROM contacts WHERE LOWER(phone) LIKE ?";
		return executeSearchQuery(sql, phone);
	}
	
	/*
	 * Searches for a contact by address.
	 */
	public List<Contact> findByAddress(String address) {
		if (address == null || address.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		String sql = "SELECT * FROM contacts WHERE LOWER(address) LIKE ?";
		return executeSearchQuery(sql, address);
	}
	
	/*
	 * Deletes a contact by ID.
	 */
	public void delete(int contactId) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, contactId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns all stored contacts.
	 */
	public List<Contact> getAll() {
		List<Contact> results = new ArrayList<>();
		String sql = "SELECT * FROM contacts";
		
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				results.add(new Contact(
					resultSet.getInt("id"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("phone"),
					resultSet.getString("address")	
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
}

package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cda.bibliotheque.model.Author;
import cda.bibliotheque.model.Customer;

import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, firstname, lastname, email FROM client;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                customers.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email")
                ));
            } 

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients -> " + e.getMessage());
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO client(lastname, firstname, email) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customer.getLastname());
            pstmt.setString(2, customer.getFirstname());
            pstmt.setString(3, customer.getEmail());
            pstmt.executeUpdate();
            System.out.println("Ajout du client effectué");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout dans addAuthor -> " + e.getMessage());
        }
    }

    public void updateCustomer (Customer customer) {
        String sql = "UPDATE client  SET firstname = ?, lastname = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, customer.getFirstname());
            pstmt.setString(2, customer.getLastname());
            pstmt.setString(3, customer.getEmail());
            pstmt.setInt(4, customer.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Client mis à jour");
            } else {
                System.out.println("Client n'existe pas");
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification dans updateCustomer -> " + e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Client supprimé avec succès");
            } else {
                System.out.println("Client n'existe pas");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion");
        }
    }
}

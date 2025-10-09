package cda.bibliotheque.controller.Customer;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.CustomerDAO;
import cda.bibliotheque.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateCustomerController {

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Customer customer = new Customer();
        customer.setFirstname(inputFirstName.getText());
        customer.setLastname(inputLastName.getText());
        customer.setEmail(inputEmail.getText());
        customerDAO.addCustomer(customer);
        App.setRoot("customers/customers");
    }

}

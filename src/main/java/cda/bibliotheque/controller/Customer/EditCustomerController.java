package cda.bibliotheque.controller.Customer;

import java.io.IOException;

import cda.bibliotheque.controller.Author.EditAuthorController;
import cda.bibliotheque.dao.CustomerDAO;
import cda.bibliotheque.model.Customer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import cda.bibliotheque.App;

public class EditCustomerController {

    private final ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    @FXML
    public void initialize() {
        customer.addListener((obs, oldCustomer, newCustomer) -> {
            if (newCustomer != null) {
                inputFirstName.setText(newCustomer.getFirstname());
                inputLastName.setText(newCustomer.getLastname());
                inputEmail.setText(newCustomer.getEmail());
            }
        });
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Customer newCustomer = customer.get();
        newCustomer.setFirstname(inputFirstName.getText());
        newCustomer.setLastname(inputLastName.getText());
        newCustomer.setEmail(inputEmail.getText());
        customerDAO.updateCustomer(newCustomer);
        App.setRoot("customers/customers");
    }

    public EditCustomerController() {}

    public void setCustomer (Customer customer) {
        this.customer.set(customer);
    }

}

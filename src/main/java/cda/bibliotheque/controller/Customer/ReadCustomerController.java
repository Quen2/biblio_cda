package cda.bibliotheque.controller.Customer;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.controller.Customer.EditCustomerController;
import cda.bibliotheque.dao.CustomerDAO;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Customer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ReadCustomerController {

    @FXML
    private TableColumn<Customer, Void> colActions;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, String> colFirstName;

    @FXML
    private TableColumn<Customer, String> colLastName;

    @FXML
    private TableView<Customer> customersTable;

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    public void initialize() {
        colFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstname()));
        colLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastname()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        colActions.setCellFactory(cell -> new TableCell<>() {
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Customer customerToEdit = customersTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("books/edit-book.fxml"));
                        Parent parent = loader.load();

                        EditCustomerController editCustomerController = loader.getController();
                        editCustomerController.setCustomer(customerToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.out.println(e.getCause());
                        System.err.println("Erreur lors de la création du bouton edit ->" + e);
                    }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Customer customerToDelete = customersTable.getItems().get(index);
                    customerDAO.deleteCustomer(customerToDelete.getId());
                    loadCustomers();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });

        loadCustomers();
    }

    private void loadCustomers() {
        customerList.setAll(customerDAO.getAllCustomers());
        customersTable.setItems(customerList);
    }

    @FXML
    void backToHomepage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void switchToCreate(ActionEvent event) throws  IOException {
        App.setRoot("customers/create-customer");
    }

}

package cda.bibliotheque.controller;

import java.io.IOException;

import cda.bibliotheque.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

        @FXML
    void switchToAuthor(ActionEvent event) throws IOException {
        App.setRoot("authors/authors");
    }

    @FXML
    void switchToBook(ActionEvent event) throws IOException {
        App.setRoot("books/books");
    }

    @FXML
    void switchToCustomer(ActionEvent event) throws IOException {
        App.setRoot("customers/customers");
    }

    @FXML
    void switchToSecondary(ActionEvent event) throws IOException {
        App.setRoot("authors/authors");
    }
}

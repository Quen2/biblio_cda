package cda.bibliotheque.controller.Book;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.dao.BookDAO;
import javafx.beans.property.SimpleObjectProperty;
import cda.bibliotheque.App;

public class EditBookController {

    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private ChoiceBox<String> colIsAvailable;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    public void initialize() {
        colIsAvailable.getItems().addAll("Disponible", "Pas disponible");
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputReleaseDate.setValue(newBook.getDate());
                inputTitle.setText(newBook.getTitle());
            }
        });
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setTile(inputTitle.getText());
        newBook.setDate(inputReleaseDate.getValue());
        newBook.setIsAvailable(colIsAvailable.getValue() == "Disponible" ? true : false);
        bookDAO.updateBook(newBook);
        App.setRoot("books/books");
    }

    public EditBookController() {}

    public void setBook (Book book) {
        this.book.set(book);
    }

}

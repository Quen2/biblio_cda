package cda.bibliotheque.controller.Book;

import java.io.IOException;
import cda.bibliotheque.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.App;

public class CreateBookController {

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setTile(inputTitle.getText());
        book.setDate(inputReleaseDate.getValue());
        bookDAO.addBook(book);
        App.setRoot("books/books");
    }

}

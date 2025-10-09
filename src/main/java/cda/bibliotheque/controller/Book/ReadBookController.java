package cda.bibliotheque.controller.Book;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cda.bibliotheque.App;

public class ReadBookController {

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> colIsAvailable;

    @FXML
    private TableColumn<Book, LocalDate> colReleaseDate;

    @FXML
    private TableColumn<Book, String> colTitle;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colIsAvailable.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIsAvailable() ? "Disponible" : "Pas disponible"));
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getDate()));
        
        loadBooks();
    }

    private void loadBooks() {
        bookList.setAll(bookDAO.getAllBooks());
        booksTable.setItems(bookList);
    }

    @FXML
    void SwitchToCreate(ActionEvent event) throws IOException {
        App.setRoot("books/create-book");
    }

}

package cda.bibliotheque.controller.Book;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cda.bibliotheque.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class ReadBookController {

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> colIsAvailable;

    @FXML
    private TableColumn<Book, Void> colActions;

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
        colActions.setCellFactory(cell -> new TableCell<>() {
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToEdit = booksTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("books/edit-book.fxml"));
                        Parent parent = loader.load();

                        EditBookController editBookController = loader.getController();
                        editBookController.setBook(bookToEdit);

                        App.getScene().setRoot(parent);
                    } catch (IOException e) {
                        System.out.println(e.getCause());
                        System.err.println("Erreur lors de la création du bouton edit ->" + e);
                    }
                });

                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToDelete = booksTable.getItems().get(index);
                    bookDAO.deleteAuthor(bookToDelete.getId());
                    loadBooks();
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

    @FXML
    void backToHomepage(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}

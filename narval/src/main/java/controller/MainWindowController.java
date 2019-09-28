package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Book;
import util.SearchBy;
import util.guice.PersistenceModule;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    @FXML
    private ComboBox searchByDropdown;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Book> resultTable;

    @FXML
    private TableColumn<Book, String> bookId;

    @FXML
    private TableColumn<Book, String> bookTitle;

    @FXML
    private TableColumn<Book, String> bookAuthor;

    @FXML
    private Button rentButton;

    @FXML
    private Button informationButton;

    private BookDAO bookDAO;

    private Boolean showRent = false;
    private Boolean showInformation = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));

        bookDAO = injector.getInstance(BookDAO.class);

        bookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));

        fillDropDown();
        rentButton.setText("Kölcsönzés");
        informationButton.setText("Információk és visszavitel");
        openRent();
        openInformation();
    }

    private void fillDropDown()
    {
        List<String> options  = new ArrayList<String>(Arrays.asList("Író","Cím","Kód"));
        ObservableList<String> dropdownOptions = FXCollections.observableArrayList(options);
        searchByDropdown.setItems(dropdownOptions);
    }

    private void openRent()
    {
        rentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent parent;
                try {
                    if (showRent == false) {
                        parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RentWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        showRent = true;
                        stage.setOnCloseRequest(windowEvent -> showRent = false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openInformation()
    {
        informationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent parent;
                try {
                    if (showInformation == false) {
                        parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/InformationWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        showInformation = true;
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent windowEvent) {
                                showInformation = false;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        if (searchByDropdown.getSelectionModel().getSelectedItem() != null) {
            List<Book> resultList = new ArrayList<>();
            String searchTerm = searchByDropdown.getSelectionModel().getSelectedItem().toString();
            if (searchTerm.equals("Író")) {
                resultList = bookDAO.bookRead(SearchBy.AUTHOR, searchField.getText());
            } else if (searchTerm.equals("Cím")) {
                resultList = bookDAO.bookRead(SearchBy.TITLE, searchField.getText());
            } else if (searchTerm.equals("Kód")) {
                resultList = bookDAO.bookRead(SearchBy.ID, searchField.getText());
            }
            if (resultList != null) {
                ObservableList<Book> result = FXCollections.observableArrayList(resultList);
                resultTable.setItems(result);
            } else {
                resultTable.getItems().clear();
            }
        }
    }
}

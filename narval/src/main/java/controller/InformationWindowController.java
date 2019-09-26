package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.BookDAO;
import dao.RentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import util.guice.PersistenceModule;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InformationWindowController implements Initializable {

    private BookDAO bookDAO;
    private RentDAO rentDAO;

    @FXML
    private ComboBox rentDropdown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        rentDAO = injector.getInstance(RentDAO.class);
        bookDAO = injector.getInstance(BookDAO.class);
        fillDropdownWithRents();
    }

    private void fillDropdownWithRents() {
        List<String> availableRents = rentDAO.getRentsWithoutEndDate();
        if (availableRents != null) {
            ObservableList<String> userDropdownItems = FXCollections.observableArrayList(availableRents);
            rentDropdown.setItems(userDropdownItems);
        }
    }
}

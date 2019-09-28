package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.BookDAO;
import dao.RentDAO;
import dao.UserDAO;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Book;
import model.Rent;
import model.User;
import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SearchBy;
import util.guice.PersistenceModule;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class InformationWindowController implements Initializable {

    private BookDAO bookDAO;
    private RentDAO rentDAO;
    private UserDAO userDAO;

    private MainWindowController mainWindowController;

    @FXML
    private ComboBox rentDropdown;

    @FXML
    private Button closeRentButton;

    @FXML
    private TextArea rentFeedback;

    private static Logger logger = LoggerFactory.getLogger(RentWindowController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));

        rentDAO = injector.getInstance(RentDAO.class);
        bookDAO = injector.getInstance(BookDAO.class);
        userDAO = injector.getInstance(UserDAO.class);

        fillDropdownWithRents();
    }

    private void fillDropdownWithRents() {
        List<String> availableRents = rentDAO.getRentsWithoutEndDate();
        if (availableRents != null) {
            ObservableList<String> rentDropdownItems = FXCollections.observableArrayList(availableRents);
            rentDropdown.setItems(rentDropdownItems);
            logger.info("Rent dropdown filled with: {}.", availableRents);
        }
    }

    private boolean checkInputValidity() {
        if (rentDropdown.getSelectionModel().getSelectedItem() == null) {
            rentFeedback.setText("Válassz ki egy kölcsönzést!");
            return false;
        } else {
            rentFeedback.setText("Sikeres visszavitel!");
            return true;
        }

    }

    @FXML
    public void closeRent(ActionEvent actionEvent) {
        if (checkInputValidity()) {
            String[] splitSelectedRent = rentDropdown.getSelectionModel().getSelectedItem().toString().split(" : ");

            Rent selectedRent = rentDAO.getRentById(splitSelectedRent[0]);
            logger.info(selectedRent.toString());

            bookDAO.updateAvailabilityToTrue(selectedRent.getBookId());

            rentDAO.updateRent(selectedRent);

            closeRentButton.setDisable(true);

            Stage stage = (Stage) closeRentButton.getScene().getWindow();

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
            delay.play();
        }
    }

    @FXML
    public void onSelectedRent(ActionEvent actionEvent) {
        String[] splitSelectedRent = rentDropdown.getSelectionModel().getSelectedItem().toString().split(" : ");

        Rent selectedRent = rentDAO.getRentById(splitSelectedRent[0]);
        logger.info(selectedRent.toString());

        User associatedUser = userDAO.getUserById(selectedRent.getUserId());
        Book associatedBook = bookDAO.bookRead(SearchBy.ID, selectedRent.getBookId()).get(0);

        rentFeedback.setText("Kölcsönzés sorszáma: " + selectedRent.getId() + "\n" +
                "Kölcsönzés dátuma: " + selectedRent.getStartDate() + "\n" +
                "Kölcsönzés határideje: " + selectedRent.getDeadline() + "\n" +
                "Olvasó: " + associatedUser.getName() + "\n" +
                "Olvasó olvasójegyszáma: " + associatedUser.getId() + "\n" +
                "Kölcsönzött könyv: " + associatedBook.getAuthor() + " - " + associatedBook.getTitle() + "\n" +
                "Kölcsönzött könyv azonosítója: " + associatedBook.getId());
    }
}

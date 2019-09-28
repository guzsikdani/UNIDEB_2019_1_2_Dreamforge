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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Rent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.guice.PersistenceModule;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RentWindowController implements Initializable {

    @FXML
    private ComboBox selectUserDropdown;

    @FXML
    private TextField bookIdField;

    @FXML
    private DatePicker deadlineDate;

    @FXML
    private TextArea feedback;

    @FXML
    private Button rentButton;

    private RentDAO rentDAO;

    private BookDAO bookDAO;

    private UserDAO userDAO;

    private static Logger logger = LoggerFactory.getLogger(RentWindowController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));

        rentDAO = injector.getInstance(RentDAO.class);
        bookDAO = injector.getInstance(BookDAO.class);
        userDAO = injector.getInstance(UserDAO.class);

        fillDropdownWithUsers();

    }

    private void fillDropdownWithUsers() {
        List<String> availableUsers = userDAO.getAllUsers();
        if (availableUsers != null) {
            ObservableList<String> userDropdownItems = FXCollections.observableArrayList(availableUsers);
            selectUserDropdown.setItems(userDropdownItems);
            logger.info("User dropdown filled with: {}.", availableUsers);
        }
    }

    private Date castDeadlineToDate(LocalDate localDate) {
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    private boolean checkInputValidity() {
        Date currentDate = new Date(System.currentTimeMillis());
        if (deadlineDate.getValue() == null) {
            feedback.setText("Add meg a lejárati időt!");
            return false;
        } else if (selectUserDropdown.getSelectionModel().getSelectedItem() == null) {
            feedback.setText("Add meg az olvasót!");
            return false;
        } else if (bookIdField.getText() == null) {
            feedback.setText("Add meg a kikölcsönzendő könyv sorszámát!");
            return false;
        } else if (!bookDAO.bookRead(util.SearchBy.ID, bookIdField.getText()).get(0).getAvailable()) {
            feedback.setText("A kiválasztott könyv nem elérhető!");
            return false;
        } else if (castDeadlineToDate(deadlineDate.getValue()).before(currentDate)) {
            feedback.setText("Adj meg egy érvényes lejárati időt!");
            return false;
        } else if (bookDAO.bookRead(util.SearchBy.ID, bookIdField.getText()).get(0) == null) {
            feedback.setText("Adj meg egy megfelelő könyv sorszámot!");
            return false;
        } else {
            feedback.setText("Sikeres kölcsönzés!");
            return true;
        }

    }

    @FXML
    public void addRent(ActionEvent actionEvent) {
        if (checkInputValidity()) {
            Date currentDate = new Date(System.currentTimeMillis());

            String bookId = bookIdField.getText();

            String userNameWithId = selectUserDropdown.getValue().toString();
            String[] splitUserName = userNameWithId.split(":");

            Date deadline = castDeadlineToDate(deadlineDate.getValue());

            Rent rent = new Rent();
            rent.setBookId(bookId);
            rent.setUserId(splitUserName[0]);
            rent.setStartDate(currentDate);
            rent.setEndDate(null);
            rent.setDeadline(deadline);

            rentDAO.persist(rent);

            bookDAO.updateAvailabilityToFalse(bookId);

            rentButton.setDisable(true);

            Stage stage = (Stage) rentButton.getScene().getWindow();

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
            delay.play();
        }
    }
}

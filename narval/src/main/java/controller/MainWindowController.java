package controller;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.SearchBy;

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
    private Button rentButton;

    @FXML
    private Button informationButton;

    private BookDAO bookDAO;

    private Boolean showrent = false;
    private Boolean showinformation = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                    if (showrent == false) {
                        parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RentWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        showrent = true;
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent windowEvent) {
                                showrent = false;
                            }
                        });
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
                    if (showinformation == false) {
                        parent = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/InformationWindow.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        showinformation = true;
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent windowEvent) {
                                showinformation = false;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

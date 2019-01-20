package simplenotepad;

import java.io.File;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.WeakEventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class NotePadInterfaceBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu mfile;
    protected final MenuItem inew;
    protected final MenuItem iopen;
    protected final MenuItem isave;
    protected final MenuItem iexit;
    protected final Menu medit;
    protected final MenuItem icut;
    protected final MenuItem icopy;
    protected final MenuItem ipaste;
    protected final MenuItem idelete;
    protected final MenuItem iselect;
    protected final Menu mhelp;
    protected final MenuItem iabout;
    protected final TextArea textArea;

    public NotePadInterfaceBase() {

        menuBar = new MenuBar();
        mfile = new Menu();
        inew = new MenuItem();
        iopen = new MenuItem();
        isave = new MenuItem();
        iexit = new MenuItem();
        medit = new Menu();
        icut = new MenuItem();
        icopy = new MenuItem();
        ipaste = new MenuItem();
        idelete = new MenuItem();
        iselect = new MenuItem();
        mhelp = new Menu();
        iabout = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        mfile.setMnemonicParsing(false);
        mfile.setText("File");

        inew.setMnemonicParsing(false);
        inew.setText("new");
        inew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY));

        iopen.setMnemonicParsing(false);
        iopen.setText("open");
        iopen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY));

        isave.setMnemonicParsing(false);
        isave.setText("save");
        isave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY));

        iexit.setMnemonicParsing(false);
        iexit.setText("Exit");
        iexit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_ANY));

        medit.setMnemonicParsing(false);
        medit.setText("Edit");

        icut.setMnemonicParsing(false);
        icut.setText("cut");
        icut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));

        icopy.setMnemonicParsing(false);
        icopy.setText("copy");
        icopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY));

        ipaste.setMnemonicParsing(false);
        ipaste.setText("paste");
        ipaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY));

        idelete.setMnemonicParsing(false);
        idelete.setText("delete");
        idelete.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY));

        iselect.setMnemonicParsing(false);
        iselect.setText("Select ALL");
        iselect.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_ANY));

        mhelp.setMnemonicParsing(false);
        mhelp.setText("Help");

        iabout.setMnemonicParsing(false);
        iabout.setText("About");
        iabout.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY));
        setTop(menuBar);

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        mfile.getItems().add(inew);
        mfile.getItems().add(iopen);
        mfile.getItems().add(isave);
        mfile.getItems().add(iexit);
        menuBar.getMenus().add(mfile);
        medit.getItems().add(icut);
        medit.getItems().add(icopy);
        medit.getItems().add(ipaste);
        medit.getItems().add(idelete);
        medit.getItems().add(iselect);
        menuBar.getMenus().add(medit);
        mhelp.getItems().add(iabout);
        menuBar.getMenus().add(mhelp);

        /**
         * file menu
         */
        iopen.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(new NotePad().getStage());
            if (selectedFile != null) {
                textArea.setText(selectedFile.getPath());
            }
        });

        isave.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showSaveDialog(new NotePad().getStage());
            if (selectedFile != null) {
                textArea.setText(selectedFile.getPath());
            }
            
        });

        inew.setOnAction((ActionEvent event) -> {
            
            Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("Exit");
        alertDialog.setHeaderText("Are you sure ?");
        Optional<ButtonType> option = alertDialog.showAndWait();

        if (option.get() == ButtonType.OK) {
            Platform.exit();
        }
            
            textArea.setText("");
        });
        iexit.setOnAction((ActionEvent event) -> {
            Closing();
        });

        /**
         * Edit menu
         */
        icopy.setOnAction((ActionEvent event) -> {
            textArea.copy();
        });

        icut.setOnAction((ActionEvent event) -> {
            textArea.cut();
        });

        ipaste.setOnAction((ActionEvent event) -> {
            textArea.paste();
        });
        
        idelete.setOnAction((ActionEvent event) -> {
            textArea.deleteText(textArea.getSelection());
        });
        iselect.setOnAction((ActionEvent event) -> {
            textArea.selectAll();
        });
        
        iabout.setOnAction((event) -> {
            Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
            alertDialog.setTitle("About Notepad");
            alertDialog.setHeaderText("First Notepad");
            alertDialog.setContentText("Designed By: Amr Saber");
            alertDialog.showAndWait(); 
        });
        
        
        
        

    }

    static  void Closing() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("Exit");
        alertDialog.setHeaderText("Are you sure ?");
        Optional<ButtonType> option = alertDialog.showAndWait();

        if (option.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

}

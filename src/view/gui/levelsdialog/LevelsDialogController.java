package view.gui.levelsdialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelsDialogController implements Initializable {
    @FXML
    Pane root;

    @FXML
    Text title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setFocusTraversable(true);
        LevelsDialogViewHelper.getInstance().configureTheLevelsDialog(root,title);
    }
}

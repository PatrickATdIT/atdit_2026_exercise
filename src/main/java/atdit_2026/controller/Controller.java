package atdit_2026.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {
  private static final Logger logger = LoggerFactory.getLogger( Controller.class );

  @FXML
  public void handleButtonClick( ) {
    logger.info( "Button wurde geklickt." );

    // Erstellen und Anzeigen der Dialogbox
    Alert alert = new Alert( Alert.AlertType.INFORMATION );
    alert.setTitle( "Begrüßung" );
    alert.setContentText( "Hallo Welt!" );
    alert.showAndWait( );
  }
}
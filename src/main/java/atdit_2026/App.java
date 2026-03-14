package atdit_2026;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

  @Override
  public void start( Stage primaryStage ) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader( getClass( ).getResource( "/view.fxml" ) );
    Scene scene = new Scene( fxmlLoader.load( ) );

    primaryStage.setTitle( "Übungsanwendung" );
    primaryStage.setScene( scene );
    primaryStage.show( );
  }

  static void main( ) {
    launch( );
  }
}
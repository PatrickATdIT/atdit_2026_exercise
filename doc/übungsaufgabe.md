# Erstellen einer JavaFX-App mit Maven

## Aufgabe

Implementieren Sie folgende Anforderungen in einer Java-Applikation.

### Funktional

Die App soll ein einfaches Fenster anzeigen, in dem sich ein Button befindet. Beim Betätigen desselben soll in einer
Dialogbox eine Hallo Welt!-Meldung ausgegeben werden.

### Technisch

Nutzen Sie JavaFX als UI-Framework und verwenden Sie FXML für die Definition der Oberflächen. Das Java Platform Module
System soll nicht benutzt werden. Der Programmablauf soll über SLF4J mit Log4j2 als Implementierung protokolliert
werden. Nutzen Sie Java 25 und Maven als Build-Tool. Das Projekt soll mithilfe von Git versioniert werden.

## Lösungsskizze

1. Erstellen Sie ein neues Java-Maven-Projekt mit Git-Repository.
2. Erstellen Sie eine `.gitignore`-Datei, die nur relevanten Quellcode berücksichtigt, und führen Sie einen
   Initialcommit durch.
3. Erstellen Sie einen neuen `development`-Branch und committen Sie ab jetzt nach jedem der folgenden Schritte.
4. Registrieren Sie die Abhängigkeiten für `JavaFX`, `SLF4J` und die `Log4j2`-Implementierung für `SLF4J` in der
   `pom.xml`.
5. Erstellen Sie die Benutzeroberfläche (Controller-Klasse und FXML-Datei) und implementieren Sie die funktionalen
   Anforderungen.
6. Erstellen Sie eine `App`-Klasse, die die `Application`-Klasse von JavaFX erweitert, das UI lädt und die
   `main`-Methode für den Programmstart enthält.
7. Ergänzen Sie das JavaFX-Maven-Plugin in der `pom.xml`, um die Anwendung zu verpacken. Registrieren Sie die
   `App`-Klasse als Hauptklasse in der Konfiguration des Plugins. Lassen Sie das Programm über das Plugin in der IDE
   ausführen.
8. Mergen Sie den `development`-Branch in den `master`-Branch und squashen Sie die Commits.

## Lösungsvorschlag

### 1. Projekt erstellen

Um das Projekt in IntelliJ mit Maven und Git zu initialisieren, erstellen Sie zunächst ein neues Java-Projekt. Stellen
Sie dabei sicher, dass als Java-JDK Version 25 ausgewählt ist. Als Build-Tool wählen Sie Maven. Aktivieren Sie außerdem
die Option, ein Git-Repository zu erstellen.

Stellen Sie sicher, dass die richtigen Maven-Koordinaten definiert sind. Klappen Sie dazu die „Advanced Settings“ auf.
Der Projektname wird direkt in die Artifact-ID abgebildet: Nennen Sie es `exercise_result_of_<ihr_name>`. Als Group-ID
können Sie `ATDIT_2026` nutzen.

### 2. .gitignore konfigurieren und Initial-Commit

Nicht alle Dateien müssen versioniert werden; zum Beispiel gehören IDE-spezifische Konfigurationen oder auch
Build-Ergebnisse nicht ins Repository, der Quelltext und die Ressourcen hingegen schon. Um ein sauberes Repository zu
gewährleisten, erstellen Sie im Stammverzeichnis Ihres Projekts eine Datei namens `.gitignore`, falls diese nicht schon
existiert. Diese stellt sicher, dass keine unnötigen Projektspezifika oder temporären Build-Dateien versioniert werden.

Fügen Sie in diese Datei den folgenden Inhalt ein:

```gitignore
# Maven Build-Ergebnisse
target/

# IntelliJ Projektdateien
.idea/
*.iml
*.iws
*.ipr

# Betriebssystem-Spezifika
.DS_Store
Thumbs.db
```

Nachdem Sie die Datei erstellt haben, öffnen Sie das Commit-Tool-Fenster in IntelliJ. Zu diesem Zeitpunkt sollten Sie
die relevanten Projektdateien (`.gitignore`, `pom.xml`) in der Changelist haben. Es ist möglich, dass bereits
ignorierte Dateien dort gelistet sind. Wenn das der Fall ist, öffnen Sie das Terminal und setzen Sie das Repository
mit dem Befehl `git reset` zurück. Danach sollte die Changelist zunächst leer sein. Fügen Sie anschließend die
einzig relevanten Dateien (abermals: `.gitignore`, `pom.xml`) wieder der Changelist hinzu (Rechtsklick auf die
Datei, dann „Add to VCS“; alternativ per Drag-and-Drop).

Markieren Sie die komplette Changelist, geben Sie eine Commit-Nachricht ein (z. B. „Initial Commit") und führen Sie den
Commit aus. Damit ist Ihr Projekt bereit.

### 3. Anlage eines Development-Branches

Erstellen Sie nun einen neuen Branch mit dem Namen `development`. Dieser dient als "Arbeitszweig" für ihre Änderungen.
Durch das separate Erfassen auf dem neuen Branch wird der `master`-Branch stabil gehalten. Um das umzusetzen, gehen Sie
wie folgt vor:

Öffnen Sie die Git-View. Wählen Sie dann den aktuellen Branch (typischerweise sollte dieser bei Ihnen `master` heißen,
aber er kann auch einen ähnlichen Namen haben wie etwa `main`) per Rechtsklick aus und wählen Sie „New Branch from ...“.
Benennen Sie den neuen Branch `development` und stellen Sie sicher, dass die Checkout-Option angewählt ist. Dadurch wird
der Branch beim Erstellen direkt ausgecheckt; andernfalls müssten Sie den Checkout selbst vornehmen, da sonst alle
Änderungen weiterhin in den `master`-Branch committet werden. Erstellen Sie den Branch anschließend über „Create“.

Ab diesem Moment führen Sie nach jedem erfolgreich abgeschlossenen Schritt einen Commit durch. Damit sichern Sie eine
historische Nachvollziehbarkeit Ihrer Änderungen und können, falls nötig, zu einem früheren Status des Projekts
zurückkehren. Stellen Sie sicher, dass Ihre Commit-Nachrichten kurz und präzise den jeweiligen Arbeitsschritt
beschreiben.

### 4. Abhängigkeiten einfügen

Das Projekt soll auf JavaFX laufen und eine Protokollierung über SLF4J mit Log4J2 realisieren. Dafür sind externe
Bibliotheken nötig:

- Für JavaFX werden `javafx-fxml` (FXML-Handling) und `javafx-controls` (Widgets und Application) benötigt.
- Für das Logging ist die API von SLF4J (`slf4j-api`) und eine entsprechende Implementierung von Log4J2
  (`log4j-slf4j2-impl`) erforderlich.

Die entsprechenden Maven-Koordinaten sind am einfachsten über das [MvnRepository](https://mvnrepository.com/) zu
ermitteln.

Um die benötigten Bibliotheken bereitzustellen, müssen diese in der `pom.xml` in den `dependencies` hinterlegt werden.
Öffnen Sie die `pom.xml` und fügen Sie die Abhängigkeiten wie folgt ein:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0)"
         xmlns:xsi="[http://www.w3.org/2001/XMLSchema-instance](http://www.w3.org/2001/XMLSchema-instance)"
         xsi:schemaLocation="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0) [http://maven.apache.org/xsd/maven-4.0.0.xsd](http://maven.apache.org/xsd/maven-4.0.0.xsd)">
    <modelVersion>4.0.0</modelVersion>

    [...]

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>25.0.2</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>25.0.2</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.17</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j2-impl</artifactId>
            <version>2.25.3</version>
        </dependency>
    </dependencies>
</project>
```

Im Anschluss aktualisieren Sie das Maven-Projekt (über das Symbol „Reload all Maven Projects“ in der Toolbar der
Maven-View).

Schließen Sie den Schritt ab, indem Sie die Änderungen mit einer passenden Commit-Nachricht committen, wie etwa: „adding
dependencies for javafx, slf4j, log4j2“.

### 5. Die Benutzeroberfläche anlegen und mit Logik versehen

Erstellen Sie im Verzeichnis `src/main/java` ein passendes Package (z. B. `atdit_2026.controller`) und darin eine neue
Klasse `Controller.java`. Diese Klasse implementiert später die UI-Interaktionen.

Legen Sie im Verzeichnis `src/main/resources` die FXML-Datei `view.fxml` an, die das Layout Ihrer Benutzeroberfläche
definiert. Implementieren Sie die Oberfläche wie folgt:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.VBox?>

<VBox xmlns:fx="[http://javafx.com/fxml](http://javafx.com/fxml)"
      fx:controller="atdit_2026.controller.Controller"
      alignment="CENTER"
      spacing="20"
      prefWidth="300"
      prefHeight="200">

    <Button text="Say Hello!" onAction="#handleButtonClick"/>
</VBox>
```

Die Grundlage der View (Root) ist eine `VBox`, die Objekte vertikal anordnet. Das Attribut `fx:controller` verbindet
einen Controller (bzw. "den" `Controller`) mit der View. Dies ist notwendig, damit der Controller später aufgefunden und
instanziiert werden kann. Die View enthält einen Button mit der Aufschrift „Say Hello!“ und bindet das Action-Event an
die Methode `handleButtonClick()` des angebundenen Controllers. Dadurch wird bei jedem Klick auf den Butten eben jene
Methode aufgerufen.

Realisieren Sie nun die funktionale Anforderung im Controller, indem Sie die Methode `handleButtonClick()`
implementieren. Ein Dialog soll eine „Hallo-Welt“-Nachricht ausgeben. Vergessen Sie dabei die Protokollierung nicht (zum
Beispiel eine Info-Message beim Erreichen der Methode).

```java
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

    Alert alert = new Alert( Alert.AlertType.INFORMATION );
    alert.setTitle( "Begrüßung" );
    alert.setContentText( "Hallo Welt!" );
    alert.showAndWait( );
  }
}
```

Nachdem Sie die Logik implementiert haben, führen Sie einen Commit durch, um den aktuellen Stand zu sichern. Eine
passende Nachricht wäre: „Implementing controller logic and FXML view“

### 6. App-Klasse erstellen

Die `App`-Klasse bildet den zentralen Einstiegspunkt Ihrer JavaFX-Anwendung. Sie muss die JavaFX-Klasse `Application`
erweitern und die `start()`-Methode überschreiben, deren Aufgabe sein soll, die FXML-Datei zu laden und "in Szene zu
setzen". Außerdem soll sie die `main`-Methode implementieren und als Einstiegspunkt dienen.

Erstellen Sie im Verzeichnis `src/main/java` im Paket `atdit_2026` die Klasse `App` und implementieren Sie sie wie
folgt:

```java
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

  public static void main( String[] args ) {
    launch( args );
  }
}
```

Die `main`-Methode ruft `launch()` auf, was den Lebenszyklus der JavaFX-Anwendung startet. In der `start()`-Methode, die
vom JavaFX-Framework aufgerufen wird, wird der `FXMLLoader` genutzt, um die `view.fxml` aus dem `resources`-Ordner zu
laden und als Inhalt der `Scene` zu setzen.

Nachdem Sie die Klasse erstellt haben, führen Sie einen Commit durch, um Ihren Fortschritt zu sichern. Eine passende
Nachricht wäre: „Adding App class to launch JavaFX application“.

### 7. JavaFX-Maven-Plugin konfigurieren

Um das Programm konsistent in der Entwicklungsumgebung zu starten, integrieren wir das `javafx-maven-plugin`. Dieses
Plugin übernimmt u.a. die Konfiguration des Modul-Pfads.

Fügen Sie also den folgenden Block in Ihre `pom.xml` gleich unterhalb der `<dependencies>` ein:

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-maven-plugin</artifactId>
            <version>0.0.8</version>
            <configuration>
                <mainClass>atdit_2026.App</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Nachdem Sie die `pom.xml` aktualisiert haben, führen Sie einen „Reload“ des Maven-Projekts aus.

Sie können die Anwendung nun direkt über das Maven-Plugin starten. Öffnen Sie dazu die Maven-View und suchen Sie unter
den Plugins nach dem JavaFX-Plugin. Dort finden Sie das Goal `javafx:run`. Ein Doppelklick darauf lässt die Applikation
starten. Alternativ lässt sich die App über die Konsole starten: `mvn javafx:run`

Sobald die Anwendung erfolgreich startet, führen Sie einen Commit durch: „Adding javafx-maven-plugin and verify
execution“.

### 8. Merging und Squashing

Die Entwicklung ist beendet, daher müssen die Codeänderungen noch in den Produktivcode (den `master`-Branch) eingemischt
werden. Dabei ist das Ziel, eine saubere Historie im `master`-Branch zurückzulassen, jedoch ohne diese durch sämtliche
Commits zu verunreinigen. Die Zwischencommits sollen zu einem logischen Commit zusammengefasst bzw. gesquasht werden.

1. Begeben Sie sich in die Git-View und checken Sie den `master`-Branch aus. Sämtliche Änderungen, die Sie gemacht
   haben, sind jetzt "verschwunden", da der `master`-Branch auf dem Stand von vor Ihren Änderungen ist.
2. Im Menü wählen Sie Git und dann Merge.
3. Im folgenden Dialog stellen Sie sicher, dass der `development`-Branch ausgewählt ist. Zusätzlich wählen Sie die
   Modify-Option "Create a single commit for all merged changes --squash". Klicken Sie dann auf Merge.

Im Ergebnis erhalten Sie alle Änderungen, die Sie im Branch `development` vorgenommen haben, in Ihrer Changelist
aufgeführt, diesmal aber im `master`-Branch. Der zugehörige Code ist auch "wieder aufgetaucht". Committen Sie diese
Änderungen, etwa mit der Commitnachricht "Merge development: Implement full JavaFX application with logging and
Maven plugin", um die Änderungen im `master`-Branch zu sichern.
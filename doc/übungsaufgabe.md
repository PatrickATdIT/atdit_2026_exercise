## Erstellen einer JavaFX-App mit Maven

### Aufgabe

Implementieren Sie folgende Anforderungen in einer Java-Applikation.

#### Funktional

Die App soll ein einfaches Fenster anzeigen, in dem sich ein Button befindet. Beim Betätigen desselben soll in einer
Dialogbox eine Hallo Welt!-Meldung ausgegeben werden.

#### Technisch

Nutzen Sie JavaFX als UI-Framework und verwenden Sie FXML für die Definition der Oberflächen. Das Programm soll
standalone, also außerhalb der IDE, ausführbar sein. Das Java Platform Module System, welches offiziell Java Platform
Module System (JPMS) genannt wird, soll nicht benutzt werden. Der Programmablauf soll über SLF4J mit Log4j2 als
Implementierung protokolliert werden. Nutzen Sie Java 25 und Maven als Buildtool. Das Projekt soll mithilfe von Git
versioniert werden.

### Lösungsskizze

1. Erstellen Sie ein neues Java-Maven-Projekt mit Git-Repository.
2. Erstellen Sie eine `.gitignore`-Datei, die nur relevanten Quellcode berücksichtigt, und führen Sie einen
   Initialcommit durch.
3. Erstellen Sie einen neuen `development`-Branch und committen Sie ab jetzt nach jedem der folgenden Schritte.
4. Registrieren Sie die Abhängigkeiten für `JavaFX`, `SLF4J` und die `Log4j2`-Implementierung für `SLF4J` in der
   `pom.xml`.
5. Erstellen Sie eine `Controller`-Klasse (noch ohne Implementierung).
6. Erstellen Sie die `FXML`-Datei im `resources`-Ordner und binden Sie den `Controller` ein.
7. Implementieren Sie den `Controller` entsprechend der funktionalen Anforderungen.
8. Erstellen Sie eine `App`-Klasse, die die `Application`-Klasse von JavaFX erweitert, das UI lädt und die `main`
   -Methode für den Programmstart enthält.
9. Ergänzen Sie das JavaFX-Maven-Plugin in der `pom.xml`, um die Anwendung zu verpacken. Registrieren Sie die `App`
   -Klasse als Hauptklasse in der Konfiguration des Plugins.
10. Lassen Sie das Programm über das Plugin in der IDE ausführen.
11. Mergen Sie den `development`-Branch in den Hauptzweig und squashen Sie die Commits.
12. Erstellen Sie eine ausführbare `jar`-Datei mithilfe des JavaFX-Plugins.
13. Führen Sie das Programm aus der Kommandozeile heraus aus.

### Lösungsvorschlag

#### 1. Projekt erstellen

Um das Projekt in IntelliJ mit Maven und Git zu initialisieren, erstellen Sie zunächst ein neues Java-Projekt. Stellen
Sie dabei sicher, dass als Java-JDK Version 25 ausgewählt ist. Als Build-Tool wählen Sie Maven. Aktivieren Sie außerdem
die Option, ein Git-Repository zu erstellen.

Stellen Sie sicher, dass die richtigen Maven-Koordinaten definiert sind. Klappen Sie dazu die „Advanced Settings“ auf.
Der Projektname wird direkt in die Artifact-ID abgebildet: Nennen Sie es `exercise_result_of_<ihr_name>`. Als Group-ID
können Sie `ATDIT_2026` nutzen.

#### 2. .gitignore konfigurieren und Initial-Commit

Nicht alle Dateien müssen versioniert werden, zum Beispiel gehören IDE-spezifische Konfigurationen oder auch
Build-Ergebnisse nicht ins Repository. Der Quelltext und Ressourcen aber schon. Um ein sauberes Repository zu
gewährleisten, erstellen Sie im Stammverzeichnis Ihres Projekts eine Datei namens `.gitignore`, falls diese nicht schon
existiert. Diese stellt sicher, dass keine unnötigen Projektspezifika oder (temporären) Build-Dateien versioniert
werden.

Fügen Sie in der Datei den folgenden Inhalt ein:

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
genau zwei Dateien in der Changelist haben: die `.gitignore` und die `pom.xml`. Es ist möglich, dass ignorierte Dateien
schon in Ihrer Changelist gelandet sind. Wenn das der Fall ist, öffnen Sie das Terminal und rufen setzen sie das
Repository mit dem Befehl `git reset` zurück. Danach sollte die Changelist wieder leer sein. Fügen Sie die `.gitignore`
und die `pom.xml` wieder der Changelist hinzu (Rechtsklick auf die Datei, dann `Add to VCS`; alternativ per Drag and
Drop).

Markieren Sie die komplette Changelist, geben Sie eine Commitnachricht ein ("Initial Commit") und führen Sie den Commit
aus.


package spaceinv.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

/*
        Menu for Space Invader Game

        ** Nothing to do here **
 */
public class SIMenu extends MenuBar {

    public SIMenu(EventHandler<ActionEvent> menuHandler) {

        Menu menuFile = createMenuFile();
        Menu menuMusic = createMenuMusic();

        // Add event handling for all menu items
        menuFile.getItems().forEach(item -> item.setOnAction(menuHandler));
        menuMusic.getItems().forEach(item -> item.setOnAction(menuHandler));

        this.getMenus().addAll(menuFile, menuMusic);
    }

    // TODO Fix selected
    private Menu createMenuFile() {
        Menu menuFile = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem stopItem = new MenuItem("Stop");
        MenuItem exitItem = new MenuItem("Exit");
        menuFile.getItems().addAll(newItem, stopItem, exitItem);
        return menuFile;
    }

   private Menu createMenuMusic() {
        Menu menuMusic = new Menu("Music");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem play = new RadioMenuItem("Play");
        RadioMenuItem noPlay = new RadioMenuItem("No Play");
        play.setToggleGroup(toggleGroup);
        play.setSelected(true);
        noPlay.setToggleGroup(toggleGroup);
        menuMusic.getItems().addAll(play, noPlay);
        return menuMusic;
    }

}

package com.lolmapeditor.leaguemapeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MapEditorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MapEditorApplication.class.getResource("map-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        MapEditorController controller = fxmlLoader.getController();
        controller.importStage(stage);
        stage.setTitle("LoL Map Cover Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
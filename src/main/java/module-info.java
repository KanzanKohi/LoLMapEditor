module com.lolmapeditor.leaguemapeditor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.lolmapeditor.leaguemapeditor to javafx.fxml;
    exports com.lolmapeditor.leaguemapeditor;
}
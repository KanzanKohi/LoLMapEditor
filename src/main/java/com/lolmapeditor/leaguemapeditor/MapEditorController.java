package com.lolmapeditor.leaguemapeditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

public class MapEditorController {

    private Stage stage;
    private File file;
    private BufferedImage image;
    @FXML
    private ImageView image_preview;

    @FXML
    protected void onOpenImageButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null){
            this.file = file;
        }

        BufferedImage image = ImageIO.read(this.file);
        BufferedImage mapJngl = ImageIO.read(new File("src/main/resources/com/lolmapeditor/leaguemapeditor", "map.png"));
        BufferedImage combined = new BufferedImage(279, 279, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = combined.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(mapJngl, 0, 0, 279, 279, null);
        graphics.setComposite(AlphaComposite.SrcIn);
        graphics.drawImage(image, 0, 0, 279, 279, null);
        graphics.dispose();
        this.image = combined;
        this.image_preview.setImage(convertBufferedImageToJavaFXImage(combined));
    }

    @FXML
    protected void onSaveImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.setInitialFileName("map_cover.png");
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null){
            try {
                ImageIO.write(this.image, "png", file);
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static Image convertBufferedImageToJavaFXImage(BufferedImage bufferedImage){
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(bufferedImage, 0, 0, bufferedImage.getHeight(), bufferedImage.getHeight(), null);

        int[] type_int_argb = ((DataBufferInt) newImage.getRaster().getDataBuffer()).getData();
        IntBuffer buffer = IntBuffer.wrap(type_int_argb);

        PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbPreInstance();
        PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer<>(newImage.getWidth(), newImage.getHeight(), buffer, pixelFormat);
        return new WritableImage(pixelBuffer);
    }

    public void importStage(Stage stage){
        this.stage = stage;
    }
}
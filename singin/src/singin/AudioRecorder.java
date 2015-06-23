package singin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class AudioRecorder implements Runnable {

    private TargetDataLine line;
    private File file;
    private AudioFormat audioFormat;
    private static final AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
    AudioInputStream audioInputStream;

    public void init(File file) {
        this.file = file;
        //Definition du format audio
        audioFormat = new AudioFormat(44100, 16, 2, true, true);

        // Test de compatibilité du format avec le systeme
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        if (!AudioSystem.isLineSupported(info)) {
            System.err.println("Le systeme ne supporte pas le format audio");
            return;
        }

        // On récupère une source audio dans le Line
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        line.stop();
        line.close();
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //On ouvre le canal d'enregistrement 
        try {
            line.open(audioFormat);
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
            return;
        }
        // On commence a écouter la carte son
        line.start();
        // On met dans un buffer ce que l'on capte dans la ligne
        audioInputStream = new AudioInputStream(line);
        try {
            //Il faut sauter certains bits du buffer pour ajuster la taille de
            //ce qui est enregistré avec la taille de ce qui est lu
            audioInputStream.skip(16000);
        } catch (IOException ex) {
            Logger.getLogger(AudioRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //On écrit dans le fichier ce qui est dans le buffer
            AudioSystem.write(audioInputStream, targetType, file);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            line.close();
            try {
                audioInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public TargetDataLine getLine() {
        return line;
    }

}

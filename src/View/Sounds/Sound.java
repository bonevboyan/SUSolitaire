package View.Sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound implements LineListener {
    private final String BUTTON_CLICK_SOUND_PATH = "src/assets/sounds/menuClick.wav";
    private final String CARD_CLICK_SOUND_PATH = "src/assets/sounds/clickCard.wav";
    private final String CARD_MOVE_SOUND_PATH = "src/assets/sounds/cardMove.wav";
    private final String VICTORY_SOUND_PATH = "src/assets/sounds/victory.wav";
    //private boolean playCompleted;

    //play sound by given file path
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            audioClip.start();

//            while (!playCompleted) {
//                // wait for the playback completes
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//            }

            //audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }

    }

    //common sounds
    public void playButtonClick() {
        play(BUTTON_CLICK_SOUND_PATH);
    }

    public void playCardClick() {
        play(CARD_CLICK_SOUND_PATH);
    }

    public void playCardMove() {
        play(CARD_MOVE_SOUND_PATH);
    }

    public void playVictory() {
        play(VICTORY_SOUND_PATH);
    }

    @Override
    public void update(LineEvent event) {
        //LineEvent.Type type = event.getType();

        //if (type == LineEvent.Type.START) {
            //System.out.println("Playback started.");

        //} else if (type == LineEvent.Type.STOP) {
            //playCompleted = true;
            //System.out.println("Playback completed.");
        //}

    }
    //* Example of playing a music file
    //* public static void main(String[] args) {
    //* String audioFilePath = "C:\\Users\\my\\Downloads\\menuClick.wav";
    //* Sound player = new Sound();
    //* player.play(audioFilePath);
    //* }
}


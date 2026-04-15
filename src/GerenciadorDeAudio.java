// Conteúdo do arquivo: GerenciadorDeAudio.java

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class GerenciadorDeAudio {

    public static void tocarSom(String audioFileName) {
        new Thread(() -> { // Roda a reprodução em uma nova thread
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream audioStream;
                URL audioUrl = GerenciadorDeAudio.class.getResource("/" + audioFileName); // Tenta como recurso

                if (audioUrl != null) {
                    audioStream = AudioSystem.getAudioInputStream(audioUrl);
                } else {
                    File audioFile = new File(audioFileName);
                    if (!audioFile.exists()) {
                        System.err.println("Erro de Áudio: Arquivo '" + audioFileName + "' não encontrado. Verifique o caminho e o nome.");
                        return;
                    }
                    audioStream = AudioSystem.getAudioInputStream(audioFile);
                }

                clip.open(audioStream);
                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                        clip.close();
                        try {
                            audioStream.close();
                        } catch (Exception e) {
                            System.err.println("Erro ao fechar audioStream para '" + audioFileName + "': " + e.getMessage());
                        }
                    }
                });

            } catch (Exception e) {
                System.err.println("Erro ao tocar o som '" + audioFileName + "': " + e.getMessage());
            }
        }).start(); // Inicia a thread
    }
}
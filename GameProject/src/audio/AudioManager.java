package audio;

import displays.ProgressBar;

import javax.swing.*;

public class AudioManager {
    public static void loadSounds() {
        final ProgressBar progressBar = new ProgressBar();

        JFrame frame = new JFrame("Loading");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(progressBar);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();

        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(0);
        });
        AudioPlayer.addBackgroundMusic(AudioConstants.BACKGROUND_MENU_MUSIC, AudioConstants.BACKGROUND_MENU_MUSIC_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(14);
        });
        AudioPlayer.addBackgroundMusic(AudioConstants.BACKGROUND_GAME_MUSIC, AudioConstants.BACKGROUND_GAME_MUSIC_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(28);
        });
        AudioPlayer.addGameSounds(AudioConstants.MENU_BUTTONS_SOUND, AudioConstants.MENU_BUTTONS_SOUND_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(42);
        });
        AudioPlayer.addGameSounds(AudioConstants.SWITCH, AudioConstants.SWITCH_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(56);
        });
        AudioPlayer.addGameSounds(AudioConstants.RIGHT_STATION, AudioConstants.RIGHT_STATION_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(70);
        });
        AudioPlayer.addGameSounds(AudioConstants.WRONG_STATION, AudioConstants.WRONG_STATION_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(84);
        });
        AudioPlayer.addGameSounds(AudioConstants.GAME_OVER, AudioConstants.GAME_OVER_PATH);
        SwingUtilities.invokeLater(() -> {
            progressBar.updateProgress(100);
        });

        frame.dispose();
    }
}

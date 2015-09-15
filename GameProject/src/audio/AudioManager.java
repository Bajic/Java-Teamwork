package audio;

public class AudioManager {
    public static void loadSounds() {
        AudioPlayer.addSound(AudioConstants.SWITCH, AudioConstants.SWITCH_PATH);

        AudioPlayer.addMusic(AudioConstants.BACKGROUND_MUSIC, AudioConstants.BACKGROUND_MUSIC_PATH);
    }
}

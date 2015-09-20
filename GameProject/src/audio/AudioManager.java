package audio;

public class AudioManager {
    public static void loadSounds() {
        AudioPlayer.addBackgroundMusic(AudioConstants.BACKGROUND_MENU_MUSIC, AudioConstants.BACKGROUND_MENU_MUSIC_PATH);
        AudioPlayer.addBackgroundMusic(AudioConstants.BACKGROUND_GAME_MUSIC, AudioConstants.BACKGROUND_GAME_MUSIC_PATH);
        AudioPlayer.addGameSounds(AudioConstants.MENU_BUTTONS_SOUND, AudioConstants.MENU_BUTTONS_SOUND_PATH);
        AudioPlayer.addGameSounds(AudioConstants.SWITCH, AudioConstants.SWITCH_PATH);
        AudioPlayer.addGameSounds(AudioConstants.RIGHT_STATION, AudioConstants.RIGHT_STATION_PATH);
        AudioPlayer.addGameSounds(AudioConstants.WRONG_STATION, AudioConstants.WRONG_STATION_PATH);
        AudioPlayer.addGameSounds(AudioConstants.GAME_OVER, AudioConstants.GAME_OVER_PATH);
    }
}

package audio;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    private static Map<String, Music> backgroundMusic = new HashMap<>();

    private static Map<String, Sound> gameSoundEffectsMap = new HashMap<>();

    public static void addBackgroundMusic(String key, String path) {
        try {
            backgroundMusic.put(key, new Music(path));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void addGameSounds(String key, String path) {
        try {
            gameSoundEffectsMap.put(key, new Sound(path));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String key) {
        switch (key) {
            case AudioConstants.MENU_BUTTONS_SOUND:
                gameSoundEffectsMap.get(key).play();
                break;
            case AudioConstants.SWITCH:
                gameSoundEffectsMap.get(key).play();
                break;
            case AudioConstants.RIGHT_STATION:
                gameSoundEffectsMap.get(key).play();
                break;
            case AudioConstants.WRONG_STATION:
                gameSoundEffectsMap.get(key).play();
                break;
            case AudioConstants.GAME_OVER:
                gameSoundEffectsMap.get(key).play();
        }

    }

    public static void playMusic(String key) {
        switch (key) {
            case AudioConstants.BACKGROUND_MENU_MUSIC:
                backgroundMusic.get(key).loop();
                break;
            case AudioConstants.BACKGROUND_GAME_MUSIC:
                backgroundMusic.get(key).loop();
                break;
        }
    }

    public static void stopMusic(String key) {
        backgroundMusic.get(key).stop();
    }
}

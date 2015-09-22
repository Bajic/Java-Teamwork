package game;

import enums.ColorType;
import enums.Difficulty;
import models.Train;
import utilities.DifficultyMultiplier;
import utilities.GlobalConstants;

public class TrainFactory {

    public static Train ProduceTrain(Difficulty difficulty) {
        double trainSpeed = GlobalConstants.TRAIN_SPEED;

        switch (difficulty) {
            case EASY:
                trainSpeed *= DifficultyMultiplier.EASY;
                break;
            case MEDIUM:
                trainSpeed *= DifficultyMultiplier.MEDIUM;
                break;
            case HARD:
                trainSpeed *= DifficultyMultiplier.HARD;
                break;
        }
        return new Train(ColorType.values()[GlobalConstants.RANDOM.nextInt(8)], trainSpeed);
    }
}

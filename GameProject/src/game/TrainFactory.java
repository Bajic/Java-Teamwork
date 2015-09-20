package game;

import models.ColorType;
import models.Train;

public class TrainFactory {
    private Engine engine;

    public TrainFactory(Engine engine){
        this.engine = engine;
    }

    public void ProduceTrain(Difficulty difficulty){
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

        this.engine.trains.add(new Train(ColorType.values()[this.engine.random.nextInt(8)], trainSpeed));
    }
}

package main.Game;

public class Question {
    private String riddle;
    private String answer;
    private String difficulty;

    public Question(String riddle, String answer, String difficulty) {
        this.riddle = riddle;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getRiddle() {
        return riddle;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "[" + difficulty.toUpperCase() + "] " + riddle;
    }
}

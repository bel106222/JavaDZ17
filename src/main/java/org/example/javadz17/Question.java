package org.example.javadz17;

public class Question {
    private String category;
    private String text;
    private String optionA;
    private String optionB;
    private String optionC;
    private char correct; // 'A', 'B' или 'C'

    public Question(String category, String text, String optionA, String optionB, String optionC, char correct) {
        this.category = category;
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correct = correct;
    }

    public String getCategory() { return category; }
    public String getText() { return text; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public char getCorrect() { return correct; }

    public void display() {
        System.out.printf("[%s] %s\n", category, text);
        System.out.printf("A) %s\nB) %s\nC) %s\n", optionA, optionB, optionC);
    }
}
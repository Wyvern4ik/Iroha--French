package com.example.chozabredtttttttto;

public class Language {
    private final String name;
    private final int flagResId;
    private final String greeting; // Приветствие на языке

    public Language(String name, int flagResId, String greeting) {
        this.name = name;
        this.flagResId = flagResId;
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public String getGreeting() {
        return greeting;
    }
}
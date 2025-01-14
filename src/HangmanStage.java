public enum HangmanStage {
    STAGE0 ("-----\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "-------"),

    STAGE1 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|\n" +
            "|\n" +
            "|\n" +
            "-------"),

    STAGE2 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|   |\n" +
            "|\n" +
            "|\n" +
            "-------"),

    STAGE3 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|  /|\n" +
            "|\n" +
            "|\n" +
            "-------"),

    STAGE4 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|  /|\\\n" +
            "|\n" +
            "|\n" +
            "-------"),

    STAGE5 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|  /|\\\n" +
            "|  /\n" +
            "|\n" +
            "-------"),

    STAGE6 ("-----\n" +
            "|   |\n" +
            "|   O\n" +
            "|  /|\\\n" +
            "|  / \\\n" +
            "|\n" +
            "-------");

    private final String representation;

    HangmanStage(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public HangmanStage nextStage() {
        // Получаем индекс текущего элемента
        int index = this.ordinal();
        // Проверяем, есть ли следующий элемент
        if (index + 1 < HangmanStage.values().length) {
            return HangmanStage.values()[index + 1];
        } else {
            return null; // Или можно выбросить исключение или вернуть что-то другое
        }
    }
}
public enum HangmanStage {
    STAGE0 ("""
            
            
            
            
            
            
            -------"""),
    STAGE1 ("""
            
            |
            |
            |
            |
            |
            -------"""),
    STAGE2 ("""
            -----
            |
            |
            |
            |
            |
            -------"""),

    STAGE3 ("""
            -----
            |   |
            |   O
            |
            |
            |
            -------"""),

    STAGE4 ("""
            -----
            |   |
            |   O
            |   |
            |
            |
            -------"""),

    STAGE5 ("""
            -----
            |   |
            |   O
            |  /|
            |
            |
            -------"""),

    STAGE6 ("""
            -----
            |   |
            |   O
            |  /|\\
            |
            |
            -------"""),

    STAGE7 ("""
            -----
            |   |
            |   O
            |  /|\\
            |  /
            |
            -------"""),

    STAGE8 ("""
            -----
            |   |
            |   O
            |  /|\\
            |  / \\
            |
            -------""");

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
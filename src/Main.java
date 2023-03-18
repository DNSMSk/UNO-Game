import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        generateOfDeck();
        System.out.println("How many players? : ");
        generatePlayers(scanner.nextInt());

    }

    public static void generatePlayers(int d) {
        Stack<Cards> deckOfCards = new Stack<>();
        int n = 1;
        Scanner scanner = new Scanner(System.in);
        deckOfCards.addAll(generateOfDeck());
        Player player1 = new Player();
        settingsOfPlayer(deckOfCards, scanner, player1);
        Player player2 = new Player();
        settingsOfPlayer(deckOfCards, scanner, player2);
        if(d>2 && d<8){
        switch (d){
            case 3:
                Player player3 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player3);

            case 4:
                Player player4 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player4);
            case 5:
                Player player5 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player5);
            case 6:
                Player player6 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player6);

            case 7:
                Player player7 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player7);

            case 8:
                Player player8 = new Player();
                settingsOfPlayer(deckOfCards, scanner, player8);
                break;
        }
        }

    }

    private static void settingsOfPlayer(Stack<Cards> deckOfCards, Scanner scanner, Player player) {
        System.out.println("Введите имя: ");
        player.setName("scanner.nextLine()");
        for (int i = 0; i < 7; i++) {
            player.Hand.push(deckOfCards.pop());
        }
        System.out.println(player.Hand);
    }

    public static Stack<Cards> generateOfDeck() {
        Stack<Cards> deckOfCards = new Stack<>();
        for (Cards.Face face : Cards.Face.values()) {
            for (Cards.Color color : Cards.Color.values()) {
                if (face == Cards.Face.BLOCK || face == Cards.Face.REVERSE || face == Cards.Face.PLUS_TWO) {
                    deckOfCards.push(new Cards(face, color));
                    deckOfCards.push(new Cards(face, color));
                } else if (face == Cards.Face.WHITE_CHANGE_COLOR || face == Cards.Face.CHANGE_COLOR || face == Cards.Face.PLUS_TEN) {
                    deckOfCards.push(new Cards(face, null));
                } else {
                    deckOfCards.push(new Cards(face, color));
                }
            }
        }
        for (Cards deckOfCard : deckOfCards) {
//            System.out.println(deckOfCard);
        }
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }

    static class Player {
        private String name;
        private int countOfCards = 7;
        public Stack<Cards> Hand = new Stack<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCountOfCards() {
            return countOfCards;
        }

        public void setCountOfCards(int countOfCards) {
            this.countOfCards = countOfCards;
        }
    }

    public record Cards(Main.Cards.Face face, Main.Cards.Color color) implements Comparable<Cards> {


        private enum Face {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, BLOCK, PLUS_TWO, PLUS_TEN, CHANGE_COLOR, WHITE_CHANGE_COLOR}

        private enum Color {RED, GREEN, BLUE, YELLOW}

        @Override
        public int compareTo(Cards card) {
            Face[] fValues = Face.values();
            List<Face> faces = Arrays.asList(fValues);


            if (faces.indexOf(this.face) < faces.indexOf(card.face())) {
                return -1;
            } else if (faces.indexOf(this.face) > faces.indexOf(card.face())) {
                return 1;
            } else if (faces.indexOf(this.face) == faces.indexOf(card.face())) {
                return String.valueOf(color).compareTo(String.valueOf(card.color()));
            }
            return 0;
        }

        @Override
        public String toString() {
            if (color == null) {
                return String.valueOf(face);
            } else {
                return face + " of " + color;
            }
        }
    }
}
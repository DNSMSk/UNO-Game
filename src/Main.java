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
        Scanner scanner = new Scanner(System.in);
        deckOfCards.addAll(generateOfDeck());
        Stack<Player> players = new Stack<>();
        for (int i =0;i<d; i++){
        players.push(new Player());
        settingsOfPlayer(deckOfCards, scanner, players.get(i));
        }
    }

    private static void settingsOfPlayer(Stack<Cards> deckOfCards, Scanner scanner, Player player) {
        System.out.println("Введите имя: ");
        player.setName(scanner.nextLine());
        for (int i = 0; i < 7; i++) {
            player.Hand.push(deckOfCards.pop());
        }
        System.out.printf("%s  has a   %s\n",player.getName(),player.Hand);

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

        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }

    static class Player {
        private String name;

        public Stack<Cards> Hand = new Stack<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    public record Cards(Main.Cards.Face face, Main.Cards.Color color)  {


        private enum Face {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, BLOCK, PLUS_TWO, PLUS_TEN, CHANGE_COLOR, WHITE_CHANGE_COLOR}

        private enum Color {RED, GREEN, BLUE, YELLOW}



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
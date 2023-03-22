import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players? : ");
        int countOfPlayers = scanner.nextInt();
        Stack<Player> players = generatePlayers(countOfPlayers, generateOfDeck());
        boolean isGameOver = false;
        while (!isGameOver) {
            if(countOfPlayers==1){
                isGameOver=true;
            }
            for (Player player :players ) {
                scanner.nextLine();
                System.out.println(player.name+"'s turn\n"+player.Hand);
                if(scanner.nextLine().equalsIgnoreCase("y")){
                    System.out.println("Enter a card index to remove");
                    player.Hand.remove(scanner.nextInt());
                }
                if (player.Hand.size()==0){
                    countOfPlayers--;
                    players.remove(player);
                }
            }
        }
    }

    public static Stack<Player> generatePlayers(int d, Stack<Cards> cardsDeck) {
        Scanner scanner = new Scanner(System.in);
        Stack<Player> players = new Stack<>();
        for (int i = 0; i < d; i++) {
            players.push(new Player());
            System.out.println("Введите имя: ");
            players.get(i).setName(scanner.nextLine());
            for (int j = 0; j < 7; j++) {
                players.get(i).Hand.push(cardsDeck.pop());
            }
            System.out.printf("%s  has a   %s\n", players.get(i).getName(), players.get(i).Hand);
        }
        return players;
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

    public record Cards(Main.Cards.Face face, Main.Cards.Color color) {


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
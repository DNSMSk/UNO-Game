import java.util.*;

public class Main {
    public static void main(String[] args) {
        generateOfDeck();

        generatePlayers();

    }

    public static void generatePlayers(){
        Player player1 = new Player();
        player1.setName("Denis");
        Stack<Cards> deckOfCards = new Stack<>();
        deckOfCards.addAll(generateOfDeck());
        for(int i = 0; i<7;i++){
        player1.Hand.push(deckOfCards.pop());}
        System.out.println(deckOfCards);
        System.out.println(player1.Hand);

    }
    public static Stack<Cards> generateOfDeck() {
        Stack<Cards> deckOfCards = new Stack<>();
        for (Cards.Face face : Cards.Face.values()) {
            for (Cards.Color color : Cards.Color.values()) {
                if (face == Cards.Face.BLOCK || face == Cards.Face.REVERSE || face == Cards.Face.PLUS_TWO ){
                    deckOfCards.push(new Cards(face, color));
                    deckOfCards.push(new Cards(face, color));
                } else
                if (face == Cards.Face.WHITE_CHANGE_COLOR || face == Cards.Face.CHANGE_COLOR || face == Cards.Face.PLUS_TEN) {
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

   static class Player{
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
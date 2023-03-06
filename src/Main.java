import java.util.*;

public class Main {
    public static void main(String[] args) {
        generateOfDeck();

    }

    private static void generatePlayers(){

    }
    private static void generateOfDeck() {
        List<Cards> deckOfCards = new ArrayList<>();
        for (Cards.Face face : Cards.Face.values()) {
            for (Cards.Color color : Cards.Color.values()) {
                if (face == Cards.Face.BLOCK || face == Cards.Face.REVERSE || face == Cards.Face.PLUS_TWO ){
                    deckOfCards.add(new Cards(face, color));
                    deckOfCards.add(new Cards(face, color));
                } else
                if (face == Cards.Face.WHITE_CHANGE_COLOR || face == Cards.Face.CHANGE_COLOR || face == Cards.Face.PLUS_TEN) {
                    deckOfCards.add(new Cards(face, null));
                } else {
                    deckOfCards.add(new Cards(face, color));
                }
            }
        }
        for (int i = 0; i < deckOfCards.size(); i++) {
            System.out.println(deckOfCards.get(i));
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
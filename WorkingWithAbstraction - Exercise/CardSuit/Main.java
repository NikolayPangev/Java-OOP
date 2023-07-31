package WorkingWithAbstraction_Exercis.CardSuit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Suits:");
        for (Suits suits : Suits.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", suits.ordinal(), suits.name());
        }
    }
}

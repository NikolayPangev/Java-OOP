package WorkingWithAbstraction_Exercise.CardRanks;


public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        for (Ranks suits : Ranks.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", suits.ordinal(), suits.name());
        }
    }
}

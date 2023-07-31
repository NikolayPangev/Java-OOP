package WorkingWithAbstraction_Exercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rank cardRank = Rank.valueOf(scanner.nextLine());
        Suit cardSuit = Suit.valueOf(scanner.nextLine());
        int cardPower = cardRank.getPower() + cardSuit.getPower();

        System.out.printf("Card name: %s of %s; Card power: %d", cardRank, cardSuit, cardPower);
    }
}

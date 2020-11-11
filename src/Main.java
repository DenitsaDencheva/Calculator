public class Main {

    public static void main(String[] args) {
        //vi ska skapa en instans av InterestCalculator klassen
        InterestCalculator interestCalculator = new InterestCalculator();

        //Vi ska fråga användaren om detaljerna av lånet, anropar följande:
        interestCalculator.gatherInformation();
        //Vi ska kalkylera räntan och samtidigt visa information om lånet per månad
        double interest = interestCalculator.calculateInterest();
        //Vi ska kalkylera räntan efter ränteavdrag
        interestCalculator.interestAfterDeduction(interest);
    }
}

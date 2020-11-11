import java.util.Scanner;

    public class InterestCalculator {

        /**
         * Vi använder scanner för att kunna läsa från terminalen
         */
        Scanner scanner = new Scanner(System.in);

        //variabel som ska spara lånesumman
        private double loan;
        //variabel som ska spara antalet år som lånet ska återbetalas
        private int years;
        //räntan som användaren har fått från banken
        private double interest;

        /**
         * Metoden samlar information om tre viktiga instansvariabler
         * - vi frågar om lånet och om det är en double sparar vi den i instansvariabeln
         * - vi frågar om antal år som lånet ska avbetalas, detta i integer
         * - vi frågar om räntan som användaren har fått, och sparas i double interest
         * om det blir fel när vi försöker hämta double eller int värde från scanner
         * vi ska visa ett felmeddelande i terminalen till användaren och försöka fråga igen
         * om vi har fått rätt information går vi ut från frågan med break och går till nästa fråga
         */
        public void gatherInformation() {
            while (true){
                try {
                    System.out.println("Hur många kronor ska du låna?");
                    this.loan = scanner.nextDouble();
                    break;
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Tyvärr fick vi inte information som vi kan hantera.. försök igen");
                }
            }


            while (true){
                try {
                    System.out.println("Hur många år kommer du att avbetala?");
                    this.years = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Tyvärr fick vi inte information som vi kan hantera... försök igen");
                }
            }


            while (true){
                try {
                    System.out.println("Vad har du för räntesats i procent?");
                    this.interest = scanner.nextDouble();
                    break;
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Tyvärr fick vi inte information som vi kan hantera... försök igen");
                }
            }


        }


        /**
         * Vi räknar ut räntan som användaren ska betala per månad
         * vi räknar ut amorteringen per månad och det som finns kvar att betalas av hela lånet
         * @return skickar den totala räntan under hela perioden som användaren ska betala till banken
         */
        public double calculateInterest() {
            //antal månader som lånet ska återbetalas
            int months = this.years * 12;
            //kostnad per månad, t.ex. 50000/12 månader
            double perMonth = this.loan/months;
            //det som finns kvar att betala, från början - viktigt så att vi inte räknar fel
            double leftToPay = this.loan;
            //totala räntan under hela perioden, börjar med 0
            double totalInterest = 0;

            System.out.println("======================================================================");
            for (int i = 1; i <= months; i++) {
                //räntan räknas enkelt, vi multiplicerar summan som finns kvar att betala med räntan och delar med 12 månader
                //t.ex. första månaden vi ska avbetala 50 000kr, 12 månader med 3% ränta det blir
                //(50000*0.03/12)=125kr
                double interestThisMonth = (leftToPay*(this.interest/100))/12;
                //vi summerar hela räntan som användaren ska betala under hela perioden
                totalInterest = totalInterest + interestThisMonth;

                //summan som vi redan har betalt
                double paid = i * perMonth;

                //det som är kvar att betala från lånet
                //skillnaden mellan hela lånet och det som vi har betalat t.o.m. nu
                leftToPay = this.loan-paid;

                //skriva ut informationen som vi har räknat t.o.m. nu
                String left = String.format("%.0f", leftToPay);
                String monthlyPayment = String.format("%.0f", perMonth);
                String interestPerMonth = String.format("%.0f", interestThisMonth);
                String totalEachMonth = String.format("%.0f", perMonth+interestThisMonth);

                System.out.println("|| Månad " + i + " || Amortering " + monthlyPayment + "kr || Ränta " + interestPerMonth + "kr || Att betala " + totalEachMonth + "kr ||Skuld " + left + "kr ||");

            }
            System.out.println("======================================================================");

            return totalInterest;
        }

        /**
         * Visa räntan efter ränteavdrag
         * @param totalInterest vi tar in räntan och räknar ut ränteavdraget
         */
        public void interestAfterDeduction(double totalInterest) {
            System.out.println("======================================================================");
            //räntan efter ränteavdrat räknas som ränta * 0.7
            //man får 30% tillbaka från skatteverket :)
            double interestAfterDeduction = totalInterest * 0.7;
            String resultat = String.format("%.0f", interestAfterDeduction);

            System.out.println("Räntan som du ska betala efter ränteavdrag är "+ resultat + "kr");
            System.out.println("======================================================================");
        }
    }

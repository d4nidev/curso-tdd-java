import java.util.Map;

class CoffeeMachine {
    private final Map<String, Double> drinkMakerPrices;
    private DrinkMaker drinkMaker;
    private String selectedDrink;
    private String sugarAmount = "";
    private String stick = "";
    private double moneyAmount = 0.0;

    CoffeeMachine(DrinkMaker drinkMaker, Map<String, Double> drinkMakerPrices) {
        this.drinkMaker = drinkMaker;
        this.drinkMakerPrices = drinkMakerPrices;
    }

    void make() {
        if (moneyAmount < drinkMakerPrices.get(selectedDrink)) {
            double pendingMoney = drinkMakerPrices.get(selectedDrink) - moneyAmount;
            drinkMaker.execute("M:" + String.format("%.2f", pendingMoney));
            return;
        }

        drinkMaker.execute(selectedDrink + ":" + sugarAmount + ":" + stick);
    }

    void coffee() {
        selectedDrink = "C";
    }

    void tea() {
        selectedDrink = "T";
    }

    void chocolate() {
        selectedDrink = "H";
    }

    void addSugar(String sugarAmount) {
        this.sugarAmount = sugarAmount;
        stick = "0";
    }

    void insertCoin(double moneyAmount) {
        this.moneyAmount += moneyAmount;
    }
}
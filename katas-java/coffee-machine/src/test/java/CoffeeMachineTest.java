import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoffeeMachineTest {

    private static final double COFFEE_PRICE = 0.6;
    private static final double TEA_PRICE = 0.5;
    private static final double CHOCOLATE_PRICE = 0.4;
    private static final double NOT_ENOUGH_MONEY_FOR_COFFEE = 0.59;
    private static final double NOT_ENOUGH_MONEY_FOR_TEA = 0.49;
    private static final double NOT_ENOUGH_MONEY_FOR_CHOCOLATE = 0.39;
    private CoffeeMachine coffeeMachine;
    private DrinkMaker drinkMaker;

    @Before
    public void setUp() {
        drinkMaker = mock(DrinkMaker.class);

        Map<String, Double> drinkMakerPrices = new HashMap<>();
        drinkMakerPrices.put("C", COFFEE_PRICE);
        drinkMakerPrices.put("T", TEA_PRICE);
        drinkMakerPrices.put("H", CHOCOLATE_PRICE);

        coffeeMachine = new CoffeeMachine(drinkMaker, drinkMakerPrices);
    }


    @Test
    public void make_coffee_solo() {
        coffeeMachine.insertCoin(COFFEE_PRICE);
        coffeeMachine.coffee();
        coffeeMachine.make();

        verify(drinkMaker).execute("C::");
    }

    @Test
    public void make_tea_solo() {
        coffeeMachine.insertCoin(TEA_PRICE);
        coffeeMachine.tea();
        coffeeMachine.make();

        verify(drinkMaker).execute("T::");
    }

    @Test
    public void make_chocolate() {
        coffeeMachine.insertCoin(CHOCOLATE_PRICE);
        coffeeMachine.chocolate();
        coffeeMachine.make();

        verify(drinkMaker).execute("H::");
    }

    @Test
    public void adds_sugar() {
        coffeeMachine.insertCoin(TEA_PRICE);
        coffeeMachine.tea();
        coffeeMachine.addSugar("2");
        coffeeMachine.make();

        verify(drinkMaker).execute("T:2:0");
    }

    @Test
    public void not_enough_money_for_coffee_sends_message() {
        coffeeMachine.insertCoin(NOT_ENOUGH_MONEY_FOR_COFFEE);
        coffeeMachine.coffee();
        coffeeMachine.addSugar("2");
        coffeeMachine.make();

        verify(drinkMaker).execute("M:0.01");
    }

    @Test
    public void not_enough_money_for_tea_sends_message() {
        coffeeMachine.insertCoin(NOT_ENOUGH_MONEY_FOR_TEA);
        coffeeMachine.tea();
        coffeeMachine.make();

        verify(drinkMaker).execute("M:0.01");
    }

    @Test
    public void not_enough_money_for_chocolate_sends_message() {
        coffeeMachine.insertCoin(NOT_ENOUGH_MONEY_FOR_CHOCOLATE);
        coffeeMachine.chocolate();
        coffeeMachine.make();

        verify(drinkMaker).execute("M:0.01");
    }
}

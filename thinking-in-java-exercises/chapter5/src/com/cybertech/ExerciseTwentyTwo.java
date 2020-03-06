package com.cybertech;

import static com.cybertech.ExerciseTwentyTwo.CURRENCY.NAIRA;

/**
 * Created by Yasholma on 26-Feb-20.
 */
class Currency {
    ExerciseTwentyTwo.CURRENCY currency;
    Currency(ExerciseTwentyTwo.CURRENCY currency) {
        this.currency = currency;
    }

    void describe() {
        switch (currency) {
            case NAIRA:
                System.out.println("This currency is for Nigeria");
                break;
            case DOLLAR:
                System.out.println("This currency is for USA");
                break;
            case POUND:
            case EURO:
                System.out.println("This currency is used in England");
                break;
            case YEN:
                System.out.println("This currency is used in Japan");
                break;
            case CEDI:
                System.out.println("This currency is for Ghana");
                break;
            default:
                System.out.println("Invalid currency");
        }
    }

}
public class ExerciseTwentyTwo {
    static enum CURRENCY  {
        NAIRA, DOLLAR, POUND, YEN, EURO, CEDI
    }

    public static void main(String[] args) {
        Currency naija = new Currency(CURRENCY.NAIRA);
        Currency england = new Currency(CURRENCY.POUND);
        Currency ghanaJelof = new Currency(CURRENCY.CEDI);

        naija.describe();
        england.describe();
        ghanaJelof.describe();
    }
}

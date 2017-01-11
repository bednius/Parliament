package main;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kreska on 08.01.17.
 */
public class Expense {
    @SerializedName("pola")
    private float[] cost;

    public float getSum() {
        float result = 0;
        for (float aCost : cost) {
            result += aCost;
        }
        return result;
    }

    public float getExpense(int i) {
        return cost[i];
    }

}

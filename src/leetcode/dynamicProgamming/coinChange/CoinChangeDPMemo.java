package leetcode.dynamicProgamming.coinChange;

/*

/*

Code
Testcase
Test Result
Test Result
322. Coin Change
Solved
Medium
Topics
conpanies icon
Companies
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
public class CoinChangeDPMemo {

    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount];
        int val = dfs(coins, amount, cache);
        return val;
    }

    public int dfs(int [] coins, int amount, int[] cache) {
        /// If amount becomes 0, the combination is valid and return 0;
        if(amount == 0) return 0;

        /// If amount falls below 0, the combination is not valid and return MAX VALUE
        if(amount < 0) return Integer.MAX_VALUE;

        if(cache[amount-1] != 0) {
            return cache[amount-1];
        }

        int minVal = Integer.MAX_VALUE;
        for(int i=0; i < coins.length; i++) {
            int result = dfs(coins, amount - coins[i], cache);
            if(result >=0 && result < minVal) {
                /// if result is not MAX, store minVal of result + 1 and minVal
                minVal = result + 1;
            }
        }

        cache[amount-1] = minVal == Integer.MAX_VALUE ? -1 : minVal;
        return cache[amount-1];
    }

    public static void main(String[] args) {
        CoinChangeDPMemo coinChange = new CoinChangeDPMemo();
        coinChange.coinChange(new int[]{5,2,1}, 11);
    }
}

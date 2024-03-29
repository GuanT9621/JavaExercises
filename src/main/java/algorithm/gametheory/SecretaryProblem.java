package algorithm.gametheory;

/**
 * 秘书问题（类似的名称有相亲问题、止步问题、见好就收问题、苏丹的嫁妆问题、挑剔的求婚者问题等）
 * https://zh.wikipedia.org/wiki/%E7%A7%98%E6%9B%B8%E5%95%8F%E9%A1%8C
 *
 * 要聘请一名秘书，有 n 个应聘者。每次面试一人，面试后就要及时决定是否聘他，如果当时决定不聘他，他便不会回来。
 * 面试后总能清楚了解应聘者的合适程度，并能和之前的每个人做比较。问什么样的策略，才使最佳人选被选中的概率最大。
 *
 * 这个问题的最优解是一个停止规则。在这个规则里，面试官会拒绝头 r - 1 个应聘者（令他们中的最佳人选为 应聘者 M），然后选出第一个比 M 好的应聘者。
 * 可见最优策略包含于这个系列的策略中。
 * （如果M在所有n个应聘者中也是最好的一个，那么这个策略将选不出任何人选）对于任意的截断值 r，最佳人选被选中的概率是：
 *
 *          r-1     n    1
 *  P(r) = ----- *  ∑  -----
 *           n     i=r  i-1
 *
 * 以下来自：https://zhuanlan.zhihu.com/p/30652564
 * 解题思路
 *  1 列出 n 个 item 的所有排列，既 n!     n      m!      组合：  n      m!
 *                                     A  =  --------        A  =  --------
 *                                      m     (m-n)!          m     n!(m-n)!
 *
 *  2 尝试策略
 *    策略0 放弃前 0 个人，后面取比其大的人，能取到最大值的概率为 1/10
 *    策略1 放弃前 1 个人，后面取比其大的人，能取到最大值的概率为 1/10 * (1/1 + 1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/8 + 1/9)
 *    策略2 放弃前 2 个人，后面取比其大的人，能取到最大值的概率为 2/10 * (1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/9)
 *    策略3 放弃前 3 个人，后面取比其大的人，能取到最大值的概率为 3/10 * (1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/9)
 *    。。。
 *    策略9 放弃前 9 个人，后面取比其大的人，能取到最大值的概率为 9/10 * (1/9)
 *
 *  3 最佳策略
 *    放弃 K 个人，选择其后第一个比 K 个人都强的候选人，这样的策略称为“策略 K”。
 *    已知 N = 4 时，最佳策略为策略 1。
 *        N = 5、6、7 时，最佳策略为策略 2。
 *        N = 8、9、10 时，最佳策略是策略 3。
 *        推而广之，可以证明 A 对于 N 个候选人， 策略 K(K > 0) 的成功概率为：
 *                 K 1    1     1           1
 *        P(N,K) = -(- + --- + --- + ... + ---)
 *                 N k   k+1   k+2         N-1
 *
 *    当 N 值较小时，我们毫不费力就能算出哪个是最佳策略(上述公式)
 *    实际上，有一种简单的算法，可以算出任意（足够大）N 值的最佳策略是策略 N/e。
 *    关于自然常数 e = 2.71828 = 1 + 1/1! + 1/2! + 1/3! + … 1/n! https://zh.wikipedia.org/wiki/E_(%E6%95%B0%E5%AD%A6%E5%B8%B8%E6%95%B0)
 *
 *  4 结论
 *    N 足够大时，策略 N/e 选到最优者的概率在 36.8% 左右（即 1/e）。
 *
 */
public class SecretaryProblem {

    static double sumProbability(double k, double n) {
        double sum = 0;
        for (double i=k; i<n; i++) {
            sum += 1/i;
        }
        return k/n * sum;
    }

    public static void main(String[] args) {
        double n = 10;
        System.out.println("Sum 0：" + 1D/n);
        for (int i = 1; i < n; i++) {
            System.out.println(("Sum " + i + " : ") + sumProbability(i, n));
        }

        System.out.println("N = " + n + "时, 策略(N/e) = " + n/2.71828 + ", 概率(1/e) = " + 1/2.71828);
    }

}

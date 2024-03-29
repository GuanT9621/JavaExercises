package leetcode;

/**
 * https://leetcode.cn/problems/cat-and-mouse-ii/
 *  猫和老鼠 II
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 *
 * 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 *
 * 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
 * 地板由字符 '.' 表示，玩家可以通过这个格子。
 * 墙用字符 '#' 表示，玩家不能通过这个格子。
 * 食物用字符 'F' 表示，玩家可以通过这个格子。
 * 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
 *
 * 猫和老鼠按照如下规则移动：
 * 老鼠 先移动 ，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
 * catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 *
 * 游戏有 4 种方式会结束：
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
 *
 * 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 true ，否则 false
 *
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] 只包含字符'C'，'M'，'F'，'.'和'#'。
 * grid 中只包含一个 'C' ，'M' 和 'F'。
 * 1 <= catJump, mouseJump <= 8
 *
 * 思路 最短路径 贪心算法 动态规划
 * 游戏的结束方式：谁先到达食物谁赢，鼠有1000步限制，相遇猫赢
 *
 */
public class N1728_hhh {

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        // 计算猫鼠距离食物的最短步数
        // 如果一样，尝试猫截老鼠，仅在 jump 一样时
        return false;
    }

}

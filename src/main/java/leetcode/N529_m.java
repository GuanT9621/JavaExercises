package leetcode;

/**
 * https://leetcode-cn.com/problems/minesweeper/
 * 让我们一起来玩扫雷游戏！
 * 给你一个大小为 m x n 二维字符矩阵 board ，表示扫雷游戏的盘面，其中：
 * 'M' 代表一个 未挖出的 地雷，
 * 'E' 代表一个 未挖出的 空方块，
 * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻，
 * 'X' 则表示一个 已挖出的 地雷。
 *
 * 给你一个整数数组 click ，其中 click = [clickr, clickc] 表示在所有 未挖出的 方块（'M' 或者 'E'）中的下一个点击位置（clickr 是行下标，clickc 是列下标）。
 *
 * 根据以下规则，返回相应位置被点击后对应的盘面：
 * 1 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X' 。
 * 2 如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的 未挖出方块'E' 都应该被递归地揭露。
 * 3 如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
 * 4 如果在此次点击中，若无更多方块可被揭露，则返回盘面。
 *
 * 如 输入：board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 * 输出：["B","1","E","1","B"],
 *      ["B","1","M","1","B"],
 *      ["B","1","1","1","B"],
 *      ["B","B","B","B","B"]
 *
 * 思路一 递归
 * 难点在 step 2 揭露。
 */
public class N529_m {

    public static char[][] updateBoard(char[][] board, int[] click) {
        int[][] box = {{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        int r = click[0];
        int c = click[1];
        if ('M' == board[r][c]) {
            board[r][c] = 'X';
        } else {
            int num = 0;
            for (int[] b : box) {
                int nr = r + b[0];
                int nc = c + b[1];
                if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) { // 边界判断
                    num += 'M' != board[nr][nc] ? 0 : 1;
                }
            }
            board[r][c] = num == 0 ? 'B' : (char)(num + '0');
            if ('B' == board[r][c]) { // 揭露
                for (int[] b : box) {
                    int nr = r + b[0];
                    int nc = c + b[1];
                    if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) { // 边界判断
                        if ('E' == board[nr][nc]) {
                            board = updateBoard(board, new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}};
        int[] click = new int[]{3,0};
        char[][] chars = updateBoard(board, click);
        for (char[] cs : chars) {
            for (char c : cs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

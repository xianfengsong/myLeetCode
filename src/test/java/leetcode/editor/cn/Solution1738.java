package leetcode.editor.cn;//给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
//
// 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
//标从 0 开始计数）执行异或运算得到。 
//
// 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[5,2],[1,6]], k = 1
//输出：7
//解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 
//
// 示例 2： 
//
// 输入：matrix = [[5,2],[1,6]], k = 2
//输出：5
//解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。 
//
// 示例 3： 
//
// 输入：matrix = [[5,2],[1,6]], k = 3
//输出：4
//解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。 
//
// 示例 4： 
//
// 输入：matrix = [[5,2],[1,6]], k = 4
//输出：0
//解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 1000 
// 0 <= matrix[i][j] <= 106 
// 1 <= k <= m * n 
// 
// Related Topics 数组 
// 👍 58 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1738 {

    public int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> hp = new PriorityQueue<>(k);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int top = i > 0 ? matrix[i - 1][j] : 0;
                int left = j > 0 ? matrix[i][j - 1] : 0;
                int topLeft = i > 0 && j > 0 ? matrix[i - 1][j - 1] : 0;
                int c = matrix[i][j] ^ top ^ left ^ topLeft;
                matrix[i][j] = c;
                //System.out.println("i=" + i + ",j=" + j + ",val=" + c);
                hp.add(c);
                if (hp.size() > k) {
                    hp.poll();
                }
            }
        }
        return hp.isEmpty() ? 0 : hp.peek();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

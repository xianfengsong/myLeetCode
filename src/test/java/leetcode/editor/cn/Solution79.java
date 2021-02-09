package leetcode.editor.cn;
//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 771 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution79 {

    char[][] mk;
    String s;

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        mk = new char[board.length][board[0].length];
        s = word;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (foo(board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean foo(char[][] board, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (k == s.length()) {
            return true;
        }
        if (mk[i][j] == 1) {
            return false;
        } else {
            mk[i][j] = 1;
        }
        boolean result = false;
        if (board[i][j] == s.charAt(k)) {
            mk[i][j] = 1;
            k += 1;
            result = foo(board, i - 1, j, k)
                    || foo(board, i + 1, j, k)
                    || foo(board, i, j - 1, k)
                    || foo(board, i, j + 1, k);
        }
        mk[i][j] = 0;
        return result;
    }
    public static void main(String []args){
        char [][] b = new char[3][3];
        b[0]="abc".toCharArray();
        b[1]="opq".toCharArray();
        b[2]="xyz".toCharArray();

        System.out.println(new Solution79().exist(b,"cba"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

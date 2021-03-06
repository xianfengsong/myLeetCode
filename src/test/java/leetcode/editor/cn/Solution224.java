package leetcode.editor.cn;
//实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 数学 
// 👍 456 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution224 {


    public int calculate(String s) {
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(1);
        int last = 0, sig = 1;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                //进位都想不到吗
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    last = last * 10 + (s.charAt(i) - '0');
                    i++;
                }
                ans += last * sig;
                last = 0;
            } else if (c == '+') {
                // + 号 和当前区间符号相同即可，不用反转
                sig = st.peek();
                i++;
            } else if (c == '-') {
                // - 号 区间符号 * -1
                sig = -st.peek();
                i++;
            } else if (c == '(') {
                //新区间开始记录符号
                st.push(sig);
                i++;
            } else if (c == ')') {
                //区间结束
                st.pop();
                i++;
            }else if (c == ' ') {
                i++;
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

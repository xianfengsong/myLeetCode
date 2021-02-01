package dp;
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
//
// 示例 1：
//
//
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
// 示例 4：
//
//
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//
//
// 示例 5：
//
//
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false
//
//
//
// 提示：
//
//
// 0 <= s.length <= 20
// 0 <= p.length <= 30
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
// Related Topics 字符串 动态规划 回溯算法
// 👍 1816 👎 0

/**
 * lc no.10
 * 注意审题 s和p需要完全匹配，a vs a*c 要返回false
 * 关键是 字符+*的处理，干脆记住有两种处理方式
 * 1. a* -> '' （直接丢弃），适用：a vs a*a = true
 * 2. a* -> a...a (用a*去匹配)，使用 aaa vs a* = true
 * 所以要把a*当作整体
 */
public class RegexMatch {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        //p empty?
        boolean eq = !s.isEmpty() && equals(s.charAt(0), p.charAt(0));
        // 下一个字符"*"，尝试1：跳过当前字符+"*" 尝试2：用当前字符重复1到n次取匹配s[i]
        if (p.length() >= 2 && '*' == p.charAt(1)) {
            // a vs a*a 这个case说明即使eq也要尝试放弃a*，即substring(2)
            return isMatch(s, p.substring(2)) || (eq && isMatch(s.substring(1), p));
            //错误
//            if(eq){
//                //s not empty
//                return isMatch(s.substring(1),p);
//            }else{
//                return isMatch(s,p.substring(2));
//            }
        } else {
            return eq && isMatch(s.substring(1), p.substring(1));
        }
    }

    private boolean equals(char s, char p) {
        return s == p || p == '.';
    }
}

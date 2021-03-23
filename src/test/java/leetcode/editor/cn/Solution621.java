package leetcode.editor.cn;//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个
//单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。 
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的 最短时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 示例 2： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
// 
//
// 示例 3： 
//
// 
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待
//命) -> (待命) -> A
// 
//
// 
//
// 提示： 
//
// 
// 1 <= task.length <= 104 
// tasks[i] 是大写英文字母 
// n 的取值范围为 [0, 100] 
// 
// Related Topics 贪心算法 队列 数组 
// 👍 609 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution621 {
    /**
     * 贪心策略出发，用几何解法得到解
     * 贪心：先为出现最多的任务分配时间片，然后向空闲时间片填充其他任务（这里想到了）
     * 然后，画出时间片的分配表格，二维数组（这里没想到，我用的链表，写了50行，结果还不对。。）
     * 数组的每一列是一个任务，每一行是时间片，最后求数组中有内容的节点数量，转换成求面积（几何好）
     * 最后一行要单独计算，因为执行到最后的任务，不需要再加n个时间间隔
     *
     * 特殊情况： 没用空隙时要单独处理，如果时间片完美占用（用时为最小值），那么用时为len(tasks)
     *           这里面积法会得到一个小于len(tasks)的值，如：AABB n=0 面积法=3
     * respect!估计很快就忘了
     * https://leetcode-cn.com/problems/task-scheduler/solution/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9/
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0, countMax = 0;
        Map<Character, Integer> counter = new HashMap<>();
        for (char t : tasks) {
            int count = counter.getOrDefault(t, 0) + 1;
            counter.put(t, count);
            maxCount = Math.max(count, maxCount);
        }
        for (Character c : counter.keySet()) {
            if (counter.get(c) == maxCount) {
                countMax += 1;
            }
        }
        return Math.max((maxCount - 1) * (n + 1) + countMax,tasks.length);
    }
    //时间优化，char的范围有限，用数组代替map
    public int leastInterval_(char[] tasks, int n) {
        int[] buckets = new int[26];
        for(int i = 0; i < tasks.length; i++){
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        int maxTimes = buckets[25];
        int maxCount = 1;
        for(int i = 25; i >= 1; i--){
            if(buckets[i] == buckets[i - 1])
                maxCount++;
            else
                break;
        }
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

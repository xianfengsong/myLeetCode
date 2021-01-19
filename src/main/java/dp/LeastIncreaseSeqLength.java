package dp;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
//
//
//
//
// 进阶：
//
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 二分查找 动态规划
// 👍 1280 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * LC No.300 最长上升子序列长度
 * 暴力：O(n^2)
 * 遍历每个节点（下标i）,把以n为尾节点的序列的长度保存到dp[i]，最后返回dp中的最大值
 * 计算以n为尾节点的序列长度时，需要遍历dp[0]~dp[i]
 * 优化：O(nlogn)
 * 把计算dp[i]时的过程从O(n)优化成O(logn),想到用二分法
 * 需要把dp[i]改成递增数组tail[i]，就可以使用二分查找， tail中的i代表序列长度，tail[i]=长度i的序列的尾节点
 */
public class LeastIncreaseSeqLength {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        List<Integer> seq = new ArrayList<>();
        for (int n : nums) {
            if (seq.isEmpty()) {
                seq.add(n);
                continue;
            }
            int tail = seq.get(seq.size() - 1);
            //因为n比当前序列尾节点大，扩展tail
            if (tail < n) {
                seq.add(n);
            } else {
                //找到n的位置，这样可以让更小的元素替换序列元素，让序列更紧凑
                int i = find(seq, n);
                seq.set(i, n);
            }
        }
        return seq.size();
    }

    //返回 list中大于等于t的最小值
    private int find(List<Integer> list, int t) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < t) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

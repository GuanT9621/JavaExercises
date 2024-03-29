package algorithm.排序;

/**
 * 冒泡排序
 *
 * 算法介绍
 * 冒泡排序是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 *
 * 算法描述
 * 1 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3 针对所有的元素重复以上的步骤，除了最后一个；
 * 4 重复步骤1~3，直到排序完成。
 *
 * 复杂度
 *  时间复杂度（平均）O(n^2)
 *  时间复杂度（最好）O(n^2)
 *  时间复杂度（最坏）O(n)
 *  空间复杂度 O(1)
 *  稳定性 稳定
 *
 * 思路：每一趟冒泡只浮出一个元素。
 */
class BubbleSort {

    static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) { // 循环次数为元素个数
            for (int j = 0; j < array.length - 1 - i; j++) { // 对比次数为未排序的元素个数
                // 每次只对比当前元素和下一个元素，然后交换。保证了下次对比时取到的当前元素必定是浮上来的那个元素。
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

}

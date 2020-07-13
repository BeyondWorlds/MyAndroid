package com.example.sort;

/**
 * Created by {wq} on 2018/4/17.
 */

public class Sort {
    /**
     * 冒泡排序
     *
     * @param data
     */
    public static void sortByBubble(int[] data) {

        for (int j = 0; j < data.length - 1; j++) {
            int flag = 0;
            for (int i = 0; i < data.length - 1 - j; i++) {
                if (data[i] > data[i + 1]) {
                    int temp = data[i + 1];
                    data[i + 1] = data[i];
                    data[i] = temp;
                    flag = 1;
                }
            }
            /**
             * flag标记,如果flag不发生变化，说明已经排序完成
             */
            if (flag == 0) {
                break;
            }
        }
    }


    /**
     * 快速排序
     * 注意1：递归有可能导致栈溢出
     * 注意2:基数可以选择中间的数，避免遇到已经排序的数效率低
     *
     * @param data
     * @param l    left
     * @param r    right
     */
    public static void sortByQuick(int data[], int l, int r) {
        if (l < r) {
            int i, j, x;
            i = l;
            j = r;
            x = data[i];
            while (i < j) {
                while (i < j && data[j] >= x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    data[i++] = data[j];
                while (i < j && data[i] <= x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    data[j--] = data[i];
            }
            data[i] = x;
            sortByQuick(data, l, i - 1); /* 递归调用 */
            sortByQuick(data, i + 1, r); /* 递归调用 */
        }
    }

    /**
     * 直接插入排序
     * break退出当前循环 注意放的位置
     *
     * @param data
     */
    public static void soryByInsert(int data[]) {
        int i, j, k;
        for (i = 1; i < data.length; i++) {
            for (j = 0; j <= i - 1; j++) {
                //后一个比前一个数字大，就保持不变,额，也可以改成后面比前面小才执行循环，就不用break了
                if (data[i] >= data[i - 1]) {
                    break;
                }
                int temp = data[i];
                //和最小的开始比，如果小于直接直接放它前面，所以上面的循环用就j++
                if (temp < data[j]) {
                    //j和后面的都后移一位
                    for (k = i - 1; k >= j; k--) {
                        data[k + 1] = data[k];
                    }
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param data
     */
    public static void sortByShell(int data[]) {
        int n = data.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            sortByInsertForShell(data, gap);
        }
    }

    private static void sortByInsertForShell(int data[], int gap) {
        for (int i = gap; i < gap * 2; i++) {
            //直接插入排序
            for (int j = i; j < data.length; j += gap) {
                if (data[j] < data[j - gap]) {
                    int temp = data[j];
                    for (int k = i - gap; k < j; k += gap) {
                        if (temp < data[k]) {
                            for (int m = j - gap; m >= k; m -= gap) {
                                data[m + gap] = data[m];
                            }
                            data[k] = temp;
                            //移动一次就可以结束当前循环了,除非把temp放进当前循环，就和上面的插入排序一样了，不过移动一次就已经排序好了
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * 选择排序
     *
     * @param data
     */
    public static void sortBySelect(int[] data) {

        for (int i = 0; i < data.length; i++) {
            int index = i;
            for (int j = i+1; j < data.length; j++) {
                if (data[j] < data[index]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(data, i, index);
            }
        }

    }

    /**
     * 交换数组位置,注意a!=b,否则数据为0
     */
    public static void swap(int data[], int a, int b) {
        data[a] = data[a] + data[b];
        data[b] = data[a] - data[b];
        data[a] = data[a] - data[b];
    }
}

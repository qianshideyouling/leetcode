public class Solution5 {
    public int divide(int dividend, int divisor) {
        int result = 0;
        if (dividend == 0) {
            return result;
        }
        int sum = 0;
        if (dividend > 0 && divisor == 1) {
            for (int i = 1; ; i = i + 1) {
                sum = sum + 1;
                if (sum == dividend) {
                    result = i;
                    break;
                }
            }
        } else if (dividend > 0 && divisor > 1) {
            for (int i = 1; ; i = i + 1) {
                sum = sum + divisor;
                if (sum == dividend) {
                    result = i;
                    break;
                }
                if (sum > dividend || sum < 0) {
                    result = i - 1;
                    break;
                }
            }
        } else if (dividend > 0 && divisor < 0) {
            for (int i = -1; ; i = i - 1) {
                sum = sum - divisor;
                if (sum == dividend) {
                    result = i;
                    break;
                }
                if (sum > dividend || sum < 0) {
                    result = i + 1;
                    break;
                }
            }
        } else if (dividend < 0 && divisor > 2) {
            for (int i = -1; ; i = i - 1) {
                sum = sum - divisor;
                if (sum == dividend) {
                    result = i;
                    break;
                }
                if (sum < dividend || sum > 0) {
                    result = i + 1;
                    break;
                }
            }

        } else if (dividend < 0 && divisor > 0) {
            for (int i = -1; ; i = i - 1) {
                sum = sum - divisor;
                if (sum == dividend) {
                    result = i;
                    break;
                }
            }
        } else {
            for (int i = 1; ; i = i + 1) {
                sum = sum + divisor;
                if (sum == dividend) {
                    if (Integer.MIN_VALUE == i) {
                        i = Integer.MAX_VALUE;
                    }
                    result = i;
                    break;
                }
                if (sum < dividend || sum > 0) {
                    result = i - 1;
                    break;
                }
            }
        }
        return result;
    }

    public int searchInsert(int[] nums, int target) {
        int position = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target > nums[i]) {
                position = i+1;
            }
            if (target == nums[i]) {
                return i;
            }
        }
        return position;
    }

    public int searchInsert2(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length-1]) {
            return nums.length;
        }
        while (true) {
            int num1 = nums[i];
            int num2 = nums[j];
            int middle = (i+j)/2;
            int num3 = nums[middle];
            if (num1 == target) {
                return i;
            }
            if (num2 == target) {
                return j;
            }
            if (j == i+1) {
                return j;
            }
            if (num3 > target) {
                j = middle;
            }
            if (num3 < target) {
                i = middle;
            }
            if (num3 == target) {
                return middle;
            }
        }
    }

    public int mySqrt(int x) {
        int i = 1;
        int j = x;
        if (x > 46341) {
            j = 46341;
        }

        while (true) {
            if (j == i + 1) {
                return i;
            }
            int middle = (i+j)/2;
            int rst = middle * middle;
            if (rst == x) {
                return middle;
            } else if (rst > x ) {
                j = middle;
            } else {
                i = middle;
            }
        }
    }

    public int mySqrt2(int x) {
        for (int i = 0; i < x; i++) {
            if (i*i> x) {
                return i-1;
            }
            if (i*i == x) {
                return i;
            }
        }
        return 0;
    }
}

class RemoveDuplicatesFromSortedArray {

  static class Solution {
      public int removeDuplicates(int[] nums) {
          int k = 0;
          for (int i = 0; i < nums.length; i++) {
              if (i != 0 && nums[i] != nums[i - 1]) {
                  k++;
                  nums[k] = nums[i];
              }
          }
          return k + 1;
      }
}

    public static void main(String args[]) {
          Solution solution = new Solution();
          int arr[] = {0,0,1,1,1,2,2,3,3,4};
          int k = solution.removeDuplicates(arr);
          int output[] = new int[k];
          for (int i = 0; i < k; i++) {
              output[i] = arr[i];
          }
          System.out.print("[");
          for (int i = 0; i < k; i++) {
              if (i != 0) {
                  System.out.print(",");
              }
              System.out.print(output[i]);
          }
          System.out.println("]");
    }
}

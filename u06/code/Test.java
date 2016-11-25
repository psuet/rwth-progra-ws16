import java.util.Arrays;

public class Test {
  public static int[] iterativeArrayIntersection(int[] a, int[] b) {
    int[] intersect = new int[0];

    for(int i = 0; i < a.length; ++i) {
      boolean contained = false;
      for(int j = 0; j < b.length; ++j) {
        if(b[j] == a[i]) {
          contained = true;
          break;
        }
      }
      if(contained) {
        int[] newIntersect = Arrays.copyOf(intersect, intersect.length + 1);
        newIntersect[intersect.length] = a[i];
        intersect = newIntersect;
      }
    }
    return intersect;
  }

  public static void main(String [] args) {
    int[] arr1 = {1, 4, 4, 5, 8, 19, 23, 42, 73};
    int[] arr2 = {1, 4, 5, 9, 17, 21, 42, 73};

    System.out.println("Input arrays:");
    System.out.println(Arrays.toString(arr1));
    System.out.println(Arrays.toString(arr2));
    System.out.println("Expected output (up to duplicate elements):");
    System.out.println(Arrays.toString(Test.iterativeArrayIntersection(arr1, arr2)));
    System.out.println("Actual output from recursive algorithm:");
    System.out.println(Arrays.toString(ArrayIntersect.arrayIntersection(arr1, arr2)));
    System.out.println("Actual output from recursive algorithm (optimized for sorted input):");
    System.out.println(Arrays.toString(ArrayIntersect.sortedArrayIntersection(arr1, arr2)));

  }
}

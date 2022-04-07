import java.util.Random;

public class Shellsort<Item extends Comparable> {
    public void shellSort(Item[] arr)
    {
        int h;
        System.out.print("Gap Sequence: ");
        for (h = 1; h < arr.length/9; h = 3*h + 1);
        for (;h > 0; h /= 3) {
            System.out.print(h + " ");
            for (int i = h; i < arr.length; i+= h)
            {
                int j = i;
                Item v = arr[i];
                while (j >= h && arr[j - h].compareTo(v) > 0)
                {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = v;
            }
        }
        System.out.print("\n");
    }

    Boolean checkSort(Item[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            if (arr[i+1].compareTo(arr[i]) < 0)
            {
                System.out.println("SORT FAILED.");
                return false;
            }
        }
        System.out.println("SORTS GOOD.");
        return true;
    }

    void printArray(Item[] arr)
    {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String[] args)
    {
        int arrLen = 200;
        Random rng = new Random();
        Shellsort<Integer> ss = new Shellsort();
        Integer[] arr = new Integer[arrLen];
        for (int i = 0; i < arrLen; i++)
            arr[i] = rng.nextInt(1500);
        ss.printArray(arr);
        ss.shellSort(arr);
        ss.printArray(arr);
        ss.checkSort(arr);
    }   
}

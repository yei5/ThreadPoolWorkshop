import java.net.*;
import java.io.*;
public class SortingThread implements Runnable {

    private final Socket clientSocket;

    public SortingThread (Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Enter the array separated by commas:");

            String[] array = in.readLine().split(",");
            int[] arr = new int[array.length];

            for(int i = 0; i< array.length;i++){
                arr[i] = Integer.parseInt(array[i]);
            }

            //Print the array received to verify
            out.println(printArray(arr));

            sort(arr,0, arr.length-1);

            out.println("Sorted array: "+printArray(arr));

            clientSocket.close();
            in.close();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String printArray(int[] array){
        StringBuilder message = new StringBuilder("[ ");
        for(int s : array){
            message.append(s).append(" ");
        }
        message.append("]");
        return message.toString();
    }

    public void sort(int[] arr, int left, int right){
        if(left < right){
            int middle = (left + right) / 2;

            sort(arr, left, middle);
            sort(arr, middle+1, right);

            merge(arr, left, middle, right);
        }
    }

    public void merge(int[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;


        int[] leftArray = new int [n1];
        int[] rightArray = new int [n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        for (int j=0; j < n2; j++) {
            rightArray[j] = arr[middle + j + 1];
        }


        int i = 0, j = 0;


        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }




}
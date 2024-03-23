import java.net.*;
import java.util.*;
import java.io.*;

public class SortingClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args){
        try{
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            //Array request
            System.out.println(in.readLine());

            String message = scanner.nextLine();
            out.println(message);

            //Array sent. To verify
            System.out.println(in.readLine());

            //Sorted array
            System.out.println(in.readLine());

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class SortingServer {
    private static final int SOCKET = 12345;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public SortingServer(){
        try{
            serverSocket = new ServerSocket(SOCKET);
            threadPool = Executors.newFixedThreadPool(10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        while(true){
            try{
                Socket clientSocket = serverSocket.accept();
                System.out.println("server open at "+ SOCKET);
                threadPool.execute(new SortingThread(clientSocket));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        SortingServer server = new SortingServer();
        server.start();
    }

}

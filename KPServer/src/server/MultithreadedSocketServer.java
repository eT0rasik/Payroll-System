package server;

import java.net.*;
import java.io.*;
public class MultithreadedSocketServer {
    public static void main(String[] args) throws Exception {
        try{
            System.out.println("Запуск сервера...");
            ServerSocket serverSocket = new ServerSocket(2525);
            int counter=0;
            System.out.println("Соединение установлено");
            while (true){
                counter++;
                Socket socket = serverSocket.accept();  // сервер принимает запрос на подключение клиента
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(socket, counter); // отправляем запрос в отдельный поток
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

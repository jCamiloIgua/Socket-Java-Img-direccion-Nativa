/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket.java.imagen.a.servidor;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author camil
 */
public class Servidor {
    public static void main(String[] args) throws IOException{
     JFrame jFrame = new JFrame("Servidor");
     jFrame.setSize(400,400);
     jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     JLabel jLabelText = new JLabel("Esperando imagen del cliente...");
     jFrame.add(jLabelText, BorderLayout.SOUTH); //parte inferior del frame
     
     jFrame.setVisible(true);
     
     //socket servidor
     
     ServerSocket serverSocket = new  ServerSocket(1234); //mismo puerto espera la escucha del puerto
     //porque el cliente se intentara conectar a este puerto
     //crear conexion 
     Socket socket = serverSocket.accept();
     
     //flujo de entrada
     InputStream inputStream = socket.getInputStream(); //podemos leer la informacion del cliente
     //bufer de entrada
     BufferedInputStream bufferedInputStream = new BufferedInputStream( inputStream);//mas eficiente los bytes
     
     //recuperar imagen del cliente
     BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);//leera el flujo de entrada que lo 
     //envolvimos en un bufer, se leera el flujo almacenado en el bufer
     //cerrar flujo 
     bufferedInputStream.close();
     //cerrar socket
     
     socket.close();
     //albergar imagen en label
     JLabel jLabelIma = new JLabel(new ImageIcon(bufferedImage));
     jLabelText.setText("Imagen recibida");
     jFrame.add(jLabelIma,BorderLayout.CENTER);
     
     
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package socket.java.imagen.a.servidor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;//pilas
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author camil
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        // coneccion de sockets a una maquina que sera servidor
        //Usado el protocolo TCP esto se hace con la clase socket en el lado del cliente
        /* se crea un socket en el extremo de comunicación del cliente, en intenta
        conectarce a un servidor creando la comunicacion entre dos maquinas
        dando su informacion de la red, y el puerto TCP en el que se esta comunicado
        para la comunicacion se usan flujos de entrada y salida, todo esto se hace con la 
        clase socket*/
        /* la clase necesita dos argumentos uno es la diraccion ip que sera el compu local
        sera el localhost y luego como segundo parametro el puerto de comunicacion que 
        queramos*/
        
        Socket unSocket = new Socket("localhost",1234);
        
        System.out.println("Conectado al servidor"); //verificar que el servidor local funciona
        //se usara java swing para la interfaz
        
        //contenedor contiene todos los elementos botones...
        //UN OBJETO QUE ME PERMITE USAR LOS METODOS DE LA CLASE DENTRO DEL PROGRAMA
        JFrame jframe = new JFrame("Cliente"); //titulo de la ventana de arriba
        jframe.setSize(400,400);//tamaño de ventana
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Al cerrar programa con la X
        
        //clase imagen de icono crea una imagen fija de gif, pnj o jpeg,
        //Usa como parametro la diraccion de la imagen a convertir en icono
        ImageIcon imagenIcon = new ImageIcon("C:\\Users\\camil\\OneDrive\\Imágenes\\6cc3c829-5e88-470c-bcbd-600bb30cec53.png");
        
        
        //Jlabel tendra la imagen 
        JLabel jlabelIcon = new JLabel(imagenIcon);
       // jlabelIcon.setSize(20,20); //tamaño de imagen del label
        JButton jButton = new JButton("Enviar imagen al servidor");
        //Agregar al frame
        jframe.add(jlabelIcon, BorderLayout.CENTER);//centar imagen
       
        jframe.add(jButton, BorderLayout.SOUTH); //parte inferior contenedor
        
        //ver frame
        jframe.setVisible(true);
        
        //funcionalidad boton
        
    
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //crear flujo de datos,  de salida, siempre esta conectado a una fuente
                //flujo es el conector y el destino sera el socket del servidor
                try{
                    OutputStream outputStream = unSocket.getOutputStream();
                    //se usara un bufer por eficiencia de la salida
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    //ek bufer se llenara con los bytes y luego cuando este lleno
                    //pasan al flujo de salida y se enviara al servidor
                    
                    //recuperar la imagen el icono de la imagen
                    //se creara la imagen almacenada en el bufer, el bufer permitira manipular datos de la imagen
                    Image image = imagenIcon.getImage();
                    
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight( null), BufferedImage.TYPE_INT_RGB);
                    //un obervador de imagen puede psat get w y get h, rastrea la carga de la imagen por la red
                    
                    //se Usara la clase de Graficos para obtener la imagen grafica dibujar imagen en el bufer
                    // parametro imagen  luego valor x y Y 
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image,0,0,null);
                    graphics.dispose();
                    
                    //se usara una clase para convertir informacion o datos en una forma de
                    //pasar el flujo, escriba la imagen renderizada en el bufer y luego toma el formato de la imagen
                    //luego la secuancia de salida
                    ImageIO.write(bufferedImage, "png",bufferedOutputStream );
                    
                    //cierre del bufer salida almacenada
                    bufferedOutputStream.close();
                    //cierre del socket
                    unSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });   
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import javax.swing.JFrame;
import model.User;
import view.Register;
import view.Home;
import view.Login;
import view.BoardClient;
import view.BoardAI;
/**
 *
 * @author admin
 */
public class client {
    public enum View{
        Login,
        Register,
        Home,
        BoardAI,
        BoardClient
    }
    //giao dien
    public static User user;
    public static Login login;
    public static Register register;
    public static Home home;
    public static BoardAI boardAI;
    public static BoardClient boardClient;
    //tao socket
    public static SocketHandle socketHandle;
    public static void main(String[] args) {
        
    }

}

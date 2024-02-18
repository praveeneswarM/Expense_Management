package exp.view;
import java.util.*;

import exp.controller.Authval;
public class Auth {
static Scanner sc=new Scanner(System.in);
static int count=0;
public static void SignIn()
{
    System.out.println("SignIn:");
    System.out.print("Enter Email:");
    String mail=sc.nextLine().trim().toLowerCase();
    if(!mail.contains("@gmail.com"))
    {
        System.out.println("Enter Valid Email");
        SignIn();
    }
    System.out.print("Enter Password:");
    String pass=sc.nextLine().trim();
    String ret=Authval.sin(mail,pass);
    if(ret!=null)
    {
        String g[]=ret.split(",");
        System.out.println();
        System.out.println("---Welcome "+g[1]+"! ---");
        System.out.println();
        u_menu.u_menu(g[0]);
    }
    else{
        System.out.println("Enter Valid Credentials!");
        count++;
        if(count<3)
        SignIn();
        else{
            System.out.println("Please SignUp as New User!");
            SignUp();
        }
    }
}



public static void SignUp()
{
    System.out.print("SignUp:");
    System.out.print("Enter Name:");
    String name=sc.nextLine().trim();
    System.out.print("Enter Email:");
    String mail=sc.nextLine().trim().toLowerCase();
    System.out.print("Enter Password");
    String pass=sc.nextLine().trim();
    System.out.print("Confirm password");
    String cpass=sc.nextLine().trim();
    String ret=Authval.sup(name,mail,pass,cpass);
    if(ret!=null)
    {
        if(ret.equals("true")){
            System.out.println("---User Added Succesfully---");
            SignIn();
        }
        else
        {
            System.out.println(ret);
            SignUp();
        }
    }
}
    public static void main(String args[])
    {
    }
}

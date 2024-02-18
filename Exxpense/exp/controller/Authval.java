package exp.controller;
import java.util.*;
import exp.view.Auth;
import exp.model.*;
public class Authval {
    static Scanner sc=new Scanner(System.in);
    public static String sin(String mail,String pass )
    {
        if(!mail.contains("@gmail.com"))
        {
            Auth.SignIn();
            return null;
        }
        else
        {
            String ret=user.signin(mail,pass);
            if(ret!=null){
                String g[]=ret.split(",");
                if(g[3].equals(pass))
                return ret;
                else 
                return null;
            }
            else
            return null;
        }
    }
    public static String sup(String name,String mail,String pass,String cpass)
    {
        if(!mail.contains("@gmail.com"))
        {
            return "Enter Valid Email!";
            
        }
        else if(name.equals("") || pass.equals("") || cpass.equals("") || mail.equals(""))
        {
            return "Fill All Details";
        }
        else if(!pass.equals(cpass))
        {
            return "Confirm and Password Should be same!";
        }
        else
        {
            int ret=user.signup(name,mail,pass);
            if(ret==1)
            return "true";
        }
        return null;
    }
}

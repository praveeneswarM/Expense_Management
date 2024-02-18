package exp.view;
import java.util.*;
public class Main {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Choice:");
        System.out.println("1.SignIn");
        System.out.println("2.SignUp");
        int x=sc.nextInt();
        switch (x) {
            case 1:{
                
                Auth.SignIn();
                break;
            }
            case 2:{
                Auth.SignUp();
                break;
            }
            default:
            {
                System.out.println("---Invalid Choice--");
                break;
            }
        }
    }
}

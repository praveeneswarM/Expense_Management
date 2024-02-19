package exp.view;
import java.util.*;

import exp.controller.*;
import exp.model.*;
public class u_menu {
    static Scanner sc=new Scanner(System.in);
   public static void u_menu(String u_id)
    {
        int n;
        do{
            System.out.println();
            System.out.println("User Menu:");
            System.out.println("1.Add Budget");
            System.out.println("2.Add Catogory");
            System.out.println("3.View Balance");
            System.out.println("4.Reset");
            System.out.println("5.Exit");
            System.out.println();
            System.out.print("Enter Choice:");
            n=sc.nextInt();
            switch(n)
            {
                case 1:{
                    AddBud(u_id);
                    break;
                }
                case 2:
                {
                    AddCat(u_id);
                    break;
                }
                case 3:
                {
                    ViwBal(u_id);
                    break;
                }
                case 4:
                {
                    Reset(u_id);
                }
                case 5:{
                    System.out.println("Exited Succesfully!!");
                    System.exit(0);
                }
                default:
                {
                    System.out.println("Enter Valid Choice");
                    break;
                }
            }
        }while(n!=5);
    }



    public static void AddBud(String u_id){
        sc.nextLine();
        System.out.print("Enter Amount:");
        String amt=sc.nextLine().trim();
        if(bud.addbud(u_id, amt)){
            System.out.println();
            System.out.println("---Budget Added Succesfully!---");
        }
        else
        {
            System.out.println();
            System.out.println("---try Again!!---");
            AddBud(u_id);
        }
    }


    public static void AddCat(String u_id)
    {
        sc.nextLine();
        ArrayList<ArrayList<Object>> resultList = new ArrayList<>();
        resultList=cat.CatView(u_id);
        System.out.println();
        System.out.println("Catogaries:");
        for (ArrayList<Object> rowData : resultList) {
            for (Object data : rowData) {
                System.out.print("\t"+data);
            }
            System.out.println();
        }

        System.out.print("Enter id of catogory:");
        String id=sc.nextLine().trim();
        System.out.print("Enter Amount:");
        String amt=sc.nextLine();
        if(id.equals("")||amt.equals(""))
        {
            System.out.println();
            System.out.println("---Enter Valid Details---");
            AddCat(u_id);
        }
        if(cat.Cval(id,amt,u_id)){
            System.out.println();
            System.out.println("---Details Updated---");
        }
        else{
            System.out.println();
            System.out.println("---Enter valid Details---");
        }
       
    }

    public static void ViwBal(String u_id)
    {
        System.out.println();
        System.out.println("Balance and Your Expense:");
        
        ArrayList<String> columnDataList = new ArrayList<>();
        columnDataList=bal.Bal(u_id);
        for(Object e:columnDataList)
        {
            System.out.println("\t"+e);
        }
    }

    public static void Reset(String u_id)
    {
        System.out.println();
        System.out.print("Remaning Balance: ");
        String ret=bal.reset(u_id);
        if(ret!=null)
        {
            System.out.print(ret);
            System.out.println();
        }
        else
        {
            System.out.println("---Try Again---");
        }
    }

}

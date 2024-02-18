package exp.controller;
import exp.model.*;
public class uval {
    public static boolean BudVal(String u_id,String amt,String std,String end)
    {
        int sc=0,en=0;
        String g[]=std.split("-");
        if(g[0].length()==4 && g[1].length()==2 && g[2].length()==2)
        sc++;
        String h[]=std.split("-");
        if(h[0].length()==4 && h[1].length()==2 && h[2].length()==2)
        en++;
        if(sc==1 && sc==en){
        if(bud.addbud(u_id, amt, std, end))
        return true;
        else
        return false;
        }
        return false;
        
    }
}

package festivalmanager.ticket;

import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;

import festivalmanager.economics.EconomicEntry;

public class Ticket{
    private String sort_str;
    private String number;
    private int priceCamping;
    private int priceDayticket;
    private int price;
    private Sort sort;

    public static enum Sort {
        DAYTICKET, 
        CAMPING, 
        EMPTY
    }
    
    public Ticket (String sort){
        this.sort = stringToSort(sort_str);
         if(this.sort == Sort.DAYTICKET){
           this.price = priceDayticket;
           TicketManager.setRemainingTicketsDayticket(TicketManager.getRemainingTicketsDayticket() - 1);
           //EconomicEntry ticket = new EconomicEntry(priceDayticket, "Ticket Tagesticket");
           //Accountancy.add(ticket);
         }
         if(this.sort == Sort.CAMPING){
           this.price = priceCamping;
           TicketManager.setRemainingTicketsCamping(TicketManager.getRemainingTicketsCamping() - 1);
         }

         int random;
         String random_str;

        do {
             random = (int) (Math.random()*1000000);
             random_str = intToString(random);
         } while (TicketManager.checkNumber(random_str) == true);

        this.number = random_str;
    }

    public static String sortToString(Sort sort){
        if(sort == Sort.DAYTICKET) {
            return "DAYTICKET";
        }
        if(sort == Sort.CAMPING) {
            return "CAMPING";
        }
        else{return "";}
    }

    public static Sort stringToSort(String string){
        if(string == null) return Sort.EMPTY;
        if(string.equals("DAYTICKET")){return Sort.DAYTICKET;}
        if(string.equals("CAMPING")){return Sort.CAMPING;}
        else{return Sort.EMPTY;}
    }

    public void setPriceCamping(int price){
        priceCamping = price;
    }

    public void setPriceDayticket(int price){
        priceDayticket = price;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public int getPriceCamping(){
        return priceCamping;
    }
    
    public int getPriceDayticket(){
        return priceDayticket;
    }

    public String getNumber(){
        return this.number;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public static String intToString(int integer){
        return ""+integer;
    }
}
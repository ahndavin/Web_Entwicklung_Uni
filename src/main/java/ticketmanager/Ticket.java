package ticketmanager;

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
         }
         if(this.sort == Sort.CAMPING){
           this.price = priceCamping;
         }

         int i;

        do {
             
         } while (condition);
        this.number = number;
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
}
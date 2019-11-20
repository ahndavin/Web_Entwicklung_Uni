package ticketmanager;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class TicketManager{
    public HashMap<String, Ticket> usedTickets;
    public static ArrayList<String> usedNumbers;
    private int remainingTicketsCamping;
    private int remainingTicketsDayticket;
    private boolean isStillSellingTickets;

    public TicketManager(){
        usedTickets = new HashMap<String, Ticket>();
        usedNumbers = new ArrayList<String>();
    }

    public void setIsStillSellingTickets(boolean bool){
        isStillSellingTickets = bool;
    }

    public void setRemainingTicketsCamping(int remaining){
        this.remainingTicketsCamping = remaining;
    }

    public void setRemainingTicketsDayticket(int remaining){
        this.remainingTicketsDayticket = remaining;
    }

    public boolean getIsStillSellingTickets(){
        return isStillSellingTickets;
    }

    public int getRemainingTicketsCamping(){
        return this.remainingTicketsCamping;
    }

    public int getRemainingTicketsDayticket(){
        return this.remainingTicketsDayticket;
    }

    public HashMap<String, Ticket> getAllTickets(){
        return usedTickets;
    }

    public void addTicket (Ticket ticket){
        usedTickets.put(ticket.getNumber(), ticket);
    }

    public ArrayList<String> getAllNumbers(){
        return usedNumbers;
    }

    public void addNumber (String number){
        usedNumbers.add(number);
    }

    public static boolean checkNumber(String number){
        if(usedNumbers.contains(number) == true){
            return true;
        }
        else{
            return false;
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class CustomDate {
    
    private final Date date;
    private final int weekDayN;
    private final String weekDay;
    private static final String[] weekDays={"DOMINGO","SEGUNDA","TERÇA","QUARTA","QUINTA","SEXTA","SÁBADO"};
    
    public CustomDate(Date date) {
        
        this.date=date;
        GregorianCalendar cal=new GregorianCalendar();
        cal.setTime(this.date);
        weekDayN=cal.get(Calendar.DAY_OF_WEEK)-1;
        weekDay=weekDays[weekDayN];
        
    }

    public Date getDate() {
        return date;
    }

    public int getWeekDayN() {
        return weekDayN;
    }

    public String getWeekDay() {
        return weekDay;
    }
    
    /**
     * Retorna um array com as datas presentes entre o período dado
     * @param startDate data de início
     * @param endDate data de fim
     * @param ignoreSaturdays true caso queira ignorar os domingos e false caso não queira ignorar
     * @return arrayList com as datas
     */
    public static ArrayList<CustomDate> getDatesByRange(Date startDate, Date endDate,boolean ignoreSaturdays) {
        
        ArrayList<CustomDate> dates=new ArrayList<>();
        Date actualDay = (Date) startDate.clone();
        Calendar actualDayAux = new GregorianCalendar(); //variável auxiliar
        actualDayAux.setTime(startDate);                 //seta o dia
        
        while (actualDay.compareTo(endDate) <= 0) {
            
            CustomDate date=new CustomDate(actualDay);
            
            if(!ignoreSaturdays || date.getWeekDayN()!=0)
                dates.add(date);
            
            actualDayAux.add(Calendar.DATE, 1);//soma uma dia
            actualDay = new Date(actualDayAux.getTime().getTime());
            
        }
        
        return dates;
        
    }
    
}

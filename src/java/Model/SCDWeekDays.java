/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe usada para armazenar os dias da semana que uma disciplina ocorre numa turma
 * @author Usuario
 */
public class SCDWeekDays {

    private CustomDate[] weekDays = new CustomDate[N_WEEK_DAYS];
    public static final int N_WEEK_DAYS=7;

    public void addDate(CustomDate date) {
        this.weekDays[date.getWeekDayN()] = date;
    }

    public CustomDate[] getWeekDays() {
        return weekDays;
    }
    public String getFormatedWeekDays()
    {
        String result="";
        ArrayList<CustomDate> weekDays =new ArrayList<>(Arrays.asList(this.weekDays));
        weekDays.removeAll(Collections.singleton(null));
        for(int i=0;i<weekDays.size();i++)
        {
            CustomDate currentDate=weekDays.get(i);
            result+=currentDate.getWeekDay();
            if(i==weekDays.size()-2) //se for o penultimo dia, escreve " E "
            {
                result=result+" E ";
            }
            else if(!(i==weekDays.size()-1))//senão, se não for o ultimo dia, escreve ", "
            {
                result=result+",";
            }
        }
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author chris
 */
public class Memories {
    private Memory todaysMemory;
    private Set<Memory> memories = new HashSet<>();
    
    public void setMemories(Set<Memory> memories) throws ParseException{
        this.memories = memories;
        Date todaysDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = ft.format(todaysDate);
        for(Memory mems : memories){
            if(strDate.equals(mems.getDate())){
                todaysMemory = mems;
            }
        }
        if(todaysMemory == null){
            todaysMemory = new Memory();
            todaysMemory.setDate(todaysDate);
        }
    }
    
    public Set<Memory> getMemories(){
        return this.memories;
    }
    
    public Memory getTodaysMemory(){
        return this.todaysMemory;
    }
    
    
}

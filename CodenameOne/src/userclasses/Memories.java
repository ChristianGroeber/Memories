/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chris
 */
public class Memories{

    private Memory todaysMemory;
    private ArrayList<Memory> memories = new ArrayList<>();
    private ArrayList<Memory> arrMemories = new ArrayList<>();
    private Boolean dev = false;

    public Memories() {

    }

    public void setMemories(ArrayList<String> memories) throws ParseException, IOException, java.text.ParseException {
        fromSet(memories);
        Date todaysDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = ft.format(todaysDate);
        System.out.println("strDate = " + strDate);
        for (Memory mems : arrMemories) {
            if (strDate.equals(ft.format(mems.getDate()))) {
                System.out.println("found today's memory");
                todaysMemory = mems;
            }
        }
        if (todaysMemory == null) {
            todaysMemory = new Memory();
            todaysMemory.setDate(ft.parse(ft.format(todaysDate)));
            System.out.println("created new memory for today");
        }
        arrMemories.add(todaysMemory);
    }

    private void fromSet(ArrayList<String> setMemories) {
        if (setMemories.isEmpty()) {
            Memory mem = new Memory();
            arrMemories.add(mem);
        } else {
            try {
                for (String i : setMemories) {
                    Memory mem = new Memory();
                    mem.fromString(i);
                    arrMemories.add(mem);
                }
            } catch (IOException | java.text.ParseException e) {
                if(dev){
                    Dialog.show("Error", "Error in Memories" + e.getMessage(), "OK", null);
                }
                System.out.println("Error in Memories" + e.getMessage());
            }catch (ParseException e){
                System.out.println("Parse Exception");
            }

        }
        this.memories = null; //TODO
    }

    public ArrayList<Memory> getMemories() {
        return this.arrMemories;
    }

    public Memory getTodaysMemory() {
        return this.todaysMemory;
    }

    public Set<String> toHashSet() {
        Set<String> memories = new HashSet<>();
        for (Memory i : arrMemories) {
            memories.add(i.toString());
        }
        return memories;
    }

    public Boolean isDev() {
        return dev;
    }

    public void setDev(Boolean dev) {
        this.dev = dev;
    }

}

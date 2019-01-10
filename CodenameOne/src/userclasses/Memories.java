/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chris
 */
public class Memories {

    private Memory todaysMemory;
    private ArrayList<Memory> memories = new ArrayList<>();
    private ArrayList<Memory> arrMemories = new ArrayList<>();

    public Memories() {

    }

    public void setMemories(ArrayList<String> memories) throws ParseException, IOException, java.text.ParseException {
        fromSet(memories);
        Date todaysDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = ft.format(todaysDate);
        for (Memory mems : arrMemories) {
            if (strDate.equals(ft.format(mems.getDate()))) {
                todaysMemory = mems;
            }
        }
        if (todaysMemory == null) {
            todaysMemory = new Memory();
            todaysMemory.setDate(ft.parse(ft.format(todaysDate)));
        }
    }

    private void fromSet(ArrayList<String> setMemories) throws IOException, java.text.ParseException, ParseException {
        if (setMemories.isEmpty()) {
            Memory mem = new Memory();
            arrMemories.add(mem);
        } else {
            for (String i : setMemories) {
                Memory mem = new Memory();
                mem.fromString(i);
                arrMemories.add(mem);
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

}

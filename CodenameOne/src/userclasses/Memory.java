/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.l10n.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author chris
 */
public class Memory {
    private final ArrayList<MyImage> images = new ArrayList<>();
    private final ArrayList<Note> notes = new ArrayList<>();
    private Date date = new Date();
    private String title = "";
    
    public Memory(){
        
    }
    
    public void addImage(MyImage image){
        images.add(image);
    }
    
    public void addNote(Note note){
        notes.add(note);
    }

    public ArrayList<MyImage> getImages() {
        return images;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
        String str = "";
        str += sd.format(date) + "///";
        for(MyImage i : images){
            str += i.toString() + "\\\\";
        }
        str += "///";
        
        for(Note i : notes){
            str += i.toString() + "\\\\";
        }
        str += "///";
        return str;
    }
}

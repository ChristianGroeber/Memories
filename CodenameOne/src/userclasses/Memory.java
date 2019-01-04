/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.ui.Image;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Memory {
    private final ArrayList<Image> images = new ArrayList<>();
    private final ArrayList<Note> notes = new ArrayList<>();
    
    public Memory(){
        
    }
    
    public void addImage(Image image){
        images.add(image);
    }
    
    public void addNote(Note note){
        notes.add(note);
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chris
 */
public class Notes {
    
    // Not sorted Set because equality is tested by "compareTo"
    private final Set<Note> notes = new HashSet<>();

    public Note[] getNotes() {
        Note[] notesArray = new Note[notes.size()];
        notesArray = notes.toArray(notesArray);
        //Arrays.sort(notesArray);
        return notesArray;
    }
    
    public void addNote(final Note note) {
        notes.add(note);        
    } 
    
    public void deleteNote(final Note note) {
        notes.remove(note);
    }
    
//    public Set<String> toSet(){
//        Set<String> setNotes = new HashSet<>();
//        for(Note i : notes){
//            
//        }
//        return setNotes;
//    }

}

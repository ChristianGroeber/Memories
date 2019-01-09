/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.Storage;
import java.util.HashMap;

/**
 *
 * @author chris
 */
public class KeyValue {
    public void save(Notes note){
        HashMap<String, String> map = new HashMap<>();
        Note notes[] = note.getNotes();
        for(Note i : notes){
            map.put(i.getTitle(), i.getText());
        }
        Storage.getInstance().writeObject("data", map);
    }
    
    public HashMap<String, String> load(){
        return ((HashMap)Storage.getInstance().readObject("data"));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.Storage;
import com.codename1.ui.Dialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chris
 */
public class KeyValue extends Memories {

    public void save(Notes note) {
        HashMap<String, String> map = new HashMap<>();
        Note notes[] = note.getNotes();
        for (Note i : notes) {
            map.put(i.getTitle(), i.getText());
        }
        Storage.getInstance().writeObject("data", map);
    }

    public void save(Memories mem) {
        Dialog ip = new InfiniteProgress().showInfiniteBlocking();
        HashMap<String, String> map = new HashMap<>();
        ArrayList<Memory> memories = mem.getMemories();
        for (Memory i : memories) {
            if (i.getPath() == null) {
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                i.setPath(sd.format(i.getDate()));
            }
            map.put(i.getPath(), i.toString());
        }
//        if(super.isDev()){
        Dialog.show("I'm now saving", map.toString(), "OK", null);
//        }
        System.out.println("I'm now Saving: " + map.toString());
        Storage.getInstance().writeObject("memories", map);
        ip.dispose();
    }

    public ArrayList<String> load() {
        Dialog ip = new InfiniteProgress().showInfiniteBlocking();
        System.out.println("loading");
        Memories mems = new Memories();
        ArrayList<String> ret = new ArrayList<>();
        HashMap<String, String> memories = (HashMap) Storage.getInstance().readObject("memories");
        try {
            for (Map.Entry<String, String> entry : memories.entrySet()) {
                String key = entry.getKey();
                ret.add(entry.getValue());
            }
        } catch (NullPointerException e) {
            if (super.isDev()) {
                Dialog.show("Error", "Error in load" + e.toString(), "OK", null);
            }
            System.out.println("Error in load" + e.toString());
        }
        ip.dispose();
        return ret;
    }
}

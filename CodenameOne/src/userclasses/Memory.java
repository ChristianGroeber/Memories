/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author chris
 */
public class Memory extends Memories {

    private final ArrayList<MyImage> images = new ArrayList<>();
    private final ArrayList<Note> notes = new ArrayList<>();
    private Date date = new Date();
    private String title = " ";
    private String path;
    private ArrayList<MyImage> savedImages = new ArrayList<>();
    private ArrayList<Note> savedNotes = new ArrayList<>();
    private ArrayList<String> savedInfo = new ArrayList<>();

    public Memory() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addImage(MyImage image) {
        images.add(image);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public ArrayList<MyImage> getImages() {
        return images;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private final SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
    private final SimpleDateFormat pathName = new SimpleDateFormat("dd-MM-yyyy");
    private final String SPL = "^^";

    @Override
    public String toString() {
        String str = "";
        path = pathName.format(date);
        str += sd.format(date) + SPL;

        if (images.isEmpty()) {
            str += " ";
            System.out.println("imgages are empty, inserting empty string");
        } else {
            for (int i = 0; i < images.size(); i++) {
                if (images.get(i).getImagePath() != null) {
                    str += images.get(i).toString() + "\\\\\\";
                } else {
                    images.remove(i);
                }
            }
        }
        if (super.isDev()) {
            Dialog.show("Images", "" + images.size(), "OK", null);
        }
        System.out.println("Images length: " + images.size());
        str += SPL;

        if (notes.isEmpty()) {
            str += " " + "\\\\\\";
        } else {
            for (Note i : notes) {
                str += i.toString() + "\\\\\\";
            }
        }

        if (title.equals(" ")) {
            title = sd.format(date);
        }
        str += SPL + title + SPL;
        return str;
    }

    public void fromString(String strMemory) throws IOException, ParseException, com.codename1.l10n.ParseException {
//        title = strMemory;
        System.out.println("this is the string i have to take a memory out of: " + strMemory);
        StringTokenizer tokenizer = null;
        try {
            tokenizer = new StringTokenizer(strMemory, SPL);
            date = sd.parse(tokenizer.nextToken());
            String strImages = tokenizer.nextToken();
            String strNotes = tokenizer.nextToken();
            try {
                title = tokenizer.nextToken();
            } catch (Exception e) {
                //tokenizer.nextToken();
                if (super.isDev()) {
                    Dialog.show("Error with tokenizer", "Error " + e.toString(), "OK", null);
                }
            }
            Dialog.show("This is what the Memory generated", sd.format(date) + "\n" + strImages + "\n" + strNotes, "OK", null);
            createImages(strImages);
            createNotes(strNotes);
        } catch (Exception e) {
            System.out.println("Couldn't split Strings with " + SPL);
        }
    }

    private void createImages(String images) throws IOException, ParseException {
        StringTokenizer tokenizer = new StringTokenizer(images, "\\\\\\");
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            System.out.println("tok = " + tok);
            MyImage myImage = new MyImage().fromString(tok);
            this.images.add(myImage);
        }
    }

    private void createNotes(String notes) throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(notes, "\\\\\\");
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            System.out.println("tok = " + tok);
            Note note = new Note().fromString(tok);
            this.notes.add(note);
        }
    }

    public boolean isSaved(String info) {
        for (String i : savedInfo) {
            if (i.equals(info)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSaved(MyImage img) {
        for (MyImage i : savedImages) {
            if (i.equals(img)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSaved(Note not) {
        for (Note i : savedNotes) {
            if (i.equals(not)) {
                return true;
            }
        }
        return false;
    }
}

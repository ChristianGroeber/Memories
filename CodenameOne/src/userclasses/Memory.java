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
public class Memory extends Memories{

    private final ArrayList<MyImage> images = new ArrayList<>();
    private final ArrayList<Note> notes = new ArrayList<>();
    private Date date = new Date();
    private String title = " ";
    private String path;

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

    @Override
    public String toString() {
        String str = "";
        path = pathName.format(date);
        str += sd.format(date) + "///";
        if (images.isEmpty()) {
            str += " ";
        }
        for (MyImage i : images) {
            str += i.toString() + "\\\\";
        }
        str += "///";

        for (Note i : notes) {
            str += i.toString() + "\\\\";
        }
        if (notes.isEmpty()) {
            str += " ";
        }
        str += "///" + title + "///";
        return str;
    }

    public void fromString(String strMemory) throws IOException, ParseException, com.codename1.l10n.ParseException {
        StringTokenizer tokenizer = new StringTokenizer(strMemory, "///");
        date = sd.parse(tokenizer.nextToken());
        String strImages = tokenizer.nextToken();
        String strNotes = tokenizer.nextToken();
        try {
            title = tokenizer.nextToken();
        } catch (Exception e) {
            if(super.isDev()){
                Dialog.show("Error with tokenizer", "Error " + e.toString(), "OK", null);
            }
        }
        createImages(strImages);
        createNotes(strNotes);
    }

    private void createImages(String images) throws IOException, ParseException {
        StringTokenizer tokenizer = new StringTokenizer(images, "\\\\");
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            System.out.println("tok = " + tok);
            MyImage myImage = new MyImage().fromString(tok);
            this.images.add(myImage);
        }
    }

    private void createNotes(String notes) throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(notes, "\\\\");
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            System.out.println("tok = " + tok);
            Note note = new Note().fromString(tok);
            this.notes.add(note);
        }
    }
}

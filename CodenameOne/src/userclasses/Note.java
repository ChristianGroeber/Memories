/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.ui.Dialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author chris
 */
public class Note extends Memories{

    private String title;
    private String text;
    private Date date;
    
    public Note(Date date, String title, String text){
        this.date = date;
        this.title = title;
        this.text = text;
    }
    
    public Note(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    SimpleDateFormat sd = new SimpleDateFormat("hh:mm");
    private final String spl = ";;";

    @Override
    public String toString() {
        String str = "";
        str += title + spl + text + spl;
        if (date == null) {
            date = new Date();
        }
        str += sd.format(date);

        return str;
    }

    public Note fromString(String strNote) throws ParseException {
        System.out.println("strNote = " + strNote);
        StringTokenizer tokenizer = new StringTokenizer(strNote, spl);
        try {
            title = tokenizer.nextToken();
            text = tokenizer.nextToken();
            date = sd.parse(tokenizer.nextToken());
        } catch (ParseException e) {
            if(super.isDev()){
                Dialog.show("Error loading strNote", e.toString(), "OK", null);
            }
            System.out.println("error loading strNote " + e);
        }catch(Exception e){
            if(super.isDev()){
                Dialog.show("Error with notes", e.toString(), "OK",null);
            }
        }

        return this;
    }

    public ArrayList<String> toArray() {
        ArrayList<String> arr = new ArrayList<>();
        if (date == null) {
            date = new Date();
        }
        arr.add(sd.format(date));
        arr.add(title);
        arr.add(text);
        return arr;
    }

}

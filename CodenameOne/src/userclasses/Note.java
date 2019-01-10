/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author chris
 */
public class Note {

    private String title;
    private String text;
    private Date date;

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

    @Override
    public String toString() {
        String str = "";
        str += title + "\\" + text + "\\";
        if (date == null) {
            date = new Date();
        }
        str += sd.format(date);

        return str;
    }

    public Note fromString(String strNote) throws ParseException {
        System.out.println("strNote = " + strNote);
        StringTokenizer tokenizer = new StringTokenizer(strNote, "\\");
        try {
            title = tokenizer.nextToken();
            text = tokenizer.nextToken();
            date = sd.parse(tokenizer.nextToken());
        } catch (Exception e) {
            System.out.println("error loading strNote " + e);
        }

        return this;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    @Override
    public String toString(){
        SimpleDateFormat sd = new SimpleDateFormat("hh:mm");
        String str = "";
        str += title + "\\" + text + "\\";
        str += sd.format(date);
        
        return str;
    }
    
}

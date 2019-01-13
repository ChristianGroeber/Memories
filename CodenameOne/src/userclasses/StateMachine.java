/**
 * Your application code goes here<br>
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
package userclasses;

import ch.bbbaden.m335.memories.MyApplication;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    private Memories memories;
    private Notes notes;
    private ArrayList<Memory> arrMemories;
    private ArrayList<Memory> memoriesOnForm = new ArrayList<>();
    private Boolean dev = false;

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
        notes = new Notes();
        System.out.println("\\\\");
    }

    @Override
    protected void onMain_BtnLaunchCameraAction(Component c, ActionEvent event) {
        String i = Capture.capturePhoto();
        if (i != null) {
            Image img = null;
            try {
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".jpg";
                System.out.println("pathToBeStored = " + pathToBeStored);
                img = Image.createImage(i);
                OutputStream out = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                ImageIO.getImageIO().save(img, out, ImageIO.FORMAT_JPEG, 0.9f);
                out.close();
                MyImage image = new MyImage();
                image.setImagePath(pathToBeStored);
                image.toString();
                memories.getTodaysMemory().addImage(image);
                putMemoryInForm(memories.getTodaysMemory());
            } catch (IOException ex) {
                if (dev) {
                    Dialog.show("Error", ex.toString(), "OK", null);
                }
            }
            findImageViewer().setImage(img);
        }

    }

    @Override
    protected void onMain_BtnChooseImageAction(Component c, ActionEvent event) {
        Form current = MyApplication.getCurrent();
        if (FileChooser.isAvailable()) {
            FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2 -> {
                if (e2 != null && e2.getSource() != null) {
                    String file = (String) e2.getSource();
                    try {
                        String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() + ".jpg";
                        Image img = Image.createImage(file);
                        OutputStream out = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                        ImageIO.getImageIO().save(img, out, ImageIO.FORMAT_JPEG, 0.9f);
                        out.close();
                        MyImage myImage = new MyImage();
                        myImage.setImagePath(pathToBeStored);
                        myImage.toString();
                        current.add(new Label(img));
                        memories.getTodaysMemory().addImage(myImage);
                        putMemoryInForm(memories.getTodaysMemory());
                        if (true) {
                            return;
                        }
                    } catch (Exception ex) {
                        if (dev) {
                            Dialog.show("Error", ex.toString(), "OK", null);
                        }
                        Log.e(ex);
                    }
                }
            });
        }
    }

    @Override
    protected void onMain_BtnRecordAction(Component c, ActionEvent event) {
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String recordingsDir = fs.getAppHomePath() + "recordings/";
        fs.mkdir(recordingsDir);
        try {
            for (String file : fs.listFiles(recordingsDir)) {
                MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
                mb.addActionListener((e) -> {
                    try {
                        Media m = MediaManager.createMedia(recordingsDir + file, false);
                        m.play();
                    } catch (IOException err) {
                        if (dev) {
                            Dialog.show("Error Recorder", err.toString(), "OK", null);
                        }
                        Log.e(err);
                    }
                });
                MyApplication.getCurrent().add(mb);
            }
            try {
                String file = Capture.captureAudio();
                if (file != null) {
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                    String fileName = sd.format(new Date());
                    String filePath = recordingsDir + fileName;
                    Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                    MultiButton mb = new MultiButton(fileName);
                    mb.addActionListener((e) -> {
                        try {
                            Media m = MediaManager.createMedia(filePath, false);
                            m.play();
                        } catch (IOException err) {
                            if (dev) {
                                Dialog.show("Error Recorder", err.toString(), "OK", null);
                            }
                            Log.e(err);
                        }
                    });
                    MyApplication.getCurrent().add(mb);
                    MyApplication.getCurrent().revalidate();
                }
            } catch (IOException err) {
                if (dev) {
                    Dialog.show("Error Recorder", err.toString(), "OK", null);
                }
                Log.e(err);
            }

        } catch (IOException err) {
            if (dev) {
                Dialog.show("Error Recorder", err.toString(), "OK", null);
            }
            Log.e(err);
        }

    }

    @Override
    protected void onMain_BtnNewNoteAction(Component c, ActionEvent event) {
        showForm("New Note", null);
    }

    @Override
    protected void onMain_BtnLoadMemoriesAction(Component c, ActionEvent event) {
        loadMemories();
        arrMemories = memories.getMemories();
        for (Memory i : arrMemories) {
            try {
                putMemoryInForm(i);
            } catch (NullPointerException e) {
                System.out.println("error error error");
                if (dev) {
                    Dialog.show("Error Load Memories", e.toString(), "OK", null);
                }
            } catch (IOException ex) {
                if (dev) {
                    Dialog.show("Error", ex.toString(), "OK", null);
                }
            }
        }
//        Container container = findConMemories();
//        for (Memory i : memories.getMemories()) {
//            for (Note x : i.getNotes()) {
//                Label title = new Label(x.getTitle());
//                Label text = new Label(x.getText());
//                container.add(title);
//                container.add(text);
//            }
//        }
    }

    @Override
    protected void onNewNote_BtnSaveNoteAction(Component c, ActionEvent event) {
        KeyValue value = new KeyValue();
        Note note = new Note();
        if (!findTxtTitle().getText().equals("")) {
            note.setTitle(findTxtTitle().getText());
        }
        if (!findTxtText().getText().equals("")) {
            note.setText(findTxtText().getText());
        }
        notes.addNote(note);
        value.save(notes);
        memories.getTodaysMemory().addNote(note);
        System.out.println(memories.getTodaysMemory());
        back();
    }

    private void save() throws IOException {
        Storage.getInstance().writeObject("Saved Data", memories.toHashSet());
        String home = FileSystemStorage.getInstance().getAppHomePath();
        for (Memory i : arrMemories) {
            OutputStream os = FileSystemStorage.getInstance().openOutputStream(home + i.getPath());
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeChars(i.toString());
            dos.flush();
            dos.close();
        }
    }

    private void putMemoryInForm(Memory mem) throws IOException {
        memoriesOnForm.add(mem);
        ArrayList<MyImage> img = mem.getImages();
        ArrayList<Note> notes = mem.getNotes();
        Container con = new Container(BoxLayout.y());
        con.setEnabled(false);
        con.setUIID(mem.getDate().toString());
        ArrayList<Component> containers = (ArrayList<Component>) findConMemories().getChildrenAsList(true);
        for (Component i : containers) {
            if (i.getUIID().equals(con.getUIID())) {
                System.out.println("found double container");
                findConMemories().removeComponent(i);
            }
        }
        TextField txtTitle = new TextField(mem.getTitle());
        con.add(txtTitle);
        if (!img.isEmpty()) {
            for (MyImage i : img) {
                addImageToForm(i, con);
            }
        }
        if (!notes.isEmpty()) {
            for (Note i : notes) {
                addNoteToForm(i, con);
            }
        }
        try {
            findConMemories().addComponent(con); //need to do this after loading
        } catch (Exception e) {
            if (dev) {
                Dialog.show("Error", "Error while putting Memory to Form\nCurrent working form: " + Display.getInstance().getCurrent().getTitle());
            }
        }
        save();
    }

    private void addImageToForm(MyImage i, Container con) {
        ImageViewer imgViewer = new ImageViewer();
        if (i.getImagePath() != null) {
            try {
                Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(i.getImagePath()));
                imgViewer.setImage(img);
            } catch (Exception e) {
                if (dev) {
                    Dialog.show("Error", "Error during image loading: " + e, "OK", null);
                }
            }
        }
        con.add(imgViewer);
    }

    private void addNoteToForm(Note i, Container con) {
        Label lblTitle = new Label();
        try {
            lblTitle.setText(i.getTitle());
        } catch (Exception e) {
            if (dev) {
                Dialog.show("Error Note", "error in title " + e.toString());
            }
        }
        Label lblText = new Label();
        try {
            lblText.setText(i.getText());
        } catch (Exception e) {
            if (dev) {
                Dialog.show("Error Note", "error in text " + e.toString());
            }
        }
        try {
            con.add(lblTitle);
            con.add(lblText);
        } catch (Exception e) {
            if (dev) {
                Dialog.show("Error Note", "error in note " + e.toString());
            }
        }
    }

    private void loadMemories() {
        if (memories == null) {
            memories = new Memories();
            try {
                memories.setMemories((ArrayList<String>) Storage.getInstance().readObject("Saved Data"));
            } catch (ParseException ex) {
                System.out.println("ex = " + ex);
            } catch (IOException ex) {
                System.out.println("ex = " + ex);
            } catch (java.text.ParseException ex) {
                System.out.println("ex = " + ex);
            } catch (NullPointerException e) {
                System.out.println("lulerror = " + e);
                try {
                    memories.setMemories(new ArrayList<String>());
                } catch (ParseException ex) {
                } catch (IOException ex) {
                } catch (java.text.ParseException ex) {
                }
            }
        }
    }

    @Override
    protected void postMain(Form f) {
        System.out.println("post show");
        loadMemories();
//        arrMemories = memories.getMemories();
//        for (Memory i : arrMemories) {
//            try {
//                putMemoryInForm(i);
//            } catch (NullPointerException e) {
//                System.out.println("error error error");
//            }
//        }
    }

    @Override
    protected void onMain_BoxDeveloperAction(Component c, ActionEvent event) {
        dev = findBoxDeveloper().isSelected();
        memories.setDev(dev);
        Button delete = new Button("delete");
        if (dev) {
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String[] files = null;
                    try {
                        String home = FileSystemStorage.getInstance().getAppHomePath();
                        System.out.println("home = " + home);
                        files = FileSystemStorage.getInstance().listFiles(home);
                        System.out.println("files = " + files.length);
                        for (String i : files) {
                            FileSystemStorage.getInstance().delete(home + i);
                        }
                        Dialog.show("Success", "Files successfully deleted", "OK", null);
                    } catch (IOException ex) {
                        Dialog.show("Error", "Error getting files", "OK", null);
                    }

                }
            });
            findConMemories().add(delete);
            Dialog.show("Info", "You are now a Developer", "OK", null);
        } else {
            try {
                findConMemories().removeComponent(delete);
            } catch (Exception e) {
            }
        }
    }
}

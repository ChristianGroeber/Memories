/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Tabs", com.codename1.ui.Tabs.class);
        UIBuilder.registerCustomComponent("ImageViewer", com.codename1.components.ImageViewer.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("TextArea", com.codename1.ui.TextArea.class);
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("CheckBox", com.codename1.ui.CheckBox.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Tabs", com.codename1.ui.Tabs.class);
        UIBuilder.registerCustomComponent("ImageViewer", com.codename1.components.ImageViewer.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Button findBtnSaveNote(Component root) {
        return (com.codename1.ui.Button)findByName("btnSaveNote", root);
    }

    public com.codename1.ui.Button findBtnSaveNote() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnSaveNote", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnSaveNote", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnLoadMemories(Component root) {
        return (com.codename1.ui.Button)findByName("btnLoadMemories", root);
    }

    public com.codename1.ui.Button findBtnLoadMemories() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnLoadMemories", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnLoadMemories", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnNewNote(Component root) {
        return (com.codename1.ui.Button)findByName("btnNewNote", root);
    }

    public com.codename1.ui.Button findBtnNewNote() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnNewNote", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnNewNote", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.CheckBox findBoxDeveloper(Component root) {
        return (com.codename1.ui.CheckBox)findByName("boxDeveloper", root);
    }

    public com.codename1.ui.CheckBox findBoxDeveloper() {
        com.codename1.ui.CheckBox cmp = (com.codename1.ui.CheckBox)findByName("boxDeveloper", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.CheckBox)findByName("boxDeveloper", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findNewMemory(Component root) {
        return (com.codename1.ui.Container)findByName("newMemory", root);
    }

    public com.codename1.ui.Container findNewMemory() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("newMemory", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("newMemory", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnChooseImage(Component root) {
        return (com.codename1.ui.Button)findByName("btnChooseImage", root);
    }

    public com.codename1.ui.Button findBtnChooseImage() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnChooseImage", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnChooseImage", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findConMemories(Component root) {
        return (com.codename1.ui.Container)findByName("conMemories", root);
    }

    public com.codename1.ui.Container findConMemories() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("conMemories", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("conMemories", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnLaunchCamera(Component root) {
        return (com.codename1.ui.Button)findByName("btnLaunchCamera", root);
    }

    public com.codename1.ui.Button findBtnLaunchCamera() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnLaunchCamera", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnLaunchCamera", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Tabs findTabs(Component root) {
        return (com.codename1.ui.Tabs)findByName("Tabs", root);
    }

    public com.codename1.ui.Tabs findTabs() {
        com.codename1.ui.Tabs cmp = (com.codename1.ui.Tabs)findByName("Tabs", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Tabs)findByName("Tabs", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.components.ImageViewer findImageViewer(Component root) {
        return (com.codename1.components.ImageViewer)findByName("ImageViewer", root);
    }

    public com.codename1.components.ImageViewer findImageViewer() {
        com.codename1.components.ImageViewer cmp = (com.codename1.components.ImageViewer)findByName("ImageViewer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.components.ImageViewer)findByName("ImageViewer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findBtnRecord(Component root) {
        return (com.codename1.ui.Button)findByName("btnRecord", root);
    }

    public com.codename1.ui.Button findBtnRecord() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("btnRecord", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("btnRecord", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findTxtTitle(Component root) {
        return (com.codename1.ui.TextField)findByName("txtTitle", root);
    }

    public com.codename1.ui.TextField findTxtTitle() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("txtTitle", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("txtTitle", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextArea findTxtText(Component root) {
        return (com.codename1.ui.TextArea)findByName("txtText", root);
    }

    public com.codename1.ui.TextArea findTxtText() {
        com.codename1.ui.TextArea cmp = (com.codename1.ui.TextArea)findByName("txtText", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextArea)findByName("txtText", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("New Note".equals(f.getName())) {
            exitNewNote(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitNewNote(Form f) {
    }


    protected void exitMain(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("New Note".equals(f.getName())) {
            beforeNewNote(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeNewNote(Form f) {
    }


    protected void beforeMain(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("New Note".equals(c.getName())) {
            beforeContainerNewNote(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerNewNote(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }

    protected void postShow(Form f) {
        if("New Note".equals(f.getName())) {
            postNewNote(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postNewNote(Form f) {
    }


    protected void postMain(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("New Note".equals(c.getName())) {
            postContainerNewNote(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerNewNote(Container c) {
    }


    protected void postContainerMain(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("New Note".equals(rootName)) {
            onCreateNewNote();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateNewNote() {
    }


    protected void onCreateMain() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("New Note".equals(f.getName())) {
            getStateNewNote(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateNewNote(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("New Note".equals(f.getName())) {
            setStateNewNote(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateNewNote(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("New Note")) {
            if("txtTitle".equals(c.getName())) {
                onNewNote_TxtTitleAction(c, event);
                return;
            }
            if("txtText".equals(c.getName())) {
                onNewNote_TxtTextAction(c, event);
                return;
            }
            if("btnSaveNote".equals(c.getName())) {
                onNewNote_BtnSaveNoteAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Main")) {
            if("boxDeveloper".equals(c.getName())) {
                onMain_BoxDeveloperAction(c, event);
                return;
            }
            if("btnLoadMemories".equals(c.getName())) {
                onMain_BtnLoadMemoriesAction(c, event);
                return;
            }
            if("btnLaunchCamera".equals(c.getName())) {
                onMain_BtnLaunchCameraAction(c, event);
                return;
            }
            if("btnChooseImage".equals(c.getName())) {
                onMain_BtnChooseImageAction(c, event);
                return;
            }
            if("btnRecord".equals(c.getName())) {
                onMain_BtnRecordAction(c, event);
                return;
            }
            if("btnNewNote".equals(c.getName())) {
                onMain_BtnNewNoteAction(c, event);
                return;
            }
        }
    }

      protected void onNewNote_TxtTitleAction(Component c, ActionEvent event) {
      }

      protected void onNewNote_TxtTextAction(Component c, ActionEvent event) {
      }

      protected void onNewNote_BtnSaveNoteAction(Component c, ActionEvent event) {
      }

      protected void onMain_BoxDeveloperAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnLoadMemoriesAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnLaunchCameraAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnChooseImageAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnRecordAction(Component c, ActionEvent event) {
      }

      protected void onMain_BtnNewNoteAction(Component c, ActionEvent event) {
      }

}

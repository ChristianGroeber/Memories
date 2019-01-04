/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m335.memories;

import com.codename1.ui.Display;

/**
 * GUI builder created Form
 *
 * @author chris
 */
public class GuiComponent extends com.codename1.ui.Form {

    public GuiComponent() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public GuiComponent(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Button gui_btnNewMemory = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_btnNewMemory.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_btnNewMemory) {
                onbtnNewMemoryActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Memories");
        setName("GuiComponent");
        addComponent(gui_btnNewMemory);
        gui_btnNewMemory.setText("New Memory");
        gui_btnNewMemory.setUIID("btnNewMemory");
                gui_btnNewMemory.setInlineStylesTheme(resourceObjectInstance);
        gui_btnNewMemory.setInlineAllStyles("bgImage:;");
        gui_btnNewMemory.setName("btnNewMemory");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btnNewMemory.getParent().getLayout()).setInsets(gui_btnNewMemory, "18.788626% auto auto 23.818897%").setReferenceComponents(gui_btnNewMemory, "-1 -1 -1 -1").setReferencePositions(gui_btnNewMemory, "0.0 0.0 0.0 0.0");
        com.codename1.ui.Command cmd_createNewMemory = new com.codename1.ui.Command("New Memory") {
                public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
                        oncreateNewMemoryCommand(ev, this);
                }
        };
        gui_btnNewMemory.setCommand(cmd_createNewMemory);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void oncreateNewMemoryCommand(com.codename1.ui.events.ActionEvent ev, com.codename1.ui.Command cmd) {
    }

    public void onbtnNewMemoryActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

}

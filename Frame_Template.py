from tkinter import *



class FrameTemplate(Tk):
    def __init__(self, *args, **kwargs):
        Tk.__init__(self, *args, **kwargs)
        #Base for all pages in the GUI
        #Canvas has the max resolution

        canvas = Canvas(self, height=1080, width=1920)
        canvas.pack(expand = YES, fill = BOTH)
    
        self.state = False
        self.bind("<F11>", self.toggleFullscreen)
        self.bind("<Escape>", self.killFullscreen)

        frame = Frame(self)
        frame.pack(side="top", fill="both", expand=True)
    
    def toggleFullscreen(self, event=None):
        #ToggleFullscreen can both toggle and kill fullscreen
        self.state = not self.state
        self.attributes("-fullscreen", self.state)
        return "break"

    def killFullscreen(self, event=None):
        #Escapekey has been bound to kill fullscreen
        self.state = False
        self.attributes("-fullscreen", self.state)
        return "break"

#class ButtonTemplate(Tk):

#class MainMenyButton(ButtonTemplate):

    


app = FrameTemplate()
app.mainloop()
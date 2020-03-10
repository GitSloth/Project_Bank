from tkinter import *
from PIL import ImageTk,Image



class CanvasInit(Tk):
    def __init__(self, *args, **kwargs):
        Tk.__init__(self, *args, **kwargs)
        #Base for all pages in the GUI
        #Canvas has the max resolution
        canvas = Canvas(self, height=1080, width=1920)
        canvas.pack(expand=True, fill="both")
        bgImage = ImageTk.PhotoImage(Image.open(r"C:\Users\lucva\Pictures/zelda.png"))
        canvas.create_image(0, 0, anchor=NW, image=bgImage)
        canvas.image = bgImage

        self.state = False
        self.bind("<F11>", self.toggleFullscreen)
        self.bind("<Escape>", self.killFullscreen)

        container = Frame(canvas)
        container.pack()
        container.rowconfigure(0, weight=1)
        container.columnconfigure(0, weight=1)

        self.frames={}
        for F in (TestPage1, TestPage2):
            page_name =F.__name__
            frame = F(parent=container, controller=self)
            self.frames[page_name] = frame
            frame.grid(row=0, column=0, sticky="nsew")
        
        self.showFrame("TestPage1")

    def showFrame(self, page_name):
        frame = self.frames[page_name]
        frame.tkraise()   

        
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

class TestPage1(Frame):

    def __init__(self, parent, controller):
        Frame.__init__(self, parent)
        self.controller = controller
        label = Label(self, text="Test1")
        label.pack(side="top", fill="x", pady=10)
        button = Button(self, text="Test2", command=lambda: controller.showFrame("TestPage2"))
        button.pack()

class TestPage2(Frame):

    def __init__(self, parent, controller):
        Frame.__init__(self, parent)
        self.controller = controller
        label = Label(self, text="Test2")
        label.pack(side="top", fill="x", pady=10)
        button = Button(self, text="Test1", command=lambda: controller.showFrame("TestPage1"))
        button.pack()

    


app = CanvasInit()
app.mainloop()
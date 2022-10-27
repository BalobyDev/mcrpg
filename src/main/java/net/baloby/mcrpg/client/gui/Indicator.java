package net.baloby.mcrpg.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Indicator {
    public ITextComponent text;
    public FontRenderer font;
    public int color = -1;
    public Indicator(String text){
        this.text = new StringTextComponent(text);
        this.font = Minecraft.getInstance().font;
    }
    public Indicator(String text,int color){
        this.text = new StringTextComponent(text);
        this.font = Minecraft.getInstance().font;
        this.color = color;
    }
    public static Indicator dmgIndicator(int dmg){return new Indicator(dmg+"",16733525);}
    public static Indicator missIndicator(){return new Indicator("MISS!");}
    public static Indicator blockIndicator(){return new Indicator("BLOCK!");}
    public static Indicator healIndicator(int hp){return new Indicator(hp+"",5635925	);}
    public static Indicator mpIndicator(int mp){return new Indicator(mp+"",5636095	);}


}

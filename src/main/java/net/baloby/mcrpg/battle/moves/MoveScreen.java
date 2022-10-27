package net.baloby.mcrpg.battle.moves;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

public class MoveScreen extends Screen {

    protected MoveScreen(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    @Override
    public boolean isPauseScreen(){return false;}

    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_){

    }
}

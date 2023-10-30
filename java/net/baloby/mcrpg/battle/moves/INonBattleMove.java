package net.baloby.mcrpg.battle.moves;

import net.baloby.mcrpg.battle.Unit.Unit;
import net.baloby.mcrpg.client.gui.profile.Profile;

public interface INonBattleMove {

     void nonBattleExecute(Profile user, Profile target);
}

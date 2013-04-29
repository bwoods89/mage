/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

package mage.sets.dragonsmaze;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Outcome;
import mage.Constants.Rarity;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CipherEffect;
import mage.cards.CardImpl;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPermanent;

/**
 *
 * @author LevelX2
 */


public class HiddenStrings extends CardImpl<HiddenStrings> {

    public HiddenStrings(UUID ownerId) {
        super(ownerId, 12, "Hidden Strings", Rarity.COMMON, new CardType[]{CardType.SORCERY}, "{1}{U}");
        this.expansionSetCode = "DGM";
        this.color.setBlue(true);

        // You may tap or untap target permanent, then you may tap or untap another target permanent
        this.getSpellAbility().addEffect(new HiddenStringsEffect());
        this.getSpellAbility().addTarget(new TargetPermanent());
        this.getSpellAbility().addTarget(new TargetPermanent());
        // Cipher
        this.getSpellAbility().addEffect(new CipherEffect());
    }

    public HiddenStrings(final HiddenStrings card) {
        super(card);
    }

    @Override
    public HiddenStrings copy() {
        return new HiddenStrings(this);
    }

}

class HiddenStringsEffect extends OneShotEffect<HiddenStringsEffect> {

    public HiddenStringsEffect() {
        super(Outcome.Tap);
        this.staticText = "You may tap or untap target permanent, then you may tap or untap another target permanent";
    }

    public HiddenStringsEffect(final HiddenStringsEffect effect) {
        super(effect);
    }

    @Override
    public HiddenStringsEffect copy() {
        return new HiddenStringsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        boolean result = false;

        Player player = game.getPlayer(source.getControllerId());
        if (player != null) {
            Permanent permanent = game.getPermanent(source.getFirstTarget());
            if (permanent != null) {
                if (permanent.isTapped()) {
                    if (player.chooseUse(Constants.Outcome.Untap, "Untap that permanent?", game)) {
                        result |= permanent.untap(game);
                    }
                } else {
                    if (player.chooseUse(Constants.Outcome.Tap, "Tap that permanent?", game)) {
                        result |= permanent.tap(game);
                    }
                }
            }
            permanent = game.getPermanent(source.getTargets().get(1).getFirstTarget());
            if (permanent != null) {
                if (permanent.isTapped()) {
                    if (player.chooseUse(Constants.Outcome.Untap, "Untap that permanent?", game)) {
                        result |= permanent.untap(game);
                    }
                } else {
                    if (player.chooseUse(Constants.Outcome.Tap, "Tap that permanent?", game)) {
                        result |= permanent.tap(game);
                    }
                }
            }
        }
        return result;
    }
}
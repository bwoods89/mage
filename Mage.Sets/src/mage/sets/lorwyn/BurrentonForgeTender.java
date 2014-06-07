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
package mage.sets.lorwyn;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.PreventDamageByTargetEffect;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.FilterObject;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.TargetSource;

/**
 *
 * @author LevelX2
 */
public class BurrentonForgeTender extends CardImpl {

    private static final FilterCard filter = new FilterCard("Red");
    private static final FilterObject filterObject = new FilterObject("red source of your choice");

    static {
        filter.add(new ColorPredicate(ObjectColor.RED));
        filterObject.add(new ColorPredicate(ObjectColor.RED));
    }

    public BurrentonForgeTender(UUID ownerId) {
        super(ownerId, 7, "Burrenton Forge-Tender", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{W}");
        this.expansionSetCode = "LRW";
        this.subtype.add("Kithkin");
        this.subtype.add("Wizard");

        this.color.setWhite(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Protection from red
        this.addAbility(new ProtectionAbility(filter));
        
        // Sacrifice Burrenton Forge-Tender: Prevent all damage a red source of your choice would deal this turn.
        Effect effect = new PreventDamageByTargetEffect(Duration.EndOfTurn, Integer.MAX_VALUE, false);
        effect.setText("Prevent all damage a red source of your choice would deal this turn");
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD,  effect, new SacrificeSourceCost());
        TargetSource targetSource = new TargetSource(filterObject);
        targetSource.setRequired(true);
        ability.addTarget(targetSource);
        this.addAbility(ability);

    }

    public BurrentonForgeTender(final BurrentonForgeTender card) {
        super(card);
    }

    @Override
    public BurrentonForgeTender copy() {
        return new BurrentonForgeTender(this);
    }
}
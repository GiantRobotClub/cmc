Mechanical Chaos NEW MECHANICS <br>

<% webrunner.cardmaster.CardmasterServerCard[] cards =	 webrunner.cardmaster.CardmasterData.loadCardData(); %>

<table border=1>

<Tr>
<td width = 500 valign= top>
Modifier cards : An entirely new card type to fill your decks with, Modifiers cards attach themselves to entities perminently giving them new abilities, stat bonuses, and can even be used against your opponent!   Any ability on a Modifier card is used normally except it treats the card it's attached to as it's 'self' - so a dizzying modifier ability would dizzy the card it's attached to, basically giving the card TWO abilities!  Moving a card causes the modifier to move with it, and returning a card to hand or destroying it otherwise will cause the modifier to be destroyed.  Simply click the "Mods" button to see the modifier layer.  Please note Modifiers do not count as entities - they cannot be targeted or seen by other cards except in cases of Allying and Power.  They do count as cards, however- an "Attach generator" counts as a Generator for cards that look for generators in your deck, for instance.

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[799]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>


<Tr>
<td width = 500 valign= top>
Face Down: Many cards in Mechanical Chaos play face down- and some even allow OTHER cards to be played face down!  Perfect for ambushes and traps!  A face down monster can be anything, and your mana totals become estimated on your opponent's side, making them unsure of which monster you played.  If your opponent plays a face down monster, do you attack it, hoping it has low defence, or could it be something huge just waiting for you to give up a card?

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[819]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>

<Tr>
<td width = 500 valign= top>
Allying : Cards band together with this ability.  "Allying X:" essentially means two things: <br />
1) "If there are X cards in play under your control (including modifiers and the location) with Allying:" <br />
2) "This Card Has Allying" - not applicable for spells.  <br />
FOR EXAMPLE: IF you have two cards out with Allying 3 and then play a card with Allying 4, then the two Allying 3 cards will have their allying abilties.  If you now play a spell with Allying 3, then that card's additional Allying aiblity will also trigger. <br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[785]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Power : Some devices just don't run on magic, and that's where Power comes in.  You must control enough cards that provide power to add up to the amount needed on the card.  First, play 1 or more cards that "provides X power".  Then play a card that uses it!  One power providing card can produce the power required for any number of "Requires 1 power" cards.<br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[914]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>





<Tr>
<td width = 500 valign= top>
Monster counters: Counters used to just be for Effects, but now there's a method to make them work with Monsters as well!  THey work like status effects - you'll see a little grey dot on the monster, one for each counter you add.  They appear as blueish beads in the status effect slot.<br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[866]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Nothing even close to a standard generator : No one color generators, and lots of duals!  In fact, there's several cycles of multi-color generators, all of them with weird effects! <br />
Allying Generators - Each 2-color combo has two Allying generators.  They cost 1 of each color of mana, and generate one and allying 4: generate the other.<br />
Power Gens - Dual gens cheaper than Principalities crystals, that also generate 1 power, but also have drawbacks.  One for each 2 color combo.<br />
"Switch" generators - Each 2-color combo also has two switch generators.  They cost one of the colors, have an into-play drawback, generate the other color, and give you the ability to trade that for another color.<br />


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[891]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>







<Tr>
<td width = 500 valign= top>
Cloning: Creating duplicates of existing cards... even uniques! <br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[825]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>







<Tr>
<td width = 500 valign= top>
Deck Searching by Type: There are cards in Mechanical Chaos that let you find specific kinds of cards - for example, generators!


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[796]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>




<Tr>
<td width = 500 valign= top>
Half-Cantrips: Spells that cantrip sometimes, but not others.


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[797]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>





<Tr>
<td width = 500 valign= top>
More!: There's plenty of completley unique cards in Mechanical Chaos, like the one to the right!


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[901]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



</table>
War of the Webcomics NEW MECHANICS <br>
<table border=1>
<Tr>
<td width = 500 valign= top>
Decoying : whenever there is a monster with Decoying in play, any ability or spell that targets monsters can only target monsters with (Decoying).  Please note that this includes your own abilities, and abilities that cannot effect the (Decoying) card for whatever reason (say, monster type).  What a Decoying card does is act as a lightning rod, requiring your opponent to destroy it instead of one of your more important monsters.  If you have a (Decoying) out your Dark Lord Khrima is suddenly lawyer-proof!<br><br>

</td>
<td width=125 valign=top>
<% webrunner.cardmaster.CardmasterServerCard[] cards =	 webrunner.cardmaster.CardmasterData.loadCardData(); %>

<%request.setAttribute("theCard",cards[659]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Moving Cards : There are cards that can move around in War of the Webcomics.  Either into your opponent's side of the field, from your opponent's side to yours, or in Gatemaster's case, across your own field.  Gatemaster can use his ability to avoid being attacked, avoid spells being cast on him, and more!<br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[576]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>


<Tr>
<td width = 500 valign= top>
Quick Hit: A new ability only given to one card in War of the Webcomics, Quick Hit allows a monster when on the offensive to avoid being damaged by defending monsters.  Basically, if a monster with Quick Hit attacks a monster without Quick Hit, and kills that monster, the attacking monster takes no damage!  However, damage taken is normal in all other eventualities.

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[615]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Drawing from your opponent's deck : War of the Webcomics includes a card that draws cards from your opponent's deck.  All normal drawing rules apply: you have to discard on a full hand, your opponent's deck loses the cards, and if you play and then get it destroyed it goes to your graveyard.  However, please note that 'decking' works on who draws the cards, not who's deck it is.  If you use Cameo and your opponent doesnt have 2 cards in his or her deck, you lose.<br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[671]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Speed Modifiers : There are spells in War of the Webcomics that modify entity speed.  They work like this:<br>
Any entity with S speed, when slowed, becomes A.  When sped up, remains S<br>
Any entity with A speed, when slowed, becomes B.  When sped up, remains A<br>
Any entity with B speed, when slowed, becomes C.  When sped up, becomes A<br>
Any entity with C speed, when slowed, becomes D.  When sped up, becomes B<br>
Any entity with D speed, when slowed, becomes E.  When sped up, becomes C<br>
Any entity with E speed, when slowed, remains E.  When sped up, becomes D<br>


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[638]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>






<Tr>
<td width = 500 valign= top>
Counterspells : War of the Webcomics introduces new spell and ability counters.  Basically, any spell or ability during the same set of spells or abilities resolving fails if they resolve after the counter.  So, Arm Breaker can counter spells and abilities of speed B and slower on your turn, but only C and slower on your opponent's, due to the nature of the speed system.

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[583]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
Ability blocking : There are some effects in War of the Webcomics which disable certian entire classes of abilities.  What they basically do is that if a spell with that kind of ability does tries to be used it fails.  The example card disables ALL abilities that can undizzy a monster or effect.<br><br>

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[661]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>



<Tr>
<td width = 500 valign= top>
No Standard Generators : The three 'basic' generators in War of the Webcomics have different sac values then the ones in other sets, but have special on-destroy effects that differ between the three.

</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[629]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>





<Tr>
<td width = 500 valign= top>
Turn Skipping : War of the Webcomics introduces time cards, which can cause players to skip their turn.  However, any automatic effects such as generators or Kid Radd pumping up, still run as if they didn't skip the turn.


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[596]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>





<Tr>
<td width = 500 valign= top>
The "Radd Effect" : Some cards in War of the Webcomics have a damage effect which reduces the amount of damage or lifegain they get to 1.  This means that Kid Radd takes four hits to kill, regardless of how much damage each hit has.  They also make great walls since they don't have overflow.   Some "set" effects are effected by that, and some aren't.


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[618]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>




<Tr>
<td width = 500 valign= top>
Flags and Variables : One last thing that's new is that there are cards that can affect themselves or others with special, arbitrary variables which have various effects.  They can be used to approximate status effects (such as Spray Glue, shown here) or to be used to 'save' values through transformations (like Sheena) and is also used by one of the new Khrima cards for his "talking to his minions" effect.


</td>
<td width=125 valign=top>

<%request.setAttribute("theCard",cards[664]);%>
<jsp:include page="singlecard.jsp"/>
</td>
</tr>


</table>
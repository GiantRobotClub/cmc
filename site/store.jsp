
<jsp:useBean id="decklist" class="webrunner.cardmaster.CardmasterDeckList" scope="page"/>
<%String name = session.getAttribute("loginname").toString();%>
<% 
decklist.loadUser(name);%>
<jsp:useBean id="deck" class="webrunner.cardmaster.CardmasterNewDeck" scope="page"/>

Buy new cards:<br>

<img src="images/xp_0_title.gif"><br>
The Original!  113 cards.  39 grey, 31 dark, and 43 light.<br>  
<%int numcards; int color;%>
15 Card Booster: Contains 9 cards from rarity 1-60, 5 from rarity 61-85, and one from rarity 86-100.  Cost: <%=webrunner.cardmaster.CardmasterData.BOOSTCOST%> points. <a href="page.jsp?pageurl=buybooster.jsp&x=0">BUY!</a> <a href="page.jsp?pageurl=buyboosterbox.jsp&x=0">BUYx10!</a><br>
<br>
50 CARD STARTER DECKS: (3000 points each)<br>
"Forces of Light" : A light colored deck.  10 Messengite Shrines and 5 Magic Orbs give you a good mana foundation, and your clerics will have a field day while a nice complement of game-controlling cards make up the backbone of the deck. <a href="page.jsp?pageurl=buystarter.jsp&n=0">BUY!</a><br>
"Heralds of Melrac" : A dark colored deck.  10 Dark Obelisks and 5 Magic Skills provide the mana, while the Followers of Melrac use their special abilities.  Cannabalize and Skeletal Abomination are your aces in the hole.<a href="page.jsp?pageurl=buystarter.jsp&n=1">BUY!</a><br>
"Slime Invader" : Grey deck.  A full complement of slimes, running on 10 Neutral Charms and 5 Mana Buckets give you a good start on the board, while a set of magic cards allow you to keep your opponent's field clear.<a href="page.jsp?pageurl=buystarter.jsp&n=2">BUY!</a><br>

<br><br>
<img src="images/xp_1_title.gif"><br>
Clash of the Webcomics!  RPG World, ADVENTURERS!, Antihero for Hire, and Starsomething based cards.  149 cards.  45 grey (plus three variations on the same card), 45 dark, 45 light and 14 multi-colored cards. <br>

15 Card Booster: Contains 9 cards from rarity 1-60, 5 from rarity 61-85, and one from rarity 86-100.  Cost: <%=webrunner.cardmaster.CardmasterData.BOOSTCOST%> points.  <a href="page.jsp?pageurl=buybooster.jsp&x=1">BUY!</a><a href="page.jsp?pageurl=buyboosterbox.jsp&x=1">BUYx10!</a><br>
<br>
50 CARD STARTER DECKS: (3000 points each)<br>
"It's a Party!" : A light colored deck.  16 Spirit Beads for mana along with all your favorite RPG World and ADVENTURERS! heroes.  Also contains many spells and effects! <a href="page.jsp?pageurl=buystarter.jsp&n=10">BUY!</a><br>
"Villainy Rules!" : A dark colored deck.  16 Power Crystals for mana, as well as a large collection of monsters and unique villians give you the evil edge!<a href="page.jsp?pageurl=buystarter.jsp&n=11">BUY!</a><br>
"Walking the Line!" : Grey deck.  A little of everything in this deck!  Some monsters, some uniques from all four comics, and some great spells effects.  Mana provided by 14 Starship Engines and 2 GameWhales.<a href="page.jsp?pageurl=buystarter.jsp&n=12">BUY!</a><br>
<br><br>
<img src="images/xp_2_title.gif"><br>
Visit the scenic lands of Iridith, the Historylands, and more with CMC's first mini-expansion!  "Unknown Expedition" consists of 20 "Location" cards available in 3-card Expedition Packs for 250 points each.  Location cards are a new addition to CMC- along with most of their abilities.  Only one location can be in play at one point - shared by both players.  Playing a location is the only way to change the location in play- so be sure to stock up on Unknown Expeditions cards.  You never know where you might end up... <br>
Expedition Pack: 3 randomly chosen Unknown Expedition cards.  250 Points. <a href="page.jsp?pageurl=buyloc.jsp">BUY!</a><br>


<br><br>
<img src="images/xp_3_title.gif"><br>
The Heralds of Melrak are rising.. beyond simply subversion and trickery, there is evidence that they are building an army!  And that army can only be to crush the world as it is currently known....  But all is not in shadow...  Prince Valian of Iridith has returned with the Order of Paladins, and they do not intend to let Melrak be reborn into this world.   Not to be outdone, the Slime kingdom has agreed to help.  Strangely, the Regent of the Dark Elves has yet to pledge his allegance to the cause- at the advice of his right-hand-man Meilar.  Could Meilar have been working for the Heralds all this time?   Even if the Dark Elves agree to rise against the Heralds, it will take more than a grand alliance to stop such a malevolant force.  Three princes: Valian, the Reborn, the leader of the Paladins, Glomp, the prince of the Slime Kingdom, and even Far'thin, prince of Dark Elves, acting against his father's wishes, set out to find the Tarafang, the first of the weapons that could be used to stop Melrak if the Heralds were to succeed.<br>Principalities continues the CMC Prime Storyline with 134 new cards.  40 each for the three main alignments, along with 14 new multi-alingment cards.<br><br>

15 Card Booster: Contains 9 cards from rarity 1-60, 5 from rarity 61-85, and one from rarity 86-100 from the "Principalities" set.  Cost: <%=webrunner.cardmaster.CardmasterData.BOOSTCOST%> points.  <a href="page.jsp?pageurl=buybooster.jsp&x=3">BUY!</a> <a href="page.jsp?pageurl=buyboosterbox.jsp&x=3">BUYx10!</a><br>
<br>
50 CARD STARTER DECKS: (3000 points each)<br>
"Prince Valian" : Light Deck.  The Paladins arrive, lead by King Valiron and his son the Prince Valian. <a href="page.jsp?pageurl=buystarter.jsp&n=20">BUY!</a><br>
"Norlaan and the Traitor" : Dark Deck.  Norlaan returns to lead his Followers to victory against the world of good, and he's bringing someone.. unexpected <a href="page.jsp?pageurl=buystarter.jsp&n=21">BUY!</a><br>
"Prince Far'thin and Prince Glomp" : Grey deck.  The Dark Elves bolster their ranks under the capable leadership of Prince Far'thin, first class Fencer.  The Slimes strengthen their defences as well, under the help of young prince Glomp. <a href="page.jsp?pageurl=buystarter.jsp&n=22">BUY!</a><br>
<br><br>





<img src="images/xp_4_title.gif"><br>
<a href="page.jsp?pageurl=exp_4_mechanics.jsp">View New War of the Webcomics Mechanics</a><br>
Before it was simply a border skirmish.. now it's an all out WAR!  War of the Webcomics!  RPG World, ADVENTURERS!, Antihero for Hire, and Starsomething are back with all-new cards - but they have to contend with cards from <a href="http://www.basilflint.com">Basil Flint</a>, <a href="http://www.sporkman.com">Sporkman</a>, <a href="http://www.kidradd.com">Kid Radd</a>, <a href="http://www.machall.com">Mac Hall</a>, and more!  New mechanics and abilities such as ability counters and Decoying crack the existing game dynamic in ways previously unheard of.  125 cards.  40 dark, 40 light, 39 grey plus 6 multi-colored cards.  <br>

15 Card Booster: Contains 9 cards from rarity 1-60, 5 from rarity 61-85, and one from rarity 86-100.  Cost: <%=webrunner.cardmaster.CardmasterData.BOOSTCOST%> points.  <a href="page.jsp?pageurl=buybooster.jsp&x=4">BUY!</a> <a href="page.jsp?pageurl=buyboosterbox.jsp&x=4">BUYx10!</a><br>
<br>
40 CARD STARTER DECKS: (3000 points each)<br>
"Tales of Heroism" : Light colored deck.  Defend the universe with heroes of all shapes and sizes.  Includes 12 Elemental Artifacts for mana generation and a wide selection of RPG World and ADVENTURERS! characters, along with a sampling of Kid Radd cards. <a href="page.jsp?pageurl=buystarter.jsp&n=30">BUY!</a><br>


"Random Monsters" : A dark colored deck.  AFH, AFH, and RPGW villians are backed up by a cavalcade of monsters and thugs.  12 Battery Relics for mana <a href="page.jsp?pageurl=buystarter.jsp&n=31">BUY!</a><br>


"Gotta Do What you Gotta Do" : Grey deck.  With the mana provided by 12 Starship Drives, the AFH heroes and miscellanious passersby can really hold their own<a href="page.jsp?pageurl=buystarter.jsp&n=32">BUY!</a><br>
<br><br>


<br><br>

<img src="images/xp_5_title.gif"><br>

<a href="page.jsp?pageurl=exp_5_mechanics.jsp">View New Mechanical Chaos Mechanics</a><br>
It's a blast from the future, as legions of robots, starships, and strange technological devices invade the world of Cardmaster Conflict!  Mechanical Chaos blasts onto the scene, introducing a new card type as well as new never-before seen ability types, and contains no single-coloured cards.  169 cards.  54 Light-Grey, 54 Dark-Grey, 54 Dark-Light, 7 tricolor.<br>
15 Card Booster: Contains 9 cards from rarity 1-60, 5 from rarity 61-85, and one from rarity 86-100.  Cost: <%=webrunner.cardmaster.CardmasterData.BOOSTCOST%> points.  <a href="page.jsp?pageurl=buybooster.jsp&x=5">BUY!</a> <a href="page.jsp?pageurl=buyboosterbox.jsp&x=5">BUYx10!</a><br>

40 CARD STARTER DECKS: (3000 points each)<br>
WARNING: Mechanical Chaos is a little more complex than the other expansions, you may not want to start with it.<br />
"Metallic March" : Grey-Light deck.  Clank, whirr.  Robots of all kinds march to the beat of valor.  Contains mostly robot monsters and related cards. - Created by Hello <a href="page.jsp?pageurl=buystarter.jsp&n=40">BUY!</a><br>
"Robot Invasion" : Grey-Dark deck.  Robots, aliens, and pirates yearn to pillage and destroy, and you're at the helm!  Contains a few pirate vessels, a few robotic horrors and aliens, as well as many other interesting cards - Created by masamunemaniac <a href="page.jsp?pageurl=buystarter.jsp&n=41">BUY!</a><br>
"Cosmic Energies" : Dark-Light deck.  Space is the theme in this deck - contains several starships and intrepid members of a ZFF starship crew. - Created by Hello <a href="page.jsp?pageurl=buystarter.jsp&n=42">BUY!</a><br>
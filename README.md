
<br>

# Minecraft Mod Collection
Inhalte: 
- Installationsanleitung
- Journey
- Mod Dokumentation
- Kommentare des Autors

---
### Installationsanleitung
Im root-Verzeichnis befindet sich ein Ordner namens "JARs (simply put them in the mods folder)".
Wie der Name vermuten lässt, beinhaltet dieser Ordner die fertigen compilierten JARs, der einzelnen Mods.
Diese gehören dann in den 'mods'-Ordner im Minecraft-Verzeichnis kopiert oder verschoben.
Dieser befindet sich meist unter "C:\Users\YOURUSERNAME\AppData\Roaming\\.minecraft\mods".
Sofern die Forge-Versionen zusammenpassen, steht einem nichts mehr im Weg und man kann genüsslich Minecraft spielen.

---
### Journey
Mein Hauptziel war es, durch das Einfügen meiner neuen Programmideen, möglichst wenig von Vanilla-Minecraft zu verändern.
Dieser Gedanke stammt auch aus der Zeit, als ich das "perfekte" Minecraft-Modpack zusammenstellen wollte.
Bereits damals war es mich wichtig, dass zum Beispiel keine neuen Blöcke hinzugefügt werden und alles noch ins Vanilla-Feeling passt.
Per Zufall habe ich auf CyberSecurity-YouTube-Channel Videos gesehen, in denen er seine Minecraft-Hacking-Journey, mit den Zuschauern teilte.
Minecraft-Hacks sind ja eigentlich auch bloß Mods, oft hald mit einem "malicious-intent".
Dort erfuhr ich das erste Mal von 'Mixin'. Durch Mixins ist es möglich, Java-Bytecode in den "normalen" Minecraft-Code zu injizieren.
Dies erschien als die perfekte Möglichkeit, neue Funktionen zu implementieren, ohne viel Neues erstellen zu müssen.
Durch einen kurzer Search nach "Minecraft Mixin" liefert Doktor Google direkt zwei Ergebnisse: einmal die SpongePowered-Mixin-GitHub-Seite und einmal die (stark unvollständige) Dokumentation dazu.
Nach etwa dreieinhalb Stunden brach ich diesen Versuch ab, da meine Mod nicht einmal einen simplen Text printen konnte.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- __Gesamt: 3,5h__

Mein nächster Gedanke war: *"Lieber doch "normales" Forge, damit habe ich ja bereits etwas Erfahrung."*
Also stürzte ich mich ins vertraute Land und begann wieder ein bisschen mit Forge herumzuspielen und alles wieder ein bisschen zu testen.
Nach etwa vier Stunden, hatte ich wieder einen super Überblick über die (doch komplexe) notwendige Ordnerstruktur.
Dazu gehören die diversen Packages für den eigenen Java-Code, die Ordner für resources und all die vielen JSON-Files, um die graphics und models richtig darstellen zu können.
Besonders zeitaufwändig war die Suche nach den richtigen events, durch die ich auf die Interaktion zwischen Hoppers und Truhen manipulieren kann.
Diese Suche war nicht nur zeitaufwändig, sondern schlussendlich auf vergebens.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- __Gesamt: 7,5h__

Als ich die ganzen vielen importierten Minecraft-Libraries betrachtete, viel mir auf, dass sowohl "Sponge" als auch "Mixin" vorkam.
Hier realisierte ich, dass seit kurzem, die Sponge-Mixins standardmäßig in Forge enthalten sind.
Mag zwar cool klingen, ist es auch. Naja, bis auf das, dass ich weitere drei Stunden probiert habe, mit Mixins zu arbeiten.
Mein Code per se war zwar richtig, doch Mixins brauchen eine ganz spezielle Einbindung und Konfiguration.
Da dies eben ein ganz neues Feature war, gab es kaum Dokumentationen, zumindest nicht für die aktuelle Version.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- Forge Mixins:  3,0h
- __Gesamt: 10,5h__

Ich sollte in etwa 25 Stunden für dieses Projekt aufwänden.
Da ich nun fast bei Halbzeit war, musste ich endlich Mal so wirklich mit dem Coden anfangen.
Also musste ich nun tatsächlich neue Blöcke erstellen, die die eigentlichen Blöcke extenden und dann die Veränderungen einprogrammieren.
Die Hauptidee widmete sich dem Hopper, der auch zum Filter werden sollte. Die Idee reichte so weit, dass der Hopper im Spiel, in eine "reale" Datei pipen kann, womit quasi ein serverübergreifender Itemtransport möglich wäre.
Da ein Hopper sowohl ein Block als auch ein Entity ist, entschied ich mich, mit etwas Leichterem zu beginnen. Mit einem Block. Beziehungsweise mit etwas noch Einfacherem, einem Item.
Es war Zeit den 'Lure Stick' zu erstellen. Dieser lockt bei Aktivierung (Rechtsclick) jene Tiere an, die das Filter-Predicate erfüllen.
Man glaubt es nicht, aber es ist verdammt schwer einem sturen Esel zu sagen, wohin es gehen soll.
Nein Spaß beiseite, Tiere haben in Minecraft tatsächlich eine komplexe Verhaltensweise, der Mob-Typ 'Dorfbewohner' sogar ein "AI-Brain".
Nach 23,5 Stunden (ich arbeite meist in halben-Stunden-Blöcken), war der Lure Stick fertig. Nein, er war sogar perfektioniert.
Von eigenen Textures, fantastischen Partikeleffekten, bis hin zur Schnittstelle für mögliche Enchantments ist alles dabei.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- Forge Mixins:  3,0h
- Lure Stick:   23,5h
- __Gesamt: 34h__

Nach einem Invest von 34 Stunden während meiner Weihnachtsferien, welcher nur deswegen so hoch sein konnte, weil ich wundersamerweise bereits vor den Ferien alle anstehenden Hausübungen bereits fertig hatte, musste dieses wunderbare Projekt vorerst gestoppt werden.

Dachste dir!
<br>
Obwohl viel in den Semesterferien zu erledigen war/ist, schaffte ich es innerhalb 3 Stunden, die 4 (+1) Enchantments zu erstellen.
- Duration: die Mobs folgen einem länger
- Amount: mehr Mobs können einem folgen
- Range: der Umkreis indem Mobs angelockt werden können, wird vergößert
- Speed: die Mobs bewegen sich schneller, während sie einem folgen
- Unbreaking: das bereits exisitierende Enchantment hat eine Wirkung auf den Lure Stick
<br>

__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- Forge Mixins:  3,0h
- Lure Stick:   23,5h
- Enchantments:  3,0h
- __Gesamt: 37__

Im Vergleich zu den 23,5 Stunden war der Lure Block mit seinen 6,5 Stunden ja quasi ein Scherz.
Dachte mir aber ehrlich gesagt, dass er viel schneller zu programmieren ist, da die Funktionalität ja einfach übernommen werden kann.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- Forge Mixins:  3,0h
- Lure Stick:   23,5h
- Enchantments:  3,0h
- Lure Block:    6,5h
- __Gesamt: 43,5h__

Um das ganze auch im Survival Modus spielbar zu machen, habe ich abschließend noch die nötigen Extras eingefügt:
Sowohl für den Lure Stick als auch den Lure Block gibts es ein Craftin-Rezept.
Der Lure Block kann am schnellsten mit Spitzhacken abgebaut werden.
Der Lure Stick kann mit Gold Nuggets repariert werden.
<br>
__Timeline:__
- Sponge Mixins: 3,5h
- Forge Testen:  4,0h
- Forge Mixins:  3,0h
- Lure Stick:   23,5h
- Enchantments:  3,0h
- Lure Block:    6,5h
- Recipes usw:   1,5h
- __Gesamt: 45,0h__

#### Future
Zurzeit entscheidet der Name des Lure Stick darüber, welche Mobs angelockt werden und welche nicht. Genaueres dazu in der Dokumentation.
Es besteht die Idee, das Selectionsverfahren intuitiver und mehr 'Vanilla-like' zu machen, indem zum Beispiel per (Shift-)Linksclick gewisse Tierarten zum Filter hinzugefügt oder entfernt werden können.

Natürlich ist auch der Hopper/Sorter geplant, doch der Aufwand ist zurzeit noch nicht absehbar. Vielleicht müssen die nächsten Ferien herhalten.

---
### Mod Dokumentation
Inhalt:
- Better Luring
  - Lure Stick
  - Lure Block
- (Better Hoppers)
  - (Filtering)
  - (Piping)

#### Better Luring
##### Lure Stick
Durch das Umbenennen des Lure Stick am Amboss kann ausgewählt, werden welche Mobs angelockt werden und welche nicht. Standardmäßig werden alle Mobs angelockt. Es gibt das Prinzip der White- oder Blacklist.
Die Grammatik ist wie folgt beschrieben: ['+' | '-'] Mobname {';' Mobname}
Ein paar leichte Beispiele sind:
- nur Kühe:						"+ Cow"
- nur Kühe und Hühner:			"+ Cow; Chicken"
- alles außer Kühe:				"- Cow"
- alles außer Kühe und Hühner:	"- Cow; Chicken"

Da standardmäßig von einer Whitelist ausgegangen wird, könnte bei den ersten zwei Beispielen das '+' weggelassen werden:
- nur Kühe:				"Cow"
- nur Kühe und Hühner:	"Cow; Chicken"

Es wurde versucht, möglichst viele Eingabe bearbeiten zu können, deshalb sind Folgende auch möglich:
- ohne Leerzeichen:					"Cow;Chicken"
- Semikolon am Anfang:				";Cow;Chicken"
- Semikolon am Ende:				"Cow;Chicken;"
- Semikolon am Anfang und Ende:		";Cow;Chicken;"
- Semikolon hintereinander:			"Cow;;;Chicken"
- Semikolon vor und nach '-':		";-;Cow;Chicken"

Da der 'diplay name' des Mobs für den Vergleich verwendet wird, muss man kein Englisch können.
Habe ich zum Beispiel Minecraft auf Deutsch eingestellt, müsste ich "Kuh" und "Huhn" im Filter verwenden.

Durch die Verwendung des 'display name', kann auch Filter auf einzelne Mobs gelegen werden.
Indem man ein Nametag in einem Ambos benennt und dann ein Mob mit dem Nametag umbenennt, ändert sich dessen 'display name'.
Somit kann man zum Beispiel ein Mob 'Max's grüner Weihnachtsfrosch nennen' und da dies ein einzigartiger 'display name' ist, folgt einem nur dieses Mob.

##### Lure Block
Der Lure Block funktioniert im Prinzip wie der Lure Stick.
Aber anstatt dynamisch die Zielposition bestimmen zu können, werden die Tiere fortlaufend zu dem Block gelockt.
Da der Block im Vergleich zum Stick keinen Cooldown hat, dient er einfach einer anderen Funktion.
Mit dem Stick kann man Mobs an eine gewünschte Stelle locken, zum Beispiel in einen Stall.
Der Block ist vor Allem in Mobfarms sehr sinnvoll, um alle Tiere zu sortieren. Welche grausamen Taten danach begangen werden, ist jedem selbst überlassen.

Der Lure Block benötigt ein Redstone-Signal, um Mobs anzulocken.
Weder Enchantments, noch das Erstellen von Filtern ist möglich.
Wird eventuell in Zukunft hinzugefügt.

---
### Kommentar des Autors
*"Im Großen und Ganzen würde ich auch dieses Projekt als einen riesen Erfolg betrachten.
Einerseits habe ich mir den Traum erfüllt, endlich meine eigene Minecraft-Mod zu programmieren.
Noch dazu eine sinnvolle, die mit großen Informatik-Themen zu tun hat. Sortieren, Gruppieren, Aussortieren, Ordnen, ...
Andererseits habe ich auch gelernt, dass programmieren in Wahrheit 80% lesen und verstehen oder planen ist, und eben nicht nur Code schreiben.
Bei eigenen Projekten geht viel Zeit für's Planend drauf, denn es soll ja auch logisch und gut aufgebaut sein.
Bei solch einem Projekt wie hier, wurde schon "alles" geplant, jetzt heißt es eben lesen, verstehen und lesen und verstehen."*

Um aus meinem ersten Projekt zu zitieren:
*"Will I finish this project in my free time? Yes! Maybe! Okay to be honest, I don't think I will have the time."*
*"__BUT I WOULD LOVE TO!__"*

(To be honest, if I finish any of the university projects, it's probably this, as I might play Minecraft again somewhere in the future and then I can play with my own mod.)
(Warum hab ich die Zeile oben auf Englisch geschrieben? Brain...)

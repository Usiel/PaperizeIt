package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class Article extends Model{
	
	public String title;
	@Column(columnDefinition = "TEXT")
	public String intro;
	@Column(columnDefinition = "TEXT")
	public String content;
	@Column(columnDefinition = "TEXT")
	public String srcUrl;
	public String imgSrc;
	public Date date;
	
	
	@ManyToOne
	public Source source;
	
	@ManyToOne
	public Preference preference;

	public Article(String title, String intro, String content, String srcUrl, String imgSrc, Source source, Preference preference, Date date) {
		this.title = title;
		this.intro = intro;
		this.content = content;
		this.srcUrl = srcUrl;
		this.imgSrc = imgSrc;
		this.source = source;
		this.preference = preference;
		this.date = date;
	}
	
	
	
	
	public static void GenerateArticles() {
		if (!Article.findAll().isEmpty()) {
			return;
		}
		
		//fetch sources from database
		Source spiegel =  null;
		Source economist = null;
		Source welt = null;
		Source bild = null;
		Source titanic = null;
		Source focus = null;
		Source faz = null;
		
		spiegel = ((Source)(Source.find("byName", "spiegel.de").fetch().get(0)));
		economist = ((Source)(Source.find("byName", "economist.de").fetch().get(0)));
		welt = ((Source)(Source.find("byName", "welt.de").fetch().get(0)));
		bild = ((Source)(Source.find("byName", "bild.de").fetch().get(0)));
		titanic = ((Source)(Source.find("byName", "titanic-magazin.de").fetch().get(0)));
		focus = ((Source)(Source.find("byName", "focus.de").fetch().get(0)));
		faz = ((Source)(Source.find("byName", "faz.de").fetch().get(0)));
		
		//fetch preferences from database
		Preference sports = new Preference(null, "Sports");
		Preference basketball = new Preference(sports, "Basketball");
		Preference football = new Preference(sports, "Football");
		Preference diving = new Preference(sports, "Diving");
		
		Preference politics = new Preference(null, "Politics");
		Preference germanPolitics = new Preference(politics, "German Politics");
		Preference internationalPolitics = new Preference(politics, "International");
		Preference law = new Preference(politics, "Law");
		
		Preference science = new Preference(null, "Science");
		Preference biology = new Preference(science, "Biology");
		Preference informatics = new Preference(science, "Informatics");
		Preference physics = new Preference(science, "Physics");
		
		Preference culture = new Preference(null, "Culture");
		Preference opera = new Preference(culture, "Opera");
		Preference cinema = new Preference(culture, "Cinema");
		Preference music = new Preference(culture, "Music");
		
		sports = ((Preference)(Preference.find("byName", "Sports").fetch().get(0)));
		basketball = ((Preference)(Preference.find("byName", "Basketball").fetch().get(0)));
		football = ((Preference)(Preference.find("byName", "Football").fetch().get(0)));
		diving = ((Preference)(Preference.find("byName", "Diving").fetch().get(0)));
		
		politics = ((Preference)(Preference.find("byName", "Politics").fetch().get(0)));
		germanPolitics = ((Preference)(Preference.find("byName", "German Politics").fetch().get(0)));
		internationalPolitics = ((Preference)(Preference.find("byName", "International").fetch().get(0)));
		law = ((Preference)(Preference.find("byName", "Law").fetch().get(0)));
		
		science = ((Preference)(Preference.find("byName", "Science").fetch().get(0)));
		biology = ((Preference)(Preference.find("byName", "Biology").fetch().get(0)));
		informatics = ((Preference)(Preference.find("byName", "Informatics").fetch().get(0)));
		physics = ((Preference)(Preference.find("byName", "Physics").fetch().get(0)));
		
		culture = ((Preference)(Preference.find("byName", "Culture").fetch().get(0)));
		opera = ((Preference)(Preference.find("byName", "Opera").fetch().get(0)));
		cinema = ((Preference)(Preference.find("byName", "Cinema").fetch().get(0)));
		music = ((Preference)(Preference.find("byName", "Music").fetch().get(0)));
		
		Article article1 = new Article("Offensive im Irak: ISIS-Extremisten rufen Islamisches Kalifat aus","Es ist eine neue Demonstration der Macht: Die ISIS-Dschihadisten haben einen Staat mit islamischer Regierungsform ausgerufen. Befehlshaber des \"Kalifats\" sei ihr Anführer Abu Bakr al-Baghdadi.","Hamburg/Bagdad - Die Dschihadistengruppe Islamischer Staat im Irak und in Großsyrien (ISIS) hat ein \"Kalifat\" ausgerufen. In einer am Sonntag im Internet veröffentlichten Audiobotschaft verkündete die Organisation die Schaffung dieser vor fast hundert Jahren verschwundenen islamischen Regierungsform.\n\nISIS veröffentlichte ihre Erklärung in mehreren Sprachen - neben Arabisch auf Englisch, Russisch, Französisch und Deutsch. Ein deutliches Zeichen dafür, dass die Dschihadisten über die Grenzen Syriens und des Irak hinaus an die Macht wollen. Deshalb hat die Gruppe auch ihren Namen offiziell geändert. Künftig heiße es nicht mehr \"Islamischer Staat im Irak und in Syrien\", sondern nur noch \"Islamischer Staat\".\n\nAnführer des Staates sei Abu Bakr al-Baghdadi, der Anführer der Dschihadisten. Als Kalif sei er fortan Befehlshaber der Gläubigen und oberster Führer des Staates. Das Wort Kalif bedeutet im Arabischen \"Nachfolger\" - Baghdadi sieht sich also als Nachfolger des Propheten Mohammed an der Spitze der islamischen Gemeinschaft.\n\nWiderspruch duldet das Kalifat nicht: In der Erklärung ruft der Islamische Staat alle Muslime auf, Baghdadi die Treue zu schwören. Alle, die dieser Forderung nicht Folge leisteten, würden als Abtrünnige behandelt und bekämpft.\n\nKampf um Tikrit\n\nDer Zeitpunkt der Erklärung ist nicht zufällig gewählt: An diesem Wochenende hat der islamische Fastenmonat Ramadan begonnen, der wichtigste Monat im islamischen Kalender.\n\nDie ISIS hatte am 9. Juni eine Offensive gegen die Regierung in Bagdad begonnen. Die radikale Sunnitengruppe brachte seitdem große Teile des Nordirak unter ihre Kontrolle. Die Dschihadisten kämpfen auch im benachbarten Syrien gegen die dortige Regierung. Ihr Ziel war stets die Gründung eines grenzübergreifenden islamischen Staates in der Region.\n\nDer syrische Arm des Terrornetzwerks al-Qaida - die Al-Nusra-Front - und andere islamistische Rebellen in Syrien haben nach Angaben der Syrischen Beobachtungsstelle für Menschenrechte vom Samstag einen Gegenangriff auf ISIS gestartet.\n\nAuch die irakische Armee hatte am Samstag eine Großoffensive gegen ISIS in der irakischen Stadt Tikrit begonnen - die islamistischen Kämpfer konnten sich jedoch offenbar erfolgreich wehren. Die Regierungstruppen haben sich laut einem BBC-Bericht in die Vorstadt Dijla, 25 Kilometer südlich von Tikrit, zurückgezogen. Es habe heftige Gefechte mit vielen Toten auf beiden Seiten gegeben, berichteten demnach Augenzeugen und Journalisten.\n\nMit der Rückeroberung der Stadt will das irakische Militär den ISIS-Vormarsch auf Bagdad stoppen. Tikrit liegt rund 140 Kilometer nordwestlich von Bagdad und ist neben Mossul eine der größten Städte in der Hand von ISIS.\n\nIrakischer General für Teilung des Landes\n\nDer Leiter des irakischen Krisenstabs im Kampf gegen die ISIS-Dschihadisten, General Ali al-Saidi, hat sich unterdessen für die Aufteilung des Landes in autonome Teilgebiete ausgesprochen. Schiiten, Sunniten und Kurden sollten jeweils ihre eigene Region erhalten, sagte der schiitische General der \"Welt am Sonntag\". Das sei \"die einzige Lösung\", um der Dschihadistengruppe ISIS den Rückhalt bei der sunnitischen Minderheit zu entziehen. \"Natürlich können sie nicht, wie früher, den gesamten Irak regieren, aber zumindest sich selbst\", sagte der General.\n\nAl-Saidi kritisierte, dass die Sunniten seit dem Sturz von Machthaber Saddam Hussein zu weit marginalisiert worden seien - dieser Vorwurf ist auch gegen den irakischen Ministerpräsident Nuri al-Maliki gemünzt: Kritiker werfen ihm vor, die Sunniten auszugrenzen und so den Vormarsch von ISIS zu begünstigen. Maliki hatte sich bisher auch immer wieder gegen eine Einheitsregierung aus Schiiten, Sunniten und Kurden ausgesprochen.","http://www.spiegel.de/politik/ausland/irak-isis-extremisten-rufen-islamisches-kalifat-aus-a-978202.html","demo1.jpg",spiegel,internationalPolitics,new Date());
		Article article2 = new Article("Entzauberter Präsident der Ukraine","Es wird weiter gekämpft und gestorben in der Ostukraine, der Friedensplan von Präsident Poroschenko scheitert. In der Ukraine werden jetzt Stimmen laut, die einen regulären Krieg gegen Separatisten und Russen fordern. Es gibt aber noch ein zweites Lager - auch das wird stärker.","Petro Poroschenko steht stark unter Druck. Man sieht das schon an der Körpersprache des ukrainischen Präsidenten, der nach seiner Wahl im Mai erst einmal alle Welt mit seinem demonstrativen Optimismus, seinem Teddybärencharme und seinem Friedensplan begeisterte.\n\nMittlerweile steht er bei öffentlichen Auftritten sehr verspannt da, die Kiefer mahlen, die Hände sind zu Fäusten geballt.\n\nDenn Petro Poroschenkos Plan scheint nicht aufzugehen. Es wird weiter gekämpft und gestorben in der Ostukraine, die Separatisten zeigen sich an keiner Stelle kompromissbereit - wenn man von der symbolischen, aber nicht kriegsentscheidenden Freilassung der zwei OSZE-Teams absieht. Und auch aus Moskau kommt wenig außer symbolischen Gesten. Die konkreten Forderungen aus Poroschenkos Friedensplan jedenfalls, Freigabe von Checkpoints und Grenzübergängen, Abgabe von Waffen und Stopp der Waffenlieferungen - sie sind bisher verpufft.\n\nAlles andere tritt in den Hintergrund:Gespräche über den Schutz der russischen Sprache, über mehr Macht für die Regionen, Lokalwahlen, mehr Finanzautonomie, baldige Parlamentswahlen. Es geht den Separatisten und ihren Hintermännern längst nicht mehr um politische Reformen, um Kompromisse, um die Interessen der Ukrainer, die sie vorzugeben vertreten. Es geht um Landgewinn.\n\nKrieg oder Nachgeben?\n\nDas Verständnis für die einseitige Waffenruhe, die der Ukrainer vor zehn Tagen ausgerufen hatte, war daher im eigenen Land gering gewesen; man hatte sie als politischen Preis für die Versprechungen vor der Wahl und als Geste an Brüssel wie Moskau akzeptiert, nach dem Motto: Vielleicht geht ja doch was, auch wenn das keiner so recht glauben mochte nach der Annexion der Krim und der militärischen Infiltration der Ostukraine.\n\nNun soll der Waffenstillstand an diesem Montag auslaufen, und in Kiew muss Poroschenko entscheiden: Gibt er den Falken in Armee und Innenministerium nach, die gegen Prorussen und Russen in einen regulären Krieg ziehen wollen? Jene Stimmen werden lauter, die sagen: Wir sind ukrainische Patrioten, wir müssen unser Land retten, wir können nicht auf Brüssel warten, das nur mit sich selbst beschäftigt ist. Dieser Teil der politischen Elite argumentiert damit, die Russen hätten sich in der Ostukraine schon festgesetzt und es sei fast zu spät, den Donbass zurückzugewinnen - wenn man jetzt nicht auf breiter Front hineingehe und kämpfe. Diese Position hat breiten Rückhalt in der Bevölkerung; ihr gilt Poroschenko zunehmend als \"lame duck\", als einer, der zu viel redet und zu viel gibt. Kein Führer, sondern ein Fürst ohne Land.\n\nEs gibt aber auch ein zweites Lager in der Ukraine, und auch das wird stärker. Intellektuelle sind darunter, aber auch Politiker. Die denken mittlerweile laut die gegenteilige Frage: Vielleicht sollte man den Donbass gehen lassen? Wer sich dort jetzt noch aufhält, will eh zu Russland. Soll Moskau das Stück Land haben, dann kann der Rest der Ukraine in Frieden leben. Auch darauf muss Poroschenko eine Antwort geben.","http://www.sueddeutsche.de/politik/petro-poroschenko-entzauberter-praesident-der-ukraine-1.2021946","demo2.jpg",spiegel,internationalPolitics,new Date());
		Article article3 = new Article("Deutsch Ersatzbank: Wir müssen draußen bleiben","Das Aufgebot des DFB bei diesem Turnier ist in zwei Teile gespalten. 16 Spieler dürfen auf WM-Einsätze hoffen, den übrigen Sieben bleibt wohl nur der Status eines Bankdrückers. Selten hat sich ein Bundestrainer so auf eine Kernmannschaft konzentriert.","Der Konkurrenzkampf - es ist eines der Lieblingswörter von Bundestrainer Joachim Löw in den vergangenen Monaten gewesen. Er hat bei jeder sich bietenden Gelegenheit darauf hingewiesen, wie eng das Gerangel um die Plätze in der deutschen Startelf bei diesem Turnier sein wird.\nJetzt sind drei WM-Partien gespielt, und das Thema hat sich extrem relativiert. Der Konkurrenzkampf beschränkt sich auf zwei, drei Positionen - ein Gutteil des 23er-Kaders hat damit gar nichts zu tun. Und darf sich auf seine Rolle als reiner WM-Bankdrücker einrichten.\nVon den 23 Spielern, die Löw mit nach Brasilien genommen hat, haben vor dem Achtelfinale gegen Algerien am Montag (22 Uhr, Liveticker SPIEGEL ONLINE) sieben noch keine WM-Minute absolviert. Und wenn alles normal läuft, wird das bei diesem Turnier auch so bleiben. Das DFB-Aufgebot ist in zwei Teile gespalten, wie es wohl noch nie bei einer WM war. Zwei Drittel, die zur engeren Wahl des Bundestrainers gehören, ein Drittel, das den Kader auffüllt, wenn man es böse ausdrückt.\n\nSieben im Kader warten auf ihre erste WM-Minute\n\nRoman Weidenfeller, Ron-Robert Zieler, Erik Durm, Kevin Großkreutz, Matthias Ginter, Julian Draxler und Christoph Kramer - es sind diese sieben Profis, die vor allem froh darüber sein können, überhaupt zum Aufgebot zu gehören. Ansprüche auf mehr können sie nicht stellen, und Löw hat sie lediglich als stille Reserve auf dem Zettel.\n\n\"Nie war es so wichtig, 14 Spieler auf hohem Niveau zu haben\", hoben Löw und Assistent Hans-Dieter Flick in diesen Tagen wiederholt hervor, um angesichts des südamerikanischen Klimas die Bedeutung der Einwechselspieler zu betonen. Dabei denken sie allerdings an Akteure wie Miroslav Klose, Bastian Schweinsteiger oder Lukas Podolski.\n\nSie sind die erste natürliche Option, wenn es um Spielerwechsel im Lauf der zweiten Spielhälfte geht. Oder um Rotationen in die Startaufstellung. Für die Anderen in der Zweidrittelgesellschaft des DFB bleibt: Auf ein Spiel um den dritten Platz hoffen - gemeinhin die einzige Gelegenheit, auch geduldigen Bankdrückern die Chance zu geben, sie aus dem Status des WM-Touristen zu erlösen.\n\nDass Weidenfeller und Zieler nur dann eine Einsatzmöglichkeit bekommen würden, wenn Stammkeeper Manuel Neuer angeschlagen oder gesperrt ausfällt, war von vornherein klar. Trotz der Erfahrung Weidenfellers: Die Hierarchie bei der deutschen Nummer eins ist in Beton gegossen. An Neuer kommt keiner vorbei, und die bisherigen Leistungen in der Gruppenphase haben das bestätigt.\n\nDurm ist ein Opfer der Taktik\n\nAber Weidenfellers Dortmunder Teamkollegen Durm und Großkreutz haben sich wohl schon insgeheim mehr ausgerechnet, als lediglich nach den Spielern ihre Stammelf-Kollegen abzuklatschen und sie während der Partie anzufeuern. Durm galt auf links hinten schon fast als konkurrenzlos, nachdem sein BVB-Konkurrent Marcel Schmelzer aus dem Aufgebot gestrichen worden war. Mittlerweile ist auch deutlich, was Löw zu dieser Entscheidung erwogen hatte. Er hatte da wohl bereits entscheiden, seine Abwehr auf vier Innenverteidiger aufzubauen. Weitere Außenspieler hielt er nicht mehr für notwendig.\n\nDas ist Durms Pech und das Glück des Schalkers Benedikt Höwedes, der dadurch auf diese Position gerückt ist. Wenn in der Abwehr jemand ersetzt werden musste, dann war der gelernte Innenverteidiger und DFB-Neuling Shkodran Mustafi die einzige bisher benutzte Option. Nach Mustafis schwacher Leistung gegen Ghana dürfte Löw für die nächsten Spiele darüber zumindest noch einmal nachdenken. Eventuell könnte dadurch Ginter doch noch auf eine Einsatzchance hoffen.\n\nDie ist für Christoph Kramer und Julian Draxler in weiter Ferne. Draxler steht in der Offensive hinten in der Schlange hinter André Schürrle, hinter Podolski - der allerdings im Achtelfinale gegen die Algerien verletzt aussetzen muss. Und Kramer ist ohnehin nur als Backup vorgesehen, falls entweder Schweinsteiger oder Sami Khedira erneut von einer Verletzung zurückgeworfen werden. Dass der Gladbacher im Gegensatz zu den Etablierten topfit ist, hat ihm zwar in Sachen Nominierung geholfen, aber nicht weiter.","http://www.spiegel.de/sport/fussball/nationalmannschaft-dfb-kader-bei-fussball-wm-2014-in-brasilien-von-loew-a-978187.html","demo3.jpg",spiegel,football,new Date());
		Article article4 = new Article("Oberste US-Richter stoppen Teil von Obamacare","Schlappe für US-Präsident Barack Obama: Familienunternehmen müssen ihren Mitarbeiterinnen keine Krankenversicherung bezahlen, in denen auch die umstrittene \"Pille danach\" abgedeckt ist.","Die Gesundheitsreform Obamacare verletzt die US-Verfassung, weil sie Firmeninhaber entgegen ihren religiösen Überzeugungen zwingt, für Mitarbeiterinnen Krankenversicherungen unter Einschluss bestimmter Formen von Schwangerschaftsabbrüchen zu bezahlen. Das hat der Supreme Court am Montagvormittag (Ortszeit) beschlossen und damit der Gesundheitsreform von Präsident Barack Obama eine Teilniederlage beigebracht.\n\nKaum war das Urteil gesprochen, hatten sich mehrere Dutzend vorwiegend weibliche Demonstrantinnen vor dem Obersten Gerichtshof versammelt. \"Geburtenkontrolle muss bleiben\", skandierten sie unter dem etwas bemüht gereimten Motto: \"Ho-ho, hey-hey, birth control is here to stay.\"\n\nDoch es geht bei dem mit fünf zu vier Stimmen äußerst knapp gesprochenen Urteil nicht um Geburtenkontrolle an sich. Die Kläger, nämlich die Familienunternehmen Conestoga Wood, ein Türen- und Möbelhersteller aus Pennsylvania, und Hobby Lobby, ein Kunsthandwerksbetrieb in Oklahoma, sahen vielmehr ihr Recht auf Religionsfreiheit durch ein spezifisches Kapitel von Obamacare ausgehebelt.\n\nUnternehmer wollen nicht für \"Sünden\" zahlen\n\nIm Kern ging es um die Frage: Dürfen Firmen, deren Inhaber Abtreibung als Sünde verstehen, gezwungen werden, für ihre Angestellten Versicherungen zu zahlen, in denen derartige Maßnahmen eingeschlossen sind? Das mag angestaubt wirken in Zeiten, in denen die Beschädigung eines Korans etwa durch Militärs weltweit unter Muslimen wie Nichtmuslimen mehr Empörung auszulösen vermag als die Tötung ungeborenen Lebens. Aber in den USA sind derartige religiöse Strömungen noch deutlich aktiver als beispielsweise in Deutschland, und dies keineswegs nur unter Katholiken. Die Familie Green, die Hobby Lobby führt, ist evangelisch. Bei den Hahns, den Gründern von Conestoga, handelt es sich um Mennoniten.\n\nIn dem 2010 von Obama unterschriebenen Affordable Care Act heißt es, dass Krankenversicherungen Medikamente abdecken müssen, die Schwangerschaftsabbrüche auslösen. Firmen mit mehr als 50 Mitarbeitern müssen die entsprechenden Prämien unabhängig von religiösen oder moralischen Einsprüchen zahlen. Arbeitgebern, die dies verweigerten, drohten Strafen von 100 Dollar pro Tag und Angestellten.\n\nGenau diese weit gefasste Verpflichtung stoppten die Richter nun. Dabei haben weder die Greens noch die Hahns Einwände gegen Verhütung etwa in Form der Pille. Sie wandten sich vielmehr konkret gegen die \"Pille danach\", die zum Abort eines bereits befruchteten Eis führt – die also aus ihrer Sicht ein Leben (in einem sehr frühen Stadium) beendet, anstatt lediglich den Beginn eines Lebens zu verhindern. Das betrifft vier von insgesamt 24 Medikamenten, die Krankenversicherungen im Rahmen von Obamacare bislang abdecken mussten. Dazu gehören Pillen wie Plan B oder Ella (Ulipristal), die bei Einnahme nach einer Empfängnis abtreibend wirken können.\n\nNur Familienunternehmen sind betroffen\n\nDie Obama-Administration hatte argumentiert, Religionsfreiheit gelte zwar für Einzelpersonen, nicht aber für auf kommerziellen Gewinn ausgerichtete Organisationen. Dieser Sicht folgte das Gericht nicht. Es übernahm mehrheitlich die Sicht der Kläger, die sich auf ein 1993 unter Präsident Bill Clinton mit breiter Mehrheit verabschiedetes Gesetz zur Ausbalancierung von staatlichen Erfordernissen und individuellen religiösen Überzeugungen beriefen (Religious Freedom Restoration Act).\n\nAllerdings wird das Urteil nur Auswirkungen auf Familienunternehmen haben, bei denen Gewissensentscheidungen und Besitzverhältnisse sehr eindeutig übereinstimmen, nicht aber auf große Konzerne.\n\nDie katholische Kirche selbst war nach einer entsprechenden Klage für ihre Einrichtungen bereits von dieser spezifischen Verpflichtung von Obamacare ausgenommen worden. Sie muss ihren Mitarbeitern beispielsweise in Krankenhäusern, Schulen oder Universitäten keine Versicherungen bezahlen, die Mittel zum Schwangerschaftsabbruch, aber auch die Pille selbst erstatten. Mit dem aktuellen Urteil haben nun erstmals auch kommerzielle Unternehmen Ausnahmen erstritten. Das könnte ein Einfallstor sein, sollten andere Familienunternehmen unter Berufung auf religiöse Überzeugungen weitergehend gegen den Versicherungsschutz für grundsätzliche Maßnahmen zur Schwangerschaftsverhütung klagen.\n\nFrauen, die in kirchlichen Einrichtungen arbeiten, können trotzdem Schwangerschaften verhüten oder abbrechen. Dies müssen sie dann aber aus eigener Tasche finanzieren. Die Pille etwa kostet in den USA für Unversicherte zwölf Dollar monatlich.","http://www.welt.de/politik/ausland/article129635326/Oberste-US-Richter-stoppen-Teil-von-Obamacare.html","demo4.jpg",welt,internationalPolitics,new Date());
		Article article5 = new Article("Cameron spricht das Wort \"Spitzenkandidat\" aus","In Brüssel musste der Regierungschef mit der Personalentscheidung für Juncker als künftigen EU-Kommissionschef eine Niederlage einstecken. Im Londoner Parlament macht er daraus einen Sieg.","Der britische Premierminister David Cameron will sich einer Zusammenarbeit mit dem designierten EU-Kommissionspräsidenten Jean-Claude Juncker nicht verschließen. Er hoffe, mit Juncker eine \"faire Lösung\" für Großbritannien zu erreichen, schrieb Cameron in einem Gastbeitrag für den \"Daily Telegraph\". Der ehemalige luxemburgische Regierungschef, gegen dessen Nominierung als EU-Kommissionschef Cameron bis zuletzt Widerstand geleistet hatte, habe in Aussicht gestellt, auf die Bedenken Londons einzugehen.\n\n\"Wenn wir uns darauf einigen können, dass wir – jeder nach seinem Rhythmus – nicht die gleichen Ziele verfolgen, dann ist eine Zusammenarbeit möglich\", erklärte der britische Premier. Am Montagnachmittag äußerte sich Cameron im britischen Parlament zum Ausgang des EU-Gipfels.\n\nDavid Camerons Gesicht verzieht sich, ehe er das ungeliebte Wort ausspricht. \"S-pitzenkandidat\", sagt der britische Premierminister, und es klingt wie ein besonders abscheuliches Verbrechen. Die Reaktion im Unterhaus scheint dem Konservativen zu gefallen. Drei Minuten später nimmt er das S-Wort schon wieder in den Mund: \"S-pitzenkandidat\", das sei die falsche Vorgehensweise, um den Leiter der EU-Kommission zu bestimmen. Deshalb habe er den Luxemburger Jean-Claude Juncker, den Kandidaten der Europäischen Volkspartei EVP, für den Posten abgelehnt. \"Es geht um ein wichtiges Prinzip.\"\n\n\"Ein schlechter Tag für Europa\"\n\nCameron wirkt seiner Sache gewiss, als er am Montagnachmittag dem Londoner Parlament vom Brüsseler EU-Gipfel berichtet. Neben ihm haben die konservativen Parteifreunde, allen voran Außenminister William Hague, auf der Regierungsbank Platz genommen. Hingegen glänzen sämtliche Minister der EU-freundlichen Liberaldemokraten, vor allem Vizepremier Nick Clegg, durch Abwesenheit. Cameron braucht genau sieben Minuten, um seine 2:26-Niederlage in einen moralischen Sieg umzumünzen: Mit Junckers Kür hätten die Staats- und Regierungschefs einen Fehler gemacht.\n\nDoch wie dies erreichen? In seiner Pressekonferenz vom Freitag hatte der Brite noch frustriert und enttäuscht geklungen, tat Juncker als \"klassischen Brüsseler Insider\" ab, was sicher nicht als Lob gemeint war. Übers Wochenende besannen sich die Konservativen auf eine diplomatischere Vorgehensweise. In der BBC beteuerte Chefdiplomat Hague treuherzig, es sei ja nie um die Person Juncker gegangen, sondern \"stets nur ums Prinzip\": Den Kommissionspräsidenten sollten auch in Zukunft die Staats- und Regierungschefs bestimmen, nicht das Brüsseler Parlament. Cameron selbst griff zum Telefonhörer und gratulierte dem ungeliebten Luxemburger zum neuen Job. Man werde zukünftig mit Juncker zusammenarbeiten, hieß es anschließend in der Downing Street.\n\nDas Feld den EU-Feinden überlassen\n\nIm Parlament formulierte es der Premierminister schon wieder etwas undiplomatischer: Der \"S-pitzenkandidat\" der EVP habe ja in seinem Wahlprogramm ausdrücklich davon gesprochen, er wolle Großbritanniens Sonderwünsche berücksichtigen. \"Wir wollen ihn daran messen.\"\n\nEinen anderen Namen hingegen erwähnt Cameron kein einziges Mal: Monatelang hatte er die deutsche Kanzlerin Angela Merkel umworben, ihr eigens im Parlament den roten Teppich ausgerollt und eine höchst seltene Audienz bei Königin Elizabeth II. verschafft. Dass ihn die vermeintliche Verbündete in der Juncker-Frage im Stich ließ, sei Anzeichen für die Isolation des Premierministers, höhnt Labour-Oppositionsführer Edward Miliband: \"Seine Strategie, mit dem britischen Austritt aus der EU zu drohen, ist fehlgeschlagen.\" Genüsslich zitiert der Sozialdemokrat aus jüngst veröffentlichten Äußerungen des konservativen polnischen Außenministers Radek Sikorski: Cameron sei nicht ernstzunehmen, weil er das Feld den parteiinternen EU-Feinden überlassen habe.\n\nDiese machen gut ein Drittel der konservativen Fraktion aus. Bei früheren Debatten haben sie ihrem Parteichef durch spitze Fragen zugesetzt. Jetzt, zehn Monate vor der nächsten Unterhauswahl, demonstrieren die Tories Einigkeit. Einer der EU-Feinde nach dem anderen gratuliert Cameron, äußert gar \"Bewunderung\" für den Premierminister, der doch eigentlich für die fortdauernde Mitgliedschaft der Insel im Brüsseler Club plädiert. Zwei Jahrzehnte lang hat sich die älteste politische Partei der Welt über der Frage der europäischen Einigung zerfleischt. An diesem Nachmittag wirkt sie selbst geeint – in der Ablehnung nicht nur des \"S-pitzenkandidaten\", sondern des gesamten Brüsseler Projekts.","http://www.welt.de/politik/ausland/article129636336/Cameron-spricht-das-Wort-S-pitzenkandidat-aus.html","demo5.jpg",welt,internationalPolitics,new Date());
		Article article6 = new Article("Ordner verbieten deutsche Fanbanner im Stadion","Die Repressionen bei der Fußball-WM 2014 gehen in die nächste Runde. Vor dem Duell mit Algerien gehen Ordner gegen deutsche Fans vor. Ob wieder alles nur ein Missverständnis seitens der Fifa ist?","Es hat ja schon so manche Ungereimtheiten in Sachen Fifa gegeben. Flitzer werden nicht in den offiziellen TV-Bildern gezeigt, Ausschreitungen auch nicht. WM-Gewinne werden steuerfrei aus dem Land geschafft. In den Bannmeilen rund um die Stadien dürfen nur Sponsorenprodukte verkauft werden. Die Weltmeisterschaft soll ein sauberes Bild abgeben. So mancher sagt sogar: ein klinisch-reines Bild.\n\nDa hinein passen keine Bilder, auf die die Fifa keinen Einfluss hat. Vor dem deutschen Spiel gegen Algerien in Porto Alegre fanden die akkreditierten Journalisten einen Hinweis im Briefumschlag mit ihren Spieltickets, dass Handy-Fotos von der Pressetribüne untersagt seien. Eine Begründung lieferte der Weltverband dafür nicht mit.\n\nAuch die deutschen Fans vor Ort sind alles andere als gut zu sprechen auf die Fifa. In den Spielen gegen Ghana und die USA ließ der Verband die Banner der Schlachtenbummler abhängen. Bereits nach dem Ghana-Spiel ließ die Fifa übermitteln, es habe sich um ein \"Missverständnis und einen Übermittlungsfehler\" gehandelt, Banner und Plakate \"werden von uns im Rahmen der bestehenden Regularien zugelassen\".\n\nDoch schon gegen die USA marschierte der Ordnungsdienst erneut in die Blöcke und kassierte die harmlosen Plakate ein. Wieder teilte der Weltverband auf \"Welt\"-Anfrage mit: \"Seitens der Fifa gab es keine Anweisungen zur Abnahme der Banner beim Spiel USA gegen Deutschland. Sollte es bei diesem Spiel ebenfalls zu vereinzelten Entfernungen von Bannern gekommen sein, dann sind diese auf die Übereifrigkeit des lokalen Sicherheitsteams zurückzuführen.\"\n\nOrdner hindern Fans mit Plakaten\n\n\"Dafür fehlt mir jegliches Verständnis. Das sind Repressionen, die weder ich noch die anderen Fans hier nachvollziehen können\", sagte Daniel Langer aus Berlin, der bislang alle deutschen Spiele live vor Ort verfolgt hat. Alles nur ein Missverständnis?","http://www.welt.de/sport/fussball/wm-2014/article129636099/Ordner-verbieten-deutsche-Fanbanner-im-Stadion.html","demo6.jpg",welt,football,new Date());
		Article article7 = new Article("Fritz-Walter-Wetter soll Vorteil für DFB-Elf sein","Bislang war die WM geprägt von großer Hitze, Trinkpausen und Wadenkrämpfen. Ausgerechnet beim Achtelfinale in Porto Alegre herrschen zwölf Grad und Dauerregen. Das kommt der deutschen Elf gelegen.","In der Nacht war es ziemlich heftig. Der Regen hatte sehr unwirsch an die Fensterscheiben der Hotels und Pensionen gepocht und einige deutsche Fans in Porto Alegre aus dem Schlaf gerissen.\n\nAls sie am Morgen danach aufwachten und herausschauten, hatte es sich im Spielort des Achtelfinals gegen Algerien nicht gebessert. Fritz-Walter-Wetter in Brasilien. Was für ein Willkommen! Damit hatten viele nicht gerechnet. Gerade die, die aus dem warmen Norden angereist waren und nur T-Shirts und Trikots dabeihaben.\n\nHier im Süden des Landes ist es im Winter jedes Jahr recht trüb, doch so oft wie in den vergangenen Tagen hat es lange nicht geregnet. In einigen Dörfern und Städten in der Region bedroht sogar Hochwasser die Existenz einiger Menschen, die lokalen Zeitungen berichten darüber in großen Artikeln.\n\nSo schlimm ist es in der Hauptstadt des Bundesstaates zum Glück längst nicht. Meist ist es Niesel- oder schwacher Regen, aber dieser ist eben sehr beständig. Die Ladenbesitzer sind gut drauf: Der Absatz an Regenschirmen ist deutlich gestiegen.\n\nWetter wie in Hamburg\n\n\"Es geht nur noch darum zu gewinnen\"\nAm Vormittag ließ der Regen dann vorübergehend nach. Die Fans aus der Bundesrepublik freuten sich und nutzten die trockenen Stunden zum Bummel durch die 1,4-Millionen-Einwohner-Stadt.\n\nDie Sonne ließ sich aber weiterhin nicht blicken – Porto Alegre präsentiert sich von seiner grauen Seite. Laut Vorhersage soll es am Nachmittag trocken bleiben, das Thermometer zeigt zwölf Grad.\n\nFür die deutsche Mannschaft könnte das Wetter eventuell ein Vorteil sein. Es erinnert mehr an Hamburg als an Strand, Samba und Caipirinha. Beim 1:0 gegen die USA in Recife waren die Spieler von Bundestrainer Joachim Löw mit dem starken Regen verhältnismäßig gut zurechtgekommen.\n\n\"Es ist auf jeden Fall leichter, von der warmen Region, in der unser Quartier liegt, in eine kältere zu kommen, als umgekehrt\", sagt Kapitän Philipp Lahm. \"Ob es aber wirklich ein Vorteil für uns ist, das werden wir erst nach dem Spiel sagen können.\"","http://www.welt.de/sport/fussball/wm-2014/article129633625/Fritz-Walter-Wetter-soll-Vorteil-fuer-DFB-Elf-sein.html","demo7.jpg",welt,football,new Date());
	
	
		article1.save();
		article2.save();
		article3.save();
		article4.save();
		article5.save();
		article6.save();
		article7.save();
	
	}
	
	
	
}

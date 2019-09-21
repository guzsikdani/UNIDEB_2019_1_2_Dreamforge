### Üzleti szereplők
<p align="justify">Jelen projektben az üzleti oldalon a Pajkaréti Közösségi Könyvtár áll, legfőképp annak könyvtári dolgozója, mivel ő lesz az, aki az elkészült programot használni fogja. A Könyvtárral a fejlesztőcsapat a Product Owner segítségével kommunikál, ő az a személy, aki a visszajelzéseket közvetíti az üzleti és a fejlesztői oldal képviselői számára.</p>

### Üzleti követelmények
<p align="justify">A Pajkaréti Közösségi Könyvtár által támasztott követelményeket követi a projekt. Azaz vállaljuk egy olyan Windows 10 60 bites operációs rendszeren futó, telepítést nem igénylő program megvalósítását, amelyen keresztül a könyvtári dolgozó nyilván tudja tartani a könyvek kölcsönzését és visszaszolgáltatását, illetve a program segítségével az állományban történő keresést is meg tudja valósítani. További elvárás az is a Könyvtár részéről, hogy a megadott határidőre (2019. szeptember 30.) elkészüljön a program.</p>

### Üzlet által elvárt funkciók
<p align="justify">A Pajkaréti Közösségi Könyvtár elsősorban egy próbaprogram fejlesztését bízta cégünkre. Értelmezésünk szerint ezt a programot a későbbiekben további funkciókkal szeretnék ellátni. A próbaprogramot tehát tekinthetjük egy Minimum Viable Productnak, amit a későbbiekben szándékozunk továbbfejleszteni, ha a Könyvtárral való együttműködés sikeresnek bizonyul.</p>

<p align="justify"> Ezek alapján tehát a próbaprogram az alábbi funkcionalitásokat szükséges, hogy tartalmazza:</p>

* A Könyvtár állományában történő keresés
* Olvasók könyvkölcsönzéseinek kezelése
* Olvasók könyv-visszaszolgáltatásainak kezelése

### Környezet és használt technológiák, eszközök
<p align="justify">Az elkészült program a Pajkaréti Közösségi Könyvtár számítógépein fog futni, amelyek jelen állapotukban nem alkalmasak arra, hogy a Windows 10 64 bites operációs rendszert futtassák, viszont a CsodaKönyvtár 1.14.4 országos pályázat keretein belül tervezik ezen számítógépek komponenseinek lecserélését, ezáltal az operációs rendszer és a program futtatása már nem fog gondot okozni. Azért ezt az operációs rendszert választottuk, mivel az erre a platformra történő fejlesztésben van a csapatunknak tapasztalata, illetve fontos szempont volt az is, hogy a választott operációs rendszer hosszú távon támogatott legyen. A CsodaKönyvtár 1.14.4 országos pályázat várhatóan biztosítani fogja az operációs rendszer licenszek megvásárlásához szükséges pénzügyi keretet is. Ha a Könyvtár igényli, akkor biztosítani tudunk számukra szakembereket, akik segíteni tudnak az operációs rendszer megfelelő feltelepítésében.</p>

<p align="justify">A programot Java programozási nyelven tervezzük megírni, azon belül is a Java SE Development Kit 11-et használva. Cégünk leginkább Java nyelven írt alkalmazások fejlesztésében tapasztalt, emiatt esett a választás erre a programozási nyelvre. A céges környezetünkben leginkább a Java SE Development Kit 8 van elterjedten használva, viszont a hosszabb támogatottsági idő reményében ezen projekt során a 11-es verziót fogjuk használni.</p>

<p align="justify">A számítógépekre várhatóan telepítve lesz a Java SE Development Kit 11, ami a használt programozási nyelvből adódóan szintén szükséges az elkészült program futtatásához.</p>

<p align="justify">A felhasználói felület kialakításához JavaFX technológiát tervezünk használni, CSS stíluslapokkal javítva annak megjelenésén. A választásunk azért esett erre a technológiára, mivel a csapatunkon belül ezt a technológiát ismerjük a legjobban. Manapság ez a technológia már nem annyira népszerű, viszont mi mégis bízunk abban, hogy megfelelő felhasználói felületet fog tudni biztosítani a fejlesztendő programunk számára.</p>

<p align="justify">A szoftverprojekt menedzselésére és a buildelés automatizálására az Apache Maven nevű eszközt használjuk. Azért ezt az eszközt választottuk, mivel kompatibilis a Java programozási nyelvvel, és a felmerülő alternatívák vagy túl elavultnak számítanak manapság (pl. Apache Ant), vagy pedig nem ismerjük őket annyira, hogy megfelelő magabiztossággal tudjuk alkalmazni (pl. Gradle). A kezeléséhez szükséges bizonyos szintű XML tudás, viszont azt a fejlesztőcsapaton belül mindannyian magabiztosan ismerjük és alkalmazzuk, így ez nem fog gondot okozni.</p>

<p align="justify">A perzisztens adatkezelés megvalósítására a Java Persistence API-t tervezzük használni. Ezen belül a Hibernate objektum-relációs leképezés eszközzel szándékozunk dolgozni a JPA implementációk közül. A csapat korábbi tapasztalatai alapján felmerült még további lehetőségként a JAXB, tehát az XML alapú perzisztens adatkezelés, illetve a Gson is, ami pedig JSON alapú. Viszont ezek a CRUD, azaz a Create, Read, Update és Delete műveletek közül az Update-et nem támogatják, a program megvalósításakor viszont várhatóan szükségünk lesz rá, emiatt le kellett mondanunk a használatukról. A JPA-t a Google Guice nevű függőség befecskendezési keretrendszer segítségével tervezzük használni.</p>

<p align="justify">Korábbi pozitív tapasztalataink alapján mindenképpen szeretnénk használni a Project Lombok nevű Java könyvtár által nyújtotta lehetőségeket, mivel rengeteg hasznos funkcióval könnyíti meg a fejlesztők életét. A könyvtár főként annotációkat definiál, amelyekkel egyszerűen és átláthatóan generálhatóak konstruktorok, getterek, setterek, illetve tartalmaz annotációt a Builder tervezési minta alkalmazásához is.</p>

<p align="justify">A hibakeresésben fontos szerepet játszik a logolás, úgy gondoljuk, hogy a hasznos log üzenetek jelentősen megkönnyíthetik a fejlesztők életét. Emiatt tervezzük valamelyik lightweight logolási keretrendszer használatát is. Többféle ilyennel is van már korábbi tapasztalatunk, pl. SLF4J, TinyLog, Java.util.logging, Logback, Log4j. A fejlesztőcsapat ezen projekt során várhatóan az SLF4J-t felhasználva fogja kezelni a logolást. A logolásra nem tervezünk konkrét log fájl létrehozását, illetve abba való kiíratást, hanem csak egyszerűen a standard outputra fogunk hagyatkozni.</p>

<p align="justify">Az egyszerű futtatás érdekében az elkészült programhoz szeretnénk az Apache Maven Shade Plugin segítségével egy futtatható JAR fájlt is létrehozni.</p>

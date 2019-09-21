### A Könyvtár igényelt rendszere
#### A Próbaprogram fejlesztése
<p align="justify">Nagyban megkönnyítené a munkánkat az, ha a DreamForge cég vállalná a nyilvántartott adataink digitalizálását és a közös együttműködés elején szükségünk lesz egy kisebb programra, amely még csak az alapvető funkciókat tartalmazná, tehát az állományban történő keresést, a kölcsönzést, és a visszaszolgáltatást. Ezáltal felmérve, hogy mennyire gördülékeny az együttműködés, de főleg azért, hogy a rendszer bevezetését ne a nagyobb, ún. éles digitális környezettel teszteljük, hanem egy kisebb, csak egy adatbázist tartalmazó szoftverrel a dolgozóink hozzászoktatása végett - ez a terv illeszkedik a másik, Fenntartható vidék 3.6.10 pályázat kereteibe, amelyben az idős lakosság informatikai készségének fejlesztése a cél.</p>

<p align="justify">A kisebb program tesztelése után közreadjuk a könyvtárhasználóknak hamis “gyakorló” adatokkal, hogy őket is rávezessük a könyvtári kereső rendszer használatára, amelyet az együttműködésünk második részében később konkrétabb irányvonalakkal kidolgozunk.</p>

<p align="justify">Az információalapú társadalomba integrálás mellett, a környezetvédelemmel szintén foglalkozunk: a papíros adminisztráció megszüntetése a környzetvédelmet segíti elő, élen járunk a település életében ezt a tudatosságot erősítve. Első körben részben, a másodikban pedig teljesen kiiktatva a sokszor használatos füzeteinket, amelyeket már nehéz vezetni. </p>

#### A jövő tervei
<p align="justify">Ha a tesztünk és a közös együttműködés sikeres, a modernizáció második lépésenként szeretnénk digitalizálni a nyilvántartási rendszerünket, illetve a leltározási folyamatot. Szeretnénk, hogy a leltáron található könyvek, a munkafolyamatok, a munkaerői és a felhasználói aktivitás egyaránt könnyedén követhetőek legyenek. Ehhez egy olyan programra van szükségünk, amely képes egy adminisztrátor közreműködésének segítségével felügyelni az alkalmazottak hozzáférését és aktivitását, mindazonáltal valós időben visszajelezni az állományban történő változásokról.</p>

<p align="justify">Újításként az elektronikus könyvek kölcsönzési lehetőségét is szeretnénk megvalósítani nyugati példa alapján. Szükségünk van egy olyan rendszerre, ami képes kezelni a könyvtárunkba beiratkozott tagok adatait, ami a következőt foglalja magában: név, születési dátum, olvasójegyszám, elérhetőségek, kölcsönzött könyvek.</p>

<p align="justify">Felhasználói felület tekintetében szeretnénk minél egyszerűbben kezelhető programot használni a mindennapok során, mivel a jelenlegi és leendő dolgozóink informatikai kompetenciájának erősítése még folyamatban van és további fejlesztéseket igényel. De, ahogy ezt már letisztáztuk a céggel, ez az idő függvénye és későbbi közös feladatunk.</p>

### Jelenlegi üzleti folyamatok modellje
Jelen állapotban minden nyilvántartással és leltározással kapcsolatos munka papíros formában történik.
* Ha egy új olvasó kerül be a könyvtári nyilvántartásba, akkor az olvasó adatait a könyvtári dolgozó feljegyzi egy papírra, ami az olvasó nyilvántartását tartalmazza.
    * Az új olvasó egy olvasójegyet kap, ami szintén tartalmazza ezeket az adatokat, továbbá kap egy olvasójegyszámot is, ami az olvasó egyedi azonosítójaként funkcionál.
    * Adatok, amelyek rögzítésre kerülnek ezen folyamat során:
        * Név
        * Születési dátum
        * Olvasójegyszám
        * Telefonszám
        * Lakcím
        * E-mail cím
* Az olvasójegyen van nyilvántartva, hogy az adott olvasó mikor, melyik könyvet kölcsönözte ki, illetve mennyi időre.
    * Ezek az adatok szerepelnek a könyvtári nyilvántartásban is, a dolgozó minden olvasó esetén egy külön papírra is feljegyzi ezeket az adatokat.
* Könyv kölcsönzése esetén a könyvtári dolgozó az olvasójegyre és a könyvtár tulajdonában lévő olvasói nyilvántartásban is feljegyzi a következő adatokat:
    * Melyik könyvre vonatkozik a kölcsönzés
    * Mikor történt a kikölcsönzés
    * Legkésőbb mikorra kell visszahoznia az olvasónak a kikölcsönzött könyvet
* Könyv kölcsönzése esetén a kikölcsönzött könyveket a könyvtári dolgozó egy, az összes könyvet tartalmazó listán az adott könyvek mellé feljegyzi az alábbiakat:
    * Mikor került kölcsönzésre a könyv
    * Melyik olvasójegyszámhoz tartozó olvasó kölcsönözte ki
* Könyv visszavitele esetén a könyvtári dolgozó az olvasójegyre és a könyvtár tulajdonában lévő olvasói nyilvántartásban is feljegyzi a következő adatokat:
    * Mikor történt a visszavitel
    * Melyik könyveket vitte vissza az olvasó
* Könyv visszavitele esetén a kikölcsönzött könyveket a könyvtári dolgozó egy, az összes könyvet tartalmazó listán az adott könyvek mellé feljegyzi az alábbi információt:
    * Mikor került visszavitelre a könyv
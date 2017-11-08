Szalai Norbert & Tóth Bálint

# Fájlmegosztó webes alkalmazás
## Az alkalmazás leírása
Fájlmegosztó webes alkalmazás, melyre fel lehet tölteni fájlokat, valamint le lehet tölteni azokat. A felhasználói élmény növelése érdekében lehetőség van a fájlok rendszerezett feltöltésére mappákba való csoportosítással. A feltöltött fájlok nyilvánossága szabályozható jogosultságok beállításával, a szabálysértő fájlok, fájlleírások jelenthetőek, a bejelentések elbírálását a moderátorok, adminisztrátorok végzik.
## Az alkalmazás funkciói
#### Felhasználói szintek
- Az alkalmazás bizonyos funkciói használhatóak regisztráció nélkül, több funkció használatához regisztráció szükséges (Username + Password + E-mail megadásával).
- Az alkalmazás lehetővé teszi "Admin" felhasználók létrehozását. Az adminoknak lehetőségük van felhasználók kitiltására, feltöltött fájlok törlésére, vagy annak adatainak módosítására (pl. név, jogosultságok).
- Az adminok létrehozhatnak jogosultsági szinteket, és hozzájuk rendelheti a jogosultsági köröket (pl. törlés, megtekintés, szerkesztés).
#### Fájlfeltöltés
- Fájlok feltöltésére a felhasználóknak van lehetőségük.
- A feltöltés során lehetőség van mappákat létrehozni és ezekbe feltölteni.
- A feltöltendő fájlhoz az adminoknak lehetősége van jogosultságok beállítására.
#### Kommentek
- A felhasználóknak lehetőségük van a számukra letölthető fájlokat kommentelni.
- A fájlokhoz tartozó kommenteket lehetőség van elrejteni/megjeleníteni.
#### Biztonság
- A szabálysértő fájlokat, kommenteket lehet jelenteni.
- A jelentéshez szöveges magyarázatot is lehet fűzni.
- A jelentett fájlokat, kommenteket az adminok, moderátorok megtekinthetik és szabálysértés esetén törölhetik, valamint kitilthatják a szabálysértő felhasználót.
## Nem funkcionális követelmények
- Letisztult, felhasználóbarát kezelőfelület.
- Felhasználók adatainak, feltöltött tartalmainak biztonságos tárolása.
- Fájlfeltölés/letöltés gyors, hibamentes működése.
## Szerepkörök
- Adminisztrátor: Az alkalmazás legmagasabb rangú karbantartója. Jogosult a felhasználók jogosultságainak módosítására, felhasználók kitiltására, fájlok módosítására, törlésére, moderátorjogok, egyéb jogkörök kiadására és elvételére.
- Moderátor: Az adminisztrátor nevezi ki. Az adminisztrátoréhoz hasonló jogkörrel bír, moderátorjogokat és egyéb jogköröket viszont nem adhat ki és nem vehet el.
- Felhasználó: Jogosult fájlok feltöltésére, letöltésére, szabálysértő elemek jelentésére.
- Adott fájl(ok)hoz jogosultságot kapott felhasználó: A felhasználó az admin döntése alapján az alábbi hozzáféréseket kaphatja meg az adott fájl(ok)hoz: hozzáférés, átnevezés, tartalommódosítás/törlés, áthelyezés (más mappába).
- Vendég: Csak publikus fájlok letöltésére van jogosultsága.

# Backend

## Fejlesztői környezet, használt technológiák
Az alkalmazás backendje egy Maven projektként kerül megvalósításra a 2017.2.5-ös verziójú IntelliJ IDEA fejlesztőkörnyezet használatával.
A gyorsabb és hatékonyabb fejlesztés érdekében a következő függőségek (dependency) lettek használva a projektben:
- JPA
- DevTools
- Lombok
- Thymeleaf
- Web
- H2

A backend fejlesztése Java programozási nyelven történik a Java Spring keretrendszerben, a funkciók láthatóságát segítő sablonok HTML-ben lettek megvalósítva, az alkalmazás mögötti adatbázis a H2.

## Könyvtárstruktúra
A projekt könyvtárstruktúrája 5 fő részre bontható:
- Model: Az adatbázis táblái és mezői.
- Repository: Az adatbázis tábláinak lekérdező műveleteit tárolja.
- Service: Az adatbázis elemeihez létrehozott műveletek.
- Controller: A projekt végpontjait kezeli.
- Templates: A végpontokat bemutató HTML sablonok.

![könyvtárstruktúra](docs/images/konyvtar.png)
## Az adatbázis struktúrája
![adatbázis](docs/images/adatb2.png)

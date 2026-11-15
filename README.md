# 🥬 VeggieProfit

### Evidencia a analytika predajne ovocia a zeleniny

![Status](https://img.shields.io/badge/status-plánovaný_projekt-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![JavaFX](https://img.shields.io/badge/UI-JavaFX-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1)

**VeggieProfit** je plánovaná desktopová aplikácia pre majiteľa malej
predajne ovocia a zeleniny. Umožní spravovať produkty a skladové zásoby,
zaznamenávať dodávky, predaje a prevádzkové výdavky a sledovať
finančné výsledky predajne.

Aplikácia bude podporovať **registráciu a prihlásenie viacerých používateľov**.
Každý účet bude predstavovať majiteľa vlastnej predajne s oddelenými údajmi.
Prihlásený používateľ bude pracovať iba so svojimi produktmi, dodávateľmi,
dodávkami, predajmi a výdavkami.

Projekt vzniká v rámci predmetu **Programovanie, algoritmy, zložitosť 1C**
na **UPJŠ v Košiciach**.

---

## 🎯 Cieľ projektu

Aplikácia bude určená pre **majiteľa predajne**, ktorý bude samostatne
vykonávať všetky operácie a sledovať:

- aktuálne množstvo produktov na sklade,
- dodané a predané množstvá,
- tržby a nákupnú hodnotu predaného tovaru,
- prevádzkové výdavky,
- finančné výsledky za zvolené obdobie,
- najpredávanejšie produkty.

## 🛠️ Plánovaná funkcionalita

### 🔐 Používateľské účty

- Spustenie aplikácie oknom prihlásenia.
- Registrácia pomocou jedinečného používateľského mena, hesla a potvrdenia hesla.
- Kontrola vyplnených polí, zhody hesiel a minimálnej dĺžky hesla.
- Ukladanie hesiel výhradne vo forme **BCrypt hashu**.
- Prihlásenie a zobrazenie mena aktuálneho používateľa v hlavnom okne.
- Odhlásenie s návratom do prihlasovacieho okna.
- Oddelenie údajov jednotlivých používateľov pri čítaní aj pri zmenách.

Pri registrácii sa spolu s účtom vytvoria jeho vlastné východiskové kategórie:

- **Produkty:** Ovocie, Zelenina.
- **Výdavky:** Nájomné, Doprava, Energie, Odpis tovaru, Iné.

Vytvorenie účtu a kategórií prebehne v jednej transakcii.
Všetci používatelia budú mať rovnakú rolu **majiteľa predajne**.

### 🥕 Produkty a skladové zásoby

- Pridávanie, úprava a deaktivácia produktov.
- Zaradenie produktov do vlastných kategórií.
- Správa vlastných kategórií; zmazanie len vtedy, ak ich nepoužívajú produkty.
- Evidencia názvu, predajnej ceny za kilogram a minimálneho stavu zásob.
- Zobrazenie aktuálneho množstva v kilogramoch.
- Vyhľadávanie podľa názvu a filtrovanie podľa kategórie.
- Upozornenia na nízky stav zásob.
- Odpis poškodeného alebo znehodnoteného tovaru: zníženie zásob a zápis
  jeho nákupnej hodnoty do vlastnej kategórie výdavkov **Odpis tovaru**.

### 🚚 Dodávatelia a dodávky

- Správa vlastných dodávateľov, ich kontaktných údajov a deaktivácia.
- Evidencia dátumu a dodávateľa každej dodávky.
- Pridanie viacerých produktov do jednej dodávky.
- Zadanie dodaného množstva a nákupnej ceny za kilogram.
- Vytvorenie nového produktu priamo pri zadávaní dodávky.
- Automatické zvýšenie zásob a prepočet priemernej nákupnej ceny.
- Uloženie dodávky, jej položiek a zmien zásob v jednej transakcii.
- Prezeranie histórie dodávok a ich položiek.

### 🛒 Predaje

- Pridanie viacerých produktov do jedného predaja.
- Zadanie predanej hmotnosti a predajnej ceny.
- Automatický výpočet celkovej sumy.
- Kontrola dostupného množstva pred potvrdením predaja.
- Automatické zníženie skladových zásob.
- Uloženie predaja, jeho položiek a zmien zásob v jednej transakcii.
- Prezeranie histórie predajov.
- Uchovanie predajnej ceny a jednotkovej nákupnej hodnoty
  platnej v čase predaja.

### 💶 Prevádzkové výdavky

- Evidencia sumy, dátumu, kategórie a opisu výdavku.
- Vlastné kategórie ako nájomné, doprava, energie, odpis tovaru a iné náklady.
- Filtrovanie výdavkov podľa obdobia a kategórie.
- Súhrn výdavkov podľa kategórií.

> Nákup tovaru sa eviduje prostredníctvom dodávok a nebude sa
> opakovane zadávať medzi prevádzkové výdavky.

## 📋 Štatistiky

Štatistiky budú vychádzať výhradne z údajov prihláseného používateľa.
Majiteľ si bude môcť zvoliť **deň, týždeň, mesiac alebo vlastné obdobie**.
Výsledky sa zobrazia ako číselné súhrny a prehľadné tabuľky.

| Ukazovateľ | Význam |
|:---|:---|
| **Tržby** | Celková suma získaná z predajov |
| **Nákupná hodnota predaného tovaru** | Náklady na obstaranie skutočne predaného množstva |
| **Hrubý zisk** | Tržby mínus nákupná hodnota predaného tovaru |
| **Prevádzkové výdavky** | Evidované výdavky za zvolené obdobie |
| **Výsledok po výdavkoch** | Hrubý zisk mínus evidované prevádzkové výdavky |

Súčasťou štatistík budú:

- predané množstvo a tržby podľa produktov,
- rebríček produktov podľa predanej hmotnosti alebo tržieb,
- rozdelenie výdavkov podľa kategórií,
- prehľad produktov s nízkym stavom zásob.

### Výpočet nákupnej hodnoty

Nákupná hodnota predaného tovaru sa určí pomocou
**priebežnej váženej priemernej nákupnej ceny**.
Pri novej dodávke sa zohľadní množstvo a hodnota existujúcich
zásob aj prijatého tovaru.

Pri predaji sa uchová aktuálna predajná cena aj jednotková nákupná
hodnota, aby neskoršie zmeny cien neovplyvnili historické výsledky.

## 📝 Spravované údaje

| Oblasť | Evidované informácie |
|:---|:---|
| **Používateľské účty** | Jedinečné meno, hash hesla a dátum vytvorenia účtu |
| **Kategórie** | Vlastné kategórie produktov a výdavkov každého používateľa |
| **Produkty** | Názov, kategória, predajná cena, minimálny a aktuálny stav zásob, priemerná nákupná cena, aktívnosť |
| **Dodávatelia** | Názov a kontaktné údaje |
| **Dodávky** | Dátum, dodávateľ, produkty, množstvá a nákupné ceny |
| **Predaje** | Dátum, produkty, predané množstvá, predajné ceny a nákupné hodnoty |
| **Výdavky** | Dátum, kategória, suma a opis |

Každá dodávka a každý predaj môžu obsahovať viacero produktov.
História operácií umožní sledovať pohyb zásob a vypočítať
výsledky za zvolené obdobie.

## 🖥️ Obrazovky aplikácie

| Obrazovka | Obsah |
|:---|:---|
| **Prihlásenie** | Zadanie mena a hesla, prechod na registráciu |
| **Registrácia** | Vytvorenie účtu a jeho východiskových kategórií |
| **Hlavný prehľad** | Prihlásený používateľ, navigácia, základné ukazovatele, nízke zásoby a odhlásenie |
| **Produkty a sklad** | Katalóg, kategórie, ceny, zásoby, vyhľadávanie a filtre |
| **Dodávatelia a dodávky** | Správa dodávateľov a kontaktných údajov, zaznamenávanie dodávok a história prijatého tovaru |
| **Predaje** | Zostavenie predaja, zadanie hmotnosti a výpočet sumy |
| **Výdavky** | Evidencia a filtrovanie prevádzkových výdavkov |
| **Štatistiky** | Výber obdobia, číselné súhrny a tabuľkové prehľady |

## 🗄️ Databázový model

Návrh databázy je uložený v súbore [init.sql](init.sql) a obsahuje **10 tabuliek**:

| Tabuľka | Účel a vlastníctvo údajov |
|:---|:---|
| `app_user` | Používateľský účet a hash hesla |
| `category` | Kategórie produktov priamo priradené používateľovi |
| `expense_category` | Kategórie výdavkov priamo priradené používateľovi |
| `supplier` | Dodávatelia priamo priradení používateľovi |
| `product` | Produkty patriace používateľovi cez kategóriu |
| `delivery` | Dodávky patriace používateľovi cez dodávateľa |
| `delivery_item` | Produkty, množstvá a nákupné ceny v dodávke |
| `sale` | Predaje priamo priradené používateľovi |
| `sale_item` | Produkty, množstvá a historické ceny v predaji |
| `expense` | Výdavky patriace používateľovi cez kategóriu výdavkov |

Vzťahy medzi dodávkami a produktmi a medzi predajmi a produktmi sú **M:N**,
realizované cez tabuľky položiek. Každý produkt sa v jednej dodávke alebo
predaji môže nachádzať najviac raz; jeho množstvo sa uvedie v príslušnej položke.

Názov kategórie bude jedinečný v rámci používateľa, názov produktu v rámci
jeho kategórie. Ceny a množstvá sa ukladajú ako **DECIMAL**.
Produkty a dodávatelia sa budú deaktivovať, aby zostala zachovaná história.

Aktuálny stav zásob (`stock`) a priemerná nákupná cena (`avg_cost`) sa ukladajú
pri produkte a aktualizujú pri skladových operáciách. Súhrnné ukazovatele
sa budú počítať z evidovaných údajov.

## ⚙️ Technické riešenie

Aplikácia bude vytvorená v **Jave 21** s používateľským rozhraním
**JavaFX**, FXML a CSS. Na správu závislostí a spúšťanie sa použije
**Maven**. Údaje sa budú ukladať do databázy **MySQL**.

Kód bude rozdelený na dátové objekty, DAO pre prístup k databáze,
služby s aplikačnou logikou a JavaFX kontroléry. Aktuálne prihláseného
používateľa bude uchovávať `Session`.

Pri čítaní aj zmene údajov sa bude overovať ich príslušnosť k prihlásenému
používateľovi. Pri ukladaní dodávok a predajov služba overí aj to, že všetky
vybrané produkty a súvisiace záznamy patria tomu istému používateľovi.
Samotné cudzie kľúče túto zhodu vlastníka v aktuálnej schéme nezaručujú.

Aplikácia bude fungovať na **Linuxe** a bude spustiteľná
z príkazového riadka. Súčasťou projektu budú testy výpočtov
a aplikačnej logiky.

## 👨‍💻 Autori

- **Volodymyr Mykytenko**
- **Vladyslav Momot**

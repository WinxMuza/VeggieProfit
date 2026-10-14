# 🥬 VeggieProfit

### Evidencia a analytika predajne ovocia a zeleniny

![Status](https://img.shields.io/badge/status-plánovaný_projekt-yellow)
![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/UI-JavaFX-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white)

**VeggieProfit** je plánovaná desktopová aplikácia pre majiteľa malej predajne
ovocia a zeleniny. Spája evidenciu produktov, skladových zásob, dodávok,
predajov a prevádzkových výdavkov s prehľadnou finančnou analytikou.

Projekt vzniká v rámci predmetu **Programovanie, algoritmy, zložitosť 1C**
na **UPJŠ v Košiciach**.

> **Stav projektu:** návrh a plánovanie. Funkcionalita uvedená nižšie
> predstavuje plánovaný rozsah aplikácie.

---

## 📑 Obsah

- [Cieľ projektu](#cieľ-projektu)
- [Používateľské roly](#používateľské-roly)
- [Plánovaná funkcionalita](#plánovaná-funkcionalita)
- [Finančná analytika](#finančná-analytika)
- [Dátový model](#dátový-model)
- [Obrazovky aplikácie](#obrazovky-aplikácie)
- [Technológie a architektúra](#technológie-a-architektúra)
- [Plán realizácie](#plán-realizácie)
- [Autori](#autori)

<a id="cieľ-projektu"></a>
## 🎯 Cieľ projektu

Cieľom aplikácie je pomôcť podnikateľovi sledovať:

- aké produkty má aktuálne na sklade,
- koľko tovaru prijal a predal,
- aké tržby dosiahol,
- koľko ho stál predaný tovar,
- aké prevádzkové výdavky zaznamenal,
- ktoré produkty sa predávajú najlepšie.

Predavač bude zaznamenávať každodenné dodávky a predaje.
Majiteľ získa prístup k celému systému a finančným výsledkom
za zvolené obdobie.

<a id="používateľské-roly"></a>
## 👥 Používateľské roly

| Funkcia | Majiteľ `OWNER` | Predavač `SELLER` |
|:---|:---:|:---:|
| Prezeranie produktov a skladových zásob | ✅ | ✅ |
| Zaznamenávanie dodávok | ✅ | ✅ |
| Zaznamenávanie predajov | ✅ | ✅ |
| Správa produktov, kategórií a predajných cien | ✅ | — |
| Správa dodávateľov | ✅ | — |
| Evidencia prevádzkových výdavkov | ✅ | — |
| Celková finančná analytika | ✅ | — |
| Správa používateľských účtov | ✅ | — |

Prihlásený používateľ bude mať dostupné funkcie podľa svojej roly.
Oprávnenia sa budú kontrolovať aj v servisnej vrstve.
Heslá budú uložené vo forme bezpečných hashov.

<a id="plánovaná-funkcionalita"></a>
## 🛠️ Plánovaná funkcionalita

### Produkty a skladové zásoby

- Pridávanie, úprava a archivácia produktov.
- Zaradenie produktov do kategórií.
- Evidencia názvu, mernej jednotky, predajnej ceny a minimálneho stavu zásob.
- Prehľad dostupného množstva v kilogramoch.
- Vyhľadávanie podľa názvu a filtrovanie podľa kategórie.
- Upozornenia na nízky stav zásob.

### Dodávky

- Výber dodávateľa a dátumu dodávky.
- Pridanie viacerých produktov do jednej dodávky.
- Evidencia množstva a nákupnej ceny za kilogram.
- Automatické zvýšenie skladových zásob.
- Uloženie používateľa, ktorý dodávku zaznamenal.
- História dodávok a ich položiek.

### Predaje

- Pridanie viacerých produktov do jedného predaja.
- Zadanie predanej hmotnosti a predajnej ceny.
- Automatický výpočet celkovej sumy.
- Kontrola dostupného množstva pred potvrdením predaja.
- Automatické zníženie skladových zásob.
- Uloženie používateľa, ktorý predaj zaznamenal.
- Uchovanie predajnej ceny a jednotkovej nákupnej hodnoty platnej pri predaji.

### Prevádzkové výdavky

- Evidencia sumy, dátumu, kategórie a opisu výdavku.
- Kategórie ako nájomné, doprava, energie, mzdy a ostatné náklady.
- História výdavkov s filtrovaním podľa obdobia a kategórie.
- Prehľad rozdelenia výdavkov podľa kategórií.

> Nákup tovaru sa eviduje prostredníctvom dodávok.
> Nebude sa opakovane zadávať medzi prevádzkové výdavky.

<a id="finančná-analytika"></a>
## 📊 Finančná analytika

Majiteľ si bude môcť vybrať **deň, týždeň, mesiac alebo vlastný interval**.

| Ukazovateľ | Význam |
|:---|:---|
| **Tržby** | Súčet predajných cien predaného množstva |
| **Nákupná hodnota predaného tovaru** | Náklady na obstaranie skutočne predaného množstva |
| **Hrubý zisk** | Tržby mínus nákupná hodnota predaného tovaru |
| **Prevádzkové výdavky** | Evidované výdavky priradené k zvolenému obdobiu |
| **Výsledok po výdavkoch** | Hrubý zisk mínus evidované prevádzkové výdavky |

Na určenie nákupnej hodnoty predaného tovaru sa použije
**priebežná vážená priemerná nákupná cena**. Pri novej dodávke sa
zohľadní hodnota a množstvo existujúcich zásob aj prijatého tovaru.

Predajná cena a jednotková nákupná hodnota sa uložia do položky predaja.
Neskoršie zmeny cien tak nebudú meniť historické výsledky.

**Súčasťou analytiky budú:**

- grafy vývoja tržieb, zisku a prevádzkových výdavkov,
- rebríček produktov podľa predanej hmotnosti a tržieb,
- rozdelenie prevádzkových výdavkov podľa kategórií,
- porovnanie výsledkov dvoch období,
- upozornenia na produkty s nízkym stavom zásob.

> Aplikácia poskytuje prevádzkovú analytiku na základe evidovaných údajov.
> Nenahrádza kompletný účtovný ani daňový systém.

<a id="dátový-model"></a>
## 🗃️ Dátový model

Údaje budú uložené v relačnej databáze.

| Entita | Hlavné údaje |
|:---|:---|
| `Product` | ID, názov, kategória, merná jednotka, predajná cena, minimálny stav zásob, aktivita |
| `Category` | ID, názov kategórie |
| `Supplier` | ID, názov, telefón, kontaktné údaje |
| `Purchase` | ID, dátum, dodávateľ, používateľ, poznámka |
| `PurchaseItem` | ID, dodávka, produkt, množstvo, jednotková nákupná cena |
| `Sale` | ID, dátum, používateľ, celková suma |
| `SaleItem` | ID, predaj, produkt, množstvo, jednotková predajná cena, jednotková nákupná hodnota |
| `Expense` | ID, kategória výdavku, suma, dátum, opis |
| `AppUser` | ID, prihlasovacie meno, hash hesla, rola |

### Vzťahy medzi entitami

- Jedna kategória môže obsahovať viacero produktov.
- Jeden dodávateľ môže byť priradený k viacerým dodávkam.
- Každá dodávka obsahuje jednu alebo viac položiek.
- Každý predaj obsahuje jednu alebo viac položiek.
- Položky dodávok a predajov odkazujú na konkrétne produkty.
- Dodávky a predaje sú priradené používateľovi, ktorý ich zaznamenal.

<a id="obrazovky-aplikácie"></a>
## 🖥️ Obrazovky aplikácie

| Obrazovka | Obsah |
|:---|:---|
| **Prihlásenie** | Overenie používateľa a jeho roly |
| **Dashboard** | Súhrnný prehľad a upozornenia podľa oprávnení |
| **Produkty a sklad** | Katalóg, kategórie, ceny, zásoby, vyhľadávanie a filtre |
| **Dodávky** | Registrácia dodávok, výber dodávateľa a história |
| **Predaje** | Zostavenie predaja, zadanie hmotnosti a výpočet sumy |
| **Výdavky** | Evidencia a filtrovanie prevádzkových výdavkov |
| **Analytika** | Výber obdobia, finančné ukazovatele, grafy a rebríčky |

Správa používateľov, dodávateľov a úpravy záznamov budú dostupné
prostredníctvom doplnkových formulárov.

<a id="technológie-a-architektúra"></a>
## ⚙️ Technológie a architektúra

| Technológia | Účel |
|:---|:---|
| **Java 21** | Aplikačná logika |
| **JavaFX, FXML a CSS** | Grafické rozhranie |
| **MySQL** | Ukladanie údajov |
| **JDBC / JdbcTemplate** | Komunikácia s databázou |
| **HikariCP** | Správa databázových pripojení |
| **Maven** | Správa závislostí, zostavenie a spúšťanie |
| **Docker Compose** | Spustenie databázy |
| **JUnit 5 a Testcontainers** | Testovanie logiky a DAO vrstvy |

Aplikácia bude rozdelená na:

- **UI vrstvu** – obrazovky a kontroléry JavaFX,
- **servisnú vrstvu** – výpočty, validácie, oprávnenia a spracovanie operácií,
- **DAO vrstvu** – prístup k databáze,
- **entity** – reprezentáciu spravovaných údajov.

Dodávky a predaje sa budú ukladať transakčne, aby sa predišlo
čiastočne uloženým operáciám a nekonzistentným zásobám.

Aplikácia bude podporovať **Linux** a spustenie z príkazového riadka.
Presný postup spustenia bude doplnený po vytvorení funkčnej verzie.

<a id="plán-realizácie"></a>
## 📋 Plán realizácie

### Základná verzia

- [ ] Návrh databázy a entity
- [ ] DAO vrstva a databázové testy
- [ ] Prihlásenie a roly OWNER / SELLER
- [ ] Správa produktov, kategórií a dodávateľov
- [ ] Evidencia dodávok
- [ ] Evidencia predajov a kontrola zásob
- [ ] Evidencia prevádzkových výdavkov
- [ ] Výpočet nákupnej hodnoty predaného tovaru a zisku
- [ ] Dashboard, grafy a porovnanie období
- [ ] Testy aplikačnej logiky
- [ ] Overenie spustenia na Linuxe

### Možné rozšírenia

- [ ] Odpisy pokazených alebo poškodených produktov
- [ ] Export štatistík do CSV

<a id="autori"></a>
## 👨‍💻 Autori

| Člen tímu |
|:---|
| [Meno a priezvisko] |
| [Meno a priezvisko] |

**Predmet:** Programovanie, algoritmy, zložitosť 1C  
**Univerzita:** Univerzita Pavla Jozefa Šafárika v Košiciach

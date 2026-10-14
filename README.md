# 🥬 VeggieProfit

### Evidencia a analytika predajne ovocia a zeleniny

![Status](https://img.shields.io/badge/status-plánovaný_projekt-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![JavaFX](https://img.shields.io/badge/UI-JavaFX-blue)

**VeggieProfit** je plánovaná desktopová aplikácia pre majiteľa malej
predajne ovocia a zeleniny. Umožní spravovať produkty a skladové zásoby,
zaznamenávať dodávky, predaje a prevádzkové výdavky a sledovať
finančné výsledky predajne.

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

### 🥕 Produkty a skladové zásoby

- Pridávanie, úprava a archivácia produktov.
- Zaradenie produktov do kategórií.
- Evidencia názvu, mernej jednotky, predajnej ceny za kilogram
  a minimálneho požadovaného stavu zásob.
- Zobrazenie aktuálneho množstva v kilogramoch.
- Vyhľadávanie podľa názvu a filtrovanie podľa kategórie.
- Upozornenia na nízky stav zásob.

### 🚚 Dodávatelia a dodávky

- Správa dodávateľov a ich kontaktných údajov.
- Evidencia dátumu a dodávateľa každej dodávky.
- Pridanie viacerých produktov do jednej dodávky.
- Zadanie dodaného množstva a nákupnej ceny za kilogram.
- Automatické zvýšenie skladových zásob.
- Prezeranie histórie dodávok a ich položiek.

### 🛒 Predaje

- Pridanie viacerých produktov do jedného predaja.
- Zadanie predanej hmotnosti a predajnej ceny.
- Automatický výpočet celkovej sumy.
- Kontrola dostupného množstva pred potvrdením predaja.
- Automatické zníženie skladových zásob.
- Prezeranie histórie predajov.
- Uchovanie predajnej ceny a jednotkovej nákupnej hodnoty
  platnej v čase predaja.

### 💶 Prevádzkové výdavky

- Evidencia sumy, dátumu, kategórie a opisu výdavku.
- Kategórie ako nájomné, doprava, energie a ostatné náklady.
- Filtrovanie výdavkov podľa obdobia a kategórie.
- Súhrn výdavkov podľa kategórií.

> Nákup tovaru sa eviduje prostredníctvom dodávok a nebude sa
> opakovane zadávať medzi prevádzkové výdavky.

## 📋 Štatistiky

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
| **Produkty** | Názov, kategória, merná jednotka, predajná cena, minimálny stav zásob |
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
| **Hlavný prehľad** | Základné ukazovatele a upozornenia na nízke zásoby |
| **Produkty a sklad** | Katalóg, kategórie, ceny, zásoby, vyhľadávanie a filtre |
| **Dodávatelia** | Správa dodávateľov a kontaktných údajov |
| **Dodávky** | Zaznamenávanie dodávok a história prijatého tovaru |
| **Predaje** | Zostavenie predaja, zadanie hmotnosti a výpočet sumy |
| **Výdavky** | Evidencia a filtrovanie prevádzkových výdavkov |
| **Štatistiky** | Výber obdobia, číselné súhrny a tabuľkové prehľady |

## ⚙️ Technické riešenie

Aplikácia bude vytvorená v **Jave 21** s používateľským rozhraním
**JavaFX**, FXML a CSS. Na správu závislostí a spúšťanie sa použije
**Maven**.

Aplikácia bude fungovať na **Linuxe** a bude spustiteľná
z príkazového riadka. Súčasťou projektu budú testy výpočtov
a aplikačnej logiky.

## 👨‍💻 Autori

- **Volodymyr Mykytenko**
- **Vladyslav Momot**

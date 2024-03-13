## Rakenduse käivitamise juhised

### Eeltingimused:

- Veenduge, et **Docker** on teie arvutisse installitud.
- **Docker Engine** peab olema käivitatud.

### Käivitamise sammud:

1. **Liikuge** projekti juurkataloogi.
2. **Avage** terminaliaken.
3. **Sisestage** järgmine käsk: *docker-compose up --build*

### Frontendi ligipääs:

- Frontendi juurde pääseb aadressil: [http://localhost:4200](http://localhost:4200)


## Protsessi kohta

Minu jaoks selle ülesande kõige keerulisem osa oli algse kontseptsiooni väljamõtlemine - kuidas mahutada kõik need funktsioonid rakendusse ja kuidas neid kasutajasõbralikumalt implementeerida. Siinkohal pöördusin kinoteatrite veebilehtede poole inspiratsiooni saamiseks.

Teine keeruline ülesanne oli parima koha otsingu algoritmi kirjutamine saalis. Valitud algoritm toimib enamiku juhtude korral hästi, kuid võib esineda probleeme suuremate gruppide (rohkem kui 15 inimest) puhul. Ei ole kindel oma praeguse lahenduse tõhususes. Võimalik, et pean selle tulevikus ümber kirjutama.
## Funktsionaalsus

- [x] Kõikide seansside kuvamine nädala jooksul lehel
- [x] Seansside filtreerimise lisamine mitmete kriteeriumide alusel
- [x] Kohtade soovitamine seansil, arvestades piletitite arvu
- [x] Kasutaja seansside ajaloo kuvamine
- [x] Filmide soovitamine kasutajale, lähtudes varasemalt vaadatud filmidest
- [x] Backendis testitud kontrollerid ja teenused
- [x] Frontendis testitud *SessionPage* ja *SessionCard* komponendid
- [x] Docker Compose loomine rakenduse mugavaks käivitamiseks


## Probleemide lahendamine

Probleemide lahendamiseks kasutasin mitmeid lähenemisviise:

1. Kontrollisin oma varasemate projektide lähenemisviise ja rakendasin sarnaseid lahendusi.
2. Otsisin abi Stackoverflow'st ja Google'ist, otsides vastuseid võtmesõnade abil.
3. Vajadusel pöördusin AI teenuste poole, et saada abi konkreetsete probleemide lahendamisel.

AI teenused olid vähem kasulikud, kuna nende vastused ei pruukinud alati vastata minu ootustele või lahendada probleemi nii, nagu ma soovisin. Kuid neid oli võimalik kasutada kiirendamaks teatud protsesse, eriti kui algoritm või funktsionaalsus oli juba põhjalikult läbi mõeldud ja vajas vaid kiiret rakendamist.

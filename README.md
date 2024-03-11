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

Teine keeruline ülesanne oli parima koha otsingu algoritmi kirjutamine saalis. Probleem seisnes selles, kuidas paigutada gruppe. Otsustasin, et kõige loogilisem on - kõik istuvad ühes reas. Kui aga ühes reas istumine pole võimalik, siis lihtsalt jaotatakse üle saali kõige mugavamatesse kohtadesse, kuna siin muutub tähtsamaks olla keskusele lähemal, kui koonduda ühte punti (selle kohta ja seansside soovitusalgoritmi kohta pole ma kindel ja tõenäoliselt kirjutaksin need tulevikus ümber).

## Funktsionaalsus

- [x] Kõikide seansside kuvamine nädala jooksul lehel
- [x] Seansside filtreerimise lisamine mitmete kriteeriumide alusel
- [x] Kohtade soovitamine seansil, arvestades piletitite arvu
- [x] Kasutaja seansside ajaloo kuvamine
- [x] Filmide soovitamine kasutajale, lähtudes varasemalt vaadatud filmidest
- [x] Docker Compose loomine rakenduse mugavaks käivitamiseks

# app-Digitalizacija
Repozitorij za projektni zadatak digitalizacija na predmetu Programsko Inženjerstvo na Fakultetu elektrotehnike i računarstva

Link na aplikaciju deployanu na servisu Render se nalazi ovdje: https://kompletici-front.onrender.com

Aplikacija za OCR (Optičko prepoznavanje znakova)

Opis projekta

Cilj ove aplikacije omogućiti korisnicima da jednostavno i brzo izvode optičko prepoznavanje znakova (OCR) na slikama koje uploadaju. Nakon što korisnik uploada sliku, aplikacija će obraditi sliku i vratiti mu tekst koji je isčitan iz slike.

Uloge korisnika

Aplikacija uključuje više razina korisničkih uloga, svaka s različitim privilegijama pregleda i manipulacije podacima. Evo popisa uloga:

Direktor: Pravo pregleda statistike aktivnosti za sve korisnike. Pregled cijele povijesti skeniranih dokumenata. Brisanje računa zaposlenika. Potpisivanje skeniranih dokumenata.

Revizor: Pregledavanje dokumenata koje su zaposlenici skenirali. Promjena kategorije dokumenata. Proslijeđivanje određenih dokumenata računovođi.

Računovođa: Slanje dokumenata direktoru na potpisivanje. Arhiviranje dokumenata.

Zaposlenik: Skeniranje dokumenata. Slanje skeniranih dokumenata revizoru. Pregled vlastite povijesti skeniranja. Autentikacija i Registracija

Aplikacija počinje s Login i Register stranicama:

Korisnici s postojećim računom mogu se prijaviti putem Login stranice. Korisnici bez računa mogu stvoriti novi račun putem Registracije.

Lokalno pokretanje aplikacije

Aplikaciju je moguće lokalno pokrenuti na sljedeći način:

Pokretanje baze u PGAdmin: Inicijalizirajte bazu podataka u PGAdmin prema postavkama u priloženom SQL dumpu. Pokretanje backenda u IntelliJ: Uvezite backend projekt u IntelliJ. Konfigurirajte postavke baze podataka u aplikaciji prema lokalnim postavkama. Pokrenite backend aplikaciju. Pokretanje frontenda u Reactu: Uvezite frontend projekt u odgovarajući razvojni okoliš. Otvorite terminal i navigirajte do direktorija frontend projekta. Pokrenite frontend aplikaciju pomoću naredbe npm start. Nakon ovih koraka, aplikacija će biti dostupna lokalno, a korisnici mogu pristupiti funkcionalnostima OCR-a, upravljati dokumentima te pregledavati i obrađivati podatke sukladno svojim ulogama.

Praćenje i kontrola koda

Za praćenje i kontrolu koda korišten je Git/GitHub. Sve izmjene i novosti u kodu prate se kroz GitHub repozitorij.

Deploy i hosting

Aplikacija je deployana uz pomoć Render alata, što osigurava stabilno i brzo iskustvo korisnika prilikom korištenja aplikacije.

Dokumentacija

Dokumentacija je pisana u alatu LaTeX radi jasnoće, preglednosti i lakšeg održavanja. Svi relevantni dokumenti i upute nalaze se u odgovarajućim LaTeX datotekama.

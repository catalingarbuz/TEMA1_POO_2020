GARBUZ CATALIN 323CD

Realizarea temei VideosDB:

Punctul de inceput in parcurgerea comenzilor este in clasa Commands,
apelata din main. In clasa Commands, in metoda iterateCommands, iterez
prin lista de comenzi de la input si verificand campurile: ActionType
Type, ObjectType, apelez clasele predestinate sa indeplineasca Comenzile,
Query-urile sau Recomandarile.

Comanda Favorite:

Iterez prin lista de utilizatori pentru a gasi utilizatorul cu username-ul      
din comanda de la input. Cand il gasesc, verific daca arraylist-ul sau de 
favorite movies contine deja filmul(folosind contains), daca nu contine filmul
verific daca filmul a fost deja privit de user(folosind containsKey) sau daca nu
 a fost privit, si pentru toate cazurile printez mesajele corespunzatoare.

Comanda View:

Din nou iterez prin lista de utilizatori pentru a gasi utilizatorul cu 
username-ul din comanda de la input. Cand il gasesc verific in istoria 
utilizatorului daca filmul a fost privit mai inainte si incrementez numarul de
vizualizari al acestuia, daca nu a fost vizionat, creez o intrare noua in 
Map-ul de filme a utilizatorului cu titlul filmului si 1 vizionare. Pentru 
ambele cazuri printez mesajele corespunzatoare.

Comanda Rating:

Pentru a salva rating-urile mi-am creeat campuri noi in MovieInputData,
SerialInputData si Season. Aceste campuri reprezinta Hashtable-uri in care voi
stoca numele utilizatorului care da nota filmului si nota sa.
Iterez prin lista de utilizatori pentru a gasi utilizatorul. Cand il gasesc,
verific daca filmul de la input a fost privit de user pentru a-i putea fi oferit
rating. In continuare verific daca acesta nu e serial (seasonNumber == 0). Daca
nu, atunci iterez prin lista de filme pentru a gasi filmul, cand il gasesc, 
atat in cazul filmelor si a serialelor, verific daca hashtable-ul cu ratinguri
a acestora nu contine deja username-ul. Daca nu, atunci adaug in hashtable-ul
respectiv o intrare noua. In cazul serialelor notele userilor le adaug la 
sezonul respectiv pentru ca in continuare sa pot calcula ratingul serialului mai
usor.

Query-uri Actori

Average: 

Mi-am creat o lista de tip Actor, in care voi salva numele actorului si media
ratingurilor filmelor si a serialelor in care a jucat. Pentru asta iterez prin
lista de actori, pentru fiecare actor iterez prin lista de filme si seriale si
verific in care filme sau seriale a jucat actorul. Atat pentru filme si pentru
seriale mi-am creat prealabil metode pentru calculul ratingului acestora. Cand
gasesc un film/serial in care a jucat actorul adun intr-o variabila ratingul
acestuia, de asemenea, daca ratingul acestuia e > 0, atunci numar cate filme 
si seriale au ratingul > 0 pentru calculul mediei. La sfarsit imi creez un 
obiect de tip actor si adaug in lista numele actorului si media ratingurilor.
La sfarsit sortez arraylist-ul in functie de medie si nume, in ordinea ceruta la
input, folosind interfata functionala Comparator si printez mesajul corespunzator
querry-ului.

Awards:

Iterez prin lista de actori. Pentru fiecare actor iterez prin lista de awards de
la input si numar folosind .containsKey, cate premii are actorul. Daca numarul 
de premii este egal cu numarul premiilor din araylist-ul de premii de la input, 
atunci numar cate premii de fiecare fel are un actor, si salvez numele actorului
si numarul premiilor intr-un array-list pe care la urma il sortez alfabetic in
ordinea ceruta de la input si printez mesajul corespunzator querry-ului.

Filter Description: 

Iterez prin lista de actori. Folosind StringTokenizer, tokenizez descrierea pentru
fiecare actor. Intr-un ciclu while, iterez prin descrierea actorului, in cadrul 
aceluiasi ciclu, iterez prin lista de cuvinte data la input, daca gasesc un 
cuvant, atunci in "copia" listei de cuvinte data la input, il setez "0" pentru ca
sa nu il mai numar inca odata, si incrementez numarul de cuvinte gasite cu 1.
La iesirea din ciclul while, verific daca numarul de cuvinte gasite este egal cu
marimea listei de cuvinte date la input, daca da, adaug numele actorului intr-un 
ArrayList de stringuri si la sfarsit ordonez arraylist-ul in ordinea ceruta la 
input si printez mesajul corespunzator querry-ului.

Query-uri video-uri

Rating:

Atat pentru filme cat si pentru seriale, parcurg lista de video-uri. Pentru fiecare
video in parte verific daca acesta corespunde filtrelor de la intrare, daca acestea
au fost introduse. Daca corespunde, sau daca filtrele lipsesc, verific daca
video-ul are rating-ul > 0, daca da il adaug intr-un arraylist in care salvez 
titlul si ratingul video-ului, pentru ca mai apoi sa sortez ratingul in functie de
rating si titlu in ordinea ceruta la input si printez mesajul corespunzator.

Favorite:

Pentru acest task, iterez prin lista de filme/seriale si salvez pentru inceput 
titlurile video-urilor care respecta filtrul din comanda de la input. In continuare, 
Intr-un ciclu for, iterez prin lista de titluri de video, in cadrul aceluiasi for,
iterez prin lista de useri, pentru a numara cati useri au fiecare din video la lista
de favorite, la iesirea din cel de-al doilea ciclu, verific daca favnr e > 0, atunci
salvez intr-un arraylist denumirea video-ului si numarul de cate ori a fost adaugat
la favorite, la sfarsit sortez array-ul dupa favnr si titlu, il ordonez in ordinea
ceruta la input si afisez mesajul corespunzator.

Longest:

Atat pentru seriale cat si pentru filme iterez prin lista de "video-uri". Pentru
fiecare videou, verific daca acesta corespunde filtrelor introduse la input si 
daca corespund salvez intr-un arraylist titlul si durata video-ului. La sfarsit,
ordonez arraylist-ul in functie de durata si titlu, ordonand-ul in ordinea 
ceruta de comanda. Afisez mesajul corespunzator query-ului.

Most Viewed:

Atat pentru seriale cat si pentru filme iterez prin lista de "video-uri". Pentru
fiecare video, verific daca acesta corespunde filtrelor introduse la input si 
daca corespund, iterez prin lista de utilizatori pentru a calcula numarul total
de vizualizari a video-ului. Dupa ce am iterat prin lista de utilizatori, 
verific daca nrviews > 0, atunci salvez intr-un ArrayList titlul video-ului si
numarul de vizualizari al acestuia. La sfarsit, ordonez arraylist-ul in functie
de numar de vizualizari si titlu, ordonand-ul in ordinea ceruta de comanda.
Afisez mesajul corespunzator query-ului.

Users Query :

Pentru acest task, prealabil mi-am creat un camp Ratingnr in UserInputData, care
era incrementat la Comanda Rating, de fiecare data cand userul acorda o nota 
unui video.
Iterez prin lista de useri. Pentru fiecare user salvez intr-un arraylist 
username-ul si numarul de ratinguri acordate ale acestuia. La sfarsit, ordonez
arraylist-ul in functie de numar de ratinguri oferite si username, ordonand-ul
in ordinea ceruta de comanda. Afisez mesajul corespunzator query-ului.

QUERY-URI : Mi-am creat mai multe clase ajutatoare pentru a crea araylist-urile
mentionate in query-urile de mai sus. Aceste clase se afla in package-ul utils.
De asemenea unele din ele sunt folosite si la recomandari.

Strategii de recomandare:

Pentru toti utilizatorii:

Standard 

Imi declar un arraylist in care voi salva video-urile care nu au fost vazute 
de un user. Iterez prin lista de useri, pana nu gasesc userul cu username-ul
din comanda. Cand il gasesc incep sa iterez prin filmele din Database. Daca
denumirea filmului nu este in map-ul History, al user-ului atunci o adaug la
array-ul unseenmovies. Dupa ce iterez prin filme verific daca marimea 
arraylist-ului in care am salvat filmele este egala cu 0, daca da atunci 
iterez si prin toate serialele, facand acelasi lucru ca la filme. Intr-un final
mai verific odata daca araylist-ul cu video-uri unseen are marimea > 0 si 
printez primul titlu din arraylist, in celalalt caz afisez mesajul corespunzator.

Best Unseen

Pentru inceput iterez prin toti userii, pentru a gasi userul cu username-ul din
comanda. Daca l-am gasit, iterez prin filme, daca gasesc un film care nu este
vizionat de user si are rating > 0, atunci il adaug intr-un arraylist unde 
salvez titlul filmului, ratingul si pozitia sa. Acelasi lucru fac si pentru 
seriale si, intr-un final, daca size-ul acestui array este 0, gasesc ca la 
recomandarea Standard primul film nevazut de user si afisez mesajul corespunzator,
altfel sortez array-ul descrescator dupa rating si pozitie si afisez titlul
primului element, alaturi de mesajul corespunzator.

Pentru utilizatorii premium: 

In cadrul fiecarei recomandari mai intai verific daca subscriptia utilizatorului
este premium sau basic, daca e basic afisez mesaju ca strategia respectiva nu 
poate fi aplicata.
Pentru "Popular" mai intai am salvat intr-un arraylist numele genului si numarul
de filme care au acel gen. Sortez acest array descrescator pentru ca de la inceput
sa fie genurile mai populare. In afara ciclului imi declar variabila result null,
intr-un while iterez mai intai prin filme, daca gasesc un film care contine genul
de la idx-ul actual, care nu este vizionat de user si variabila result inca este
null, atunci salvez in aceasta numele filmului. Daca dupa iesirea din ciclu, 
result inca mai este null, iterez prin seriale si fac acelasi lucru ca la filme.
Daca result e diferit de null la iesirea din ultimul ciclu, atunci iesim din 
ciclul while, altfel, trecem la urmatorul gen. Intr-un final daca variabila 
result e diferita de null afisam rezultatul corespunzator, altfel afisam mesajul
ca recomandarea popular nu poate fi aplicata.

Pentru "Favorite" iterez prin toate filmele, pentru fiecare film iterez prin lista
de utilizatori si incrementez variabila favnr daca filmul este in lista de favorite
a utilizatorului. Dupa ce iterez prin toti utilizatorii, daca favnr e mai mare ca
0, salvez intr-un arraylist titlul filmului, numarul de liste favorite din care 
face parte si pozitia sa in baza de date. Acelasi lucru fac si pentru seriale.
Daca dimensiunea acestui array la sfarsit este 0, atunci afisez ca recomandarea
nu poate fi aplicata, altfel sortez crescator array-ul dupa nrfav si pozitie.
Dupa asta iterez prin array, si de fiecare data cand gasesc un video nevizualizat
de user-ul din comanda, il pun in String-ul video, astfel ultimul video adaugat
va fi cel mai des adaugat la lista de favorite. Daca dupa iterarea prin array, 
string-ul video a ramas null, afisez mesajul ca recomandarea nu a putut fi aplicata,
altfel afisez mesajul corespunzator.

Pentru "Search" 
Iterez prin toate filmele, pentru fiecare film verific daca acesta contine genul dat
ca filtru de la input si daca userul din comanda nu a vizionat acel film. Daca cele 
doua se respecta salvez intr-un arraylist titlul filmului si ratingul sau. Acelasi
lucru fac si pentru seriale. Intr-un sfarsit, daca marimea array-ului este mai mare
decat 0, sortez array-ul crescator dupa rating si titlu, iterez prin array-ul sortat
si salvez intr-un string arraylist, titlurile video-urilor care urmeaza sa fie 
afisate ca rezultat al comenzii.

Feedback: 
Tema a fost interesanta, in unele momente complicata(calculul ratingului pentru seriale,
verificarea daca un user a dat rating mai inainte unui serial, querry rating, 
popular recom). In urma parcurgerii temei, m-am familiarizat bine cu limbajul si
coding style-ul java. De asemenea am inteles ca mai sunt multe lucruri pe care 
trebuie sa le invat mai bine.

P.S. Cand am zis ca afisez sau printez rezultatul, ma refer la faptul ca il adaug in
arrayResult.


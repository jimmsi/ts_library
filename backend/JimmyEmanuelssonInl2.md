1. Vad upplevde du som mest utmanande med att skriva tester med Selenium?

Jag upplevde det väldigt utmanande att hitta element på ett effektivt och 
säkert sätt. Dels finns det många olika sätt att hitta samma element, och 
även om man använder en css-selector så kan den skrivas på många olika sätt:
"form.max-w-md.bg-base-300", "input[placeholder=\"Author\"]", etc. Svårt att 
veta vilken typ som funkar i vilket sammanhang, och i andra sammanhang var 
det mer effektivt (eller man var tvungen) att välja xpath, då annat inte 
fanns att tillgå. linkText är ytterligare en variant och varför måste man 
använda stora bokstäver när länktexten i sig inte gör det? Utmaningen i det 
här sammanhanget tycker jag består i att det inte finns något tydligt 
mönster att följa och att man ofta måste tänka olika i varje enskilt fall. 
En relaterad utmaning handlar om att veta vilka xpath och vilka 
css-selektorer som är särskilt känsliga för förändring (vilket potentiellt 
gör att testerna måste skrivas om).

2. Vad tror du är största utmaningen med att få till bra acceptanstester med 
Cucumber?

Den största utmaningen med att få till bra acceptanstester med Cucumber är 
att hitta en balans där den Gherkin man skriver är tillräckligt detaljerad för 
att fånga alla tänkbara krav som är önskvärda för kunden (oavsett om kunden är 
medveten om att kraven är önskvärda) men där man samtidigt skär ner på 
detaljer i den mån att man inte låser sig vid specifika tekniska 
implementationer. 

Är man för detaljerad blir det svårt att underhålla tester om programmet 
(och koden) ändras. Är man för generell och ospecifik blir det ofta "för lätt" 
att leva upp till kraven, och det blir också svårare att vara överens mellan 
affärssidan och utvecklarsidan kring vad kraven innebär. Säg att man har 
följande:

När användaren skapar en kund
Då ska kunden skapas

Utvecklarna kan koda vilket system som helst där man skapar kunder med 
valfria fält. Affärssidan kanske tycker det är självklart att kunder ska ha 
egenskaper såsom epost, telefonnummer, osv., men om detta inte tydliggörs i 
den gemensamma gherkin är det svårt att veta om kraven är uppfyllda.

3. Berätta om och motivera dina val av tester för uppgift två i workshop sju.

Jag har gjort två tester för uppgift två. Först testar jag att användaren ska 
kunna navigera från startsidan till söksidan för att sedan söka på böcker av en 
viss författare och att sökresultatet ska inkludera alla böcker som biblioteket 
har av den givna författaren. Detta är ett rimligt test för det visar på många 
olika saker, dels att navigering fungerar, att sökformuläret visas, att 
användaren kan skriva in ett namn under Author, att formuläret skickas, att ett 
resultat presenteras, och att resultatet inkluderar all den data som förväntas.
Eftersom sökning på författarnamn i det här fallet genererar fler än en bok 
testar jag också så att samtliga böcker faktiskt är skrivna av den givna 
författaren och om någon av böckerna (mot förmodan) skulle vara skrivna av 
någon annan så går testet inte igenom. 

Det andra testet kontrollerar så att jag får ett informativt och 
korrekt meddelande (No Books found) när jag söker på en titel som inte finns i 
biblioteket. Det är ett rimligt test, dels för att det är ett vanligt 
användarscenario, att man helt enkelt inte hittar en bok man söker efter, 
men också för att testa så systemet visar rätt (fel)meddelande när data 
(böcker) inte finns, vilket är en viktig funktion i sig hos ett system. Jag 
testar här i tre separata steg så det faktiskt rör sig om (1) ett tomt 
resultat, (2) att meddelandet visas och (3) att meddelandet har rätt 
textinnehåll. Skulle det vara fel på någon av dessa tre visar testet exakt 
vad som fallerar.

4. I uppgift tre för workshop sju ställs frågan om lämplig detaljnivå på 
testerna. Om du hann med att göra den uppgiften, vilken detaljnivå landade du på
och varför? Om du inte har gjort uppgiften, skriv in Gherkin för uppgiften 
   ("Lending out a book") på lämplig nivå här och motivera ditt val.

Jag har valt denna utformning av en Gherkin:
Scenario: Lending out a book
Given the user logs in using username "johndoe" with password "iamgod"
And the user "johndoe" has the role of an administrator
When the user enters book 1234
And enters user id 5678
Then the book 1234 should be noted as lent out to user 5678

Jag har valt att ta bort "And the user clicks on the login button" och "And 
clicks on the button that lends out the book" då dessa detaljer kanske stör 
och/eller att man måste uppdatera testerna när placering av saker ändras. 
Kanske sitter inte login-knappen på samma plats längre, kanske ser 
utlåningsformuläret lite annorlunda ut. Det är inte heller detaljer som är så 
intressanta för kunden. Däremot kan det vara bra för kunden att förstå att en 
användare kan ha olika roller med olika befogenheter, exempelvis 
administratörsrollen, vilken jag la till. 

Det är med lite tveksamhet jag inkluderar detaljer som bok-id och 
användar-id i min Gherkin, då jag tycker att det finns fördelar och nackdelar 
med att inkludera tekniska detaljer såsom att en utlåning sker genom att man 
knyter ett användar-id till ett bok-id. Det är ett högst relevant krav från 
kunden att man har ett robust utlåningssystem, där det måste finnas logik 
som säkerställer att böcker knyts till låntagare, men hur den logiken 
implementeras rent tekniskt behöver kanske inte kunden veta. Kanske hade 
följande rader kunnat räcka? 

When the user lends a book with a specific ID to another user
Then the book should be marked as lent out to that user

Samtidigt kanske det finns en poäng i att inviga kunden i programmerarnas 
implementering av logiken i de fall där tekniken eller detaljerna inte är 
komplicerade, såsom är fallet ovan (och därav valde jag att trots allt ta med 
den biten). På ett liknande sätt kan man resonera med inkluderande av 
exempeldata (såsom "johndoe") i testerna, där det också finns för- och 
nackdelar. En fördel är att en feature blir mindre abstrakt för kunden och det 
blir lättare att föreställa sig vad som sker. Samtidigt kan man kanske tycka 
att det är lite snyggare med generiska placeholders som <username>.
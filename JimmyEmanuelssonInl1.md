1. Berätta om dina val av tester för Username och motivera varför du valt dem. 

Jag har valt att skriva fyra grundläggande tester för Username. Det första 
testet skickar in fyra olika testvärden (string) som har godkända tecken men 
inte uppfyller längdkravet för Username (ska inte lyckas). Det andra testet 
skickar in två testvärden med rätt längd men med otillåtna tecken (ska inte 
lyckas). Det tredje testet skickar in två testvärden som ska gå igenom, med 
olika varianter av mixade tillåtna tecken. Det fjärde testet testar om Username 
med exakt fyra tillåtna tecken går igenom (vilket ska lyckas). 

Är det ett rimligt och bra urval av tester? 

Testerna täcker in flera vanliga felfaktorer som fel typ av tecken, fel 
längd, men också ett urval av olika fall med tecken-kombinationer som ska 
passera. Jag testar för gränsvärden, så som att fyra tecken långa kombinationer 
(med giltiga tecken) ska passera, medan tre inte ska det. 

Är det något du i efterhand tycker borde läggas till eller tas bort? 

I efterhand tänker jag att jag kanske borde ha skrivit ett test som 
kontrollerar att validate-metoden hanterar null på ett rimligt sätt (vilket 
också förstås i så fall eventuellt behöver läggas till i själva koden). Ett 
annat rimligt testfall vore att testa väldigt långa Username då det i 
krav-specifikationen för närvarande inte finns någon begränsning i antalet 
tecken (en begränsning som kanske borde finnas - det är svårt 
att se nyttan med orimligt långa Username).  

2. Vad anser du själv om skillnaderna mellan asserts från JUnit och de som 
erbjuds av AssertJ? Är det värt att plocka in ett extra bibliotek för detta 
eller är det bara onödigt? 

Asserts används för att verifiera testresultat. En viktig skillnad mellan 
asserts från JUnit och de från AssertJ är att AssertJ erbjuder en mer läsbar 
syntax som påminner om vardagligt språk: assertThat(x).isEqualTo(y) är 
helt enkelt mer läsbart för människor än assertEquals(x, y). Det gör att man, 
särskilt i en komplex kodbas, snabbare kan se och förstå vad testet försöker 
kontrollera. 

3. Givet det ursprungliga utseendet hos metoden register i UserDao, vad hade 
varit ett bra och rimligt urval av tester för den metoden? Ge exempel på tester 
och testdata.

Eftersom den ursprungliga register-metoden är spretig och innehåller många 
olika saker, såsom lösenord-encoding, ersättning av vissa tecken samt 
interaktioner med databasen, blir det lite svårare att skriva bra tester. 
Men en strategi är att försöka isolera olika beståndsdelar och testa dem.
Exempel på test för replace-funktionen skulle kunna bestå i att mocka 
databas-relaterad kod, och password-encoding för att sedan testa att skicka in 
ett namn som Ian O'Toole och kontrollera så att ' blir ersatt av \\'. 







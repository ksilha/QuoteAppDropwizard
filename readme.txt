Quote of the Day (Dropwizard) REST API

Run Application
java -jar QuoteAppDropwizard-1.0-SNAPSHOT.jar server QuoteApp.yml

Function		Method			URL

findRandomXML		Get			http://localhost:8080/api/quotes/random/xml
findRandom		Get			http://localhost:8080/api/quotes/random
create			Post			http://localhost:8080/api/quotes
edit			Put			http://localhost:8080/api/quotes
remove			Delete			http://localhost:8080/api/quotes
findXML			Get			http://localhost:8080/api/quotes/{id}/xml
find			Get			http://localhost:8080/api/quotes/{id}
findAllXML		Get			http://localhost:8080/api/quotes
findAll			Get			http://localhost:8080/api/quotes/xml


Quote of the Day (Dropwizard) Web Application

http://localhost:8080/


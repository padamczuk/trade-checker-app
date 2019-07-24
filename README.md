# trade-checker-app
1. Perform gradlew clean build
2. Application image (package) ready to run/install is generated in build\distributions directory
3. Unpack app-dist-1.0.zip
4. Content :
 start.bat - runs app in background
 stop.bat - connects to background application process via jmx and stops it.
 jar files - app and dependenies
5. The service is exposed under: http://localhost:8080/trade-checker-app/rest/tradeChecker url.
6. To verify if it is working use chrome browser with restlet plugin installed (see restlet-example.png).

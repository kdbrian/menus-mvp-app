## Menus Mvp

>> Concept: The problem am trying to provide a solution for is when someone wants to order food for example in a
>   local restaurant; they have to either call in, visit the restaurant, geus, download a pdf or use other means to access the restaurants
> menu feed. Am centralizing this through the menus software system aimed at bringing together restaurants and diversifying the contents to allow
> users and clients have the flexibility of not knowing what they eat first hand and explore different tastes and cuisines once they start using the app.


# Disclaimer
> All the data either locally in the users phone through customizations and sharings are uploaded to the app server for simplicity of access and 
> to engage the user and make content more diverse. None of which is harmful to either parties. If you sign up, no fremium gatekeeping.



# How to :

1. Clone the project through github 
    ```bash
        git clone git@github.com/kdbrian/menus.git
    ```
2. Run the menus server
   ## Checklist
   -Install maven, used to build the project
    > maven is a build tool like gradle. I chose it for its light weight nature but gradle will work too
   - Install Jdk any latest version will work. I used ```bash java17```.
   - Open the application.properties and fill in the required fields; including the db url.
   - Run ```bash mvn clean install```
   - Access the api endpoints as specified
   - The api docs at the openapispec.yaml file
   
3. Run the android app
    - Open the menus/MenusMvp folder from android studio
    - Add the following properties to your <code>local.properties</code>
      - serverUrl - The server base url, as the protocol, host and port.
      - graphPath - The endpoint to the graph application, set via the application .properties in the server.
      - serverHealthUrl - The actuator endpoint for checking the app status
    - If using a local server make sure to whitelist the server's Ip address via the <code>res/xml/network_security_settings.xml</code> 
    - Wait for a clean build.
    - Attach your device and run the app
   

# Run
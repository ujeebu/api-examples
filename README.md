## Lexper API
 Lexper is a set of APIs that provide developers with extraction, text analysis and language detection capabilities.

 [check demo](https://lexper.com/demo)

## Lexper examples:

 - **Java**

    To run the Java example we use the [Apache HttpClient](https://hc.apache.org/downloads.cgi) library to make the API request and the [Gson](https://github.com/google/gson) library to process the JSON received.

    ```bash
         cd java
         ## compile java class
         # for unix or mac
         javac -cp ".:./lib/*" .\RequestExample.java
         # for windows
         #javac -cp ".;./lib/*" .\RequestExample.java

         ## run the code
         # For unix or mac
         java -cp ".;./lib/*" RequestExample
         # for windows
         #java -cp ".:./lib/*" RequestExample
    ```

 - **Python**
    ```
    cd python
    # install requests library
    pip install  -r requirements.txt
    python requests-example.py
    ```
 - **Javascript**
    ```bash
    cd js
    # install request package
    npm install
    node  request-example.js
    ```
  - **PHP**
    ```bash
    cd php
    php request-example.php
    ```
  - **Ruby**
    ```bash
    cd ruby
    # install httpparty gem
    gem install httparty
    ruby http-party-example.rb
    ```
  - **C#**
    ```bash
    cd c#/cs-example
    # add needed packages
    dotnet add package RestSharp --version 106.2.1
    dotnet add package Newtonsoft.Json --version 11.0.1
    # run
    dotnet run
    ```


# 1. Compile java class

- For Windows 
    ```bash
     javac -cp ".;./lib/*" .\RequestExample.java 
    ```
- For unix or mac
    ```bash
     javac -cp ".:./lib/*" .\RequestExample.java
    ```


# 2. Run the code

- For Windows
    ```bash
        java -cp ".;./lib/*" RequestExample
    ```
- For unix or mac
    ```bash
        java -cp ".:./lib/*" RequestExample
    ```
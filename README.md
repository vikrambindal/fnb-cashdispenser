# Cash Dispenser

Technology Stack: Java 1.6+, Android, JUnit 4
IDE: Android Studio (MobileClient), Eclipse (TcpServer)
NOTE: Enterprise frameworks such as Spring was not used on server as it would be redundant in this case. Ionic framework did not really had a good plugin for Tcp/Ip communication with the server, and the existing plugins community support was not that big, as a result the program is developed using Android in Java.

Assumptions Made:
As per the requirement, it was not possible to achieve certain amounts as the canisters did not have 1 cent in it. For completness of the program, assumption was made to have 1cent canister as well, so that all the denominations dispensed could be achieved. Without having a 1 cent, this would not be possible. 

Program:
Server: The main application file (CashDispenseServer) is responsible for starting the server.
Client: The LoginActivity is the main starting file for the Android platform.
Application: Login to the application on mobile with credentials for both username and password as demo

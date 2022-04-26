# Note-Management-Web-App-API

This is an API that will be used by another application. This is a "backend" to a privacy-focused note management web application. The app can be accessed by going to https://spy-notes.rk0.xyz/. The way this web application works is that there is some code that runs on the browser (the "frontend") and code that runs on a server (the "backend"). That frontend code will be in charge of presenting information to the user, as well as handling animations, input validations, and some other UI responsibilities. The backend code is often responsible for things like centralized data storage and authentication. Intuitively, having data stored in a central location is what lets you log into an app on your phone, create some content, and then be able to log in with a totally different computer and continue working.

This is the backend data-storage code for the note-taking web application mentioned above. The user can call to create users, fetch users list, create notes under a user id, fetch the list of notes under a user id, or delete a note under a user id. The user can also call this api to generate a QR code for a user id.

# API Screenshots:
User is able to call this api to create a user id, this user id as well as the time it was created will be stored into dynamoDB:
![usercreation](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/usercreation.png)

User can also call /users/{userid}/qr to generate a QR code representing the user id:
![](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/src/main/resources/QRCode.png)

The user can also make call to fetch the list of users stored in dynamoDB:
![fetchusers](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/fetchusers.png)

User can also create note under a user id, this note will be store in a separate table in dynamoDB:
![notecreation](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/createnotes.png)

Also user can fetch the list of notes under a user id:
![fetchnotes](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/fetchnotes.png)

The user can also delete any note from the user id, the note will be removed from the dynamoDB table:
![deletenotes](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/deletenotes.png)

Below are screenshots of items inside the two dynamoDB tables(user table and note table):
![usertable](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/useridtable.png)
![notestable](https://github.com/ShuaoC/Note-Management-Web-App-API/blob/main/pics/notestable.png)


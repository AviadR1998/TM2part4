# TM2part4

## How to start

First clone our project by the shell command git clone https://github.com/AviadR1998/TM2part4.git Then open android studio, click on <br/> file->open->client(the client folder within your cloned folder)
Afterward, open `cmd` or `shell` and change dir to the server directory and now we should use some installs here. `npm i express`, `npm i socket.io`, `npm i mongodb`, `npm i jsonwebtoken`, `npm i body-parser`, `npm i socket.io-client`.<br />
to start the server use the command `node app.js` from within the server folder.<br />
for the client side just play an `emulator` or connect a phone to your pc and reun it via android studio.<br />

## Ex1
In this part we want to design 3 pages and a popup to add contact member.<br />
The pages in our chat web are `Register page`, `Login page` and `Chat page`<br />
the `Popup` will used us to add new contacts to the user. <br />

## Login Page
the Login page will let our user to insert his Username and his Password and enter to his profile by press on the `Login` button.<br />
if the user doesn't have a user he can register by clicking the link `Register here!` in the bottom of the page. <br />

### Design
in this page we used Bootstrap features for the design of the fields and the Login button. All our contents in a card and inside the card we put <br />
1) two fields - `Username` field and `Password` field and for each field we add a hint and an icon to illustrate what need to be insert in the followed field. we covered the password's notes for the privacy of the user.<br />
2) Login button designed by bootstrap.<br />
3) a link to the `Register page`.<br />
4) at the top of the page we have toggle button to change to dark/light mode. (not working in this version)<br />

### image for illustration:

light mode:<br />
![loginLight](https://user-images.githubusercontent.com/117992376/235321049-a100ed9f-573b-46d7-a3d2-0ba18249bad8.png)

dark mode:<br />
![loginDark](https://user-images.githubusercontent.com/117992376/235321055-0def40dc-7b68-4719-b894-adfc16feea99.png)

## Register Page
the Register page will let our user to insert details to open for him a account that will serves him to use our chat web. the user need to insert his Username, his Password, Confirm his password, Display name and a picture of himself. he would enter to his new profile by press on the `Register` button. if the user already has a user he can login by clicking the link `Login here!` in the bottom of the page. <br />

### Design
in this page we used Bootstrap features for the design of the fields and the Register button. All our contents in a card and inside the card we put <br />
1) two text fields - `Username` field and `Display name` field and for each field we add a hint and an icon to illustrate what need to be insert in the followed field.<br/>
2) two password fields - `Password` field and `Confirm Password` field and for each field we add a hint and an icon to illustrate what need to be insert in the followed field. we covered the notes for the privacy of the user. <br />
3) image field - also here we have an icon to illustrate the field. also when the user upload an image this image will upload and shown herself to the user so he could look how it will be shown in our chat. (NOTE!! this feature will not work in this part because we don't have `JS` instead we put a generic photo) <br />
4) Register button designed by bootstrap<br />
5) a link to the `Login page`<br />
6) at the top of the page we have toggle button to change to dark/light mode. (not working in this version)<br />

### image for illustration:

light mode:<br />
![registerLight](https://user-images.githubusercontent.com/117992376/235321059-1d4e430b-9f40-4eeb-abd1-407d4dd9a008.png)

dark mode:<br />
![registerDark](https://user-images.githubusercontent.com/117992376/235321062-844ef63e-9714-4631-8461-81949a8964a4.png)

## Chat page
In the `Chat page` the user could chat with his contacts, add new contacts to his list of contact and then talked with this contacts if they exist. the user could talk to couple contacts in diffrent chats by writing to text bar and press the `send` icon the the message will send to the contact the user want to send to. Also the user will shown all the messages his contacts send to him. the user could replace between different chats by clicking on another contact in the left side, and by that talk with diffrent people. if the user will press on the `add people` icon he could add more contacts to his list. the user can logout back to the `login page` if he wanted to.<br />

### Design
in this page we used Bootstrap features for the design our page. All our contents in a card we split this card into two parts.<br />
part 1 - this is the left side of the card in this part the user will show: <br />
1.1 the `image` and the `dispaly name` he choose for himself.<br />
1.2 icon for `add contact` to talk to.<br />
1.3 all the contacts he can talk to are arranged in a list and in each item in the list will be a diffrent contact. each item shown the contact's image, name, the last message that sent in this conversation and the date and the hour of their last message in this conversation. The selected conversation will filled with special color to distinguish this conversation from the other, if we stand on another conversation the item will be filled with the same special color but disapear whe we leave.<br />
1.4 a toggle buttom to switch to light/dark mode.<br />
part 2 - this is the right side of the card the user will show: <br />
2.1 the `display name` of the contact's user he talked with him in a given conversation. this name will be presented in the top of the conversation in center of the row. <br />
2.2 all the messages in a given conversation with the same contact all the masseges will be in a list and for each item will shown the context of the message the near to it the picture of the mesaage's sender. the user messages will be in the right side of this part and his picture will be on the right side of the whole message, The other contact will be in the left side of this part and his picture will be on the left side of the whole message. <br />
2.3 new messages will add to the bottom of the list. <br />
2.4 in the bottom of this part we have a input text field with hint `Write something` where the user write down his message and when he finish to write his message he will click on the button rigth next to him (the one with the `send` icon inside him). we used bootstrap to design this button and text field. <br />

### image for illustration:

light mode:<br />
![chatsLight](https://user-images.githubusercontent.com/117992376/234992980-59bef26e-f067-4e6f-82ee-18127b29802b.png)

dark mode:<br />
![chatsDark](https://user-images.githubusercontent.com/117992376/234992956-13543601-e5f1-43ee-87c3-4830492f14d5.png)

## Popup
In the Popup we will let the user add Contact member that the user want to chat with and click `Save` at the end.<br />

### Design
in this page we used Bootstrap features for the design

### image for illustration:
<img alt="רררר" src="https://user-images.githubusercontent.com/117992376/234983402-b40769a4-47d2-47d2-8e7a-afe23b205c72.png">

## icon
we have icon in our project beside the title. <br />
<img width="160" alt="רררר" src="https://user-images.githubusercontent.com/117992376/234972459-aeb7b02a-3cd5-46b3-8790-73ebcc5806ca.png">

(Note: we want to add more some features to our web but we were restricted and we couldn't use `JS`.) 
<br/>
# Ex1-part2 <br/>

## Description
in this part we need to activate some of our application code from the previous part by using react. we took our html page and translate it into components so that the code will fit into the react language.<br />

### Register Page
In the `register page` the user creates a new account. for this he needs to complete 5 fields:
1. `username` - here the user needs to enter his username. the username contains only letters and numbers <br />
2. `password` - here the user needs to enter his password. the password should contain at least 8 numbers or letters an at least 1 capital letter and at least 1 number<br />
3. `confirm password` - here the user needs to repeat his password to make sure he writes his password correctly.<br />
4. `display name` - here the user enter the name he wants that his contacts will see. he needs to enter up to 10 numbers or letters<br />
5. `display picture` - here the user upload the photo he wants that his contacts will see.<br />
before you click the `Register` button the user can stand with the cursor on each one of the icons an see what he needs to enter in each field. but after he pressed on the `Register` button this feature gone but where the user coreect there will be a message `Excellent` otherwise will shown a message that will tell why he failed in that field<br />

#### example for failing in register:
![regerr](https://github.com/NirAkay/TM2part2/assets/117992376/3e4b2499-1fe9-47ab-b434-db33e7f525d6)

### Login Page
In the `login page` the user needs to enter his `username` and `password` if he fullfiled these fields correct and press on the `Login` button he will transfer to the `chat page`. Otherwise, a message will apear on the screen that says `Username or Password are incorrect. please try again.` in red color. If the user want to open a new account he can press on the `Register here` link to transfer to the `Register page`.<br />
#### example for failing in login:
![new-pic-login](https://github.com/NirAkay/TM2part2/assets/96340949/e03cad46-8136-4cd9-8b18-132577b3597c)


### Chat Page
in this page the user can chats with his contacts, add new contacts and swich between the conversations.< br/>
this page divide into two parts: <br />
in the left side we have all the contacts the user talks to, the last message that send in that conversation and it's date. if we enter to each conversation we will see all the last messages that sent.All of the messages will appear in the right side of the screen with the pictures of the one who send the message next to it the user can add new text messages by insert text in the text field below and click on the `send` button. the messages of the user will be on the rigth side and the one who he talked with on the left side. also on the left side of the screen we will find the `add contact` icon, if the user will press on it a popup will open and the user could insert a new contact and by pressing on the `save` button he will add him to his `contacts list` the user could logout by clicking on the `Logout` button and it will transfer him to the `Login page` if he want to.<br />


![new-chats](https://github.com/NirAkay/TM2part2/assets/96340949/8cf8b882-8a5d-415c-af2d-6c1a532f2147)



### Flow of normal submition
our project will beggin in the `Login page` if the user have an account he could enter his `username` and `password` and login to the `Chat page` othewise click on the `Register here!` link and transfer to the `Register page` then he could insert his details about himself and press on `Register` button if he inser correct details he will transfer back to the `Login page` otherwise he should fix the incorrect fields. then he will insert his new username and password and will transfer to the `Chat page` there he could talks with his contacts and even add new one and at the end if he want he could logout with the `Logout` button.


### App flow example
![new-pic-login](https://github.com/NirAkay/TM2part2/assets/96340949/5dcbc298-14a1-43c3-8281-2c7c4145ed32)


#### after click on dark mode switch
![new-login-dark](https://github.com/NirAkay/TM2part2/assets/96340949/b88845d4-f61c-47a4-997c-34cab66709d8)<br/><br/>



#### after click on Register here
![new-register](https://github.com/NirAkay/TM2part2/assets/96340949/d67cb03b-34ef-4d03-aafd-c5b99f14a4a0)<br/><br/><br/>

![new-good-login](https://github.com/NirAkay/TM2part2/assets/96340949/d2f5c62d-403a-4c99-bf58-325556906ee3)<br/><br/><br/>


![new-chats](https://github.com/NirAkay/TM2part2/assets/96340949/be889118-8856-4d22-85af-a1c2a512a6c5)<br/><br/><br/>


### App design
our application have 2 design modes. `light-mode`, which is the default mode, and `dark-mode`. The user may change the page style by simply click on the dark mode
switch located up in register or login pages and left down in chat page.


## Ex2

### Description 
in this part we take our project from the last part and make him to work with server. also we use Mongodb database that will work with the server and help us the save data about our users.<br />

### Register 

on the `register` page the client side only validate if the field are right (see the previous part for more info about our valdittions). and in the server we check whether the username is already exist in our database or not. if all the fields indeed valid and the `username` doesn't exist in our database the web will move to the `login` page.<br />

### Login 

on the `login` page the client will insert his `username` and `password` and this fields will transfer to the server. if this fields exist in the database the server the web will move to the `Chat` page, otherwise a message `Username or Password are incorrect. please try again.` will appear on the page.<br />

### Chat 

on the `chat` page the client will add conatcts and if they are exist in the database the contact will add to the list of contacts. when the user sent a message to this contact the message he sent will transfer to the database and then will transfer to this contact. our user will recive the messages in the moment his contact sent it to him - live updating.<br />

### Server
the server will run endlesslesy and will respone to requests from users that connects to it. the server is responsible to send messages to the contacts from the other side of the chat.<br /> 

### New feataures
this time we added the client-server functionality, a live add contact, send new message updating and even multiple tabs open on the chat screen of the same user will update any changes one of them makes!

### Notice
our server is not suport an uploading of big profile pictures, so to make it convenience to use please make sure you are upload small ones and keep it simple.
have fun using our app!

### Images for ilustration of chat -

![chat1](https://github.com/NirAkay/TM2part3/assets/117992376/068151da-a65a-4728-963c-5085967a3fdf)
![chat2](https://github.com/NirAkay/TM2part3/assets/117992376/6ca80ccd-6c4c-4dac-bfed-4fd7d4da45d1)
![chat 3](https://github.com/NirAkay/TM2part3/assets/117992376/e4850fc7-1351-4764-8e8c-6c8de934a664)
![chat4](https://github.com/NirAkay/TM2part3/assets/117992376/f1dea50b-78f0-4cb6-946e-6a7895470e53)
![chat5](https://github.com/NirAkay/TM2part3/assets/117992376/651cd676-22c4-4250-a582-6d8a49dc2da5)





## Ex3
### Description
In this part we took our project from the last parts and we make an app for android by using the platform of android studio. <br />
we add some features to our app that weren't apear in the last parts like settings page and push notification messages when a contact send a message we send a proper notification using firebase to the user. <br />

### Settings page
In this page the user got 2 options to change as he likes. server link and night mode.
`Server change` - the user can change the `url` of the server, this preference will save for the next app runinngs as well.
`Night mode` - the user can change the app theme mode from light to dark(night) mode, this preference will save for the next app runinngs as well.

#### Note
as default the server url is set to `http://10.0.2.2:5000/api/`
<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/bc3f855a-27a5-4636-be5e-e06503c627d7">

### Push Notifications
in this part we create a notification that will pop up every time a contact will send you a message. the notification will receive with the displayname in the title and the message in the body and with icon of a bell. the notification will recieved even when the app is closed.<br />
image for ilustration:<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/fe1ad8d8-3681-4379-bff2-7f8e990aed40">

### About Push Notifications
in some android devices, our app notification setting is off by default. to make sure your device supports 2chat push notifications. go to settings,
search for Notifications and look up for 2chat app and turn on all the push notifications settings for our app. good luck.

### Register
in this part the user completes the fields and clicks on `SIGN UP` the data will validate(the valildation from the last part) and then transfer to the server if the username exist in our DB the server return an error otherwise the user will transfer to the `login` page.<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/525a3bc4-9060-45ec-9cdd-b4755f0eb5f5">

### Login
in the `login` page the user insert his username and password and this details will transfer to the server and will checked with the DB and if he insert the correct details he will transfer to the `chat` page otherwise an error message will recived. <br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/69a6d274-b826-47da-ae10-1db83cee27f4">

### Chats
in this page we will talk to a local DB and to the server. each time we will enter to this page the app will load at first our data from the local DB and in the background we will load our data from the DB that on the Server and update the local DB. this page will show us all the cahts we have with our contacts(their displayname, profilepic, the last message in the chat and the date this message sent). we can click on a chat and transtfer to the `message` page were the messages are shown or click on the `back` button to logout from the `chat` page and transfer to the `login` page. Also, we can click on the `add contact` button to transfer to the `AddContact` page in order to  add a new chat.<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/7c7f91fe-2d1b-4a9f-82d0-9120a06ded89">

### Add contact 
in this page the user could create a new chat by sending the username to the server. if the username is exist we will transfer back to the `chat` page otherwise an error message will appear on screen. the user can press on the back button to return to the `chat` page<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/d84bfc61-7a8a-404f-8a50-8ead77c6a504">

### Messages
in this page the user will see all of his messages in this chat. when the user will send/receive message this message will show to him on the screen. the user can send a message by write a message and click on the `send` button. each time the page will upload the app first will read the data in the local DB and in the background the page will load the data from the server DB and update the local DB.<br />
<img width="200" alt="mitk" src="https://github.com/AviadR1998/TM2part4/assets/117992376/93c5438d-cf74-4792-a9ee-a78e019c651e">


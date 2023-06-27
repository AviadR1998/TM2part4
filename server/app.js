import express from 'express';
import bodyParser from 'body-parser';
import usersRouter from './routes/users.js';
import tokensRouter from './routes/tokens.js';
import chatsRouter from './routes/chats.js';
import { handleClientMsg } from './sockets/socket.js'
////
//import jwt from "jsonwebtoken";
//const key = "my secret key";
//console.log(jwt.sign("nir", key));
////
import cors from 'cors';
const app = express();

import http from 'http';
const server = http.createServer(app);
import { Server } from "socket.io";

var socketArr = new Map();
const io = new Server(server, {
    cors: { origin: "http://localhost:3000" }
});

export const myIo = io;
export const arrSoc = socketArr;
io.on('connection', (socket) => {
    socket.on('username', (username) => {
        socketArr.set(socket, username);
    })
    socket.on('disconnect', () => {
        socketArr.delete(socket);
    });

    socket.on('clientSentMessage', (data) => {
        handleClientMsg(socketArr.get(socket), data, socket);
    })

});

var androidTokensMap = new Map();

export const androidTokens = androidTokensMap;


app.use(cors());
app.use(bodyParser());
app.use(express.static('public'));
app.use('/api/Users', usersRouter);
app.use('/api/Tokens', tokensRouter);
app.use('/api/Chats', chatsRouter);

server.listen(5000);
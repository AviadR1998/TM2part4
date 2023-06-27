import { MongoClient, ObjectId } from "mongodb";
import functions from "./tokens.js"
import { myIo, arrSoc, androidTokens } from "../app.js";

async function getUserChats(bearer, token) {
    const data = functions.validateToken(bearer, token);
    if (data == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        await client.connect();
        const db = client.db('TM2');
        const chats = db.collection('Chats');
        let results = await chats.find({ "users.username": data.username }).toArray()
        let finalRes;
        if (results.length === 0) {
            return [];
        } else {
            finalRes = results.map((chat, key) => {
                let num = 0;
                if (chat.users[0].username == data.username) {
                    num = 1;
                }
                return {
                    id: chat._id, user: {
                        username: chat.users[num].username, displayName: chat.users[num].displayName,
                        profilePic: chat.users[num].profilePic
                    }, lastMessage: chat.messages.length > 0 ? {
                        id: chat.messages[chat.messages.length - 1].id,
                        created: chat.messages[chat.messages.length - 1].created, content: chat.messages[chat.messages.length - 1].content
                    } : null
                }
            });
            return finalRes;
        }
    } catch {
    } finally {
        await client.close()
    }
}

async function deleteChatById(bearer, token, chatId) {
    const data = functions.validateToken(bearer, token);
    if (data == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        await client.connect();
        const db = client.db('TM2');
        const chats = db.collection('Chats');
        results = await chats.deleteOne({ _id: chatId, "users.username": data.username });
        if (results.deletedCount === 0) {
            return 404;
        } else {
            return 204;
        }
    } finally {
        await client.close()
    }
}

async function getChatById(bearer, token, chatId) {
    const data = functions.validateToken(bearer, token);
    if (data == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        await client.connect();
        const db = client.db('TM2');
        const chats = db.collection('Chats');
        let results = await chats.find({ _id: new ObjectId(chatId), "users.username": data.username }).toArray()
        let finalRes;
        if (results.length === 0) {
            finalRes = 401;
        } else {
            let mapRes = results[0].messages.map(msg => {
                return ({
                    id: msg.id, created: msg.created, sender: {
                        username: msg.sender.username, displayName: msg.sender.displayName,
                        profilePic: results[0].users[0].username === msg.sender.username ? results[0].users[0].profilePic : results[0].users[1].profilePic
                    },
                    content: msg.content
                })
            })
            finalRes = {
                id: results[0]._id, users: results[0].users, messages: mapRes
            }
        }
        return finalRes;
    } catch {
        return 401;
    } finally {
        await client.close();
    }
}

async function createChat(bearer, token, recieverData) {
    const senderData = functions.validateToken(bearer, token);
    if (senderData == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        //const client = new MongoClient("mongodb://127.0.0.1:27017");
        client.connect();
        const db = client.db('TM2');
        const users = db.collection('Users');
        if (senderData.username === recieverData.username) {
            return 400;
        }
        let recRes = await users.find({ username: recieverData.username }).toArray()
        if (recRes.length === 0) {
            return 400;
        }
        let senRes = await users.find({ username: senderData.username }).toArray()
        if (senRes.length === 0) {
            return 401;
        }
        const chats = db.collection('Chats');
        const finalJson = {
            users: [{ username: senderData.username, displayName: senRes[0].displayName, profilePic: senRes[0].profilePic }
                , { username: recieverData.username, displayName: recRes[0].displayName, profilePic: recRes[0].profilePic }],
            messages: []
        };
        const res = await chats.insertOne(finalJson);
        if (res.acknowledged === true) {
            return {
                id: res.insertedId, user: {
                    username: recieverData.username, displayName: recRes[0].displayName,
                    profilePic: recRes[0].profilePic
                }
            }
        } else {
            return 401;
        }
    } catch {
        return 400;
    }
    finally {
    }
}

function addZero(num) {
    return num < 10 ? '0' + num : num;
}

async function getMessP(newText, bearer, token, id) {
    const data = functions.validateToken(bearer, token);
    if (data == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        client.connect();
        var date = new Date();
        var fullDate = addZero(date.getFullYear()) + '-' + addZero((date.getMonth() + 1)) + '-' + addZero(date.getDate()) + 'T' +
            addZero(date.getHours()) + ':' + addZero(date.getMinutes()) + ':' + addZero(date.getSeconds()) +
            '.' + addZero(date.getMilliseconds());
        const db = client.db('TM2');
        const users = db.collection('Users');
        const chats = db.collection('Chats');
        let user = await users.find({ username: data.username }).toArray();
        let chat = await chats.find({ _id: new ObjectId(id) }).toArray();
        let myMessages = chat[0].messages;
        let retMessage = { id: myMessages.length, created: fullDate, sender: { username: user[0].username, displayName: user[0].displayName, profilePic: user[0].profilePic }, content: newText }
        let newMessage = { id: myMessages.length, created: fullDate, sender: { username: user[0].username, displayName: user[0].displayName }, content: newText }
        myMessages.push(newMessage);
        await chats.updateOne({ _id: new ObjectId(id), "users.username": data.username }, { $set: { messages: myMessages } });
        var otherUsername, senderUsername;
        if (chat[0].users[0].username === data.username) {
            otherUsername = chat[0].users[1].username;
            senderUsername = chat[0].users[0].username;
        } else {
            otherUsername = chat[0].users[0].username;
            senderUsername = chat[0].users[1].username;
        }
        arrSoc.forEach((username, socketId) => {
            if (username === otherUsername) {
                myIo.to(socketId.id).emit('newMsg', { chat: { id: chat[0]._id, user: { username: user[0].username, displayName: user[0].displayName, profilePic: user[0].profilePic }, lastMessage: retMessage }, msg: retMessage });
            }
        })
        const serverKey = 'AAAASTrmcZI:APA91bHTvyxT7R8WlraIR-n8LOecluXrEK_utjLUGvettTyAw4_5Wv8Ed0cAJqgPQnouaHZYDLtUNMwdIF128fzVCHrdeuc5P9sME0vVfckCNPMt5bS-zgLRRCqNxRg5UL06LIyGmaPq';
        androidTokens.forEach(async (username, token) => {
            if (username === otherUsername || username === senderUsername) {
                let myMsgCheck = "0";
                if (username === data.username) {
                    myMsgCheck = "1";
                }
                let notificationReg = {
                    to: token,
                    data: {
                        'chatId': id,
                        'fullDate': fullDate,
                        'username': senderUsername,
                        'check': myMsgCheck,
                    },
                    notification: {
                        title: user[0].displayName,
                        body: newText
                    },
                }

                const fetchRes = await fetch('https://fcm.googleapis.com/fcm/send', {
                    method: 'POST',
                    headers: {
                        'Authorization': `key=${serverKey}`,
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(notificationReg),
                })
            }
        })
        return retMessage;
    } catch (err) {
        return 401;
    } finally {
        client.close();
    }
}

async function getMessG(bearer, token, id) {
    const data = functions.validateToken(bearer, token);
    if (data == null) {
        return 401;
    }
    const client = new MongoClient("mongodb://127.0.0.1:27017");
    try {
        client.connect();
        const db = client.db('TM2');
        const chats = db.collection('Chats');
        let chat = await chats.find({ _id: new ObjectId(id), "users.username": data.username }).toArray();
        if (chat.length === 0) {
            return 401;
        } else {
            return chat[0].messages.reverse();
        }
    } catch (err) {
        return 401;
    } finally {
        client.close();
    }
}

export default { getMessP, getMessG, createChat, deleteChatById, getUserChats, getChatById }
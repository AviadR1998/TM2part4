import myModels from '../models/chats.js'

const getAllChats = async (req, res) => {
    const myRes = await myModels.getUserChats(req.headers.authorization.split(" ")[0], req.headers.authorization.split(" ")[1]);
    if (myRes.status !== 401) {
        res.status(200).json(myRes);
    } else {
        res.status(401);
    }
    res.end();
}

const deleteChat = async (req, res) => {
    let chatId = req.params.id;
    const myRes = await myModels.deleteChat(req.headers.authorization.split(" ")[0],
        req.headers.authorization.split(" ")[1], chatId);
    res.status(myRes);
    res.end;
}

const getChat = async (req, res) => {
    let chatId = req.params.id;
    const myRes = await myModels.getChatById(req.headers.authorization.split(" ")[0],
        req.headers.authorization.split(" ")[1], chatId);
    if (myRes === 401) {
        res.status(401);
    } else {
        res.status(200).json(myRes);
    }
    res.end();
}

const createNewChat = async (req, res) => {
    let chatId = req.params['id'];
    const myRes = await myModels.createChat(req.headers.authorization.split(" ")[0],
        req.headers.authorization.split(" ")[1], req.body);
    if (myRes === 401) {
        res.status(401);
    } else if (myRes === 400) {
        res.status(400);
    }
    else {
        res.status(200).json(myRes);
    }
    res.end();
}

const returnMessagesP = async (req, res) => {
    const myRes = await myModels.getMessP(req.body.msg, req.headers.authorization.split(" ")[0],
        req.headers.authorization.split(" ")[1], req.params.id);
    if (myRes !== 401) {
        res.status(200).json(myRes);
    } else {
        res.status(401);
    }
    res.end();
}

const returnMessagesG = async (req, res) => {
    const myRes = await myModels.getMessG(req.headers.authorization.split(" ")[0],
        req.headers.authorization.split(" ")[1], req.params.id);
    if (myRes !== 401) {
        res.status(200).json(myRes);
    } else {
        res.status(401);
    }
    res.end();
}


export { returnMessagesP, returnMessagesG, createNewChat, getChat, deleteChat, getAllChats };
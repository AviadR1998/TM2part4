import express from 'express'
import { returnMessagesG, returnMessagesP, createNewChat, getAllChats, deleteChat, getChat } from '../controllers/chats.js'
var chatsRouter = express.Router();

chatsRouter.get('/', getAllChats);
chatsRouter.post('/', createNewChat);
chatsRouter.get('/:id', getChat);
chatsRouter.delete('/:id', deleteChat);
chatsRouter.post('/:id/Messages', returnMessagesP);
chatsRouter.get('/:id/Messages', returnMessagesG);

export default chatsRouter;
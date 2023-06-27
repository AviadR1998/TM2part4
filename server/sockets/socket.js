import { myIo, arrSoc } from "../app.js";

function handleClientMsg(nameOfUser, data, socket) {
    arrSoc.forEach((username, socketId) => {
        if (username === nameOfUser && socketId.id !== socket.id) {
            myIo.to(socketId.id).emit('updateChat', data);
        }
    })
}

export { handleClientMsg }
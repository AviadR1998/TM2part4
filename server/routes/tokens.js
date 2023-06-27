import express from 'express'
import { generateTokken, generateAndroidTokken } from '../controllers/tokens.js'
var tokensRouter = express.Router();

tokensRouter.post('/', generateTokken);
tokensRouter.post('/androidToken', generateAndroidTokken)

export default tokensRouter;
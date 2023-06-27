import myModels from '../models/tokens.js'

const generateTokken = async (req, res) => {
    const myRes = await myModels.getTokken(req.body);
    if (myRes.status === 200) {
        res.status(200).send(myRes.tokken);
    } else {
        res.status(myRes.status);
    }
    res.end();
}

const generateAndroidTokken = async (req, res) => {
    // const myRes = await myModels.getAndroidTokken(req.body, req.headers.authorization);
    // if (myRes.status === 200) {
    //     res.status(200).send(myRes.tokken);
    // } else {
    //     res.status(myRes.status);
    // }
    // res.end();
    const myRes = await myModels.getAndroidTokken(req.body, req.headers.authorization);
    res.status(200);
    res.end();
}

export { generateTokken, generateAndroidTokken };
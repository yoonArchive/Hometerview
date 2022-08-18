const express = require('express');
const app = express();
const https = require('https');
const http = require('http');
const cors = require('cors');
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(cors());

app.post('/test', (req, res) => {
  console.log(req.body);
  // console.log(req.body.text);
  // res.send(req.body.text + "인가요?");
});

app.post('/tts/ttsrequest', async (req, res) => {
  const textToSpeech = require('@google-cloud/text-to-speech');
  console.log(req.body);
  // dot env

  require('dotenv').config();
  const fs = require('fs');
  const util = require('util');
  const client = new textToSpeech.TextToSpeechClient();
  const text = req.body.text;
  const request = {
    input: { text: text },
    voice: {
      languageCode: 'ko-KR',
      ssmlGender: 'MALE',
      name: 'ko-KR-Wavenet-D',
    },
    audioConfig: { audioEncoding: 'MP3' },
  };
  const [response] = await client.synthesizeSpeech(request);
  // const writeFile = util.promisify(fs.writeFile);
  // await writeFile("output.mp3",response.audioContent,'binary');
  console.log(response);
  console.log('Text to Speech has completed. Audio file has been saved');

  res.send(response.audioContent);
});

app.listen(9002, function () {
  console.log('listening on 9002');
});

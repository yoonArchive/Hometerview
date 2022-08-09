const express = require('express');
const app = express();

app.listen(9002, function(){
  console.log('listening on 9002')
});

app.post('/ttsrequest', async (req, res) => {
  const textToSpeech = require('@google-cloud/text-to-speech')
  console.log(req);
  // dot env

  require('dotenv').config()
  const fs = require('fs')
  const util = require('util')
  const client = new textToSpeech.TextToSpeechClient()
  const text = "test voice";
  const request = {
    input : {text:text},
    voice : {languageCode:'en-US', ssmlGender:'NEUTRAL'},
    audioConfig: {audioEncoding : 'MP3'}
  };
  const [response] = await client.synthesizeSpeech(request);
  const writeFile = util.promisify(fs.writeFile);
  await writeFile("output.mp3",response.audioContent,'binary');
  console.log(response);
  console.log('Text to Speech has completed. Audio file has been saved');
  let responseData = response.audioContent;
  console.log(responseData);
  res.send(responseData);
})

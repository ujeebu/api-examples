// npm install request

const request = require('request')
const baseUri = 'https://lexper.p.mashape.com/v1.1/extract'
const options = {
    url: 'https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab',  // url to extract
    text: 1, // return extracted text
    html: 1, // extract html
    media: 1, // extract media
    feeds: 0, // do not extract RSS feeds
    images: 1, // extract all images present in HTML
    author: 1, // extract article's author
    pub_date: 1, // extract article's publish date
    js: 0,     // do not run js
    js_wait: 0, // when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
    strip_tags: 'form,style', //  tags to strip from the extracted HTML 
    timeout: 6, // 	request timeout in seconds.
}
const headers = {
    'X-Mashape-Key': '8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R'
}


request({
    uri: baseUri,
    method: 'GET',
    headers,
    qs: options,
},
     (error, response, body) => {
         
        if (error) {
            console.error(error.message);
            return false;
        }
        if (response && response.statusCode !== 200) {
            console.error(`Bad status code ${response.statusCode}`);
        }
        const result = JSON.parse(body)
        console.log(JSON.stringify(result, null, 4)) // pretty-print json

    }
)
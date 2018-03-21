const http = require('https')
const host = 'lexper.p.mashape.com'
const options = {
    url: 'https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab',  // url to extract
    text : 1, // return extracted text
    html : 1, // extract html
    media : 1, // extract media
    feeds: 0, // do not extract RSS feeds
    images: 1, // extract all images present in HTML
    author: 1, // extract article's author
    pub_date: 1, // extract article's publish date
    js : 0,     // do not run js
    js_wait: 0, // when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
    strip_tags: 'form,style', //  tags to strip from the extracted HTML 
    timeout: 6, // 	request timeout in seconds.
}
const headers = {
    'X-Mashape-Key': '8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R'
}

// build query string
const qs = Object.keys(options).map((param) => `${encodeURIComponent(param)}=${encodeURIComponent(options[param])}`).join('&')
const path = `/v1.1/extract?${qs}` 

http.get({
    protocol: 'https:',
    host,
    path,
    headers
}, (res) => {
    const { statusCode } = res;
    let error;
    if (statusCode !== 200) {
        error = new Error('Request Failed.\n' +
            `Status Code: ${statusCode}`);
    }

    if (error) {
        console.error(error.message);
        res.resume();
        return;
    }

    res.setEncoding('utf8');
    let rawData = '';
    res.on('data', (chunk) => { rawData += chunk; });
    res.on('end', () => {
        try {
            const parsedData = JSON.parse(rawData);
            console.log(parsedData);
        } catch (e) {
            console.error(e.message);
        }
    });
   
}).on('error', (e) => {
    console.error(`Got error: ${e.message}`);
});

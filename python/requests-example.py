# pip install requests


import requests
import json

baseUri = 'https://lexper.p.mashape.com/v1.1/extract'
options = {
    'url' : 'https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab',  # url to extract
    'text' : 1, # return extracted text
    'html' : 1, # extract html
    'media' : 1, # extract media
    'feeds' : 0, # do not extract RSS feeds
    'images' : 1, # extract all images present in HTML
    'author' : 1, # extract article's author
    'pub_date' : 1, # extract article's publish date
    'js' : 0,     # do not run js
    'js_wait' : 0, # when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
    'strip_tags' : 'form,style', # tags to strip from the extracted HTML
    'timeout' : 6, # request timeout in seconds.
}
headers = {
    'X-Mashape-Key': '8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R'
}

response = requests.get(baseUri, params=options, headers=headers, timeout=6)

if response.status_code != requests.codes.ok:
    print("Bad status code " + str(response.status_code))

#print(response.text)

jsonResponse = json.loads(response.text)
print(json.dumps(jsonResponse, indent=4, separators=(',', ': ')))

#gem install httparty
require 'json'
require 'httparty'

url = 'https://lexper.p.mashape.com/v1.1/extract'
query = {
    'url' =>  'https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab',  # url to extract
    'text' =>  1.to_s, # return extracted text
    'html' =>  1.to_s, # extract html
    'media' =>  1.to_s, # extract media
    'feeds' =>  0.to_s, # do not extract RSS feeds
    'images' =>  1.to_s, # extract all images present in HTML
    'author' =>  1.to_s, # extract article's author
    'pub_date' =>  1.to_s, # extract article's publish date
    'js' =>  0.to_s,     # do not run js
    'js_wait' =>  0.to_s, # when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
    'strip_tags' =>  'form,style', #  tags to strip from the extracted HTML 
    'timeout' =>  6.to_s, # 	request timeout in seconds.
}

headers = {
    'X-Mashape-Key' => '8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R'
}

options  = {
    query: query,
    headers: headers
}
response = HTTParty.get(url, options)

if response.code != 200
    puts "Bad status code #{response.code}"
end

puts JSON.pretty_generate(response.parsed_response)

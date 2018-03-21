<?php

// base uri
$baseUri = 'https://lexper.p.mashape.com/v1.1/extract';

// request options
$options = [
'url' => 'https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab',  // url to extract
'text' => 1, // return extracted text
'html' => 1, // extract html
'media' => 1, // extract media
'feeds' => 0, // do not extract RSS feeds
'images' => 1, // extract all images present in HTML
'author' => 1, // extract article's author
    'pub_date' => 1, // extract article's publish date
'js' => 0,     // do not run js
'js_wait' => 0, // when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
'strip_tags' => 'form,style', // tags to strip from the extracted HTML
'timeout' => 6, // request timeout in seconds.
];

// request headers
$headers = [
'X-Mashape-Key' => '8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R'
];

// make get request
$response = getRequest($baseUri, $options, $headers);

// pretty print response
print(json_encode(json_decode($response, true), JSON_PRETTY_PRINT)) . PHP_EOL;

/**
 * @param $uri
 * @param $qs
 * @param $headers
 * @return string|null
 */
function getRequest($uri, $qs = [], $headers = []) {

	// build api url with qs
	$apiUrl = $uri . "?" . http_build_query($qs);

	$curl = curl_init($apiUrl);

	// no need headers
	curl_setopt($curl, CURLOPT_HEADER  , false);
	curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

// convert headers to CURLOPT_HTTPHEADER accepted format
	array_walk($headers, function(&$headerValue, $headerName){
		$headerValue = "{$headerName}: {$headerValue}";
	});

	// set headers
	curl_setopt($curl, CURLOPT_HTTPHEADER, array_values($headers));

	// Send the request
	$response = curl_exec($curl);

	if (! $response) {
		print('Error: "' . curl_error($curl) . '" - Code: ' . curl_errno($curl) . PHP_EOL);

		return null;
	}


	// Close request to clear up resources
	curl_close($curl);

	return $response;
}












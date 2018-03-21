using Newtonsoft.Json;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;


namespace cs_example
{
    class Program
    {


        private static readonly HttpClient client = new HttpClient();


        const String BASE_URI = "https://lexper.p.mashape.com";
        const String EXTRACT_ENDPOINT = "/v1.1/extract";
        const String KEY_NAME = "X-Mashape-Key";
        const String KEY_VALUE = "8vKX7VEKR2mshaOFlTBQnqOeGkkep1cgXBfjsnbVLeoliSC01R";

        static void Main(string[] args)
        {
            Console.WriteLine("Starting request to " + BASE_URI);
            Program example = new Program();
            example.RunExample();
            // example.RunExampleAsync().GetAwaiter().GetResult();
        }

        private void RunExample()
        {
            RestClient client = new RestClient(BASE_URI);

            RestRequest request = this.BuildRequest();

            request.AddHeader(KEY_NAME, KEY_VALUE);

            // execute the request
            IRestResponse response = client.Execute(request);
            String content = response.Content; // raw content as string

            if (! response.IsSuccessful)
            {
                Console.WriteLine("Invalid Status Code : " + response.StatusCode);
            }
            Object res = JsonConvert.DeserializeObject(content);
            content = JsonConvert.SerializeObject(res, Formatting.Indented);
            Console.WriteLine(content);

        }

        private RestRequest BuildRequest()
        {
            RestRequest request = new RestRequest(EXTRACT_ENDPOINT, Method.GET);
            request.AddParameter("url", "https://medium.com/financial-times/silicon-valleys-founder-factory-80dade9377ab"); //  url to extract
            request.AddParameter("text", "1");       // return extracted text
            request.AddParameter("html", "1");   // extract html
            request.AddParameter("media", "1");  // extract media
            request.AddParameter("feeds", "0");  // do not extract RSS feeds
            request.AddParameter("images", "1");     // extract all images present in HTML
            request.AddParameter("author", "1");     // extract article's author
            request.AddParameter("pub_date", "1");   // extract article's publish date
            request.AddParameter("js", "0");     // do not run js
            request.AddParameter("js_wait", " 0");   // when JavaScript is enabled, indicates how many seconds the API should wait for the JS interpreter before starting the extraction.
            request.AddParameter("strip_tags", "form,style");    // tags to strip from the extracted HTML
            request.AddParameter("timeout", "6");    // request timeout in seconds.
            return request;
        }


    }
}

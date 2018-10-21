<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class QuoteController extends Controller
{
        public function index()
        {
            $client = new \GuzzleHttp\Client();
            $request = $client->get('https://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1');
            $response = $request->getBody()->getContents();
            
            $resp = json_decode($response);

            foreach ($resp as $r)
            {
                return [
                    'quote' => [
                        'author' => $r->title,
                        'body'  => $r->content
                    ]
                ];
            }
            
        }
}
